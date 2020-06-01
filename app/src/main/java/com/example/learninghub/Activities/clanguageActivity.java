package com.example.learninghub.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learninghub.R;

import java.util.ArrayList;

import Constants.Constants1;

public class clanguageActivity extends AppCompatActivity {
    private static final String TAG="Clanguage";
    private Context mContext;
    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanguage);


        mContext=clanguageActivity.this;

        titleArrayList=new ArrayList<String>();


        titleArrayList.add(Constants1.WHAT_IS_C);
        titleArrayList.add(Constants1.HISTORY_OF_C);
        titleArrayList.add(Constants1.HOW_INSTALL_C);
        titleArrayList.add(Constants1.FEATURES_OF_C);
        titleArrayList.add(Constants1.VARIABLES_IN_C);
        titleArrayList.add(Constants1.FIRST_C_PROGRAM);
        titleArrayList.add(Constants1.IF_ELSE_CONDITION);
        titleArrayList.add(Constants1.C_ARRAY);
        titleArrayList.add(Constants1.C_FUNCTION);
        titleArrayList.add(Constants1.C_POINTER);
        titleArrayList.add(Constants1.CALL_REFERENCE);
        titleArrayList.add(Constants1.FIBONACCI_SERIES_IN_C);
        titleArrayList.add(Constants1.MATRIX_MULTIPLICATION_IN_C);
        titleArrayList.add(Constants1.PALINDROME_IN_C);







        mRecyclerView=(RecyclerView) findViewById(R.id.title_layout_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        TitleAdapter adapter=new TitleAdapter(mContext, titleArrayList,new CustomItemClickListener() {
            @Override
            public void onItemclick(View v, int position) {

                Intent desIntent=new Intent(mContext,DescriptionActivity.class);

                desIntent.putExtra("titles",titleArrayList.get(position));


                startActivity(desIntent);



                //Toast.makeText(mContext,"clicked"+titleArrayList.get(position),Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setAdapter(adapter);

    }
}
