package me.dri.model.dao;

import me.dri.model.entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Department findByID(Integer id);
    List<Department> findAll();
}
