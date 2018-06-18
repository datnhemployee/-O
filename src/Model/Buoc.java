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
public class Buoc {

    protected int BuocID;
    protected int TheID;
    protected String NoiDung;
    protected boolean TrangThaiBuoc;

    public int getBuocID() {
        return BuocID;
    }

    public void setBuocID(int BuocID) {
        this.BuocID = BuocID;
    }

    public int getTheID() {
        return TheID;
    }

    public void setTheID(int TheID) {
        this.TheID = TheID;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public boolean isTrangThaiBuoc() {
        return TrangThaiBuoc;
    }

    public void setTrangThaiBuoc(boolean TrangThaiBuoc) {
        this.TrangThaiBuoc = TrangThaiBuoc;
    }

    private Buoc() {
    }

    public Buoc(
            int id,
            int theid,
            String noidung,
            boolean trangthai) {
        this.setBuocID(id);
        this.setTheID(theid);
        this.setNoiDung(noidung);
        this.setTrangThaiBuoc(trangthai);
    }

    @Override
    public String toString() {
        return "Buoc{" + "BuocID=" + BuocID + ", TheID=" + TheID + ", NoiDung=" + NoiDung + ", TrangThaiBuoc=" + TrangThaiBuoc + '}';
    }
    
}
