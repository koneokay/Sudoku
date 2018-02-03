package com.cesi.romain.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener {
    Activity level;
    Button level1;
    Button level2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LinearLayout huhu = new LinearLayout(getBaseContext());
//        Button bouton = new Button(getBaseContext());
//        huhu.addView(bouton);
//        setContentView(huhu);
        level=this;
        level1 =  findViewById(R.id.buttonlvl1);
        level2 =  findViewById(R.id.buttonlvl2);
        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent defineIntent = new Intent(level, ChoixGrille.class);
        // objet qui vas nous permettre de passe des variables ici la variable passInfo
        Bundle objetbunble = new Bundle();
        switch(v.getId()) {
            case R.id.buttonlvl1:
                objetbunble.putString("level","1");
                break;
            case R.id.buttonlvl2:
                objetbunble.putString("level","2");
                break;
        }
        // on passe notre objet a notre activities
        defineIntent.putExtras(objetbunble );
        // on appelle notre activité !!!
        level.startActivity(defineIntent);
    }

}
