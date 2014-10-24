/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.*;


/**
 *
 * @author eco
 */
public class db {
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public db(){
        connect();
    }
    
    public void connect(){
       
        try{
            String dirver="sun.jdbc.odbc.JdbcOdbcDriver";
            Class.forName(dirver);
            String db="jdbc:odbc:db1";
            con=DriverManager.getConnection(db);
            
        
            
            /*
            con=DriverManager.getConnection("jdbc:odbc://D:/Netbeans/db1.accdb");
            st=con.createStatement();
                     System.out.println("3erer");
            
                    */
            /*
            String fileName="D:/Netbeans/db1.accdb";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+fileName;
	    con = DriverManager.getConnection(url,"","");
                    */
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);      
            String sql="select * from Table1";
            rs=st.executeQuery(sql);
            
       /*
            while(rs.next()){
                int id=rs.getInt("ID");
                String fname=rs.getString("Fname");
                String lname=rs.getString("Lname");
                String age=rs.getString("Age");
                
                System.out.println(id+" "+fname+" "+lname+" "+age);
            }
            */
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public static void main(String[] args) {
  
        new db();
        new gui();
    }
    
}
