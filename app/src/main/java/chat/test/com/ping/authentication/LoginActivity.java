package chat.test.com.ping.authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chat.test.com.ping.R;
import chat.test.com.ping.message.MessageActivity;
import chat.test.com.ping.model.PingUser;
import chat.test.com.ping.util.Constants;
import chat.test.com.ping.util.PreferencesStorage;
import chat.test.com.ping.util.Validation;

/**
 * Handles all tasks related to user's signIn
 *
 * @author Devesh Shetty
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    EditText mETEmail;

    @BindView(R.id.et_password)
    EditText mETPassword;

    private Context mContext = LoginActivity.this;//set the context
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "LoginActivity";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String userId = PreferencesStorage.getValueForKey(mContext, PreferencesStorage.USER_ID);

        if(userId != null){
            //no need to show the login/signup screen again
            Intent intent = new Intent(mContext, MessageActivity.class);
            startActivity(intent);
            finish();
        }

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if(user != null){
                //User has logged in
                Intent intent = new Intent(mContext, MessageActivity.class);
                startActivity(intent);
                finish();
            }else{

                //User has logged out
                PreferencesStorage.clearData(mContext);

            }

        };
    }

    /**
     *
     * @param view
     */
    @OnClick({R.id.btn_login, R.id.btn_signup})
    public void onButtonClick(View view){
        Log.d(TAG, "Button clicked");
        String userInfo[] = getUserInfo();
        if(userInfo == null){
            return;
        }

        String email = userInfo[0],
                password = userInfo[1];


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference pingUsersReference = firebaseDatabase.getReference(Constants.PING_USERS_REF);

        int id = view.getId();

        switch (id){
            case R.id.btn_login:
                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, (task)->{

                            if(!task.isSuccessful()){
                                Toast.makeText(mContext, R.string.login_failed, Toast.LENGTH_LONG).show();
                            }

                        });
                break;

            case R.id.btn_signup:
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, (task)->{

                            if(!task.isSuccessful()){
                                Toast.makeText(mContext, R.string.signup_failed, Toast.LENGTH_LONG).show();
                            }else{
                                //get the auto-generated key to the child location
                                String userId = pingUsersReference.push().getKey();
                                PingUser user = new PingUser(userId, email, null);
                                //store the user object at the child key location
                                pingUsersReference.child(userId).setValue(user);

                                //store the userId in the preferences
                                PreferencesStorage.setValueForKey(mContext, PreferencesStorage.USER_ID, userId);

                            }

                        });
                break;
        }


        progressDialog.dismiss();

    }

    /**
     *
     * @return null if invalid email id and password is provided else return res[] containing the email and password
     */
    private String[] getUserInfo(){
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        String email = mETEmail.getText().toString().trim();
        if(!Validation.isValidEmail(email)){
            mETEmail.setText("");
            mETEmail.requestFocus();
            mETEmail.setHint(R.string.incorrect_email);
            progressDialog.dismiss();
            return null;
        }

        String password = mETPassword.getText().toString().trim();
        if(!Validation.checkPassword(password)){
            mETPassword.setText("");
            mETPassword.requestFocus();
            mETPassword.setHint(R.string.incorrect_password);
            progressDialog.dismiss();
            return null;
        }

        return new String[]{email, password};
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }



}
