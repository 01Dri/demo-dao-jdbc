package me.dri.db;

import me.dri.exceptions.DbExcepetion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Db {

    private static Connection conn = null;

    public static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbExcepetion("Erro ao ler o arquivo db.properties, causa: " + e.getMessage());
        }
    }

    public static Connection getConnection() {

        if (conn != null) {
            System.out.println("Conexão com o banco de dados já foi estabelicida!");
        } else {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbExcepetion("Erro ao tenta estabelecer uma conexão com o banco de dados, causa: "
                        + e.getMessage());
            }
        }
        return conn;

    }

    public static void closeConnection() {
        if (conn == null) {
            System.out.println("Conexão com o banco já foi desligada!");
        } else {
            try {
                conn.close();
                System.out.println("Conexão desligada!");
            } catch (SQLException e) {
                throw new DbExcepetion("Erro ao tentar desligar a conexão com o banco de dados, causa: "
                        + e.getMessage());
            }
        }

    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbExcepetion("Erro ao tentar finalizar statement, causa: " + e.getMessage());
            }
        }
    }

    public static void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbExcepetion("Erro ao tentar finalizar result, causa: " + e.getMessage());
            }
        }
    }
}

