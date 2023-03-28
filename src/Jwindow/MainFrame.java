package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainFrame extends JFrame {

    private JLabel tidLabel;  // 教师工号标签
    private JTextField tidField;  // 教师工号文本框
    private JButton queryButton;  // 查询按钮
    private JTable courseTable;  // 课程表格
    private JScrollPane courseScrollPane;  // 课程表格滚动面板
    private JPanel coursePanel;  // 课程面板

    private JLabel cidLabel;  // 课程号标签
    private JTextField cidField;  // 课程号文本框
    private JButton searchButton;  // 搜索按钮
    private JTable studentTable;  // 学生表格
    private JScrollPane studentScrollPane;  // 学生表格滚动面板
    private JPanel studentPanel;  // 学生面板

    private Connection conn;  // 数据库连接
    private Statement stmt;  // 数据库操作对象
    private ResultSet rs;  // 数据库结果集

    // 构造函数
    public MainFrame(String title) {
        super(title);

        // 初始化界面
        initUI();

        // 初始化数据库连接
        initDB();
    }

    // 初始化界面
    private void initUI() {
        // 设置布局
        setLayout(new BorderLayout());

        // 创建教师查询面板
        JPanel teacherPanel = new JPanel(new FlowLayout());
        tidLabel = new JLabel("教师工号：");
        tidField = new JTextField(10);
        queryButton = new JButton("查询");
        teacherPanel.add(tidLabel);
        teacherPanel.add(tidField);
        teacherPanel.add(queryButton);

        // 创建课程表格
        courseTable = new JTable();
        courseScrollPane = new JScrollPane(courseTable);
        coursePanel = new JPanel(new BorderLayout());
        coursePanel.add(courseScrollPane, BorderLayout.CENTER);

        // 创建学生查询面板
        JPanel searchPanel = new JPanel(new FlowLayout());
        cidLabel = new JLabel("课程号：");
        cidField = new JTextField(10);
        searchButton = new JButton("搜索");
        searchPanel.add(cidLabel);
        searchPanel.add(cidField);
        searchPanel.add(searchButton);

        // 创建学生表格
        studentTable = new JTable();
        studentScrollPane = new JScrollPane(studentTable);
        studentPanel = new JPanel(new BorderLayout());
        studentPanel.add(studentScrollPane, BorderLayout.CENTER);

        // 添加组件到界面中
        add(teacherPanel, BorderLayout.NORTH);
        add(coursePanel, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);

        // 设置窗口大小、位置和关闭方式
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 给查询按钮添加事件监听器
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 查询数据库，更新课程表格
                updateCourseTable();
            }
        });

        //
        // 给搜索按钮添加事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 查询数据库，更新学生表格
                updateStudentTable();
            }
        });

        // 显示界面
        setVisible(true);
    }

    // 初始化数据库连接
    private void initDB() {
        try {
            // 加载数据库驱动
            //Class.forName("com.mysql.jdbc.Driver");

            // 获取数据库连接
            conn = MySQLConnection.getConnection();

            // 创建数据库操作对象
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询数据库，更新课程表格
    private void updateCourseTable() {
        try {
            // 获取教师工号
            String tid = tidField.getText();

            // 构造SQL语句
            String sql = "SELECT c.cid, c.cname, c.cxueyuan FROM course c, teacher t, xuanke te WHERE c.cid=te.cid AND t.tid=te.tid AND t.tid='" + tid + "'";

            // 执行查询
            rs = stmt.executeQuery(sql);

            // 更新课程表格
            courseTable.setModel(new CourseTableModel(rs));

            // 关闭结果集
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询数据库，更新学生表格
    private void updateStudentTable() {
        try {
            // 获取课程号
            String cid = cidField.getText();

            // 构造SQL语句
            String sql = "SELECT s.sid, s.sname, s.sxueyuan, s.smajor, s.sclass, x.grade FROM students s, xuanke x WHERE s.sid=x.sid AND x.cid='" + cid + "'";

            // 执行查询
            rs = stmt.executeQuery(sql);

            // 更新学生表格
            studentTable.setModel(new StudentTableModel(rs));

            // 关闭结果集
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 主函数
    public static void main(String[] args) {
        new MainFrame("Java GUI示例");
    }
}