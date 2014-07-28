/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servereditor;

/**
 *
 * @author kalpak44
 */
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Server implements Runnable {
    private ServerSocket server;
    private Socket socket;
    private DataBase db = new DataBase();
    private Statement statement;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private List<String[]> pData;
    private List<String> brands,types;
    private int sPort;

    
    
    
    public Server(){
        Propierties pr = new Propierties();
        this.sPort = Integer.parseInt(pr.getSPort());
        System.out.println("Initialization server Successsful\nPORT="+sPort);

    }
    
    public void startServer(){
        try{
            server = new ServerSocket(3128);
            new Thread(this).start();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Address is already in use."
                ,"Title", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);
        }    
    }

    
    
    public void run(){
        System.out.println("Server running.");
        JOptionPane.showMessageDialog(null, "Server runing."
                ,"Title", JOptionPane.INFORMATION_MESSAGE);
        while(true){
            try{

                socket = server.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                String auth[];
                auth =(String[])ois.readObject();
                
                    if(authorization(auth[0],auth[1])){
                        oos.writeObject(true);
                        oos.flush();
                        oos.writeObject(sayHello(auth[0]));
                        oos.flush();
                        
                        //********************************************************
                        //menu
                        sMenu(auth[0]);
                        //********************************************************

                    }else{
                        oos.writeObject(false);
                    }                

            }catch(IOException e){
                System.err.println("IOException in Run Method");
            } catch (ClassNotFoundException ex) {
                System.err.println("ClassNotFoundException in Run Method");
            }
        }
    }
    
    private void sMenu(String login){
        //menu client-server
        //if menu =
        // 1 - transfer all products
        // 2 - transfer Brands
        // 3 - get brands ang transfer products to brand
        // 4 - get qualities and transfer products to brand
        // 5 - transfer Names
        // 6 - get Names and transfer products to name
        // 7 - exit session
        // 8 - get cart list
        // 9 - check cart and buy
        
        try {
            int menu = 0;
            menu = (Integer) ois.readObject();
            if(menu==1){
                //client want all products
                System.out.println("\nClient "+login+": WANT TO VIEW ALL PRODUCTS");
                //get All from database
                getAllProducts();
                //transmite to client
                System.out.println("server: TRANSMITE ALL PRODUCTS");
                oos.writeObject(pData);
                oos.flush();
                sMenu(login);
            } else if (menu==2){
                //client want brands list
                System.out.println("\nClient "+login+": WANT BRANDS");
                getBrandsList();
                oos.writeObject(brands);
                oos.flush();
                System.out.println("server: TRANSMITE BRANDS:");
                sMenu(login);
            }else if(menu ==3){
                System.out.print("\nClient "+login+": WANT PRODUCTS TO BRAND = ");
                String br = (String)ois.readObject();
                System.out.println(br);
                getProductsToBrand(br);
                oos.writeObject(pData);
                oos.flush();
                System.out.print("server: TRANSFER PRODUCTS TO THIS BRAND\n");
                sMenu(login);
            }else if(menu ==4){
                System.out.print("\nClient "+login+": WANT PRODUCTS TO QUALITY = ");
                String qu = (String)ois.readObject();
                System.out.println(qu);
                
                if(qu.equals("Good")){
                    getProductsToQuality("Good");
                }else if(qu.equals("Bad")){
                    getProductsToQuality("Bad");
                }else if(qu.equals("Normal")){
                    getProductsToQuality("Normal");
                }else{
                    sMenu(login);
                }
                System.out.print("server: TRANSFER PRODUCTS TO THIS QUALITY\n");
                oos.writeObject(pData);
                oos.flush();
                sMenu(login);
            }else if(menu == 5){
                //client want brands list
                System.out.println("\nClient "+login+": WANT NAMES");
                getTypessList();
                oos.writeObject(types);
                oos.flush();
                System.out.println("server: TRANSMITE NAMES:");
                sMenu(login);
            }else if(menu == 6){
                System.out.print("\nClient "+login+": WANT PRODUCTS TO NAME =");
                String nm = (String)ois.readObject();
                System.out.println(nm);
                getProductsToType(nm);
                oos.writeObject(pData);
                oos.flush();
                System.out.print("server: TRANSFER PRODUCTS TO THIS NAME");
                sMenu(login);
            }else if(menu == 7){
                System.out.println("\nClient "+login+": EXIT");
            }else if(menu == 8){
                System.out.print("\nClient "+login+": WANT PRODUCTS TO ID = ");
                
                
                List<Integer> idList = (List)ois.readObject();
                
                String id="";
                
                for(int i = 0; i<idList.size();i++){
                    if(i==idList.size()-1){
                        id = id + "'"+idList.get(i)+"'";
                    }else{
                        id = id + "'" + idList.get(i) + "' OR p_id = ";
                    }
                }
                System.out.println(id);
                getProductsToID(id);
                oos.writeObject(pData);
                oos.flush();
                
                sMenu(login);
            } else if(menu ==9){
                
                System.out.println("\nClient "+login+": BAY");
                
                //get idList
                List<Integer> idList = (List)ois.readObject();
                //update product-user and product tables
                for(int i = 0; i<idList.size();i++){
                    insertProductUser(getUserId(login), idList.get(i)+"");
                }
                //get sumSpent
                int spentSum = calculateSumSpent(idList);
                
                //get discount sum
                int disSum = getDiscountSum(calcDiscaunt(spentSum));
                
                //total sum
                int totalSum = spentSum - disSum;
                
                System.out.println("USER SUM: "+spentSum);
                System.out.println("DISCOUNT SUM: "+disSum);
                System.out.println("DISCOUNT SUM: "+totalSum);
                
                //create array buySums
                Integer []buySums = new Integer[3];
                buySums[0]=spentSum;
                buySums[1]=disSum;
                buySums[2]=totalSum;
                //transmite to client
                oos.writeObject(buySums);
                oos.flush();
                //write sum spent on database
                updatePurchase(Integer.parseInt(getUserId(login)),spentSum);
                
                //update products table
                for(int i = 0; i<idList.size();i++){
                    updateProductId(idList.get(i));
                }
                
                sMenu(login);
            }
        } catch (Exception ex) {}
        
        
    }
    
    private void updateProductId(int p_id){
        try {
            Connection con = db.getConnection();
            Statement statement;
            
            String query1 ="SELECT available FROM products WHERE p_id = "+p_id+";";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query1);
            rs.next();
            int available = rs.getInt("available")-1;
            System.out.println("av:"+available);
            String query2 ="UPDATE products SET  `available` =  '"+available+"' WHERE `p_id` ="+p_id+";";
            statement = con.createStatement();
            statement.executeUpdate(query2);

            
        } catch (SQLException ex) {
            System.err.println(ex);
        } 
            
    }
    
    private void updatePurchase(int user_id, int sum_spent){
        try {
            Connection con = db.getConnection();
            Statement statement;
            
            String query ="INSERT INTO  purchase (\n" +
                "`user_id` ,\n" +
                "`sum_spent`\n" +
                ")\n" +
                "VALUES (\n" +
                "'"+user_id+"',  '"+sum_spent+"'\n" +
                ");";
            statement = con.createStatement();
            statement.executeUpdate(query);

            
        } catch (SQLException ex) {
            System.err.println(ex);
        } 
            
    }
    

    private int getDiscountSum(Integer sum){
        
        try {
            Connection con = db.getConnection();
            Statement statement;
            
            String query ="SELECT discount_sum FROM discounts WHERE amount_sum = "+sum+";";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            int discountSum = rs.getInt("discount_sum");
            return discountSum;
            
        } catch (SQLException ex) {
            return 0;
        } 
            
    }
    
    private int calcDiscaunt(int mySum){
        try {
            
            Connection con = db.getConnection();
            Statement statement;
            List<Integer> discountList = new ArrayList<Integer>();
            String query1 ="SELECT amount_sum FROM discounts;";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query1);
            
            while(rs.next()){
                discountList.add(rs.getInt("amount_sum"));
            }
            
            for(int i=1;i<discountList.size()+1;i++){
                if(i!=discountList.size()){
                    if((discountList.get(i-1) <= mySum)&& (mySum < discountList.get(i))){
                        return discountList.get(i-1);
                    } else if(discountList.get(i) > mySum){
                        break;
                    }
                }else if(discountList.size()==i){
                    if(discountList.get(i-1) <= mySum){
                        return discountList.get(i-1);
                    }
                }
            }
            
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    
    private int calculateSumSpent(List idList){
        
        try {
            Connection con = db.getConnection();
            Statement statement;
            
            int sumSpent = 0;
            for(int i =0;i<idList.size();i++){
                String query ="SELECT price FROM products WHERE available != 0 AND p_id = "+idList.get(i)+";";
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                sumSpent = rs.getInt("price")+sumSpent;
                
            }
           
                
            return sumSpent;
            
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return 0;
        } 
            
    }
    
    private void insertSumSpent(String user_id, String sum){
        try {
            Connection con = db.getConnection();
            statement = con.createStatement();
            String query = "INSERT INTO  `purchase` (`user_id` ,`sum_spent`) \n" +
                    "VALUES (\n" +
                    " '"+user_id+"',  '"+sum+"');";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
            
    }
    
    private void insertProductUser(String user_id, String p_id){
        try {
            Connection con = db.getConnection();
            statement = con.createStatement();
            String query = "INSERT INTO  `product-user` (`user_id` ,`p_id`) \n" +
                    "VALUES (\n" +
                    " '"+user_id+"',  '"+p_id+"');";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
            
    }
    
    private String getUserId(String login){
        try{
            String query ="SELECT user_id FROM users WHERE login ='"+login+"';";
            Connection con = db.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String userId = rs.getString("user_id");
            return userId;
            
            
        }catch(Exception e){
            System.err.println("getName Exception: "+e);
            return "";
        }
    }
    
    private void getProductsToID(String id){
        try{
            
            
            Connection con = db.getConnection();
            
            String query ="SELECT * FROM products WHERE available != 0 AND p_id = "+id+";";
            
            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            this.pData = new ArrayList<String[]>();
            
            while(rs.next()){
                this.pData.add(new String[] {rs.getString("p_id"),rs.getString("product_name"),rs.getString("brand"),rs.getString("model"),rs.getString("quality"),rs.getString("available"),rs.getString("price")});
                
            }
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private void getProductsToType(String type){
        try{
            Connection con = db.getConnection();
            String query ="SELECT * FROM products WHERE available != 0 AND product_name = '"+type+"';";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            this.pData = new ArrayList<String[]>();
            
            while(rs.next()){
                this.pData.add(new String[] {rs.getString("p_id"),rs.getString("product_name"),rs.getString("brand"),rs.getString("model"),rs.getString("quality"),rs.getString("available"),rs.getString("price")});
                
            }
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private void getTypessList(){
        try{
            Connection con = db.getConnection();
            String query ="SELECT product_name FROM products WHERE available != 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            List<String> namesAll= new ArrayList<>();
            while(rs.next()){
                namesAll.add(rs.getString("product_name"));
            }
            
            this.types = new ArrayList<String>(new HashSet<String>(namesAll));
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private void getProductsToQuality(String quality){
        try{
            Connection con = db.getConnection();
            String query ="SELECT * FROM products WHERE available != 0 AND quality = '"+quality+"';";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            this.pData = new ArrayList<String[]>();
            
            while(rs.next()){
                this.pData.add(new String[] {rs.getString("p_id"),rs.getString("product_name"),rs.getString("brand"),rs.getString("model"),rs.getString("quality"),rs.getString("available"),rs.getString("price")});
                
            }
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private void getProductsToBrand(String brand){
        try{
            Connection con = db.getConnection();
            String query ="SELECT * FROM products WHERE available != 0 AND brand = '"+brand+"';";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            this.pData = new ArrayList<String[]>();
            
            while(rs.next()){
                this.pData.add(new String[] {rs.getString("p_id"),rs.getString("product_name"),rs.getString("brand"),rs.getString("model"),rs.getString("quality"),rs.getString("available"),rs.getString("price")});
                
            }
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    private void getBrandsList(){
        try{
            Connection con = db.getConnection();
            String query ="SELECT brand FROM products WHERE available != 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            List<String> brandAll= new ArrayList<>();
            while(rs.next()){
                brandAll.add(rs.getString("brand"));
            }
            
            this.brands = new ArrayList<String>(new HashSet<String>(brandAll));
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private void getAllProducts(){
        try{
            Connection con = db.getConnection();
            String query ="SELECT * FROM products WHERE available != 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            this.pData = new ArrayList<String[]>();
            
            while(rs.next()){
                this.pData.add(new String[] {rs.getString("p_id"),rs.getString("product_name"),rs.getString("brand"),rs.getString("model"),rs.getString("quality"),rs.getString("available"),rs.getString("price")});
                
            }
            
        }catch(Exception e){
            System.err.println("getUsers Exception: "+e);
        }
    }
    
    private boolean authorization(String login, String password){
        try{
            String verification ="SELECT user_id FROM users WHERE login='"+login+"' AND password='"+password+"'";
            Connection con = db.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(verification);
            
            if(rs.last()){
                System.out.println("Client "+login+": AUTHORIZED");
                return true;
            }else{
                System.out.println("authificated failed");
                return false;
            }
        }catch(Exception e){
            System.err.println("user Login Exception: "+e);
            return false;
        }
    }
    
    private String sayHello(String login){
        try{
            String query ="SELECT * FROM users WHERE login ='"+login+"';";
            Connection con = db.getConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String name = rs.getString("name_user")+" "+rs.getString("last_name");
            return "Hello "+name;
            
            
        }catch(Exception e){
            System.err.println("getName Exception: "+e);
            return "Can not get a name.";
        }
    }
}
