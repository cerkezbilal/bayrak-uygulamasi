package com.bilal.karademir.bayrakquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
   Button buttonA,buttonB,buttonC,buttonD;
   TextView textViewDogru,textViewYanlis,textViewSoruSayisi;
   ImageView imageViewBayrak;
   Context context = this;
   private ArrayList<Bayraklar> sorularListe;
   private ArrayList<Bayraklar> yanlisSeceneklerListe;
   private Bayraklar dogruSoru;
   private Veritabani vt;

   //sayclar
   private int soruSayac = 0;
   private int yanlisSayac = 0;
   private int dogruSayac = 0;

   //Seçenekler

    private HashSet<Bayraklar> secenekleriKaristirmaListe = new HashSet<>();
    private  ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        tanimla();

        vt = new Veritabani(context);
        sorularListe = new BayraklarDao().rastgele5Getir(vt);
        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dogruKontrol(buttonA);
                sayacKontrol();

            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                sayacKontrol();

            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonC);
                sayacKontrol();

            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonD);
                sayacKontrol();

            }
        });



    }

    public void soruYukle(){
        textViewSoruSayisi.setText((soruSayac+1)+". SORU");


        dogruSoru = sorularListe.get(soruSayac);
        yanlisSeceneklerListe = new BayraklarDao().rastgele3YanlisSecenekGetir(vt,dogruSoru.getBayrak_id());
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim()
                ,"drawable",getPackageName()));
        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(2));

        seceneklerListe.clear();
        for(Bayraklar b : secenekleriKaristirmaListe){

            seceneklerListe.add(b);
        }

        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());


    }


    public void tanimla(){

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayisi = findViewById(R.id.textViewSoruSayisi);
        imageViewBayrak = findViewById(R.id.imageViewBayrak);

    }

    public void dogruKontrol(Button button){

        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        if(buttonYazi.equals(dogruCevap)){

            dogruSayac++;

        }else {

            yanlisSayac++;

        }

        textViewDogru.setText("Doğru : "+dogruSayac);
        textViewYanlis.setText("Yanlış : "+yanlisSayac);

    }

    public void sayacKontrol(){
        soruSayac++;
        if(soruSayac != 5){
            soruYukle();
        }else {
            Intent intent = new Intent(context,ResultActivity.class);
            intent.putExtra("dogruSayac",dogruSayac);
            startActivity(intent);
            finish();
        }
    }

}
