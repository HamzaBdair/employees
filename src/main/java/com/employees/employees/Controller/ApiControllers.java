package com.employees.employees.Controller;

import com.employees.employees.Models.Employee;
import com.employees.employees.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private EmployeeRepo emlployeeRepo;

    @GetMapping(value="/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value="/employees")
    public List<Employee> getEmployees(){
    return emlployeeRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveEmployee(@RequestBody Employee employee){
        emlployeeRepo.save(employee);
        return "Saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateEmployee(@PathVariable long id,@RequestBody Employee employee){
        Employee updateEmployee = emlployeeRepo.findById(id).get();
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getFirstName());
        updateEmployee.setOccupation(employee.getOccupation());
        updateEmployee.setAge(employee.getAge());
        emlployeeRepo.save(updateEmployee);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
    Employee deleteEmployee=emlployeeRepo.findById(id).get();
    emlployeeRepo.delete(deleteEmployee);
    return "Delete employee with id: "+id;
    }
}
