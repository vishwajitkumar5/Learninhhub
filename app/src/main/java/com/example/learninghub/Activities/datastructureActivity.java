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

import Constants.Constant3;

public class datastructureActivity extends AppCompatActivity {

    private static final String TAG="datastructure";
    private Context mContext;
    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datastructure);

        mContext=datastructureActivity.this;

        titleArrayList=new ArrayList<String>();

        titleArrayList.add(Constant3.ALGORITHM);
        titleArrayList.add(Constant3.STRUCTURE);
        titleArrayList.add(Constant3.DATA_STRUCTURE);
        titleArrayList.add(Constant3.LINKED_LIST);
        titleArrayList.add(Constant3.BUBBLE_SORT);
        titleArrayList.add(Constant3.INSERTION_SORT);
        titleArrayList.add(Constant3.MERGE_SORT);
        titleArrayList.add(Constant3.QUICK_SORT);
        titleArrayList.add(Constant3.LINEAR_SEARCH);
        titleArrayList.add(Constant3.BINARY_SEARCH);
        titleArrayList.add(Constant3.BINARY_TREE);
        titleArrayList.add(Constant3.BINARY_SEARCH_TREE);

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
