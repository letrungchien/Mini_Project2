package org.example.miniproject.controller;

import org.example.miniproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private  EmployeeRepository employeeRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "home";
    }
}
