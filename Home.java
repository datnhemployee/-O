package OoO.form.home;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.nio.file.FileSystems;
import java.util.Arrays;
import javax.imageio.ImageIO;

import OoO.form.home.componentLongTermHatching.eggButton;
import OoO.tools.*;

public class Home extends JFrame {

    private ImageIcon imgBackground;

    public final File LoginBackgroundDir = new File(
            FileSystems.getDefault().getPath("").toAbsolutePath().toString()
            + "\\OoO\\img\\LoginBackground.png");

    public Home() {
        this.formatBackgroundImage();
        this.design();

    }

    private void formatBackgroundImage() {
        try {
            Image tempImg = ImageIO.read(LoginBackgroundDir);
            /*
            * Scale the image to have a smaller one than one another which size itself 400x600 
             */
            int iScaleImgHeight
                    = 3 * cConstantIndex.iconstScaleImgHeight;
            int iScaleImgWidth
                    = 3 * cConstantIndex.iconstScaleImgWidth;

            tempImg = tempImg.getScaledInstance(
                    iScaleImgWidth,
                    iScaleImgHeight,
                    Image.SCALE_DEFAULT);
            imgBackground = new ImageIcon(tempImg);
            lblBackground = new JLabel(imgBackground);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    private void instantiateComponent() {

        pnlLongTermProgress = new JPanel();
        this.btnLayNewEgg = new eggButton(
                        "eggLayNew.png",
                        90,
                        100,
                        "Lay new egg",
                        "eggBtn.png"
                        );

        this.pnlYearlyCalendar = new JPanel();
        this.lblYearlyCalendar = new JLabel(
                "YEARLY CALENDAR",
                JLabel.CENTER);
    }

    private void borderDemo() {
        this.lblBackground.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK));

        this.pnlLongTermProgress.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLUE));
        this.pnlYearlyCalendar.setBorder(
                BorderFactory.createLineBorder(
                        Color.YELLOW));

        this.btnLayNewEgg.setBorder(
                BorderFactory.createLineBorder(
                        Color.GREEN));
        this.lblYearlyCalendar.setBorder(
                BorderFactory.createLineBorder(
                        Color.ORANGE));
    }

    private void resizeTitle() {
//        this.btnLayNewEgg.setPreferredSize(
//                new Dimension(
//                        this.btnLayNewEgg.getText().length(),
//                        this.btnLayNewEgg.getFont().getSize()*2));
    }

    private void design() {

        this.instantiateComponent();

        /*
        * Deign the Long- term hatching title
         */
        this.pnlLongTermProgress.setBackground(
                new Color(255, 255, 255, 0));
        this.pnlYearlyCalendar.setBackground(
                new Color(255, 255, 255, 0));
        
//        this.lblLongTermProgress.setFont(
//                FontTool.getFont("Cambria",
//                        40));
//        this.lblLongTermProgress.setForeground(
//                Color.red);
        this.resizeTitle();

        this.borderDemo();

//        Font[] temp = GraphicsEnvironment.
//                            getLocalGraphicsEnvironment().
//                                getAllFonts();
//        for(Font a: temp){
//            System.out.printf(
//                    "\n Family Name: " + a.getFamily()
//                    + "\n Name: "+ a.getName());
//        }
//        System.out.printf("\n Present Font: "+ 
//                    this.pnlLongTermProgress.getFont().getFamily().toString()
//                    + "\n Present Font Size: " + this.lblLongTermProgress.getFont().getSize()
//                    + "\n pnlLongTermProgress.height: " + this.lblLongTermProgress.getPreferredSize().height);
//        
        setSize(new Dimension(
                imgBackground.getIconWidth(),
                imgBackground.getIconHeight()));
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(1, 2));

        this.addComponent();
    }

    private void addComponent() {
        this.add(lblBackground);

        lblBackground.setLayout(new GridLayout(1, 2));
        lblBackground.add(
                this.pnlLongTermProgress);
        lblBackground.add(
                this.pnlYearlyCalendar);

        this.pnlLongTermProgress.setLayout(
                new BorderLayout(10,10));
        this.pnlYearlyCalendar.setLayout(
                new BorderLayout());

//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.FIRST_LINE_END;
//        c.gridx = 0;
//        c.gridy = 0;
        this.pnlLongTermProgress.add(
                this.btnLayNewEgg,
                BorderLayout.PAGE_START);
        this.pnlYearlyCalendar.add(
                this.lblYearlyCalendar,
                BorderLayout.PAGE_START);

    }

    private JLabel lblBackground;
    private eggButton btnLayNewEgg;
    private JLabel lblYearlyCalendar;
    private JPanel pnlLongTermProgress;
    private JPanel pnlYearlyCalendar;
}
