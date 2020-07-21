package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosCompletoGostariaAnalisarIlustracoesPoesia extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoGostariaAnalisarIlustracoesPoesia.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_gostaria_analisar_ilustracoes);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Ilustrações no texto poético");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ15_1(getPosRadioGroupSelected(viewHolder.radioGroupQ15_1));

            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompletoGostariaAnalisarIlustracoesPoesia.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Salvar e continuar pra próxima
     *
     * @return
     */
    @Override
    protected Class getNextActivity() {
        return null;
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent = null;
        salvarQuesitos();
        if (analise.getQ15_1() != null) {
            switch (analise.getQ15_1()) {
                case Analise.IDENTIFICACOES.SIM_MINUSCULAS:
                    intent = new Intent(QuesitosCompletoGostariaAnalisarIlustracoesPoesia.this, QuesitosCompletoProjetoGrafico.class);
                    break;
                case Analise.IDENTIFICACOES.NAO_MINUSCULAS:
                    intent = new Intent(QuesitosCompletoGostariaAnalisarIlustracoesPoesia.this, QuesitosCompletoDificuldadeLeitura.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosCompletoGostariaAnalisarIlustracoesPoesia.this, "Selecione se gostaria de analisar analisar as ilustrações e o projeto do gráfico", Toast.LENGTH_LONG).show();
        }
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {
        RadioGroup radioGroupQ15_1;


        ViewHolder() {
            super();
            radioGroupQ15_1 = findViewById(R.id.radio_group_q15_1);
            setRadioByText(radioGroupQ15_1, analise.getQ15_1());
        }

    }
}





