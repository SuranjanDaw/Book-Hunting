package com.example.android.mynetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView ins;
    private TextView bname;
    private TextView bauth;
    private EditText bookinput;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.searchBook);
        ins = findViewById(R.id.instruction);
        bname  = findViewById(R.id.bookTitle);
        bauth = findViewById(R.id.bookAuthor);
        bookinput  =findViewById(R.id.bookinput);

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String book = bookinput.getText().toString();
                bname.setText("Loading Books...");
                bauth.setText("");
                MyAsyncTask myAsyncTask = new MyAsyncTask(bname,bauth);
                myAsyncTask.execute(book);

            }
        });
        if(savedInstanceState != null){
            ins.setText(savedInstanceState.getString(TEXT_STATE));
        }

        }
        protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,ins.getText().toString());
        }
}