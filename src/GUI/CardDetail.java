/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.*;
import GUI.RecycleBin.BuocBin;
import GUI.myComponent.*;
import Model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author DAT
 */
public class CardDetail extends javax.swing.JFrame {

    private ControllerMau ctrlMau;
    private SQLConnectivity SQlcon;
    private The info;
    private ControllerThe ctrlThe;
    private ControllerBang ctrlBang;
    private ArrayList<Buoc> listBuoc;
    private ControllerBuoc ctrlBuoc;
    private ControllerOOO ctrlOOO;
    private ControllerOOO_THE ctrlOOO_THE;

    private int STTTheHienTai;
    private ArrayList<The> listThe;
    private ArrayList<OoO> listOOO;
    private int BangID;
    private int NexBuocID;
    private int NexOOOID;

    /**
     * Creates new form Card
     */
    private CardDetail() {
    }

    public CardDetail(
            SQLConnectivity SQLcon,
            The the,
            int BangID) {
        this.SQlcon = SQLcon;
        this.info = the;
        this.BangID = BangID;
        ctrlThe = new ControllerThe(SQLcon);
        ctrlBang = new ControllerBang(SQLcon);
        ctrlMau = new ControllerMau(SQLcon);
        ctrlBuoc = new ControllerBuoc(SQLcon);
        ctrlOOO = new ControllerOOO(SQlcon);
        ctrlOOO_THE = new ControllerOOO_THE(SQLcon);

        try {
            NexBuocID = ctrlBuoc.getNextID();
            NexOOOID = ctrlOOO.getNextID();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listBuoc = new ArrayList();
        listOOO = new ArrayList();
        loadListThe();
        addListBuoc();
        loadListOOO();

        initComponents();
        design();
    }

    protected void design() {
        this.setIconImage(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLDefaultOoO,
                                50,
                                70));
        this.setBackground(new Color(0, 0, 0, 0));

        lblBackground = new JLabel();
        lblBackground.setSize(this.getSize());
        lblBackground.setLocation(0, 0);
        lblBackground.setBackground(new Color(0, 0, 0, 0));
        try {
            lblBackground.setIcon(
                    BoardDetail.getBackgroundColor(
                            ctrlBang.select(info.getBangID()),
                            this.getWidth(),
                            this.getHeight()));
        } catch (Exception ex) {
            this.lblBackground.setIcon(
                    new ImageIcon(Tools.ImageTool.getScaledImage(
                            ConstantIndex.URLBackground_TableDetail_Red,
                            this.getWidth(),
                            this.getHeight())));
        }

        /*
        * Btnback
         */
        this.lblIconBack = new JLabel();
        this.lblIconBack.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Back,
                                20,
                                50)));
        this.lblIconBack.setSize(
                lblIconBack.getIcon().getIconWidth(),
                lblIconBack.getIcon().getIconHeight());
        this.lblIconBack.setLocation(20, 20);

        /*
        * BtnBack
         */
        this.BtnBack = new JLabel();
        this.BtnBack.setText("Trở lại");
        this.BtnBack.setForeground(Color.WHITE);
        this.BtnBack.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        20)
                        .deriveFont(Font.BOLD));
        this.BtnBack.setLocation(
                lblIconBack.getX()
                + lblIconBack.getWidth()
                + 5,
                lblIconBack.getY());
        this.BtnBack.setSize(
                100,
                lblIconBack.getHeight());

        lblThe = new JLabel("THẺ");
        lblThe.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        40)
                        .deriveFont(Font.BOLD));
        lblThe.setForeground(Color.white);
        lblThe.setLocation(
                BtnBack.getX()
                + BtnBack.getWidth()
                + 10,
                lblIconBack.getY());
        lblThe.setSize(
                lblThe.getText().length()
                * 30,
                lblThe.getFont().getSize() * 2);

        String title = "Chưa có nội dung ";
        TheBackground = new JTextArea(title);
        TheBackground.setForeground(Color.white);
        if (this.info != null) {
            TheBackground.setText(info.getTen());
        }
        TheBackground.setBackground(
                new Color(210, 77, 87));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                TheBackground.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                TheBackground.setBackground(
                        new Color(210, 77, 87));
            }
        }
        TheBackground.setFont(
                Tools.FontTool.getFont("Open Sans Light", 20));

        TheBackground.setOpaque(true);
        TheBackground.setSize(
                this.getWidth() / 7,
                this.getHeight() / 7
        );
        TheBackground.setLocation(
                TheBackground.getWidth() / 5
                + 10,
                lblThe.getY()
                + lblThe.getHeight()
                + 10
        );

        BtnNexThe = new JLabel();
        BtnPreThe = new JLabel();
        BtnNexThe.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Nex,
                                TheBackground.getWidth() / 5,
                                TheBackground.getHeight() - 10
                        )));
        BtnPreThe.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Pre,
                                TheBackground.getWidth() / 5,
                                TheBackground.getHeight() - 10
                        )));
        BtnPreThe.setSize(
                BtnPreThe.getIcon().getIconWidth(),
                BtnPreThe.getIcon().getIconHeight()
        );
        BtnNexThe.setSize(
                BtnNexThe.getIcon().getIconWidth(),
                BtnNexThe.getIcon().getIconHeight()
        );

        TheBackground.setSize(
                this.getWidth() / 7
                + BtnNexThe.getWidth() * 2,
                this.getHeight() / 7
        );
        BtnNexThe.setLocation(
                TheBackground.getX()
                + TheBackground.getWidth()
                + 5,
                TheBackground.getY()
        );
        BtnPreThe.setLocation(
                TheBackground.getX()
                - BtnNexThe.getWidth()
                - 5,
                TheBackground.getY()
        );

        lblIconBuoc = new JLabel();
        lblIconBuoc.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLIconBuoc_White,
                                lblThe.getWidth(),
                                lblThe.getHeight())));
        lblIconBuoc.setLocation(
                TheBackground.getX()
                + TheBackground.getWidth()
                + 100,
                lblThe.getY()
        );
        lblIconBuoc.setSize(
                lblIconBuoc.getIcon().getIconWidth(),
                lblIconBuoc.getIcon().getIconHeight());

        lblBuoc = new JLabel("Bước");
        lblBuoc.setFont(
                Tools.FontTool.getFont("Open Sans Light",
                        40).deriveFont(Font.BOLD));
        lblBuoc.setForeground(Color.WHITE);
        lblBuoc.setSize(
                lblBuoc.getText().length() * 30,
                lblBuoc.getFont().getSize());
        lblBuoc.setLocation(
                lblIconBuoc.getX()
                + lblIconBuoc.getWidth()
                + 10,
                lblIconBuoc.getY()
                + lblIconBuoc.getHeight() / 3
        );

        /*
        * btnExit
         */
        BtnExit = new JLabel();
        BtnExit.setBackground(new Color(0, 0, 0, 0));
        BtnExit.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnExit.png",
                                20,
                                15)));
        BtnExit.setBounds(
                this.lblBackground.getWidth()
                - BtnExit.getIcon().getIconWidth() - 20,
                20,
                BtnExit.getIcon().getIconWidth(),
                BtnExit.getIcon().getIconHeight()
        );

        /*
        * BtnMinimize
         */
        BtnMinimize = new JLabel();
        BtnMinimize.setBackground(new Color(0, 0, 0, 0));
        BtnMinimize.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMinimum.png",
                                25,
                                2)));
        BtnMinimize.setBounds(
                this.BtnExit.getX() - this.BtnExit.getWidth() - 20,
                this.BtnExit.getY()
                + (this.BtnExit.getHeight()
                - BtnExit.getIcon().getIconHeight()) / 2,
                BtnMinimize.getIcon().getIconWidth(),
                BtnExit.getIcon().getIconHeight()
        );

        pnlBuoc = new JPanel(new BorderLayout());
        pnlBuoc.setBackground(new Color(00, 0, 0, 0));
        pnlBuoc.setLocation(
                lblIconBuoc.getX(),
                lblIconBuoc.getY()
                + lblIconBuoc.getHeight()
                + 5
        );
        pnlBuoc.setSize(
                this.lblBackground.getWidth()
                - this.TheBackground.getX()
                - TheBackground.getWidth() * 3,
                this.TheBackground.getHeight() * 3 / 2
        );

        containerBuoc = new JPanel();
        try {
            containerBuoc.setBackground(
                    BoardDetail.getColor_info(
                            ctrlBang.select(info.getBangID())));
        } catch (Exception ex) {
            containerBuoc.setBackground(new Color(210, 77, 87));
        }
        containerBuoc.setSize(
                pnlBuoc.getSize());
        JScrollPane temp1 = new JScrollPane(
                containerBuoc,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        temp1.getVerticalScrollBar().setOpaque(false);
        temp1.getVerticalScrollBar().setUI(null);
        temp1.setBorder(null);
        temp1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        temp1.setDoubleBuffered(true);
        temp1.setBackground(
                new Color(210, 77, 87));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                temp1.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                temp1.setBackground(
                        new Color(210, 77, 87));
            }
        }
        pnlBuoc.add(temp1);
        this.containerBuoc.setLayout(new BoxLayout(containerBuoc, BoxLayout.Y_AXIS));

        try {
            scrllbrBuoc = new customScrollbar(
                    temp1.getVerticalScrollBar(),
                    BoardDetail.getColor_info(
                            ctrlBang.select(info.getBangID())),
                    new Color(255, 255, 255, 255),
                    null,
                    5,
                    20,
                    this.pnlBuoc.getHeight()
            );
        } catch (Exception ex) {
            scrllbrBuoc = new customScrollbar(
                    temp1.getVerticalScrollBar(),
                    new Color(210, 77, 87),
                    new Color(255, 255, 255, 255),
                    null,
                    5,
                    20,
                    this.pnlBuoc.getHeight()
            );
        }
        scrllbrBuoc.setSize(
                this.scrllbrBuoc.getWidth(),
                this.pnlBuoc.getHeight());
        scrllbrBuoc.setLocation(
                this.pnlBuoc.getX()
                + this.pnlBuoc.getWidth()
                + 10,
                this.pnlBuoc.getY()
        );

        this.txtBuoc = new JTextArea();
        txtBuoc.setText("Ghi nội dung vào đây");
        txtBuoc.setFont(
                Tools.FontTool.getFont("Open Sans", 20));
        txtBuoc.setDisabledTextColor(new Color(191, 191, 191));
        txtBuoc.setSize(
                this.lblBackground.getWidth()
                - this.scrllbrBuoc.getX()
                - this.scrllbrBuoc.getWidth()
                - 50,
                this.scrllbrBuoc.getHeight()
                - 50
        );
        txtBuoc.setLocation(
                this.lblBackground.getWidth()
                - txtBuoc.getWidth()
                - 25,
                this.pnlBuoc.getY()
        );

        this.BtnThemBuoc = new JLabel();
        BtnThemBuoc = new JLabel("Thêm");
        BtnThemBuoc.setBackground(
                new Color(210, 77, 87));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                BtnThemBuoc.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                BtnThemBuoc.setBackground(
                        new Color(210, 77, 87));
            }
        }
        BtnThemBuoc.setForeground(Color.white);
        BtnThemBuoc.setFont(
                Tools.FontTool
                        .getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        BtnThemBuoc.setVerticalTextPosition(
                SwingConstants.CENTER);
        BtnThemBuoc.setHorizontalAlignment(
                SwingConstants.CENTER);
        BtnThemBuoc.setOpaque(true);
        BtnThemBuoc.setSize(
                txtBuoc.getWidth(),
                50
        );
        BtnThemBuoc.setLocation(
                this.txtBuoc.getX(),
                this.txtBuoc.getY()
                + this.txtBuoc.getHeight()
                + 10
        );

        lblOOO = new JLabel();
        Mau mau = null;
        try {
            mau = ctrlMau.getMau(
                    ctrlBang.select(info.getBangID()).getMauID());
        } catch (Exception ex) {
        }
        lblOOO.setIcon(
                ConstantIndex.getOOO(mau,
                        100,
                        120));
        lblOOO.setSize(
                lblOOO.getIcon().getIconWidth(),
                lblOOO.getIcon().getIconHeight()
        );
        lblOOO.setLocation(
                TheBackground.getX(),
                TheBackground.getY()
                + pnlBuoc.getHeight()
                + 10
        );

        lblNhacNho = new JLabel("Nhắc nhở");
        lblNhacNho.setFont(Tools.FontTool.getFont(
                "Open Sans Light", 40));
        lblNhacNho.setForeground(Color.WHITE);
        lblNhacNho.setSize(
                lblNhacNho.getText().length()
                * 30,
                lblNhacNho.getFont().getSize()
        );
        lblNhacNho.setLocation(
                lblOOO.getX()
                + lblOOO.getWidth()
                + 10,
                lblOOO.getY()
                + 5
        );

        lblNgayOOO = new JLabel("Ngày");
        lblThangOOO = new JLabel("Tháng");
        lblNamOOO = new JLabel("Năm");

        lblNgayOOO.setFont(
                Tools.FontTool.getFont("Open Sans Light", 30));
        lblThangOOO.setFont(
                lblNgayOOO.getFont());
        lblNamOOO.setFont(
                lblNgayOOO.getFont());

        lblNgayOOO.setForeground(Color.white);
        lblThangOOO.setForeground(Color.white);
        lblNamOOO.setForeground(Color.white);

        lblNgayOOO.setSize(
                70,
                70);
        lblThangOOO.setSize(
                lblNgayOOO.getSize().width * 3 / 2,
                lblNgayOOO.getSize().height);
        lblNamOOO.setSize(lblNgayOOO.getSize());

        lblNgayOOO.setLocation(
                lblNhacNho.getX(),
                lblNhacNho.getY()
                + lblNhacNho.getHeight()
                + 5
        );
        lblThangOOO.setLocation(
                lblNgayOOO.getX(),
                lblNgayOOO.getY()
                + lblNgayOOO.getHeight()
                + 5
        );
        lblNamOOO.setLocation(
                lblThangOOO.getX(),
                lblThangOOO.getY()
                + lblThangOOO.getHeight()
                + 5
        );

        this.spnNgayOOO = new mySpinner(150, lblNgayOOO.getHeight());
        this.spnThangOOO = new mySpinner(150, lblThangOOO.getHeight()) {
            @Override
            public void BtnLeft_MouseClicked() {
                super.BtnLeft_MouseClicked();
                increaseNgayOOO();
            }

            @Override
            public void BtnRight_MouseClicked() {
                super.BtnRight_MouseClicked();
                increaseNgayOOO();
            }
        };
        this.spnNamOOO = new mySpinner(150, lblNamOOO.getHeight());

        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                spnNgayOOO.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
                spnThangOOO.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
                spnNamOOO.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                spnNgayOOO.setBackgroundColor(
                        new Color(210, 77, 87));
                spnThangOOO.setBackgroundColor(
                        new Color(210, 77, 87));
                spnNamOOO.setBackgroundColor(
                        new Color(210, 77, 87));
            }
        }
        spnNgayOOO.setSize(150, lblNgayOOO.getHeight());
        spnThangOOO.setSize(150, lblThangOOO.getHeight());
        spnNamOOO.setSize(150, lblNamOOO.getHeight());

        spnNgayOOO.setLocation(
                this.lblThangOOO.getX()
                + this.lblThangOOO.getWidth()
                + 10,
                this.lblNgayOOO.getY());
        spnThangOOO.setLocation(
                spnNgayOOO.getX(),
                this.lblThangOOO.getY());
        spnNamOOO.setLocation(
                spnNgayOOO.getX(),
                this.lblNamOOO.getY());

        pnlOOO = new JPanel(new BorderLayout());
        pnlOOO.setLocation(
                pnlBuoc.getX(),
                this.spnNgayOOO.getY()
        );
        pnlOOO.setBackground(new Color(0, 0, 0, 0));
        pnlOOO.setSize(
                this.lblBackground.getWidth()
                - this.pnlOOO.getX()
                - 100,
                200
        );

        containerOOO = new JPanel();
        try {
            containerOOO.setBackground(
                    BoardDetail.getColor_info(
                            ctrlBang.select(this.BangID)));
        } catch (Exception ex) {
            containerOOO.setBackground(new Color(210, 77, 87));
        }
        JScrollPane temp2 = new JScrollPane(
                containerOOO,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        temp2.getVerticalScrollBar().setOpaque(false);
        temp2.getVerticalScrollBar().setUI(null);
        temp2.setBorder(null);
        temp2.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        temp2.setDoubleBuffered(true);
        try {
            temp2.setBackground(
                    BoardDetail.getColor_info(
                            ctrlBang.select(this.BangID)));
        } catch (Exception ex) {
            temp2.setBackground(new Color(210, 77, 87));
        }
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                temp1.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                temp1.setBackground(
                        new Color(210, 77, 87));
            }
        }
        pnlOOO.add(temp2);
        this.containerOOO.setLayout(
                new BoxLayout(containerOOO, BoxLayout.Y_AXIS));

        try {
            scrllbrOOO = new customScrollbar(
                    temp2.getVerticalScrollBar(),
                    BoardDetail.getColor_info(
                            ctrlBang.select(info.getBangID())),
                    new Color(255, 255, 255, 255),
                    null,
                    5,
                    20,
                    this.pnlOOO.getHeight()
            );
        } catch (Exception ex) {
            scrllbrOOO = new customScrollbar(
                    temp2.getVerticalScrollBar(),
                    new Color(210, 77, 87, 255),
                    new Color(255, 255, 255, 255),
                    null,
                    5,
                    20,
                    this.pnlOOO.getHeight()
            );
        }

        scrllbrOOO.setSize(
                this.scrllbrOOO.getWidth(),
                this.pnlOOO.getHeight());
        scrllbrOOO.setLocation(
                this.pnlOOO.getX()
                + this.pnlOOO.getWidth()
                + 10,
                this.pnlOOO.getY()
        );

        BtnThemOOO = new JLabel("Thêm");
        BtnThemOOO.setBackground(
                new Color(210, 77, 87));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                BtnThemOOO.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                BtnThemOOO.setBackground(
                        new Color(210, 77, 87));
            }
        }
        BtnThemOOO.setForeground(Color.white);
        BtnThemOOO.setFont(
                Tools.FontTool
                        .getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        BtnThemOOO.setVerticalTextPosition(
                SwingConstants.CENTER);
        BtnThemOOO.setHorizontalAlignment(
                SwingConstants.CENTER);
        BtnThemOOO.setOpaque(true);
        BtnThemOOO.setSize(
                pnlOOO.getWidth(),
                50
        );
        BtnThemOOO.setLocation(
                pnlOOO.getX(),
                pnlOOO.getY()
                + pnlOOO.getHeight()
                + 10
        );

        // Thay đổi 
        lblIconHanChot = new JLabel();
        lblIconHanChot.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLIconDeadline_White,
                                100,
                                120)));
        lblIconHanChot.setSize(
                lblIconHanChot.getIcon().getIconWidth(),
                lblIconHanChot.getIcon().getIconHeight()
        );
        lblIconHanChot.setLocation(
                lblOOO.getX(),
                lblNamOOO.getY()
                + lblNamOOO.getHeight()
                + 10
        );

        lblHanChot = new JLabel("Hạn chót");
        lblHanChot.setFont(Tools.FontTool.getFont(
                "Open Sans Light", 40));
        lblHanChot.setForeground(Color.WHITE);
        lblHanChot.setSize(
                lblHanChot.getText().length()
                * 21,
                70
        );
        lblHanChot.setLocation(
                lblIconHanChot.getX()
                + lblIconHanChot.getWidth()
                + 10,
                lblIconHanChot.getY()
                + 5
        );

        lblNgayHanChot = new JLabel("Ngày");
        lblThangHanChot = new JLabel("Tháng");
        lblNamHanChot = new JLabel("Năm");

        lblNgayHanChot.setFont(
                Tools.FontTool.getFont("Open Sans Light", 30));
        lblThangHanChot.setFont(
                lblNgayHanChot.getFont());
        lblNamHanChot.setFont(
                lblNgayHanChot.getFont());

        lblNgayHanChot.setForeground(Color.white);
        lblThangHanChot.setForeground(Color.white);
        lblNamHanChot.setForeground(Color.white);

        lblNgayHanChot.setSize(
                70,
                70);
        lblThangHanChot.setSize(
                lblNgayHanChot.getSize().width * 3 / 2,
                lblNgayHanChot.getSize().height);
        lblNamHanChot.setSize(lblNgayHanChot.getSize());

        this.spnNgayHanChot = new mySpinner(150, lblNgayHanChot.getHeight());

        this.spnThangHanChot = new mySpinner(150, lblThangHanChot.getHeight()) {
            @Override
            public void BtnLeft_MouseClicked() {
                super.BtnLeft_MouseClicked();
                increaseNgayHanChot();
            }

            @Override
            public void BtnRight_MouseClicked() {
                super.BtnRight_MouseClicked();
                increaseNgayHanChot();
            }
        };
        this.spnNamHanChot = new mySpinner(150, lblNamHanChot.getHeight());

        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                spnNgayHanChot.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
                spnThangHanChot.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
                spnNamHanChot.setBackgroundColor(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                spnNgayHanChot.setBackgroundColor(
                        new Color(210, 77, 87));
                spnThangHanChot.setBackgroundColor(
                        new Color(210, 77, 87));
                spnNamHanChot.setBackgroundColor(
                        new Color(210, 77, 87));
            }
        }
        spnNgayHanChot.setSize(150, lblNgayHanChot.getHeight());
        spnThangHanChot.setSize(150, lblThangHanChot.getHeight());
        spnNamHanChot.setSize(150, lblNamHanChot.getHeight());

        lblNgayHanChot.setLocation(
                lblHanChot.getX(),
                lblHanChot.getY()
                + lblHanChot.getHeight()
                + 5
        );
        spnNgayHanChot.setLocation(
                this.lblNgayHanChot.getX()
                + this.lblNgayHanChot.getWidth()
                + 30,
                this.lblNgayHanChot.getY());

        lblThangHanChot.setLocation(
                spnNgayHanChot.getX()
                + spnNgayHanChot.getWidth()
                + 30,
                this.spnNgayHanChot.getY());

        spnThangHanChot.setLocation(
                lblThangHanChot.getX()
                + lblThangHanChot.getWidth()
                + 30,
                this.spnNgayHanChot.getY());

        lblNamHanChot.setLocation(
                spnThangHanChot.getX()
                + spnThangHanChot.getWidth()
                + 30,
                this.spnNgayHanChot.getY());

        spnNamHanChot.setLocation(
                lblNamHanChot.getX()
                + lblNamHanChot.getWidth()
                + 30,
                this.spnNgayHanChot.getY());

        btnLuu = new JLabel("Lưu lại");
        btnLuu.setBackground(
                new Color(210, 77, 87));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                btnLuu.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                btnLuu.setBackground(
                        new Color(210, 77, 87));
            }
        }
        btnLuu.setForeground(Color.white);
        btnLuu.setFont(
                Tools.FontTool
                        .getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        btnLuu.setVerticalTextPosition(
                SwingConstants.CENTER);
        btnLuu.setHorizontalAlignment(
                SwingConstants.CENTER);
        btnLuu.setOpaque(true);
        btnLuu.setSize(
                this.lblBackground.getWidth() / 4,
                50
        );
        btnLuu.setLocation(
                spnNamHanChot.getX()
                + spnNamHanChot.getWidth()
                + 30,
                this.lblBackground.getHeight()
                - btnLuu.getHeight() * 2
                - 20
        );

        btnXoa = new JLabel("Xoá");
        btnXoa.setBackground(
                new Color(218, 227, 243));
        if (this.info != null) {
            try {
                Bang temp = ctrlBang.select(
                        info.getBangID());
                btnXoa.setBackground(
                        BoardDetail.getColor_info(temp));
            } catch (Exception ex) {
                btnXoa.setBackground(
                        new Color(210, 77, 87));
            }
        }
        btnXoa.setForeground(Color.white);
        btnXoa.setFont(
                Tools.FontTool
                        .getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        btnXoa.setVerticalTextPosition(
                SwingConstants.CENTER);
        btnXoa.setHorizontalAlignment(
                SwingConstants.CENTER);
        btnXoa.setOpaque(true);
        btnXoa.setSize(
                btnLuu.getWidth(),
                btnLuu.getHeight()
        );
        btnXoa.setLocation(
                btnLuu.getX(),
                btnLuu.getY()
                + btnLuu.getHeight()
                + 10
        );

        addListeners();
        loadDate();
        addSteps();
        addOOO();

//        Calendar c = Calendar.getInstance();
//        c.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Ho_Chi_Minh")));
//        c.setTime(new Date(System.currentTimeMillis()));
//        int Ngay = c.get(Calendar.DAY_OF_MONTH);
//        int Thang = c.get(Calendar.MONTH);
//        int Nam = c.get(Calendar.YEAR);
//        loadHanChot();
//      addListeners();
//        c.add(Calendar.DAY_OF_MONTH, 1);
        this.add(this.lblBackground);
        this.lblBackground.add(lblThe);
        this.lblBackground.add(TheBackground);
        lblBackground.add(BtnNexThe);
        lblBackground.add(BtnPreThe);
        lblBackground.add(lblIconBuoc);
        lblBackground.add(lblBuoc);
        lblBackground.add(BtnExit);
        lblBackground.add(BtnMinimize);
        lblBackground.add(pnlBuoc);
        lblBackground.add(scrllbrBuoc);
        lblBackground.add(lblOOO);
        lblBackground.add(lblNhacNho);
        lblBackground.add(lblNgayOOO);
        lblBackground.add(lblThangOOO);
        lblBackground.add(lblNamOOO);

        lblBackground.add(this.spnNamOOO);
        lblBackground.add(this.spnNgayOOO);
        lblBackground.add(this.spnThangOOO);

        lblBackground.add(this.pnlOOO);
        lblBackground.add(this.scrllbrOOO);
        lblBackground.add(this.BtnThemOOO);

        lblBackground.add(this.lblIconHanChot);
        lblBackground.add(this.lblHanChot);
        lblBackground.add(this.lblNgayHanChot);
        lblBackground.add(this.lblThangHanChot);
        lblBackground.add(this.lblNamHanChot);
        lblBackground.add(this.spnNamHanChot);
        lblBackground.add(this.spnNgayHanChot);
        lblBackground.add(this.spnThangHanChot);
        lblBackground.add(this.btnLuu);
        lblBackground.add(this.btnXoa);

        lblBackground.add(this.BtnThemBuoc);
        lblBackground.add(this.txtBuoc);

        lblBackground.add(this.BtnBack);
        lblBackground.add(this.lblIconBack);
    }

    protected void loadListThe() {
        try {
            listThe = ctrlThe.select_BangID(this.BangID);
            listThe.add(null);
            if (this.info != null) {
                for (int i = 0; i < listThe.size(); i++) {
                    if (listThe.get(i) != null) {
                        if (listThe.get(i).getTheID()
                                == this.info.getTheID()) {
                            STTTheHienTai = i;
                            return;
                        }
                    }
                }
            }
            STTTheHienTai = listThe.size() - 1;
        } catch (Exception ex) {
            new MessageBox("LỖI KHÔNG THỂ LẤY DANH SÁCH THẺ",
                    "Nguyên nhân: " + ex).setVisible(true);
        }

    }

    protected void addListBuoc() {
        if (this.info != null) {
            try {
                this.listBuoc = ctrlBuoc.select_TheID(info.getTheID());
            } catch (Exception ex) {
                new MessageBox(
                        "LỖI LẤY THÔNG TIN BƯỚC",
                        "Không thể lấy thông tin bước vì "
                        + ex)
                        .setVisible(true);
            }
        }
    }

    private void addListeners() {
        this.BtnNexThe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNexThe_mouseClicked();
            }
        });
        this.BtnPreThe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNexThe_mouseClicked();
            }
        });
        this.btnLuu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnLuu_mouseClicked();
            }
        });
        this.btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnXoa_mouseClicked();
            }
        });
        this.BtnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBack_mouseClicked();
            }
        });
        this.lblIconBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBack_mouseClicked();
            }
        });
        this.BtnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMinimize_mouseClicked();
            }
        });
        this.BtnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExit_mouseClicked();
            }
        });
        this.BtnThemBuoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnThemBuoc_mouseClicked();
            }
        });
        this.BtnThemOOO.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnThemOOO_mouseClicked();
            }
        });
    }

    protected void btnThemOOO_mouseClicked() {
        try {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, spnThangOOO.getValue() - 1);
            c.set(Calendar.DAY_OF_MONTH, spnNgayOOO.getValue());
            c.set(Calendar.YEAR, spnNamOOO.getValue());
            if (!ctrlOOO.isExistDate(new Date(c.getTimeInMillis()))) {
                System.out.println("Trước Thêm = " + listOOO.toString());
                listOOO.add(
                        new OoO(
                                this.NexOOOID,
                                new Date(c.getTimeInMillis())
                        ));

                containerOOO.add(
                        new myDateOOO(
                                new Date(c.getTimeInMillis()),
                                this.pnlOOO.getWidth() - 20,
                                70));
                for (int i = 0;
                        i < this.containerOOO
                                .getComponents().length;
                        i++) {
                    containerOOO.getComponent(i).repaint();
                }
                containerOOO.repaint();
                this.NexOOOID++;
            }
        } catch (Exception ex) {
            System.out.println("Lỗi addOOO: " + ex);
        }
    }

    protected void btnThemBuoc_mouseClicked() {
        if (!txtBuoc.getText().isEmpty()
                && !txtBuoc.getText().equalsIgnoreCase("Ghi nội dung vào đây")) {
            try {
                int id = ctrlThe.getNextID();
                if (this.info != null) {
                    id = info.getTheID();
                }
                this.listBuoc.add(
                        new Buoc(
                                this.NexBuocID,
                                id,
                                this.txtBuoc.getText(),
                                false
                        ));
                this.containerBuoc.add(
                        new Step(
                                this.SQlcon,
                                new Buoc(
                                        this.NexBuocID,
                                        id,
                                        this.txtBuoc.getText(),
                                        false
                                ),
                                this.pnlBuoc.getWidth() - 10,
                                70
                        ));
                NexBuocID++;
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ THÊM",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
        }

    }

    protected void btnExit_mouseClicked() {
        try {
            Tools.TimeTool.exportToFile_LastestLogIn(
                    new Date(System.currentTimeMillis()));
            this.dispose();
            System.exit(0);
        } catch (Exception ex) {
            new MessageBox("LỖI KHÔNG THỂ THOÁT",
                    "\n Không thể kết xuất ngày hôm nay"
                    + "\n Xuất hiện lỗi hệ thông "
                    + ex).setVisible(true);
        }
    }

    protected void btnMinimize_mouseClicked() {
        this.setState(JFrame.ICONIFIED);
    }

    protected void btnBack_mouseClicked() {
        try {
            new BoardDetail(
                    ctrlBang.select(
                            this.BangID),
                    this.SQlcon
            ).setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            new BoardDetail(
                    null,
                    this.SQlcon
            ).setVisible(true);
            this.dispose();
        }
    }

    protected void btnXoa_mouseClicked() {
        if (this.info != null) {
            try {
                ctrlThe.delete(info.getTheID());
                new BoardDetail(
                        ctrlBang.select(this.BangID),
                        this.SQlcon)
                        .setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ XOÁ",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
        }
    }

    protected void btnLuu_mouseClicked() {
        if (this.info != null) {
            for (int i = 0;
                    i < this.containerBuoc
                            .getComponents().length;
                    i++) {
                try {
                    Buoc buoc = ((Step) containerBuoc.getComponent(i))
                            .getBuoc();
                    if (ctrlBuoc.isExistID(
                            buoc.getBuocID())) {
                        ctrlBuoc.update(buoc);
                    } else {
                        ctrlBuoc.insert(buoc);
                    }
                } catch (Exception ex) {
                    new MessageBox("LỖI KHÔNG THỂ CẬP NHẬT BƯỚC",
                            " Xuất hiện lỗi hệ thông "
                            + ex).setVisible(true);
                }
            }
            BuocBin.setCon(SQlcon);
            try {
                BuocBin.wash();
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ XOÁ BƯỚC",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            this.listBuoc.clear();
            this.addListBuoc();
            this.containerBuoc.removeAll();
            this.addSteps();

            for (int i = 0;
                    i < this.containerOOO
                            .getComponents().length;
                    i++) {
                try {
                    Date d = ((myDateOOO) containerOOO.getComponent(i))
                            .getDate();
                    int NexID = ctrlOOO.getNextID();
                    if (!ctrlOOO.isExistDate(d)) {
                        NexID = NexID + i;
                        ctrlOOO.insert(
                                new OoO(
                                        NexID,
                                        d
                                ));
                    }else{
                        NexID = ctrlOOO.select(d).get(0).getOOOID();
                    }
                    ctrlOOO_THE.insert(new OoO_The(NexID, this.info.getTheID()));
                } catch (Exception ex) {
                    new MessageBox("LỖI KHÔNG THỂ CẬP NHẬT NGÀY BÁOs",
                            " Xuất hiện lỗi hệ thông "
                            + ex).setVisible(true);
                }
            }
            The newOne = new The(
                    this.info.getTheID(),
                    this.BangID,
                    this.getHanChotDate(),
                    this.TheBackground.getText());
            try {
                ctrlThe.update(newOne);
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ CẬP NHẬT THẺ",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            try {
                new CardDetail(
                        this.SQlcon,
                        newOne,
                        this.BangID
                ).setVisible(true);
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ XUẤT BẢNG THẺ",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            this.dispose();
        } else {
            The newOne = null;
            try {
                newOne = new The(
                        ctrlThe.getNextID(),
                        this.BangID,
                        this.getHanChotDate(),
                        this.TheBackground.getText());
                ctrlThe.insert(newOne);
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ THÊM THẺ",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            for (int i = 0;
                    i < this.containerBuoc
                            .getComponents().length;
                    i++) {
                try {
                    if (!((Step) containerBuoc.getComponent(i)).isCheckDel()) {
                        Buoc buoc = ((Step) containerBuoc.getComponent(i))
                                .getBuoc();
                        if (ctrlBuoc.isExistID(
                                buoc.getBuocID())) {
                            ctrlBuoc.update(buoc);
                        } else {
                            buoc.setBuocID(ctrlBuoc.getNextID() + i);

                            ctrlBuoc.insert(buoc);
                        }
                    }
                } catch (Exception ex) {
                    new MessageBox("LỖI KHÔNG THỂ CẬP NHẬT BƯỚC",
                            " Xuất hiện lỗi hệ thông "
                            + ex).setVisible(true);
                }
            }
            try {
                BuocBin.wash();
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ XOÁ BƯỚC",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            for (int i = 0;
                    i < this.containerOOO
                            .getComponents().length;
                    i++) {
                try {
                    Date d = ((myDateOOO) containerOOO.getComponent(i))
                            .getDate();
                    int NexID = ctrlOOO.getNextID() + i;
                    if (!ctrlOOO.isExistDate(d)) {
                        ctrlOOO.insert(
                                new OoO(
                                        NexID,
                                        d
                                ));
                    } else {
                        NexID = ctrlOOO.select(d).get(0).getOOOID();
                    }
                    ctrlOOO_THE.insert(new OoO_The(NexID, newOne.getTheID()));
                } catch (Exception ex) {
                    new MessageBox("LỖI KHÔNG THỂ CẬP NHẬT NGÀY BÁO",
                            " Xuất hiện lỗi hệ thông "
                            + ex).setVisible(true);
                }
            }
            try {
                new CardDetail(
                        this.SQlcon,
                        newOne,
                        this.BangID
                ).setVisible(true);
            } catch (Exception ex) {
                new MessageBox("LỖI KHÔNG THỂ XUẤT BẢNG THẺ",
                        " Xuất hiện lỗi hệ thông "
                        + ex).setVisible(true);
            }
            this.dispose();
        }
    }

    protected void btnNexThe_mouseClicked() {
        int nex = this.STTTheHienTai;
        if (STTTheHienTai == 0) {
            nex = this.listThe.size() - 1;
        } else {
            nex--;
        }
        new CardDetail(
                this.SQlcon,
                this.listThe.get(nex),
                this.BangID
        ).setVisible(true);
        this.dispose();
    }

    protected void addSteps() {
        if (!this.listBuoc.isEmpty()) {
            for (int i = 0; i < listBuoc.size(); i++) {
                this.containerBuoc.add(
                        new Step(
                                this.SQlcon,
                                listBuoc.get(i),
                                this.pnlBuoc.getWidth() - 10,
                                70
                        ));
            }
        }
    }

    protected void loadListOOO() {
        ArrayList<OoO_The> listOOO_THE = new ArrayList();
        try {
            listOOO_THE
                    = ctrlOOO_THE.select_THEID(this.info.getTheID());
            for (int i = 0; i < listOOO_THE.size(); i++) {
                listOOO.add(
                        ctrlOOO.select(
                                listOOO_THE.get(i).getOOOID()));
            }
        } catch (Exception ex) {
            System.out.println("Lỗi loadlistOOO: " + ex);
        }
    }

    protected void addOOO() {
        if (!this.listOOO.isEmpty()) {
            for (int i = 0; i < this.listOOO.size(); i++) {
                try {
                    containerOOO.add(
                            new myDateOOO(
                                    listOOO.get(i).getNgayBao(),
                                    this.pnlOOO.getWidth() - 20,
                                    70));
                } catch (Exception ex) {
                    System.out.println("Lỗi addOOO: " + ex);
                }
            }
        }
    }

    protected void increaseNgayOOO() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, spnNamOOO.getValue());
        c.set(Calendar.MONTH, spnThangOOO.getValue() - 1);

        spnNgayOOO.setMax(c.getActualMaximum(Calendar.DAY_OF_MONTH));

        c.setTime(new Date(System.currentTimeMillis()));
        if (spnThangOOO.getValue() - 1
                != c.get(Calendar.MONTH)) {
            spnNgayOOO.setMin(1);
            spnNgayOOO.setValue(spnThangHanChot.getMin());
        } else {
            spnNgayOOO.setValue(
                    c.get(Calendar.DAY_OF_MONTH));
            spnNgayOOO.setMin(
                    c.get(Calendar.DAY_OF_MONTH));
        }
        spnNgayOOO.repaint();
    }

    protected void increaseNgayHanChot() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, spnNamHanChot.getValue());
        c.set(Calendar.MONTH, spnThangHanChot.getValue() - 1);

        spnNgayHanChot.setMax(c.getActualMaximum(Calendar.DAY_OF_MONTH));

        c.setTime(new Date(System.currentTimeMillis()));
        if (spnThangHanChot.getValue() - 1
                != c.get(Calendar.MONTH)) {
            spnNgayHanChot.setMin(1);
            spnNgayHanChot.setValue(spnThangHanChot.getMin());
        } else {
            spnNgayHanChot.setValue(
                    c.get(Calendar.DAY_OF_MONTH));
            spnNgayHanChot.setMin(
                    c.get(Calendar.DAY_OF_MONTH));
        }

        spnNgayHanChot.repaint();
    }

    protected Date getOOODate() {
        int ngay = this.spnNgayOOO.getValue();
        int thang = this.spnThangOOO.getValue() - 1;
        int nam = this.spnNamOOO.getValue();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, nam);
        c.set(Calendar.MONTH, thang);
        c.set(Calendar.DAY_OF_MONTH, ngay);

        return new Date(c.getTimeInMillis());
    }

    protected Date getHanChotDate() {
        int ngay = this.spnNgayHanChot.getValue();
        int thang = this.spnThangHanChot.getValue() - 1;
        int nam = this.spnNamHanChot.getValue();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, nam);
        c.set(Calendar.MONTH, thang);
        c.set(Calendar.DAY_OF_MONTH, ngay);

        return new Date(c.getTimeInMillis());
    }

    protected void loadDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        spnNgayOOO.setValue(c.get(Calendar.DAY_OF_MONTH));
        spnThangOOO.setValue(c.get(Calendar.MONTH) + 1);
        spnNamOOO.setValue(c.get(Calendar.YEAR));

        spnNgayOOO.setMin(c.get(Calendar.DAY_OF_MONTH));
        spnThangOOO.setMin(c.get(Calendar.MONTH) + 1);
        spnNamOOO.setMin(c.get(Calendar.YEAR));

        spnNgayOOO.setMax(c.getMaximum(Calendar.DAY_OF_MONTH));
        spnThangOOO.setMax(12);
        spnNamOOO.setMax(Integer.MAX_VALUE);

        if (info != null && info.getHanChot() != null) {
            c.setTime(info.getHanChot());
        }
        this.spnNgayHanChot.setValue(c.get(Calendar.DAY_OF_MONTH));
        spnThangHanChot.setValue(c.get(Calendar.MONTH) + 1);
        spnNamHanChot.setValue(c.get(Calendar.YEAR));

        spnNgayHanChot.setMin(c.get(Calendar.DAY_OF_MONTH));
        spnThangHanChot.setMin(c.get(Calendar.MONTH) + 1);
        spnNamHanChot.setMin(c.get(Calendar.YEAR));

        spnNgayHanChot.setMax(c.getMaximum(Calendar.DAY_OF_MONTH));
        spnThangHanChot.setMax(12);
        spnNamHanChot.setMax(Integer.MAX_VALUE);
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
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1500, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lblBackground;

    private JLabel lblThe;
    private JLabel lblIconBuoc;
    private JLabel lblBuoc;

    private JTextArea TheBackground;
    private JLabel BtnNexThe;
    private JLabel BtnPreThe;

    private JPanel pnlBuoc;
    private JPanel containerBuoc;
    private customScrollbar scrllbrBuoc;
    private JLabel BtnThemBuoc;
    private JTextArea txtBuoc;

    private JLabel lblNhacNho;
    private JLabel lblOOO;

    private JLabel lblNgayOOO;
    private JLabel lblThangOOO;
    private JLabel lblNamOOO;

    private mySpinner spnNgayOOO;
    private mySpinner spnThangOOO;
    private mySpinner spnNamOOO;
    private JLabel BtnThemOOO;

    private JPanel pnlOOO;
    private JPanel containerOOO;
    private customScrollbar scrllbrOOO;

    private JLabel lblHanChot;
    private JLabel lblIconHanChot;

    private JLabel lblNgayHanChot;

    private JLabel lblThangHanChot;

    private JLabel lblNamHanChot;
    private mySpinner spnNgayHanChot;
    private mySpinner spnThangHanChot;
    private mySpinner spnNamHanChot;

    private JLabel btnLuu;
    private JLabel btnXoa;

    private JLabel BtnBack;
    private JLabel lblIconBack;

    private JLabel BtnExit;
    private JLabel BtnMinimize;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
