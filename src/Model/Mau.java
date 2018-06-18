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
public class Mau {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.MauID;
        hash = 97 * hash + this.Red;
        hash = 97 * hash + this.Green;
        hash = 97 * hash + this.Blue;
        hash = 97 * hash + this.Alpha;
        return hash;
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
        final Mau other = (Mau) obj;
        if (this.MauID != other.MauID) {
            return false;
        }
        if (this.Red != other.Red) {
            return false;
        }
        if (this.Green != other.Green) {
            return false;
        }
        if (this.Blue != other.Blue) {
            return false;
        }
        return this.Alpha == other.Alpha;
    }

    
    private int MauID;
    private int Red;

    private Mau(){}
    public Mau(int MauID, int Red, int Green, int Blue, int Alpha) {
        this.MauID = MauID;
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
        this.Alpha = Alpha;
    }
    private int Green;
    private int Blue;
    private int Alpha;
    
    public int getMauID() {
        return MauID;
    }

    public void setMauID(int MauID) {
        this.MauID = MauID;
    }

    public int getRed() {
        return Red;
    }

    public void setRed(int Red) {
        this.Red = Red;
    }

    public int getGreen() {
        return Green;
    }

    public void setGreen(int Green) {
        this.Green = Green;
    }

    public int getBlue() {
        return Blue;
    }

    public void setBlue(int Blue) {
        this.Blue = Blue;
    }

    public int getAlpha() {
        return Alpha;
    }

    public void setAlpha(int Alpha) {
        this.Alpha = Alpha;
    }
    
    @Override
    public String toString() {
        return "Mau{" 
                + "MauID=" + MauID 
                + ", Red=" + Red 
                + ", Green=" + Green 
                + ", Blue=" + Blue 
                + ", Alpha=" + Alpha 
                + '}';
    }
}
