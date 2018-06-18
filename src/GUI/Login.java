/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.SQLConnectivity;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;

import GUI.myComponent.*;
import Model.ConstantIndex;
import Model.SQLConnection;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author DAT
 */
public class Login extends javax.swing.JFrame {

    public SQLConnectivity conSQL;

    public Login() {
        initComponents();
        design();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        lblBackground.setBackground(new Color(0,0,0,0));
        lblBackground.setIcon(new ImageIcon(
            Tools.ImageTool.getScaledImage("Background\\BGLogin.png", 
                500, 
                800))
    );
    lblBackground.setPreferredSize(new Dimension(
        500,
        800));
getContentPane().add(lblBackground);
lblBackground.setBounds(0, 0, 520, 900);

setSize(new java.awt.Dimension(520, 920));
setLocationRelativeTo(null);
}// </editor-fold>//GEN-END:initComponents

    private void design() {
        this.setIconImage(
                Tools.ImageTool.getScaledImage(
                        ConstantIndex.URLDefaultOoO,
                        50,
                        70));
        this.setBackground(new Color(0, 0, 0, 0));

        /*
        * Lbl OoO
         */
        lblOoO = new JLabel();
        lblOoO.setBackground(new Color(0, 0, 0, 0));
        lblOoO.setIcon(new ImageIcon(Tools.ImageTool.getScaledImage(
                "Icon\\OoO_Orange.png",
                120,
                150)));
//        lblOoO.setPreferredSize(new Dimension(
//                lblOoO.getIcon().getIconWidth(),
//                lblOoO.getIcon().getIconHeight()));
        lblOoO.setSize(new Dimension(
                lblOoO.getIcon().getIconWidth(),
                lblOoO.getIcon().getIconHeight()));
        lblOoO.setLocation(new Point(
                (lblBackground.getX()
                + lblBackground.getWidth()
                - lblOoO.getWidth()) / 2,
                lblBackground.getLocation().y + 50)
        );

        /*
        * Lbl Login
         */
        lblLogin = new JLabel("KẾT NỐI CSDL");
        lblLogin.setBackground(new Color(0, 0, 0, 0));
        lblLogin.setForeground(new Color(210, 77, 87));
        lblLogin.setFont(Tools.FontTool.getFont("Cambria", 40)
                .deriveFont(Font.BOLD));
        lblLogin.setSize(new Dimension(
                lblLogin.getText().length()
                * lblLogin.getFont().getSize(),
                lblLogin.getFont().getSize()));
        lblLogin.setVerticalTextPosition(SwingConstants.CENTER);
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
//        System.out.printf("lblBackground.getX() = " + lblBackground.getX());
//        System.out.printf("lblBackground.getSize().width = " + lblBackground.getSize().width);
//        System.out.printf("lblLogin.getSize().width = " + lblLogin.getSize().width);
        lblLogin.setLocation(
                (lblBackground.getX()
                + lblBackground.getSize().width
                - lblLogin.getSize().width) / 2,
                lblOoO.getLocation().y
                + lblOoO.getSize().height + 10);
        /*
        * Username
         */
        txtUsername = new myTextArea(
                "Tên tài khoản SQL",
                new Color(210, 77, 87),
                Tools.FontTool.getFont("Open Sans Light", 30),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Icon\\username.png",
                        50,
                        50)),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Line\\DarkRed.png",
                        this.lblBackground.getWidth() - 20,
                        5))
        );
//        System.out.printf("\n" + this.lblBackground.getPreferredSize().width);
//        System.out.printf("\n" + txtUsername.getWidth());
//        System.out.printf("\n" + txtUsername.getIcon().getIconWidth());
        txtUsername.setBounds(
                30,
                this.lblLogin.getY()
                + this.lblLogin.getHeight()
                + 10,
                this.lblBackground.getWidth() - 60,
                70);
        txtUsername.setAlignmentX(CENTER_ALIGNMENT);

        /*
        * Password
         */
        txtPassword = new myTextArea(
                "Mật khẩu",
                txtUsername.getTextColor(),
                txtUsername.getTextFont(),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Icon\\Password.png",
                        50,
                        50)),
                txtUsername.getFooterLine()
        );
