
package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosCompleto2Activity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompleto2Activity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_2);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText(R.string.title_complete_2);
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            //binds feito pelo OnClick
            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompleto2Activity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_completo_2_texto_informativo:
                if (checked)
                    analise.setQ6_1(Analise.ConstsIdentificacoes.TEXTO_INFORMATIVO);
                break;
            case R.id.radio_button_completo_2_texto_argumentativo:
                if (checked)
                    analise.setQ6_1(Analise.ConstsIdentificacoes.TEXTO_ARGUMENTATIVO);

                break;
            case R.id.radio_button_completo_2_texto_narrativo:
                if (checked)
                    analise.setQ6_1(Analise.ConstsIdentificacoes.TEXTO_NARRATIVO);

                break;
            case R.id.radio_button_completo_2_texto_poesia:
                if (checked)
                    analise.setQ6_1(Analise.ConstsIdentificacoes.TEXTO_POESIA);
                break;
        }
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent;
        if (analise.getQ6_1() != null) {
            salvarQuesitos();
            switch (analise.getQ6_1()) {
                case Analise.ConstsIdentificacoes.TEXTO_INFORMATIVO:
                    intent = new Intent(QuesitosCompleto2Activity.this, QuesitosCompletoTextoInformativo.class);
                    intent.putExtra("analise", analise);
                    startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
                    break;
                case Analise.ConstsIdentificacoes.TEXTO_ARGUMENTATIVO:
                    intent = new Intent(QuesitosCompleto2Activity.this, QuesitosCompletoTextoArgumentativo.class);
                    intent.putExtra("analise", analise);
                    startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
                    break;

                case Analise.ConstsIdentificacoes.TEXTO_POESIA:
                    intent = new Intent(QuesitosCompleto2Activity.this, QuesitosCompletoTextoPoesia.class);
                    intent.putExtra("analise", analise);
                    startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);

                    break;

                case Analise.ConstsIdentificacoes.TEXTO_NARRATIVO:
                    intent = new Intent(QuesitosCompleto2Activity.this, QuesitosCompletoTextoNarrativo.class);
                    intent.putExtra("analise", analise);
                    startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
                    break;

            }
        } else {
            Toast.makeText(QuesitosCompleto2Activity.this, "Selecione um tipo de texto", Toast.LENGTH_LONG).show();
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

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {

        ViewHolder() {
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarContinuarPraProximaActivity();

                }
            });

            if (analise.getQ6_1() != null) {
                switch (analise.getQ6_1()) {
                    case Analise.ConstsIdentificacoes.TEXTO_ARGUMENTATIVO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_argumentativo)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.TEXTO_INFORMATIVO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_informativo)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.TEXTO_POESIA:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_poesia)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.TEXTO_NARRATIVO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_narrativo)).setChecked(true);
                        break;
                }
            }
        }
    }
}
