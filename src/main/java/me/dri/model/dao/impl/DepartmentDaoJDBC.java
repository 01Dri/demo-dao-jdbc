package me.dri.model.dao.impl;

import me.dri.db.Db;
import me.dri.exceptions.DbExcepetion;
import me.dri.model.dao.DepartmentDao;
import me.dri.model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDaoJDBC implements DepartmentDao {

    private final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            String insert = "INSERT INTO department (Name)  VALUES (?)";
            st = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                Db.closeResult(rs);
            }
            else  {
                throw new DbExcepetion("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        }
        finally {
            Db.closeStatement(st);

        }

    }

    @Override
    public void update(Department obj) {
        String queryUpdate = "UPDATE department SET Name = ? WHERE Id = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(queryUpdate);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        }
        finally {
            Db.closeStatement(st);

        }


    }

    @Override
    public void deleteById(Integer id) {
        String queryDeleteById = "DELETE FROM department WHERE Id = ?";
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(queryDeleteById);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new DbExcepetion("Department not exists");
            }
        }
        catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        }
        finally {
            Db.closeStatement(st);
        }


    }

    @Override
    public Department findByID(Integer id) {
        String queryFindById = "SELECT * FROM department " +
                " WHERE Id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(queryFindById);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;

            }
            return null;
        }
        catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        }
        finally {
            Db.closeStatement(st);
            Db.closeResult(rs);

        }

    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            List<Department> departments = new ArrayList<>();
            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                departments.add(dep);
            }
            return departments;
        }
        catch (SQLException e) {
            throw new DbExcepetion(e.getMessage());
        }
        finally {
            Db.closeStatement(st);
            Db.closeResult(rs);

        }
    }
}
