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
public class OoO_The {

    protected int OOOID;
    protected int THEID;

    private OoO_The() {
    }

    public OoO_The(int OOOID, int THEID) {
        this.OOOID = OOOID;
        this.THEID = THEID;
    }

    public int getOOOID() {
        return OOOID;
    }

    public void setOOOID(int OOOID) {
        this.OOOID = OOOID;
    }

    public int getTHEID() {
        return THEID;
    }

    public void setTHEID(int THEID) {
        this.THEID = THEID;
    }

    @Override
    public String toString() {
        return "OoO_The{" + "OOOID=" + OOOID + ", THEID=" + THEID + '}';
    }

}
