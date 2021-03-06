package br.com.dforlani.readwithme.ui.audiorecorder;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.storage.FirebaseStorageApp;
import br.com.dforlani.readwithme.ui.adapter.AudioAdapter;
import br.com.dforlani.readwithme.util.Preferencias;

public class AudioRecorderActivity extends AppCompatActivity {

    private static final String TAG = "AudioRecord";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String EXTENSAO = ".3gp";
    private String filename = null;
    private Analise analise = null;


    RecyclerView audioRecycler;
    AudioAdapter audioAdapter;

    // private RecordButton recordButton = null;
    private MediaRecorder recorder = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private Button bttNovoAudio;
    boolean mStartRecording = true;

    private FirebaseStorageApp storage;



    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_audio_recorder);
        setTitle("Impressões em Áudio");
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        //alterrar a cor da barra superior do Android
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        bttNovoAudio = findViewById(R.id.act_audio_btt_novo_audio);
        bttNovoAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    bttNovoAudio.setText("Parar Gravação");
                } else {
                    bttNovoAudio.setText(R.string.novo_audio);
                }
                mStartRecording = !mStartRecording;
            }
        });

        //pega informações de uma Analise sendo editada, se for o caso
        Intent intent = getIntent();
        analise = (Analise) intent.getSerializableExtra("analise");
        audioRecycler = findViewById(R.id.audio_recycler_view);
        audioAdapter = new AudioAdapter(analise.getAudios(), this.getBaseContext(), this);
        audioRecycler.setAdapter(audioAdapter);


        storage = new FirebaseStorageApp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }


    private String getPah() {
        return getExternalCacheDir().getAbsolutePath();
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        filename = "/" + UUID.randomUUID().toString() + EXTENSAO;

        recorder.setOutputFile(getPah() + filename);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopRecording() {
        try {
            recorder.stop();
            recorder.release();

            Calendar c = Calendar.getInstance(); //retorna um calendar com a hora do sistema
            String data = c.get(Calendar.DAY_OF_MONTH)
                    + "/" + (c.get(Calendar.MONTH) + 1)
                    + "/" + c.get(Calendar.YEAR)
                    + " " + c.get(Calendar.HOUR_OF_DAY)
                    + ":" + c.get(Calendar.MINUTE)
                    + ":" + c.get(Calendar.SECOND);

            adicionarAudioToAnalise(filename, data);
            Preferencias pref = new Preferencias(this.getBaseContext());
            String email = pref.getEmail();
            storage.upload(email, getPah(), filename);

        } catch (Exception e) {
            Log.d(TAG, "Impossível parar");
        }
        recorder = null;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }

    public void adicionarAudioToAnalise(String filename, String data) {
        Map<String, String> audio = new HashMap<>();
        audio.put(Analise.COLUMN_AUDIO_DATA, data);
        audio.put(Analise.COLUMN_AUDIO_NOME, filename);
        analise.getAudios().add(audio);

        audioAdapter.notifyDataSetChanged();

        //prepara o retorno, pro caso do usuário fechar a tela
        Bundle bundle= new Bundle();
        bundle.putSerializable("analise", analise);
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);

    }


}
