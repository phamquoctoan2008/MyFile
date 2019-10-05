package com.example.myfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_moi,btn_luu,btn_open;
    EditText edt_filename, edt_noidung;
   // Spinner spinner_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_luu = findViewById(R.id.button_luu);
        btn_moi = findViewById(R.id.button_moi);
        edt_filename = findViewById(R.id.edit_tenfile);
        edt_noidung = findViewById(R.id.edit_noidung);
        btn_open = findViewById(R.id.button_mo);


        btn_moi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_filename.setText("");
                edt_noidung.setText("");
            }
        });

        final ArrayList<String> filenamelist = new ArrayList<>();
        filenamelist.add("Hello");

        final Spinner sp_filemane = findViewById(R.id.spinnner_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, filenamelist);
        sp_filemane.setAdapter(adapter);
        sp_filemane.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edt_filename.setText(filenamelist.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = edt_filename.getText().toString();
                filenamelist.add(filename);
                SharedPreferences pref = getApplicationContext().getSharedPreferences(filename,0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("noidung",edt_noidung.getText().toString());
                editor.commit();
//
//
//
//                try{
//                    FileOutputStream fout = openFileOutput(filename, Context.MODE_PRIVATE);
//                    fout.write(edt_noidung.getText().toString().getBytes());
//                    fout.close();
//                }catch(Exception e){
//                    Toast.makeText(MainActivity.this,"Loi luu file.",Toast.LENGTH_LONG).show();
//                }
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filemane = edt_filename.getText().toString();
//


                SharedPreferences pref = getApplicationContext().getSharedPreferences(filemane,Context.MODE_PRIVATE);
                 edt_noidung.setText(pref.getString("noidung",null));
//
//                StringBuffer buffer = new StringBuffer();
////              String line = null;
//                try{
//                    FileInputStream fin = openFileInput(filemane);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
//                    while ((line = br.readLine()) != null)
//                        buffer.append(line).append("\n");
//                    edt_noidung.setText(buffer.toString());
//                } catch (IOException e){
//
//                }
            }
        });

//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myfile",0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("noidung",edt_noidung);
//        editor.commit();


    //    pref.getString("noidung",null);
    }
}
