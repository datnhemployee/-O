/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAT
 */
public class ReadTxt {

    public static ReadTxt Instance
            = new ReadTxt();

    public static final String currentFolderPath
            = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

    public ReadTxt() {
    }

    public boolean createNewFile(String path) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            return f.createNewFile();
        }
        return false;
    }

    public ArrayList<String> selectAll(String path) {
        ArrayList<String> result = new ArrayList<String>();
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(path);
            Scanner inp = new Scanner(fi, "UTF-8");
            while (inp.hasNextLine()) {
                result.add(inp.nextLine().replace(System.lineSeparator(), ""));
            }
        } catch (Exception e) {
            fi.close();
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public ArrayList<Integer> toIntArray(ArrayList<String> arr) {
        if (arr == null || arr.isEmpty()) {
            return new ArrayList();
        }
        ArrayList<Integer> result = new ArrayList();
        for (String e : arr) {
            result.add(Integer.parseInt(e));
        }
        return result;
    }

    public void update(ArrayList<Integer> arr, String path) throws Exception {

        if (!this.isFileExist(path)) {
            throw new Exception("File not exist.");
        }
        File file = new File(path);
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        for (Integer e : arr) {
            if (!insert(e, path)) {
                throw new Exception("\n Can not insert new one into file.");
            }
        }
    }

    public boolean insert(Integer e, String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("File not exist.");
        }
        OutputStream op = null;
        try {
            op = new BufferedOutputStream(Files.newOutputStream(
                    file.toPath(),
                    StandardOpenOption.APPEND));

            String contentInByte = e + System.lineSeparator();
//            System.out.printf("\n content: "+ e.toString());
            op.write(contentInByte.getBytes());
            op.flush();
            op.close();
            return true;
        } catch (Exception ex) {
            try {
                if (op != null) {
                    op.close();
                }
            } catch (Exception e1) {
                throw new Exception("\n Message: " + e1);
            }
            throw new Exception("\n Message: " + ex);
        }
    }

    public boolean isFileExist(String path) throws Exception {
        try {
            File temp = new File(path);
            if (temp.exists()
                    && temp.isFile()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception("\n It is not able check file exist."
                    + "\nMessage: " + e);
        }
    }
}
