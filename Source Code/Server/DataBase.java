/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servereditor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kalpak44
 */
public class DataBase extends javax.swing.JFrame{
    private String dbUrl, dbUser, dbPass;
    private Connection con;
    private Boolean validation = false;

    DataBase(String dbUrl,String dbUser,String dbPass){
        connect(dbUrl,dbUser,dbPass);
    }
    DataBase(){
        Propierties pr = new Propierties();
        this.dbUrl =(String) pr.getDbUrl();
        this.dbUser = (String)pr.getDbUser();
        this.dbPass = (String)pr.getDbPass();
        
        connect(dbUrl,dbUser,dbPass);
    }
    
    public void connect(String dbUrl,String dbUser,String dbPass){
        try{
            //accesing driver from JAR file
            Class.forName("com.mysql.jdbc.Driver");
            
            this.con = DriverManager.getConnection("jdbc:mysql://"+dbUrl,dbUser,dbPass);
            if(con!=null) {
                System.out.println("Database Connection Successful !\n");
                this.validation = true;
            }
            
        }catch(Exception e){
            
            
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Configuration failed. Do you want to change settings?", "exit",dialogButton);
            if(dialogResult==0){
                this.validation = false;
                new ConfigForm().setVisible(true);
            }else{
                System.out.println("Bye");
                this.validation = false;
    		System.exit(0);
            }
        }
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public Boolean getValidation(){
        return validation ;
    }
    
    
}
