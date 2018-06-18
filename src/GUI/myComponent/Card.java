/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import Controller.*;
import GUI.CardDetail;
import GUI.RecycleBin.TheBin;
import GUI.BoardDetail;
import Model.Bang;
import Model.Buoc;
import Model.ConstantIndex;
import Model.The;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAT
 */
public class Card extends javax.swing.JPanel {

    private SQLConnectivity conSQL;
    private The the;
    private ArrayList<Buoc> listBuoc;
    private Iterator IterBuoc;
    private ControllerBang ctrlBang;
    private ControllerThe ctrlThe;
    private ControllerBuoc ctrlBuoc;

    private boolean isInBin = false;

    private Card() {
    }

    public Card(
            The the,
            SQLConnectivity conSQL) {
        initComponents();

        this.conSQL = conSQL;
        ctrlBang = new ControllerBang(conSQL);
        ctrlThe = new ControllerThe(conSQL);
        ctrlBuoc = new ControllerBuoc(conSQL);

        listBuoc = new ArrayList();
        this.the = the;
        if (the != null) {
            try {
                listBuoc = ctrlBuoc.select_TheID(
                        the.getTheID());
            } catch (Exception ex) {
                ex.printStackTrace();
                new MessageBox(
                        "LỖI LẤY DỮ LIỆU THẺ",
                        "Không thể lấy dữ liệu về bước của thẻ với nội dung: "
                        + the.toString() + " Nguyên nhân :" + ex
                ).setVisible(true);
            }
        }

        design();
    }

