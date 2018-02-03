package com.cesi.romain.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cesi.romain.objet.VGrille;

import java.util.Timer;
import java.util.TimerTask;

public class UneGrille extends Activity {
    TextView levelnum;
    TextView Progress;
    TextView timer;
    int minutes=0;
    int secondes=0;
    boolean paused=false;
    Timer t = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_une_grille);
        Bundle objetbunble = this.getIntent().getExtras();
        // récupération de la valeur!!
        //String InfoPasse=objetbunble.description() ;
        VGrille Info=(VGrille) objetbunble.getSerializable("LaGrille");
        levelnum = findViewById(R.id.LevNum);
        Progress = findViewById(R.id.Progress);

         levelnum.setText("Niveau :"+String.valueOf(Info.getLevl())+" - Numero :"+String.valueOf(Info.getNum()));
         Progress.setText("Progression : "+String.valueOf(Info.getDone()+"%"));

        //Declare the timer

        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!paused) {
                            timer = findViewById(R.id.Timer);

                            if (secondes == 60) {
                                timer.setText(String.valueOf(minutes) + ":" + String.valueOf(secondes));

                                secondes = 0;
                                minutes++;

                            } else {
                                timer.setText(String.valueOf(minutes) + ":" + String.valueOf(secondes));
                                secondes++;
                            }
                        }


                    }

                });
            }

        }, 0, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        paused=false;
    }
    @Override
    public void onPause() {
        super.onPause();
        paused=true;
    }

}