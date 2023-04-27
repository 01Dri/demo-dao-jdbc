package me.dri.aplication;

import me.dri.model.dao.SellerDao;
import me.dri.model.dao.impl.DaoFactory;
import me.dri.model.entities.Department;
import me.dri.model.entities.Seller;

import java.util.Date;

public class Main {


    public static void main(String[] args) {

      SellerDao sellerDao = DaoFactory.createSellerDao();
      System.out.println("=== TEST 1: seller findByID ===");
      Seller seller = sellerDao.findByID(1);
      System.out.println(seller);

    }

}
