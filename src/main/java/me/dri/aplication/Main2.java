package me.dri.aplication;

import me.dri.model.dao.DepartmentDao;
import me.dri.model.dao.impl.DaoFactory;
import me.dri.model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1: department insert ===");
        Department department = new Department(null, "Fruits");
        departmentDao.insert(department);
        System.out.println("Id from " + department.getName() + " is " + department.getId());

        System.out.println("=== TEST 2: department findById ===");
        Department newDepartment = departmentDao.findByID(3);
        System.out.println(newDepartment);
        

        System.out.println("=== TEST 3: department update ===");
        department = departmentDao.findByID(1);
        department.setName("Computadores");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println("=== TEST 4: department findAll ===");
        List<Department> list = new ArrayList<>();
        list = departmentDao.findAll();
        for (Department dep : list) {
            System.out.println(dep);
        }
        System.out.println("=== TEST 5: department deleteByID ===");
        departmentDao.deleteById(5);


    }

}
