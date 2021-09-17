package com.example.prova_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Atividade responsável por carregar a tela de estatísticas*/
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /** Carrega as caixas de texto da tela*/
        EditText less19 = findViewById(R.id.txt_less19);
        EditText less59 = findViewById(R.id.txt_less59);
        EditText bigger60 = findViewById(R.id.txt_bigger60);

        /** Carrega a tela anterior*/
        Intent intent = this.getIntent();

        /** Carrega o texto com os valores das estatísticas*/
        String qt_less19 = intent.getStringExtra("qt_less19");
        String qt_less59 = intent.getStringExtra("qt_less59");
        String qt_bigger60 = intent.getStringExtra("qt_bigger60");

        /** Exibir os valores nas caixas de texto correspondentes*/
        less19.setText(qt_less19);
        less59.setText(qt_less59);
        bigger60.setText(qt_bigger60);
    }
}