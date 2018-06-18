/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ControllerHinh;
import Controller.ControllerMau;
import Controller.SQLConnectivity;
import Tools.ReadTxt;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author DAT
 */
public class ConstantIndex {

    // DEFAULT INSTANCE
    /*
    *  Mau
     */
    public final static Mau MAU_DO = new Mau(1, 210, 77, 87, 255);
    public final static Mau MAU_LA = new Mau(2, 27, 188, 155, 255);
    public final static Mau MAU_TIM = new Mau(3, 122, 48, 160, 255);
    public final static Mau MAU_VANG = new Mau(4, 244, 208, 63, 255);
    public final static Mau MAU_DUONG = new Mau(5, 0, 32, 96, 255);

    /*
    * URL
     */
    /// Hinh
    public final static String URLDefaultHinh
            = ReadTxt.currentFolderPath
            + "\\src\\userBackground\\hinh1.jpg";
    public final static Hinh DefaultHinh
            = new Hinh(1, URLDefaultHinh);

    /// Footer
    public final static String URLDefaultBoardFooterGray
            = "Background\\BoardFooterGray.png";
    public final static String URLDefaultBoardFooterDarkBlue
            = "Background\\BoardFooterDarkBlue.png";
    public final static String URLDefaultBoardFooterGreen
            = "Background\\BoardFooterGreen.png";
    public final static String URLDefaultBoardFooterPurple
            = "Background\\BoardFooterPurple.png";
    public final static String URLDefaultBoardFooterRed
            = "Background\\BoardFooterRed.png";
    public final static String URLDefaultBoardFooterGold
            = "Background\\BoardFooterGold.png";

    /// BtnOoO
    public final static String URLDefaultBtnOoOGray
            = "Button\\BtnOoOGray.png";
    public final static String URLDefaultBtnOoODarkBlue
            = "Button\\BtnOoODarkBlue.png";
    public final static String URLDefaultBtnOoORed
            = "Button\\BtnOoORed.png";
    public final static String URLDefaultBtnOoOGold
            = "Button\\BtnOoOGold.png";
    public final static String URLDefaultBtnOoOGreen
            = "Button\\BtnOoOGreen.png";
    public final static String URLDefaultBtnOoOPurple
            = "Button\\BtnOoOPurple.png";

    /// OoO_Icon
    public final static String URLDefaultOoO
            = "Icon\\OoO_Orange.png";
    public final static String URLOOO_Blue
            = "Icon\\OoO_Blue.png";
    public final static String URLOOO_Gold
            = "Icon\\OoO_Gold.png";
    public final static String URLOOO_Green
            = "Icon\\OoO_Green.png";
    public final static String URLOOO_Purple
            = "Icon\\OoO_Purple.png";

    /*
    * BG TableDetail
     */
    public final static String URLBackground_TableDetail_Blue
            = "Background\\BGTableDetail_DarkBlue.png";
    public final static String URLBackground_TableDetail_Red
            = "Background\\BGTableList.png";
    public final static String URLBackground_Yellow
            = "Background\\BG_Yellow.png";
    public final static String URLBackground_Green
            = "Background\\BG_Green.png";
    public final static String URLBackground_Purple
            = "Background\\BG_Purple.png";
    /*
    * BTn
     */
    public final static String URLButton_Back
            = "Button\\BtnBack.png";
    public final static String URLButton_Edit_White
            = "Button\\BtnEdit_White.png";
    public final static String URLButton_Pre
            = "Button\\BtnPre.png";
    public final static String URLButton_Nex
            = "Button\\BtnNex.png";
    public final static String URLButton_Right_1
            = "Button\\BtnRight_1.png";
    public final static String URLButton_Right_2
            = "Button\\BtnRight_2.png";
    public final static String URLButton_Left_1
            = "Button\\BtnLeft_1.png";
    public final static String URLButton_Left_2
            = "Button\\BtnLeft_2.png";

