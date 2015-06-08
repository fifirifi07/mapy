package com.example.aib.restauwacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aib.restauwacja.data.EmailAndPassword;
import com.example.aib.restauwacja.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.StringUtils;

@EActivity(R.layout.activity_login)
public class Login extends ActionBarActivity {
    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText emailTxt;
    @ViewById
    EditText passwordTxt;

    ProgressDialog ringProgressDialog;

    private static Button loginButton;
    private static Button registrationButton;

    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginOnClick();
        RegistrationOnClick();
    }*/


    @Click
    void loginBtnClicked()
    {
        if(StringUtils.hasText(emailTxt.getText().toString()) == true && StringUtils.hasText(passwordTxt.getText().toString()) == true) {
            ringProgressDialog.show();
            EmailAndPassword emailAndPassword = new EmailAndPassword();
            emailAndPassword.email = emailTxt.getText().toString(); //example@example.com
            emailAndPassword.password = passwordTxt.getText().toString(); //test00
            restLoginBackgroundTask.login(emailAndPassword);
        } else{
            Toast.makeText(this, "Uzupełnij wymagane pola", Toast.LENGTH_LONG).show();
        }
    }

    /*
    public void LoginOnClick()
    {
        loginButton = (Button)findViewById(R.id.loginBtn);
        if(StringUtils.hasText(emailTxt.getText().toString()) == true && StringUtils.hasText(passwordTxt.getText().toString()) == true) {
            ringProgressDialog.show();
            EmailAndPassword emailAndPassword = new EmailAndPassword();
            emailAndPassword.email = emailTxt.getText().toString(); //example@example.com
            emailAndPassword.password = passwordTxt.getText().toString(); //test00
            restLoginBackgroundTask.login(emailAndPassword);
        } else{
            Toast.makeText(this, "Uzupełnij wymagane pola", Toast.LENGTH_LONG).show();
        }

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.aib.restauwacja.UserReservations");
                        startActivity(intent);
                    }
                }
        );
    }*/

    @Click
    void registrationBtnClicked()
    {
        Registration_.intent(this).start();
    }

    @Click
    void facebookBtnClicked()
    {
        MapsActivity_.intent(this).start();
    }


    /*
    public void RegistrationOnClick()
    {
        registrationButton = (Button)findViewById(R.id.registrationBtn);
        registrationButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.aib.restauwacja.Registration");
                        startActivity(intent);
                    }
                }
        );
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginSuccess(User user){
        ringProgressDialog.dismiss();
        UserReservations_.intent(this).user(user).start();
        Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Błąd\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}
