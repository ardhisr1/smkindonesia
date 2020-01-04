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
public class Mapel {
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
            result = statement.executeQuery("SELECT count(*) FROM mapel");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT kdmapel FROM mapel");
            
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
    
    public static Object[][] getMapel()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM mapel");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][2];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM mapel");
            
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
    
    public static boolean addMapel(String kdmapel, String namaMapel)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO mapel VALUES (?, ?)");
            preparedStatement.setString(1, kdmapel);
            preparedStatement.setString(2, namaMapel);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateMapel(String kdMapel, String namaMapel)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE mapel SET namaMapel = ? WHERE kdmapel = ?");
            preparedStatement.setString(1, namaMapel);
            preparedStatement.setString(2, kdMapel);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteMapel(String kdMapel)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM mapel WHERE kdmapel = ?");
            preparedStatement.setString(1, kdMapel);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
