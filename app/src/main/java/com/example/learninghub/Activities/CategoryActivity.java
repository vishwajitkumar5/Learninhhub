package com.example.learninghub.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.learninghub.R;

import static com.example.learninghub.Activities.SlpashActivity.catList;



public class CategoryActivity extends AppCompatActivity {
    private GridView catGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridview);

        // List<String> catList= new ArrayList<>();

        /*catList.add("CAT 1");
        catList.add("CAT 2");
        catList.add("CAT 3");
        catList.add("CAT 4");
        catList.add("CAT 5");
        catList.add("CAT 6");
        catList.add("CAT 7");
        catList.add("CAT 8");*/
        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            CategoryActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
