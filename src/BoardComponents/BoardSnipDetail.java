package BoardComponents;

import Controller.SQLConnectivity;
import Model.Bang;
import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;

/* 
 * BoardSnipDetail.java requires
 * images/dukeWaveRed.gif. 
 */
public class BoardSnipDetail extends JPanel {

    private JLayeredPane layeredPane;

    private BasicData pan1;
    private MoreInfo pan2;

    public BoardSnipDetail(
            SQLConnectivity conSQL,
            Bang info,
            ImageIcon FooterBackground) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.setBackground(new Color(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(
                FooterBackground.getIconWidth(),
                FooterBackground.getIconHeight() * 3
        ));

        //Create and set up the layered pane.
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(
                FooterBackground.getIconWidth(),
                FooterBackground.getIconHeight() * 3
        ));
        layeredPane.setBackground(new Color(0, 0, 0, 0));

        //Add several overlapping, colored labels to the layered pane
        //using absolute positioning/sizing.
        pan1 = new BasicData(info, FooterBackground);
        layeredPane.add(pan1, new Integer(1));

        pan2 = new MoreInfo(
                conSQL,
                info,
                FooterBackground.getIconWidth(),
                FooterBackground.getIconHeight() * 3);
        layeredPane.add(pan2, new Integer(2));

        //Add control pane and layered pane to this JPanel.
//        add(Box.createRigidArea(new Dimension(0, 10)));
//        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
    }
}
