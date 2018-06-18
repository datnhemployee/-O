/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.ControllerBang;
import Controller.ControllerHinh;
import Controller.ControllerMau;
import Controller.SQLConnectivity;
import GUI.RecycleBin.BangBin;
import GUI.myComponent.*;
import Model.Bang;
import Model.ConstantIndex;
import Model.SQLConnection;
import BoardComponents.BoardSnipDetail;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author DAT
 */
public class BoardList extends javax.swing.JFrame {

    private SQLConnectivity con;
    private ArrayList<BoardSnipDetail> PaneList;

    private ArrayList<Bang> listBang;
    private ControllerMau ctrlMau;
    private ControllerHinh ctrlHinh;
    private ControllerBang ctrlBang;

    /**
     * Creates new form TableList
     */
    private BoardList() {
    }

    public BoardList(SQLConnectivity con) {

        this.con = con;

        /*
        * getBang 
         */
        listBang = new ArrayList<Bang>();
        PaneList = new ArrayList();
        ctrlMau = new ControllerMau(con);
        ctrlHinh = new ControllerHinh(con);
        ctrlBang = new ControllerBang(con);
        initComponents();
        loadBang();
        design();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1500, 800));
        setSize(new java.awt.Dimension(1500, 800));

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

    private void loadBang() {
        try {

            Model.ConstantIndex.defaultInit(con);
            listBang = ctrlBang.selectAll();
            ArrayList<Bang> list = ctrlBang.selectAll();

        } catch (Exception ex) {
            new MessageBox(
                    "THÔNG BÁO",
                    ex.getMessage()
            );
        }
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
                        "Background\\BGTableList.png",
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
                new Color(210, 77, 87),
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMenu.png",
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
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\NullBtn.png",
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
        * btnThem
         */
        btnThem = new ButtonWithImage(
                "Thêm",
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnThem.setBounds(
                (this.lblBackground.getWidth() - 10
                - this.btnThem.getImage().getIconWidth()),
                this.OoOIcon.getY()
                + this.OoOIcon.getHeight() / 2 + 30,
                this.btnThem.getImage().getIconWidth(),
                this.btnThem.getImage().getIconHeight()
        );

        /*
        * btnDongBo
         */
        btnDongBo = new ButtonWithImage(
                "Đồng bộ",
                btnThem.getTextColor(),
                btnThem.getTextFont(),
                btnThem.getImage()
        );
        btnDongBo.setBounds(
                this.btnThem.getX() - 10
                - this.btnThem.getImage().getIconWidth(),
                btnThem.getY(),
                this.btnDongBo.getImage().getIconWidth(),
                this.btnDongBo.getImage().getIconHeight()
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
        * btnMinimun
         */
        btnMinimum = new JLabel();
        btnMinimum.setBackground(new Color(0, 0, 0, 0));
        btnMinimum.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMinimum.png",
                                25,
                                2)));
        btnMinimum.setBounds(
                this.btnExit.getX() - this.btnExit.getWidth() - 20,
                this.btnExit.getY()
                + (this.btnExit.getHeight()
                - btnExit.getIcon().getIconHeight()) / 2,
                btnMinimum.getIcon().getIconWidth(),
                btnExit.getIcon().getIconHeight()
        );

        /*
        * BtnSetting
         */
        btnSetting = new JLabel();
        btnSetting.setBackground(new Color(0, 0, 0, 0));
        btnSetting.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnSetting.png",
                                30,
                                30)));
        btnSetting.setBounds(
                this.btnExit.getX() - 5,
                this.btnExit.getY()
                + this.btnExit.getHeight()
                + 20,
                btnSetting.getIcon().getIconWidth(),
                btnSetting.getIcon().getIconHeight()
        );

        /*
        * List
         */
        List = new JPanel(new BorderLayout());
        List.setBackground(new Color(0, 0, 0, 0));
        List.setLocation(
                this.btnBang.getX()
                + this.btnBang.getWidth() / 2,
                this.btnThem.getY()
                + this.btnThem.getHeight()
                + 20
        );
        List.setSize(
                this.lblBackground.getWidth()
                - this.btnBang.getX()
                - this.btnBang.getWidth() / 2
                - 20,
                this.lblBackground.getHeight()
                - this.btnThem.getY()
                - this.btnThem.getHeight()
                - 40);
        container = new JPanel();
        container.setBackground(new Color(239, 72, 54));

        JScrollPane temp = new JScrollPane(
                container,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        temp.getVerticalScrollBar().setOpaque(false);
        temp.getVerticalScrollBar().setUI(null);
        temp.setBorder(null);
        temp.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        temp.setDoubleBuffered(true);
        temp.setBackground(new Color(210, 77, 87));
        this.container.setLayout(
                new GridBagLayout());
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        /*
        * scrollBar
         */
        scrollBar = new customScrollbar(
                temp.getVerticalScrollBar(),
                new Color(0, 0, 0, 255),
                new Color(255, 255, 255, 255),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Background//BGTableList.png",
                                20,
                                List.getHeight())),
                5,
                20,
                List.getHeight()
        );
        scrollBar.setBounds(
                this.btnBang.getX(),
                this.List.getY(),
                20,
                this.List.getHeight());

        /*
        * Add Listeners
         */
        addListeners();
        /*
        * Add Components
         */
        this.add(lblBackground);
        lblBackground.add(this.btnBang);
        lblBackground.add(this.btnNhatKi);
        lblBackground.add(this.OoOIcon);

        lblBackground.add(this.btnThem);
        lblBackground.add(this.btnDongBo);

        lblBackground.add(this.btnExit);
        lblBackground.add(this.btnMinimum);
        lblBackground.add(this.btnSetting);

        lblBackground.add(this.List);
        lblBackground.add(this.scrollBar);
        List.add(temp);
        addBoard();

        pack();
    }

    protected void addListeners() {
        this.btnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSetting_mouseClicked(e);
            }
        });
        this.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExit_mouseClicked(e);
            }
        });

        this.btnMinimum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMinimum_mouseClicked(e);
            }
        });

        this.btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThem_mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThem_mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnThem_mouseClicked(e);
            }
        });

        this.btnDongBo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDongBo_mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDongBo_mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnDongBo_mouseClicked(e);
            }
        });

        this.btnNhatKi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNhatKi_mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNhatKi_mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnNhatKi_mouseClicked(e);
            }
        });

    }

    private void btnSetting_mouseClicked(MouseEvent e) {
        new About().setVisible(true);
    }
    private void btnThem_mouseEntered(MouseEvent e) {
        this.btnThem.setTextColor(
                new Color(
                        Model.ConstantIndex.MAU_DO.getRed(),
                        Model.ConstantIndex.MAU_DO.getGreen(),
                        Model.ConstantIndex.MAU_DO.getBlue(),
                        Model.ConstantIndex.MAU_DO.getAlpha()
                ));
        this.btnThem.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMenu.png",
                                200,
                                50))
        );
        btnThem.repaint();
    }

    private void btnThem_mouseExited(MouseEvent e) {
        this.btnThem.setTextColor(Color.white);
        this.btnThem.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnThem.repaint();
    }

    private void btnThem_mouseClicked(MouseEvent e) {
        new BoardDetail(null,this.con).setVisible(true);
        this.dispose();
    }

    private void btnDongBo_mouseEntered(MouseEvent e) {
        this.btnDongBo.setTextColor(
                new Color(
                        Model.ConstantIndex.MAU_DO.getRed(),
                        Model.ConstantIndex.MAU_DO.getGreen(),
                        Model.ConstantIndex.MAU_DO.getBlue(),
                        Model.ConstantIndex.MAU_DO.getAlpha()
                ));
        this.btnDongBo.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMenu.png",
                                200,
                                50))
        );
        btnDongBo.repaint();
    }

    private void btnDongBo_mouseExited(MouseEvent e) {
        this.btnDongBo.setTextColor(Color.white);
        this.btnDongBo.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnDongBo.repaint();
    }

    private void btnNhatKi_mouseEntered(MouseEvent e) {
        this.btnNhatKi.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                100,
                                50))
        );
        btnNhatKi.repaint();
    }

    private void btnNhatKi_mouseExited(MouseEvent e) {
        this.btnNhatKi.setImage(new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        "Button\\NullBtn.png",
                        100,
                        50)));
        btnNhatKi.repaint();
    }

    private void btnNhatKi_mouseClicked(MouseEvent e) {
        new Diary(this.con).setVisible(true);
        this.dispose();
    }

    private void btnDongBo_mouseClicked(MouseEvent e) {
        try {
            BangBin.setCon(con);
            BangBin.wash();
            
            new BoardList(this.con).setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            new MessageBox("THÔNG BÁO KHÔNG THỂ ĐỒNG BỘ",
            "Nguyuên nhân: "+ ex).setVisible(true);
        }
    }

    public void btnExit_mouseClicked(MouseEvent e) {
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

    public void btnMinimum_mouseClicked(MouseEvent e) {
        this.setState(JFrame.ICONIFIED);
    }

    protected void addBoard() {
        if (this.listBang.isEmpty()) {
            Dimension d = getDimensionPane(0);
            this.container.add(new BoardSnipDetail(
                            this.con,
                            null,
                            new ImageIcon(
                                    Tools.ImageTool.getScaledImage(
                                            "Background\\BoardFooterDarkBlue.png",
                                            d.width,
                                            d.height)
                            )), getGridBagConstraint(0));
        } else {
            for (int i = 0; i < listBang.size(); i++) {
                Dimension d = getDimensionPane(i);
                this.container.add(new BoardSnipDetail(
                                this.con,
                                listBang.get(i),
                                getFooterImage(
                                        listBang.get(i),
                                        d.width,
                                        d.height)),
                        getGridBagConstraint(i));
            }
        }
    }

    public static GridBagConstraints getGridBagConstraint(int pos) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        switch (pos % 10) {
            case 0:
            case 6:
            case 8:
                c.gridx = 0;
                break;
            case 1:
            case 4:
            case 7:
                c.gridx = 3;
                break;
            case 5:
                c.gridx = 2;
                break;
            case 9:
                c.gridx = 1;
                break;
            case 3:
                c.gridx = 4;
                break;
            case 2:
                c.gridx = 5;
        }

//        System.out.println(
//                "Before: c.gridx = " + c.gridx);
        switch (pos % 10) {
            case 0:
            case 1:
            case 2:
                c.gridy = (pos / 10) * 7;
                break;
            case 3:
                c.gridy = (pos / 10) * 7 + 2;
                break;
            case 4:
                c.gridy = (pos / 10) * 7 + 1;
                break;
            case 9:
            case 8:
            case 7:
                c.gridy = (pos / 10) * 7 + 3;
                break;
            case 6:
                c.gridy = (pos / 10) * 7 + 4;
                break;
            case 5:
                c.gridy = (pos / 10) * 7 + 5;
        }

        c.insets = new Insets(5, 5, 5, 5);

        switch (pos % 10) {
            case 0:
            case 7:
                c.gridwidth = 3;
                c.gridheight = 3;
                break;
            case 1:
            case 6:
                c.gridwidth = 2;
                c.gridheight = 2;
                break;
            case 3:
            case 9:
                c.gridwidth = 2;
                c.gridheight = 1;
                break;
            case 5:
            case 2:
                c.gridwidth = 1;
                c.gridheight = 1;
                break;
            case 4:
            case 8:
                c.gridwidth = 1;
                c.gridheight = 2;
                break;
        }

//        System.out.println(
//                "After:c.gridx = " + c.gridx);
        return c;
    }

    public static ImageIcon getFooterImage(
            Bang info,
            int width,
            int height
    ) {
        if (info.getMauID()
                == Model.ConstantIndex.MAU_DO.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLDefaultBoardFooterRed,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_DUONG.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLDefaultBoardFooterDarkBlue,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_LA.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLDefaultBoardFooterGreen,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_TIM.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLDefaultBoardFooterPurple,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_VANG.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLDefaultBoardFooterGold,
                            width, height));
        }
        return new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        Model.ConstantIndex.URLDefaultBoardFooterGray,
                        width, height));

    }

    public static Dimension getDimensionPane(int pos) {
        Dimension d = new Dimension();
        switch (pos % 10) {
            case 0:
            case 7:
                d.width = 600;
                d.height = 175;
                break;
            case 1:
            case 6:
                d.width = 400;
                d.height = 100;
                break;
            case 3:
            case 9:
                d.width = 400;
                d.height = 70;
                break;
            case 5:
            case 2:
                d.width = 200;
                d.height = 100;
                break;
            case 4:
            case 8:
                d.width = 200;
                d.height = 70;
                break;
        }
        return d;
    }

    private JLabel lblBackground;
    private ButtonWithImage btnBang;
    private ButtonWithImage btnNhatKi;
    private JLabel OoOIcon;
    private ButtonWithImage btnThem;
    private ButtonWithImage btnDongBo;
    private JLabel btnExit;
    private JLabel btnMinimum;
    private JLabel btnSetting;

    private JPanel List;
    private customScrollbar scrollBar;
    private JPanel container;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
