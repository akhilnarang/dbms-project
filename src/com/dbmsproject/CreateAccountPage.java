package com.dbmsproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author akhil
 */
public class CreateAccountPage extends javax.swing.JFrame {

    /**
     * Creates new form CreateAccountPage
     */
    public CreateAccountPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerAccountLabel = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        emailInput = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        registerAccountLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        registerAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerAccountLabel.setText("Register an Account");

        usernameLabel.setText("Enter username");

        passwordLabel.setText("Enter password");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        emailLabel.setText("Enter email");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(registerButton)
                            .addGap(61, 61, 61)
                            .addComponent(exitButton)
                            .addGap(51, 51, 51)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(registerAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel)
                            .addComponent(emailLabel))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(passwordInput)
                                .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(registerButton)
                    .addComponent(resetButton))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String username = usernameInput.getText();
        String password = new String(passwordInput.getPassword());
        String email = emailInput.getText();
        int id = -1;
        if (username.equals("admin")) {
            Utils.showMessage(this, "This username is reserved for administrators");
            resetButton.doClick();
            return;
        }
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Utils.showMessage(this, "Please enter proper details");
            return;
        }
        if (username.length() > 20 || password.length() > 20) {
            Utils.showMessage(this, "Please enter shorter details(max 20 characters)");
            return;
        }

        SQLUtils sql = new SQLUtils(this);
        List<Map<String, Object>> queryData = sql.selectQueryWhere("username", "users", String.format("username=\'%s\'", username), "");
        if (!queryData.isEmpty()) {
            Map<String, Object> resultSet = queryData.get(0);
            if (!resultSet.isEmpty()) {
                Utils.showMessage(this, String.format("Invalid username %s, it is already taken!", username));
                resetButton.doClick();
                return;
            }
            resultSet = sql.selectQueryWhere("email", "users", String.format("email=\'%s\'", email), "").get(0);
            if (!resultSet.isEmpty()) {
                Utils.showMessage(this, String.format("Invalid email %s, it is already taken!", email));
                resetButton.doClick();
                return;
            }
        }
        queryData = sql.selectQuery("id", "users", "order by id desc limit 1");
        id = queryData.isEmpty() ? 1 : Integer.parseInt(queryData.get(0).get("id").toString()) + 1;
        User user = new User(id, username, Utils.encrypt(password), email);
        int n = sql.insert("users", user);
        Utils.showMessage(this, String.format("%d rows affected!", n));
        sql.close();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        usernameInput.setText("");
        passwordInput.setText("");
        emailInput.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        Utils.showMessage(this, "Exiting");
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(CreateAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAccountPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel registerAccountLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField usernameInput;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
