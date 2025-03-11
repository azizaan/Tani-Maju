/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form;

/**
 *
 * @author ACER
 */
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    static Connection conn;

    public static Connection getKoneksi() throws SQLException {
        // Ensure the connection is valid, if not, create a new one
        if (conn == null || conn.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/studicase_pupuk";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
        }
        
        // Check if the connection is valid, if not, reconnect
        if (!conn.isValid(2)) { // Timeout is set to 2 seconds for validity check
            conn.close();  // Close any invalid connection
            String url = "jdbc:mysql://localhost:3306/studicase_pupuk";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass); // Reconnect
        }

        return conn;
    }
}
