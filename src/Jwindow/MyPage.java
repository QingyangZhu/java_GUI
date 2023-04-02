package Jwindow;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyPage extends JFrame implements ActionListener {

    private JPanel contentPane;
    JButton btnHomepage,btnGradeManagement,btnGradeQuery;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyPage frame = new MyPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 480);
        setTitle("My Page");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        // 添加个人信息按钮
        btnHomepage = new JButton("个人信息");
        btnHomepage.setFont(new Font("宋体", Font.BOLD, 16));
        btnHomepage.setBackground(new Color(255, 215, 0));
        GridBagConstraints gbc_btnHomepage = new GridBagConstraints();
        gbc_btnHomepage.insets = new Insets(0, 0, 5, 5);
        gbc_btnHomepage.gridx = 2;
        gbc_btnHomepage.gridy = 2;
        contentPane.add(btnHomepage, gbc_btnHomepage);
        btnHomepage.addActionListener(this);

        // 添加成绩管理按钮
        btnGradeManagement = new JButton("成绩管理");
        btnGradeManagement.setFont(new Font("宋体", Font.BOLD, 16));
        btnGradeManagement.setBackground(new Color(255, 140, 0));
        GridBagConstraints gbc_btnGradeManagement = new GridBagConstraints();
        gbc_btnGradeManagement.insets = new Insets(0, 0, 5, 5);
        gbc_btnGradeManagement.gridx = 3;
        gbc_btnGradeManagement.gridy = 2;
        contentPane.add(btnGradeManagement, gbc_btnGradeManagement);
        btnGradeManagement.addActionListener(this);

        //考试信息
        btnGradeQuery = new JButton("考试信息");
        btnGradeQuery.setFont(new Font("宋体", Font.BOLD, 16));
        btnGradeQuery.setBackground(new Color(255, 69, 0));
        GridBagConstraints gbc_btnGradeQuery = new GridBagConstraints();
        gbc_btnGradeQuery.insets = new Insets(0, 0, 5, 5);
        gbc_btnGradeQuery.gridx = 4;
        gbc_btnGradeQuery.gridy = 2;
        contentPane.add(btnGradeQuery, gbc_btnGradeQuery);
        btnGradeQuery.addActionListener(this);

        // 添加标题
        JLabel lblTitle = new JLabel("欢迎来到我的个人主页");
        lblTitle.setForeground(new Color(0, 0, 205));
        lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 28));
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.gridwidth = 3;
        gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
        gbc_lblTitle.gridx = 1;
        gbc_lblTitle.gridy = 1;
        contentPane.add(lblTitle, gbc_lblTitle);

        // 添加背景图标
        JLabel lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon("src/image/mypage.jpg"));
        GridBagConstraints gbc_lblBackground = new GridBagConstraints();
        gbc_lblBackground.gridheight = 0;
        gbc_lblBackground.gridwidth = 0;
        gbc_lblBackground.gridx = 0;
        gbc_lblBackground.gridy = 0;
        contentPane.add(lblBackground, gbc_lblBackground);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == btnHomepage) {
            // 跳转到登录页面
            new StudentInfoGUI().setVisible(true);
            // 关闭当前窗口
            //dispose();
        }
        if (event.getSource() == btnGradeManagement) {
            // 跳转到注册页面
            new StudentGradeGUI(LoginFrame.userTextField.getText()).setVisible(true);
            // 关闭当前窗口
            //dispose();
        }
        if (event.getSource() == btnGradeQuery) {
            // 跳转到注册页面
            new ExamInfoGUI(LoginFrame.userTextField.getText()).setVisible(true);
            // 关闭当前窗口
            //dispose();
        }
    }

}
