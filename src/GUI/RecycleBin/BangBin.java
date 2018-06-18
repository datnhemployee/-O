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
public class BangBin {

    private static ArrayList<Bang> bin;
    private static ControllerBang ctrlBang;

    private BangBin() {
        bin = new ArrayList();
    }

    public static void setCon(SQLConnectivity SQLcon) {
        ctrlBang = new ControllerBang(SQLcon);
    }

    protected static void init() {
        if (bin == null) {
            bin = new ArrayList();
        }
    }

    public static boolean isExist(int id) {
        init();
        for (int i = 0; i < bin.size(); i++) {
            if (bin.get(i).getBangID() == id) {
                return true;
            }
        }
        return false;
    }

    public static void throwToBin(Bang bang) {
        init();
        if (!isExist(bang.getBangID())) {
            bin.add(bang);
        }
    }

    public static void wash() throws Exception {
        init();
        for (int i = 0; i < bin.size(); i++) {
            ctrlBang.delete(
                    bin.get(i).getBangID());
        }
        bin.clear();
    }

    public static void restore(int id) {
        init();
        for (int i = 0; i < bin.size(); i++) {
            if (bin.get(i).getBangID() == id) {
                bin.remove(i);
            }
            return;
        }
    }
}
