package br.com.dforlani.readwithme.ui.quesitos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosAnotacoesLivresActivity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosAnotacoesLivresActivity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_anotacoes_livres);

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText(R.string.title_anotacoes_livres);

        viewHolder = new ViewHolder();
    }


    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ3_1(viewHolder.q3_1.getText().toString());

            analise.save(email);

        } else {
            Toast.makeText(QuesitosAnotacoesLivresActivity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }






    class ViewHolder {
        Button continuar;
        Button voltar;

        TextInputEditText q3_1;


        ViewHolder() {

            q3_1 = findViewById(R.id.act_text_q_anotacoes_livres_input_q3_1);
            q3_1.setText(analise.getQ3_1());

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
        }


    }
}
