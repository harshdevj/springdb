package com.example.springdb;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/api")
public class LoginController {

    // Database credentials
    static final String DB_URL = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)" +
            "(host=adb.ap-mumbai-1.oraclecloud.com))(connect_data=(service_name=g12a583074c6c9b_harshdb_high.adb.oraclecloud.com))" +
            "(security=(ssl_server_dn_match=yes)))";
    static final String USER = "ADMIN";
    static final String PASS = "Autonomous25";

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Create statement
        stmt = conn.createStatement();
        String sql;
        ResultSet rs;

        // Example: Select data
        sql = "SELECT * FROM DEVUSER.logins where uname='" + username +"' and password='" + password + "'";
        rs = stmt.executeQuery(sql);

        boolean isValid = false;
        while (rs.next()) {
            // Retrieve data by column name
            String user = rs.getString("uname");
            String pwd = rs.getString("password");

            // Display values
            System.out.println("UID: " + user + ", PWD: " + pwd);
            isValid = true;
        }
        rs.close();
        stmt.close();
        conn.close();

        if (isValid) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}
