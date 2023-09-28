package com.musterdekho.tutorial.controller;

import com.musterdekho.tutorial.dto.TaskDto;
import com.musterdekho.tutorial.entity.Task;
import com.musterdekho.tutorial.entity.User;
import com.musterdekho.tutorial.service.TaskService;
import com.musterdekho.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController

public class TaskController {
    @Autowired
    private TaskService service;
    private UserService userService;
    @GetMapping("/addtask")
    public @ResponseBody byte[] userRegister() throws IOException {
        Resource resource = new ClassPathResource("templates/addtask.html"); // Assuming your HTML file is in the "static" folder
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @GetMapping("/title")
    public @ResponseBody byte[] findTaskTitle() throws IOException {
        Resource resource = new ClassPathResource("templates/title.html"); // Assuming your HTML file is in the "static" folder
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @GetMapping("/description")
    public @ResponseBody byte[] findTaskDescription() throws IOException {
        Resource resource = new ClassPathResource("templates/description.html"); // Assuming your HTML file is in the "static" folder
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @GetMapping("/gettasks")
    public ModelAndView getAllTasks() {
//         List<TaskDto> taskList = service.findAll();
        List<Task> taskList = service.findAll();


         ModelAndView mav = new ModelAndView("index"); // Specify the template name directly
         mav.addObject("listtask", taskList);

        return mav;
    }
    @PostMapping("/createtask")
    public ModelAndView createTask(@ModelAttribute Task task, @RequestParam Integer user) {
        service.createTask(task, user);
        List<Task> taskList = service.findAll();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/gettasks");
         return mav;

    }
    @GetMapping("/findbytitle")

    public ModelAndView findTaskByTitle(@ModelAttribute Task task,@RequestParam String title){

        List<Task> taskList = (List<Task>) service.findTaskByTitle(title);


        ModelAndView mav = new ModelAndView("titletask"); // Specify the template name directly
        mav.addObject("listtask", taskList);

        return mav;
    }
    @GetMapping("/findbydescription")

    public ModelAndView findTaskByDescription(@RequestParam String description){
        List<Task> taskList = service.findTaskByDescription(description);


        ModelAndView mav = new ModelAndView("descriptiontask"); // Specify the template name directly
        mav.addObject("listtask", taskList);

        return mav;
    }
    @GetMapping("/findbystatus")
    public ModelAndView findTaskByStatus(){

        List<Task> taskList = service.taskStatus(true);


        ModelAndView mav = new ModelAndView("activetask"); // Specify the template name directly
        mav.addObject("listtask", taskList);

        return mav;
    }
    @GetMapping("/findduedate")
    public ModelAndView findduedate( @RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate){
        List<Task> taskList = service.taskDueDate(dueDate);


        ModelAndView mav = new ModelAndView("activetask"); // Specify the template name directly
        mav.addObject("listtask", taskList);

        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Task task){
        ModelAndView mav = new ModelAndView();
        Integer id= task.getId();
        User user= task.getAssignTo();
        Integer uid= user.getId();
        service.updateTask(task);
       // mav.addObject("message", "Invoice with id: '" + id + "' is updated successfully!");
        mav.setViewName("redirect:/gettasks");
        return mav;
    }

@RequestMapping(value="/updatetask/{id}/{user}",method = RequestMethod.GET)
public ModelAndView updateUser(@PathVariable Integer id, @PathVariable Integer user) {
    ModelAndView mav = new ModelAndView();
    String page = null;


        Task task = service.findById(id);
        mav.addObject("task", task);
        mav.setViewName("edittask");


    return mav;
}
    @GetMapping("/deletetask/{id}")
    public ModelAndView DeleteTask(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        service.DeleteTask(id);
        mav.setViewName("redirect:/gettasks");
        return mav;
    }
}
