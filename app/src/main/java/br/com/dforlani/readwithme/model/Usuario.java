package br.com.dforlani.readwithme.model;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Usuario extends ModelFirestore {
    private static final String TAG = "Usuario.class";

    private static final String CAMPO_EMAIL = "email";

    public static final String COLECAO = "Usuario";

    private String id;
    private String email;

    public Usuario(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario() {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static Usuario findByEmail(String email) {
        QueryDocumentSnapshot aux = Usuario.find(COLECAO, CAMPO_EMAIL, email);
        if (aux != null) {
            return aux.toObject(Usuario.class);
        } else {
            return null;
        }
    }

    /**
     * Cria um usuário para o email passado caso ele não exista
     * @param email
     * @return
     */
    public static Usuario crieUsuarioIfNotExist(String email) {
        // [START listen_state]
        final Usuario[] auxUser = {null};
        final String EMAIL = email;
        if(email != null && email.trim().length() > 0) {
            //se não tiver criado, o sistema cria uma nova coleção pra esse email
            Map<String, Object> data = new HashMap<>();
            data.put("email", EMAIL);
            Usuario.addDocument(COLECAO, data, email);
//            db.collection(COLECAO)
//                    .whereEqualTo(CAMPO_EMAIL, email)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    Log.d(TAG, "Usuario existente: "+document.getId() + " => " + document.getData());
//                                    auxUser[0] = document.toObject(Usuario.class);
//                                }
//                                if(auxUser[0] == null) {
//                                    Log.d(TAG, "Criando um usuario, pq ele não existe: ", task.getException());
//                                    Map<String, Object> data = new HashMap<>();
//                                    data.put("email", EMAIL);
//                                    Usuario.addDocument(COLECAO, data);
//                                }
//                            }
//                        }
//                    });
        }
        return auxUser[0];
        // [END listen_state]
    }
}
