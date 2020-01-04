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
public class Guru {
    
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
            result = statement.executeQuery("SELECT count(*) FROM guru");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT kdguru FROM guru");
            
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
            result = statement.executeQuery("SELECT count(*) FROM guru");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][5];
            preparedStatement = con.prepareStatement("SELECT * FROM guru WHERE namaguru LIKE ? OR nip LIKE ?");
            preparedStatement.setString(1, "%" + field + "%");
            preparedStatement.setString(2, "%" + field + "%");
            result = preparedStatement.executeQuery();
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static Object[][] getGuru()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM guru");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][5];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM guru");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addGuru(String kodeGuru, String nip, String namaGuru, String alamat, String jk)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO guru VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, kodeGuru);
            preparedStatement.setString(2, nip);
            preparedStatement.setString(3, namaGuru);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, jk);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateGuru(String kodeGuru, String nip, String namaGuru, String alamat, String jk)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE guru SET nip = ?, namaguru = ?, alamat = ?, jk = ? WHERE kdguru = ?");
            preparedStatement.setString(1, nip);
            preparedStatement.setString(2, namaGuru);
            preparedStatement.setString(3, alamat);
            preparedStatement.setString(4, jk);
            preparedStatement.setString(5, kodeGuru);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteGuru(String kodeGuru)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM guru WHERE kdguru = ?");
            preparedStatement.setString(1, kodeGuru);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
