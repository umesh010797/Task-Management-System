package com.musterdekho.tutorial.service;

import com.musterdekho.tutorial.dto.TaskDto;
import com.musterdekho.tutorial.entity.Task;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    public Task createTask(Task task, Integer user);
    public List<Task> findAll();
    public Task findById(Integer id);
    public List<Task> findTaskByTitle(String title);
    public List<Task> findTaskByDescription(String description);
    public List<Task> taskStatus(Boolean status);
    public List<Task> taskDueDate(Date dueDate);
   // Task updateTask (Task task,Integer user,Integer id);
   Task updateTask (Task task);
    public void DeleteTask(Integer id);
}
