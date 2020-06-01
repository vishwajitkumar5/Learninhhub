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

import Constants.Constant2;

public class pythonActivity extends AppCompatActivity {



    private static final String TAG="python";
    private Context mContext;
    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);


        mContext = pythonActivity.this;

        titleArrayList = new ArrayList<String>();
        titleArrayList.add(Constant2.Geeting_STARTED);
        titleArrayList.add(Constant2.INTRO_TO_PYTHON);
        titleArrayList.add(Constant2.PYTHON_SYNTAX);
        titleArrayList.add(Constant2.PYTHON_COMMENT);
        titleArrayList.add(Constant2.PYTHON_VARIABLE);
        titleArrayList.add(Constant2.PYTHON_DATATYPE);
        titleArrayList.add(Constant2.PYTHON_NUMBER);
        titleArrayList.add(Constant2.PYTHON_STRING);
        titleArrayList.add(Constant2.PYTHON_BOOLEAN);
        titleArrayList.add(Constant2.PYTHON_OPERATOR);
        titleArrayList.add(Constant2.PYTHON_LIST);
        titleArrayList.add(Constant2.PYTHON_TUPLE);
        titleArrayList.add(Constant2.PYTHON_SET);
        titleArrayList.add(Constant2.PYTHON_DICTIONARIES);
        titleArrayList.add(Constant2.PYTHON_CONDITION);
        titleArrayList.add(Constant2.PYTHON_WHILELOOP);
        titleArrayList.add(Constant2.PYTHON_FORLOOP);
        titleArrayList.add(Constant2.PYTHON_FUNCTION);
        titleArrayList.add(Constant2.PYTHON_ARRAY);
        titleArrayList.add(Constant2.PYTHON_CLASSES);
        titleArrayList.add(Constant2.PYTHON_TRYEXCEPT);
        titleArrayList.add(Constant2.PYTHON_USERINPUT);
        titleArrayList.add(Constant2.PYTHON_FILEOPEN);
        titleArrayList.add(Constant2.PYTHON_IMPKEYWORD);
        titleArrayList.add(Constant2.PYTHON_FILEMETHOD);
        titleArrayList.add(Constant2.PYTHON_SCOPE);



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
