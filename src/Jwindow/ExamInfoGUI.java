package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamInfoGUI extends JFrame {

    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;

    public ExamInfoGUI(String sid) {
        setTitle("考试信息");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        String[] columnNames = {"考试编号", "开始时间", "结束时间", "科目名"};

        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);

        try {
            Connection conn = MySQLConnection.getConnection();
            String sql = "SELECT exam.eid, exam.etime_begin, exam.etime_end, exam.ename FROM xuanke, course, exam WHERE xuanke.cid = course.cid AND course.cid = exam.cid AND xuanke.sid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String eid = rs.getString("eid");
                String beginTime = rs.getString("etime_begin");
                String endTime = rs.getString("etime_end");
                String ename = rs.getString("ename");
                Object[] rowData = {eid, beginTime, endTime, ename};
                tableModel.addRow(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 16));
        table.setFont(new Font("宋体", Font.PLAIN, 14));

        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);

        scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(750, 600)); // 设置表格的大小

        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);


        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {

        new ExamInfoGUI("123");
    }
}

