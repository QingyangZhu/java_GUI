package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Register_win extends JFrame {
    // 用户名、密码、确认密码的文本框
    private JTextField username_textField;
    private JPasswordField password_textField;
    private JPasswordField confirm_textField;

    public Register_win() {
        // 设置窗口标题和大小
        setTitle("注册");
        setSize(300, 200);
        // 设置窗口位置
        setLocationRelativeTo(null);
        // 设置布局为网格布局
        setLayout(new GridBagLayout());
        // 创建 GridBagConstraints 对象，用于设置组件的约束
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);
        c.weightx = 1.0;
        // 添加用户名文本框和标签
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("用户名："), c);
        c.gridx = 1;
        c.gridy = 0;
        username_textField = new JTextField();
        add(username_textField, c);
        // 添加密码文本框和标签
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("密码："), c);
        c.gridx = 1;
        c.gridy = 1;
        password_textField = new JPasswordField();
        add(password_textField, c);
        // 添加确认密码文本框和标签
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("确认密码："), c);
        c.gridx = 1;
        c.gridy = 2;
        confirm_textField = new JPasswordField();
        add(confirm_textField, c);
        // 添加注册按钮
        c.gridx = 1;
        c.gridy = 3;
        JButton register_button = new JButton("注册");
        register_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取用户名、密码、确认密码
                String username = username_textField.getText();
                String password = new String(password_textField.getPassword());
                String confirm = new String(confirm_textField.getPassword());
                // 判断用户名、密码、确认密码是否为空
                if (username.equals("") || password.equals("") || confirm.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名、密码、确认密码不能为空！", "注册失败", JOptionPane.WARNING_MESSAGE);
                } else if (!password.equals(confirm)) { // 判断密码和确认密码是否相同
                    JOptionPane.showMessageDialog(null, "密码和确认密码不相同！", "注册失败", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 弹窗提示注册成功
                    JOptionPane.showMessageDialog(null, "注册成功！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
                    // 跳转到登录页面
                    new Login_win().setVisible(true);
                    // 关闭当前窗口
                    dispose();
                }
            }
        });
        add(register_button, c);
    }




    public static void main(String[] args) {
        // 创建注册窗口实例并显示
        new Register_win().setVisible(true);
    }
}

