/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.myComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author DAT
 */
public class customScrollbar extends javax.swing.JPanel {

    /**
     *
     */
    private Point mouseFirstPos;
    private Point mouseSecPos;

    private int BoxFirstY;
    private int Max;

    private int Value;
    private JScrollBar Target;
    private int PaneHeightPerBoxHeight;
    
    /**
     * Creates new form customScrollbar
     */
    private customScrollbar() {
    }

    public customScrollbar(
            JScrollBar Target,
            Color BarColor,
            Color BoxColor,
            ImageIcon BoxImage,
            int PaneHeightPerBoxHeight,
            int width,
            int height
    ) {
        this.Target = Target;
        this.PaneHeightPerBoxHeight = PaneHeightPerBoxHeight;

        this.Max = this.getMax();

        this.Value = this.getTargetValue();
        
        
        initComponents();

        design(
                BarColor,
                BoxColor,
                BoxImage,
                width,
                height);

    }

    private void design(
            Color BarColor,
            Color BoxColor,
            ImageIcon BoxImage,
            int width,
            int height) {

        /**
         * this
         */
        this.setSize(
                width,
                height);
        this.setBackground(new Color(0, 0, 0, 0));
        
        /**
         * lblBackground
         */
        this.lblBackground.setBackground(BarColor);
        this.lblBackground.setBounds(
                0,
                0,
                width,
                height);
        this.lblBackground.setIcon(BoxImage);
        /**
         * Box
         */
        this.Box.setBounds(
                0,
                0,
                width,
                height
                / this.PaneHeightPerBoxHeight);
        this.Box.setBackground(BoxColor);
        

        addListeners();

        this.add(lblBackground);
        lblBackground.add(Box);
    }

    public int getTrueHeight() {
        return this.getHeight()
                - this.Box.getHeight();
    }

    public int getMax() {
        return this.Target.getMaximum();
    }

    public int getMin() {
        return this.Target.getMinimum();
    }

    public int getTargetExtent() {
        return this.Target.getModel().getExtent();
    }

    public int getValue() {
        return this.getTargetValue()
                + this.getTargetExtent();
    }

    public int getTargetValue() {
        return this.Target.getValue();
    }

    public void setTargetValue(int Value) {
        this.Target.setValue(
                Value - this.getTargetExtent());
    }

    public void setMax(int Max) {
        this.Target.setMaximum(Max);
    }

    public void setMin(int Min) {
        this.Target.setMinimum(Min);
    }

    protected boolean resize() {
        if (this.getHeight() == 0) {
            return false;
        }
        this.Box.setBounds(
                0,
                0,
                this.getWidth(),
                (this.getMax() - this.getMin() - this.getTargetExtent())
                * this.getHeight()
                / this.PaneHeightPerBoxHeight);
        return true;
    }

    public void addListeners() {
        /**
         * Box.MouseListeners
         */
        this.Box.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Box_MousePressed(e);
            }

        });

        this.Box.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Box_MouseDragged(e);
            }
        });
        /**
         * this.Size changed
         */
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });

        /**
         * Bar.Pressed
         */
        this.lblBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Bar_MousePressed(e);
            }
        });
        /**
         * Target.Max Or Target.Min shall have changed
         */
        this.Target.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Target_Changed(e);
            }
        });
    }

    protected void Target_Changed(ChangeEvent e) {
        if (Max != this.getMax()) {
            this.Box.getSize(new Dimension(
                    this.getWidth(),
                    (this.getMax() - this.getMin() - this.getTargetExtent())
                    * this.getHeight()
                    / this.PaneHeightPerBoxHeight));
            this.Box.setLocation(
                    this.Box.getX(),
                    this.SpecificPosition_getValue(this.Box.getY())
            );
            Max = this.getMax();
        }
        
        if(this.Value != this.getTargetValue()){
            this.Value = this.getTargetValue();
            this.setValue(Value);
        }
    }

    protected void Box_MousePressed(MouseEvent e) {
        this.mouseFirstPos = e.getLocationOnScreen();
        BoxFirstY = this.Box.getY();
    }

    protected void Box_MouseDragged(MouseEvent e) {
        this.mouseSecPos = e.getLocationOnScreen();
        int BoxY = this.BoxFirstY
                + (this.mouseSecPos.y - this.mouseFirstPos.y);
        if (BoxY >= 0 && BoxY + this.Box.getHeight() <= this.getHeight()) {
            this.Box.setLocation(this.Box.getX(), BoxY);
            this.Target.setValue(
                    this.SpecificPosition_getValue(BoxY));
        } else if (BoxY < 0) {
            this.Box.setLocation(this.Box.getX(), 0);
            this.Target.setValue(this.getMin());
        } else {
            this.Box.setLocation(this.Box.getX(),
                    this.getHeight() - this.Box.getHeight());
            this.Target.setValue(this.getMax());
        }
    }

    protected void Bar_MousePressed(MouseEvent e) {
        int BoxY = e.getY();
        if (BoxY >= 0 && BoxY + this.Box.getHeight() <= this.getHeight()) {
            this.Box.setLocation(this.Box.getX(), BoxY);
            this.Target.setValue(
                    this.SpecificPosition_getValue(BoxY));
        } else if (BoxY < 0) {
            this.Box.setLocation(this.Box.getX(), 0);
            this.Target.setValue(this.getMin());
        } else {
            this.Box.setLocation(this.Box.getX(),
                    this.getHeight() - this.Box.getHeight());
            this.Target.setValue(this.getMax());
        }

    }

    private void setValue(int Value) {
        this.Value = Value;
        this.Box.setLocation(
                this.SpecificValue_getBoxPosition(Value));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Box = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setLayout(null);

        Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Box.setDoubleBuffered(true);
        Box.setMaximumSize(new Dimension(this.getWidth(),this.getHeight()/ this.PaneHeightPerBoxHeight));
        Box.setMinimumSize(new Dimension(this.getWidth(),this.getHeight()/ this.PaneHeightPerBoxHeight));
        Box.setOpaque(true);
        Box.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/ this.PaneHeightPerBoxHeight));
        add(Box);
        Box.setBounds(0, 0, 50, 60);

        lblBackground.setBackground(new java.awt.Color(255, 51, 51));
        lblBackground.setDoubleBuffered(true);
        lblBackground.setOpaque(true);
        add(lblBackground);
        lblBackground.setBounds(0, -4, 50, 300);
    }// </editor-fold>//GEN-END:initComponents

    public int SpecificPosition_getValue(int posByDir) {
        return posByDir
                * (this.getMax() - this.getMin() - this.getTargetExtent())
                / this.getTrueHeight();
    }

    public Point SpecificValue_getBoxPosition(int Value) {
        return new Point(
                this.Box.getX(),
                Value * this.getTrueHeight()
                / (this.getMax() - this.getMin() - this.getTargetExtent())
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Box;
    private javax.swing.JLabel lblBackground;
    // End of variables declaration//GEN-END:variables

    
}
