package com.example.harshitvaish.filewritingexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    public static final String FILE_NAME = "exmple.txt";
    EditText desc;
    Button btn, btn_rd;
    TextView textv;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        desc = findViewById(R.id.desc);
        textv = findViewById(R.id.text);
        btn = findViewById(R.id.btn);
        btn_rd = findViewById(R.id.btn_rd);
        ctx = getApplicationContext();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fileout = null;
                String text = desc.getText().toString();

                try {
                    fileout = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fileout.write(text.getBytes());
                    desc.getText().clear();
                    Toast.makeText(ctx, "FIle Saved Succesfully " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fileout != null) {
                        try {
                            fileout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        btn_rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bc = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;
                    while ((text = bc.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                    textv.setText(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


    }
}
