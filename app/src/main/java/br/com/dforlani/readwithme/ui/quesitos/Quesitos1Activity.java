package br.com.dforlani.readwithme.ui.quesitos;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Calendar;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class Quesitos1Activity extends AppCompatActivity {

    private static final String TAG = "Quesitos1Activity.class";
    ViewHolder viewHolder;
    private Analise analise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos1);

        //pega informações de uma Analise sendo editada, se for o caso
        Intent i = getIntent();
        Object aux = i.getSerializableExtra("analise");
        if(aux != null){
            analise = (Analise)aux;
        }else{//se não for pra edição, inicia um novo objeto Análise
            analise = new Analise();
        }

        this.setTitle(R.string.title_q1);
        viewHolder = new ViewHolder();


    }

    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
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
            Toast.makeText(Quesitos1Activity.this, "Nenhum Email fornecido, não foi possível salvar.",
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
        DatePickerDialog picker;
        ImageButton dtInicio;
        ImageButton dtFim;
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
        String q1_11 = "";
        RadioButton radioButton;

        ViewHolder() {
            continuar = findViewById(R.id.act_quesitos1_btt_continuar);
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarQuesitos();
                }


            });

            voltar = findViewById(R.id.act_quesitos1_btt_voltar);
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }


            });


            dtInicio = findViewById(R.id.act_quesitos1_date_inicio);
            dtInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(Quesitos1Activity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    q1_9.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });

            dtFim = findViewById(R.id.act_quesitos1_date_fim);
            dtFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(Quesitos1Activity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    q1_10.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });

            q1_1 = findViewById(R.id.act_quesitos1_1);
            q1_2 = findViewById(R.id.act_quesitos1_2);
            q1_3 = findViewById(R.id.act_quesitos1_3);
            q1_4 = findViewById(R.id.act_quesitos1_4);
            q1_5 = findViewById(R.id.act_quesitos1_5);
            q1_6 = findViewById(R.id.act_quesitos1_6);
            q1_7 = findViewById(R.id.act_quesitos1_7);
            q1_8 = findViewById(R.id.act_quesitos1_8);
            q1_9 = findViewById(R.id.act_quesitos1_9);
            q1_10 = findViewById(R.id.act_quesitos1_10);

            radioButton = findViewById(R.id.radio_button_sim);
            radioButton.setChecked(true);//por padrão vai vir checado


        }


    }
}
