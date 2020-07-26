package com.jayshreegopalapps.motivationalquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadActivity extends AppCompatActivity {
    TextInputEditText e1, e2, e3;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        e1 = findViewById(R.id.c_id_edit_text);
        e2 = findViewById(R.id.q_text_edit_text);
        e3 = findViewById(R.id.a_id_edit_text);

        btn1 = findViewById(R.id.button_upload);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e1.getText().toString().equals("") && !e2.getText().toString().equals("") && !e3.getText().toString().equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url("https://motivational-quotes-webservice.herokuapp.com/api/uploadQuote/" + e1.getText().toString() + "/" + e2.getText().toString()).build();
                            try {
                                Response r = client.newCall(request).execute();
                                String jsonString = r.body().string();
                                final JSONObject obj = new JSONObject(jsonString);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Toast.makeText(UploadActivity.this, obj.getString(  "response"), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                obj.get("response");
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }
}
