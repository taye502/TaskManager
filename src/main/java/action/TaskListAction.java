package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import model.User;
import repository.TaskRepository;


public class TaskListAction extends BaseAuthAction {

    private TaskRepository taskRepository = new TaskRepository();

    @Override
    protected String executeAuthenticated(HttpServletRequest request, HttpServletResponse response, User loginUser) 
            throws ServletException, IOException {
        List<Task> tasks = taskRepository.findByUserId(loginUser.getId());
        
        request.setAttribute("tasks", tasks);
        
        return "/WEB-INF/views/task_list.jsp";
    }
}