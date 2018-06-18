/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author DAT
 */
public class The {

   
    protected int TheID;
    protected int BangID;
    protected String Ten;
    protected Date ThoiGianBao_KeTiep;
    protected Date HanChot;
    protected int TongSoBuoc;
    protected int SoBuocHoanTat;

    private The() {
    }

    public The(int TheID, int BangID,Date HanChot, String Ten) {
        this(TheID, BangID, Ten, null, HanChot, 0, 0);
    }

    public The(
            int TheID,
            int BangID,
            String Ten,
            Date ThoiGianBao_KeTiep,
            Date HanChot,
            int TongSoBuoc,
            int SoBuocHoanTat) {
        this.TheID = TheID;
        this.BangID = BangID;
        this.Ten = Ten;
        this.ThoiGianBao_KeTiep = ThoiGianBao_KeTiep;
        this.HanChot = HanChot;
        this.TongSoBuoc = TongSoBuoc;
        this.SoBuocHoanTat = SoBuocHoanTat;
    }

    public int getTheID() {
        return TheID;
    }

    public void setTheID(int TheID) {
        this.TheID = TheID;
    }

    public int getBangID() {
        return BangID;
    }

    public void setBangID(int BangID) {
        this.BangID = BangID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getThoiGianBao_KeTiep() {
        return ThoiGianBao_KeTiep;
    }

    public void setThoiGianBao_KeTiep(Date ThoiGianBao_KeTiep) {
        this.ThoiGianBao_KeTiep = ThoiGianBao_KeTiep;
    }

    public Date getHanChot() {
        return HanChot;
    }

    public void setHanChot(Date HanChot) {
        this.HanChot = HanChot;
    }

    public int getTongSoBuoc() {
        return TongSoBuoc;
    }

    public void setTongSoBuoc(int TongSoBuoc) {
        this.TongSoBuoc = TongSoBuoc;
    }

    public int getSoBuocHoanTat() {
        return SoBuocHoanTat;
    }

    public void setSoBuocHoanTat(int SoBuocHoanTat) {
        this.SoBuocHoanTat = SoBuocHoanTat;
    }
    
     @Override
    public String toString() {
        return "The{" + "TheID=" + TheID + ", BangID=" + BangID + ", Ten=" + Ten + ", ThoiGianBao_KeTiep=" + ThoiGianBao_KeTiep + ", HanChot=" + HanChot + ", TongSoBuoc=" + TongSoBuoc + ", SoBuocHoanTat=" + SoBuocHoanTat + '}';
    }

}
