/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import Model.ConstantIndex;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author DAT
 */
public class mySpinner extends javax.swing.JPanel {

    protected int Value;
    protected int Max;
    protected int Min;

    /**
     * Creates new form mySpinner
     */
    public mySpinner(
            int width,
            int height) {
        initComponents();

        this.Value = 0;
        this.Max = 0;
        this.Min = 0;

        design(width, height);
    }

    public mySpinner(
            int Value,
            int Max,
            int Min,
            int width,
            int height) {
        initComponents();

        if (Value > Max || Value < Min) {
            this.Value = 0;
            this.Max = 0;
            this.Min = 0;
        } else {
            this.Value = Value;
            this.Max = Max;
            this.Min = Min;
        }
        design(width, height);
    }

    public void setBackgroundColor(Color color) {
        this.txtNumber.setBackground(color);
    }

    protected void design(
            int width,
            int height) {
        if (width == 0
                || height == 0) {
            this.setSize(
                    240,
                    100);
        } else {
            this.setSize(width, height);
        }
        this.setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        this.txtNumber.setBackground(new Color(210, 77, 87));
        this.txtNumber.setText(Integer.toString(Value));
        this.txtNumber.setForeground(Color.white);
        txtNumber.setHorizontalTextPosition(SwingConstants.CENTER);
        txtNumber.setFont(
                Tools.FontTool.getFont("Open Sans", 20));
        txtNumber.setSize(
                this.getWidth() / 3,
                getHeight());

        this.BtnLeft.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Left_1,
                                this.getWidth() / 5,
                                this.getHeight())));
        BtnLeft.setSize(
                BtnLeft.getIcon().getIconWidth(),
                BtnLeft.getIcon().getIconHeight());
        BtnLeft.setBackground(new Color(00, 0, 0, 0));
        BtnLeft.setOpaque(false);
        this.BtnRight.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Right_1,
                                this.getWidth() / 5,
                                this.getHeight())));
        BtnRight.setSize(
                BtnRight.getIcon().getIconWidth(),
                BtnRight.getIcon().getIconHeight());
        BtnRight.setBackground(new Color(00, 0, 0, 0));
        BtnRight.setOpaque(false);

        addListeners();
    }

    protected void addListeners() {
        this.BtnLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BtnLeft_MouseClicked();
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                mouseWheeled(e);
            }
        });

        this.BtnRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BtnRight_MouseClicked();
            }
        });
    }

    public void resize() {
        System.out.println("Sized");
        int width = this.getWidth();
        int height = this.getHeight();
        this.BtnLeft.setSize(
                width / 5,
                height
        );
        this.BtnLeft.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Left_1,
                                width / 5,
                                height)));

        System.out.println("BtnLeft = " + BtnLeft.getSize());
        this.BtnRight.setSize(
                width / 5,
                height);
        this.BtnRight.setIcon(
                new ImageIcon(
                        Tools.ImageTool.getScaledImage(
                                ConstantIndex.URLButton_Right_1,
                                width / 5,
                                height)));

        this.txtNumber.setSize(
                width / 3,
                height
        );
        BtnLeft.setLocation(0, 0);
        txtNumber.setLocation(
                BtnLeft.getWidth(), 0);
        BtnRight.setLocation(
                txtNumber.getX()
                + txtNumber.getWidth(), 0);

        this.BtnRight.repaint();
        this.txtNumber.repaint();
        this.repaint();
        this.BtnLeft.repaint();
    }

    public void mouseWheeled(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            BtnRight_MouseClicked();
        } else {
            BtnLeft_MouseClicked();
        }
    }

    public void BtnLeft_MouseClicked() {
        System.out.println("Click = " + Value);
        System.out.println("Min = " + Min);
        if (this.Value > Min) {
            this.Value--;
        }
         System.out.println("After Click = " + Value);
        this.txtNumber.setText(Integer.toString(Value));
        this.txtNumber.repaint();
    }

    public void BtnRight_MouseClicked() {
        if (this.Value < Max) {
            this.Value++;
        }
        this.txtNumber.setText(Integer.toString(Value));
        this.txtNumber.repaint();
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
        this.txtNumber.setText(Integer.toString(Value));
        this.txtNumber.repaint();
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int Max) {
            this.Max = Max;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int Min) {
            this.Min = Min;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        BtnLeft = new javax.swing.JLabel();
        BtnRight = new javax.swing.JLabel();
        txtNumber = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BtnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRight, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addComponent(BtnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnLeft;
    private javax.swing.JLabel BtnRight;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel txtNumber;
    // End of variables declaration//GEN-END:variables
}
