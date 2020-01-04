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
public class Jurusan {
    
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    
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
    
    public static String[] getPrimaryCode()
    {
        int countData = 0;
        String[] data = null;
        
        try {
            connectDB();
            
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM jurusan");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT kdjurusan FROM jurusan");
            
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
    
    public static Object[][] getJurusan()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM jurusan");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][2];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM jurusan");
            
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
    
    public static Object[][] search(String jurusan)
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM jurusan");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][2];
            preparedStatement = con.prepareStatement("SELECT * FROM jurusan WHERE namajurusan LIKE ?");
            preparedStatement.setString(1, "%" + jurusan + "%");
            result = preparedStatement.executeQuery();
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                ++counter;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addJurusan(String kodeJurusan, String jurusan)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO jurusan VALUES (?, ?)");
            preparedStatement.setString(1, kodeJurusan);
            preparedStatement.setString(2, jurusan);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateJurusan(String kodeJurusan, String jurusan)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE jurusan SET namajurusan = ? WHERE kdjurusan = ?");
            preparedStatement.setString(1, jurusan);
            preparedStatement.setString(2, kodeJurusan);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteJurusan(String kodeJurusan)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM jurusan WHERE kdjurusan = ?");
            preparedStatement.setString(1, kodeJurusan);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
