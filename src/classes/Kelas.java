/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class Kelas {
    
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    
    public static String[] getPrimaryCode()
    {
        int countData = 0;
        String[] data = null;
        
        try {
            connectDB();
            
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM kelas");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT kdkelas FROM kelas");
            
            int counter = 0;
            
            while(result.next()) {
                data[counter] = result.getString(1);
                ++counter;
            }
            
        } catch(Exception e) {
            System.exit(1);
        }
        
        return data;
    }
    
    public static void connectDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/smkindonesia", "root", "");            
            
        } catch (Exception e) {
            
            System.out.println(e);
            System.exit(1);
            
        }
    }
    
    public static Object[][] getKelas()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM kelas");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][2];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM kelas");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addKelas(String kdKelas, String namaKelas)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO kelas VALUES (?, ?)");
            preparedStatement.setString(1, kdKelas);
            preparedStatement.setString(2, namaKelas);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateKelas(String kdKelas, String namaKelas)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE kelas SET namakelas = ? WHERE kdkelas = ?");
            preparedStatement.setString(1, namaKelas);
            preparedStatement.setString(2, kdKelas);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteKelas(String kdKelas)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM kelas WHERE kdkelas = ?");
            preparedStatement.setString(1, kdKelas);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
