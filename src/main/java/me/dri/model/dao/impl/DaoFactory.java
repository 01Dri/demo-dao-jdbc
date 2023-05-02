package me.dri.model.dao.impl;

import me.dri.db.Db;
import me.dri.model.dao.DepartmentDao;
import me.dri.model.dao.SellerDao;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(Db.getConnection());
    }
    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(Db.getConnection());
    }
}
