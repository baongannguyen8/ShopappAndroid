package com.aptech.myapp.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.aptech.myapp.R;
import com.aptech.myapp.dtos.requests.LoginRequest;
import com.aptech.myapp.dtos.responses.LoginResponse;
import com.aptech.myapp.screens.products.ProductListActivity;
import com.aptech.myapp.services.ApiCallback;
import com.aptech.myapp.services.auth.AuthService;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;//mapping(properties <=> id in xml file)
    private EditText editTextPassword;//mapping
    private Button buttonLogin;//mapping
    private Button buttonFacebookLogin;//mapping

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this); //Resource
        setContentView(R.layout.login_activity); // connect with xml file
        //mapping
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        editTextEmail.setText("hoang.nd@aptechlearning.edu.vn");
        editTextPassword.setText("Abc123456");
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonFacebookLogin = findViewById(R.id.buttonFacebookLogin);
        //handle clicked event
        /*
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("bam loginnnnnn");
                //Toast.makeText(LoginActivity.this,"login login haha", Toast.LENGTH_LONG).show();
                //print email and password
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String displayMessage = String.format("email = %s, spassword = %s", email, password);
                //js : `email = ${email}, spassword = ${password}`
                Toast.makeText(LoginActivity.this,displayMessage, Toast.LENGTH_LONG).show();

            }
        });
         */

        buttonLogin.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }
            LoginRequest request = new LoginRequest(email, password);
            AuthService authService = new AuthService();

            authService.login(request, new ApiCallback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse response) {
                    runOnUiThread(() -> {
                        Toast.makeText(LoginActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        // Save token or navigate to the next screen
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    runOnUiThread(() -> {
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });
        buttonFacebookLogin.setOnClickListener((View v) -> {
            // Creating an intent to navigate to ProductListActivity
            Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
        //reference
        //EditText txtSample = editTextEmail;//reference(not assignment)
//        Product productA = new Product("iphone 15", 12345.6f);
//        Product productB = new Product("iphone 16", 12345.6f);
//        Product productC = productA; //reference
//        productC.name = "xxxx";//error because name is "private"
//        productC.setName("xx");
        //productA.getName() = ??
        System.out.println("haha");
    }
}