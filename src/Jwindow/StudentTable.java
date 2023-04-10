package Jwindow;

import MySQLdiver.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentTable extends JFrame {
    private String cid; // 课程号
    private JTable table; // 表格

    public StudentTable(String cid) {
        this.cid = cid;
        setTitle("选修课程 " + cid + " 的学生");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 居中显示

        // 获取数据并展示在表格中
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("学号");
        model.addColumn("姓名");
        model.addColumn("学院");
        model.addColumn("专业");
        model.addColumn("班级");
        model.addColumn("成绩");
        try {
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT x.sid, s.sname, s.sxueyuan, s.smajor, s.sclass, x.grade FROM xuanke x, students s WHERE x.cid = ? AND x.sid = s.sid");
            stmt.setString(1, cid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("sid");
                row[1] = rs.getString("sname");
                row[2] = rs.getString("sxueyuan");
                row[3] = rs.getString("smajor");
                row[4] = rs.getString("sclass");
                row[5] = rs.getFloat("grade");
                model.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table = new JTable(model);

        // 添加修改成绩按钮列
        TableColumn column = new TableColumn();
        column.setHeaderValue("");
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumnModel().addColumn(column);

        JScrollPane sp = new JScrollPane(table);
        getContentPane().add(sp);

        setVisible(true);
    }

    // 按钮渲染器
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("修改成绩");
            return this;
        }
    }

    // 按钮编辑器
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String sid;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String gradeStr = JOptionPane.showInputDialog("请输入成绩：");
                    try {
                        float grade = Float.parseFloat(gradeStr);
                        // 更新成绩
                        Connection conn = MySQLConnection.getConnection();
                        PreparedStatement stmt = conn.prepareStatement("UPDATE xuanke SET grade = ? WHERE cid = ? AND sid = ?");
                        stmt.setFloat(1, grade);
                        stmt.setString(2, cid);
                        stmt.setString(3, sid);
                        stmt.executeUpdate();
                        stmt.close();
                        conn.close();
                        // 更新表格
                        table.getModel().setValueAt(grade, table.getSelectedRow(), 5);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "成绩必须为数字！", "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            sid = (String) table.getModel().getValueAt(row, 0);
            button.setText("修改成绩");
            return button;
        }
    }

    public static void main(String[] args) {
        new StudentTable("1");
    }
}