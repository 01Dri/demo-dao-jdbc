package me.dri.aplication;

import me.dri.model.dao.SellerDao;
import me.dri.model.dao.impl.DaoFactory;
import me.dri.model.entities.Department;
import me.dri.model.entities.Seller;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
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
      System.out.println("=== TEST 4: seller insert ===");
      Seller newSeller = new Seller(null, "Melanie" , "melanie@gmail.com" , new Date(), 4000.0 , dep);
      sellerDao.insert(newSeller);
      System.out.println("Inserted! New ID = " + newSeller.getId());

      System.out.println("=== TEST 6: seller update ===");
      seller = sellerDao.findByID(1);
      seller.setName("Marta Waine");
      sellerDao.update(seller);
      System.out.println("Update completed!");

      System.out.println("=== TEST 5: seller delete ===");
      System.out.print("Enter a number: ");
      int id = sc.nextInt();
      sellerDao.deleteById(id);
      System.out.println("User removed!");


    }

}
