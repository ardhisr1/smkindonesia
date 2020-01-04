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
public class Nilai {
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
    
    public static Object[][] getNilai()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM nilai");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][10];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM nilai");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                dataResult[counter][5] = result.getString(6);
                dataResult[counter][6] = result.getString(7);
                dataResult[counter][7] = result.getString(8);
                dataResult[counter][8] = result.getString(9);
                dataResult[counter][9] = result.getString(10);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addNilai(String kodeNilai, String nis, String kodeGuru, String kodeKelas, String kodeJurusan, String kodeMapel, double uh, double uts, double uas)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO nilai VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, kodeNilai);
            preparedStatement.setString(2, nis);
            preparedStatement.setString(3, kodeGuru);
            preparedStatement.setString(4, kodeKelas);
            preparedStatement.setString(5, kodeJurusan);
            preparedStatement.setString(6, kodeMapel);
            preparedStatement.setDouble(7, uh);
            preparedStatement.setDouble(8, uts);
            preparedStatement.setDouble(9, uas);
            preparedStatement.setDouble(10, (uh+uts+uas)/3.0);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateNilai(String kodeNilai, String nis, String kodeGuru, String kodeKelas, String kodeJurusan, String kodeMapel, double uh, double uts, double uas)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE nilai SET nis = ?, kdguru = ?, kdkelas = ?, kdjurusan = ?, kdmapel = ?, uh = ?, uts = ?, uas = ?, na = ? WHERE kdnilai = ?");            
            preparedStatement.setString(1, nis);
            preparedStatement.setString(2, kodeGuru);
            preparedStatement.setString(3, kodeKelas);
            preparedStatement.setString(4, kodeJurusan);
            preparedStatement.setString(5, kodeMapel);
            preparedStatement.setDouble(6, uh);
            preparedStatement.setDouble(7, uts);
            preparedStatement.setDouble(8, uas);
            preparedStatement.setDouble(9, (uh+uts+uas)/3.0);
            preparedStatement.setString(10, kodeNilai);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteNilai(String kodeNilai)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM nilai WHERE kdnilai = ?");
            preparedStatement.setString(1, kodeNilai);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
}
