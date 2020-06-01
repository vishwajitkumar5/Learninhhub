package com.example.learninghub.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.learninghub.BuildConfig;
import com.example.learninghub.R;

public class Aboutus extends AppCompatActivity {

    TextView tvCode,tvName;
    TextView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        tvCode=findViewById(R.id.tv_code);
        tvName=findViewById(R.id.tv_name);
        back_btn=findViewById(R.id.back_aboutus);

        tvCode.setText(String.valueOf(BuildConfig.VERSION_CODE));

        tvName.setText(String.valueOf(BuildConfig.VERSION_NAME));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AboutusActivity=new Intent(getApplicationContext(),Home.class);
                startActivity(AboutusActivity);
                finish();
            }
        });

    }
}
