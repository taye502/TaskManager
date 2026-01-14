package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionFactory;

public class ConnectionTest {

    public static void main(String[] args) {
        System.out.println("=== Database Connection Test Start ===");

        try (Connection conn = ConnectionFactory.getConnection()) {
            
       
            if (conn != null && !conn.isClosed()) {
                System.out.println("[Success] Connected to the database!");
            }
            String sql = "SELECT count(*) FROM users";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("[Success] User count: " + count);
                }
            }

            
            String taskSql = "SELECT title, status FROM tasks";
            try (PreparedStatement ps = conn.prepareStatement(taskSql);
                 ResultSet rs = ps.executeQuery()) {
                
                System.out.println("\n--- Task List ---");
                while (rs.next()) {
                    String title = rs.getString("title");
                    String status = rs.getString("status");
                    System.out.println("Task: " + title + " [" + status + "]");
                }
            }

        } catch (SQLException e) {
            System.err.println("[Error] Database operation failed.");
            e.printStackTrace();
        }

        System.out.println("=== Database Connection Test End ===");
    }
}