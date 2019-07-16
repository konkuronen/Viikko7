package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextInputEditText tiedostonimi;
    TextInputEditText kirjoitus;
    TextView tekstikentta;
    TextView tekstinimi;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tekstikentta = findViewById(R.id.tekstikentta);
        kirjoitus = findViewById(R.id.kirjoitus);
        tiedostonimi = findViewById(R.id.tiedostonimi);
        tekstinimi = findViewById(R.id.tekstinimi);
        context = MainActivity.this;
        System.out.println(context.getFilesDir());
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String teksti = kirjoitus.getText().toString().trim();
            String nimi = tiedostonimi.getText().toString().trim();
            if (tiedostonimi.getText() != null){
                tekstinimi.setText(nimi);
            } if (kirjoitus.getText() != null) {
                tekstikentta.setText(teksti);
            }
            kirjoitus.setText(null);
            tiedostonimi.setText(null);
            return true;
        }
        return false;
    }

    public void readFile(View v)  {
        try {
            String nimi = tekstinimi.getText().toString();
            InputStream ins = context.openFileInput(nimi);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                tekstikentta.setText(s);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(tekstinimi.getText().toString(), Context.MODE_PRIVATE));

            String s = "";

            s = tekstikentta.getText().toString();

            osw.write(s);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }

}
