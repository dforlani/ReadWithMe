package br.com.dforlani.readwithme.ui.quesitos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.barcode.BarCodeActivity;
import br.com.dforlani.readwithme.util.GoogleBooksAPI;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosReacoesActivity extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosReacoesActivity.class";

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_reacoes);

        this.setTitle(getString(R.string.title_apenas_reacoes));
        viewHolder = new ViewHolder();
    }


    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ2_1(getPosRadioGroupSelected(viewHolder.radioInteresseQ2_1));
            analise.setQ2_2(viewHolder.q2_2.getText().toString());
            analise.setQ2_3(getPosRadioGroupSelected(viewHolder.radioSentiaQ2_3));
            analise.setQ2_4(viewHolder.q2_4.getText().toString());
            analise.setQ2_5(getPosRadioGroupSelected(viewHolder.radioPrazerQ2_5));
            analise.setQ2_6(viewHolder.q2_6.getText().toString());
            analise.setQ2_7(getPosRadioGroupSelected(viewHolder.radioObjetivoQ2_7));


            analise.setQ2_8(getTagListCheck(viewHolder.getIdentificadoresCheckQ2_8()));


            analise.setQ2_9(viewHolder.q2_9.getText().toString());
            analise.setQ2_10(getPosRadioGroupSelected(viewHolder.radioIdeiasAutorQ2_10));
            analise.setQ2_11(viewHolder.q2_11.getText().toString());
            analise.setQ2_12(viewHolder.q2_12.getText().toString());
            analise.setQ2_13(getPosRadioGroupSelected(viewHolder.radioEstiloAutorQ2_13));
            analise.setQ2_14(viewHolder.q2_14.getText().toString());

            analise.setQ2_15(getPosRadioGroupSelected(viewHolder.radioGeneroAgradaQ2_15));
            analise.setQ2_16(viewHolder.q2_16.getText().toString());


            analise.setQ2_17(getTagListCheck(viewHolder.getIdentificadoresCheckQ2_17()));

            analise.setQ2_18(viewHolder.q2_18.getText().toString());
            analise.setQ2_19(viewHolder.q2_19.getText().toString());


            analise.save(email);

        } else {
            Toast.makeText(QuesitosReacoesActivity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }

    class ViewHolder {
        Button continuar;
        Button voltar;
        RadioGroup radioInteresseQ2_1;
        TextInputEditText q2_2;
        RadioGroup radioSentiaQ2_3;
        TextInputEditText q2_4;
        RadioGroup radioPrazerQ2_5;

        TextInputEditText q2_6;
        RadioGroup radioObjetivoQ2_7;


        CheckBox checkAvalieiMalQ2_8;
        CheckBox checkTextoNaoCumpreQ2_8;
        CheckBox checkTextoNoCumpreRecomendouQ2_8;
        CheckBox checkProfundidadeMaiorMenorQ2_8;
        CheckBox checkOutraCoisaQ2_8;


        TextInputEditText q2_9;
        RadioGroup radioIdeiasAutorQ2_10;


        TextInputEditText q2_11;
        TextInputEditText q2_12;
        RadioGroup radioEstiloAutorQ2_13;


        TextInputEditText q2_14;
        RadioGroup radioGeneroAgradaQ2_15;


        TextInputEditText q2_16;


        CheckBox checkVidaAcademicaQ2_17;
        CheckBox checkPrazerDiversaoQ2_17;
        CheckBox checkMinhaPesquisaAtualQ2_17;
        CheckBox checkPesquisasFuturasQ2_17;
        CheckBox checkVidaProfissionalQ2_17;
        CheckBox checkPraticaLeituraQ2_17;
        CheckBox checkProducaoTextosQ2_17;
        CheckBox checkVidaPessoalQ2_17;
        CheckBox checkConhecimentoMundoQ2_17;
        CheckBox checkGostoMuitoQ2_17;


        TextInputEditText q2_18;
        TextInputEditText q2_19;


        ViewHolder() {
            radioInteresseQ2_1 = findViewById(R.id.act_reacoes_radio_classifica_interesse);
            setRadio(radioInteresseQ2_1, analise.getQ2_1());

            q2_2 = findViewById(R.id.act_reacoes_input_2);
            q2_2.setText(analise.getQ2_2());

            radioSentiaQ2_3 = findViewById(R.id.act_reacoes_radio_sentia);
            setRadio(radioSentiaQ2_3, analise.getQ2_3());

            q2_4 = findViewById(R.id.act_reacoes_input_4);
            q2_4.setText(analise.getQ2_4());

            radioPrazerQ2_5 = findViewById(R.id.act_reacoes_radio_classifica_prazer);
            setRadio(radioPrazerQ2_5, analise.getQ2_5());

            q2_6 = findViewById(R.id.act_reacoes_input_6);
            q2_6.setText(analise.getQ2_6());

            radioObjetivoQ2_7 = findViewById(R.id.act_reacoes_radio_objetivo_ao_ler);
            setRadio(radioObjetivoQ2_7, analise.getQ2_7());

            checkAvalieiMalQ2_8 = findViewById(R.id.act_reacoes_check_avaliei_mal);
            checkTextoNaoCumpreQ2_8 = findViewById(R.id.act_reacoes_check_texto_nao_cumpre);
            checkTextoNoCumpreRecomendouQ2_8 = findViewById(R.id.act_reacoes_check_texto_nao_cumpre_quem_recomendou);
            checkProfundidadeMaiorMenorQ2_8 = findViewById(R.id.act_reacoes_check_profundidade_maior_menor);
            checkOutraCoisaQ2_8 = findViewById(R.id.act_reacoes_check_outra_coisa);
            setCheckBox(analise.getQ2_8(), getIdentificadoresCheckQ2_8());


            q2_9 = findViewById(R.id.act_reacoes_input_9);
            q2_9.setText(analise.getQ2_9());

            radioIdeiasAutorQ2_10 = findViewById(R.id.act_reacoes_radio_ideias_autor);
            setRadio(radioIdeiasAutorQ2_10, analise.getQ2_10());

            q2_11 = findViewById(R.id.act_reacoes_input_11);
            q2_11.setText(analise.getQ2_11());

            q2_12 = findViewById(R.id.act_reacoes_input_12);
            q2_12.setText(analise.getQ2_12());

            radioEstiloAutorQ2_13 = findViewById(R.id.act_reacoes_radio_estilo_autor);
            setRadio(radioEstiloAutorQ2_13, analise.getQ2_13());

            q2_14 = findViewById(R.id.act_reacoes_input_14);
            q2_14.setText(analise.getQ2_14());

            radioGeneroAgradaQ2_15 = findViewById(R.id.act_reacoes_radio_genero_agrada);
            setRadio(radioGeneroAgradaQ2_15, analise.getQ2_15());

            q2_16 = findViewById(R.id.act_reacoes_input_16);
            q2_16.setText(analise.getQ2_16());
            setCheckBox(analise.getQ2_17(), getIdentificadoresCheckQ2_17());

            checkVidaAcademicaQ2_17 = findViewById(R.id.act_reacoes_check_vida_academica);
            checkPrazerDiversaoQ2_17 = findViewById(R.id.act_reacoes_check_prazer_e_diversao);
            checkMinhaPesquisaAtualQ2_17 = findViewById(R.id.act_reacoes_check_minha_pesquisa_atual);
            checkPesquisasFuturasQ2_17 = findViewById(R.id.act_reacoes_check_pesquisas_futuras);
            checkVidaProfissionalQ2_17 = findViewById(R.id.act_reacoes_check_vida_profissional);
            checkPraticaLeituraQ2_17 = findViewById(R.id.act_reacoes_check_pratica_leitura);
            checkProducaoTextosQ2_17 = findViewById(R.id.act_reacoes_check_producao_textos);
            checkVidaPessoalQ2_17 = findViewById(R.id.act_reacoes_check_vida_pessoal);
            checkConhecimentoMundoQ2_17 = findViewById(R.id.act_reacoes_check_conhecimento_mundo);
            checkGostoMuitoQ2_17 = findViewById(R.id.act_reacoes_check_gosto_muito);

            q2_18 = findViewById(R.id.act_reacoes_input_18);
            q2_18.setText(analise.getQ2_18());

            q2_19 = findViewById(R.id.act_reacoes_input_19);
            q2_19.setText(analise.getQ2_19());


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

        private List<Integer> getIdentificadoresCheckQ2_8() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_reacoes_check_avaliei_mal);
            auxListCheck.add(R.id.act_reacoes_check_texto_nao_cumpre);
            auxListCheck.add(R.id.act_reacoes_check_texto_nao_cumpre_quem_recomendou);
            auxListCheck.add(R.id.act_reacoes_check_profundidade_maior_menor);
            auxListCheck.add(R.id.act_reacoes_check_outra_coisa);
            return auxListCheck;
        }



        public List<Integer> getIdentificadoresCheckQ2_17() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_reacoes_check_vida_academica);
            auxListCheck.add(R.id.act_reacoes_check_prazer_e_diversao);
            auxListCheck.add(R.id.act_reacoes_check_minha_pesquisa_atual);
            auxListCheck.add(R.id.act_reacoes_check_pesquisas_futuras);
            auxListCheck.add(R.id.act_reacoes_check_vida_profissional);
            auxListCheck.add(R.id.act_reacoes_check_pratica_leitura);
            auxListCheck.add(R.id.act_reacoes_check_producao_textos);
            auxListCheck.add(R.id.act_reacoes_check_vida_pessoal);
            auxListCheck.add(R.id.act_reacoes_check_conhecimento_mundo);
            auxListCheck.add(R.id.act_reacoes_check_gosto_muito);
            return auxListCheck;
        }
    }
}
