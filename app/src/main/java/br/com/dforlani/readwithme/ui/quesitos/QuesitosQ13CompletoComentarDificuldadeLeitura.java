package br.com.dforlani.readwithme.ui.quesitos;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosQ13CompletoComentarDificuldadeLeitura extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoComentarDificuldadeLeitura.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_comentar_difuldade_leitura);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Dificuldades de leitura");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ13_1(getTagListCheck(viewHolder.getIdentificadoresCheckQ13_1()));

            analise.setQ13_2(viewHolder.q13_2.getText().toString());
            analise.setQ13_3(viewHolder.q13_3.getText().toString());
            analise.setQ13_4(viewHolder.q13_4.getText().toString());

            analise.save(email);
        } else {
            Toast.makeText(QuesitosQ13CompletoComentarDificuldadeLeitura.this, "Nenhum Email fornecido, não foi possível salvar.",
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
        return QuesitosQ14CompletoRelacoesOutrosConteudos.class;
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {

        CheckBox check1Q13_1;
        CheckBox check2Q13_1;
        CheckBox check3Q13_1;
        CheckBox check4Q13_1;
        CheckBox check5Q13_1;
        CheckBox check6Q13_1;
        CheckBox check7Q13_1;


        TextInputEditText q13_2;
        TextInputEditText q13_3;
        TextInputEditText q13_4;


        ViewHolder() {
            super();
            check1Q13_1 = findViewById(R.id.act_comentar_difuldade_check1_Q13_1);
            check2Q13_1 = findViewById(R.id.act_comentar_difuldade_check2_Q13_1);
            check3Q13_1 = findViewById(R.id.act_comentar_difuldade_check3_Q13_1);
            check4Q13_1 = findViewById(R.id.act_comentar_difuldade_check4_Q13_1);
            check5Q13_1 = findViewById(R.id.act_comentar_difuldade_check5_Q13_1);
            check6Q13_1 = findViewById(R.id.act_comentar_difuldade_check6_Q13_1);
            check7Q13_1 = findViewById(R.id.act_comentar_difuldade_check7_Q13_1);

            setCheckBox(analise.getQ13_1(), getIdentificadoresCheckQ13_1());


            q13_2 = findViewById(R.id.act_input_act_q13_2);
            q13_2.setText(analise.getQ13_2());

            q13_3 = findViewById(R.id.act_input_act_q13_3);
            q13_3.setText(analise.getQ13_3());

            q13_4 = findViewById(R.id.act_input_act_q13_4);
            q13_4.setText(analise.getQ13_4());
        }

        private List<Integer> getIdentificadoresCheckQ13_1() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_comentar_difuldade_check1_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check2_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check3_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check4_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check5_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check6_Q13_1);
            auxListCheck.add(R.id.act_comentar_difuldade_check7_Q13_1);

            return auxListCheck;

        }
    }
}




