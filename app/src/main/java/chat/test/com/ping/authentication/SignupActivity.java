package chat.test.com.ping.authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.OnClick;
import chat.test.com.ping.R;
import chat.test.com.ping.util.Validation;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    EditText mETEmail;

    @BindView(R.id.et_password)
    EditText mETPassword;

    private Context mContext = SignupActivity.this;//set the context
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "LoginActivity";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    /**
     * The method to handle the logic of Login button click
     * @param v
     */
    @OnClick(R.id.btn_signup)
    public void onSignupClick(View v){
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        String email = mETEmail.getText().toString();

        if(!Validation.isValidEmail(email)){
            mETEmail.setText("");
            mETEmail.requestFocus();
            mETEmail.setHint(R.string.incorrect_email);
            progressDialog.dismiss();
            return;
        }

        String password = mETPassword.getText().toString();
        if(!Validation.checkPassword(password)){
            mETPassword.setText("");
            mETPassword.requestFocus();
            mETPassword.setHint(R.string.incorrect_password);
            progressDialog.dismiss();
            return;
        }



    }


}