//        System.out.printf("\n"+this.lblBackground.getPreferredSize().width);
//        System.out.printf("\n"+txtUsername.getWidth());
        txtPassword.setBounds(
                30,
                this.txtUsername.getY()
                + this.txtUsername.getHeight()
                + 10,
                this.lblBackground.getWidth() - 60,
                70);

        /*
        * Database
         */
        txtDatabase = new myTextArea(
                "Tên Cơ Sở Dữ Liệu",
                txtUsername.getTextColor(),
                txtUsername.getTextFont(),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Icon\\Database.png",
                        50,
                        50)),
                txtUsername.getFooterLine()
        );
        txtDatabase.setBounds(
                30,
                this.txtPassword.getY()
                + this.txtPassword.getHeight()
                + 10,
                this.lblBackground.getWidth() - 60,
                70);

        /*
        * Port
         */
        txtPort = new myTextArea(
                "Mã Số Cổng",
                txtUsername.getTextColor(),
                txtUsername.getTextFont(),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Icon\\Port.png",
                        50,
                        50)),
                txtUsername.getFooterLine()
        );
        txtPort.setBounds(
                30,
                this.txtDatabase.getY()
                + this.txtDatabase.getHeight()
                + 10,
                this.lblBackground.getWidth() - 60,
                70);

        /*
        * Btn Hows
         */
        BtnHow = new JLabel("Cách tìm...");
        BtnHow.setBackground(new Color(0, 0, 0, 0));
        BtnHow.setForeground(this.txtUsername.getTextColor());
        BtnHow.setFont(Tools.FontTool.getFont(
                "Open Sans Light", 25));
        BtnHow.setBounds(
                this.txtPort.getX()
                + this.txtPort.getWidth()
                - BtnHow.getText().length()
                * BtnHow.getFont().getSize(),
                this.txtPort.getY()
                + this.txtPort.getHeight()
                + 10,
                BtnHow.getText().length()
                * BtnHow.getFont().getSize(),
                BtnHow.getFont().getSize());
        BtnHow.setLocation(new Point(
                txtPort.getSize().width
                + txtPort.getLocation().x
                - BtnHow.getSize().width / 2,
                txtPort.getLocation().y
                + txtPort.getSize().height
                + BtnHow.getSize().height));
        BtnHow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                BtnHow_Click(evt);
            }
        });
        btnInfo = new JLabel();
        btnInfo.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Button\\BtnSetting.png",
                                30,
                                30)));
        btnInfo.setSize(
                this.btnInfo.getIcon().getIconWidth(),
                this.btnInfo.getIcon().getIconHeight()
        );
        btnInfo.setLocation(
                this.lblBackground.getX()
                + this.lblBackground.getWidth()
                - btnInfo.getWidth()
                - 20,
                this.lblOoO.getY()
        );
        btnInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnInfo_clicked();
            }
        });
        /*
        * Btn Login
         */
        btnLogin = new ButtonWithImage(
                "ĐĂNG NHẬP",
                this.txtUsername.getTextColor(),
                Tools.FontTool.getFont("Cambria", 30)
                        .deriveFont(Font.BOLD),
                new ImageIcon(Tools.ImageTool.getScaledImage(
                        "Button\\BtnLogin.png",
                        this.lblBackground.getSize().width - 100,
                        50))
        );

        btnLogin.setBounds(
                (this.lblBackground.getPreferredSize().width
                - btnLogin.getImage().getIconWidth()) / 2,
                (this.lblBackground.getPreferredSize().height
                - btnLogin.getImage().getIconHeight() * 2),
                btnLogin.getImage().getIconWidth(),
                btnLogin.getImage().getIconHeight());

        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                btnLogin_Click(evt);
            }
        });


        /*
        * Btn Exit
         */
        btnExit = new ButtonWithImage(
                "THOÁT",
                this.btnLogin.getTextColor(),
                this.btnLogin.getTextFont(),
                this.btnLogin.getImage()
        );

        btnExit.setBounds(
                (this.lblBackground.getPreferredSize().width
                - btnExit.getImage().getIconWidth()) / 2,
                (this.lblBackground.getPreferredSize().height
                - btnExit.getImage().getIconHeight() / 2),
                btnExit.getImage().getIconWidth(),
                btnExit.getImage().getIconHeight());

        btnExit.setAlignmentX(CENTER_ALIGNMENT);
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                btnExit_Click(evt);
            }
        });

        /*
        * Add Components
         */
        this.add(lblBackground);

        lblBackground.setLayout(null);
        lblBackground.add(lblLogin);
        lblBackground.add(lblOoO);

        lblBackground.add(txtUsername);
        lblBackground.add(txtDatabase);
        lblBackground.add(txtPassword);
        lblBackground.add(txtPort);

        lblBackground.add(BtnHow);

        lblBackground.add(btnLogin);
        lblBackground.add(btnExit);
        lblBackground.add(btnInfo);
    }

    public void btnExit_Click(MouseEvent evt) {
        this.dispose();
        System.exit(1);
    }

    public void btnLogin_Click(MouseEvent evt) {
        try {
            conSQL = new SQLConnectivity(
                    new SQLConnection(
                            txtUsername.getText(),
                            txtPassword.getText(),
                            txtDatabase.getText(),
                            txtPort.getText()));
            conSQL.connect();
            new BoardList(this.conSQL).setVisible(true);
            this.setVisible(false);
            this.dispose();
        } catch (Exception e) {
            MessageBox ms = new MessageBox(
                    "KẾT NỐI THẤT BẠI",
                    "\n Tên tài khoản, mật khẩu, tên CSDL"
                    + "hoặc cổng bị sai."
                    + "\n Xem file hướng dẫn để biết thêm thông tin chi tiết"
                    + "\n Lỗi hệ thống: " + e
            );
            ms.setVisible(true);

        }
    }

    public void BtnHow_Click(MouseEvent evt) {
        new MessageBox("HƯỚNG DẪN KẾT NỐI SQL",
                "I.Cách xem tên tài khoản: "
                + "\n 1. Chạy ứng dụng Microsoft SQL Server Management Studio"
                + "\n 2. Chọn Authentication--> Windows Authentication--> Chọn OK"
                + "\n 3. Tìm Object Explorer --> Chọn Sercurity --> Login"
                + "\n - Nếu chưa tồn tại tài khoản mà bạn muốn đăng nhập hoặc"
                + "\n bạn đã quên mật khẩu thì làm theo các bước sau: "
                + "\n   + Click chuột phải--> Chọn \" New Login \" "
                + "\n   + Gõ vào \" Login Name \" tên tài khoản mới."
                + "\n   + Gõ vào \" Password \" và \"Comfirm Password\" mật khẩu của bạn."
                + "\n   + Chọn thẻ Status --> Chọn Grant và Enabled --> Chọn OK."
                + "\n II.Cách tìm số cống: "
                + "\n Cổng kết nối  SQL mặc định là 1433. Tuy nhiên, bạn có "
                + "\n thể liên hệ hỗ trợ tại nút Information tại góc phải màn hình"
                + " nếu có xảy ra sai sót.").setVisible(true);
    }

    public void btnInfo_clicked() {
        new About().setVisible(true);
    }

    private JLabel lblLogin;
    private JLabel lblOoO;

    private myTextArea txtUsername;
    private myTextArea txtPassword;
    private myTextArea txtDatabase;
    private myTextArea txtPort;

    private ButtonWithImage btnExit;
    private ButtonWithImage btnLogin;
    private JLabel btnInfo;

    private JLabel BtnHow;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    // End of variables declaration//GEN-END:variables
}
