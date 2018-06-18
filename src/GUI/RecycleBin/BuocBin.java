/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.RecycleBin;

import Controller.*;
import Model.*;
import java.util.ArrayList;

/**
 *
 * @author DAT
 */
public class BuocBin {

    private static ArrayList<Buoc> bin;
    private static ControllerThe ctrlThe;
    private static ControllerBuoc ctrlBuoc;

    private BuocBin() {
        bin = new ArrayList();
    }

    public static void setCon(SQLConnectivity SQLcon) {
        ctrlThe = new ControllerThe(SQLcon);
        ctrlBuoc = new ControllerBuoc(SQLcon);
    }

    protected static void init() {
        if (bin == null) {
            bin = new ArrayList();
        }
    }

    public static boolean isExist(int id) {
        init();
        for (int i = 0; i < bin.size(); i++) {
            if (bin.get(i).getTheID() == id) {
                return true;
            }
        }
        return false;
    }

    public static void throwToBin(Buoc buoc) {
        init();
        if (!isExist(buoc.getTheID())) {
            bin.add(buoc);
        }
    }

    public static void wash() throws Exception {
        init();
        for (int i = 0; i < bin.size(); i++) {
            System.out.println("Bin =" + bin.get(i).toString());
            ctrlBuoc.delete(
                    bin.get(i).getBuocID());
        }
        bin.clear();
    }

    public static void restore(int id) {
        init();
        for (int i = 0; i < bin.size(); i++) {
            if (bin.get(i).getTheID() == id) {
                bin.remove(i);
            }
            return;
        }
    }
}
