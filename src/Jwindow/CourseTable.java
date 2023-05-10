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

public class CourseTable extends JFrame {
    private String tid; // 教师工号
    private JTable table; // 表格

    public CourseTable(String tid) {
        this.tid = tid;
        setTitle("教师 " + tid + " 所上的课程");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // 居中显示

        // 获取数据并展示在表格中
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("课程号");
        model.addColumn("课程名称");
        model.addColumn("开课学院");
        try {
            Connection conn  = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT cid, cname, cxueyuan FROM course WHERE tid = ?");
            stmt.setString(1, tid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("cid");
                row[1] = rs.getString("cname");
                row[2] = rs.getString("cxueyuan");
                model.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 添加按钮列
        TableColumn column = new TableColumn();
        column.setHeaderValue("");
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(new JCheckBox()));
        table = new JTable(model);
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
            setText("查看学生");
            return this;
        }
    }

    // 按钮编辑器
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String cid;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new StudentTable(cid);
                }
            });
        }
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            cid = (String) table.getValueAt(row, 0);
            button.setText("查看学生");
            return button;
        }
        public Object getCellEditorValue() {
            return cid; // 不写入任何值到表格模型
        }
    }

    public static void main(String[] args) {
        String username = LoginFrame.userTextField.getText();
        new CourseTable(username);
    }
}
