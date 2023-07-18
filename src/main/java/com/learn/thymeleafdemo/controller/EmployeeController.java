package com.learn.thymeleafdemo.controller;

import com.learn.thymeleafdemo.entity.Employee;
import com.learn.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  //dispaying list of all employees
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @PostMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        // creating model attribue to bind form data

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";

    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value ="id") long id, Model model){

        // get employee from service
        Employee employee = employeeService.getEmployeeById(id);

        //setting employee as model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee{id}")
    public String deleteEmployee(@PathVariable(value= "id") long id){

        // call delete employee method

        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

}
