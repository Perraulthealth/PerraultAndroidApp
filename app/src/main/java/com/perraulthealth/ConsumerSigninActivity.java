package com.perraulthealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.Doctor.DoctorHomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ConsumerSigninActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private CheckBox checkboxSignedin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private ProgressDialog progressDialog;
    private  String TAG ="ConsumerSigninActivity" ;
    private String email;
    private String password;
    private boolean checkbox;





    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_signin);





        findViewById(R.id.editTextEmail).setOnClickListener(this);
        findViewById(R.id.editTextPassword).setOnClickListener(this);
        findViewById(R.id.checkboxSignedin).setOnClickListener(this);
        findViewById(R.id.buttonSignin).setOnClickListener(this);
        findViewById(R.id.textViewSignup).setOnClickListener(this);



            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            checkboxSignedin = (CheckBox) findViewById(R.id.checkboxSignedin);

        mAuth = FirebaseAuth.getInstance();


        System.out.println("checkbox" + checkbox);
        System.out.println("FirebaseAuth Signin" + mAuth);





        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //finish();
                    //startActivity(new Intent(getApplicationContext(),ConsumerMapsActivity.class ));
                    callHome();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");


                }
                // ...
            }
        };

    }
    @Override
    public void onStart() {
        super.onStart();
      //mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

    }
    @Override
    public void onClick (View v)
    {
        int i = v.getId();

        if(i ==R.id.buttonSignin) {


            email = editTextEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
                return;
            }
            password = editTextPassword.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
                return;
            }
            //checkbox = checkboxSignedin.isChecked();
            signIn();


        }

        if(i==R.id.textViewSignup)
        {
            finish();
            startActivity(new Intent(this,ConsumerSignupActivity.class ));
        }
        if(i==R.id.checkboxSignedin) {

            checkbox = checkboxSignedin.isChecked();
        }

    }

    private void signIn() {
        Log.d(TAG, "signIn:" + email);

       //progressDialog.setMessage("Signing in User...");
        //progressDialog.show();

        // [START sign_in_with_email]
        System.out.println("FirebaseAuth signInWithEmailAndPassword" + mAuth);

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            saveSigninData( );
                            //finish();
                           // progressDialog.dismiss();
                            //otp auth
                            callHome();
                           // startActivity(new Intent(getApplicationContext(), ConsumerMapsActivity.class));
                        }


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //progressDialog.dismiss();
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(ConsumerSigninActivity.this, "auth_failed",
                                    Toast.LENGTH_SHORT).show();
                            //finish();
                            //startActivity(new Intent(getApplicationContext(), ConsumerSignupActivity.class));
                        }

                        // [START_EXCLUDE]


                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }


    private void saveSigninData() {

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            String uid = user.getUid();

        Log.d(TAG, "saveSigninData "+ mRootRef);
        Log.d(TAG, "saveSigninData "+ user);
        Log.d(TAG, "saveSigninData "+ FirebaseDatabase.getInstance());
        Log.d(TAG, "saveSigninData "+ uid);


                mRootRef.child("Users").child(uid).child("checkbox").setValue(checkbox);
        }
    private void callHome()
    {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        String uid = user.getUid();
        DatabaseReference ref = mRootRef.child("Users").child(uid).child("Usertype");
       ref.addValueEventListener(new ValueEventListener(){
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               String Usertype = dataSnapshot.getValue(String.class);
               if(Usertype.equals("Patient"))
               {
                   finish();
                   startActivity(new Intent(getApplicationContext(),ConsumerMapsActivity.class ));
               }
               if(Usertype.equals("Doctor"))
               {
                   finish();
                   startActivity(new Intent(getApplicationContext(),DoctorHomeActivity.class ));
               }

           }
           @Override
           public void onCancelled(DatabaseError firebaseError) {
               Log.e("Chat", "The read failed: " );
           }
       });
       }
}



