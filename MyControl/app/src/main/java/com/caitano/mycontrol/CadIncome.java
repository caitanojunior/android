package com.caitano.mycontrol;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * Created by caitano on 01/05/16.
 */
public class CadIncome extends Activity implements TextWatcher {

    private EditText field_income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_income);
        field_income = (EditText) findViewById(R.id.field_income);
        field_income.setInputType(InputType.TYPE_CLASS_NUMBER);
        field_income.addTextChangedListener(new TextWatcher() {

            private boolean isUpdating = false;

            // Pega a formatacao do sistema, se for Brasil R$ se EUA US$
            private NumberFormat nf = NumberFormat.getCurrencyInstance();


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Não iremos utilizar...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Evita que o método seja executado varias vezes. Se tirar ele entre em loop
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;
                String str = s.toString();

                // Verifica se já existe a máscara no texto.
                boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) &&
                        (str.indexOf(".") > -1 || str.indexOf(",") > -1));

                // Verificamos se existe máscara
                if (hasMask) {

                    // Retiramos a máscara.
                    str = str.replaceAll("[R$]", "").replaceAll("[,]", "")
                            .replaceAll("[.]", "");
                }

                try {
                    // Transformamos o número que está escrito no EditText em monetário.
                    str = nf.format(Double.parseDouble(str) / 100);
                    field_income.setText(str);
                    field_income.setSelection(field_income.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Não iremos utilizar...
            }
        });
    }


    public void clearField(View view) {
        this.field_income = (EditText) findViewById(R.id.field_income);
        field_income.setText("");
    }

    public void sumIncome(View view) {

        Editable valueFieldIncome = field_income.getText(); //pega valor digitado
        String oldValue = valueFieldIncome.toString(); //transforma em String
        String newValue = oldValue.replace("R$", ""); //retira o "R$"


        //The lines below modify the total amount
        View parent = (View)view.getParent();
        if (parent != null) {
            //get value intial of the text view incomes
            TextView txtView = (TextView) parent.findViewById(R.id.sum_income);
            String initialValue = (String) txtView.getText(); //turn to string

            //turn string to boolean
            initialValue = initialValue.replace(".", "");//eliminates point
            initialValue = initialValue.replace(",", ".");//replaces points with commas
            Double initialValueDouble = Double.parseDouble(initialValue);

            //get value intial of the Edit text incomes
            newValue = newValue.replace(".", "");//eliminates point
            newValue = newValue.replace(",", ".");//replaces points with commas
            Double newValueDouble = Double.parseDouble(newValue);

            Double totalValue = initialValueDouble + newValueDouble;

            String turnDouble2String = String.valueOf(totalValue);
            turnDouble2String = initialValue.replace(".", ",");

            txtView.setText(turnDouble2String); //set new value for total incomes

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Não iremos utilizar...
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Não iremos utilizar...
    }

    @Override
    public void afterTextChanged(Editable s) {
        //Não iremos utilizar...
    }
}
