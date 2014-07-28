package ClientEditor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Propierties {
    private String HOST,port;
    Propierties(String HOST, String PORT){
        setPropierties(HOST, PORT);
    }
    Propierties(){
        Properties prop = new Properties();
	InputStream input = null;
 
	try {
            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
            HOST = prop.getProperty("HOST");
            port = prop.getProperty("PORT");

            
            //initialization variables
            this.HOST=HOST;
            this.port = port;
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Congratulations failed. Please Configure this program."
                ,"Title", JOptionPane.WARNING_MESSAGE);
        }
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
    
    
    public void setPropierties(String HOST, String PORT){
        Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("HOST", HOST);
		prop.setProperty("PORT", PORT);
		// save properties to project root folder
		prop.store(output, null);
                JOptionPane.showMessageDialog(null, "Congratulations saved."
                ,"Title", JOptionPane.INFORMATION_MESSAGE);
                
 
	} catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Congratulations not saved."
                ,"Title", JOptionPane.WARNING_MESSAGE);
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
    
    
    public String getHOST(){
        return HOST;
    }
    
    public int getPORT(){
        return Integer.parseInt(port);
    }
    
}
