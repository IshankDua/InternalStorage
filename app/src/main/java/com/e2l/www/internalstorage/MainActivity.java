package com.e2l.www.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName,editTextData;
    TextView txtfilename,txtdata;
    Button saveButton,readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = (EditText) findViewById(R.id.edtfilename);
        editTextData = (EditText) findViewById(R.id.edtdata);
        txtfilename= (TextView) findViewById(R.id.txtfilename);
        txtdata = (TextView) findViewById(R.id.txtdata);
        saveButton = (Button) findViewById(R.id.btnsavetofile);
        readButton = (Button) findViewById(R.id.btnreadfile);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = editTextFileName.getText().toString();
                String data = editTextData.getText().toString();

                FileOutputStream fos;
                try {

                    fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), filename + "saved", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String filename = editTextFileName.getText().toString();
                    FileInputStream fin = openFileInput(filename);
                    InputStreamReader inputStreamReader = new InputStreamReader(fin);
                    BufferedReader br =new BufferedReader(inputStreamReader);

                    StringBuilder stringBuffer = new StringBuilder();

                    String line = null;

                    while((line = br.readLine()) != null){
                        stringBuffer.append(line);
                    }
                    fin.close();
                    inputStreamReader.close();
                    txtfilename.setText("File Name : -" +filename);
                    txtdata .setText("Data:- "+stringBuffer.toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });



    }
}
