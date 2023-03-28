package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel userLabel, passwordLabel, identityLabel;
    public static JTextField userTextField;
    private JPasswordField passwordField;
    private JComboBox<String> identityComboBox;
    private JButton loginButton, resetButton;
    String id;

    public LoginFrame() {
        setTitle("登录");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        identityLabel = new JLabel("身份：");
        identityLabel.setBounds(50, 50, 50, 30);
        panel.add(identityLabel);

        String[] identities = {"学生", "老师"};
        identityComboBox = new JComboBox<>(identities);
        identityComboBox.setBounds(100, 50, 100, 30);
        panel.add(identityComboBox);

        userLabel = new JLabel("账号：");
        userLabel.setBounds(50, 100, 80, 30);
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(130, 100, 150, 30);
        panel.add(userTextField);

        passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(50, 150, 80, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 150, 150, 30);
        panel.add(passwordField);

        loginButton = new JButton("登录");
        loginButton.setBounds(50, 200, 80, 30);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        resetButton = new JButton("重置");
        resetButton.setBounds(200, 200, 80, 30);
        resetButton.addActionListener(this);
        panel.add(resetButton);

        add(panel);
        setVisible(true);

        id = userTextField.getText();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String identity = (String)identityComboBox.getSelectedItem();
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());
            boolean success = false;

            try {
                Connection con = MySQLConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs;

                if (identity.equals("学生")) {
                    rs = stmt.executeQuery("SELECT * FROM students WHERE sid ='" + username + "' AND spassword='" + password + "'");
                    if (rs.next()) {
                        success = true;
                        //con.close();
                        new MyPage().setVisible(true);
                        // 关闭当前窗口
                        dispose();
                    }
                } else if (identity.equals("老师")) {
                    rs = stmt.executeQuery("SELECT * FROM teacher WHERE tid ='" + username + "' AND tpassword ='" + password + "'");
                    if (rs.next()) {
                        success = true;
                        //con.close();
                        new MyPage().setVisible(true);
                        // 关闭当前窗口
                        dispose();
                    }
                }
                //con.close();此处若保留此代码会导致登录失败后断开数据库连接，导致用户无法再次进行登录验证

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (success) {
                JOptionPane.showMessageDialog(null, "登录成功");

            } else {
                JOptionPane.showMessageDialog(null, "账号或密码错误");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
