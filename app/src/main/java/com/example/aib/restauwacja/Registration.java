package com.example.aib.restauwacja;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aib.restauwacja.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import org.springframework.util.StringUtils;

@EActivity(R.layout.activity_registration)
public class Registration extends ActionBarActivity {

    @Bean
    @NonConfigurationInstance
    RestReservationBackgroundTask restReservationBackgroundTask;

    @ViewById
    EditText emailTxt;

    @ViewById
    EditText passwordTxt;

    @ViewById
    EditText passwordTxt2;

    @ViewById
    EditText imieTxt;

    @ViewById
    EditText nazwiskoTxt;

    ProgressDialog ringProgressDialog;


    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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


    @Click
    void zalozKontoBtnClicked()
    {
        if(StringUtils.hasText(emailTxt.getText().toString()) == true
                && StringUtils.hasText(passwordTxt.getText().toString()) == true
                && StringUtils.hasText(passwordTxt2.getText().toString()) == true
                && StringUtils.hasText(imieTxt.getText().toString()) == true
                && StringUtils.hasText(nazwiskoTxt.getText().toString()) == true)
        {
            String haslo1 = passwordTxt.getText().toString();
            String haslo2 = passwordTxt2.getText().toString();
            if(haslo1.equals(haslo2))
            {
                ringProgressDialog.show();
                User user = new User();
                user.email =emailTxt.getText().toString();
                user.password = passwordTxt.getText().toString();
                user.firstName = imieTxt.getText().toString();
                user.lastName = nazwiskoTxt.getText().toString();
                user.displayName = user.firstName + " " + user.lastName;
                restReservationBackgroundTask.register(user);
            }
            else
            {
                Toast.makeText(this, "Hasla nie zgadzaja sie ze soba", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show();
        }
    }

    public void registerSuccess(User user){
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
