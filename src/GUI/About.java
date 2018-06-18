/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.SQLConnectivity;
import Model.ConstantIndex;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author DAT
 */
public class About extends javax.swing.JFrame {

    /**
     * Creates new form Setting
     */
    public About() {
        initComponents();

        
        design();
    }

    protected void design() {
        setType(javax.swing.JFrame.Type.UTILITY);
        lblBackground = new JLabel();
        this.setIconImage(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLDefaultOoO,
                                50,
                                70));
        lblAbout = new JLabel("Về chúng tôi");
        lblIconPhone = new JLabel();
        lblPhone = new JLabel("(+84)093.908.3581");
        lblIconGmail = new JLabel();
        lblGmail = new JLabel("15520104@gm.uit.edu.vn");
        OoOIcon = new JLabel();
        btnMinimize = new JLabel();
        btnExit = new JLabel();

        this.setBackground(new Color(0, 0, 0, 0));

        this.lblBackground.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLBackground_TableDetail_Red,
                                this.getWidth(),
                                this.getHeight()))
        );
        this.lblBackground.setSize(
                this.lblBackground.getIcon().getIconWidth(),
                this.lblBackground.getIcon().getIconHeight()
        );

        /*
        * 
         */
        OoOIcon = new JLabel();
        OoOIcon.setBackground(new Color(0, 0, 0, 0));
        OoOIcon.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\OoO_Orange.png",
                                100,
                                125)));
        OoOIcon.setBounds(
                (this.lblBackground.getWidth()
                - OoOIcon.getIcon().getIconWidth()) / 2,
                0,
                OoOIcon.getIcon().getIconWidth(),
                OoOIcon.getIcon().getIconHeight()
        );

        /*
        * btnExit
         */
        btnExit = new JLabel();
        btnExit.setBackground(new Color(0, 0, 0, 0));
        btnExit.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnExit.png",
                                20,
                                15)));
        btnExit.setBounds(
                this.lblBackground.getWidth()
                - btnExit.getIcon().getIconWidth() - 20,
                20,
                btnExit.getIcon().getIconWidth(),
                btnExit.getIcon().getIconHeight()
        );

        /*
        * BtnMinimize
         */
        btnMinimize = new JLabel();
        btnMinimize.setBackground(new Color(0, 0, 0, 0));
        btnMinimize.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMinimum.png",
                                25,
                                2)));
        btnMinimize.setBounds(
                this.btnExit.getX() - this.btnExit.getWidth() - 20,
                this.btnExit.getY()
                + (this.btnExit.getHeight()
                - btnExit.getIcon().getIconHeight()) / 2,
                btnMinimize.getIcon().getIconWidth(),
                btnExit.getIcon().getIconHeight()
        );

        lblAbout.setFont(
                Tools.FontTool.getFont("Cambria", 32));
        lblAbout.setForeground(Color.white);
        lblAbout.setSize(
                lblAbout.getText().length()
                * 21,
                50
        );
        lblAbout.setLocation(
                10,
                this.OoOIcon.getY()
                + this.OoOIcon.getHeight()
        );

        lblIconPhone.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\Phone.png",
                                100,
                                100)));
        lblIconPhone.setSize(
                lblIconPhone.getIcon().getIconWidth(),
                lblIconPhone.getIcon().getIconHeight()
        );
        lblIconPhone.setLocation(
                10,
                lblAbout.getY()
                + lblAbout.getHeight()
                + 20
        );

        lblIconGmail.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\Gmail.png",
                                100,
                                100)));
        lblIconGmail.setSize(
                lblIconGmail.getIcon().getIconWidth(),
                lblIconGmail.getIcon().getIconHeight()
        );
        lblIconGmail.setLocation(
                10,
                lblIconPhone.getY()
                + lblIconPhone.getHeight()
                + 20
        );
        lblPhone.setFont(
                Tools.FontTool.getFont("Cambria", 20)
                        .deriveFont(Font.BOLD));
        lblGmail.setFont(
                Tools.FontTool.getFont("Cambria", 20)
                        .deriveFont(Font.BOLD));
        lblPhone.setForeground(Color.white);
        lblGmail.setForeground(Color.white);
        lblPhone.setSize(
                this.lblBackground.getWidth()
                - this.lblIconGmail.getWidth() * 2,
                100
        );
        lblGmail.setSize(
                this.lblBackground.getWidth()
                - this.lblIconGmail.getWidth() * 2,
                100
        );
        lblPhone.setLocation(
                this.lblIconPhone.getX()
                + this.lblIconPhone.getWidth()
                + 20,
                this.lblIconPhone.getY()
                + (this.lblIconPhone.getHeight()
                - this.lblPhone.getHeight()) / 2
        );
        lblGmail.setLocation(
                this.lblIconGmail.getX()
                + this.lblIconGmail.getWidth()
                + 20,
                this.lblIconGmail.getY()
                + (this.lblIconGmail.getHeight()
                - this.lblGmail.getHeight()) / 2
        );

        addListener();
        this.add(lblBackground);
        this.lblBackground.add(this.btnExit);
        this.lblBackground.add(this.btnMinimize);
        lblBackground.add(this.OoOIcon);
        lblBackground.add(this.lblAbout);
        lblBackground.add(this.lblIconGmail);
        lblBackground.add(this.lblPhone);
        lblBackground.add(this.lblGmail);
        lblBackground.add(this.lblIconPhone);

    }

    protected void addListener() {
        this.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExit_mouseClicked();
            }
        });
        this.btnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMinimize_mouseClicked();
            }
        });

    }

    protected void btnExit_mouseClicked() {
        this.dispose();
    }

    protected void btnMinimize_mouseClicked() {
        this.setState(JFrame.ICONIFIED);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(500, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lblBackground;
    private JLabel lblAbout;
    private JLabel lblIconPhone;
    private JLabel lblPhone;
    private JLabel lblIconGmail;
    private JLabel lblGmail;
    private JLabel OoOIcon;
    private JLabel btnMinimize;
    private JLabel btnExit;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
