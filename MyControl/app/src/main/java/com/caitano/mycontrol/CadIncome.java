package com.caitano.mycontrol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by caitano on 01/05/16.
 */
public class CadIncome extends Activity {

    private EditText field_income;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_income);
    }

    public void clearField(View view){
        this.field_income = (EditText) findViewById(R.id.field_income);
        field_income.setText("");
    }

    public void sumIncome(View view){

    }
}
