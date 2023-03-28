package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ExamScheduleGUI extends JFrame {

    private JLabel labelTid;
    private JTextField textTid;
    private JButton buttonQuery;
    private JButton buttonAdd;
    private JTable tableExam;

    public ExamScheduleGUI() {
        super("考试安排");

        // 创建GUI组件
        labelTid = new JLabel("工号：");
        String username = LoginFrame.userTextField.getText();
        textTid = new JTextField(username,10);
        buttonQuery = new JButton("查询");
        buttonAdd = new JButton("添加");
        tableExam = new JTable();

        // 添加GUI组件到窗口
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.add(labelTid);
        panelTop.add(textTid);
        panelTop.add(buttonQuery);
        panelTop.add(buttonAdd);

        JScrollPane panelCenter = new JScrollPane(tableExam);

        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);

        // 添加事件处理
        buttonQuery.addActionListener(e -> queryExamSchedule(textTid.getText().trim()));
        buttonAdd.addActionListener(e -> {
            if (textTid.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请先输入工号");
                return;
            }
            AddExamScheduleDialog dialog = new AddExamScheduleDialog(this);
            dialog.setVisible(true);
        });

        // 设置窗口属性
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void queryExamSchedule(String tid) {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT eid, ename, etime_begin, etime_end FROM exam WHERE tid = ?")) {
            stmt.setString(1, tid);
            ResultSet rs = stmt.executeQuery();

            // 设置表格数据模型
            DefaultTableModel model = new DefaultTableModel(new String[]{"考试编号", "课程名称", "开始时间", "结束时间"}, 0);
            while (rs.next()) {
                int eid = rs.getInt("eid");
                String ename = rs.getString("ename");
                Timestamp timeBegin = rs.getTimestamp("etime_begin");
                Timestamp timeEnd = rs.getTimestamp("etime_end");
                model.addRow(new Object[]{eid, ename, timeBegin, timeEnd});
            }
            tableExam.setModel(model);

            // 设置表格列宽
            tableExam.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableExam.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableExam.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableExam.getColumnModel().getColumn(3).setPreferredWidth(150);
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "查询考试安排失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ExamScheduleGUI();
    }

    // 内部类：添加考试安排对话框
    private class AddExamScheduleDialog extends JDialog {

        private JLabel labelEname;
        private JTextField textEname;
        private JLabel labelTimeBegin;
        private JTextField textTimeBegin;
        private JLabel labelTimeEnd;
        private JTextField textTimeEnd;
        private JButton buttonOK;
        private JButton buttonCancel;

        public AddExamScheduleDialog(Frame owner) {
            super(owner, "新增考试安排", true);
            setLayout(new GridLayout(4, 2));

            // 创建GUI组件
            labelEname = new JLabel("课程名称：");
            textEname = new JTextField();
            labelTimeBegin = new JLabel("开始时间（格式：yyyy-MM-dd HH:mm:ss）：");
            textTimeBegin = new JTextField();
            labelTimeEnd = new JLabel("结束时间（格式：yyyy-MM-dd HH:mm:ss）：");
            textTimeEnd = new JTextField();
            buttonOK = new JButton("确定");
            buttonCancel = new JButton("取消");

            // 添加GUI组件到窗口
            add(labelEname);
            add(textEname);
            add(labelTimeBegin);
            add(textTimeBegin);
            add(labelTimeEnd);
            add(textTimeEnd);
            add(buttonOK);
            add(buttonCancel);

            // 添加事件处理
            buttonOK.addActionListener(e -> {
                String ename = textEname.getText().trim();
                String timeBeginStr = textTimeBegin.getText().trim();
                String timeEndStr = textTimeEnd.getText().trim();
                if (ename.isEmpty() || timeBeginStr.isEmpty() || timeEndStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "请填写完整信息");
                    return;
                }
                Timestamp timeBegin = Timestamp.valueOf(timeBeginStr);
                Timestamp timeEnd = Timestamp.valueOf(timeEndStr);
                addExamSchedule(textTid.getText().trim(), ename, timeBegin, timeEnd);
                dispose();
            });
            buttonCancel.addActionListener(e -> dispose());

            // 设置窗口属性
            pack();
            setLocationRelativeTo(owner);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);


        }

        private void addExamSchedule(String tid, String ename, Timestamp timeBegin, Timestamp timeEnd) {
            try (Connection conn = MySQLConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO exam (tid, ename, etime_begin, etime_end) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, tid);
                stmt.setString(2, ename);
                stmt.setTimestamp(3, timeBegin);
                stmt.setTimestamp(4, timeEnd);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    ExamScheduleGUI gui = (ExamScheduleGUI) getOwner();
                    gui.queryExamSchedule(tid);
                    JOptionPane.showMessageDialog(this, "新增考试安排成功");
                } else {
                    JOptionPane.showMessageDialog(this, "新增考试安排失败");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "新增考试安排失败：" + e.getMessage());
            }
        }
    }
}