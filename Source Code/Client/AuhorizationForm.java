/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientEditor;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AuhorizationForm extends javax.swing.JFrame {
    String[] aData = new String[2];
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    
    public AuhorizationForm() {
        initComponents();
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        PasswordLabel = new javax.swing.JLabel();
        passwordPasswordField = new javax.swing.JPasswordField();
        authorizationButton = new javax.swing.JToggleButton();
        authjMenuBar = new javax.swing.JMenuBar();
        filejMenu = new javax.swing.JMenu();
        connectionsjMenuItem = new javax.swing.JMenuItem();
        exitjMenuItem = new javax.swing.JMenuItem();
        aboutjMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Authorization");
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);
        setIconImages(null);
        setLocationByPlatform(true);
        setName("authFrame"); // NOI18N
        setResizable(false);

        loginLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        loginLabel.setText("Login:");

        loginTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PasswordLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        PasswordLabel.setText("Password:");

        passwordPasswordField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        authorizationButton.setText("Log in");
        authorizationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorizationButtonActionPerformed(evt);
            }
        });

        filejMenu.setText("File");

        connectionsjMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        connectionsjMenuItem.setText("Options");
        connectionsjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionsjMenuItemActionPerformed(evt);
            }
        });
        filejMenu.add(connectionsjMenuItem);

        exitjMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitjMenuItem.setText("Exit");
        exitjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitjMenuItemActionPerformed(evt);
            }
        });
        filejMenu.add(exitjMenuItem);

        authjMenuBar.add(filejMenu);

        aboutjMenu.setText("About");
        authjMenuBar.add(aboutjMenu);

        setJMenuBar(authjMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordPasswordField)
                    .addComponent(loginTextField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 215, Short.MAX_VALUE)
                        .addComponent(authorizationButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordLabel)
                            .addComponent(loginLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(authorizationButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitjMenuItemActionPerformed
        // TODO add your handling code here:
        System.out.println("Bye");
        System.exit(0);
    }//GEN-LAST:event_exitjMenuItemActionPerformed

    private void connectionsjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionsjMenuItemActionPerformed
        // TODO add your handling code here:
        new ConfigForm();
    }//GEN-LAST:event_connectionsjMenuItemActionPerformed

    private void authorizationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorizationButtonActionPerformed
        // TODO add your handling code here:
        Propierties pr = new Propierties();
        
        
        ServerData sData = new ServerData();
        
        try{
            socket = new Socket(pr.getHOST(),pr.getPORT());
            sData.setSocket(socket);
            
            //setVisible(false);
            aData[0]=loginTextField.getText();
            aData[1]=passwordPasswordField.getText();
            
            System.out.println("SEND TO SERVER:");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            
            sData.setOIS(ois);
            sData.setOOS(oos);
            
            System.out.println("Login: "+aData[0] + "\nPassword: *****");
            oos.writeObject(aData);
            oos.flush();
            
            if((Boolean)ois.readObject()){
                System.out.println("\nAuthorization: SUCCES");
                setVisible(false);
                new MainDataForm(sData);
            }else{
                System.out.println("\nAuthorization: FAILED");
                JOptionPane.showMessageDialog(null, "Check input data."
                ,"Title", JOptionPane.WARNING_MESSAGE);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Server not found. Please, check your settings."
                ,"Title", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
    }//GEN-LAST:event_authorizationButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JMenu aboutjMenu;
    private javax.swing.JMenuBar authjMenuBar;
    private javax.swing.JToggleButton authorizationButton;
    private javax.swing.JMenuItem connectionsjMenuItem;
    private javax.swing.JMenuItem exitjMenuItem;
    private javax.swing.JMenu filejMenu;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JPasswordField passwordPasswordField;
    // End of variables declaration//GEN-END:variables
}
