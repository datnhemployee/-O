/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import Controller.*;
import GUI.BoardDetail;
import Model.Buoc;
import Model.ConstantIndex;
import Model.The;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author DAT
 */
public class CardWithDate extends javax.swing.JPanel {

    private SQLConnectivity con;
    private The the;
    private ArrayList<Buoc> listBuoc;
    private int index;
    private ControllerBang ctrlBang;
    private ControllerThe ctrlThe;
    private ControllerBuoc ctrlBuoc;

    private CardWithDate() {

    }

    public CardWithDate(
            SQLConnectivity SQLcon,
            The the,
            int width,
            int height) {
        con = SQLcon;
        this.the = the;
        ctrlBang = new ControllerBang(con);
        ctrlThe = new ControllerThe(con);
        ctrlBuoc = new ControllerBuoc(con);
        index = 0;
        listBuoc = new ArrayList();

        initComponents();

        loadListBuoc();
        design(width, height);
    }

    protected void design(
            int width,
            int height
    ) {
        this.setBackground(new Color(0, 0, 0, 0));
        if (width == 0
                || height == 0) {
            width = 800;
            height = 100;
        }
        this.setSize(width, height);

        String NoiDung = "";
        if (listBuoc.isEmpty()) {
            NoiDung = the.getTen() + " : Chưa có bước làm việc";
        } else {
            try {
                NoiDung
                        = ctrlBang.select(the.getBangID()).getTen() + " : "
                        + the.getTen() + " : "
                        + listBuoc.get(0).getNoiDung();
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ LẤY THÔNG TIN THẺ",
                        "Lỗi: " + ex).setVisible(true);
            }
        }

        NoiDungBuoc = new JLabel(NoiDung);
        NoiDungBuoc.setForeground(Color.WHITE);
        NoiDungBuoc.setFont(Tools.FontTool
                .getFont("Open Sans", 20)
                .deriveFont(Font.BOLD));
        try {
            NoiDungBuoc.setBackground(BoardDetail.getColor_info(
                    ctrlBang.select(the.getBangID())));
        } catch (Exception ex) {
            NoiDungBuoc.setBackground(new Color(0, 47, 247));
        }
        NoiDungBuoc.setHorizontalTextPosition(SwingConstants.CENTER);
        NoiDungBuoc.setVerticalTextPosition(SwingConstants.CENTER);
        NoiDungBuoc.setOpaque(true);
        NoiDungBuoc.setSize(
                width - 20,
                height - 10);
        NoiDungBuoc.setLocation(
                10, 5);

        addListener();

        this.add(this.NoiDungBuoc);
    }

    protected void loadListBuoc() {
        try {
            listBuoc = this.ctrlBuoc.select_TheID(the.getTheID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void addListener() {
        this.NoiDungBuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NoiDungBuoc_mouseClicked();
            }

        });
    }

    protected void NoiDungBuoc_mouseClicked() {
        if (!listBuoc.isEmpty()) {
            this.index = (this.index + 1) % listBuoc.size();
            try {
                NoiDungBuoc.setText(
                        ctrlBang.select(the.getBangID()).getTen() + " : "
                        + the.getTen() + ": "
                        + listBuoc.get(index).getNoiDung()
                );
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ LẤY THÔNG TIN THẺ",
                        "Lỗi: " + ex).setVisible(true);
            }
            NoiDungBuoc.repaint();
        }
    }

    @SuppressWarnings("unchecked")
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
            .addGap(0, 56, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private JLabel NoiDungBuoc;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
