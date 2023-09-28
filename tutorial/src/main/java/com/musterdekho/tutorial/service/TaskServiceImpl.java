package com.musterdekho.tutorial.service;

import com.musterdekho.tutorial.dto.TaskDto;
import com.musterdekho.tutorial.entity.Task;
import com.musterdekho.tutorial.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository repository;
    @Override
    public Task createTask(Task task, Integer user) {

         repository.createTask( task.getTitle(), task.getDescription(), task.getStatus(),task.getDueDate(),user);
        return task;
    }
    @Override
    public List<Task> findAll() {
        //return repository.getAllTask();
        return repository.findAll();
    }

    @Override
    public Task findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Task> findTaskByTitle(String title) {

        return  repository.findByTitle(title);
    }

    @Override
    public List<Task> findTaskByDescription(String description) {
        return  repository.findByDescription(description);
    }

    @Override
    public List<Task> taskStatus(Boolean status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Task> taskDueDate(Date dueDate) {
        return repository.findByDueDate(dueDate);
    }


    public Task updateTask(Task task){
        return repository.save(task);

    }


    @Override
    public void DeleteTask(Integer id) {
        repository.deleteById(id);

    }
}
