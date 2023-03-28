package Jwindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class teacher_page extends JFrame {

    // Define the components on the teacher_page
    private JPanel mainPanel, headerPanel, buttonPanel;
    private JLabel welcomeLabel, infoLabel, examLabel, courseLabel, gradeLabel;
    private JButton infoButton, examButton, courseButton, gradeButton;

    // Constructor
    public teacher_page() {

        // Set the default look and feel for the GUI components
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Enumeration keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof FontUIResource) {
                    FontUIResource orig = (FontUIResource) value;
                    Font font = new Font("微软雅黑", orig.getStyle(), orig.getSize());
                    UIManager.put(key, new FontUIResource(font));
                }
            }
        } catch (Exception e) {
        }

        // Create the main panel
        mainPanel = new JPanel(new BorderLayout());

        // Create the header panel
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setPreferredSize(new Dimension(800, 100));
        headerPanel.setBackground(new Color(90, 150, 250));

        // Create the welcome label
        welcomeLabel = new JLabel("欢迎您，教师！");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 28));

        // Add the welcome label to the header panel
        headerPanel.add(welcomeLabel);

        // Create the button panel
        buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setPreferredSize(new Dimension(800, 500));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create the information button
        infoButton = new JButton("<html><center>我的<br>信息</center></html>");
        infoButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        infoButton.setIcon(new ImageIcon("src/icons/user.png"));
        infoButton.setHorizontalTextPosition(JButton.CENTER);
        infoButton.setVerticalTextPosition(JButton.BOTTOM);

        // Create the exam button
        examButton = new JButton("<html><center>考试<br>安排</center></html>");
        examButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        examButton.setIcon(new ImageIcon("src/icons/calendar.png"));
        examButton.setHorizontalTextPosition(JButton.CENTER);
        examButton.setVerticalTextPosition(JButton.BOTTOM);

        // Create the course button
        courseButton = new JButton("<html><center>课程<br>管理</center></html>");
        courseButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        courseButton.setIcon(new ImageIcon("src/icons/book.png"));
        courseButton.setHorizontalTextPosition(JButton.CENTER);
        courseButton.setVerticalTextPosition(JButton.BOTTOM);

        // Create the grade button
        gradeButton = new JButton("<html><center>成绩<br>填报</center></html>");
        gradeButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        gradeButton.setIcon(new ImageIcon("src/icons/clipboard.png"));
        gradeButton.setHorizontalTextPosition(JButton.CENTER);
        gradeButton.setVerticalTextPosition(JButton.BOTTOM);
        // Add the buttons to the button panel
        buttonPanel.add(infoButton);
        buttonPanel.add(examButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(gradeButton);

        /*// Create the information label
        infoLabel = new JLabel("<html><h1>我的信息</h1><p>您的个人信息将在此处显示。</p></html>");
        infoLabel.setVerticalAlignment(SwingConstants.TOP);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create the exam label
        examLabel = new JLabel("<html><h1>考试安排</h1><p>考试安排将在此处显示。</p></html>");
        examLabel.setVerticalAlignment(SwingConstants.TOP);
        examLabel.setHorizontalAlignment(SwingConstants.CENTER);
        examLabel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create the course label
        courseLabel = new JLabel("<html><h1>课程管理</h1><p>您的课程将在此处显示。</p></html>");
        courseLabel.setVerticalAlignment(SwingConstants.TOP);
        courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseLabel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create the grade label
        gradeLabel = new JLabel("<html><h1>成绩填报</h1><p>成绩填报将在此处显示。</p></html>");
        gradeLabel.setVerticalAlignment(SwingConstants.TOP);
        gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gradeLabel.setBorder(new EmptyBorder(20, 20, 20, 20));*/

        // Add the components to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        //mainPanel.add(infoLabel, BorderLayout.SOUTH);

        // Set the JFrame properties
        setTitle("教师页面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(mainPanel);
        setVisible(true);

        // Add action listeners to the buttons
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TeacherInfoGUI().setVisible(true);
                /*infoLabel.setVisible(true);
                examLabel.setVisible(false);
                courseLabel.setVisible(false);
                gradeLabel.setVisible(false);*/
            }
        });

        examButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExamScheduleGUI().setVisible(true);
                /*infoLabel.setVisible(false);
                examLabel.setVisible(true);
                courseLabel.setVisible(false);
                gradeLabel.setVisible(false);*/
            }
        });

        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Course().setVisible(true);
                /*infoLabel.setVisible(false);
                examLabel.setVisible(false);
                courseLabel.setVisible(true);
                gradeLabel.setVisible(false);*/
            }
        });

        gradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = LoginFrame.userTextField.getText();
                new CourseTable(username).setVisible(true);
                /*infoLabel.setVisible(false);
                examLabel.setVisible(false);
                courseLabel.setVisible(false);
                gradeLabel.setVisible(true);*/
            }
        });
    }

    // Main method
    public static void main(String[] args) {
        new teacher_page();
    }
}