    protected void design() {
        this.setPreferredSize(new Dimension(300, 300));
        this.setSize(new Dimension(300, 300));
        this.setBackground(new Color(0, 00, 0, 0));
        this.setOpaque(true);
        String err = "";
        lblBackground = new JLabel();
        lblBackground.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Background\\BG_The.png",
                                this.getWidth(),
                                this.getHeight())));
        lblBackground.setBackground(new Color(0, 0, 0, 0));
        lblBackground.setOpaque(true);
        lblBackground.setSize(
                lblBackground.getIcon().getIconWidth(),
                lblBackground.getIcon().getIconHeight()
        );
        lblBackground.setLocation(0, 0);

        lblTen = new JLabel("Chưa có dữ liệu");
        if (the != null) {
            lblTen.setText(the.getTen());
        }
        lblTen.setBackground(new Color(0, 0, 0, 0));
        try {
            lblTen.setForeground(BoardDetail.getColor_info(
                            ctrlBang.select(the.getBangID())));
        } catch (Exception ex) {
            lblTen.setForeground(new Color(210, 77, 87));
        }
        lblTen.setFont(
                Tools.FontTool.getFont("Cambria", 20));
        lblTen.setSize(
                lblTen.getText().length()
                * lblTen.getFont().getSize(),
                30
        );
        lblTen.setLocation(
                10, 5);

        TheBackground = new JLabel("Chưa có dữ liệu");
        TheBackground.setOpaque(true);
        if (the != null) {
            try {
                TheBackground.setBackground(BoardDetail.getColor_info(
                                ctrlBang.select(
                                        the.getBangID())
                        ));
            } catch (Exception ex) {
                err += "\n" + ex;
                ex.printStackTrace();
                TheBackground.setBackground(new Color(210, 77, 87));
            }
        }
        try {
        if (!listBuoc.isEmpty()) {
                    TheBackground.setText(
                            listBuoc.get(0).getNoiDung());
                }
        } catch (Exception ex) {
                err += "\n" + ex;
        }
        TheBackground.setFont(
                Tools.FontTool.getFont("Cambria", 20));
        TheBackground.setForeground(Color.white);
        TheBackground.setSize(
                this.lblBackground.getWidth()
                - 10,
                this.lblBackground.getHeight() / 2
        );
        TheBackground.setLocation(
                5,
                lblTen.getY()
                + lblTen.getHeight()
        );

        lblNexOOO = new JLabel("Chưa có dữ liệu");
        if (the != null) {
            try {
                if (the != null) {
                    if (the.getThoiGianBao_KeTiep() != null) {
                        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
                        lblNexOOO.setText(
                                sf.format(the.getThoiGianBao_KeTiep()));
                    }
                }
            } catch (Exception ex) {
                err += "\n" + ex;
            }
        }
        lblNexOOO.setFont(
                Tools.FontTool.getFont("Cambria", 20));
        lblNexOOO.setForeground(Color.white);
        lblNexOOO.setSize(
                this.TheBackground.getWidth(),
                50
        );
        lblNexOOO.setLocation(5, 5);

        lblIconDone = new JLabel();
        lblIconDone.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\IconDoneWhite.png",
                                20,
                                20)));
        lblIconDone.setSize(
                this.lblIconDone.getIcon().getIconWidth(),
                this.lblIconDone.getIcon().getIconHeight()
        );
        lblIconDone.setLocation(
                5,
                TheBackground.getHeight()
                - lblIconDone.getHeight()
                - 5
        );

        lblDone = new JLabel(" 0 / 0");
        if (this.the != null) {
            lblDone.setText(
                    the.getSoBuocHoanTat()
                    + " / "
                    + the.getTongSoBuoc());
        }
        lblDone.setForeground(Color.white);
        lblDone.setSize(
                50,
                30
        );
        lblDone.setLocation(
                lblIconDone.getX()
                + lblIconDone.getWidth()
                + 5,
                TheBackground.getHeight()
                - lblIconDone.getHeight()
                - 5
        );

        lblDeadline = new JLabel("Chưa có dữ liệu");
        try {
            lblDeadline.setForeground(BoardDetail.getColor_info(
                            ctrlBang.select(the.getBangID())));
        } catch (Exception ex) {
            lblDeadline.setForeground(new Color(210, 77, 87));
        }
        if(the!=null){
            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
            this.lblDeadline.setText(sf.format(the.getHanChot()));
        }
        lblDeadline.setFont(
                Tools.FontTool.getFont("Cambria", 15));
        if (the != null) {
            // Thoi gian còn lại
        }
        lblDeadline.setSize(
                this.lblBackground.getWidth() - 50,
                20
        );
        lblDeadline.setLocation(
                this.TheBackground.getX(),
                this.TheBackground.getY()
                + this.TheBackground.getHeight()
                + 5
        );

        try {
            btnXoa = new ButtonWithImage(
                    " Xoá ",
                    Color.white,
                    Tools.FontTool.getFont("Open Sans Light", 20),
                    ConstantIndex.getButton(
                            ctrlBang.select(the.getBangID()),
                            this.lblBackground.getWidth() / 2 
                                    - 10,
                            50));
        } catch (Exception ex) {
            btnXoa = new ButtonWithImage(
                    " Xoá ",
                    Color.white,
                    Tools.FontTool.getFont("Open Sans Light", 20),
                    ConstantIndex.getButton(
                            new Bang(1,"","",1),
                            this.lblBackground.getWidth() / 2
                            - 10,
                            50));
        }
        btnXoa.setSize(
                btnXoa.Image.getIconWidth(),
                btnXoa.Image.getIconHeight()
        );
        btnXoa.setLocation(
                5,
                this.lblBackground.getHeight()
                - this.btnXoa.getHeight()
                - 10
        );

        try {
            btnSua = new ButtonWithImage(
                    " Sửa ",
                    Color.white,
                    Tools.FontTool.getFont("Open Sans Light", 20),
                    ConstantIndex.getButton(
                            ctrlBang.select(the.getBangID()),
                            this.lblBackground.getWidth() / 2
                            - 10 ,
                            50));
        } catch (Exception ex) {
            btnSua = new ButtonWithImage(
                    " Sửa ",
                    Color.white,
                    Tools.FontTool.getFont("Open Sans Light", 20),
                    ConstantIndex.getButton(
                            new Bang(1,"","",1),
                            this.lblBackground.getWidth() / 2
                            - 10,
                            50));
        }
        btnSua.setSize(
                btnSua.Image.getIconWidth(),
                btnSua.Image.getIconHeight()
        );
        btnSua.setLocation(
                btnXoa.getX()
                + btnXoa.getWidth()
                + 5,
                btnXoa.getY()
        );

        addListeners();

        this.add(this.lblBackground);
        lblBackground.add(lblTen);
        lblBackground.add(TheBackground);
        TheBackground.add(this.lblNexOOO);
        TheBackground.add(this.lblDone);
        TheBackground.add(this.lblIconDone);
        lblBackground.add(this.lblDeadline);

        lblBackground.add(this.btnSua);
        lblBackground.add(this.btnXoa);

        if (!err.isEmpty()) {
            new MessageBox("LỖI CHƯƠNG TRÌNH", err);
        }
    }

    protected void addListeners() {
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnXoa_mouseClicked(e);

            }
        });
        
        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSua_mouseClicked(e);

            }
        });
    }

    protected void btnXoa_mouseClicked(MouseEvent e) {
        isInBin = !isInBin;
        if (isInBin) {
            btnXoa.setText("Đã chọn xoá");
            btnXoa.setForeground(new Color(191, 191, 191));
            btnXoa.repaint();
            
            TheBin.setCon(conSQL);
            TheBin.throwToBin(the);
        } else {
            btnXoa.setText("Xoá");
            btnXoa.setForeground(Color.white);
            btnXoa.repaint();
            
            TheBin.setCon(conSQL);
            TheBin.restore(the.getTheID());
        }
    }
     protected void btnSua_mouseClicked(MouseEvent e) {
        if (this.the != null) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            new CardDetail( conSQL, the,the.getBangID()).setVisible(true);
            topFrame.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lblBackground;
    private ButtonWithImage btnXoa;
    private ButtonWithImage btnSua;

    private JLabel TheBackground;
    private JLabel lblNexOOO;

    private JLabel lblIconDone;
    private JLabel lblDone;

    private JLabel lblDeadline;

    private JLabel lblTen;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
