package org.example.miniproject.controller;

import org.example.miniproject.model.Employee;
import org.example.miniproject.repository.DepartmentRepository;
import org.example.miniproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @GetMapping
    public String home(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "home";
    }

    @GetMapping("/employees/add")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "add";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute Employee employee,
                               @RequestParam("file") MultipartFile file) throws IOException {

        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            employee.setDepartment(
                    departmentRepository.findById(employee.getDepartment().getId()).orElse(null)
            );
        }

        if (!file.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            file.transferTo(new File(uploadDir + fileName));
            employee.setAvatar(fileName);
        }

        employeeRepository.save(employee);
        return "redirect:/";
    }
}
