/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import Model.ConstantIndex;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author DAT
 */
public class myDateOOO extends javax.swing.JPanel {

    private Date d;

    private myDateOOO() {
        initComponents();
    }

    public myDateOOO(
            Date d,
            int width,
            int height) throws Exception {
        if (d == null) {
            throw new Exception("There is no way to create a null date");
        }
        this.d = d;

        design(width, height);
    }

    protected void design(
            int width,
            int height) {
        if (width == 0
                || height == 0) {
            this.setSize(200, 70);
        } else {
            this.setSize(width, height);
        }
        this.setBackground(new Color(0,0,0,0));
        lblBackground = new JLabel();
        txtDate = new JLabel();

        Date cur = new Date(System.currentTimeMillis());
        try {
            if (d.before(Tools.TimeTool.getLastestDateLogin())) {
                lblBackground.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        ConstantIndex.URLDate_Passed,
                                        this.getWidth(),
                                        this.getHeight())));
            } else if (d.before(cur)) {
                lblBackground.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        ConstantIndex.URLDate_Missed,
                                        this.getWidth(),
                                        this.getHeight())));
            } else {
                lblBackground.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        ConstantIndex.URLDate_OOO,
                                        this.getWidth(),
                                        this.getHeight())));
            }
        } catch (Exception ex) {
            new MessageBox("LỖI KHÔNG CẬP NHẬT ĐƯỢC NGÀY", ""
                    + "Nguyên nhân: " + ex).setVisible(true);
        }
        lblBackground.setSize(
                lblBackground.getIcon().getIconWidth(),
                lblBackground.getIcon().getIconHeight()
        );
        lblBackground.setLocation(0, 0);

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        txtDate.setText(sf.format(d));
        txtDate.setFont(Tools.FontTool.getFont("Open Sans Light", 30)
                .deriveFont(Font.BOLD));
        txtDate.setHorizontalAlignment(SwingConstants.CENTER);
        txtDate.setVerticalAlignment(SwingConstants.CENTER);
        txtDate.setHorizontalTextPosition(SwingConstants.CENTER);
        txtDate.setVerticalTextPosition(SwingConstants.CENTER);
        txtDate.setSize(
                this.lblBackground.getWidth() / 2,
                this.lblBackground.getHeight() - 20);
       

        this.add(this.lblBackground);
        this.lblBackground.add(this.txtDate);
    }
    public void setDate(Date d){
        this.d = d;
    }
    public Date getDate(){
        return d;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private JLabel lblBackground;
    private JLabel txtDate;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
