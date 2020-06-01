package com.example.learninghub.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.learninghub.R;

public class QuizActivity extends AppCompatActivity {
    private TextView title;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        title = findViewById(R.id.main_title);
        start = findViewById(R.id.ma_startB);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        title.setTypeface(typeface);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CategoryActivity.class);
                startActivity(intent);

            }
        });
    }
}
