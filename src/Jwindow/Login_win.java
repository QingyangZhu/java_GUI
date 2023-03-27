package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//这个类用来实现登录功能
public class Login_win extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    Login_win() {
        // 设置窗口标题
        super("登录");
        // 设置窗口大小
        setSize(400, 250);
        // 创建界面组件
        usernameLabel = new JLabel("用户名：");
        passwordLabel = new JLabel("密码：");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("登录");
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

        gc.gridx = 1;
        gc.gridy = 3;
        add(loginButton, gc);

        // 添加事件监听器
        loginButton.addActionListener((ActionListener) this);

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
                new MyPage().setVisible(true);
                // 关闭当前窗口
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        }
    }
    public static void main(String[] args) {
        new Login_win();
    }

}
