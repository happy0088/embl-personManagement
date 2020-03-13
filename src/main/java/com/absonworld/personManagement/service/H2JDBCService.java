package com.absonworld.personManagement.service;

import com.absonworld.personManagement.entity.Person;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class H2JDBCService {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    static Connection conn = null;
    static Statement stmt = null;

    static {
        try {
            conn = getConnection();
            dropTable();
            createTable(conn);
            insertData();
            printTableData("PERSON");
            // gc();

        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } /*finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try*/
        System.out.println("Goodbye!");
    }

    public static void printTableData(String table) {
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * from " + table);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData() throws SQLException {
        // STEP 3: Execute a query
        stmt = conn.createStatement();
        String sql = "INSERT INTO PERSON(personId,firstName,lastName,favouriteColor,age,hobby) " + "VALUES (10001,'Happy', 'Singh','Black',30,'drums')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO PERSON(personId,firstName,lastName,favouriteColor,age,hobby) " + "VALUES (10002,'Abhishek', 'Kumar','White',31,'beatboxing')";
        stmt.executeUpdate(sql);

        System.out.println("Inserted records into the table...");
    }

    public static Connection getConnection() {
        // STEP 1: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTable(Connection conn) {
        //STEP 3: Execute a query
        System.out.println("Creating table in given database...");
        try {
            stmt = conn.createStatement();

            String sql = "CREATE TABLE if not exists  PERSON " +
                    "(personId INTEGER not NULL auto_increment, " +
                    " firstName VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER, " +
                    " favouriteColor VARCHAR(255), " +
                    " hobby VARCHAR(255), " +
                    " PRIMARY KEY ( personId ))";
            stmt.executeUpdate(sql);

            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        //STEP 3: Execute a query
        System.out.println("Dropping table in given database...");
        try {
            stmt = conn.createStatement();

            String sql = "Drop TABLE  PERSON ";
            stmt.executeUpdate(sql);
            System.out.println("Dropped table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void gc() {
        // STEP 4: Clean-up environment
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int createPerson(Person person) {

        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO PERSON(firstName,lastName,favouriteColor,age,hobby) " +
                    "VALUES (" + "\'"+ person.getFirst_name() +"\'"+ ", " + "\'"+person.getLast_name() +"\'"+ ", " +"\'"+ person.getFavourite_colour() + "\'"+"," +
                    " " + person.getAge() +
                    ", " + "\'"+person.getHobby() +"\'"+ ")";

            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Person getPersonByID(Long id) {
        printTableData("PERSON");
        Person person = null;
        String sql = "SELECT * FROM PERSON where personId = " + id;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // STEP 4: Extract data from result set
            {
                while (rs.next()) {
                    person = new Person();
                    // Retrieve by column name
                    person.setPersonId(rs.getLong("personId"));
                    person.setFirst_name(rs.getString("firstName"));
                    person.setLast_name(rs.getString("lastName"));
                    person.setFavourite_colour(rs.getString("favouriteColor"));
                    person.setAge(rs.getInt("age"));
                    // person.setHobby(rs.getString("hobby"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person getPersonDetails(String firstName) {
        printTableData("PERSON");
        Person person = null;
        String sql = "SELECT * FROM PERSON where firstName = \'" + firstName + "\' ";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // STEP 4: Extract data from result set
            {
                while (rs.next()) {
                    person = new Person();
                    // Retrieve by column name
                    person.setPersonId(rs.getLong("personId"));
                    person.setFirst_name(rs.getString("firstName"));
                    person.setLast_name(rs.getString("lastName"));
                    person.setFavourite_colour(rs.getString("favouriteColor"));
                    person.setAge(rs.getInt("age"));
                     person.setHobby(Collections.singletonList(rs.getString("hobby")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }


    public int updatePerson(Person person) {
        String sql = "update PERSON set firstName =  \'" + person.getFirst_name() + "\' where personId =" + person.getPersonId();
        try {
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            printTableData("PERSON" +
                    "");
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String createSession(int cid) {
        String sessionId = createSessionId(cid);
        int status = 0;
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO SESSION(sessionId,cid) VALUES (\'" + sessionId + "\', " + cid + ")";
            status = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status > 0) {
            return sessionId;
        }

        return null;
    }

    public String fetchSession(int cid) {
        String sql = "SELECT sessionId FROM SESSION where cid = " + cid;
        ResultSet rs = null;
        String sessionId = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // STEP 4: Extract data from result set
            while (rs.next()) {
                sessionId = rs.getString("sessionId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printTableData("SESSION");
        return sessionId;
    }


    private String createSessionId(int cid) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;
    }

    public boolean validateSession(String sessionId) {
        String sql = "SELECT sessionId FROM SESSION where sessionId = \'" + sessionId + "\'";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // STEP 4: Extract data from result set
            while (rs.next()) {
                //sessionId= rs.getString("sessionId");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printTableData("SESSION");
        return false;
    }

    public List<Person> getAllPersonDetails() {

        printTableData("PERSON");
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM PERSON ";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // STEP 4: Extract data from result set
            //if (rs.getFetchSize() > 0)
            {
                while (rs.next()) {
                    Person person = new Person();
                    // Retrieve by column name
                    person.setPersonId(rs.getLong("personId"));
                    person.setFirst_name(rs.getString("firstName"));
                    person.setLast_name(rs.getString("lastName"));
                    person.setFavourite_colour(rs.getString("favouriteColor"));
                    person.setAge(rs.getInt("age"));
                     person.setHobby(Collections.singletonList(rs.getString("hobby")));
                    personList.add(person);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public int deletePerson(Long id) {
        {
            String sql = "Delete from PERSON where personId =" + id;
            try {
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sql);
                printTableData("PERSON");
                System.out.println("Deleted "+id);
                return count;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
}