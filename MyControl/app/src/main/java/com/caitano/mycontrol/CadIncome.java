package com.caitano.mycontrol;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

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
        Editable valorCampo = field_income.getText(); //pega valor digitado
        String antigoValor = valorCampo.toString(); //transforma em String
        String novoValor = antigoValor.replace("R$", ""); //retira o "R$"
        
        System.out.println(novoValor);
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
