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

import Constants.Constants;

public class javaActivity extends AppCompatActivity {
    private static final String TAG="Java";
    private Context mContext;
    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        mContext=javaActivity.this;

        titleArrayList=new ArrayList<String>();

        titleArrayList.add(Constants.WHAT_IS_JAVA);
        titleArrayList.add(Constants.HISTORY_OF_JAVA);
        titleArrayList.add(Constants.FEATURES_OF_JAVA);
        titleArrayList.add(Constants.C_VS_JAVA);
        titleArrayList.add(Constants.HELLO_JAVA_PROGRAM);
        titleArrayList.add(Constants.PROGRAM_INTERNAL);
        titleArrayList.add(Constants.HOW_TO_SET_PATH);
        titleArrayList.add(Constants.JDK_JRE_AND_JVM);
        titleArrayList.add(Constants.INTERNAL_DETAIL_OF_JVM);
        titleArrayList.add(Constants.JAVA_VARIABLES);
        titleArrayList.add(Constants.JAVA_DATA_TYPES);
        titleArrayList.add(Constants.UNICODE_SYSTEM);
        titleArrayList.add(Constants.OPERATORS);














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
