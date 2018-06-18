/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import Tools.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author DAT
 */
public class ControllerID {

    public static ControllerID Instance = new ControllerID();

    private ControllerID() {
    }

    public ArrayList<Integer> addID(int id, ArrayList<Integer> arr) {
        if (arr != null) {
            if (!arr.contains(id)) {
                arr.add(id);
            }
        }
        return arr;
    }

    public boolean init(String path) throws Exception {
        if(!ReadTxt.Instance.isFileExist(path))
            return ReadTxt.Instance.createNewFile(path);
        return false;
    }

    public ArrayList<Integer> getAll(String path)
            throws Exception {
        if (!ReadTxt.Instance.isFileExist(path)) {
            init(path);
        }
       
        return ReadTxt.Instance.toIntArray(
                ReadTxt.Instance.selectAll(path));
    }

    public void update(ArrayList<Integer> arr, String path)
            throws Exception {
        if (!ReadTxt.Instance.isFileExist(path)) {
            init(path);
        }
        ReadTxt.Instance.update(arr, path);
    }

    public int getNextDeletedID(String path) throws Exception {
        if (!ReadTxt.Instance.isFileExist(path)) {
            init(path);
        }
        ArrayList<Integer> rs = getAll(path);
        if (rs != null && !rs.isEmpty()) {
            return rs.get(rs.size() - 1);
        }
        return -1;
    }

    public void usedID(int ID, String path, ArrayList<Integer> arr)
            throws Exception {
        if (!ReadTxt.Instance.isFileExist(path)) {
           init(path);
        }
        arr.remove(new Integer(ID));
        this.update(arr, path);
    }
}
