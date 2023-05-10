package Jwindow;

import javax.swing.*;
import java.awt.*;

public class StudentHomepage extends JFrame {
    public StudentHomepage() {
        // 设置窗口标题
        super("学生个人主页");

        // 设置窗口大小
        setSize(600, 400);

        // 设置窗口在屏幕中央显示
        setLocationRelativeTo(null);

        // 设置窗口关闭时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建顶部欢迎语句
        JLabel welcomeLabel = new JLabel("欢迎来到学生个人主页！", JLabel.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));

        // 创建功能选择区面板
        JPanel functionPanel = new JPanel(new GridLayout(1, 3));
        functionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 创建个人信息按钮
        ImageIcon infoIcon = new ImageIcon("info.png");
        JButton infoButton = new JButton("个人信息", infoIcon);
        infoButton.setVerticalTextPosition(JButton.BOTTOM);
        infoButton.setHorizontalTextPosition(JButton.CENTER);
        infoButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        // 创建成绩查询按钮
        ImageIcon scoreIcon = new ImageIcon("score.png");
        JButton scoreButton = new JButton("成绩查询", scoreIcon);
        scoreButton.setVerticalTextPosition(JButton.BOTTOM);
        scoreButton.setHorizontalTextPosition(JButton.CENTER);
        scoreButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        // 创建考试信息按钮
        ImageIcon examIcon = new ImageIcon("exam.png");
        JButton examButton = new JButton("考试信息", examIcon);
        examButton.setVerticalTextPosition(JButton.BOTTOM);
        examButton.setHorizontalTextPosition(JButton.CENTER);
        examButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        // 将按钮添加到面板中
        functionPanel.add(infoButton);
        functionPanel.add(scoreButton);
        functionPanel.add(examButton);

        // 将欢迎语句和功能选择区面板添加到窗口中
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(welcomeLabel, BorderLayout.NORTH);
        contentPane.add(functionPanel, BorderLayout.CENTER);

        // 显示窗口
        setVisible(true);
    }


}
