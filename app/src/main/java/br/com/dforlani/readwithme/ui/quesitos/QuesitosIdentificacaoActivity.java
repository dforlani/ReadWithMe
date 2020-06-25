package br.com.dforlani.readwithme.ui.quesitos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.barcode.BarCodeActivity;
import br.com.dforlani.readwithme.util.GoogleBooksAPI;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosIdentificacaoActivity extends QuesitosBaseActivity {

    private static final String TAG = "Quesitos1Activity.class";
    private static final int REQUEST_BARCOCODE_RESULT = 998;

    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_identificacao);

        this.setTitle(R.string.title_q1);
        viewHolder = new ViewHolder();

        progressBar = findViewById(R.id.activity_quesitos1_progressBar);

    }

    private void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setISBN(viewHolder.ISBN.getText().toString());
            analise.setQ1_1(viewHolder.q1_1.getText().toString());
            analise.setQ1_2(viewHolder.q1_2.getText().toString());
            analise.setQ1_3(viewHolder.q1_3.getText().toString());
            analise.setQ1_4(viewHolder.q1_4.getText().toString());
            analise.setQ1_5(viewHolder.q1_5.getText().toString());
            analise.setQ1_6(viewHolder.q1_6.getText().toString());
            analise.setQ1_7(viewHolder.q1_7.getText().toString());
            analise.setQ1_8(viewHolder.q1_8.getText().toString());
            analise.setQ1_9(viewHolder.q1_9.getText().toString());
            analise.setQ1_10(viewHolder.q1_10.getText().toString());
            analise.setQ1_11(viewHolder.q1_11);

            analise.save(email);

        } else {
            Toast.makeText(QuesitosIdentificacaoActivity.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_apenas_anotacoes:
                if (checked)
                    viewHolder.q1_11 = Analise.ConstsIdentificacoes.APENAS_ANOTACAOES;
                break;
            case R.id.radio_button_apenas_reacoes:
                if (checked)
                    viewHolder.q1_11 = Analise.ConstsIdentificacoes.APENAS_REACOES;

                break;
            case R.id.radio_button_apenas_resumos:
                if (checked)
                    viewHolder.q1_11 = Analise.ConstsIdentificacoes.APENAS_RESUMOS;

                break;
            case R.id.radio_button_encerrar:
                if (checked)
                    viewHolder.q1_11 = Analise.ConstsIdentificacoes.ENCERRAR;
                break;
            case R.id.radio_button_sim:
                if (checked)
                    viewHolder.q1_11 = Analise.ConstsIdentificacoes.ANALISE_COMPLETA;
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_BARCOCODE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("isbn")) {
                    String result = data.getStringExtra("isbn");
                    viewHolder.ISBN.setText(result);
                    if (result != null && result.length() > 0) {
                        buscaDadosISBN(result);
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void buscaDadosISBN(String isbn) {
        GoogleBooksAPI book = new GoogleBooksAPI(QuesitosIdentificacaoActivity.this);
        progressBar.setVisibility(View.VISIBLE);

        JSONObject json = book.doInBackground(isbn);
        progressBar.setVisibility(View.GONE);
        if (json != null) {

            try {
                viewHolder.q1_1.setText(json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").get("title").toString());
                String autores = "";
                JSONArray titulos = null;
                titulos = json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors");
                String separador = "";
                for (int i = 0; i < titulos.length(); i++) {
                    autores += separador + titulos.get(i).toString();
                    separador = ", ";
                }
                if (autores.length() > 0) {
                    viewHolder.q1_2.setText(autores);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //responseJson.getJSONArray("volumeInfo");
        } else {
            Toast.makeText(this, "Não foi possível acessar os servidores da Google, tente novamente.", Toast.LENGTH_LONG).show();
        }
    }


    class ViewHolder {
        DatePickerDialog picker;
        ImageButton dtInicio;
        ImageButton dtFim;
        Button continuar;
        Button voltar;
        TextInputEditText q1_1;
        TextInputEditText q1_2;
        TextInputEditText q1_3;
        TextInputEditText q1_4;
        TextInputEditText q1_5;
        TextInputEditText q1_6;
        TextInputEditText q1_7;
        TextInputEditText q1_8;
        TextInputEditText q1_9;
        TextInputEditText q1_10;
        TextInputEditText ISBN;
        String q1_11 = "";
        ImageButton barCodeCamera;
        ImageButton bttSearch;

        ViewHolder() {
            continuar = findViewById(R.id.act_quesitos1_btt_continuar);
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarQuesitos();
                    switch (analise.getQ1_11()) {
                        case Analise.ConstsIdentificacoes.APENAS_ANOTACAOES:
                            int oi1 = 2;
                            break;

                        case Analise.ConstsIdentificacoes.APENAS_REACOES:
                            Intent intent = new Intent(QuesitosIdentificacaoActivity.this, QuesitosReacoesActivity.class);
                            intent.putExtra("analise", analise);
                            startActivity(intent);
                            finish();
                            break;

                        case Analise.ConstsIdentificacoes.APENAS_RESUMOS:
                            int oi2 = 1;
                           break;

                        case Analise.ConstsIdentificacoes.ENCERRAR:
                            finish();
                            break;


                    }
                }


            });

            voltar = findViewById(R.id.act_quesitos1_btt_voltar);
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }


            });

            barCodeCamera = findViewById(R.id.activity_quesitos1_barcode);
            barCodeCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), BarCodeActivity.class);
                    startActivityForResult(intent, REQUEST_BARCOCODE_RESULT);
                }


            });


            bttSearch = findViewById(R.id.activity_quesitos1_search);
            bttSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vai buscar informações do ISBN no Google
                    if ((ISBN.getText().toString() != null) && ((ISBN.getText().toString().trim().length() > 0))) {
                        buscaDadosISBN(ISBN.getText().toString().trim());
                    }
                }
            });


            dtInicio = findViewById(R.id.act_quesitos1_date_inicio);
            dtInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(QuesitosIdentificacaoActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    q1_9.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });


            dtFim = findViewById(R.id.act_quesitos1_date_fim);
            dtFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(QuesitosIdentificacaoActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    q1_10.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });

            ISBN = findViewById(R.id.act_quesitos1_ISBN);
            ISBN.setText(analise.getISBN());

            q1_1 = findViewById(R.id.act_quesitos1_1);
            q1_1.setText(analise.getQ1_1());

            q1_2 = findViewById(R.id.act_quesitos1_2);
            q1_2.setText(analise.getQ1_2());

            q1_3 = findViewById(R.id.act_quesitos1_3);
            q1_3.setText(analise.getQ1_3());

            q1_4 = findViewById(R.id.act_quesitos1_4);
            q1_4.setText(analise.getQ1_4());

            q1_5 = findViewById(R.id.act_quesitos1_5);
            q1_5.setText(analise.getQ1_5());

            q1_6 = findViewById(R.id.act_quesitos1_6);
            q1_6.setText(analise.getQ1_6());

            q1_7 = findViewById(R.id.act_quesitos1_7);
            q1_7.setText(analise.getQ1_7());

            q1_8 = findViewById(R.id.act_quesitos1_8);
            q1_8.setText(analise.getQ1_8());

            q1_9 = findViewById(R.id.act_quesitos1_9);
            q1_9.setText(analise.getQ1_9());

            q1_10 = findViewById(R.id.act_quesitos1_10);
            q1_10.setText(analise.getQ1_10());

            q1_11 = analise.getQ1_11();
            if (q1_11 != null) {
                switch (q1_11) {
                    case Analise.ConstsIdentificacoes.APENAS_ANOTACAOES:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_anotacoes)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.APENAS_REACOES:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_reacoes)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.APENAS_RESUMOS:
                        ((RadioButton) findViewById(R.id.radio_button_apenas_resumos)).setChecked(true);
                        break;

                    case Analise.ConstsIdentificacoes.ENCERRAR:
                        ((RadioButton) findViewById(R.id.radio_button_encerrar)).setChecked(true);
                        break;

                    default:
                        ((RadioButton) findViewById(R.id.radio_button_sim)).setChecked(true);//por padrão vai vir checado
                }
            } else {
                ((RadioButton) findViewById(R.id.radio_button_sim)).setChecked(true);//por padrão vai vir checado
            }
        }
    }
}
