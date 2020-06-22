package br.com.dforlani.readwithme.ui.adapter;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.quesitos.Quesitos1Activity;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
    public List<Map<String, String>> audios;
    Context context;
    Activity parent;
    private MediaPlayer player = null;
    private static final String TAG = "AudioRecordAdapter";


    public AudioAdapter(List<Map<String, String>> audios, Context context, Activity parent) {
        this.audios = audios;
        this.context = context;
        this.parent = parent;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_audio, parent, false);
        return new AudioViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        Map<String, String> audio = audios.get(position);
        holder.bind(audio);
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }


    class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView textDataAudio;
        View itemView;
        ImageButton bttStartStop, bttDelete;
        Map<String, String> audio;
        boolean mStartPlaying = true;


        AudioViewHolder(final View itemView) {
            super(itemView);

            this.itemView = itemView;
            textDataAudio = itemView.findViewById(R.id.adapter_audio_data);
            bttStartStop = itemView.findViewById(R.id.adapter_item_audio_play_stop);
            bttStartStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onPlay(mStartPlaying, context.getExternalCacheDir().getAbsolutePath() + audio.get(Analise.COLUMN_AUDIO_NOME));
                    if (mStartPlaying) {
                        bttStartStop.setImageResource(R.drawable.ic_stop_black_24dp);
                    } else {
                        bttStartStop.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                    mStartPlaying = !mStartPlaying;

                }
            });

            bttDelete = itemView.findViewById(R.id.adapter_item_audio_delete);
            bttDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dialog de confirmação para remoção
                    AlertDialog.Builder alert = new AlertDialog.Builder(parent);
                    alert.setTitle("Remover Áudio");
                    alert.setMessage("Deseja realmente remover este áudio?");
                    alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            audios.remove(audio);
                            notifyDataSetChanged();
                        }
                    });
                    alert.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close dialog
                            dialog.cancel();
                        }
                    });
                    alert.show();


                }
            });

        }

        void bind(Map<String, String> audio) {
            this.audio = audio;
            textDataAudio.setText(audio.get("data"));
        }

        private void onPlay(boolean start, String filename) {
            if (start) {
                startPlaying(filename);
            } else {
                stopPlaying();
            }
        }

        private void startPlaying(String fileName) {
            player = new MediaPlayer();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    bttStartStop.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }

            });
            try {
                player.setDataSource(fileName);
                player.prepare();
                player.start();

            } catch (IOException e) {
                Log.e(TAG, "prepare() failed");
            }
        }

        private void stopPlaying() {
            if (player != null)
                player.release();
            player = null;
        }
    }

}