package com.mywebserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtEmail, edtPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtFirstName = findViewById(R.id.firstname);
        edtLastName = findViewById(R.id.edt_lastname);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnRegister = findViewById(R.id.register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstName = edtFirstName.getText().toString();
                String strLastName = edtLastName.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                loadDatafromServer(strFirstName, strLastName, strEmail, strPassword);

            }
        });
    }

    private void loadDatafromServer(String strFirstName, String strLastName, String strEmail, String strPassword) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "onResponse: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String fn = jsonObject.getString("first_name");
                    String ln = jsonObject.getString("last_name");
                    String email = jsonObject.getString("email");
                    Log.e("TAG", "onResponse: " + fn + " " + ln);

                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("firstName", fn);
                    i.putExtra("lastName", ln);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("first_name", strFirstName);
                hashMap.put("last_name", strLastName);
                hashMap.put("email", strEmail);
                hashMap.put("password", strPassword);

                return hashMap;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}