package me.dri.model.dao;


import me.dri.model.entities.Department;
import me.dri.model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findByID(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
