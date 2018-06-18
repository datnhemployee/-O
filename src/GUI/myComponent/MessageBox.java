/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

/**
 *
 * @author DAT
 */
public class MessageBox extends javax.swing.JFrame {

    private String Message;

    /**
     * Creates new form MessageBox
     */
    private MessageBox() {
    }

    public MessageBox(
            String Title,
            String Message) {
        this.Message = Message;

        initComponents();
        design(Title);
    }

    private void design(
            String Title) {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(
                new Dimension(400, 400));
        this.setLocationRelativeTo(null);

        /*
        * lblBackground
         */
        this.lblBackground.setLocation(new Point(0, 0));
        this.lblBackground.setIcon(
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Background\\BGMessageBox.png",
                        this.getWidth() - 2,
                        this.getHeight() - 2))
        );
        this.lblBackground.setSize(
                new Dimension(
                        this.lblBackground.getIcon().getIconWidth(),
                        this.lblBackground.getIcon().getIconHeight()));
        this.lblBackground.setBackground(new Color(0, 0, 0, 0));

        /*
        * BtnBack
         */
        this.BtnBack = new ButtonWithImage(
                "QUAY TRỞ LẠI",
                new Color(210, 77, 87),
                Tools.FontTool.getFont("Cambria", 28)
                        .deriveFont(Font.BOLD),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Button\\BtnLogin.png",
                        this.lblBackground.getWidth() - 20,
                        50))
        );
        this.BtnBack.setSize(
                new Dimension(
                        this.BtnBack.getImage().getIconWidth(),
                        this.BtnBack.getImage().getIconHeight()
                ));

        this.BtnBack.setLocation(
                (this.lblBackground.getWidth()
                - this.BtnBack.getWidth()) / 2,
                this.lblBackground.getHeight()
                - this.BtnBack.getHeight() * 5 / 3);

        container1 = new JPanel();
        container1.setBackground(new Color(0, 0, 0, 0));
        /*
        * lblDetail
         */
        this.txtTitle = new JTextArea(Title);
        this.txtTitle.setLineWrap(true);
        this.txtTitle.setWrapStyleWord(true);
        this.txtTitle.setFont(Tools.FontTool.getFont("Open Sans", 25));
        this.txtTitle.setForeground(Color.WHITE);
        this.txtTitle.setBackground(new Color(210, 77, 87));
        this.txtTitle.setAlignmentX(LEFT_ALIGNMENT);
        this.txtTitle.setEditable(false);

        /*
        * txtDetail
         */
        this.txtDetail = new JTextArea(this.Message);
        this.txtDetail.setFont(Tools.FontTool.getFont("Open Sans", 20));
        this.txtDetail.setLineWrap(true);
        this.txtDetail.setWrapStyleWord(true);
        this.txtDetail.setForeground(Color.WHITE);
        this.txtDetail.setBackground(new Color(210, 77, 87));
        this.txtDetail.setAlignmentX(LEFT_ALIGNMENT);
        this.txtDetail.setEditable(false);
        this.txtDetail.setEditable(false);
        
        /*
        * scrllpn
         */
        this.scrllpn = new JPanel(new BorderLayout());
        this.scrllpn.setBackground(new Color(0, 0, 0, 0));
        this.scrllpn.setLocation(
                10, 10);
        this.scrllpn.setSize(
                this.lblBackground.getWidth() - 20,
                this.lblBackground.getHeight()
                - this.BtnBack.getHeight() * 8 / 3
                - 10
        );

        System.out.printf(" this.scrllpn.getWidth() = "
                + (this.lblBackground.getWidth() - 10));

        System.out.printf(" this.scrllpn.getHeight() = "
                + (this.lblBackground.getHeight()
                - this.BtnBack.getHeight() * 8 / 3));
        /*
        * Add Components
         */
        this.lblBackground.add(this.BtnBack);
        lblBackground.add(this.scrllpn);
        JScrollPane temp1 = new JScrollPane(
                container1,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        temp1.getVerticalScrollBar().setOpaque(false);
        temp1.getVerticalScrollBar().setUI(null);
        temp1.setBorder(null);
        temp1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        temp1.setDoubleBuffered(true);
        temp1.setBackground(new Color(210, 77, 87));
        scrllpn.add(temp1);
        this.container1.setLayout(
                new BoxLayout(container1, BoxLayout.Y_AXIS));
        this.container1.add(this.txtTitle);
        this.container1.add(this.txtDetail);

        /*
        * scrllBar
         */
        scrllBar = new customScrollbar(
                temp1.getVerticalScrollBar(),
                new Color(210, 77, 87, 255),
                new Color(255, 255, 255, 255),
                null,
                5,
                20,
                this.scrllpn.getHeight()
        );
        this.setSize(this.lblBackground.getWidth()
                + scrllBar.getWidth() * 2,
                this.lblBackground.getHeight());
        this.lblBackground.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Background\\BGMessageBox.png",
                                this.getWidth(),
                                this.getHeight())));
        this.lblBackground.setSize(
                this.lblBackground.getIcon().getIconWidth(),
                this.lblBackground.getIcon().getIconHeight());

        scrllBar.setSize(
                this.scrllBar.getWidth(),
                this.scrllpn.getHeight());

        scrllBar.setLocation(
                new Point(
                        this.scrllpn.getX()
                        + this.scrllpn.getWidth()
                        + this.scrllBar.getWidth(),
                        this.scrllpn.getY()));
        this.BtnBack.setLocation(
                (this.lblBackground.getWidth() - this.BtnBack.getWidth()) / 2,
                this.BtnBack.getY()
        );

        this.lblBackground.add(this.scrllBar);

        this.BtnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                back();
            }
        }
        );
    }

    protected void back() {
        this.setVisible(false);
        this.dispose();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        setSize(new java.awt.Dimension(499, 303));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JPanel container1;
    private ButtonWithImage BtnBack;
    private JTextArea txtTitle;
    private JTextArea txtDetail;
    private JPanel scrllpn;
    private customScrollbar scrllBar;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    // End of variables declaration//GEN-END:variables
}
