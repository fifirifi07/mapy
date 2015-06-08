package com.example.aib.restauwacja;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class DateAndTimePicker extends ActionBarActivity {

    EditText edit;

    Button dateBtn;
    Button timeBtn;
    Button reserveBtn;

    int year_x, mounth_x, day_x;
    int hour_x, minute_x;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.making_reservation);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        mounth_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDateOnButtonClick();
        showTimeOnButtonClick();
        edit = (EditText) findViewById(R.id.amountEditText);
        reserveBtn = (Button) findViewById(R.id.makeReservationButton);
        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = edit.getText().toString();
                Toast.makeText(DateAndTimePicker.this, edit.getText(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void showDateOnButtonClick()
    {
        dateBtn = (Button)findViewById(R.id.datePickerButton);

        dateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);

                    }
                }
        );
    }


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            mounth_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(DateAndTimePicker.this, day_x + "/" + mounth_x + "/" + year_x, Toast.LENGTH_SHORT).show();
        }
    };

    public void showTimeOnButtonClick()
    {
        timeBtn = (Button)findViewById(R.id.timePickerButton);
        timeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(TIME_DIALOG_ID);
                    }
                }
        );

    }

    protected TimePickerDialog.OnTimeSetListener tpickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            Toast.makeText(DateAndTimePicker.this, hour_x + ":" + minute_x, Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected Dialog onCreateDialog(int id)
    {

        if (id == TIME_DIALOG_ID)
            return new TimePickerDialog(DateAndTimePicker.this, tpickerListener, hour_x, minute_x, true);
        else if (id == DATE_DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, mounth_x, day_x);
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
