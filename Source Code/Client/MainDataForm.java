package ClientEditor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainDataForm extends javax.swing.JFrame {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerData sData;
    private double sum=0;
    
    private List<String[]> pData;
    private List<String> brands,types;
    
    private List<Integer> cart = new ArrayList<Integer>();
    
    //menu client-server
    //if menu =
    // 1 - view all products
    // 2 - get Brands
    // 3 - transfer brands ang get products to brand
    // 4 - transfer qualities and get products to brand
    // 5 - get Names
    // 6 - transfer type get products to type
    // 7 - exit session
    
    private int menu = 0;
    
    public MainDataForm(ServerData sData) {
        initComponents();
        setVisible(true);
        //***********************************************************************************
        this.oos = sData.getOOS();
        this.ois = sData.getOIS();
        this.sData = sData;
        
        String hello; 
        try {
            hello = (String)ois.readObject();
        } catch (Exception e) {
            hello = "error";
        }
        
        if(!hello.equals("error")){
            JOptionPane.showMessageDialog(null, hello
                ,"Title", JOptionPane.INFORMATION_MESSAGE);
        }
        helloLabel.setText(hello);
        //************************************************************************************
        
        view.setSelectedIndex(0);
        
        qualityComboBox.addItem("Good");
        qualityComboBox.addItem("Bad");
        qualityComboBox.addItem("Normal");
        
        checkFromCart();
        storeTable.getColumnModel().getColumn(0).setPreferredWidth(8);
        storeTable.getColumnModel().getColumn(5).setPreferredWidth(15);
        storeTable.getColumnModel().getColumn(6).setPreferredWidth(15);
        buyButton.setVisible(false);
        
        
    }
    private void checkFromCart(){
        DefaultTableModel tm = (DefaultTableModel) storeTable.getModel();
        
         if(!cart.isEmpty()){
             int id,dId;
             for(int j=0;j<cart.size();j++){
                 id = cart.get(j);
                 for(int i=0;i<tm.getRowCount();i++){
                     dId=(Integer)tm.getValueAt(i, 0);                     
                     if(id == dId){
                         tm.setValueAt(new Boolean(true), i, 7);
                     }
                 }                 
             }
         }
    }
    
    
    
    private void setProducts(){
        try {
            pData = (List)ois.readObject();
            
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        } 
        
        //get table model
        DefaultTableModel tm = (DefaultTableModel) storeTable.getModel();
        tm.setRowCount(0);
        
        
        //display on table and console
        
        for(int i=0;i<pData.size();i++){
            tm.addRow(new Object[] {new Integer(Integer.parseInt(pData.get(i)[0])),new String(pData.get(i)[1]),new String(pData.get(i)[2]),new String(pData.get(i)[3]),new String(pData.get(i)[4]),new Integer(Integer.parseInt(pData.get(i)[5])),new Double(Double.parseDouble(pData.get(i)[6])),new Boolean(false), new Integer(1)});
            System.out.println(pData.get(i)[0]+" "+ pData.get(i)[1]+" "+pData.get(i)[2]+" "+pData.get(i)[3]+" "+pData.get(i)[4]+" "+pData.get(i)[5]+" "+pData.get(i)[6]);
        }
        System.out.println("");
        checkFromCart();
        
    }
    
    private void setTypes(){
        try {
            types = (List)ois.readObject();
            
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        }
        //Set menu
        typesComboBox.removeAllItems();
        for(int i=0;i<types.size();i++){
            typesComboBox.addItem(types.get(i));
        }
    }
    

    
    private void setBrands(){
        try {
            brands = (List)ois.readObject();
            
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        }
        //Set menu
        brandComboBox.removeAllItems();
        for(int i=0;i<brands.size();i++){
            brandComboBox.addItem(brands.get(i));
        }
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
        tableScrol = new javax.swing.JScrollPane();
        storeTable = new javax.swing.JTable();
        helloLabel = new javax.swing.JLabel();
        labelDisplay = new javax.swing.JLabel();
        displaingSroll = new javax.swing.JScrollPane();
        view = new javax.swing.JList();
        typeSelectBox = new javax.swing.JPanel();
        typesComboBox = new javax.swing.JComboBox();
        typesSelectButton = new javax.swing.JToggleButton();
        brandSelectBox = new javax.swing.JPanel();
        brandComboBox = new javax.swing.JComboBox();
        brandSelectButton = new javax.swing.JToggleButton();
        addButton = new javax.swing.JToggleButton();
        qualitySelectBox = new javax.swing.JPanel();
        qualityComboBox = new javax.swing.JComboBox();
        qualitySelectButton = new javax.swing.JToggleButton();
        myCartButton1 = new javax.swing.JButton();
        buyButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        programjMenu = new javax.swing.JMenu();
        optionsMenu = new javax.swing.JMenuItem();
        logOutMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        ViewjMenu = new javax.swing.JMenu();
        jMenu = new javax.swing.JMenu();

        jLabel1.setText("Hello, Ivan Ivanov");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        storeTable.setBorder(new javax.swing.border.MatteBorder(null));
        storeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Brand", "Model", "Quality", "Available", "Price", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        storeTable.setAutoscrolls(false);
        storeTable.setRequestFocusEnabled(false);
        tableScrol.setViewportView(storeTable);

        helloLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        helloLabel.setText("Hello, Iavan Ivanov!");

        labelDisplay.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        labelDisplay.setText("Display:");

        view.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "All", "Brand", "Quality", "Type" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        view.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                viewValueChanged(evt);
            }
        });
        displaingSroll.setViewportView(view);

        typeSelectBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Type"));

        typesSelectButton.setText("Find");
        typesSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typesSelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout typeSelectBoxLayout = new javax.swing.GroupLayout(typeSelectBox);
        typeSelectBox.setLayout(typeSelectBoxLayout);
        typeSelectBoxLayout.setHorizontalGroup(
            typeSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typeSelectBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(typeSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typesComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typesSelectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        typeSelectBoxLayout.setVerticalGroup(
            typeSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typeSelectBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(typesSelectButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        brandSelectBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Brand"));

        brandSelectButton.setText("Find");
        brandSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brandSelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout brandSelectBoxLayout = new javax.swing.GroupLayout(brandSelectBox);
        brandSelectBox.setLayout(brandSelectBoxLayout);
        brandSelectBoxLayout.setHorizontalGroup(
            brandSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandSelectBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(brandSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brandComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandSelectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        brandSelectBoxLayout.setVerticalGroup(
            brandSelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandSelectBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(brandSelectButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        addButton.setText("update card");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        qualitySelectBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Quality"));

        qualitySelectButton.setText("Find");
        qualitySelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualitySelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout qualitySelectBoxLayout = new javax.swing.GroupLayout(qualitySelectBox);
        qualitySelectBox.setLayout(qualitySelectBoxLayout);
        qualitySelectBoxLayout.setHorizontalGroup(
            qualitySelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qualitySelectBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(qualitySelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qualityComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qualitySelectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        qualitySelectBoxLayout.setVerticalGroup(
            qualitySelectBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qualitySelectBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qualityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(qualitySelectButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        myCartButton1.setText("my cart");
        myCartButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myCartButton1ActionPerformed(evt);
            }
        });

        buyButton.setText("bye");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        programjMenu.setText("Program");

        optionsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        optionsMenu.setText("Options");
        optionsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsMenuActionPerformed(evt);
            }
        });
        programjMenu.add(optionsMenu);

        logOutMenu.setText("LogOut");
        logOutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutMenuActionPerformed(evt);
            }
        });
        programjMenu.add(logOutMenu);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        programjMenu.add(exitMenu);

        jMenuBar1.add(programjMenu);

        ViewjMenu.setText("View");
        jMenuBar1.add(ViewjMenu);

        jMenu.setText("About");
        jMenuBar1.add(jMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(myCartButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tableScrol, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qualitySelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDisplay)
                    .addComponent(displaingSroll, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typeSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(helloLabel)
                        .addGap(18, 18, 18)
                        .addComponent(labelDisplay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(displaingSroll, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qualitySelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brandSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addComponent(tableScrol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myCartButton1)
                        .addComponent(buyButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_viewValueChanged
        // TODO add your handling code here:
        buyButton.setVisible(false);
        int select = new Integer(view.getSelectedIndex());
        if(select == 0){
            try {
                //all
                brandSelectBox.setVisible(false);
                qualitySelectBox.setVisible(false);
                typeSelectBox.setVisible(false);
                
                oos.writeObject(new Integer(menu=1));
                oos.flush();
                
                setProducts();
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        if(select == 1){
            //brand
            brandSelectBox.setVisible(true);
            qualitySelectBox.setVisible(false);
            typeSelectBox.setVisible(false);
            
            try {
                
                oos.writeObject(new Integer(menu=2));
                oos.flush();
                
                setBrands();
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        if(select == 2){
            //quality
            brandSelectBox.setVisible(false);
            qualitySelectBox.setVisible(true);
            typeSelectBox.setVisible(false);
            try {
                
                oos.writeObject(new Integer(menu=4));
                oos.flush();
                oos.writeObject(new String((String)qualityComboBox.getSelectedItem()));
                oos.flush();
                
                setProducts();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        if(select == 3){
            //type
            brandSelectBox.setVisible(false);
            qualitySelectBox.setVisible(false);
            typeSelectBox.setVisible(true);
            try {
                
                oos.writeObject(new Integer(menu=5));
                oos.flush();
                
                setTypes();
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_viewValueChanged

    private void brandSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brandSelectButtonActionPerformed
        buyButton.setVisible(false);
        try {
            // TODO add your handling code here:
            oos.writeObject(new Integer(menu=3));
            oos.flush();
            oos.writeObject((String)brandComboBox.getSelectedItem());
            oos.flush();
            System.out.println("GET PRODUCTS TO BRAND = "+(String)brandComboBox.getSelectedItem());
            setProducts();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_brandSelectButtonActionPerformed

    private void qualitySelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qualitySelectButtonActionPerformed
        // TODO add your handling code here:
        buyButton.setVisible(false);
        try {
            oos.writeObject(new Integer(menu=4));
            oos.flush();
            oos.writeObject((String)qualityComboBox.getSelectedItem());
            oos.flush();
            System.out.println("GET PRODUCTS TO Quality = "+(String)qualityComboBox.getSelectedItem());
            setProducts();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_qualitySelectButtonActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want realy exit?", "title",dialogButton);
        if(dialogResult==0){
            System.out.println("By");
            try {
                oos.writeObject(new Integer(menu=7));
            } catch (IOException ex) {}
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuActionPerformed

    private void logOutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutMenuActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want realy exit this account?", "title",dialogButton);
        if(dialogResult==0){
            setVisible(false);
            try {
                oos.writeObject(new Integer(menu=7));
            } catch (Exception ex){}
            
            new AuhorizationForm();
        }
        
    }//GEN-LAST:event_logOutMenuActionPerformed

    private void optionsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsMenuActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "After changing of settings restart this program!"
                ,"Title", JOptionPane.WARNING_MESSAGE);
        new ConfigForm();
        
    }//GEN-LAST:event_optionsMenuActionPerformed

    private void typesSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typesSelectButtonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            System.out.println("GET PRODUCTS TO BRAND = "+(String)typesComboBox.getSelectedItem());
            oos.writeObject(new Integer(menu=6));
            oos.writeObject((String)typesComboBox.getSelectedItem());
            setProducts();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Error"
                ,"Title", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_typesSelectButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        buyButton.setVisible(false);
        DefaultTableModel tm = (DefaultTableModel) storeTable.getModel();
        
    	for(int i = 0;i<tm.getRowCount();i++){
            
    		if((Boolean)tm.getValueAt(i, 7).equals(true)){
                    cart.add((Integer)tm.getValueAt(i, 0));
                   
                }else if((Boolean)tm.getValueAt(i, 7).equals(false)){
                    cart.remove((Integer)tm.getValueAt(i, 0));
                }	
    	}
        
        
    	if(cart.size()==0){
    		JOptionPane.showMessageDialog(this, "Nothing selected =/ ."
                    ,"Error", JOptionPane.WARNING_MESSAGE);
    	}else{
    		JOptionPane.showMessageDialog(this, "Information updated succesful."
	                ,"Title", JOptionPane.PLAIN_MESSAGE);
    	}
    }//GEN-LAST:event_addButtonActionPerformed

    private void myCartButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myCartButton1ActionPerformed
        // TODO add your handling code here:
        buyButton.setVisible(true);
        DefaultTableModel tm = (DefaultTableModel) storeTable.getModel();
        tm.setRowCount(0);
        
        
        //Converting ArrayList to HashSet to remove duplicates
        LinkedHashSet<Integer> listToSet = new LinkedHashSet<Integer>(cart);
      
        //Creating Arraylist without duplicate values
        cart = new ArrayList<Integer>(listToSet);
        try {
            for(int i = 0;i<cart.size();i++){
                System.out.println("GET PRODUCTS TO ID = "+cart.get(i));
            }
            oos.writeObject(new Integer(menu=8));
            oos.writeObject(cart);
            setProducts();
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Error"
                    ,"Title", JOptionPane.ERROR_MESSAGE);
        }
        
       
        
    }//GEN-LAST:event_myCartButton1ActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        // TODO add your handling code here:
        if(!pData.isEmpty()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want buy this products?", "title",dialogButton);
            if(dialogResult==0){
                try {
                    oos.writeObject(new Integer(menu=9));
                    oos.flush();
                    
                    for(int i = 0;i<cart.size();i++){
                        System.out.println("BUY PRODUCTS TO ID = "+cart.get(i)+";");
                    }
                    
                    oos.writeObject(cart);
                    oos.flush();
                    
                    
                    
                    
                    //geting info
                    Integer []buySums;
                    buySums = (Integer[])ois.readObject();
                        
                        
                    JOptionPane.showMessageDialog(null, "Thanks... You sum is: "+buySums[0]+"; You discount is "+buySums[1]+"; Total sum: "+buySums[2]+"","Succes:", JOptionPane.INFORMATION_MESSAGE);
                    cart.clear();
                    
                } catch (Exception ex) {
                    System.err.println("GETPRODUCTEXEPTION "+ex);
                }
                
            }
        }
    }//GEN-LAST:event_buyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ViewjMenu;
    private javax.swing.JToggleButton addButton;
    private javax.swing.JComboBox brandComboBox;
    private javax.swing.JPanel brandSelectBox;
    private javax.swing.JToggleButton brandSelectButton;
    private javax.swing.JButton buyButton;
    private javax.swing.JScrollPane displaingSroll;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JLabel helloLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDisplay;
    private javax.swing.JMenuItem logOutMenu;
    private javax.swing.JButton myCartButton1;
    private javax.swing.JMenuItem optionsMenu;
    private javax.swing.JMenu programjMenu;
    private javax.swing.JComboBox qualityComboBox;
    private javax.swing.JPanel qualitySelectBox;
    private javax.swing.JToggleButton qualitySelectButton;
    private javax.swing.JTable storeTable;
    private javax.swing.JScrollPane tableScrol;
    private javax.swing.JPanel typeSelectBox;
    private javax.swing.JComboBox typesComboBox;
    private javax.swing.JToggleButton typesSelectButton;
    private javax.swing.JList view;
    // End of variables declaration//GEN-END:variables
}
