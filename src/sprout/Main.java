package sprout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main extends javax.swing.JFrame {
    private components.FlaskGaugeComponent flaskGuageComponent1;
    public int currentLevel = 0;
    private int targetLevel = 0; // Initialize targetLevel here
    private Timer timer;
    private ImageIcon[] progressImages; // Array to hold the images for different progress levels
    private JLabel LevelLabel; // JLabel to display the progress images
    /**
     * Creates new form Main
     */
    public Main() {
    initComponents();
    progressImages = new ImageIcon[]{
        new ImageIcon(getClass().getResource("/images/image1.png")),
        new ImageIcon(getClass().getResource("/images/image2.png")),
        new ImageIcon(getClass().getResource("/images/image3.png")),
        new ImageIcon(getClass().getResource("/images/image4.png")),
        new ImageIcon(getClass().getResource("/images/image5.png"))
    };
    flaskGuageComponent1 = new components.FlaskGaugeComponent(); // Initialize the component first
    loadLevel(); // Load the level from file
    loadTargetLevel(); // Load the target level from file
    flaskGaugeComponent1.setTargetLevel(targetLevel); // Set the target level after it's loaded
    flaskGuageComponent1.setLevel(currentLevel);
    simulateButtonClick(); // Call the method to simulate button click
    // Add a window listener to save the target level when closing the application
    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            saveTargetLevel();
        }
    });
    updateRatioLabel();
    
    timer = new Timer();
    timer.scheduleAtFixedRate(new ResetTask(), getNextMidnight(), 24 * 60 * 60 * 1000);
}
    
    private void updateProgressImage(int currentLevel, int targetLevel) {
    // Check if currentLevel exceeds targetLevel
    if (currentLevel >= targetLevel) {
        // Display the last image in the array
        imageLevelLabel.setIcon(progressImages[progressImages.length - 1]);
    } else {
        // Calculate the progress ratio
        double progressRatio = (double) currentLevel / targetLevel;

        // Determine the index of the image based on the progress ratio
        int imageIndex = (int) (progressRatio * (progressImages.length - 1));

        // Update the image on the JLabel
        if (imageIndex >= 0 && imageIndex < progressImages.length) {
            imageLevelLabel.setIcon(progressImages[imageIndex]);
        } else {
            // Handle index out of bounds if needed
        }
    }
}
    
    private long getNextMidnight(){
    Calendar now = Calendar.getInstance();
    Calendar midnight = Calendar.getInstance();
    midnight.set(Calendar.HOUR_OF_DAY, 0);
    midnight.set(Calendar.MINUTE, 0);
    midnight.set(Calendar.SECOND, 0);
    midnight.set(Calendar.MILLISECOND, 0);
    
    if (midnight.before(now)){
            midnight.add(Calendar.DAY_OF_MONTH, 1);
        }
    return midnight.getTimeInMillis() - now.getTimeInMillis();
    }
    
    class ResetTask extends TimerTask {
        public void run() {
            // Reset the current level to 0
            currentLevel = 0;
            // Update the gauge or save the level to a file
            // flaskGaugeComponent1.setLevel(currentLevel);
            // saveLevel();
            System.out.println("Current level reset to 0 at midnight.");
            updateRatioLabel(); // Update the ratio label
        }
    }
            
    private void saveTargetLevel() {
    try {
        FileWriter writer = new FileWriter("targetLevel.txt");
        writer.write(Integer.toString(targetLevel));
        writer.close();
    } catch (IOException e) {
        System.err.println("Error: Unable to save target level");
    }
}

    private void loadTargetLevel() {
    try {
        File file = new File("targetLevel.txt");
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                targetLevel = scanner.nextInt();
            }
            scanner.close();
        }
    } catch (FileNotFoundException e) {
        System.err.println("Error: File not found.");
    }
}

    
    private void updateRatioLabel() {
    int targetLevel = flaskGaugeComponent1.getTargetLevel(); // Get the target level
    jLabelRatio.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 18)); // Set the font
    jLabelRatio.setText(currentLevel + "mL / " + targetLevel + "mL");
    updateProgressImage(currentLevel, targetLevel);
       }

    private void simulateButtonClick() {
    String text = "0"; // Simulate a button click with a value of 0
    updateRatioLabel(); // Update the ratio label
    try {
        int value = Integer.parseInt(text);
        currentLevel += value;
        updateProgressImage(currentLevel, targetLevel);
        flaskGaugeComponent1.setLevel(currentLevel);
    } catch (NumberFormatException e) {
        System.err.println("Error: Not a valid integer");
    }
}
     
    private void loadLevel(){
        try{
            File file = new File("level.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    currentLevel = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }
    }
    
    private void saveLevel() {
        try {
            FileWriter writer = new FileWriter("level.txt");
            writer.write(Integer.toString(currentLevel));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to save level");
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

        gradientPanel1 = new components.GradientPanel();
        jPanel1 = new javax.swing.JPanel();
        drinkButton1 = new components.DrinkButton();
        flaskGaugeComponent1 = new components.FlaskGaugeComponent();
        jLabelRatio = new javax.swing.JLabel();
        drinkButton3 = new components.DrinkButton();
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        drinkButton2 = new components.DrinkButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        imageLevelLabel = new javax.swing.JLabel();
        drinkButton4 = new components.DrinkButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        drinkButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        drinkButton1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        drinkButton1.setLabel("Drink!");
        drinkButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkButton1MouseClicked(evt);
            }
        });
        drinkButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinkButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(drinkButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, -1, -1));

        jLabelRatio.setText("jLabel1");
        jLabelRatio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelRatioPropertyChange(evt);
            }
        });

        drinkButton3.setText("Change Target");
        drinkButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinkButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(96, 153, 102));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jFormattedTextField2.setBackground(new java.awt.Color(0, 0, 0, 0));
        jFormattedTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter Amount Taken (mL)"));
        jFormattedTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jFormattedTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFormattedTextField2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });
        jFormattedTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyTyped(evt);
            }
        });
        jPanel2.add(jFormattedTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, -1));

        drinkButton2.setText("Reset");
        drinkButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinkButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout flaskGaugeComponent1Layout = new javax.swing.GroupLayout(flaskGaugeComponent1);
        flaskGaugeComponent1.setLayout(flaskGaugeComponent1Layout);
        flaskGaugeComponent1Layout.setHorizontalGroup(
            flaskGaugeComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flaskGaugeComponent1Layout.createSequentialGroup()
                .addGroup(flaskGaugeComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flaskGaugeComponent1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(flaskGaugeComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(drinkButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(flaskGaugeComponent1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drinkButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        flaskGaugeComponent1Layout.setVerticalGroup(
            flaskGaugeComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flaskGaugeComponent1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabelRatio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                .addGroup(flaskGaugeComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drinkButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drinkButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel1.add(flaskGaugeComponent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -30, 190, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 24)); // NOI18N
        jLabel2.setText("Your water intake for the day");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        imageLevelLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/image1.png"))); // NOI18N

        drinkButton4.setText("X");
        drinkButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinkButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                        .addComponent(drinkButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                        .addComponent(imageLevelLabel)
                        .addGap(107, 107, 107)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(drinkButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageLevelLabel)
                .addGap(86, 86, 86))
        );

        getContentPane().add(gradientPanel1);

        setSize(new java.awt.Dimension(800, 605));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jFormattedTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyTyped
        char c = evt.getKeyChar();

        if(!Character.isDigit(c)){
            evt.consume();
            }
    }//GEN-LAST:event_jFormattedTextField2KeyTyped

    private void drinkButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinkButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drinkButton1ActionPerformed

    private void drinkButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButton1MouseClicked
        String text = jFormattedTextField2.getText();
    try {
        int value = Integer.parseInt(text);
        // Add the parsed value to currentLevel
        currentLevel += value;
        // Update the gauge with the new level
        flaskGaugeComponent1.setLevel(currentLevel);
        // Save the current level to file
        saveLevel();
        // Now currentLevel holds the updated value
        System.out.println("Current Level: " + currentLevel); // Just for testing, you can replace this with your desired action
        
        // Check if the currentLevel exceeds or equals 20000 mL
        if (currentLevel >= 20000) {
            // Show a warning message dialog
            javax.swing.JOptionPane.showMessageDialog(this, "Warning: You have reached 20,000 mL of water intake.");
        }
    } catch (NumberFormatException e) {
        // Handle the case where the text is not a valid integer
        System.err.println("Error: Not a valid integer");
    }
    updateRatioLabel(); // Update the ratio label
    }//GEN-LAST:event_drinkButton1MouseClicked

    private void drinkButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinkButton2ActionPerformed
        currentLevel = 0; // Reset the current level to 0
        flaskGaugeComponent1.setLevel(currentLevel); // Update the gauge with the new level
        saveLevel(); // Save the new level to file 
        simulateButtonClick();
        updateRatioLabel(); // Update the ratio label
    }//GEN-LAST:event_drinkButton2ActionPerformed

    private void jLabelRatioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelRatioPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelRatioPropertyChange

    private void drinkButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinkButton3ActionPerformed
                                   
    // Create a dialog window
    javax.swing.JDialog dialog = new javax.swing.JDialog();
    dialog.setTitle("Enter Target mL");
    dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    // Add a JTextField to the dialog
    javax.swing.JTextField textField = new javax.swing.JTextField(10); // Specify the preferred width
    dialog.add(textField);

    // Add an "OK" button to the dialog
    javax.swing.JButton okButton = new javax.swing.JButton("OK");
    okButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            String text = textField.getText();
            try {
                int newValue = Integer.parseInt(text);
                targetLevel = newValue;
                flaskGaugeComponent1.setTargetLevel(targetLevel); // Update the gauge with the new target level
                dialog.dispose(); // Close the dialog
                updateRatioLabel(); // Update the ratio label
                saveTargetLevel();
            } catch (NumberFormatException e) {
                // Handle invalid input
                javax.swing.JOptionPane.showMessageDialog(dialog, "Please enter a valid integer.");
            }
        }
    });
    dialog.add(okButton);

    // Layout the components using a JPanel with FlowLayout
    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.add(textField);
    panel.add(okButton);
    dialog.add(panel);

    dialog.setSize(300, 100); // Set the size of the dialog
    dialog.setLocationRelativeTo(null); // Center the dialog on the screen
    dialog.setVisible(true); // Make the dialog visible
    }//GEN-LAST:event_drinkButton3ActionPerformed

    private void drinkButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinkButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_drinkButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.DrinkButton drinkButton1;
    private components.DrinkButton drinkButton2;
    private components.DrinkButton drinkButton3;
    private components.DrinkButton drinkButton4;
    private components.FlaskGaugeComponent flaskGaugeComponent1;
    private components.GradientPanel gradientPanel1;
    private javax.swing.JLabel imageLevelLabel;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelRatio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
