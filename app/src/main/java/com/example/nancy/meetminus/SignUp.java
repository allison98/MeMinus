package com.example.nancy.meetminus;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.left.rightmesh.mesh.MeshManager;
import io.left.rightmesh.util.RightMeshException;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.hasPermissions;
import static io.left.rightmesh.mesh.MeshManager.REMOVED;



public class SignUp extends Activity {
    /**
     * Add new USERS to database!!
     * Handles peer update events from the mesh - maintains a list of peers and updates the display.
     *
     * @param e event object from mesh
     */

    static int userID =0;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private String currentUserID;


    User me;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device
            // Do next code
        }



//        if (Build.VERSION.SDK_INT >= 23) {
//            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v("SignUp","Permission is granted");
//
//            } else {
//
//                Log.v("SignUp","Permission is revoked");
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//            }
//        }
//        else { //permission is automatically granted on sdk<23 upon installation
//            Log.v("SignUp","Permission is granted");
//
//        }
//        SharedPreferences settings = getSharedPreferences(MainPP_SP, 0);
//        HashMap<String, String> map = (HashMap<String, String>) settings.getAll();
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            Log.d("TAG","@@@ IN IF Build.VERSION.SDK_INT >= 23");
//            String[] PERMISSIONS = {android.Manifest.permission.CAMERA,
//                    android.Manifest.permission.READ_PHONE_STATE,
//                    android.Manifest.permission.INTERNET,
//                    android.Manifest.permission.ACCESS_NETWORK_STATE,
//                    android.Manifest.permission.ACCESS_WIFI_STATE,
//                    android. Manifest.permission.NFC,
//                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
//                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            };
//
//            if (!hasPermissions(SignUp., PERMISSIONS)) {
//                Log.d("TAG","@@@ IN IF hasPermissions");
//                ActivityCompat.requestPermissions(SignUp.this, PERMISSIONS, REQUEST );
//            } else {
//                Log.d("TAG","@@@ IN ELSE hasPermissions");
//                callNextActivity();
//            }
//        } else {
//            Log.d("TAG","@@@ IN ELSE  Build.VERSION.SDK_INT >= 23");
//            callNextActivity();
//        }
            mAuth = FirebaseAuth.getInstance();

         final EditText email = (EditText) findViewById(R.id.email);
         final EditText pass = (EditText) findViewById(R.id.password);
        //final EditText name = (EditText) findViewById(R.id.name);
        //final EditText username = (EditText) findViewById(R.id.username);
        //final EditText number = (EditText) findViewById(R.id.number);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // createUser(view, email.getText().toString(), pass.getText().toString());
                addNewUser(view, email.getText().toString(), pass.getText().toString());


                Intent i = new Intent(SignUp.this, MainActivity.class);
                i.putExtra("MeUser", me);
                startActivity(i);
            }
        });


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(SignUp.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(SignUp.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(SignUp.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

//    public void createUser(View view, String email, String pass){
//
//        mAuth.createUserWithEmailAndPassword(email, pass)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d("SIGNUP", "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(SignUp.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//
//                            }
//                        }
//
//                );
//
//
//
//
//    }


    public void addNewUser(View view, String email, String password) {

        SessionManager sessionManager = new SessionManager(SignUp.this);
        sessionManager.createLoginSession(email, password);


//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("SIGNUP", "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//                                Toast.makeText(SignUp.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(SignUp.this, "auth failed",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        } else if (task.isSuccessful()) {
//                            currentUserID = mAuth.getCurrentUser().getUid();
//                            me.userID = currentUserID;
//                            Log.d("SIGNUP", "onComplete: Authstate changed: " + currentUserID);
//                        }
//
//                        // ...
//                    }
//                });
//
//        User user = new User( email, password);
//
//        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).setValue(user);
//        me = user;
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
    }

}

