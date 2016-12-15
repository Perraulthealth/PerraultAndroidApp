package com.perraulthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

//import java.util.function.Consumer;

public class ConsumerSignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextMobile;
    private EditText editTextEmail;
    private EditText editTextPassword;
     private String email;
     private String mobile;
     private String password;



    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    //private ProgressDialog progressDialog;
    private String TAG ="ConsumerSignupActivity" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_signup);



        findViewById(R.id.editTextMobile).setOnClickListener(this);
        findViewById(R.id.editTextEmail).setOnClickListener(this);
        findViewById(R.id.editTextPassword).setOnClickListener(this);
        findViewById(R.id.buttonSignup).setOnClickListener(this);
        findViewById(R.id.textViewSignin).setOnClickListener(this);












        mAuth = FirebaseAuth.getInstance();

       System.out.println("FirebaseAuth" + mAuth);
        //mAuth = com.google.android.gms.internal.zzahu@42992438
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                System.out.println("FirebaseUser-tarun" + user);
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:-tarun" + user.getUid());
                    //startActivity(new Intent(getApplicationContext(),ConsumerMapsSigninActivity.class ));
                    callHome();
                   // finish();
                    //startActivity(new Intent(getApplicationContext(),ConsumerMapsActivity.class ));
                    //startActivity(new Intent(getApplicationContext(),WhoAreYou.class ));
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out-tarun");
                    //createAccount();
                    //finish();
                    //startActivity(new Intent(getApplicationContext(),ConsumerSigninActivity.class ));
                }
                // ...
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v)
    {


        int i = v.getId();
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        if(i ==R.id.buttonSignup) {
                mobile = editTextMobile.getText().toString().trim();
                System.out.println("mobile" + mobile);
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(this, "Please enter the mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
            email = editTextEmail.getText().toString().trim();
            System.out.println("email" + email);
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
                return;
            }
            password = editTextPassword.getText().toString().trim();
            System.out.println("password" + password);


            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please Create the password", Toast.LENGTH_LONG).show();
                return;
            }
            int len = password.length();

            if(len<6)
            {
                Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_LONG).show();
                return;
            }

            createAccount();
        }

        if(i==R.id.textViewSignin)
        {
            finish();
            startActivity(new Intent(this,ConsumerSigninActivity.class ));
        }

    }

    private void createAccount( ) {

        System.out.println("email " + email);
        System.out.println("password " + password);
        System.out.println("mobile " + mobile);

        Log.d(TAG, "createUserWithEmail:onComplete:" + mobile+ email+ password);
        Log.d(TAG, "mAuth.getInstance() " + FirebaseAuth.getInstance());
        //progressDialog.setMessage("Registering User...");
        //progressDialog.show();

        // [START create_user_with_email]
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            saveSignupData( );
                            finish();
                           // progressDialog.dismiss();
                            //otp auth
                           // startActivity(new Intent(getApplicationContext(), ConsumerMapsActivity.class));
                            startActivity(new Intent(getApplicationContext(), WhoAreYou.class));
                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            //progressDialog.dismiss();
                            Toast.makeText(ConsumerSignupActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();

                           // finish();
                            //startActivity(new Intent(getApplicationContext(), ConsumerSigninActivity.class));
                        }

                        // [START_EXCLUDE]
                        //progressDialog.dismiss();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]

    }


    private void saveSignupData() {



        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Log.d(TAG, "user " + user);
        // Log.d(TAG, "mAuth " + mAuth);
        Log.d(TAG, "mAuth.getInstance() " + FirebaseAuth.getInstance());
        Log.d(TAG, "mRootRef " + mRootRef);
        Log.d(TAG, "FirebaseDatabase.getInstance() " + FirebaseDatabase.getInstance());
        Log.d(TAG, "email "+ email);
        Log.d(TAG, "password "+ password);
        Log.d(TAG, "mobile "+ mobile);
        Log.d(TAG, "uid "+ uid);

        mRootRef.child("Users").child(uid).child("email").setValue(email);
        Log.d(TAG, "mRootRef.child - Consumer ref-tarun :"+  mRootRef.child("Users").getRef());
        mRootRef.child("Users").child(uid).child("password").setValue(password);
        mRootRef.child("Users").child(uid).child("mobile").setValue(mobile);
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
                Log.d(TAG, "Usertype"+  Usertype);

                if(null==Usertype) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), WhoAreYou.class));
                }
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









