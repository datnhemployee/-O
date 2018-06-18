/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.*;
import GUI.RecycleBin.TheBin;
import GUI.myComponent.*;
import Model.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author DAT
 */
public class BoardDetail extends javax.swing.JFrame {

    private SQLConnectivity con;
    private Bang info;
    private ArrayList<The> listThe;
    private ControllerThe ctrlThe;
    private ControllerBang ctrlBang;
    private ControllerBuoc ctrlBuoc;
    private ControllerOOO ctrlOOO;
    private ControllerOOO_THE ctrlOOO_THE;

    /**
     * Creates new form CardList
     */
    private BoardDetail() {
    }

    public BoardDetail(Bang bang, SQLConnectivity con) {
        info = bang;
        this.con = con;
        listThe = new ArrayList();

        setCon(con);

        loadListThe();
        initComponents();
        design();
    }

    protected void setCon(SQLConnectivity con) {

        ctrlThe = new ControllerThe(con);
        ctrlBang = new ControllerBang(con);
        ctrlBuoc = new ControllerBuoc(con);
        ctrlOOO = new ControllerOOO(con);
        ctrlOOO_THE = new ControllerOOO_THE(con);

    }

    private void design() {
        this.setIconImage(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLDefaultOoO,
                                50,
                                70));
        this.setBackground(new Color(0, 0, 0, 0));
        /*
        * lblBackground
         */
        lblBackground = new JLabel();
        lblBackground.setSize(this.getSize());
        lblBackground.setLocation(0, 0);
        lblBackground.setBackground(new Color(0, 0, 0, 0));
        lblBackground.setIcon(
                getBackgroundColor(
                        this.info,
                        this.getWidth(),
                        this.getHeight()));

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

