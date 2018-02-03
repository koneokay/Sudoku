package com.cesi.romain.objet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cesi.romain.sudoku.ChoixGrille;
import com.cesi.romain.sudoku.UneGrille;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by romai on 01/02/2018.
 */

public class GrilleDessin extends View implements View.OnTouchListener{
    Context context;
    String ChiffreAPlacer;
    int tailleFen;
    int c;
    int x=0;
    int y =0;
    String chiffre="";
    String[][] TabSudoku;
    Config[] TabSudokumodif;
    LinkedList<Carre> carres;
    LinkedList<StringChiffre> Strings;

    public GrilleDessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        carres= new LinkedList<Carre>();
        Strings= new LinkedList<StringChiffre>();
        Paint npaint = new Paint();
        npaint.setColor(Color.BLACK);
        String sudoku = "008203500009670408346050702430010059967005001000496203280034067703500904004107020";
         TabSudoku = new String[9][9];
         TabSudokumodif = new Config[81];
        int cpt = 0;
        for (int i = 0; i < TabSudoku.length; i++) {
            for (int j = 0; j < TabSudoku[i].length; j++) {
                String chaine = String.valueOf(sudoku.charAt(cpt));
                TabSudoku[i][j] = chaine;
                if(chaine.equals("0")==true) {
                    TabSudokumodif[cpt] = new Config(true,npaint);
                }else{
                    TabSudokumodif[cpt] = new Config(false,npaint);
                }
                cpt++;
            }
        }
        this.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
       final int x = (int) motionEvent.getX(); final int y =(int)motionEvent.getY();
        int id=0;
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                AlertDialog.Builder builder;

