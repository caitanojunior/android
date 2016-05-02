package com.caitano.mycontrol;

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

    public void StartScreenCadIncome(View view){
        Intent cad_income = new Intent(this, CadIncome.class);
        startActivity(cad_income);
    }
}
