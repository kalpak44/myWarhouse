package ClientEditor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerData {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    public Socket getSocket(){
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    
    public void setOOS(ObjectOutputStream oos){
        this.oos = oos;
    }
    public ObjectOutputStream getOOS(){
        return oos;
    }
    
    public void setOIS(ObjectInputStream ois){
        this.ois = ois;
    }
    public ObjectInputStream getOIS(){
        return ois;
    }
    
    
}
