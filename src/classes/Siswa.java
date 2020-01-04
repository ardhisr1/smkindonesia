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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Siswa {
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    
    public static void connectDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/smkindonesia", "root", "");            
            
        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println(e);
            System.exit(1);
            
        }
    }
    
    public static String[] getPrimaryCode()
    {
        int countData = 0;
        String[] data = null;
        
        try {
            connectDB();
            
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM siswa");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT nis FROM siswa");
            
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
    
    public static Object[][] search(String field)
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM siswa");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][5];
            preparedStatement = con.prepareStatement("SELECT * FROM siswa WHERE namasiswa LIKE ? OR nis LIKE ?");
            preparedStatement.setString(1, "%" + field + "%");
            preparedStatement.setString(2, "%" + field + "%");
            result = preparedStatement.executeQuery();
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);              
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static Object[][] getSiswa()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM siswa");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][4];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM siswa");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addSiswa(String nis, String namaSiswa, String alamat, String jk)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO siswa VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, nis);
            preparedStatement.setString(2, namaSiswa);
            preparedStatement.setString(3, alamat);
            preparedStatement.setString(4, jk);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateSiswa(String nis, String namaSiswa, String alamat, String jk)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE siswa SET namasiswa = ?, alamat = ?, jk = ? WHERE nis = ?");
            preparedStatement.setString(1, namaSiswa);
            preparedStatement.setString(2, alamat);
            preparedStatement.setString(3, jk);
            preparedStatement.setString(4, nis);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteSiswa(String nis)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM siswa WHERE nis = ?");
            preparedStatement.setString(1, nis);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}