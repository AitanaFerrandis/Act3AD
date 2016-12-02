package com.example.aitana.ej3a_ficheros_ad;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    Button btnMostrar, btnEscribe;
    TextView txtVMuestra;
    EditText editxtFich;
    String FILE_NAME = "fich.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnEscribe = (Button) findViewById(R.id.btnEscribe);
        txtVMuestra = (TextView) findViewById(R.id.txtVMuestra);
        editxtFich = (EditText) findViewById(R.id.editxtFich);



        btnEscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editxtFich.getText().toString().equals("")){
                    //si el edittext tiene contenido, creamos un fichero de salida para escribirlo
                    try {
                        //con el mode_append hacemos que se escriba encima de lo que ya hay y no perder lo que ya tiene el fichero
                        FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                        String cadenaOutput = new String(editxtFich.getText().toString());
                        DataOutputStream dos = new DataOutputStream(fos);
                        dos.writeBytes(cadenaOutput);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    //si el edittext está vacío le pedimos que introduzca lo que desee escribir
                    Toast.makeText(getApplicationContext(), "Por favor, introduzca lo que desee escribir en el fichero",
                    Toast.LENGTH_LONG).show();
                }
            }
        });


        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    //Resources myResources = getResources();
                    //InputStream myFile = myResources.openRawResource(R.raw.fichero);

                    //leemos el fichero que previamente hemos escrito (o ya esta escrito) y lo mostramos en el textview
                    FileInputStream fin = openFileInput(FILE_NAME);
                    DataInputStream dis = new DataInputStream(fin);
                    byte[] buff = new byte[1000];
                    //myFile.read(buff);
                    dis.read(buff);
                    txtVMuestra.setText(new String(buff));
                    //Toast.makeText(getApplicationContext(), "He leído: " + new String(buff), Toast.LENGTH_LONG).show();
                    //myFile.close();
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




    }

}
