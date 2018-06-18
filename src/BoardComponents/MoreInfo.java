/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardComponents;

import Controller.*;
import GUI.Login;
import GUI.RecycleBin.BangBin;
import GUI.BoardDetail;
import GUI.myComponent.TitleWithIconLine;
import Model.Bang;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;

/**
 *
 * @author DAT
 */
public class MoreInfo extends javax.swing.JPanel {

    private SQLConnectivity conSQL;
    private ControllerBang ctrlBang;
    private Bang info;
    private boolean show = true;
    private boolean isDeleting = false;

    private MoreInfo() {
    }

    /**
     * Creates new form panel2
     */
    public MoreInfo(SQLConnectivity conSQL, Bang bang, int width, int height) {
        initComponents();
        this.conSQL = conSQL;
        info = bang;

        ctrlBang = new ControllerBang(this.conSQL);
        design(width, height);
    }

    private void design(int width, int height) {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(
                width,
                height);

        this.setOpaque(false);

        this.lblBackground.setBackground(new Color(0, 0, 0, 0));
        lblBackground.setSize(width, height);

        /*
        * Icon
         */
        Icon = new JLabel();

        Icon.setLocation(10, 10);
        Icon.setSize(
                50,
                60);

        /*
        * Line
         */
        Line = new JLabel();

        Line.setSize(
                lblBackground.getWidth(),
                1);
        Line.setLocation(
                Icon.getWidth()
                + Icon.getX(),
                Icon.getY()
                + Icon.getHeight() / 2
                - 5
        );


        /*
        * Title
         */
        Title = new JLabel("NỘI DUNG");
        Title.setFont(
                Tools.FontTool.getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD));
        Title.setForeground(new Color(0, 0, 0, 0));
        Title.setLocation(
                Icon.getWidth()
                + Icon.getX(),
                Icon.getY()
                + Icon.getHeight() / 2
                + 5);
        Title.setSize(
                this.lblBackground.getWidth(),
                20);

        /*
        * Content
         */
        Content = new JLabel();
        Content.setFont(Tools.FontTool.getFont("Cambria", 20));
        Content.setLocation(
                this.Icon.getX(),
                this.Icon.getHeight()
                + this.Icon.getY()
        );
        Content.setHorizontalTextPosition(SwingConstants.CENTER);
        Content.setVerticalTextPosition(SwingConstants.CENTER);
        Content.setSize(
                this.lblBackground.getWidth(),
                this.lblBackground.getHeight() / 2
        );

        /*
        * btnEdit
         */
        btnEdit = new JLabel();
        btnEdit.setBackground(new Color(0, 0, 0, 0));
        btnEdit.setIcon(null);
        btnEdit.setSize(
                50 + this.getWidth() / 50,
                30 + this.getWidth() / 50);
        btnEdit.setLocation(
                Content.getX(),
                this.lblBackground.getHeight()
                - 10 - btnEdit.getHeight()
        );


        /*
        * btnChkDeleting
         */
        btnChkDeleting = new JLabel();
        btnChkDeleting.setBackground(new Color(0, 0, 0, 0));
        btnChkDeleting.setIcon(null);
        btnChkDeleting.setSize(
                50 + this.getWidth() / 50,
                30 + this.getWidth() / 50);
        btnChkDeleting.setLocation(
                this.lblBackground.getWidth()
                - 10 - btnChkDeleting.getWidth(),
                btnEdit.getY()
        );

        /*
        * Add Components
         */
        lblBackground.add(this.Content);
        lblBackground.add(this.Title);
        lblBackground.add(this.btnChkDeleting);
        lblBackground.add(this.btnEdit);
        lblBackground.add(Icon);
        lblBackground.add(Line);

