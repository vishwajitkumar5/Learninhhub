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

import Constants.Constant4;

public class PhpActivity extends AppCompatActivity {

    private static final String TAG="python";
    private Context mContext;
    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php);


        mContext = PhpActivity.this;

        titleArrayList = new ArrayList<String>();
        titleArrayList.add(Constant4.WHAT_IS_Php);
        titleArrayList.add(Constant4.HISTORY_OF_Php);
        titleArrayList.add(Constant4.FEATURES_OF_Php);
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
