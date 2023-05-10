package Jwindow;

import javax.swing.*;
import java.awt.*;

public class StudentPage extends JFrame {
    private JLabel welcomeLabel;
    private JButton infoButton;
    private JButton gradeButton;
    private JButton examButton;

    public StudentPage() {
        super("学生个人主页");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("欢迎来到学生个人主页！");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        infoButton = new JButton("个人信息");
        infoButton.setIcon(new ImageIcon("info.png"));
        infoButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        buttonPanel.add(infoButton);

        gradeButton = new JButton("成绩查询");
        gradeButton.setIcon(new ImageIcon("grade.png"));
        gradeButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        buttonPanel.add(gradeButton);

        examButton = new JButton("考试信息");
        examButton.setIcon(new ImageIcon("exam.png"));
        examButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        buttonPanel.add(examButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
