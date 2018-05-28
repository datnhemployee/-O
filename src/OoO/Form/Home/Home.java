/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.Form.Home;

import OoO.Tools.ImageTool;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 *
 * @author DAT
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        PreviousBtn = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NextBtn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BasketLabel = new javax.swing.JLabel();
        AddEggLabel = new javax.swing.JLabel();
        CalendarLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HomeFrame");
        setBackground(new java.awt.Color(0, 0, 0));
        setName("Home"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new Dimension(1500,1200));
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jPanel2.setLayout(null);

        jPanel1.setLayout(null);

        PreviousBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/PreviousButton.png"))); // NOI18N
        jPanel1.add(PreviousBtn);
        PreviousBtn.setBounds(0, 0, 64, 64);
        jPanel1.add(jLabel8);
        jLabel8.setBounds(64, 0, 128, 64);

        NextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/NextButton.png"))); // NOI18N
        jPanel1.add(NextBtn);
        NextBtn.setBounds(194, 0, 64, 64);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(810, 180, 256, 64);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/GridImage/GridView.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(130, 330, 408, 230);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/GridImage/GridView.png"))); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(730, 330, 410, 230);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/BannerIcon.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 570, 110, 140);

        BasketLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/Basket.png"))); // NOI18N
        BasketLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BasketLabelMouseClicked(evt);
            }
        });
        jPanel2.add(BasketLabel);
        BasketLabel.setBounds(180, 10, 250, 120);

        AddEggLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/AddBtn.png"))); // NOI18N
        AddEggLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddEggLabelMouseClicked(evt);
            }
        });
        jPanel2.add(AddEggLabel);
        AddEggLabel.setBounds(520, 30, 70, 80);

        CalendarLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/ButtonImage/CalendarButton.png"))); // NOI18N
        CalendarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalendarLabelMouseClicked(evt);
            }
        });
        jPanel2.add(CalendarLabel);
        CalendarLabel.setBounds(850, 50, 220, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OoO/img/Home/Background/option.png"))); // NOI18N
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 720);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 720);

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AddEggLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddEggLabelMouseClicked
        AddEggFrame AddEgg = new AddEggFrame();
        this.dispose();
        AddEgg.setVisible(true);
    }//GEN-LAST:event_AddEggLabelMouseClicked

    private void BasketLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BasketLabelMouseClicked
         BasketFrame Basket = new BasketFrame();
        this.dispose();
         Basket.setVisible(true);
    }//GEN-LAST:event_BasketLabelMouseClicked

    private void CalendarLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CalendarLabelMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
        BannerFrame Banner = new BannerFrame();
        this.dispose();
        Banner.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home().setVisible(true);
                
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddEggLabel;
    private javax.swing.JLabel BasketLabel;
    private javax.swing.JLabel CalendarLabel;
    private javax.swing.JLabel NextBtn;
    private javax.swing.JLabel PreviousBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}