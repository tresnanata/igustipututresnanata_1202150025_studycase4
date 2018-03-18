package com.example.khimi.igustipututresnanata_1202150025_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class cariGambarActivity extends AppCompatActivity {
    //deklarasi variabel
    ImageView hasilImage;
    ProgressDialog mProgressDialog;
    EditText textURL;
    Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        downloadButton = (Button) findViewById(R.id.buttonAsyncTask);
        hasilImage = (ImageView) findViewById(R.id.imageHasil);
        textURL = (EditText) findViewById(R.id.editTextURL);

        //mengeksekusi ini ketika button di klik
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageUrl = textURL.getText().toString();
                if (imageUrl.isEmpty()) {
                    Toast.makeText(cariGambarActivity.this, "Image URL Belum dimasukkan", Toast.LENGTH_SHORT).show();//akan menampilkan toast ketika button diklik tapi url belum dimasukkan
                } else {
                    new ImageDownloader().execute(imageUrl);
                }
            }
        });
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(cariGambarActivity.this);// Membuat dialog
            mProgressDialog.setTitle("Search Image"); // Melakukan set title pada progress dialog
            mProgressDialog.setMessage("Loading...");  //  Melakukan set pesan pada progress dialog
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();// Menampilkan dialog
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            Bitmap bitmap = null;
            String imageURL = URL[0];
            try {
                InputStream input = new java.net.URL(imageURL).openStream();// Download Image dari URL
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap; //melakukan return pada bitmap
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            hasilImage.setImageBitmap(result);  // mengeset bitmap yang didapat ke dalam ImageView
            mProgressDialog.dismiss();   // Menutup progress dialog
        }
    }
}