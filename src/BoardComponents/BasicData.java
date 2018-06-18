/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardComponents;

import GUI.myComponent.ButtonWithImage;
import Model.Bang;
import Model.ConstantIndex;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author DAT
 */
public class BasicData extends javax.swing.JPanel {

    Bang info;

    /**
     * Creates new form Panel1
     */
    public BasicData(Bang info,
            ImageIcon FooterBackground) {
        initComponents();
        this.info = info;

        design(FooterBackground);

    }

    public static ImageIcon getBtnOoOImage(
            Bang info,
            int width,
            int height
    ) {
        if (info.getNgayBao_GanNhat() != null) {
            if (info.getMauID()
                    == Model.ConstantIndex.MAU_DO.getMauID()) {
                return new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                Model.ConstantIndex.URLDefaultBtnOoORed,
                                width, height));
            }
            if (info.getMauID()
                    == Model.ConstantIndex.MAU_DUONG.getMauID()) {
                return new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                Model.ConstantIndex.URLDefaultBtnOoODarkBlue,
                                width, height));
            }
            if (info.getMauID()
                    == Model.ConstantIndex.MAU_LA.getMauID()) {
                return new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                Model.ConstantIndex.URLDefaultBtnOoOGreen,
                                width, height));
            }
            if (info.getMauID()
                    == Model.ConstantIndex.MAU_TIM.getMauID()) {
                return new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                Model.ConstantIndex.URLDefaultBtnOoOPurple,
                                width, height));
            }
            if (info.getMauID()
                    == Model.ConstantIndex.MAU_VANG.getMauID()) {
                return new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                Model.ConstantIndex.URLDefaultBtnOoOGold,
                                width, height));
            }
        }
        return new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        Model.ConstantIndex.URLDefaultBtnOoOGray,
                        width, height));
    }

    private void design(ImageIcon FooterBackground) {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(
                FooterBackground.getIconWidth(),
                FooterBackground.getIconHeight() * 3);

        /*
        * lblbackground
         */
        this.lblBackground = new JLabel();
        this.lblBackground.setBackground(new Color(0, 0, 0, 0));
        this.lblBackground.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Background\\Board.png",
                                FooterBackground.getIconWidth(),
                                FooterBackground.getIconHeight() * 3)));
        this.lblBackground.setBounds(
                0,
                0,
                this.lblBackground.getIcon().getIconWidth(),
                this.lblBackground.getIcon().getIconHeight());

        /*
        * Footer
         */
        String FooterText = "Chưa có thông tin hiển thị";
        if (info != null) {
            if (info.getNgayKetThuc_XaNhat() != null) {
                FooterText = getDeadLine_String(info);
            }
        }
        Footer = new ButtonWithImage(
                FooterText,
                Color.WHITE,
                Tools.FontTool.getFont("Open Sans Light", 20)
                        .deriveFont(Font.BOLD),
                FooterBackground
        );

        Footer.setOpaque(false);
        Footer.setBounds(
                0,
                Footer.getImage().getIconHeight() * 2,
                Footer.getImage().getIconWidth(),
                Footer.getImage().getIconHeight());

        /*
        * BtnOoOClock
         */
        BtnOoOClock = new JLabel();
        BtnOoOClock.setBackground(new Color(0, 0, 0, 0));
        BtnOoOClock.setLocation(5, 5);
        if (this.info == null) {
            BtnOoOClock.setIcon(
                    new ImageIcon(
                            Tools.ImageTool.getScaledImage(
                                    Model.ConstantIndex.URLDefaultBtnOoOGray,
                                    60,
                                    60
                            )));
        } else {
            BtnOoOClock.setIcon(
                    getBtnOoOImage(
                            this.info,
                            60, 60));
        }
        BtnOoOClock.setSize(
                this.BtnOoOClock.getIcon().getIconWidth(),
                this.BtnOoOClock.getIcon().getIconHeight());

        /*
        * Title
         */
        Title = new JLabel();
        Title.setBackground(new Color(0, 0, 0, 0));
        Title.setFont(Tools.FontTool.getFont("Open Sans Light", 20)
                .deriveFont(Font.BOLD));
        Title.setForeground(getColor(info));
        if (this.info == null) {
            Title.setText("Chưa có thông tin để hiển thị");
        } else {

            Title.setText(info.getTen());
        }
        Title.setSize(
                this.lblBackground.getWidth(),
                Title.getFont().getSize() * 2);
        Title.setLocation(
                (this.lblBackground.getWidth()
                - this.Title.getWidth()) / 2,
                (this.lblBackground.getHeight()
                - this.Title.getHeight()) / 2);
        Title.setVerticalAlignment(SwingConstants.CENTER);
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        /*
        * lblDoneIcon
         */
        lblDoneIcon = new JLabel();
        lblDoneIcon.setBackground(new Color(0, 0, 0, 0));
        lblDoneIcon.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                "Icon\\IconDoneDarkBlue.png",
                                40,
                                40)));
        lblDoneIcon.setSize(
                lblDoneIcon.getIcon().getIconWidth(),
                lblDoneIcon.getIcon().getIconHeight());
        lblDoneIcon.setLocation(
                this.lblBackground.getWidth() - 40,
                this.BtnOoOClock.getY());

        /*
        * lblProcess 
         */
        lblProcess = new JLabel();
        lblProcess.setBackground(new Color(0, 0, 0, 0));
        lblProcess.setFont(Tools.FontTool.getFont("Open Sans", 20));
        lblProcess.setForeground(new Color(27, 188, 155));
        if (this.info == null) {
            lblProcess.setText("0 / 0");

        } else {
            lblProcess.setText(
                    info.getSoTheHoanTat()
                    + " / "
                    + info.getTongSoThe()
            );
        }
        lblProcess.setSize(
                lblProcess.getText().length()
                * lblProcess.getFont().getSize(),
                lblProcess.getFont().getSize() * 2);
        lblProcess.setLocation(
                this.lblDoneIcon.getX()
                - lblDoneIcon.getWidth(),
                this.lblDoneIcon.getY()
        );

        this.add(this.lblBackground);

        this.lblBackground.add(Footer);
        this.lblBackground.add(this.BtnOoOClock);
        this.lblBackground.add(this.Title);
        this.lblBackground.add(lblDoneIcon);
        this.lblBackground.add(lblProcess);
    }

    public static Color getColor(Bang info) {
        if (info != null) {
            if (info.getMauID()
                    == ConstantIndex.MAU_DO.getMauID()) {
                return new Color(
                        ConstantIndex.MAU_DO.getRed(),
                        ConstantIndex.MAU_DO.getGreen(),
                        ConstantIndex.MAU_DO.getBlue(),
                        ConstantIndex.MAU_DO.getAlpha()
                );
            }
            if (info.getMauID()
                    == ConstantIndex.MAU_LA.getMauID()) {
                return new Color(
                        ConstantIndex.MAU_LA.getRed(),
                        ConstantIndex.MAU_LA.getGreen(),
                        ConstantIndex.MAU_LA.getBlue(),
                        ConstantIndex.MAU_LA.getAlpha()
                );
            }
            if (info.getMauID()
                    == ConstantIndex.MAU_DUONG.getMauID()) {
                return new Color(
                        ConstantIndex.MAU_DUONG.getRed(),
                        ConstantIndex.MAU_DUONG.getGreen(),
                        ConstantIndex.MAU_DUONG.getBlue(),
                        ConstantIndex.MAU_DUONG.getAlpha()
                );
            }
            if (info.getMauID()
                    == ConstantIndex.MAU_TIM.getMauID()) {
                return new Color(
                        ConstantIndex.MAU_TIM.getRed(),
                        ConstantIndex.MAU_TIM.getGreen(),
                        ConstantIndex.MAU_TIM.getBlue(),
                        ConstantIndex.MAU_TIM.getAlpha()
                );
            }
            if (info.getMauID()
                    == ConstantIndex.MAU_VANG.getMauID()) {
                return new Color(
                        ConstantIndex.MAU_VANG.getRed(),
                        ConstantIndex.MAU_VANG.getGreen(),
                        ConstantIndex.MAU_VANG.getBlue(),
                        ConstantIndex.MAU_VANG.getAlpha()
                );
            }
        }
        return new Color(
                191,
                191,
                191
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private JLabel BtnOoOClock;
    private JLabel lblBackground;
    private JLabel Title;
    private ButtonWithImage Footer;
    private JLabel lblDoneIcon;
    private JLabel lblProcess;

    private static String getDeadLine_String(Bang info) {
        if (info.getNgayKetThuc_XaNhat() != null) {
            Calendar c = Calendar.getInstance();
            Date temp = info.getNgayKetThuc_XaNhat();
            c.setTime(temp);
            LocalDate d1 = LocalDate.of(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));

            c.setTimeInMillis(System.currentTimeMillis());
            LocalDate d2 = LocalDate.of(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            Period p = Period.between(d2, d1);
            if (p.getMonths() != 0) {
                return "Còn "
                        + p.getMonths()
                        + " tháng "
                        + p.getDays()
                        + " ngày nữa ";
            } else {
                return "Còn "
                        + p.getDays()
                        + " ngày nữa ";
            }
        }
        return "Chưa có dữ liệu";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
