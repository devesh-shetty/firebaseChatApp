package chat.test.com.ping.authentication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chat.test.com.ping.R;
import chat.test.com.ping.message.MessageActivity;
import chat.test.com.ping.util.Validation;

/**
 * Handles all tasks related to user's signIn
 *
 * @author Devesh Shetty
 */
public class LoginActivity extends AppCompatActivity {

    private Context mContext = LoginActivity.this;//set the context

    @BindView(R.id.et_email)
    EditText mETEmail;

    @BindView(R.id.et_password)
    EditText mETPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * The method to handle the logic of Login button click
     * @param view
     */
    @OnClick(R.id.btn_login)
    public void onLoginClick(View view){
        String email = mETEmail.getText().toString();

        if(!Validation.isValidEmail(email)){
            mETEmail.setText("");
            mETEmail.requestFocus();
            mETEmail.setHint("Email id is incorrect");
            return;
        }

        String password = mETPassword.getText().toString();
        if(!Validation.checkPassword(password)){
            mETPassword.setText("");
            mETPassword.requestFocus();
            mETPassword.setHint("Password is incorrect");
            return;
        }



        Intent intent = new Intent(mContext, MessageActivity.class);
        startActivity(intent);
    }

    /**
     * The method to handle the logic of Signup button click
     * @param view
     */
    @OnClick(R.id.btn_signup)
    public void onSignupClick(View view){
        Intent intent = new Intent(mContext, SignupActivity.class);
        startActivity(intent);
    }


}
