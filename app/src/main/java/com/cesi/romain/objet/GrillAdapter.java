package com.cesi.romain.objet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cesi.romain.objet.VGrille;
import com.cesi.romain.sudoku.GrilleViewHolder;
import com.cesi.romain.sudoku.R;

import java.util.List;

/**
 * Created by romai on 31/01/2018.
 */

public class GrillAdapter extends ArrayAdapter<VGrille> {

    //tweets est la liste des models à afficher
    public GrillAdapter(Context context, List<VGrille> grille) {
        super(context, 0, grille);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_view_holder,parent, false);
        }

        GrilleViewHolder viewHolder =(GrilleViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new GrilleViewHolder();
            viewHolder.Level =  convertView.findViewById(R.id.Level);
            viewHolder.Numero =  convertView.findViewById(R.id.numero);
            viewHolder.Pourcent =  convertView.findViewById(R.id.pourcent);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        VGrille grille = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.Level.setText(" Level : "+String.valueOf(grille.getLevl()));
        viewHolder.Numero.setText(" Numero :"+String.valueOf(grille.getNum()));
        viewHolder.Pourcent.setText(" Reussite actuelle :"+String.valueOf(grille.getDone()+"%"));

        return convertView;
    }

}