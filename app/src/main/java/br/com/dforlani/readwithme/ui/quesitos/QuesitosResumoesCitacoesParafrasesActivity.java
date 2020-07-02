package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosResumoesCitacoesParafrasesActivity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosResumoesCitacoesParafrasesActivity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_resumoes_citacoes_parafrases);


        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText(R.string.title_resumos_citacoes_parafrase);

        viewHolder = new ViewHolder();
    }


    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ4_1(viewHolder.q4_1.getText().toString());
            analise.setQ4_2(viewHolder.q4_2.getText().toString());
            analise.setQ4_3(viewHolder.q4_3.getText().toString());
            analise.setQ4_4(viewHolder.q4_4.getText().toString());

            analise.save(email);

        } else {
            Toast.makeText(QuesitosResumoesCitacoesParafrasesActivity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBackPressed() {
        voltarActIdentificacao();
    }

    private void voltarActIdentificacao() {
        Intent intent = new Intent(QuesitosResumoesCitacoesParafrasesActivity.this, QuesitosIdentificacaoActivity.class);
        intent.putExtra("analise", analise);
        startActivity(intent);
        finish();
    }

    class ViewHolder {
        Button continuar;
        Button voltar;

        TextInputEditText q4_1;
        TextInputEditText q4_2;
        TextInputEditText q4_3;
        TextInputEditText q4_4;


        ViewHolder() {

            q4_1 = findViewById(R.id.act_text_q_resumos_citacoes_input_q4_1);
            q4_1.setText(analise.getQ4_1());

            q4_2 = findViewById(R.id.act_text_q_resumos_citacoes_input_q4_2);
            q4_2.setText(analise.getQ4_2());

            q4_3 = findViewById(R.id.act_text_q_resumos_citacoes_input_q4_3);
            q4_3.setText(analise.getQ4_3());

            q4_4 = findViewById(R.id.act_text_q_resumos_citacoes_input_q4_4);
            q4_4.setText(analise.getQ4_4());


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
                    voltarActIdentificacao();
                }
            });
        }
    }
}
