/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Display;

import Main.Game;
import geometrywars.GameStates.GameStateManager;
import geometrywars.GameStates.Menus.MainMenu;
import geometrywars.scaling.Scale;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

/**
 *
 * @author Olivier
 */
public class SettingsForm extends javax.swing.JDialog {

    private GameStateManager gsm;
    private Window gameWindow;
    private Frame gameFrame;
    private int width, height, fullWidth, fullHeight;
    
    private Scale scaler;

    public SettingsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupFullscreen = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radiobtnOn = new javax.swing.JRadioButton();
        radiobtnOff = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        cboResolutions = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Fullscreen");

        btnGroupFullscreen.add(radiobtnOn);
        radiobtnOn.setText("On");

        btnGroupFullscreen.add(radiobtnOff);
        radiobtnOff.setSelected(true);
        radiobtnOff.setText("Off");

        jLabel2.setText("Screen resolution");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radiobtnOn)
                        .addGap(18, 18, 18)
                        .addComponent(radiobtnOff))
                    .addComponent(cboResolutions, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radiobtnOn)
                    .addComponent(radiobtnOff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cboResolutions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Video", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sound", jPanel2);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        gameWindow = gsm.getWindow();
        gameFrame = gameWindow.getFrame();
        fillInResolutions();
        centerDialog();
    }//GEN-LAST:event_formWindowOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        gameFrame.dispose();
        if(radiobtnOn.isSelected()){
            SetFullscreen();
        }
        if(radiobtnOff.isSelected()){
            setNormalMode(); 
        }
        centerDialog();
        gameFrame.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupFullscreen;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboResolutions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton radiobtnOff;
    private javax.swing.JRadioButton radiobtnOn;
    // End of variables declaration//GEN-END:variables

    private void fillInResolutions() {
        String resolutions[] = {"800x600", "1024x768", "1280x720", "1280x800", "1400x1050", "1600x900", "1920x1080"};
        cboResolutions.removeAllItems();
        for (String resolution : resolutions) {
            cboResolutions.addItem(resolution);
        }
        cboResolutions.setSelectedItem(resolutions[6]);
    }

    public void setGsm(GameStateManager gsm) {
        this.gsm = gsm;
    }

    private void SetFullscreen() {
        gameFrame.setUndecorated(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        fullWidth = (int) tk.getScreenSize().getWidth();
        fullHeight = (int) tk.getScreenSize().getHeight();
        setDimensions(fullWidth, fullHeight);
        
    }

    private void setDimensions(int width, int height) {
        gameWindow.setWidth(width);
        gameWindow.setHeight(height);
        gameFrame.setSize(new Dimension(width, height));
        scaler = Game.scale;
        scaler.calculateWidthScale();
        scaler.calculateHeightScale();
        Game.widthScaler = scaler.getWidthScale();
        Game.heightScaler = scaler.getHeightScale();
        gsm.setState(new MainMenu(gsm));
    }

    private void setNormalMode() {       
        gameFrame.setUndecorated(false);
        changeResolution();
        
    }

    private void changeResolution() {
        if (cboResolutions.getSelectedIndex() > -1) {
            String selectedItem = cboResolutions.getSelectedItem().toString();
            String[] splittedItem = selectedItem.split("x");
            width = Integer.parseInt(splittedItem[0]);
            height = Integer.parseInt(splittedItem[1]);
        }
        setDimensions(width, height);
    }
    
    private void centerDialog(){
        this.setLocationRelativeTo(gameFrame);
    }
}