        addListeners();

    }

    protected void addListeners() {
        this.btnEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Edit_MouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Edit_MouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Edit_MouseLeaved(e);
            }
        });

        this.btnChkDeleting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChkDeleting_MouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ChkDeleting_MouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ChkDeleting_MouseLeaved(e);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Detail_MouseClicked(e);
            }

        });

    }

    public void Edit_MouseClicked(MouseEvent e) {
        if (this.info != null) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            new BoardDetail(info, conSQL).setVisible(true);
            topFrame.dispose();
        }
    }

    public void ChkDeleting_MouseClicked(MouseEvent e) {
        isDeleting = !isDeleting;
        if (!show) {
            if (isDeleting) {
                this.btnChkDeleting.setIcon(new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\Delete_White.png",
                                50 + this.getWidth() / 50,
                                30 + this.getWidth() / 50)));
                btnChkDeleting.repaint();
                BangBin.setCon(conSQL);
                BangBin.throwToBin(info);
            } else {
                this.btnChkDeleting.setIcon(new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\Delete_White.png",
                                50 + this.getWidth() / 50,
                                30 + this.getWidth() / 50)));
                btnChkDeleting.repaint();
                BangBin.setCon(conSQL);
                BangBin.restore(info.getBangID());
            }
        }
    }

    protected void ChkDeleting_MouseLeaved(MouseEvent e) {
        if (!show) {
            if (!isDeleting) {
                this.btnChkDeleting.setIcon(new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\Delete_White.png",
                                50 + this.getWidth() / 50,
                                30 + this.getWidth() / 50)));
                btnChkDeleting.repaint();
            }
        }
    }

    protected void ChkDeleting_MouseEntered(MouseEvent e) {
        if (!show) {
            if (!isDeleting) {
                this.btnChkDeleting.setIcon(new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\DeleteFullfill_Red.png",
                                50 + this.getWidth() / 50,
                                30 + this.getWidth() / 50)));
                btnChkDeleting.repaint();
            }
        }
    }

    protected void Edit_MouseEntered(MouseEvent e) {
        if (!show) {
            this.btnEdit.setIcon(new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            "Icon\\EditFullfill_Red.png",
                            50 + this.getWidth() / 50,
                            30 + this.getWidth() / 50)));
            btnEdit.repaint();
        }
    }

    protected void Edit_MouseLeaved(MouseEvent e) {
        if (!show) {
            this.btnEdit.setIcon(new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            "Icon\\Edit_White.png",
                            50 + this.getWidth() / 50,
                            30 + this.getWidth() / 50)));
            btnEdit.repaint();
        }
    }

    protected void Detail_MouseClicked(MouseEvent event) {
        if (show) {
            String Text = "Chưa có nội dung";
            if (info != null) {
                Text = "<html>"
                        + "<head>"
                        + "<style>"
                        + ".container .content{"
                        + "color: \"white\""
                        + "}"
                        + "</style>"
                        + "</head>"
                        + "<body>"
                        + "<div class= \"container\">"
                        + "<div class = \"content\">"
                        + "<br/>"
                        + info.getMoTa().replace("\n",
                                "<br/>")
                        + "</div>"
                        + "</div>"
                        + "</body>"
                        + "</html>";
            }
            this.Content.setText(Text);
            btnChkDeleting.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Icon\\Delete_White.png",
                                    50 + this.getWidth() / 50,
                                    30 + this.getWidth() / 50)));
            btnEdit.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "Icon\\Edit_White.png",
                                    50 + this.getWidth() / 50,
                                    30 + this.getWidth() / 50)));
            Line.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "\\Line\\White.png",
                                    this.lblBackground.getWidth()
                                    - this.Icon.getWidth() - 30,
                                    1)));
            Icon.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    "\\Icon\\Content_White.png",
                                    50,
                                    60)));
            this.Title.setForeground(Color.white);
            this.lblBackground.setBackground(new Color(0, 0, 0, 189));
            this.setBackground(new Color(0, 0, 0, 255));
        } else {
            this.Content.setText("");
            this.btnChkDeleting.setIcon(null);
            this.Line.setIcon(null);
            this.Icon.setIcon(null);
            this.Title.setForeground(new Color(0, 0, 0, 0));
            this.btnEdit.setIcon(null);
            this.lblBackground.setBackground(new Color(0, 0, 0, 0));
            this.setBackground(new Color(0, 0, 0, 0));
        }
        this.Content.repaint();
        this.lblBackground.repaint();
        this.Line.repaint();
        this.Icon.repaint();
        this.btnChkDeleting.repaint();
        this.btnEdit.repaint();
        Title.repaint();
        this.repaint();

        show = !show;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBackground = new javax.swing.JLabel();

        setBackground(Color.BLUE);

        lblBackground.setBackground(new Color(0,0,0,0));
        lblBackground.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private JLabel Icon;
    private JLabel Line;
    private JLabel Title;
    private JLabel Content;
    private JLabel btnEdit;
    private JLabel btnChkDeleting;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    // End of variables declaration//GEN-END:variables

}
