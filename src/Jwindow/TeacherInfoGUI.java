package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherInfoGUI extends JFrame {

    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel majorLabel;
    private JLabel collegeLabel;

    private JTextField nameField;
    private JTextField idField;
    private JTextField majorField;
    private JTextField collegeField;

    private JButton editButton;
    private JButton saveButton;
    private JButton cancelButton;

    private boolean editing = false;

    public TeacherInfoGUI() {
        String username = LoginFrame.userTextField.getText();
        initComponents();
        loadData(username);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("教师个人信息展示");
        setResizable(false);

        nameLabel = new JLabel("姓名：");
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        idLabel = new JLabel("工号：");
        idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        majorLabel = new JLabel("专业：");
        majorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        collegeLabel = new JLabel("学院：");
        collegeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        nameField = new JTextField(20);
        nameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nameField.setEditable(false);

        idField = new JTextField(20);
        idField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        idField.setEditable(false);

        majorField = new JTextField(20);
        majorField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        majorField.setEditable(false);

        collegeField = new JTextField(20);
        collegeField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        collegeField.setEditable(false);

        editButton = new JButton("编辑");
        editButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editing = true;
                nameField.setEditable(true);
                majorField.setEditable(true);
                collegeField.setEditable(true);
                nameField.requestFocus();
                editButton.setEnabled(false);
                saveButton.setEnabled(true);
                cancelButton.setEnabled(true);
            }
        });

        saveButton = new JButton("保存");
        saveButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        saveButton.setEnabled(false);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String name = nameField.getText().trim();
                String major = majorField.getText().trim();
                String college = collegeField.getText().trim();

                if (name.isEmpty() || major.isEmpty() || college.isEmpty()) {
                    JOptionPane.showMessageDialog(TeacherInfoGUI.this, "请填写完整信息！", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection conn = MySQLConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE teacher SET tname=?, tmajor=?, txueyuan=? WHERE tid=?");
                    stmt.setString(1, name);
                    stmt.setString(2, major);
                    stmt.setString(3, college);
                    stmt.setString(4, idField.getText().trim());
                    stmt.executeUpdate();
                    stmt.close();
                    //conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                editing = false;
                nameField.setEditable(false);
                majorField.setEditable(false);
                collegeField.setEditable(false);
                editButton.setEnabled(true);
                saveButton.setEnabled(false);
                cancelButton.setEnabled(false);
            }
        });

        cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editing = false;
                nameField.setEditable(false);
                majorField.setEditable(false);
                collegeField.setEditable(false);
                loadData(idField.getText().trim());
                editButton.setEnabled(true);
                saveButton.setEnabled(false);
                cancelButton.setEnabled(false);
            }
        });

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);

        c.gridx = 0;
        c.gridy = 0;
        contentPanel.add(nameLabel, c);

        c.gridx = 1;
        contentPanel.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        contentPanel.add(idLabel, c);

        c.gridx = 1;
        contentPanel.add(idField, c);

        c.gridx = 0;
        c.gridy = 2;
        contentPanel.add(majorLabel, c);

        c.gridx = 1;
        contentPanel.add(majorField, c);

        c.gridx = 0;
        c.gridy = 3;
        contentPanel.add(collegeLabel, c);

        c.gridx = 1;
        contentPanel.add(collegeField, c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void loadData(String tid) {
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT tname, tid, tmajor, txueyuan FROM teacher WHERE tid=?");
            stmt.setString(1, tid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("tname"));
                idField.setText(rs.getString("tid"));
                majorField.setText(rs.getString("tmajor"));
                collegeField.setText(rs.getString("txueyuan"));
            }

            rs.close();
            stmt.close();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
