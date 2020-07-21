package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosQ7CompletoTextoInformativo extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoTextoInformativo.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_texto_informativo);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText(R.string.title_quesitos_texto_informativo);
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ7_1(viewHolder.q7_1.getText().toString());
            analise.setQ7_2(viewHolder.q7_2.getText().toString());
            analise.setQ7_3(viewHolder.q7_3.getText().toString());
            analise.setQ7_4(viewHolder.q7_4.getText().toString());
            analise.setQ7_5(viewHolder.q7_5.getText().toString());


            analise.save(email);
        } else {
            Toast.makeText(QuesitosQ7CompletoTextoInformativo.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_completo_texto_informativo_sim:
                if (checked)
                    analise.setQ7_6(Analise.IDENTIFICACOES.SIM);
                break;
            case R.id.radio_button_completo_texto_informativo_nao:
                if (checked)
                    analise.setQ7_6(Analise.IDENTIFICACOES.NAO);
                break;
        }
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent = null;
        salvarQuesitos();
        if (analise.getQ7_6() != null) {

            switch (analise.getQ7_6()) {
                case Analise.IDENTIFICACOES.SIM:
                    intent = new Intent(QuesitosQ7CompletoTextoInformativo.this, QuesitosQ11CompletoAnaliseBibliografia.class);
                    break;
                case Analise.IDENTIFICACOES.NAO:
                    intent = new Intent(QuesitosQ7CompletoTextoInformativo.this, Quesitos12CompletoDificuldadeLeitura.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosQ7CompletoTextoInformativo.this, "Selecione se há pontos importantes sobre a bibliografia", Toast.LENGTH_LONG).show();
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

        TextInputEditText q7_1;
        TextInputEditText q7_2;
        TextInputEditText q7_3;
        TextInputEditText q7_4;
        TextInputEditText q7_5;

        ViewHolder() {
            super();
            q7_1 = findViewById(R.id.act_input_act_q7_1);
            q7_1.setText(analise.getQ7_1());

            q7_2 = findViewById(R.id.act_input_act_q7_2);
            q7_2.setText(analise.getQ7_2());

            q7_3 = findViewById(R.id.act_input_act_q7_3);
            q7_3.setText(analise.getQ7_3());

            q7_4 = findViewById(R.id.act_input_act_q7_4);
            q7_4.setText(analise.getQ7_4());

            q7_5 = findViewById(R.id.act_input_act_q7_5);
            q7_5.setText(analise.getQ7_5());

            if (analise.getQ7_6() != null) {
                switch (analise.getQ7_6()) {
                    case Analise.IDENTIFICACOES.SIM:
                        ((RadioButton) findViewById(R.id.radio_button_completo_texto_informativo_sim)).setChecked(true);
                        break;

                    case Analise.IDENTIFICACOES.NAO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_texto_informativo_nao)).setChecked(true);
                        break;
                }
            }
        }
    }
}

