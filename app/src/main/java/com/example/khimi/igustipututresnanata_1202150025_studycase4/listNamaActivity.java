package com.example.khimi.igustipututresnanata_1202150025_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class listNamaActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private AddItemToListView mAddItemToListView;
    private ListView mListView;
    private Button mAsyncTask;
    private String [] listMahasiswa= {
            "Tresna","Khim","Hime","Ericko","Lim","Milea",
            "Lara","Barry","Dilan"};//membuat string array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.itemListView);
        mAsyncTask = (Button) findViewById(R.id.buttonAsyncTask);
        mListView.setVisibility(View.GONE);//membuat progress bar visible saat aplikasi di run

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));//Membuat Adapter
        mAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //process adapter dengan AsyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView  extends AsyncTask<Void, String, Void> {
        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(listNamaActivity.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion

            //untuk progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //akan menghandle cancel asyntask ketika mengklik button negative
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : listMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer status = (int) ((counter/(float)listMahasiswa.length)*100);
            mProgressBar.setProgress(status);
            mProgressDialog.setProgress(status);
            mProgressDialog.setMessage(String.valueOf(status+"%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);//menutup/menyembunyikan progress bar
            mProgressDialog.dismiss();//menghapus progress dialog
            mListView.setVisibility(View.VISIBLE);
        }
    }

}