    public final static String URLButton_Blue
            = "Button\\BtnBlue.png";
    public final static String URLButton_Red
            = "Button\\BtnRed.png";
    public final static String URLButton_Purple
            = "Button\\BtnPurple.png";
    public final static String URLButton_Green
            = "Button\\BtnGreen.png";
    public final static String URLButton_Gold
            = "Button\\BtnGold.png";
    
    public final static String URLButton_Del_Gray
            = "Button\\Delete_Gray.png";
    /*
    * Border
     */
    public final static String URLLightWhiteBorder
            = "Border\\LightWhiteBorder.png";
    /*
    * Icon
     */
    public final static String URLIconBuoc_White
            = "Icon\\Buoc_White.png";
    public final static String URLIconDeadline_White
            = "Icon\\Deadline.png";

    /*
    * BG_DateOOO
    */
    public final static String URLDate_Passed
            = "Button\\DatePassed.png";
    public final static String URLDate_Missed
            = "Button\\DateMissed.png";
    public final static String URLDate_OOO
            = "Button\\DateOOO.png";
    /*
    * Hinh
     */
    public final static Hinh HINH_DEFAULT = new Hinh(1, URLDefaultHinh);

    public static ConstantIndex Instance
            = new ConstantIndex();
    public static String SQLConnectivity_NoneParameterInit = null;
    public static String SQLConnectivity_NullInit = null;

    public static int BuocID_MAX;
    public static int TheID_MAX;

    public ConstantIndex() {
        ConstantIndex.SQLConnectivity_NoneParameterInit
                = "\n There is no way to create a null SQLConnectivity."
                + "\n Try initialize a new one with "
                + "username, password, database name and port."
                + "\n See instruction\\DatabaseConnectivity.txt for more details.";
        ConstantIndex.SQLConnectivity_NullInit
                = "\n There is no way to create a SQLConnectivity with null parameters."
                + "\n Try initialize a new one with "
                + "username, password, database name and port."
                + "\n See instruction\\DatabaseConnectivity.txt for more details.";

        BuocID_MAX = 50;
        TheID_MAX = 50;

    }

    public static void defaultInit(SQLConnectivity con) {
        ControllerMau ctrlMau = new ControllerMau(con);
        ControllerHinh ctrlHinh = new ControllerHinh(con);

        try {
            ctrlMau.insert(MAU_DO);
            ctrlMau.insert(MAU_LA);
            ctrlMau.insert(MAU_TIM);
            ctrlMau.insert(MAU_VANG);
            ctrlMau.insert(MAU_DUONG);

            ctrlHinh.insert(DefaultHinh);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ImageIcon getButton(Bang info, int width, int height) {
        if (info.getMauID()
                == Model.ConstantIndex.MAU_DO.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLButton_Red,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_DUONG.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLButton_Blue,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_LA.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLButton_Green,
                            width, height));
        }
        if (info.getMauID()
                == Model.ConstantIndex.MAU_VANG.getMauID()) {
            return new ImageIcon(
                    Tools.ImageTool.getScaledImage(
                            Model.ConstantIndex.URLButton_Gold,
                            width, height));
        }
        return new ImageIcon(
                Tools.ImageTool.getScaledImage(
                        Model.ConstantIndex.URLButton_Purple,
                        width, height));
    }

    public static ImageIcon getOOO(Mau mau, int width, int height) {
        String url = ConstantIndex.URLDefaultOoO;
        if (mau != null) {
            if (mau.getMauID() == ConstantIndex.MAU_DUONG.getMauID()) {
                url = ConstantIndex.URLOOO_Blue;
            } else if (mau.getMauID() == ConstantIndex.MAU_LA.getMauID()) {
                url = ConstantIndex.URLOOO_Green;
            } else if (mau.getMauID() == ConstantIndex.MAU_TIM.getMauID()) {
                url = ConstantIndex.URLOOO_Purple;
            } else if (mau.getMauID() == ConstantIndex.MAU_VANG.getMauID()) {
                url = ConstantIndex.URLOOO_Gold;
            }
        }
        return new ImageIcon(
                Tools.ImageTool.getScaledImage(url, width, height));
    }
}
