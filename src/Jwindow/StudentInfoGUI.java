package Jwindow;

import MySQLdiver.MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class StudentInfoGUI extends JFrame implements ActionListener {
    // 定义组件
    JLabel lb1, lb2, lb3, lb4;
    JTextField tf1, tf2, tf3, tf4;
    JButton btn_edit, btn_save;


    // 定义数据库连接和语句对象
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public StudentInfoGUI() {
        // 初始化组件
        lb1 = new JLabel("学号：");
        lb2 = new JLabel("学院：");
        lb3 = new JLabel("专业：");
        lb4 = new JLabel("班级：");

        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf3 = new JTextField(10);
        tf4 = new JTextField(10);

        btn_edit = new JButton("编辑");
        btn_save = new JButton("保存");

        // 设置布局
        JPanel panel1 = new JPanel(new GridLayout(4, 2));
        panel1.add(lb1);
        panel1.add(tf1);
        panel1.add(lb2);
        panel1.add(tf2);
        panel1.add(lb3);
        panel1.add(tf3);
        panel1.add(lb4);
        panel1.add(tf4);

        JPanel panel2 = new JPanel();
        panel2.add(btn_edit);
        panel2.add(btn_save);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel1, BorderLayout.CENTER);
        container.add(panel2, BorderLayout.SOUTH);

        // 添加事件监听器
        btn_edit.addActionListener(this);
        btn_save.addActionListener(this);

        // 设置窗口属性
        setTitle("学生个人信息");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // 连接数据库并读取学生信息
        try {
            Connection conn = MySQLConnection.getConnection();
            stmt = conn.createStatement();
            String username = LoginFrame.userTextField.getText();
            //String username = "20214780";
            rs = stmt.executeQuery("SELECT * FROM students WHERE sid = '"+username+"'");
            if (rs.next()) {
                tf1.setText(rs.getString("sid"));
                tf2.setText(rs.getString("sxueyuan"));
                tf3.setText(rs.getString("smajor"));
                tf4.setText(rs.getString("sclass"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "无法连接数据库：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_edit) {
            // 编辑按钮被按下
            tf1.setEditable(true);
            tf2.setEditable(true);
            tf3.setEditable(true);
            tf4.setEditable(true);
        } else if (e.getSource() == btn_save) {
// 保存按钮被按下
            String 学号 = tf1.getText().trim();
            String 学院 = tf2.getText().trim();
            String 专业 = tf3.getText().trim();
            String 班级 = tf4.getText().trim();
            try {
                stmt.executeUpdate("UPDATE students SET sxueyuan = '" + 学院 + "', smajor = '" + 专业 + "', sclass = '" + 班级 + "' WHERE sid = '" + 学号 + "'");
                JOptionPane.showMessageDialog(null, "学生信息已保存。", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "无法保存学生信息：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new StudentInfoGUI();
    }
}

