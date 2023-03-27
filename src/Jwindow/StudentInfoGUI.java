package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentInfoGUI extends JFrame implements ActionListener {
    // 定义组件
    JLabel lb1, lb2, lb3, lb4;
    JTextField tf1, tf2, tf3, tf4;
    JButton btn_edit, btn_save;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
            tf1.setEditable(false);
            tf2.setEditable(false);
            tf3.setEditable(false);
            tf4.setEditable(false);
            // 这里可以保存修改后的信息到数据库或文件中
            // 在这里只是简单地弹出一个对话框显示修改后的信息
            JOptionPane.showMessageDialog(null, "学号：" + tf1.getText() + "\n学院：" + tf2.getText() + "\n专业：" + tf3.getText() + "\n班级：" + tf4.getText(), "修改后的学生信息", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new StudentInfoGUI();
    }
}

