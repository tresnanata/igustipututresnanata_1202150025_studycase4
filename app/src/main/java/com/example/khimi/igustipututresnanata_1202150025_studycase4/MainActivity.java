package com.example.khimi.igustipututresnanata_1202150025_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //intent untuk berpindah ke cariGambarActivity.class
    public void tampilGambar(View view) {
        Intent gambar = new Intent(getApplicationContext(),cariGambarActivity.class);
        startActivity(gambar);
    }
    //intent untuk berpindah ke listNamactivity.class
    public void tampilNama(View view) {
        Intent listNama = new Intent(getApplicationContext(),listNamaActivity.class);
        startActivity(listNama);
    }
}
