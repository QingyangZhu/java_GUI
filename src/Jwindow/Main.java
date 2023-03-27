package Jwindow;

import MySQLdiver.MySQLConnection;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = MySQLConnection.getConnection();

            // 创建Statement对象
            stmt = conn.createStatement();

            // 查询数据
            rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("id: " + id + ", name: " + name + ", age: " + age);
            }

            // 插入数据
            String insertSql = "INSERT INTO users(id,name, age) VALUES(1,'Tom', 20)";
            int affectedRows = stmt.executeUpdate(insertSql);
            System.out.println("插入" + affectedRows + "行数据");

            // 更新数据
            String updateSql = "UPDATE users SET age = 21 WHERE name = 'Tom'";
            affectedRows = stmt.executeUpdate(updateSql);
            System.out.println("更新" + affectedRows + "行数据");

            // 删除数据
            String deleteSql = "DELETE FROM users WHERE name = 'Tom'";
            affectedRows = stmt.executeUpdate(deleteSql);
            System.out.println("删除" + affectedRows + "行数据");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
