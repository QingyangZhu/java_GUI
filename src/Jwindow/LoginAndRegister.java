package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginAndRegister extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel, confirmLabel;
    private JTextField usernameField;
    private JPasswordField passwordField, confirmField;
    private JButton loginButton, registerButton;

    public LoginAndRegister() {

        // 设置窗口标题
        super("登录和注册");

        // 设置窗口大小
        setSize(400, 250);

        // 创建界面组件
        usernameLabel = new JLabel("用户名：");
        passwordLabel = new JLabel("密码：");
        confirmLabel = new JLabel("确认密码：");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmField = new JPasswordField(15);
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        // 设置布局管理器
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(5, 5, 5, 5);
        add(usernameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(usernameField, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(passwordField, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(confirmLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(confirmField, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(loginButton, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(registerButton, gc);

        // 添加事件监听器
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // 显示窗口
        setVisible(true);

        // 设置窗口关闭行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 实现 ActionListener 接口的方法
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // 检查用户名和密码是否匹配
            if (username.equals("admin") && password.equals("123456")) {
                JOptionPane.showMessageDialog(this, "登录成功！");
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        } else if (event.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirm = String.valueOf(confirmField.getPassword());
            // 检查密码和确认密码是否一致
            if (password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "注册成功！");
            } else {
                JOptionPane.showMessageDialog(this, "密码和确认密码不一致！");
            }
        }
    }

    public static void main(String[] args) {
        new LoginAndRegister();
    }
}
