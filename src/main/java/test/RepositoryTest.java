package test;

import java.util.List;

import model.Task;
import model.User;
import repository.TaskRepository;
import repository.UserRepository;

public class RepositoryTest {

    public static void main(String[] args) {
        System.out.println("=== Repository Layer Test Start ===");

        
        System.out.println("\n[Test 1] User Search");
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByUsername("test_user"); 
        
        if (user != null) {
            System.out.println("Found User: " + user.getUsername() + " (ID: " + user.getId() + ")");
        } else {
            System.out.println("User not found.");
        }

        
        System.out.println("\n[Test 2] Task Search by User ID");
        TaskRepository taskRepo = new TaskRepository();
        
        if (user != null) {
            
            List<Task> tasks = taskRepo.findByUserId(user.getId());
            System.out.println("Tasks for " + user.getUsername() + ": " + tasks.size() + " found.");
            
            for (Task t : tasks) {
                System.out.println("- " + t.getTitle() + " [" + t.getStatus() + "]");
            }
        }

        System.out.println("\n=== Repository Layer Test End ===");
    }
}