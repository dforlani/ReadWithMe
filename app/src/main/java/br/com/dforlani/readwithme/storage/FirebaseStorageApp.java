package br.com.dforlani.readwithme.storage;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import br.com.dforlani.readwithme.ui.adapter.AudioAdapter;

public class FirebaseStorageApp {
    //STORAGE
    private StorageReference mStorageRef;

    public FirebaseStorageApp() {
        //FIREBASE STORAGE
        mStorageRef = com.google.firebase.storage.FirebaseStorage.getInstance().getReference();
    }


    public void upload(String email, String localPath, String filename) {
        File file = new File(localPath + "/" + filename);
        if (file.isFile()) {
            Uri fileUri = Uri.fromFile(file);
            StorageReference riversRef = mStorageRef.child(email + "/" + filename);

            riversRef.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Log.d("Upload", "erro no upload");
                        }
                    });
        }
    }

    public void download(String email, String path, String filename, String sufixFile, final AudioAdapter.OnDataReceiveCallback callbak) {
        try {
            File localFile = new File(path, filename + "." + sufixFile);
            StorageReference ref = mStorageRef.child(email + "/" + filename + "." + sufixFile);
            ref.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            callbak.onDataReceived();
                            Log.d("download", "Sucesso");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                    Log.d("download", "Erro no download");
                }
            });
        } catch (Exception e) {

        }
    }
}
