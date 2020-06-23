package br.com.dforlani.readwithme.ui.quesitos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.barcode.BarCodeActivity;
import br.com.dforlani.readwithme.util.GoogleBooksAPI;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosReacoesActivity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosReacoesActivity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_reacoes);

        this.setTitle(getString(R.string.title_apenas_reacoes));
        viewHolder = new ViewHolder();
    }

    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setISBN(viewHolder.ISBN.getText().toString());
            analise.setQ1_1(viewHolder.q1_1.getText().toString());
            analise.setQ1_2(viewHolder.q1_2.getText().toString());
            analise.setQ1_3(viewHolder.q1_3.getText().toString());
            analise.setQ1_4(viewHolder.q1_4.getText().toString());
            analise.setQ1_5(viewHolder.q1_5.getText().toString());
            analise.setQ1_6(viewHolder.q1_6.getText().toString());
            analise.setQ1_7(viewHolder.q1_7.getText().toString());
            analise.setQ1_8(viewHolder.q1_8.getText().toString());
            analise.setQ1_9(viewHolder.q1_9.getText().toString());
            analise.setQ1_10(viewHolder.q1_10.getText().toString());
            analise.setQ1_11(viewHolder.q1_11);

            analise.save(email);

        } else {
            Toast.makeText(QuesitosReacoesActivity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_apenas_anotacoes:
                if (checked)
                    viewHolder.q1_11 = Analise.APENAS_ANOTACAOES;
                break;
            case R.id.radio_button_apenas_reacoes:
                if (checked)
                    viewHolder.q1_11 = Analise.APENAS_REACOES;

                break;
            case R.id.radio_button_apenas_resumos:
                if (checked)
                    viewHolder.q1_11 = Analise.APENAS_RESUMOS;

                break;
            case R.id.radio_button_encerrar:
                if (checked)
                    viewHolder.q1_11 = Analise.ENCERRAR;
                break;
            case R.id.radio_button_sim:
                if (checked)
                    viewHolder.q1_11 = Analise.ANALISE_COMPLETA;
                break;
        }
    }

    class ViewHolder {
        Button continuar;
        Button voltar;
        TextInputEditText q1_1;
        TextInputEditText q1_2;
        TextInputEditText q1_3;
        TextInputEditText q1_4;
        TextInputEditText q1_5;
        TextInputEditText q1_6;
        TextInputEditText q1_7;
        TextInputEditText q1_8;
        TextInputEditText q1_9;
        TextInputEditText q1_10;
        TextInputEditText ISBN;
        String q1_11 = "";


        ViewHolder() {
            continuar = findViewById(R.id.act_quesitos_reacoes_btt_continuar);
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarQuesitos();
                    finish();
                }


            });

            voltar = findViewById(R.id.act_quesitos_reacoes_btt_voltar);
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }

            });

            q1_1 = findViewById(R.id.act_quesitos1_1);
            q1_1.setText(analise.getQ1_1());

            q1_11 = analise.getQ1_11();
            if (q1_11 != null) {
                switch (q1_11) {
                    case Analise.APENAS_ANOTACAOES:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_anotacoes)).setChecked(true);
                        break;

                    case Analise.APENAS_REACOES:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_reacoes)).setChecked(true);
                        break;

                    case Analise.APENAS_RESUMOS:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_resumos)).setChecked(true);
                        break;

                    case Analise.ENCERRAR:
                        ((RadioButton) findViewById(R.id.radio_button_encerrar)).setChecked(true);
                        break;

                    default:
                        ((RadioButton) findViewById(R.id.radio_button_sim)).setChecked(true);//por padrão vai vir checado
                }
            } else {
                ((RadioButton) findViewById(R.id.radio_button_sim)).setChecked(true);//por padrão vai vir checado
            }
        }
    }
}
