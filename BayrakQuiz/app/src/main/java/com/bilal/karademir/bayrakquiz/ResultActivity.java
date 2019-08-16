package com.bilal.karademir.bayrakquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewSonuc,textViewYuzdeSonuc;
    Button buttonTekrar;
    Context context = this;

    private int dogruSayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonTekrar = findViewById(R.id.buttonTekrar);
        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc);

        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        textViewSonuc.setText(dogruSayac+" DOĞRU "+(5-dogruSayac)+" YANLIŞ");
        textViewYuzdeSonuc.setText("% "+(dogruSayac*20));

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,QuizActivity.class));
                finish();
            }
        });


    }
}
