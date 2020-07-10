package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosCompletoDificuldadeLeitura extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoDificuldadeLeitura.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_dificuldade_leitura);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Você teve dificuldades com a leitura?");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ12_1(getPosRadioGroupSelected(viewHolder.radioGroupQ12_1));

            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompletoDificuldadeLeitura.this, "Nenhum Email fornecido, não foi possível salvar.",
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
        if (analise.getQ12_1() != null) {
            switch (analise.getQ12_1()) {
                case Analise.ConstsIdentificacoes.SIM_MINUSCULAS:
                    intent = new Intent(QuesitosCompletoDificuldadeLeitura.this, QuesitosCompletoComentarDificuldadeLeitura.class);
                    break;
                case Analise.ConstsIdentificacoes.NAO_MINUSCULAS:
                    intent = new Intent(QuesitosCompletoDificuldadeLeitura.this, QuesitosCompletoTextoComOutrosConteudos.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosCompletoDificuldadeLeitura.this, "Selecione se gostaria de analisar a história existente no poema", Toast.LENGTH_LONG).show();
        }
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {
        RadioGroup radioGroupQ12_1;


        ViewHolder() {
            super();
            radioGroupQ12_1 = findViewById(R.id.radio_group_q12_1);
            setRadioByText(radioGroupQ12_1, analise.getQ12_1());
        }

    }
}





