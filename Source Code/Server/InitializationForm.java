/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servereditor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kalpak44
 */
public class InitializationForm extends javax.swing.JFrame {

    /**InitializationForm(int status)
     * 
     * status 1 - create tables and insert  demo data on database
     * status 2 - only create tables on database
     * 
     */
    
    private DataBase db;
    private Connection con;
    private Statement statement;
    
    public InitializationForm(int status) {
        if(status == 2){
            initComponents();

            tableInit();
            new MainForm().setVisible(true);

            
        }
        if(status == 1){
            
            try {
                
                initComponents();

                tableInit();
                insertDemoUser();
                insertDemoProducts();
                insertDemoDiscounts();
                
                new Propierties().setConfigSuccessfull();
                
                new MainForm().setVisible(true);
            } catch (Exception ex) {
                System.err.println(ex);
            }
            
        }
        
    }
    private void insertDemoDiscounts(){
        try {
            db = new DataBase();


            con = db.getConnection();
            statement = con.createStatement();
            
            String query1 = "INSERT INTO  `discounts` (`amount_sum`, `discount_sum`) \n" +
                "VALUES (\n" +
                " '1000',  '78');";
            statement.executeUpdate(query1);
            jProgressBar1.setValue(1);
            

            con = db.getConnection();
            statement = con.createStatement();
            
            String query2 = "INSERT INTO  `discounts` (`amount_sum`, `discount_sum`) \n" +
                "VALUES (\n" +
                " '2000',  '150');";
            statement.executeUpdate(query2);
            con.close();
            
        } catch (Exception ex) {
            Logger.getLogger(InitializationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    private void insertDemoProducts(){
        try {
            db = new DataBase();
            Random randomNumbers;
            int n=0;
            
            for(int i=0;i<=20;i++){
                con = db.getConnection();
                statement = con.createStatement();
                randomNumbers = new Random();
                int number = 190 + randomNumbers.nextInt( 300 - 200 + 1 );
                int av = 20 + randomNumbers.nextInt( 50 - 20 + 1 );
                String quality;
                if(av%2==0){
                    quality="Good";
                }else{
                    quality="Normal";
                }
                
                String query = "INSERT INTO  `products` (`product_name` ,  `brand` ,  `model` ,  `quality` ,  `price` ,  `available` ) \n" +
                    "VALUES (\n" +
                    "'Monitor',  'Samsung',  'Md-d"+i+"-4df',  '"+quality+"',  '"+number+"',  '"+av+"'\n" +
                    ");";
                statement.executeUpdate(query);

                
            }
            
            for(int i=0;i<=10;i++){
                con = db.getConnection();
                statement = con.createStatement();
                randomNumbers = new Random();
                int number = 100 + randomNumbers.nextInt( 200 - 150 + 1 );
                int av = 20 + randomNumbers.nextInt( 50 - 20 + 1 );
                String quality;
                if(av%2==0){
                    quality="Good";
                }else{
                    quality="Normal";
                }
                
                String query = "INSERT INTO  `products` (`product_name` ,  `brand` ,  `model` ,  `quality` ,  `price` ,  `available` ) \n" +
                    "VALUES (\n" +
                    "'Printer',  'Samsung',  'Md-dow-a"+i+"',  '"+quality+"',  '"+number+"',  '"+av+"'\n" +
                    ");";
                statement.executeUpdate(query);
                
            }
            
            for(int i=0;i<=18;i++){
                con = db.getConnection();
                statement = con.createStatement();
                randomNumbers = new Random();
                int number = 100 + randomNumbers.nextInt( 200 - 150 + 1 );
                int av = 20 + randomNumbers.nextInt( 50 - 20 + 1 );
                String quality;
                if(av%2==0){
                    quality="Good";
                }else{
                    quality="Normal";
                }
                
                String query = "INSERT INTO  `products` (`product_name` ,  `brand` ,  `model` ,  `quality` ,  `price` ,  `available` ) \n" +
                    "VALUES (\n" +
                    "'Printer',  'LG',  'b-"+i+"',  '"+quality+"',  '"+number+"',  '"+av+"'\n" +
                    ");";
                statement.executeUpdate(query);
                n++;
                
            }
            
            for(int i=0;i<=22;i++){
                con = db.getConnection();
                statement = con.createStatement();
                randomNumbers = new Random();
                int number = 100 + randomNumbers.nextInt( 200 - 150 + 1 );
                int av = 20 + randomNumbers.nextInt( 50 - 20 + 1 );
                String quality;
                if(av%2==0){
                    quality="Good";
                }else{
                    quality="Normal";
                }
                
                String query = "INSERT INTO  `products` (`product_name` ,  `brand` ,  `model` ,  `quality` ,  `price` ,  `available` ) \n" +
                    "VALUES (\n" +
                    "'NoteBook',  'Samsung',  'bm-"+i+"20',  '"+quality+"',  '"+number+"',  '"+av+"'\n" +
                    ");";
                statement.executeUpdate(query);

                n++;
                
            }

            con.close();
            
            
        } catch (Exception ex) {
            Logger.getLogger(InitializationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertDemoUser(){
        try {
            db = new DataBase();
            jLabel1.setText("User Creating status:");
            con = db.getConnection();
            statement = con.createStatement();
            
            String query = "INSERT INTO  `users` ( `login` ,  `password` ,  `name_user` ,  `last_name` ) \n" +
                "VALUES (\n" +
                " 'user',  '123',  'Ivan',  'Ivanov'\n" +
                ");";
            statement.executeUpdate(query);
            jProgressBar1.setValue(2);
            con.close();
            
        } catch (Exception ex) {
            Logger.getLogger(InitializationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tableInit(){
        
        try {
                db = new DataBase();
                jLabel1.setText("Tables Creating status:");
                con = db.getConnection();
                statement = con.createStatement();
                
                statement.executeUpdate("DROP TABLE IF EXISTS `users` ;");            
                String strUserTb = "CREATE TABLE `users` (\n" +
                        "`user_id` INT( 10 ) NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
                        "`login` VARCHAR( 25 ) NULL ,\n" +
                        "`password` VARCHAR( 35 ) NULL ,\n" +
                        "`name_user` VARCHAR( 35 ) NULL ,\n" +
                        "`last_name` VARCHAR( 35 ) NULL\n" +
                        ") ENGINE = InnoDB;";
                statement.executeUpdate(strUserTb);
                

                //create table products
                statement.executeUpdate("DROP TABLE IF EXISTS `products` ;");
                String strProductTb = "CREATE TABLE `products` (\n" +
                        "`p_id` INT( 10 ) NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
                        "`product_name` VARCHAR( 30 ) NULL ,\n" +
                        "`brand` VARCHAR( 30 ) NULL ,\n" +
                        "`model` VARCHAR( 35 ) NULL ,\n" +
                        "`quality` VARCHAR( 35 ) NULL ,\n" +
                        "`available` INT( 10 ) NULL,\n" +
                        "`price` INT( 10 ) NULL" +
                        ") ENGINE = InnoDB;";
                statement.executeUpdate(strProductTb);
                


                //create table product-user
                statement.executeUpdate("DROP TABLE IF EXISTS `product-user` ;");
                String strProductUserTb = "CREATE TABLE `product-user` (\n" +
                        "`user_id` INT( 10 ) NULL, \n" +
                        "`p_id` INT( 10 ) NULL \n" +
                        ") ENGINE = InnoDB;";
                statement.executeUpdate(strProductUserTb);
                


                //create table purchase
                statement.executeUpdate("DROP TABLE IF EXISTS `purchase` ;");
                String strPurchaseTb = "CREATE TABLE `purchase` (\n" +
                        "`user_id` INT( 10 ) NULL,\n" +
                        "`sum_spent` INT( 10 ) NULL\n" +
                        ") ENGINE = InnoDB;";
                statement.executeUpdate(strPurchaseTb);


                //create table discounts
                statement.executeUpdate("DROP TABLE IF EXISTS `discounts` ;");
                String strDiscountsTb = "CREATE TABLE `discounts` (\n" +
                        "`dis_id` INT( 10 ) NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
                        "`amount_sum` INT( 10 ) NULL,\n" +
                        "`discount_sum` INT( 10 ) NULL\n" +
                        ") ENGINE = InnoDB;";
                statement.executeUpdate(strDiscountsTb);
                

                con.close();
                

                
            } catch (Exception e) {}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel1.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
