package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//这个类主要用来欢迎用户使用
public class Hello_win extends JFrame implements ActionListener {
    //从JFrame中继承而来的欢迎窗口
    private JLabel label; //欢迎的图片标签
    private JButton button_login; //登录注册按钮，跳转到登录注册页面
    private JButton button_register; //登录注册按钮，跳转到登录注册页面
    ImageIcon icon = new ImageIcon("src/image/welcome.jpg");


    Hello_win(){
        // 设置窗口标题
        setTitle("欢迎！");
        icon.setImage(icon.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT));//设置图像大小
        label = new JLabel(icon);
        button_login = new JButton("登录");
        button_register = new JButton("注册");

        // 添加事件监听器
        button_register.addActionListener((ActionListener) this);
        button_login.addActionListener((ActionListener) this);



        // 使用布局管理器将UI元素添加到窗口中
        setLayout(new FlowLayout());
        add(label,BorderLayout.NORTH);
        add(button_login);
        add(button_register);

        // 设置窗口大小和位置
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        // 显示窗口
        setVisible(true);

    }

    // 实现 ActionListener 接口的方法
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button_login) {
            // 跳转到登录页面
            new LoginFrame().setVisible(true);
            // 关闭当前窗口
            dispose();
        } else if (event.getSource() == button_register) {
            // 跳转到注册页面
            new RegistrationForm().setVisible(true);
            // 关闭当前窗口
            dispose();
        }
    }

    public static void main(String[] args) {
        new Hello_win();
    }

}
