package com.example.learninghub.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learninghub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private CircleImageView ProfileImage;
    static int PReqCode=1 ;
    static int REQUESTCODE=1 ;
    private static final int PICK_IMAGE = 1;
    Uri pickedImgUri;
    private TextView login;


    private EditText userName,userEmail,userPassword,userPassword2;
    private ProgressBar loadingProgress;
    private Button regBtn;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //fire base

        userName=findViewById(R.id.Name);
        userEmail=findViewById(R.id.Emailid);
        userPassword=findViewById(R.id.password);
        userPassword2=findViewById(R.id.repassword);
        loadingProgress=findViewById(R.id.progressBar);
        regBtn=findViewById(R.id.button);
        loadingProgress.setVisibility(View.INVISIBLE);
         login=findViewById(R.id.Login_register);
        mAuth = FirebaseAuth.getInstance();




        ProfileImage=(CircleImageView) findViewById(R.id.regUserPhoto);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().start(RegisterActivity.this);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(Register);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(view.VISIBLE);
                final String email=userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2=userPassword2.getText().toString();
                final String name=userName.getText().toString();
                if( email.isEmpty() || name.isEmpty() ||password.isEmpty() || password2.isEmpty() ||!password.equals(password2)){
                    // something goes wrong:all fields must be filled
                    //we need to display an error message
                    showMessage("Please Varify all fields");

                    regBtn.setVisibility(view.VISIBLE);
                    loadingProgress.setVisibility(view.INVISIBLE);
                }
                else {
                    //everything is ok and all fields now we can start creating user account
                    createUserAccount(email,name,password);
                }
            }
        });

    }

    private void createUserAccount(String email, final String name, String password) {
        //this method creates user account with specific email and password

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //user account created
                            showMessage("Account created");
                            //after we created user account we need to update his profile picture and name
                            updateUserInfo( name,pickedImgUri,mAuth.getCurrentUser());
                        }
                        else
                        {
                            //account creation failed
                            showMessage("Account creation failed"+task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }
    //update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {
        // first we need to upload user photo to firebase storage and get url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath=mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri) .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //image upload sucessfull
                //now we can get our image url
                imageFilePath.getDownloadUrl() .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //uri contain user image url
                        UserProfileChangeRequest profileUpdate=new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            showMessage("REGISTER COMPLETE");
                                            updateUI();
                                        }

                                    }
                                });


                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent homeActivity=new Intent(getApplicationContext(), Home.class);
        startActivity(homeActivity);
        finish();

    }


    //simple method to show toast message
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                pickedImgUri = result.getUri();
                ProfileImage.setImageURI(pickedImgUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this," "+error,Toast.LENGTH_SHORT).show();
            }
        }


    }
}
