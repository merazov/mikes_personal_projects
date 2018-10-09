package com.mike.sql.hypersql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
public class UseHSQLDBForUnitTestWithStatementsToConductStaticQueries {

    @Autowired
    private Connection dbConnection;

    @Before
    public void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        // initialize database
        initDatabase();
    }

    @Test
    public void checkNameExistsTest() throws SQLException {
        Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                           ResultSet.CONCUR_READ_ONLY);

        ResultSet result = statement.executeQuery("SELECT name FROM employee");
        if (result.first()) {
            assertEquals("Miguel Erazo", result.getString("name"));
        }

        if (result.last()) {
            assertEquals("Adriana Arcaine", result.getString("name"));
        }
    }

    private void initDatabase() throws SQLException {

        try (Statement statement = dbConnection.createStatement();) {
            statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(250) NOT NULL, email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
            dbConnection.commit();

            statement.executeUpdate("INSERT INTO employee VALUES (1,'Miguel Erazo', 'merazo@amazon.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (2,'Adriana Arcaine', 'merazo@amazon.com')");

            dbConnection.commit();
        }
    }
}
