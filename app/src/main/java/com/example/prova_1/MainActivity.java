package com.example.prova_1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Variáveis globais da lista*/
    EditText years_list;
    EditText sex_list;
    RadioButton igg_positive_list;
    RadioButton igg_negative_list;
    TextView txt_years_list;
    TextView txt_sex_list;
    TextView txt_igg_list;
    ImageView igg_img;

    /** Variáveis globais da tela*/
    ListView lv;
    ArrayList<ItensList> itensLists;
    MyAdapter adapter;

    /** Variáveis globais do cadastro*/
    EditText name;
    EditText yearsOld;
    EditText sex;
    RadioButton igg_positive;
    RadioButton igg_negative;

    /** Variável auxiliar*/
    int pos_list;

    /** Método de carregar a tela*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Atribuindo valores da view nas variáveis criadas*/
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_clean_list = findViewById(R.id.btn_clear);
        Button btn_estat = findViewById(R.id.btn_est);

        name = findViewById(R.id.txt_name);
        yearsOld = findViewById(R.id.txt_yearsOld);
        sex = findViewById(R.id.txt_sex);

        igg_positive = findViewById(R.id.radioPositive);
        igg_negative = findViewById(R.id.radioNegative);

        igg_img = findViewById(R.id.image_igg);

        lv = findViewById(R.id.list_item);

        /** ListView da tela*/
        itensLists = new ArrayList<>();

        adapter = new MyAdapter(this, R.layout.list_itens, itensLists);

        lv.setAdapter(adapter);

        /** Evento para quando o botão de adicionar é clicado*/
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Verifica se todos os campos estão preenchidos*/
                if(!name.getText().toString().isEmpty() &&
                        !yearsOld.getText().toString().isEmpty() &&
                        !sex.getText().toString().isEmpty() &&
                        (igg_positive.isChecked() || igg_negative.isChecked()))
                {
                    /** Cria uma outra lista e adiciona os itens dos campos de cadastro nela*/
                    ItensList iL = new ItensList();

                    iL.setName(name.getText().toString());
                    iL.setYearsOld(Integer.parseInt(yearsOld.getText().toString()));
                    iL.setSex(sex.getText().toString());

                    iL.setIgg(igg_positive.isChecked());

                    /** Adiciona esses itens à listView*/
                    itensLists.add(iL);

                    /** Notifica o adaptador*/
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), "Item Adicionado.", Toast.LENGTH_LONG).show();

                    /** Reseta os valores dos campos de cadastro*/
                    name.setText("");
                    yearsOld.setText("");
                    sex.setText("");
                    igg_negative.setChecked(false);
                    igg_positive.setChecked(false);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Itens incompletos", Toast.LENGTH_LONG).show();
                }

            }
        });

        /** Evento para quando um item da lista é clicado*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                /** Preencha os valores das variáveis globais com o item da lista que foi selecionado*/
                years_list = view.findViewById(R.id.txt_yearsOld_list);
                sex_list = view.findViewById(R.id.txt_sex_list);
                igg_positive_list = view.findViewById(R.id.radioPositive_list);
                igg_negative_list = view.findViewById(R.id.radioNegative_list);

                txt_years_list = view.findViewById(R.id.txt_yearsOld_title_list);
                txt_sex_list = view.findViewById(R.id.txt_sex_title_list);
                txt_igg_list = view.findViewById(R.id.radio_title_list);

                /** Verifica se os outros atributos estão visíveis ou não*/
                if(years_list.getVisibility() == View.VISIBLE){

                    /** Se estão visíveis, eles ficam invisíveis*/
                    years_list.setVisibility(View.GONE);
                    sex_list.setVisibility(View.GONE);
                    igg_positive_list.setVisibility(View.GONE);
                    igg_negative_list.setVisibility(View.GONE);

                    txt_years_list.setVisibility(View.GONE);
                    txt_sex_list.setVisibility(View.GONE);
                    txt_igg_list.setVisibility(View.GONE);
                }
                else{
                    /** Se estão invisíveis, eles ficam visíveis*/
                    years_list.setVisibility(View.VISIBLE);
                    sex_list.setVisibility(View.VISIBLE);
                    igg_positive_list.setVisibility(View.VISIBLE);
                    igg_negative_list.setVisibility(View.VISIBLE);

                    txt_years_list.setVisibility(View.VISIBLE);
                    txt_sex_list.setVisibility(View.VISIBLE);
                    txt_igg_list.setVisibility(View.VISIBLE);
                }
            }
        });

        /** Se um item foi segurado por um tempo, ele preenche a variável com a posição referente*/
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                pos_list = pos;
                return false;
            }
        });

        /** Evento para quando o botão de limpar a lista é clicado*/
        btn_clean_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                /** Pergunta se o usuário quer realmente apagar a lista*/
                builder.setTitle("Limpar lista");
                builder.setMessage("Realmente deseja excluir todos os itens da lista?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itensLists.clear();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Itens excluído", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Exclusão cancelada", Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();
            }
        });

        /** Associa a lista com o menu de contexto*/
        registerForContextMenu(lv);

        /** Evento para quando o botão de estatistica é clicado*/
        btn_estat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Cálculo*/
                double qt_less19 = 0;
                double qt_less59 = 0;
                double qt_bigger60 = 0;

                int qtd = 0;

                for (ItensList itens : itensLists){
                    if (itens.getIgg()){
                        if (itens.getYearsOld() <= 19)
                            qt_less19++;
                        else if (itens.getYearsOld() >= 20 && itens.getYearsOld() <= 59)
                            qt_less59++;
                        else
                            qt_bigger60++;
                        qtd++;
                    }
                }

                /** Carrega a segunda tela*/
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra("qt_less19", Double.toString(Math.round(qt_less19*100/qtd)) + "%");
                intent.putExtra("qt_less59", Double.toString(Math.round(qt_less59*100/qtd)) + "%");
                intent.putExtra("qt_bigger60", Double.toString(Math.round(qt_bigger60*100/qtd)) + "%");

                startActivity(intent);
            }
        });

    }

    /** Infla o menu de contexto*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_options, menu);
    }

    /** Carregado quando um item do menu de contexto é selecionado*/
    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.remove_item:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Excluir esse item");
                builder.setMessage("Realmente deseja excluir o item da lista?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itensLists.remove(pos_list);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Item excluído", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Exclusão cancelada", Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();

                break;
        }

        return  super.onContextItemSelected(item);
    }
}