package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
//这个界面写成了用表格来展示所有老师的信息了，后续可以修改为老师查看自己教学班上的所有填写的信息
public class TeacherInfoListGUI extends JFrame {
    private JTable table;

    public TeacherInfoListGUI() {
        setTitle("教师个人信息");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        //创建表格
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        //获取数据并填充表格
        try {
            Connection con = MySQLConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tname, tid, tmajor, txueyuan FROM teacher");

            //将数据转换为二维数组
            String[][] data = new String[100][4];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("tname");
                data[i][1] = rs.getString("tid");
                data[i][2] = rs.getString("tmajor");
                data[i][3] = rs.getString("txueyuan");
                i++;
            }

            //将数据填充到表格中
            String[] columnNames = {"姓名", "工号", "专业", "学院"};
            table.setModel(new DefaultTableModel(data, columnNames));

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        //添加表格到界面中
        add(scrollPane);

        //显示界面
        setVisible(true);
    }

    public static void main(String[] args){
        new TeacherInfoListGUI();
    }
}