                for (Carre carre : carres)
                {

                    if((carre.x<=x && x<=carre.cx) && (carre.y<y && y<carre.cy)){
                        id=carres.indexOf(carre);
                    }
                }
                if (id>=82) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
//                    } else {
//                        builder = new AlertDialog.Builder(context);
//                    }
//                    builder.setTitle("Case select")
//                            .setMessage(" numéro :" + Strings.get(id - 1).Chiffre)
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with next page
//
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
                    ChiffreAPlacer=Strings.get(id - 1).Chiffre;
                }
                break;
            case MotionEvent.ACTION_UP:
                for (Carre carre : carres)
                {

                    if((carre.x<=x && x<=carre.cx) && (carre.y<y && y<carre.cy)){
                        id=carres.indexOf(carre);
                    }
                }
                if(id>0 && id<82 && ChiffreAPlacer.equals("")==false){
                    double D1=(id-1)/9;
                    double D2=(id-1)%9;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
//                    } else {
//                        builder = new AlertDialog.Builder(context);
//                    }
//                    builder.setTitle("Case lache")
//                            .setMessage(" dimension :" + String.valueOf((int)D1)+" "+ String.valueOf((int)D2))
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with next page
//
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
                    if(TabSudokumodif[id-1].modifiable==true) {
                        String valanterieur=TabSudoku[(int) D1][(int) D2];
                        TabSudoku[(int) D1][(int) D2] = ChiffreAPlacer;
                        AnalyseTab((int)D1,(int)D2, ChiffreAPlacer,valanterieur);
                        ChiffreAPlacer = "";
                    }
                }

               break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        this.invalidate();
        return true;
    }
    public void AnalyseTab(int D1,int D2,String Chiffre,String valanterieur){
        int cpterreur=0;
        Paint paintred = new Paint();
        paintred.setColor(Color.RED);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Paint paintgreen = new Paint();
        paintgreen.setColor(Color.GREEN);
        for(int i=0;i<TabSudoku.length;i++){
            int id=(i*9)+(D2);
            if(TabSudoku[i][D2].equals(Chiffre)){

                if(TabSudokumodif[id].modifiable==true) {

                    TabSudokumodif[id].paint = paintred;
                    cpterreur++;
                }else{
                   // TabSudokumodif[id].paint = paint;
                    cpterreur++;
                }
            }else{
               if(TabSudokumodif[id].modifiable && valanterieur.equals(TabSudoku[i][D2])) {
                   AnalyseTab(i,D2,valanterieur,valanterieur);
//                    TabSudokumodif[id].paint = paint;
                }
            }
        }
        for(int i=0;i<TabSudoku[D1].length;i++){
            int id=(i)+(D1*9);
            if(TabSudoku[D1][i].equals(Chiffre)){

                if(TabSudokumodif[id].modifiable==true) {
                    TabSudokumodif[id].paint = paintred;
                    cpterreur++;
                }else{

                   // TabSudokumodif[id].paint = paint;
                    cpterreur++;
                }
            }else{
                if(TabSudokumodif[id].modifiable && valanterieur.equals(TabSudoku[D1][i])) {
                    AnalyseTab(i,D2,valanterieur,valanterieur);
//                    TabSudokumodif[id].paint = paint;
                }
            }
        }
        int identifiant=(D1*9)+(D2);
        if(cpterreur==2){
            TabSudokumodif[identifiant].paint=paintgreen;
        }else if (cpterreur>2){
            TabSudokumodif[identifiant].paint=paintred;
        }

    }
    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        Paint npaint = new Paint();
        npaint.setColor(Color.BLACK);
        Paint npaintRED = new Paint();
        npaintRED.setColor(Color.RED);


        if (canvas.getHeight() > canvas.getWidth()) {
            tailleFen = canvas.getWidth();
            x = 0;
            y = 0;
        } else {
            tailleFen = canvas.getHeight();
            x = 0;
            y = 0;
        }
        carres.clear();
        Strings.clear();
        int cpt=0;
        if(carres.isEmpty()==true) {
            carres.add(new Carre(x, y, tailleFen, npaint));
            c = ((tailleFen - 17) / 9);
            int xini = x;
            for (int j = 0; j < 9; j++) {
                if (j == 0 || j == 3 || j == 6) {
                    y += 2;
                }
                y++;
                for (int i = 0; i < 9; i++) {
                    if (i == 0 || i == 3 || i == 6) {
                        x += 2;
                    }
                    x++;
                    //y+=c;
                    carres.add(new Carre(x, y, c));
                    if (TabSudokumodif[cpt].modifiable == false){
                        Strings.add(new StringChiffre(x + (c / 3), y + ((c / 3) * 2), TabSudoku[j][i],npaint));
                }else{
                        Strings.add(new StringChiffre(x + (c / 3), y + ((c / 3) * 2), TabSudoku[j][i],TabSudokumodif[cpt].paint));
                }
                    x += c;
                    cpt++;
                }
                y += c;
                x = xini;
            }
            for (int i = 1; i <= 9; i++) {
                carres.add(new Carre(x, tailleFen + 50 - 3, c, npaint));
                Strings.add(new StringChiffre(x + (c / 3), tailleFen + 50 + ((c / 3) * 2), String.valueOf(i),npaintRED));
                x += c + 3;
            }
        }
        for (Carre carre : carres)
            carre.draw(canvas);
        for (StringChiffre chiffre : Strings)
            if (chiffre.Chiffre.equals("0") == false)
                chiffre.draw(canvas);

    }
}
class Carre{
    int x,y,cx,cy;
    private Paint paint;

    public Carre(int x, int y, int c, Paint paint) {
        this.x = x;
        this.y = y;
        this.cx = c+x;
        this.cy = c+y;
        this.paint = paint;
    }

    public Carre(int x , int y, int c) {
        this.cx = x+c;this.cy = y+c; this.x = x; this.y = y;
        int R=(int)(Math.random()*256);
        int V=(int)(Math.random()*256);
        int B=(int)(Math.random()*256);
        paint=new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.rgb(255,255,255));
    }
    public void draw(Canvas canvas){
        canvas.drawRect(x,y,cx,cy,paint);
    }
}
class StringChiffre {
    int x, y;
    String Chiffre;
    public Paint paint;

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public StringChiffre(int x, int y, String chiffre, Paint paint) {
        this.x = x;
        this.y = y;
        Chiffre = chiffre;
        this.paint = paint;
        this.paint.setTextSize(40);

    }

    public StringChiffre(int x, int y, String Chiffre) {
        this.x = x;
        this.y = y;
        this.Chiffre = Chiffre;
        paint = new Paint();
        paint.setTextSize(40);
        //  this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(Chiffre, x, y, paint);
    }
}
    class Config {
        boolean modifiable;
        public Paint paint;
        public Config(boolean modifiable, Paint paint) {
            this.modifiable = modifiable;
            this.paint = paint;
        }
    }
