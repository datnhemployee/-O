/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import Controller.*;
import Controller.SQLConnectivity;
import GUI.RecycleBin.BuocBin;
import GUI.BoardDetail;
import Model.Buoc;
import Model.ConstantIndex;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author DAT
 */
public class Step extends javax.swing.JPanel {

    private boolean checkDel = false;
    private boolean status = false;
    private SQLConnectivity conSQL;
    private Buoc info;
    private ControllerThe ctrlThe;
    private ControllerBang ctrlBang;
    private ControllerBuoc ctrlBuoc;

    private Step() {
    }

    /**
     * Creates new form Step
     */
    public Step(
            SQLConnectivity con,
            Buoc info,
            int width,
            int height
    ) {

        initComponents();
        conSQL = con;
        this.info = info;
        if (info != null) {
            this.status = info.isTrangThaiBuoc();
        } else {
            status = false;
        }
        checkDel = false;

        init();
        design(width, height);
    }

    protected void init() {
        this.ctrlBang = new ControllerBang(this.conSQL);
        this.ctrlBuoc = new ControllerBuoc(this.conSQL);
        this.ctrlThe = new ControllerThe(this.conSQL);
    }

    protected void design(
            int width,
            int height) {
        if (width == 0
                || height == 0) {
            this.setSize(800, 70);
        }
        this.setSize(width, height);
        this.setBackground(new Color(0, 0, 0, 0));

        lblStatus = new JLabel();
        try {
            if (info.isTrangThaiBuoc()) {
                lblStatus.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        "Icon\\IconDoneDarkBlue.png",
                                        30,
                                        30)));
            } else {
                lblStatus.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        "Icon\\IconUnDoneRed.png",
                                        30,
                                        30)));
            }
        } catch (Exception ex) {
            lblStatus.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Button\\BtnMenu.png",
                                    30,
                                    30)));
        }
        lblStatus.setSize(
                lblStatus.getIcon().getIconWidth(),
                lblStatus.getIcon().getIconHeight());
        System.out.println((this.getHeight()
                - lblStatus.getHeight())
                / 2);
        lblStatus.setLocation(
                5,
                (this.getHeight()
                - lblStatus.getHeight())
                / 2);

        txtName = new JTextArea("Chưa có thông tin");
        txtName.setBackground(Color.white);
        try {
            txtName.setForeground(BoardDetail.getColor_info(
                            ctrlBang.select(
                                    ctrlThe.select(this.info.getTheID())
                                            .getBangID())));
        } catch (Exception ex) {
            txtName.setForeground(new Color(210, 77, 87));
        }
        if (info != null) {
            txtName.setText(info.getNoiDung());
        }
        txtName.setFont(
                Tools.FontTool.getFont("Open Sans", 20));
        txtName.setSize(
                this.getWidth()
                - this.lblStatus.getWidth()
                - 20,
                this.getHeight()
                - 10
        );
        txtName.setLocation(
                lblStatus.getWidth()
                + 10,
                5
        );

        btnCheckDelete = new JLabel();
        btnCheckDelete.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Del_Gray,
                                30,
                                30)));
        btnCheckDelete.setSize(
                btnCheckDelete.getIcon().getIconWidth(),
                btnCheckDelete.getIcon().getIconHeight()
        );
        btnCheckDelete.setLocation(
                this.txtName.getWidth()
                - btnCheckDelete.getWidth()
                - 5,
                (txtName.getHeight()
                - this.btnCheckDelete.getHeight()) / 2
        );

        addListeners();
        this.add(this.lblStatus);
        this.add(this.txtName);
        this.txtName.add(this.btnCheckDelete);
    }

    public void addListeners() {
        this.btnCheckDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnCheckDelete_mouseClicked(e);
            }
        });
        this.lblStatus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblStatus_mouseClicked(e);
            }
        });
    }

    protected void lblStatus_mouseClicked(MouseEvent e) {
        status = !status;
        System.out.println("status Clicked=" + status);
        try {
            if (status) {

                lblStatus.setIcon(null);

                this.lblStatus.repaint();
                lblStatus.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        "Icon\\IconDoneDarkBlue.png",
                                        30,
                                        30)));
                this.lblStatus.repaint();

            } else {
                lblStatus.setIcon(null);
                this.lblStatus.repaint();
                lblStatus.setIcon(
                        new ImageIcon(
                                Tools.ImageTool.getScaledImage(
                                        "Icon\\IconUnDoneRed.png",
                                        30,
                                        30)));
                this.lblStatus.repaint();
            }
        } catch (Exception ex) {
            new MessageBox("LỖI CẬP NHẬT BƯỚC",
                    "Hệ thống không thể cập nhật bước vì: \n"
                    + ex
            );
        }

    }

    public Buoc getBuoc() {
        System.out.println("status =" + status);
        return new Buoc(
                info.getBuocID(),
                info.getTheID(),
                this.txtName.getText(),
                this.status
        );
    }

    protected void btnCheckDelete_mouseClicked(MouseEvent e) {
        this.checkDel = !this.checkDel;
        if (checkDel) {
            btnCheckDelete.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Button\\Delete_Red.png",
                                    30, 30)));
            BuocBin.setCon(conSQL);
            BuocBin.throwToBin(info);
        } else {
            btnCheckDelete.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    ConstantIndex.URLButton_Del_Gray,
                                    30, 30)));
            BuocBin.setCon(conSQL);
            BuocBin.restore(info.getBuocID());
        }
        this.btnCheckDelete.repaint();
    }

    public boolean isCheckDel() {
        return checkDel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lblStatus;
    private JTextArea txtName;
    private JLabel btnCheckDelete;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
