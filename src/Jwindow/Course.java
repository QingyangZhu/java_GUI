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
        super("课程信息");

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        // 创建组件
        panel = new JPanel(new BorderLayout());
        label = new JLabel("当前教师工号：");
        //String username = LoginFrame.userTextField.getText();
        String username = "123";
        tidField = new JTextField(username,10);
        searchButton = new JButton("搜索");
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

        editButton = new JButton("编辑");
        editButton.addActionListener(this);
        addButton = new JButton("新增");
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
                model.addColumn("课程编号");
                model.addColumn("课程名称");
                model.addColumn("开课学院");
                model.addColumn("课程学分");

                // 遍历结果集并添加到表格模型中
                while (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String cxueyuan = rs.getString("cxueyuan");
                    String cgrade = rs.getString("cgrade");

                    model.addRow(new Object[]{cid, cname, cxueyuan,cgrade});
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
                JOptionPane.showMessageDialog(this, "请选择需要编辑的课程列");
                return;
            }

            String cid = (String) table.getValueAt(row, 0);
            String cname = (String) table.getValueAt(row, 1);
            String cxueyuan = (String) table.getValueAt(row, 2);
            String cgrade = (String) table.getValueAt(row, 3);
            // 弹出对话框让用户输入修改后的信息
            JTextField cidField = new JTextField(cid);
            JTextField cnameField = new JTextField(cname);
            JTextField cxueyuanField = new JTextField(cxueyuan);
            JTextField cgradeField = new JTextField(cgrade);

            Object[] fields = {"课程编号：", cidField, "课程名称：", cnameField, "开课学院：", cxueyuanField,"课程学分：",cgradeField};
            int result = JOptionPane.showConfirmDialog(this, fields, "修改课程信息", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newCid = cidField.getText();
                String newCname = cnameField.getText();
                String newCxueyuan = cxueyuanField.getText();
                String newCgrade = cgradeField.getText();

                // 更新数据库
                String update = "UPDATE course SET cid='" + newCid + "', cname='" + newCname + "', cxueyuan='" + newCxueyuan + "',cgrade='" + newCgrade + "' WHERE cid='" + cid + "'";
                try {
                    stmt.executeUpdate(update);
                    JOptionPane.showMessageDialog(this, "课程信息更新成功！");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "课程修改失败！");
                }
            }
        } else if (e.getSource() == addButton) {
            // 添加新课程
            JTextField cidField = new JTextField();
            JTextField cnameField = new JTextField();
            JTextField cxueyuanField = new JTextField();
            JTextField cgradeField = new JTextField();

            Object[] fields = {"课程编号：", cidField, "课程名称：", cnameField, "开课学院：", cxueyuanField,"课程学分：",cgradeField};
            int result = JOptionPane.showConfirmDialog(this, fields, "新增课程", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String cid = cidField.getText();
                String cname = cnameField.getText();
                String cxueyuan = cxueyuanField.getText();
                String cgrade = cgradeField.getText();

                // 插入数据库
                String insert = "INSERT INTO course (cid, cname, cxueyuan, tid,cgrade) VALUES ('" + cid + "', '" + cname + "', '" + cxueyuan + "', '" + tidField.getText() + "','" + cgrade + "')";
                try {
                    stmt.executeUpdate(insert);
                    JOptionPane.showMessageDialog(this, "课程新增成功！");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "课程添加失败！");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Course();
    }
}