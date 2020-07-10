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

public class QuesitosCompletoTextoArgumentativo extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoTextoArgumentativo.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_texto_argumentativo);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Pontos importantes do texto argumentativo");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ8_1(viewHolder.q8_1.getText().toString());
            analise.setQ8_2(viewHolder.q8_2.getText().toString());
            analise.setQ8_3(viewHolder.q8_3.getText().toString());
            analise.setQ8_4(viewHolder.q8_4.getText().toString());
            analise.setQ8_5(viewHolder.q8_5.getText().toString());
            analise.setQ8_6(viewHolder.q8_6.getText().toString());
            analise.setQ8_7(viewHolder.q8_7.getText().toString());


            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompletoTextoArgumentativo.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_completo_2_texto_argumentativo_sim:
                if (checked)
                    analise.setQ8_8(Analise.ConstsIdentificacoes.SIM);
                break;
            case R.id.radio_button_completo_2_texto_argumentativo_nao:
                if (checked)
                    analise.setQ8_8(Analise.ConstsIdentificacoes.NAO);
                break;
        }
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent = null;
        if (analise.getQ8_8() != null) {
            salvarQuesitos();
            switch (analise.getQ8_8()) {
                case Analise.ConstsIdentificacoes.SIM:
                    intent = new Intent(QuesitosCompletoTextoArgumentativo.this, QuesitosCompletoAnaliseBibliografia.class);
                    break;
                case Analise.ConstsIdentificacoes.NAO:
                    intent = new Intent(QuesitosCompletoTextoArgumentativo.this, QuesitosCompletoDificuldadeLeitura.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosCompletoTextoArgumentativo.this, "Selecione se há pontos importantes sobre a bibliografia", Toast.LENGTH_LONG).show();
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

        TextInputEditText q8_1;
        TextInputEditText q8_2;
        TextInputEditText q8_3;
        TextInputEditText q8_4;
        TextInputEditText q8_5;
        TextInputEditText q8_6;
        TextInputEditText q8_7;

        ViewHolder() {
            super();
            q8_1 = findViewById(R.id.act_input_act_q8_1);
            q8_1.setText(analise.getQ8_1());

            q8_2 = findViewById(R.id.act_input_act_q8_2);
            q8_2.setText(analise.getQ8_2());

            q8_3 = findViewById(R.id.act_input_act_q8_3);
            q8_3.setText(analise.getQ8_3());

            q8_4 = findViewById(R.id.act_input_act_q8_4);
            q8_4.setText(analise.getQ8_4());

            q8_5 = findViewById(R.id.act_input_act_q8_5);
            q8_5.setText(analise.getQ8_5());

            q8_6 = findViewById(R.id.act_input_act_q8_6);
            q8_6.setText(analise.getQ8_6());

            q8_7 = findViewById(R.id.act_input_act_q8_7);
            q8_7.setText(analise.getQ8_7());

            if (analise.getQ8_8() != null) {
                switch (analise.getQ8_8()) {
                    case Analise.ConstsIdentificacoes.SIM:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_argumentativo_sim)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.NAO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_argumentativo_nao)).setChecked(true);
                        break;
                }
            }
        }
    }
}


