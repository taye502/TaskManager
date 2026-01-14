package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskRepository extends BaseRepository {

    
    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks ORDER BY created_at DESC";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                tasks.add(mapToTask(rs));
            }
        } catch (SQLException e) {
            handleSQLException(e, "findAll");
        }
        return tasks;
    }

    public List<Task> findByUserId(int userId) {
        String sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY created_at DESC";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasks.add(mapToTask(rs));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "findByUserId");
        }
        return tasks;
    }


    private Task mapToTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("task_id"));
        task.setUserId(rs.getInt("user_id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setStatus(rs.getString("status"));
        task.setPriority(rs.getString("priority"));
        task.setCreatedAt(rs.getTimestamp("created_at"));
        task.setUpdatedAt(rs.getTimestamp("updated_at"));
        return task;
    }
    public boolean isOwner(int taskId, int userId) {
        String sql = "SELECT count(*) FROM tasks WHERE task_id = ? AND user_id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "isOwner");
        }
        return false;
    }

    public boolean delete(int taskId, int userId) {
        String sql = "DELETE FROM tasks WHERE task_id = ? AND user_id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
            
        } catch (SQLException e) {
            handleSQLException(e, "delete");
            return false;
        }
    }
}