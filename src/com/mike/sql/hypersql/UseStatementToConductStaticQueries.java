package com.mike.sql.hypersql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;

public class UseStatementToConductStaticQueries {
    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        // initialize database
        initDatabase();
    }

    @Test
    public void checkNameExistsTest() throws SQLException {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                              ResultSet.CONCUR_READ_ONLY);) {

            ResultSet result = statement.executeQuery("SELECT name FROM employee");
            if (result.first()) {
                assertEquals("Miguel Erazo", result.getString("name"));
            }

            if (result.last()) {
                assertEquals("Adriana Arcaine", result.getString("name"));
            }

        }
    }

    private static void initDatabase() throws SQLException {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(250) NOT NULL, email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
            connection.commit();

            statement.executeUpdate("INSERT INTO employee VALUES (1,'Miguel Erazo', 'merazo@amazon.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (2,'Adriana Arcaine', 'merazo@amazon.com')");
            
            connection.commit();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
    }
}
