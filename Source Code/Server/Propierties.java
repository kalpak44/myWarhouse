/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servereditor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Propierties extends javax.swing.JFrame{
   
    ConfigForm configForm = new ConfigForm();
    private String dbUrl, dbUser, dbPass, servPort, demo;
    Propierties(){
        Properties prop = new Properties();
	InputStream input = null;
 
	try {
            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
            dbUrl = prop.getProperty("database");
            dbUser = prop.getProperty("dbuser");
            dbPass = prop.getProperty("dbpassword");
            servPort = prop.getProperty("sPort");
            demo = prop.getProperty("DemoData");
            
            //initialization variables
            this.dbUrl=dbUrl;
            this.dbUser = dbUser;
            this.dbPass = dbPass;
            this.servPort=servPort;
            
        }catch(IOException e){}
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void getPropieries(){
        Properties prop = new Properties();
	InputStream input = null;
 
	try {
 
		input = new FileInputStream("config.properties");
 
		// load a properties file
		prop.load(input);
 
		// get the property value
                dbUrl = prop.getProperty("database");
                dbUser = prop.getProperty("dbuser");
                dbPass = prop.getProperty("dbpassword");
                servPort = prop.getProperty("sPort");
                demo = prop.getProperty("DemoData");
                
                System.out.println("writen demo is "+demo);
                
                if(demo.equals("none")){
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want install demo products?", "title",dialogButton);
                    if(dialogResult==0){
                        //1 - initialization and install demo data
                        new InitializationForm(1);
                    }else{
                        //2 - initialization and did not install demo data
                        new InitializationForm(2);
                    }
                }else if(demo.equals("configured")){
                    new MainForm().setVisible(true);
                }
                
               
               
 
	} catch (IOException ex) {
            
            System.out.println("hel yah: Not File Propieties");
            
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Not found configuration file, do you want to configure server?", "exit",dialogButton);
            if(dialogResult==0){
                configForm.setVisible(true);
            }else{
                System.out.println("Bye");
    		System.exit(0);
            }
            
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
                
	}
    }
    
    
    
    public void setPropierties(String dbUrl, String dbUser, String dbPass, String servPort){
        Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("database", dbUrl);
		prop.setProperty("dbuser", dbUser);
		prop.setProperty("dbpassword", dbPass);
                prop.setProperty("sPort", servPort);
                prop.setProperty("DemoData", "none");
                
		// save properties to project root folder
		prop.store(output, null);
                
                
                this.dbUrl=dbUrl;
                this.dbUser = dbUser;
                this.dbPass = dbPass;
                this.servPort=servPort;
                this.demo = "none";
                
 
	} catch (IOException io) {
            JOptionPane.showMessageDialog(this, "Congratulations not saved."
                ,"Title", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
	}
    }
    
    public void setPropierties(String dbUrl, String dbUser, String dbPass, String servPort, String demo){
        Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("database", dbUrl);
		prop.setProperty("dbuser", dbUser);
		prop.setProperty("dbpassword", dbPass);
                prop.setProperty("sPort", servPort);
                prop.setProperty("DemoData", demo);
                
		// save properties to project root folder
		prop.store(output, null);
                
                
                this.dbUrl=dbUrl;
                this.dbUser = dbUser;
                this.dbPass = dbPass;
                this.servPort=servPort;
                this.demo = demo;
                
 
	} catch (IOException io) {
            JOptionPane.showMessageDialog(this, "Congratulations not saved."
                ,"Title", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
	}
    }

    
    
    public String getDbUrl(){
        return dbUrl;
    }
    
    public String getDbUser(){
        return dbUser;
    }
    
    public String getDbPass(){
        return dbPass;
    }
    public String getSPort(){
        return servPort;
    }
    private String getDemo(){
        return demo;
    }
    public void setConfigSuccessfull(){
        setPropierties(dbUrl, dbUser, dbPass, servPort, "configured");
    }
}
