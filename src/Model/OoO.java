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
public class OoO {

    protected int OOOID;
    protected Date NgayBao;
    
    private OoO() {
    }

    public OoO(int OOOID, Date NgayBao) {
        this.OOOID = OOOID;
        this.NgayBao = NgayBao;
    }

    public int getOOOID() {
        return OOOID;
    }

    public void setOOOID(int OOOID) {
        this.OOOID = OOOID;
    }

    public Date getNgayBao() {
        return NgayBao;
    }

    public void setNgayBao(Date NgayBao) {
        this.NgayBao = NgayBao;
    }

    @Override
    public String toString() {
        return "OoO{" + "OOOID=" + OOOID + ", NgayBao=" + NgayBao + '}';
    }
    
}