        /*
        * lblChiTiet
         */
        this.lblChiTiet = new JLabel();
        this.lblChiTiet.setText("CHI TIẾT");
        this.lblChiTiet.setForeground(Color.WHITE);
        this.lblChiTiet.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        40)
                        .deriveFont(Font.BOLD));
        this.lblChiTiet.setLocation(
                BtnBack.getX()
                + BtnBack.getWidth()
                + 20,
                BtnBack.getY());
        this.lblChiTiet.setSize(
                lblChiTiet.getText().length()
                * lblChiTiet.getFont().getSize(),
                BtnBack.getHeight());
        /*
        * lblOOO
         */
        lblOOO = new JLabel();
        this.lblOOO.setIcon(
                getOOO(this.info,
                        80,
                        100));
        this.lblOOO.setSize(
                lblOOO.getIcon().getIconWidth(),
                lblOOO.getIcon().getIconHeight());
        this.lblOOO.setLocation(
                (this.lblBackground.getWidth()
                - lblOOO.getWidth()) / 2,
                5);

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

        /*
        * BtnSetting
         */
        BtnSetting = new JLabel();
        BtnSetting.setBackground(new Color(0, 0, 0, 0));
        BtnSetting.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnSetting.png",
                                30,
                                30)));
        BtnSetting.setBounds(
                this.BtnExit.getX() - 5,
                this.BtnExit.getY()
                + this.BtnExit.getHeight()
                + 20,
                BtnSetting.getIcon().getIconWidth(),
                BtnSetting.getIcon().getIconHeight()
        );

        /*
        * lblTitle
         */
        txtTitle = new JTextArea();
        txtTitle.setText("Chưa có tên ");
        if (info != null) {
            txtTitle.setText(info.getTen());
        }
        try {
            txtTitle.setBackground(
                    getColor_info(info));
        } catch (Exception e) {
            txtTitle.setBackground(new Color(210, 77, 87));
        }
        this.txtTitle.setFont(
                Tools.FontTool.getFont(
                        "Open Sans Light",
                        40)
                        .deriveFont(Font.BOLD));
        txtTitle.setForeground(Color.WHITE);
        txtTitle.setSize(
                txtTitle.getText().length()
                * txtTitle.getFont().getSize(),
                lblChiTiet.getHeight());
        txtTitle.setLocation(
                BtnBack.getX(),
                BtnBack.getY()
                + 100
        );

        /*
        * pnlDetail
         */
        Detail = new JPanel(new BorderLayout());
        Detail.setSize(
                this.lblBackground.getWidth() / 2,
                lblBackground.getHeight() / 4);
        Detail.setLocation(
                txtTitle.getX()
                + 10,
                txtTitle.getY()
                + txtTitle.getHeight()
                + 10
        );

        /*
        * txtDetail
         */
        this.txtDetail = new JTextArea();
        this.txtDetail.setFont(
                Tools.FontTool.getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        this.txtDetail.setLineWrap(true);
        this.txtDetail.setWrapStyleWord(true);
        this.txtDetail.setForeground(Color.WHITE);
        this.txtDetail.setBackground(
                getColor_info(this.info));
        String MoTaChiTiet = "Nội dung \n Chưa có dữ liệu.";
        if (info != null) {
            MoTaChiTiet = info.getMoTa();
        }
        txtDetail.setText(MoTaChiTiet);
        this.txtDetail.setAlignmentX(LEFT_ALIGNMENT);
        this.txtDetail.setEditable(true);
        /*
        * ContainerDetail
         */
        ContainerDetail = new JPanel();
        ContainerDetail.setBackground(new Color(0, 0, 0, 0));

        JScrollPane temp1 = new JScrollPane(
                ContainerDetail,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        temp1.getVerticalScrollBar().setOpaque(false);
        temp1.getVerticalScrollBar().setUI(null);
        temp1.setBorder(null);
        temp1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        temp1.setDoubleBuffered(true);
        temp1.setBackground(getColor_info(info));
        Detail.add(temp1);

        this.ContainerDetail.setLayout(
                new BoxLayout(ContainerDetail, BoxLayout.Y_AXIS));
        this.ContainerDetail.add(this.txtDetail);

        scrllBarDetail = new customScrollbar(
                temp1.getVerticalScrollBar(),
                getColor_info(info),
                new Color(255, 255, 255, 255),
                null,
                5,
                20,
                this.Detail.getHeight()
        );
        scrllBarDetail.setSize(
                this.scrllBarDetail.getWidth(),
                this.Detail.getHeight());

        scrllBarDetail.setLocation(
                new Point(
                        this.Detail.getX()
                        - this.scrllBarDetail.getWidth()
                        - 5,
                        this.Detail.getY()));

        /*
        * lblMau
         */
        Mau = new JLabel();
        Mau.setText("Màu:");
        this.Mau.setFont(
                Tools.FontTool.getFont("Open Sans Light", 30)
                        .deriveFont(Font.BOLD));
        this.Mau.setForeground(Color.WHITE);
        Mau.setLocation(
                this.Detail.getX()
                + Detail.getWidth()
                + 5,
                this.Detail.getY());
        Mau.setSize(
                Mau.getText().length()
                * 30,
                40);
        /*
         * JLable Red,Blue,Green,Yellow,Purple
         */
        Red = new JLabel();
        Blue = new JLabel();
        Green = new JLabel();
        Yellow = new JLabel();
        Purple = new JLabel();

        Red.setSize(100, 70);
        Blue.setSize(
                Red.getWidth(),
                Red.getHeight());
        Green.setSize(
                Red.getWidth(),
                Red.getHeight());
        Yellow.setSize(
                Red.getWidth(),
                Red.getHeight());
        Purple.setSize(
                Red.getWidth(),
                Red.getHeight());

        Red.setLocation(
                Mau.getX()
                + Mau.getWidth() / 2,
                Mau.getY()
                + Mau.getHeight()
                + 5
        );
        Blue.setLocation(
                Red.getX()
                + Red.getWidth() * 3 / 2
                + 10,
                Red.getY()
        );
        Green.setLocation(
                Blue.getX()
                + Blue.getWidth() * 3 / 2
                + 10,
                Red.getY()
        );
        Yellow.setLocation(
                Red.getX(),
                Red.getY()
                + Red.getHeight()
                + 10
        );
        Purple.setLocation(
                Yellow.getX()
                + Yellow.getWidth() * 3 / 2
                + 10,
                Yellow.getY()
        );
        chooseBackground();

        Red.setOpaque(true);
        Blue.setOpaque(true);
        Green.setOpaque(true);
        Yellow.setOpaque(true);
        Purple.setOpaque(true);

        Red.setBackground(
                new Color(
                        210, 77, 87
                ));
        Blue.setBackground(
                new Color(
                        0, 32, 96
                ));
        Green.setBackground(
                new Color(
                        27, 188, 155
                ));
        Yellow.setBackground(
                new Color(
                        244, 208, 63
                ));
        Purple.setBackground(
                new Color(
                        122, 48, 160
                ));;

        /*
        *  lblThe
         */
        lblThe = new JLabel();
        lblThe.setText("Thẻ");
        this.lblThe.setFont(
                Tools.FontTool.getFont("Open Sans Light", 40)
                        .deriveFont(Font.BOLD));
        this.lblThe.setForeground(Color.WHITE);
        lblThe.setSize(
                Mau.getText().length() * 30,
                40);
        lblThe.setLocation(
                Detail.getX(),
                Detail.getY()
                + Detail.getHeight()
                + 5
        );

        /*
        * BtnThem,Luu,Xoa
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
                this.lblThe.getX(),
                lblThe.getY()
                + lblThe.getHeight()
                + 5,
                this.btnThem.getImage().getIconWidth(),
                this.btnThem.getImage().getIconHeight()
        );

        btnLuu = new ButtonWithImage(
                "Lưu",
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnLuu.setBounds(
                btnThem.getX()
                + btnThem.getWidth()
                + 100,
                btnThem.getY(),
                this.btnLuu.getImage().getIconWidth(),
                this.btnLuu.getImage().getIconHeight()
        );

        btnXoa = new ButtonWithImage(
                "Xoá bảng",
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 26)
                        .deriveFont(Font.BOLD),
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnXoa.setBounds(
                btnLuu.getX()
                + btnLuu.getWidth()
                + 100,
                btnThem.getY(),
                this.btnXoa.getImage().getIconWidth(),
                this.btnXoa.getImage().getIconHeight()
        );

        /*
        * pnlThe
         */
        pnlThe = new JPanel(new BorderLayout());
        pnlThe.setBackground(new Color(0, 0, 0, 0));
        pnlThe.setSize(
                this.lblBackground.getWidth()
                - 100,
                lblBackground.getHeight()
                - btnThem.getY()
                - btnThem.getHeight()
                - 20
        );
        pnlThe.setLocation(
                lblThe.getX(),
                btnThem.getY()
                + btnThem.getHeight()
                + 5
        );

        /*
        * ContainerThe
         */
        ContainerThe = new JPanel();
        ContainerThe.setBackground(
                getColor_info(info));

        JScrollPane temp2 = new JScrollPane(
                ContainerThe,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        temp2.getVerticalScrollBar().setOpaque(false);
        temp2.getVerticalScrollBar().setUI(null);
        temp2.setBorder(null);
        temp2.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        temp2.setDoubleBuffered(true);
        temp2.setBackground(getColor_info(info));
        pnlThe.add(temp2);
        this.ContainerThe.setLayout(
                new GridBagLayout());
        ContainerThe.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addCards();

        scrllBarCards = new customScrollbar(
                temp2.getVerticalScrollBar(),
                getColor_info(info),
                new Color(255, 255, 255, 255),
                null,
                5,
                20,
                this.pnlThe.getHeight()
        );

        scrllBarCards.setSize(
                this.scrllBarCards.getWidth(),
                this.pnlThe.getHeight());

        scrllBarCards.setLocation(
                new Point(
                        this.pnlThe.getX()
                        - this.scrllBarCards.getWidth()
                        - 5,
                        this.pnlThe.getY()));

        addListeners();
        /*
        * Add Components
         */
        this.add(this.lblBackground);
        lblBackground.add(lblIconBack);
        lblBackground.add(BtnBack);
        lblBackground.add(lblChiTiet);
        lblBackground.add(this.lblOOO);
        lblBackground.add(this.BtnExit);
        lblBackground.add(this.BtnSetting);
        lblBackground.add(this.BtnMinimize);
        lblBackground.add(this.txtTitle);
        lblBackground.add(Detail);
        this.lblBackground.add(this.scrllBarDetail);
        lblBackground.add(Mau);
        lblBackground.add(Red);
        lblBackground.add(Blue);
        lblBackground.add(Green);
        lblBackground.add(Yellow);
        lblBackground.add(Purple);
        lblBackground.add(lblThe);
        lblBackground.add(btnLuu);
        lblBackground.add(btnThem);
        lblBackground.add(btnXoa);
        lblBackground.add(pnlThe);
        lblBackground.add(scrllBarCards);
    }

    protected void addListeners() {
        this.BtnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSetting_mouseClicked(e);
            }
        });
        this.Blue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blue_mouseClicked(e);
            }
        });
        this.Yellow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                yellow_mouseClicked(e);
            }
        });
        this.Purple.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                purple_mouseClicked(e);
            }
        });
        this.Green.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                green_mouseClicked(e);
            }
        });
        this.Red.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                red_mouseClicked(e);
            }
        });
        this.btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThem_mouseEntered(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnThem_mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThem_mouseExited(e);
            }
        });
        this.btnLuu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLuu_mouseEntered(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnLuu_mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLuu_mouseExited(e);
            }
        });
        this.btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnXoa_mouseEntered(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnXoa_mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnXoa_mouseExited(e);
            }
        });
        this.BtnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBack_mouseClicked(e);
            }
        });
        this.lblIconBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnBack_mouseClicked(e);
            }
        });

        this.BtnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExit_mouseClicked(e);
            }
        });
        this.BtnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMinimize_mouseClicked(e);
            }
        });
    }

    private void btnExit_mouseClicked(MouseEvent e) {
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

    private void btnMinimize_mouseClicked(MouseEvent e) {
        this.setState(JFrame.ICONIFIED);
    }

    private void resetBackground() {
        this.Red.setBorder(null);
        this.Blue.setBorder(null);
        this.Purple.setBorder(null);
        this.Yellow.setBorder(null);
        this.Green.setBorder(null);

    }

    private void blue_mouseClicked(MouseEvent e) {
        resetBackground();
        this.Blue.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    private void red_mouseClicked(MouseEvent e) {
        resetBackground();
        this.Red.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    private void yellow_mouseClicked(MouseEvent e) {
        resetBackground();
        this.Yellow.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    private void purple_mouseClicked(MouseEvent e) {
        resetBackground();
        this.Purple.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    private void green_mouseClicked(MouseEvent e) {
        resetBackground();
        this.Green.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    private void btnThem_mouseEntered(MouseEvent e) {
        if (this.info != null) {
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
    }

    private void btnThem_mouseExited(MouseEvent e) {
        if (this.info != null) {
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
    }

    private void btnThem_mouseClicked(MouseEvent e) {
        if (this.info != null) {
            new CardDetail(
                    this.con,
                    null,
                    this.info.getBangID())
                    .setVisible(true);
            this.dispose();
        }
    }

    private void btnLuu_mouseEntered(MouseEvent e) {
        this.btnLuu.setTextColor(
                new Color(
                        Model.ConstantIndex.MAU_DO.getRed(),
                        Model.ConstantIndex.MAU_DO.getGreen(),
                        Model.ConstantIndex.MAU_DO.getBlue(),
                        Model.ConstantIndex.MAU_DO.getAlpha()
                ));
        this.btnLuu.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnMenu.png",
                                200,
                                50))
        );
        btnLuu.repaint();
    }

    private void btnLuu_mouseExited(MouseEvent e) {
        this.btnLuu.setTextColor(Color.white);
        this.btnLuu.setImage(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnThemXoaSua.png",
                                200,
                                50))
        );
        btnLuu.repaint();
    }

    private void btnLuu_mouseClicked(MouseEvent e) {
        if (this.info != null) {
            try {
                Bang nextOne = new Bang(
                        info.getBangID(),
                        this.txtTitle.getText(),
                        this.txtDetail.getText(),
                        this.getMauID().getMauID()
                );
                ctrlBang.update(nextOne);

                TheBin.setCon(con);
                TheBin.wash();
                new BoardDetail(nextOne, this.con).setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                new MessageBox(
                        "LỖI CẬP NHẬT THẺ",
                        "Không thể cập nhật thẻ vì: "
                        + ex)
                        .setVisible(true);
            }
        } else {
            Bang nextOne;
            try {
                nextOne = new Bang(
                        ctrlBang.getNextID(),
                        this.txtTitle.getText(),
                        this.txtDetail.getText(),
                        this.getMauID().getMauID()
                );
                ctrlBang.insert(nextOne);

            } catch (Exception ex) {
                new MessageBox(
                        "LỖI THÊM THẺ",
                        "Không thể thêm thẻ vì: "
                        + ex)
                        .setVisible(true);
            }
        }
    }

    private void btnXoa_mouseEntered(MouseEvent e) {
        if (this.info != null) {
            this.btnXoa.setTextColor(
                    new Color(
                            Model.ConstantIndex.MAU_DO.getRed(),
                            Model.ConstantIndex.MAU_DO.getGreen(),
                            Model.ConstantIndex.MAU_DO.getBlue(),
                            Model.ConstantIndex.MAU_DO.getAlpha()
                    ));
            this.btnXoa.setImage(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Button\\BtnMenu.png",
                                    200,
                                    50))
            );
            btnXoa.repaint();
        }
    }

    private void btnXoa_mouseExited(MouseEvent e) {
        if (this.info != null) {
            this.btnXoa.setTextColor(Color.white);
            this.btnXoa.setImage(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Button\\BtnThemXoaSua.png",
                                    200,
                                    50))
            );
            btnThem.repaint();
        }
    }

    private void btnXoa_mouseClicked(MouseEvent e) {
        if (this.info != null) {
            try {
                ctrlBang.delete(info.getBangID());
                new BoardList(this.con).setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                new MessageBox("LỖI XOÁ BẢNG", "Không thể xoá bảng vì " + ex);
            }
        }
    }

    protected void btnBack_mouseClicked(MouseEvent e) {
        new BoardList(con).setVisible(true);
        this.dispose();
    }

    protected void loadListThe() {
        try {
            if (info != null) {

                listThe = ctrlThe.select_BangID(
                        this.info.getBangID());
            }
        } catch (Exception ex) {
            new MessageBox("LỖI KHÔNG THỂ LẤY THẺ",
                    "Xuất hiện lỗi không thể lấy thẻ"
                    + "\n Nguyên nhân: " + ex
            ).setVisible(true);

        }
    }

    private void addCards() {
        if (!this.listThe.isEmpty()) {
            for (int i = 0; i < listThe.size(); i++) {
                this.ContainerThe.add(
                        new Card(
                                listThe.get(i),
                                this.con),
                        getGridBagConstraint(i));
            }
        }
    }

    protected void chooseBackground() {
        if (info != null) {
            Color c = getColor_info(info);
            if (c == this.Blue.getBackground()) {
                Blue.setBorder(BorderFactory.createLineBorder(Color.white));
            } else if (c == this.Purple.getBackground()) {
                Purple.setBorder(BorderFactory.createLineBorder(Color.white));
            } else if (c == this.Yellow.getBackground()) {
                Yellow.setBorder(BorderFactory.createLineBorder(Color.white));
            } else if (c == this.Green.getBackground()) {
                Green.setBorder(BorderFactory.createLineBorder(Color.white));
            } else {
                Red.setBorder(BorderFactory.createLineBorder(Color.white));
            }
        } else{
            Red.setBorder(BorderFactory.createLineBorder(Color.white));
        }
    }

    public GridBagConstraints getGridBagConstraint(int pos) {
//        System.out.println(
//                "pos = " + pos);
//        System.out.println(
//                "pos% 10 = " + (pos%10));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        c.gridx = pos % 3;

//        System.out.println(
//                "Before: c.gridx = " + c.gridx);
        c.gridy = pos / 3;

        c.insets = new Insets(5, 5, 5, 5);

        c.gridwidth = 1;
        c.gridheight = 1;

//        System.out.println(
//                "After:c.gridx = " + c.gridx);
        return c;
    }

    public static ImageIcon getBackgroundColor(Bang info, int witdh, int height) {
        if (info == null
                || info.getMauID() == ConstantIndex.MAU_DO.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLBackground_TableDetail_Red,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_DUONG.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLBackground_TableDetail_Blue,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_LA.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLBackground_Green,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_TIM.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLBackground_Purple,
                    witdh,
                    height));
        }
        return new ImageIcon(Tools.ImageTool.getScaledImage(
                ConstantIndex.URLBackground_Yellow,
                witdh,
                height));

    }

    private static ImageIcon getOOO(Bang info, int witdh, int height) {
        if (info == null
                || info.getMauID() == ConstantIndex.MAU_DO.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLDefaultOoO,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_DUONG.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLOOO_Blue,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_LA.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLOOO_Green,
                    witdh,
                    height));
        }
        if (info.getMauID() == ConstantIndex.MAU_TIM.getMauID()) {
            return new ImageIcon(Tools.ImageTool.getScaledImage(
                    ConstantIndex.URLOOO_Purple,
                    witdh,
                    height));
        }
        return new ImageIcon(Tools.ImageTool.getScaledImage(
                ConstantIndex.URLOOO_Gold,
                witdh,
                height));

    }

    public static Mau getMau_info(Bang info) {

        if (info == null
                || info.getMauID()
                == ConstantIndex.MAU_DO.getMauID()) {
            return ConstantIndex.MAU_DO;
        }
        if (info.getMauID()
                == ConstantIndex.MAU_DUONG.getMauID()) {
            return ConstantIndex.MAU_DUONG;
        }
        if (info.getMauID()
                == ConstantIndex.MAU_LA.getMauID()) {
            return ConstantIndex.MAU_LA;
        }
        if (info.getMauID()
                == ConstantIndex.MAU_TIM.getMauID()) {
            return ConstantIndex.MAU_TIM;
        }
        return ConstantIndex.MAU_VANG;
    }

    public static Color getColor_info(Bang info) {

        Mau mau = getMau_info(info);
        return new Color(
                mau.getRed(),
                mau.getGreen(),
                mau.getBlue(),
                mau.getAlpha()
        );
    }

    private void btnSetting_mouseClicked(MouseEvent e) {
        new About().setVisible(true);
    }
    protected Mau getMauID() {
        if (Red.getBorder() != null) {
            return ConstantIndex.MAU_DO;
        }
        if (Green.getBorder() != null) {
            return ConstantIndex.MAU_LA;
        }
        if (Yellow.getBorder() != null) {
            return ConstantIndex.MAU_VANG;
        }
        if (Purple.getBorder() != null) {
            return ConstantIndex.MAU_TIM;
        }
        return ConstantIndex.MAU_DUONG;
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
    private JLabel lblIconBack;
    private JLabel BtnBack;
    private JLabel lblChiTiet;
    private JLabel lblOOO;
    private JLabel BtnMinimize;
    private JLabel BtnExit;
    private JLabel BtnSetting;
    private JTextArea txtTitle;
    private JTextArea txtDetail;
    private customScrollbar scrllBarDetail;

    private JLabel Mau;

    private JLabel Red;
    private JLabel Blue;
    private JLabel Green;
    private JLabel Yellow;
    private JLabel Purple;

    private JPanel Detail;
    private JPanel ContainerDetail;

    private ButtonWithImage btnLuu;
    private ButtonWithImage btnThem;
    private ButtonWithImage btnXoa;

    private JLabel lblThe;

    private JPanel pnlThe;
    private JPanel ContainerThe;
    private customScrollbar scrllBarCards;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
