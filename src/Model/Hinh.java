/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DAT
 */
public class Hinh {
    private int HinhID;
    private String Path;
    
    private Hinh(){}

    public Hinh(int HinhID, String DuongDan) {
        this.HinhID = HinhID;
        this.Path = DuongDan;
    }
    
    
    public int getHinhID() {
        return HinhID;
    }

    public void setHinhID(int HinhID) {
        this.HinhID = HinhID;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }
    
    public boolean equals(Hinh hinh){
        if (this == hinh) {
            return true;
        }
        if (hinh == null) {
            return false;
        }
        if (getClass() != hinh.getClass()) {
            return false;
        }
        if(hinh.getHinhID() == this.getHinhID()
                && hinh.getPath().equals(hinh.getPath()))
            return true;
        return false;
    }
    public static int getDefaultHinhID(){
        return 1;
    }
    
    @Override
    public String toString() {
        return "Hinh{" + "HinhID=" + HinhID + ", Path=" + Path + '}';
    }
}
