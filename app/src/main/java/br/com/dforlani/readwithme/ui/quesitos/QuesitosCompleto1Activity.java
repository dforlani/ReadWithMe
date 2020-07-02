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

public class QuesitosCompleto1Activity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompleto1Activity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_quesitos_completo_1);

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText(R.string.title_elementos_exteriores_ao_texto);

        viewHolder = new ViewHolder();
    }


    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ5_1(viewHolder.q5_1.getText().toString());
            analise.setQ5_2(viewHolder.q5_2.getText().toString());
            analise.setQ5_3(viewHolder.q5_3.getText().toString());
            analise.setQ5_4(viewHolder.q5_4.getText().toString());
            analise.setQ5_5(viewHolder.q5_5.getText().toString());

            analise.save(email);

        } else {
            Toast.makeText(QuesitosCompleto1Activity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    class ViewHolder {
        Button continuar;
        Button voltar;

        TextInputEditText q5_1;
        TextInputEditText q5_2;
        TextInputEditText q5_3;
        TextInputEditText q5_4;
        TextInputEditText q5_5;


        ViewHolder() {

            q5_1 = findViewById(R.id.act_text_completo_input_q5_1);
            q5_1.setText(analise.getQ5_1());

            q5_2 = findViewById(R.id.act_text_completo_input_q5_2);
            q5_2.setText(analise.getQ5_2());

            q5_3 = findViewById(R.id.act_text_completo_input_q5_3);
            q5_3.setText(analise.getQ5_3());

            q5_4 = findViewById(R.id.act_text_completo_input_q5_4);
            q5_4.setText(analise.getQ5_4());

            q5_5 = findViewById(R.id.act_text_completo_input_q5_5);
            q5_5.setText(analise.getQ5_5());


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
                    Intent intent = new Intent(QuesitosCompleto1Activity.this, QuesitosIdentificacaoActivity.class);
                    intent.putExtra("analise", analise);
                    startActivity(intent);
                    finish();
                }

            });
        }


    }
}
