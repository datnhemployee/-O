/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author DAT
 */
public class Bang {



    private int BangID;
    private String Ten;

    private String MoTa;

    private Date NgayBao_GanNhat;
    private Date NgayKetThuc_XaNhat;
    private int SoTheHoanTat;
    private int TongSoThe;

    private int MauID;
    private int HinhID;

    private Bang() {
    }

    public Bang(
            int BangID, 
            String Ten, 
            String MoTa, 
            int MauID) {
        this(BangID, Ten, MoTa,
                null, null,
                0, 0, MauID,
                Hinh.getDefaultHinhID());
    }

    public Bang(
            int BangID,
            String Ten,
            String MoTa,
            int MauID,
            int HinhID) {
        this(BangID, Ten, MoTa,
                null, null,
                0, 0, MauID, HinhID);
    }

    public Bang(
            int BangID,
            String Ten,
            String MoTa,
            Date NgayBao_GanNhat,
            Date NgayKetThuc_XaNhat,
            int SoTheHoanTat,
            int TongSoThe,
            int MauID,
            int HinhID) {
        this.BangID = BangID;
        this.Ten = Ten;
        this.MoTa = MoTa;
        this.NgayBao_GanNhat = NgayBao_GanNhat;
        this.NgayKetThuc_XaNhat = NgayKetThuc_XaNhat;
        this.SoTheHoanTat = SoTheHoanTat;
        this.TongSoThe = TongSoThe;
        this.MauID = MauID;
        this.HinhID = HinhID;
    }

    public int getBangID() {
        return BangID;
    }

    public void setBangID(int BangID) {
        this.BangID = BangID;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public Date getNgayBao_GanNhat() {
        return NgayBao_GanNhat;
    }

    public void setNgayBao_GanNhat(Date NgayBao_GanNhat) {
        this.NgayBao_GanNhat = NgayBao_GanNhat;
    }

    public Date getNgayKetThuc_XaNhat() {
        return NgayKetThuc_XaNhat;
    }

    public void setNgayKetThuc_XaNhat(Date NgayKetThuc_XaNhat) {
        this.NgayKetThuc_XaNhat = NgayKetThuc_XaNhat;
    }

    public int getSoTheHoanTat() {
        return SoTheHoanTat;
    }

    public void setSoTheHoanTat(int SoTheHoanTat) {
        this.SoTheHoanTat = SoTheHoanTat;
    }

    public int getTongSoThe() {
        return TongSoThe;
    }

    public void setTongSoThe(int TongSoThe) {
        this.TongSoThe = TongSoThe;
    }

    public int getMauID() {
        return MauID;
    }

    public void setMauID(int MauID) {
        this.MauID = MauID;
    }

    public int getHinhID() {
        return HinhID;
    }

    public void setHinhID(int HinhID) {
        this.HinhID = HinhID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }
    
        @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bang other = (Bang) obj;
        if (this.BangID != other.BangID) {
            return false;
        }
        if (this.SoTheHoanTat != other.SoTheHoanTat) {
            return false;
        }
        if (this.TongSoThe != other.TongSoThe) {
            return false;
        }
        if (this.MauID != other.MauID) {
            return false;
        }
        if (this.HinhID != other.HinhID) {
            return false;
        }
        if (!Objects.equals(this.Ten, other.Ten)) {
            return false;
        }
        if (!Objects.equals(this.MoTa, other.MoTa)) {
            return false;
        }
        if (!Objects.equals(this.NgayBao_GanNhat, other.NgayBao_GanNhat)) {
            return false;
        }
        if (!Objects.equals(this.NgayKetThuc_XaNhat, other.NgayKetThuc_XaNhat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bang{" + "BangID=" + BangID + ", Ten=" + Ten + ", MoTa=" + MoTa + ", NgayBao_GanNhat=" + NgayBao_GanNhat + ", NgayKetThuc_XaNhat=" + NgayKetThuc_XaNhat + ", SoTheHoanTat=" + SoTheHoanTat + ", TongSoThe=" + TongSoThe + ", MauID=" + MauID + ", HinhID=" + HinhID + '}';
    }

}
