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
public class TheBin {

    private static ArrayList<The> bin;
    private static ControllerThe ctrlThe;

    private TheBin() {
        bin = new ArrayList();
    }

    public static void setCon(SQLConnectivity SQLcon) {
        ctrlThe = new ControllerThe(SQLcon);
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

    public static void throwToBin(The the) {
        init();
        if (!isExist(the.getTheID())) {
            bin.add(the);
        }
    }

    public static void wash() throws Exception {
        init();
        for (int i = 0; i < bin.size(); i++) {
            ctrlThe.delete(
                    bin.get(i).getTheID());
        }
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
