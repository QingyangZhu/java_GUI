package Jwindow;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

// 学生表格模型
class StudentTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    // 列名
    private static final String[] COLUMN_NAMES = { "学号", "姓名", "学院", "专业", "班级", "成绩" };

    // 数据
    private Object[][] data;

    public StudentTableModel(ResultSet rs) {
        try {
            // 获取结果集的行数和列数
            rs.last();
            int rowCount = rs.getRow();
            int columnCount = rs.getMetaData().getColumnCount();

            // 初始化数据数组
            data = new Object[rowCount][columnCount];

            // 将结果集的数据存入数据数组中
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < columnCount; j++) {
                    data[i][j] = rs.getObject(j + 1);
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}

// 课程表格模型
class CourseTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    // 列名
    private static final String[] COLUMN_NAMES = { "课程号", "课程名称", "开课学院" };

    // 数据
    private Object[][] data;

    public CourseTableModel(ResultSet rs) {
        try {
            // 获取结果集的行数和列数
            rs.last();
            int rowCount = rs.getRow();
            int columnCount = rs.getMetaData().getColumnCount();

            // 初始化数据数组
            data = new Object[rowCount][columnCount];

            // 将结果集的数据存入数据数组中
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < columnCount; j++) {
                    data[i][j] = rs.getObject(j + 1);
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
