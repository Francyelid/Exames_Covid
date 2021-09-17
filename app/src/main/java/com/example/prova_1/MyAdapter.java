package com.example.prova_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/** Classe do adaptador personalisado entre o ItensList e a tela da lista*/
public class MyAdapter extends ArrayAdapter<ItensList> {

    /** Variáveis globais*/
    private ArrayList<ItensList> itensLists;
    private Context context;
    private int resource;

    /** Construtor do adaptador*/
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItensList> itensLists){
        super(context, resource, itensLists);

        this.context = context;
        this.resource = resource;
        this.itensLists = itensLists;
    }

    /** Método getView editado para carregar os itens da classe nas views da tela*/
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        ViewHolder holder;

        /** Verifica se a view carregou antes*/
        if (convertView == null){
            /** Se não, ele carrega os itens da tela no holder*/
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();

            holder.name = convertView.findViewById(R.id.txt_name_list);
            holder.yearsOld = convertView.findViewById(R.id.txt_yearsOld_list);
            holder.sex = convertView.findViewById(R.id.txt_sex_list);

            holder.igg_positive = convertView.findViewById(R.id.radioPositive_list);
            holder.igg_negative = convertView.findViewById(R.id.radioNegative_list);

            holder.igg_img = convertView.findViewById(R.id.image_igg);

            convertView.setTag(holder);
        }
        else{
            /** Se sim, ele cria uma tag*/
            holder = (ViewHolder)convertView.getTag();
        }

        /** Define os valores nas views correspondentes*/
        ItensList item = itensLists.get(position);

        holder.name.setText(item.getName());
        holder.yearsOld.setText(Integer.toString(item.getYearsOld()));
        holder.sex.setText(item.getSex());

        holder.igg_negative.setChecked(!item.getIgg());
        holder.igg_positive.setChecked(item.getIgg());

        holder.igg_img.setImageResource(holder.igg_positive.isChecked() ? R.drawable.positive : R.drawable.negative);

        /** Retorna essa view*/
        return convertView;
    }

    /**Classe das variáveis que serão mudadas*/
    static class ViewHolder
    {
        TextView name;
        TextView yearsOld;
        TextView sex;

        RadioButton igg_positive;
        RadioButton igg_negative;

        ImageView igg_img;
    }
}
