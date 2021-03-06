package br.com.dforlani.readwithme.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class GoogleBooksAPI extends AsyncTask<String, Object, JSONObject> {

    ConnectivityManager mConnectivityManager;
    Context context;
    EditText titulo, autores;
    ProgressBar progressBar;
    Activity act;

    public GoogleBooksAPI(Context context, EditText titulo, EditText autores, ProgressBar progressBar, Activity act) {
        this.act = act;
        this.titulo = titulo;
        this.autores = autores;
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        // Check network connection.
        if (isNetworkConnected() == false) {
            // Cancel request.
            Log.i(getClass().getName(), "Not connected to the internet");
            Toast.makeText(this.context, "Não foi possível buscar, pois não foi detectada conexão com a Internet.", Toast.LENGTH_LONG).show();
            encerraProgressBar();
            cancel(true);
            return;
        }
    }

    @Override
    public JSONObject doInBackground(String... isbns) {

        //StrictMode detexta problemas e avisa ao desenvolvedor, deve ser utilizado apenas em desenvolvimento
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        // Stop if cancelled
        if (isCancelled()) {
            return null;
        }

        String apiUrlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbns[0];
        try {
            HttpURLConnection connection = null;
            // Build Connection.
            try {
                URL url = new URL(apiUrlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000); // 5 seconds
                connection.setConnectTimeout(5000); // 5 seconds
            } catch (MalformedURLException e) {
                // Impossible: The only two URLs used in the app are taken from string resources.
                e.printStackTrace();
            } catch (ProtocolException e) {
                // Impossible: "GET" is a perfectly valid request method.
                e.printStackTrace();
            }
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                Log.w(getClass().getName(), "GoogleBooksAPI request failed. Response Code: " + responseCode);
                connection.disconnect();
                return null;
            }

            // Read data from response.
            StringBuilder builder = new StringBuilder();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = responseReader.readLine();
            while (line != null) {
                builder.append(line);
                line = responseReader.readLine();
            }
            String responseString = builder.toString();
            Log.d(getClass().getName(), "Response String: " + responseString);
            JSONObject responseJson = new JSONObject(responseString);
            // Close connection and return response code.
            connection.disconnect();

            atualizaUI(responseJson);
            return responseJson;
        } catch (SocketTimeoutException e) {
            Log.w(getClass().getName(), "Connection timed out. Returning null");
            return null;
        } catch (IOException e) {
            Log.d(getClass().getName(), "IOException when connecting to Google Books API.");
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            Log.d(getClass().getName(), "JSONException when connecting to Google Books API.");
            e.printStackTrace();
            return null;
        }
    }

    private void encerraProgressBar() {
        act.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void atualizaUI(final JSONObject json) {
        encerraProgressBar();
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (json != null && ((Integer) json.get("totalItems")) > 0) {

                        titulo.setText(json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").get("title").toString());
                        String autoresText = "";
                        JSONArray titulos = null;
                        titulos = json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors");
                        String separador = "";
                        for (int i = 0; i < titulos.length(); i++) {
                            autoresText += separador + titulos.get(i).toString();
                            separador = ", ";
                        }
                        if (autoresText.length() > 0) {
                            autores.setText(autoresText);
                        }

                    } else
                        Toast.makeText(context, "Não foi encontrado nenhum livro para esse ISBN", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(context, "Não foi encontrado nenhum livro para esse ISBN", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onPostExecute(JSONObject responseJson) {

        if (isCancelled()) {
            // Request was cancelled due to no network connection.
            Toast.makeText(this.context, "Cancelado", Toast.LENGTH_LONG);
        } else if (responseJson == null) {
            Toast.makeText(this.context, "Nenhum informação encontrada", Toast.LENGTH_LONG);
        } else {
            // All went well. Do something with your new JSONObject.
        }
        encerraProgressBar();
    }


    protected boolean isNetworkConnected() {

        // Instantiate mConnectivityManager if necessary
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        // Is device connected to the Internet?
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}