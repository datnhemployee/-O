/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.ControllerBang;
import Controller.SQLConnectivity;
import Controller.ControllerHinh;
import Controller.ControllerID;
import Controller.ControllerMau;
import Controller.ControllerThe;
import GUI.*;
import GUI.myComponent.MessageBox;
import Model.Bang;
import Model.ConstantIndex;
import Model.Hinh;
import Model.Mau;
import Model.SQLConnection;
import Model.The;
import Tools.FontTool;
import java.awt.Color;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAT
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                new Login().setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                new MessageBox(
                        "THÔNG BÁO LỖI",
                        e.toString()
                ).setVisible(true);
            }
        });

    }

}
