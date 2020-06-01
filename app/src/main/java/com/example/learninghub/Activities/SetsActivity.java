package com.example.learninghub.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.learninghub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetsActivity extends AppCompatActivity {
    private GridView sets_grid;
    private FirebaseFirestore firestore;
    private Dialog loadingDialog;
    public static int category_id;
    //private Dialog loadingDialog;
    //public static List<String> setsIDs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        Toolbar toolbar= findViewById(R.id.set_toolbar);
        setSupportActionBar(toolbar);
        String title= getIntent().getStringExtra("CATEGORY");
        category_id= getIntent().getIntExtra("CATEGORY_ID",1);

        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sets_grid= findViewById(R.id.sets_gridview);

        loadingDialog = new Dialog(SetsActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        firestore=FirebaseFirestore.getInstance();
        ///  SetsAdapter adapter = new SetsAdapter(6);
        //sets_grid.setAdapter(adapter);
        loadSets();

    }
    public void loadSets()
    {



        firestore.collection("QUIZ4").document("CAT"+String.valueOf(category_id))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    if(doc.exists())
                    {
                        long sets = (long)doc.get("SETS");

                        SetsAdapter adapter = new SetsAdapter((int)sets);
                        sets_grid.setAdapter(adapter);


                    }
                    else
                    {
                        Toast.makeText(SetsActivity.this,"No CAT Document Exists!",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else
                {

                    Toast.makeText(SetsActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            SetsActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
