/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.*;
import GUI.myComponent.*;
import Model.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author DAT
 */
public class Diary extends javax.swing.JFrame {

    private SQLConnectivity SQLcon;
    private ArrayList<The> listThe;
    private ControllerThe ctrlThe;
    private ControllerThe ctrlBang;
    private ControllerOOO ctrlOOO;
    private ControllerOOO_THE ctrlOOO_THE;

    private Diary() {
    }

    public Diary(
            SQLConnectivity con
    ) {
        initComponents();
        this.SQLcon = con;
        listThe = new ArrayList();
        ctrlThe = new ControllerThe(this.SQLcon);
        ctrlOOO = new ControllerOOO(this.SQLcon);
        ctrlOOO_THE = new ControllerOOO_THE(SQLcon);

        loadThe(new Date(System.currentTimeMillis()));
        design();
    }

    private void design() {

        this.setIconImage(
                Tools.ImageTool.getScaledImage(
                        ConstantIndex.URLDefaultOoO,
                        50,
                        70));
        /*
        * lbl Background
         */
        lblBackground = new JLabel();
        lblBackground.setIcon(new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        "Background\\BG_Green.png",
                        this.getSize().width,
                        this.getSize().height)));
        lblBackground.setLocation(new Point(0, 0));
        lblBackground.setSize(new Dimension(
                this.getSize().width,
                this.getSize().height));

        /*
        * Card Bang
         */
        btnBang = new ButtonWithImage(
                "Bảng",
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\NullBtn.png",
                                100,
                                50))
        );
        btnBang.setBounds(
                20,
                20,
                this.btnBang.getImage().getIconWidth(),
                this.btnBang.getImage().getIconHeight()
        );

        /*
        * Card Nhat Ki
         */
        btnNhatKi = new ButtonWithImage(
                "Nhật Kí",
                new Color(0, 246, 146),
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMenu.png",
                                100,
                                50))
        );
        btnNhatKi.setBounds(
                btnBang.getX()
                + btnBang.getWidth() + 30,
                20,
                this.btnNhatKi.getImage().getIconWidth(),
                this.btnNhatKi.getImage().getIconHeight()
        );
        lblNgay = new JLabel("Ngày");
        lblThang = new JLabel("Tháng");
        lblNam = new JLabel("Năm");

        lblNgay.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        25));
        lblNgay.setForeground(Color.white);

        lblThang.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        25));
        lblThang.setForeground(Color.white);

        lblNam.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        25));
        lblNam.setForeground(Color.white);

        lblNgay.setSize(
                lblNgay.getText().length() * 21,
                50
        );
        lblThang.setSize(
                lblThang.getText().length() * 21,
                50
        );
        lblNam.setSize(
                lblNam.getText().length() * 21,
                50
        );

        this.spnNgay = new mySpinner(150, 70);
        this.spnThang = new mySpinner(150, 70) {
            @Override
            public void BtnLeft_MouseClicked() {
                super.BtnLeft_MouseClicked();
                increaseDate();
            }

            @Override
            public void BtnRight_MouseClicked() {
                super.BtnRight_MouseClicked();
                increaseDate();
            }
        };
        this.spnNam = new mySpinner(150, 70);

        spnNgay.setBackgroundColor(
                new Color(210, 77, 87));
        spnThang.setBackgroundColor(
                new Color(210, 77, 87));
        spnNam.setBackgroundColor(
                new Color(210, 77, 87));

        spnNgay.setSize(150, 70);
        spnThang.setSize(150, 70);
        spnNam.setSize(150, 70);

        loadDate();

        lblNgay.setLocation(
                btnBang.getX()
                + btnBang.getWidth(),
                btnBang.getY()
                + btnBang.getHeight() * 2
        );
        spnNgay.setLocation(
                this.lblNgay.getX()
                + this.lblNgay.getWidth()
                + 30,
                this.lblNgay.getY());
        lblThang.setLocation(
                spnNgay.getX()
                + spnNgay.getWidth()
                + 30,
                spnNgay.getY()
        );
        spnThang.setLocation(
                lblThang.getX()
                + lblThang.getWidth()
                + 30,
                this.lblThang.getY());
        lblNam.setLocation(
                spnThang.getX()
                + spnThang.getWidth()
                + 30,
                spnThang.getY()
        );
        spnNam.setLocation(
                lblNam.getX()
                + lblNam.getWidth()
                + 30,
                this.lblNam.getY());

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

        /*
        * pnlThe
         */
        this.pnlThe = new JPanel(new BorderLayout());
        pnlThe.setBackground(new Color(0, 0, 0, 0));
        pnlThe.setLocation(
                this.lblNgay.getX(),
                this.spnNgay.getY()
                + this.spnNgay.getHeight()
                + 20
        );
        pnlThe.setSize(
                this.lblBackground.getWidth()
                - this.lblNgay.getX() * 2,
                this.lblBackground.getHeight()
                - this.spnNgay.getY()
                - this.spnNgay.getHeight()
                - 40);
        containerThe = new JPanel();
        containerThe.setBackground(new Color(0, 246, 146));

        JScrollPane temp = new JScrollPane(
                containerThe,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        temp.getVerticalScrollBar().setOpaque(false);
        temp.getVerticalScrollBar().setUI(null);
        temp.setBorder(null);
        temp.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        temp.setDoubleBuffered(true);
        temp.setBackground(new Color(0, 246, 146));
        this.containerThe.setLayout(new BoxLayout(containerThe, BoxLayout.PAGE_AXIS));
        containerThe.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        /*
        * scrollBar
         */
        this.scrllbrBuoc = new customScrollbar(
                temp.getVerticalScrollBar(),
                new Color(0, 246, 146),
                new Color(255, 255, 255, 255),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Background//BG_Green.png",
                                20,
                                pnlThe.getHeight())),
                5,
                20,
                pnlThe.getHeight()
        );
        scrllbrBuoc.setBounds(
                this.btnBang.getX(),
                this.pnlThe.getY(),
                20,
                this.pnlThe.getHeight());

        /*
        * 
         */
        OoOIcon = new JLabel();
        OoOIcon.setBackground(new Color(0, 0, 0, 0));
        OoOIcon.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\OoO_Green.png",
                                70,
                                100)));
        OoOIcon.setBounds(
                (this.lblBackground.getWidth()
                - OoOIcon.getIcon().getIconWidth()) / 2,
                0,
                OoOIcon.getIcon().getIconWidth(),
                OoOIcon.getIcon().getIconHeight()
        );

        btnOK = new JLabel("OK");
        btnOK.setFont(
                Tools.FontTool
                        .getFont("Open Sans Light", 30)
                        .deriveFont(Font.BOLD));
        btnOK.setForeground(Color.white);
        btnOK.setSize(100, 100);
        btnOK.setBorder(
                BorderFactory.createLineBorder(
                        Color.white,
                        5,
                        true));
        btnOK.setLocation(
                this.pnlThe.getX()
                + this.pnlThe.getWidth()
                - this.btnOK.getWidth() * 2,
                spnNam.getY()
                + (spnNam.getHeight()
                - btnOK.getHeight()) / 2
        );

        addListener();

        this.add(lblBackground);
        this.lblBackground.add(btnBang);
        this.lblBackground.add(btnNhatKi);
        this.lblBackground.add(lblNgay);
        this.lblBackground.add(lblThang);
        this.lblBackground.add(lblNam);
        this.lblBackground.add(spnNgay);
        this.lblBackground.add(spnThang);
        this.lblBackground.add(spnNam);
        this.lblBackground.add(btnMinimize);
        this.lblBackground.add(btnExit);
        this.lblBackground.add(pnlThe);
        this.lblBackground.add(scrllbrBuoc);
        this.lblBackground.add(OoOIcon);
        lblBackground.add(btnOK);
        pnlThe.add(temp);
        this.addThe();

    }

    public void addListener() {
        btnBang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBang_mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBang_mouseExited(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnBang_mouseEntered(e);
            }
        });
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
        this.btnOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnOK_mouseClicked();
            }
        });
    }

    protected void btnOK_mouseClicked() {
        this.containerThe.removeAll();
        this.listThe.clear();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, spnNgay.getValue());
        c.set(Calendar.MONTH, spnThang.getValue() - 1);
        c.set(Calendar.YEAR, spnNam.getValue());
        loadThe(new Date(c.getTimeInMillis()));
        this.addThe();
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

    protected void increaseDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, spnThang.getValue() - 1);
        c.set(Calendar.YEAR, spnThang.getValue());

        spnNgay.setMax(c.getActualMaximum(Calendar.DAY_OF_MONTH));
        spnNgay.setValue(1);
    }

    protected void loadDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));

        spnNgay.setMin(1);
        spnThang.setMin(1);
        spnNam.setMin(1);

        spnNgay.setValue(c.get(Calendar.DAY_OF_MONTH));
        spnThang.setValue(c.get(Calendar.MONTH) + 1);
        spnNam.setValue(c.get(Calendar.YEAR));

        spnNgay.setMax(c.getActualMaximum(Calendar.DAY_OF_MONTH));
        spnThang.setMax(12);
        spnNam.setMax(Integer.MAX_VALUE);
    }

    protected void addThe() {
        for (int i = 0; i < this.listThe.size(); i++) {
            this.containerThe.add(
                    new CardWithDate(
                            SQLcon,
                            listThe.get(i),
                            this.pnlThe.getWidth() - 20,
                            50));
        }
        containerThe.repaint();
    }

    protected boolean checkNgayBao(Date date, The the) throws Exception {
        ArrayList<OoO_The> listOOO_THE = ctrlOOO_THE.selectAll();
        ArrayList<OoO> ListOOO = ctrlOOO.select(date);
        ArrayList<Integer> listOOOID = new ArrayList();
        System.out.println("date =" + new SimpleDateFormat("dd-MM-yyyy").format(date));
        System.out.println("listOOO_THE =" + listOOO_THE.toString());
        System.out.println("ListOOO =" + ListOOO.toString());
        System.out.println("listOOOID =" + listOOOID.toString());
        for (OoO_The e : listOOO_THE) {
            if (e.getTHEID() == the.getTheID()) {
                if (!listOOOID.contains(e.getOOOID())) {
                    listOOOID.add(e.getOOOID());
                }
            }
        }
        System.out.println("listOOOID =" + listOOOID.toString());
        for (OoO e : ListOOO) {
            if (listOOOID.contains(e.getOOOID())) {
                return true;
            }
        }
        return false;
    }

    protected void loadThe(Date date) {
        try {
            ArrayList<The> all = ctrlThe.selectAll();
            for (int i = 0; i < all.size(); i++) {
                if (checkNgayBao(date, all.get(i))) {
                    this.listThe.add(all.get(i));
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Diary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnBang_mouseEntered(MouseEvent e) {
        this.btnBang.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                100,
                                50))
        );
        btnBang.repaint();
    }

    private void btnBang_mouseExited(MouseEvent e) {
        this.btnBang.setImage(new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        "Button\\NullBtn.png",
                        100,
                        50)));
        btnBang.repaint();
    }

    private void btnBang_mouseClicked(MouseEvent e) {
        new BoardList(this.SQLcon).setVisible(true);
        this.dispose();
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1500, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lblBackground;
    private ButtonWithImage btnBang;
    private ButtonWithImage btnNhatKi;
    private JLabel lblNgay, lblThang, lblNam;
    private mySpinner spnNgay;
    private mySpinner spnThang;
    private mySpinner spnNam;

    private JLabel btnMinimize;
    private JLabel btnExit;

    private JPanel pnlThe;
    private JPanel containerThe;
    private customScrollbar scrllbrBuoc;

    private JLabel OoOIcon;
    private JLabel btnOK;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
