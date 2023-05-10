package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame {


    // GUI组件
    private JTabbedPane tabbedPane;
    private JPanel teacherPanel, studentPanel;
    private JLabel teacherIdLabel, teacherPasswordLabel, teacherPasswordConfirmLabel, teacherNameLabel, teacherXueyuanLabel, teacherMajorLabel;
    private JTextField teacherIdField, teacherNameField, teacherXueyuanField, teacherMajorField;
    private JPasswordField teacherPasswordField, teacherPasswordConfirmField;
    private JButton teacherSubmitButton;
    private JLabel studentIdLabel, studentPasswordLabel, studentPasswordConfirmLabel, studentNameLabel, studentXueyuanLabel, studentMajorLabel,studentClassLabel;
    private JTextField studentIdField, studentNameField, studentXueyuanField, studentMajorField,studentClassField;
    private JPasswordField studentPasswordField, studentPasswordConfirmField;
    private JButton studentSubmitButton;

    public RegistrationForm() {
        // 初始化GUI组件
        setTitle("用户注册");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        teacherPanel = new JPanel();
        studentPanel = new JPanel();
        teacherIdLabel = new JLabel("工号:");
        teacherIdField = new JTextField(20);
        teacherPasswordLabel = new JLabel("密码:");
        teacherPasswordField = new JPasswordField(20);
        teacherPasswordConfirmLabel = new JLabel("确认密码:");
        teacherPasswordConfirmField = new JPasswordField(20);
        teacherNameLabel = new JLabel("姓名:");
        teacherNameField = new JTextField(20);
        teacherXueyuanLabel = new JLabel("学院:");
        teacherXueyuanField = new JTextField(20);
        teacherMajorLabel = new JLabel("专业:");
        teacherMajorField = new JTextField(20);
        teacherSubmitButton = new JButton("提交注册");
        studentIdLabel = new JLabel("学号:");
        studentIdField = new JTextField(20);
        studentPasswordLabel = new JLabel("密码:");
        studentPasswordField = new JPasswordField(20);
        studentPasswordConfirmLabel = new JLabel("确认密码:");
        studentPasswordConfirmField = new JPasswordField(20);
        studentNameLabel = new JLabel("姓名");
        studentNameField = new JTextField(20);
        studentXueyuanLabel = new JLabel("学院");
        studentXueyuanField = new JTextField(20);
        studentMajorLabel = new JLabel("专业");
        studentMajorField = new JTextField(20);
        studentClassLabel = new JLabel("班级");
        studentClassField = new JTextField(20);
        studentSubmitButton = new JButton("提交注册");

        // 设置布局
        teacherPanel.setLayout(new GridLayout(7, 2));
        teacherPanel.add(teacherIdLabel);
        teacherPanel.add(teacherIdField);
        teacherPanel.add(teacherPasswordLabel);
        teacherPanel.add(teacherPasswordField);
        teacherPanel.add(teacherPasswordConfirmLabel);
        teacherPanel.add(teacherPasswordConfirmField);
        teacherPanel.add(teacherNameLabel);
        teacherPanel.add(teacherNameField);
        teacherPanel.add(teacherXueyuanLabel);
        teacherPanel.add(teacherXueyuanField);
        teacherPanel.add(teacherMajorLabel);
        teacherPanel.add(teacherMajorField);
        teacherPanel.add(teacherSubmitButton);
        studentPanel.setLayout(new GridLayout(8, 2));
        studentPanel.add(studentIdLabel);
        studentPanel.add(studentIdField);
        studentPanel.add(studentPasswordLabel);
        studentPanel.add(studentPasswordField);
        studentPanel.add(studentPasswordConfirmLabel);
        studentPanel.add(studentPasswordConfirmField);
        studentPanel.add(studentNameLabel);
        studentPanel.add(studentNameField);
        studentPanel.add(studentXueyuanLabel);
        studentPanel.add(studentXueyuanField);
        studentPanel.add(studentMajorLabel);
        studentPanel.add(studentMajorField);
        studentPanel.add(studentClassLabel);
        studentPanel.add(studentClassField);
        studentPanel.add(studentSubmitButton);

        // 添加选项卡和表单
        tabbedPane.addTab("教师注册", teacherPanel);
        tabbedPane.addTab("学生注册", studentPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // 添加监听器
        teacherSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerTeacher();
            }
        });
        studentSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        // 显示窗口
        setVisible(true);
    }

    // 注册教师
    private void registerTeacher() {
        // 从表单中获取输入
        String id = teacherIdField.getText();
        String password = new String(teacherPasswordField.getPassword());
        String confirmPassword = new String(teacherPasswordConfirmField.getPassword());
        String name = teacherNameField.getText();
        String xueyuan = teacherXueyuanField.getText();
        String major = teacherMajorField.getText();

        // 检查是否有空的输入
        if (id.equals("") || password.equals("") || confirmPassword.equals("") || name.equals("") || xueyuan.equals("") || major.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        // 检查密码是否匹配
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.");
            return;
        }

        // 检查账号是否已经存在
        if (checkTeacherExists(id)) {
            JOptionPane.showMessageDialog(this, "This account already exists. Please choose another one.");
            return;
        }
        // 将教师信息保存到teacher表中
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO teacher (tid, tpassword, tname, txueyuan, tmajor) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, xueyuan);
            stmt.setString(5, major);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful.");
            // 跳转到登录页面
            new LoginFrame().setVisible(true);
            // 关闭当前窗口
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error registering teacher: " + ex.getMessage());
        }
    }

    // 注册学生
    private void registerStudent() {
        // 从表单中获取输入
        String id = studentIdField.getText();
        String password = new String(studentPasswordField.getPassword());
        String confirmPassword = new String(studentPasswordConfirmField.getPassword());
        String name = studentNameField.getText();
        String xueyuan = studentXueyuanField.getText();
        String major = studentMajorField.getText();
        String sclass = studentClassField.getText();

        // 检查是否有空的输入
        if (id.equals("") || password.equals("") || confirmPassword.equals("") || name.equals("") || xueyuan.equals("") || major.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        // 检查密码是否匹配
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.");
            return;
        }

        // 检查账号是否已经存在
        if (checkStudentExists(id)) {
            JOptionPane.showMessageDialog(this, "This account already exists. Please choose another one.");
            return;
        }

        // 将学生信息保存到student表中
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (sid, spassword, sname, sxueyuan, smajor,sclass) VALUES (?, ?, ?, ?, ?,?)");
            stmt.setString(1, id);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, xueyuan);
            stmt.setString(5, major);
            stmt.setString(6, sclass);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful.");
            // 跳转到登录页面
            new LoginFrame().setVisible(true);
            // 关闭当前窗口
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error registering student: " + ex.getMessage());
        }
    }

    // 检查教师账号是否已经存在
    private boolean checkTeacherExists(String id) {
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM teacher WHERE tid = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error checking if teacher exists: " + ex.getMessage());
            return true;
        }
    }

    // 检查学生账号是否已经存在
    private boolean checkStudentExists(String id) {
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE sid = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error checking if student exists: " + ex.getMessage());
            return true;
        }
    }


}
