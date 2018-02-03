package com.cesi.romain.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cesi.romain.objet.GrillAdapter;
import com.cesi.romain.objet.VGrille;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoixGrille extends Activity {
    ListView mListView;
    List<VGrille> grille= new ArrayList<VGrille>();
    Activity CGrille;
  //  private VGrille[] levels=new VGrille[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_grille);
        CGrille=this;
        mListView = (ListView) findViewById(R.id.listView);
        Bundle objetbunble = this.getIntent().getExtras();
        // récupération de la valeur!!
        //String InfoPasse=objetbunble.description() ;
        String Info= objetbunble.getString("level");
        // on afffiche l'information dans l'edittext
        LinearLayout layout = new LinearLayout(getBaseContext());

        switch(Info) {
            case "1":
                for(int i=0;i<100;i++) {
                    Double d = new Double(Math.random()*100);
                    VGrille grilletemp = new VGrille();
                    grilletemp.setLevl(1);
                    grilletemp.setNum(i+1);
                    grilletemp.setDone(d.intValue());
                    grille.add( grilletemp);
                 //   grille.add( new VGrille(1,i,d.intValue()));

                   // levels[i]= new VGrille(1,i,d.intValue());
//                    levels[i]="1-"+i+1;
                }
                break;
            case "2":
                for(int i=0;i<10;i++) {
                    Double d = new Double(Math.random()*100);
                    grille.add( new VGrille(2,i+1,d.intValue()));
                    //levels[i]="2-"+i+1;
                }
                break;
        }

        GrillAdapter adapter = new GrillAdapter(ChoixGrille.this, grille);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder;
                final VGrille grilleact=grille.get(i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ChoixGrille.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ChoixGrille.this);
                }
                builder.setTitle("Level "+String.valueOf(grilleact.getLevl())+" Numero "+String.valueOf(grilleact.getNum()) )
                        .setMessage("Vous avez selectionner :\n"+ "Level "+String.valueOf(grilleact.getLevl())+" - Numero "+String.valueOf(grilleact.getNum()) +"\n Vous avez fini se niveau à "+String.valueOf(grilleact.getDone())+"%" )
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with next page

                                Intent defineIntent = new Intent(CGrille,UneGrille.class);
                                // objet qui vas nous permettre de passe des variables ici la variable passInfo
                                Bundle objetbunble = new Bundle();
                                objetbunble.putSerializable("LaGrille",(Serializable)grilleact );
                                // on passe notre objet a notre activities
                                defineIntent.putExtras(objetbunble );
                                // on appelle notre activité !!!
                                CGrille.startActivity(defineIntent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
//        final ArrayAdapter<VGrille> adapter = new ArrayAdapter<VGrille>(ChoixGrille.this,
//                android.R.layout.simple_list_item_1, levels);
//        mListView.setAdapter(adapter);
           // setContentView(layout);
    }
}
