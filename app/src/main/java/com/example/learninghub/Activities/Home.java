package com.example.learninghub.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learninghub.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button btn_java, btn_clanguage, btn_datastructure, btn_php, btn_python, btn_assesment;


    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Dialog popAddPost;
    ImageView popupUserImage, popupPostImage, popupAddBtn;
    TextView popupTitle, popupDescription;
    ProgressBar popupClickProgress;
    private Uri pickedImgUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        btn_java = findViewById(R.id.buttonJava);
        btn_clanguage = findViewById(R.id.buttonC_Language);
        btn_datastructure = findViewById(R.id.buttonData_Structure);
        btn_php = findViewById(R.id.buttonPhp);
        btn_python = findViewById(R.id.buttonPython);
        btn_assesment = findViewById(R.id.buttonAssesment);

        btn_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent javaIntent =new Intent(Home.this,javaActivity.class);
                startActivity(javaIntent);
            }
        });

        btn_clanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clanguageIntent =new Intent(Home.this,clanguageActivity.class);
                startActivity(clanguageIntent);
            }
        });

        btn_datastructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent datastructureIntent =new Intent(Home.this,datastructureActivity.class);
                startActivity(datastructureIntent);
            }
        });
        btn_php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phpIntent =new Intent(Home.this,PhpActivity.class);
                startActivity(phpIntent);
            }
        });

        btn_python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pythonIntent =new Intent(Home.this,pythonActivity.class);
                startActivity(pythonIntent);
            }
        });

        btn_assesment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent assesmentIntent =new Intent(Home.this,SlpashActivity.class);
              startActivity(assesmentIntent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        return true;

    }

    @SuppressWarnings("statementWithEmptyBody")

    private void updateNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhot = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());


        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent HomeActivity = new Intent(getApplicationContext(), Home.class);
            startActivity(HomeActivity);
            finish();
        } else if (id == R.id.nav_Aboutus) {

            Intent HomeActivity = new Intent(getApplicationContext(), Aboutus.class);
            startActivity(HomeActivity);
            finish();
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad";
            String shareSubject = "Your subject here";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "ShareVia"));
        } else if (id == R.id.nav_Logout) {

            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
