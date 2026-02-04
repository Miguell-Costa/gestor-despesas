package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:db/gestaoFinanceira.db");
        } catch (SQLException e) {
            System.out.println("Erro ao ligar à base de dados");
            e.printStackTrace();
            return null;
        }
    }
}
