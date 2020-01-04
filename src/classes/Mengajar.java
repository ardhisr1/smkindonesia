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
public class Mengajar {
    
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
        
    public static Object[][] getMengajar()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM mengajar");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][5];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM mengajar");
            
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
    
    public static boolean addMengajar(String kodeMengajar, String kodeGuru, String kodeJurusan, String kodeKelas, String kodeMapel)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO mengajar VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, kodeMengajar);
            preparedStatement.setString(2, kodeGuru);
            preparedStatement.setString(3, kodeJurusan);
            preparedStatement.setString(4, kodeKelas);
            preparedStatement.setString(5, kodeMapel);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateMengajar(String kodeMengajar, String kodeGuru, String kodeJurusan, String kodeKelas, String kodeMapel)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE mengajar SET kdguru = ?, kdjurusan = ?, kdkelas = ?, kdmapel = ? WHERE kdmengajar = ?");
            preparedStatement.setString(1, kodeGuru);
            preparedStatement.setString(2, kodeJurusan);
            preparedStatement.setString(3, kodeKelas);
            preparedStatement.setString(4, kodeMapel);
            preparedStatement.setString(5, kodeMengajar);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteMengajar(String kodeMengajar)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM mengajar WHERE kdmengajar = ?");
            preparedStatement.setString(1, kodeMengajar);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
