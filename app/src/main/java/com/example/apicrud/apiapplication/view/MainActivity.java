package com.example.apicrud.apiapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apicrud.apiapplication.R;
import com.example.apicrud.apiapplication.model.getall.GetResponse;
import com.example.apicrud.apiapplication.presenter.Presenter;
import com.example.apicrud.apiapplication.presenter.View;

public class MainActivity extends AppCompatActivity implements View {
    EditText ed1, ed2;
    Button btn1, btn2;
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.description);
        btn1 = findViewById(R.id.btn_submit);
        btn2 = findViewById(R.id.btn_lihat);
        presenter = new Presenter(this);
        btn1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
               presenter.createItems(ed1.getText().toString(), ed2.getText().toString());
            }
        });
        btn2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent x = new Intent(getApplicationContext(), Lihat.class);
                startActivity(x);
            }
        });
    }

    @Override
    public void getSuccess(GetResponse list) {

    }

    @Override
    public void setToast(String message) {
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onFailure(String failureMessage) {

    }
}
