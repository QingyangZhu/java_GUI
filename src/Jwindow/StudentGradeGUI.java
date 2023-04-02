package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;

public class StudentGradeGUI extends JFrame {
    private String studentID;

    private JTable table;
    private DefaultTableModel model;
    private JLabel averageLabel;

    public StudentGradeGUI(String studentID) {
        super("成绩信息");
        this.studentID = studentID;
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格和表格模型
        String[] columnNames = {"成绩编号", "课程名称", "任课教师", "课程成绩", "课程学分"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // 设置表格样式
        table.setRowHeight(30);
        table.setFont(new Font("宋体", Font.PLAIN, 16));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加表格到窗口
        this.add(scrollPane);

        // 添加加权平均分标签
        averageLabel = new JLabel("加权平均分：");
        averageLabel.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(averageLabel, BorderLayout.SOUTH);

        // 查询数据库
        try {

            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT gid, cname, tname, grade, cgrade FROM sgrade, course, teacher WHERE sgrade.cid = course.cid AND sgrade.tid = teacher.tid AND sid = " + studentID;
            ResultSet resultSet = statement.executeQuery(sql);

            double weightedSum = 0;
            double creditSum = 0;
            while (resultSet.next()) {
                String gid = resultSet.getString("gid");
                String cname = resultSet.getString("cname");
                String tname = resultSet.getString("tname");
                String grade = resultSet.getString("grade");
                String cgrade = resultSet.getString("cgrade");

                double credit = Double.parseDouble(cgrade);
                double score = Double.parseDouble(grade);
                weightedSum += credit * score;
                creditSum += credit;

                model.addRow(new String[]{gid, cname, tname, grade, cgrade});
            }

            resultSet.close();
            statement.close();
            connection.close();

            // 计算加权平均分
            BigDecimal average = new BigDecimal(weightedSum / creditSum).setScale(2, BigDecimal.ROUND_HALF_UP);
            averageLabel.setText("加权平均分：" + average.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }


    public static void main(String[] args) {
        String studentID = "123";
        new StudentGradeGUI(studentID);
    }
}
