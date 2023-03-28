package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Course extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JTextField tidField;
    private JButton searchButton;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton editButton;
    private JButton addButton;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public Course() {
        super("Course Information");

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        // 创建组件
        panel = new JPanel(new BorderLayout());
        label = new JLabel("Teacher ID:");
        String username = LoginFrame.userTextField.getText();
        tidField = new JTextField(username,10);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        // 添加组件到面板
        JPanel searchPanel = new JPanel();
        searchPanel.add(label);
        searchPanel.add(tidField);
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.NORTH);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // 显示面板
        add(panel);
        setVisible(true);

        // 初始化数据库连接
        try {
            Connection conn = MySQLConnection.getConnection();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // 查询课程信息
            String tid = tidField.getText();
            String query = "SELECT * FROM course WHERE tid='" + tid + "'";
            try {
                rs = stmt.executeQuery(query);

                // 构造表格模型
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Course ID");
                model.addColumn("Course Name");
                model.addColumn("Department");

                // 遍历结果集并添加到表格模型中
                while (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String cxueyuan = rs.getString("cxueyuan");

                    model.addRow(new Object[]{cid, cname, cxueyuan});
                }

                // 显示表格
                table.setModel(model);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == editButton) {
            // 编辑课程信息
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row.");
                return;
            }

            String cid = (String) table.getValueAt(row, 0);
            String cname = (String) table.getValueAt(row, 1);
            String cxueyuan = (String) table.getValueAt(row, 2);
            // 弹出对话框让用户输入修改后的信息
            JTextField cidField = new JTextField(cid);
            JTextField cnameField = new JTextField(cname);
            JTextField cxueyuanField = new JTextField(cxueyuan);

            Object[] fields = {"Course ID:", cidField, "Course Name:", cnameField, "Department:", cxueyuanField};
            int result = JOptionPane.showConfirmDialog(this, fields, "Edit Course", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newCid = cidField.getText();
                String newCname = cnameField.getText();
                String newCxueyuan = cxueyuanField.getText();

                // 更新数据库
                String update = "UPDATE course SET cid='" + newCid + "', cname='" + newCname + "', cxueyuan='" + newCxueyuan + "' WHERE cid='" + cid + "'";
                try {
                    stmt.executeUpdate(update);
                    JOptionPane.showMessageDialog(this, "Course updated successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to update course.");
                }
            }
        } else if (e.getSource() == addButton) {
            // 添加新课程
            JTextField cidField = new JTextField();
            JTextField cnameField = new JTextField();
            JTextField cxueyuanField = new JTextField();

            Object[] fields = {"Course ID:", cidField, "Course Name:", cnameField, "Department:", cxueyuanField};
            int result = JOptionPane.showConfirmDialog(this, fields, "Add Course", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String cid = cidField.getText();
                String cname = cnameField.getText();
                String cxueyuan = cxueyuanField.getText();

                // 插入数据库
                String insert = "INSERT INTO course (cid, cname, cxueyuan, tid) VALUES ('" + cid + "', '" + cname + "', '" + cxueyuan + "', '" + tidField.getText() + "')";
                try {
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(this, "Course added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to add course.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Course();
    }
}