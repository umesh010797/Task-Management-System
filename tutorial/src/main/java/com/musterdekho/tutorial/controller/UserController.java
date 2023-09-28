package com.musterdekho.tutorial.controller;

import com.musterdekho.tutorial.dto.TaskDto;
import com.musterdekho.tutorial.entity.Task;
import com.musterdekho.tutorial.entity.User;
import com.musterdekho.tutorial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
@Controller
@RestController
//@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/userRegister")
    public @ResponseBody byte[] userRegister() throws IOException {
        Resource resource = new ClassPathResource("templates/userRegister.html"); // Assuming your HTML file is in the "static" folder
        return Files.readAllBytes(resource.getFile().toPath());
    }
    @PostMapping("/createuser")
    public ModelAndView createUser(@ModelAttribute User user, HttpSession session) throws IOException {
        service.createUser(user);
        session.setAttribute("successMessage", "User registered successfully");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/finduser");
        return mav;
    }



    @GetMapping("/finduser")
    public ModelAndView findUser() {
        List<User> userList = service.findAll();
        ModelAndView mav = new ModelAndView("users"); // Specify the template name directly
        mav.addObject("listuser", userList);
        return mav;
    }

//    @GetMapping("/create")
//    public ModelAndView showCreateUser(Model model) {
//        List<User> userList = service.findAll();
//        ModelAndView mav = new ModelAndView("users"); // Specify the template name directly
//        mav.addObject("listuser", userList);
//
//        return mav;
//    }


    @GetMapping("/findbyid/{id}")

    public User findProduct(@PathVariable Integer id){
        return service.findUser(id);
}
//    @PutMapping("/updateuse")
//    User updateUser (@RequestBody User user){
//        return service.updateUser(user);
//    }
//    @DeleteMapping("/deletebyid/{id}")
//    public void Deleteuser(@PathVariable Integer id){
//        service.DeleteUser(id);
//
//    }
@PutMapping("/updateuse")
User updateUser (@RequestBody User user){
    return service.updateUser(user);
}
    @DeleteMapping("/deletebyid/{id}")
    public void Deleteuser(@PathVariable Integer id){
        service.DeleteUser(id);

    }

    }
