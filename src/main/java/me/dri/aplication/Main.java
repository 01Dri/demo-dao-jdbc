package me.dri.aplication;

import me.dri.model.dao.SellerDao;
import me.dri.model.dao.impl.DaoFactory;
import me.dri.model.entities.Department;
import me.dri.model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) {

      SellerDao sellerDao = DaoFactory.createSellerDao();
      System.out.println("=== TEST 1: seller findByID ===");
      Seller seller = sellerDao.findByID(1);
      System.out.println(seller);

      System.out.println("=== TEST 2: seller findByDepartment ===");
      Department dep = new Department(2, null);
      List<Seller> list = sellerDao.findByDepartment(dep);
      for (Seller obj : list) {
          System.out.println(obj);
      }
      System.out.println("=== TEST 3: seller findAll ===");
      list = sellerDao.findAll();
      for (Seller seller1 : list) {
          System.out.println(seller1);
      }
    }

}
