package com.cesi.romain.objet;

import java.io.Serializable;

/**
 * Created by romai on 31/01/2018.
 */

public class VGrille implements Serializable{
        int levl;
        int num;
        int done;

    public VGrille() {
    }

    public int getLevl() {
        return levl;
    }

    public void setLevl(int levl) {
        this.levl = levl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public VGrille (int levl , int num , int done ){
        this.done=done;
        this.num=num;
        this.levl=levl;
    }

}
