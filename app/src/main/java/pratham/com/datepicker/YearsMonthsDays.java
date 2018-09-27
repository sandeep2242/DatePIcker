package pratham.com.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class YearsMonthsDays extends AppCompatActivity {

    int  yDob,yDob2;
    int year;
    Calendar cal;
    Boolean isCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years_months_days);

        final TextView dob = findViewById(R.id.dob);
        final TextView ymdT = findViewById(R.id.ymdT);
        final TextView future = findViewById(R.id.future);
        final Switch stch = findViewById(R.id.switchBtn);

        stch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ymdT.setVisibility(View.GONE);
                    future.setVisibility(View.VISIBLE);
                    isCheck = true;
                }else {
                    future.setVisibility(View.GONE);
                    ymdT.setVisibility(View.VISIBLE);
                    ymdT.setText("");
                    isCheck = false;
                }
            }
        });

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(YearsMonthsDays.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int y, m, d;
                        dob.setText(dayOfMonth + "-" + (month + 1) + "-" + year);


                        yDob = year;
                        cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.YEAR, year);

                        int remaing = getTimeRemaining(cal);
                        int leapDays = leapyear(year);
                        y = remaing / 365;
                        m = (remaing % 365) / 30;
                        d = Math.abs(((remaing % 365) % 30) - leapDays);

                        String s = "You are " + y + " years ," + m + " months ," + d + " days Old";
                        ymdT.setVisibility(View.VISIBLE);
                        ymdT.setText(s);

                        //          dob.setText(String.valueOf(remaing));
                    }
                }, year, month, day);
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dialog.show();
            }
        });

        future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(YearsMonthsDays.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int y, m, d;
                        future.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                        yDob2=year;
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.YEAR, year);

                        int remaing = getTimeRemaining(cal);
                        int leapDays = leapyear(year);
                        y = remaing / 365;
                        m = (remaing % 365) / 30;
                        d = Math.abs(((remaing % 365) % 30) - leapDays);

                        String s = "On "+dayOfMonth + "-" + (month + 1) + "-" + year+" You will be \n" + y + " years ," + m + " months ," + d + " days Old";
                        ymdT.setVisibility(View.VISIBLE);
                        ymdT.setText(s);

                        //          dob.setText(String.valueOf(remaing));
                    }
                }, year, month, day);
                dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                dialog.show();
            }
        });
    }

    public int getTimeRemaining(Calendar calendar) {
        Calendar sDate = toCalendar(calendar.getTimeInMillis());
        Calendar eDate;
        if (isCheck) {
            eDate = toCalendar(cal.getTimeInMillis());
        } else {
            eDate = toCalendar(System.currentTimeMillis());
        }


        // Get the represented date in milliseconds
        long milis1 = sDate.getTimeInMillis(); // why didn't use calendar.getTimeInMillis()?
        long milis2 = eDate.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = Math.abs(milis2 - milis1);

        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    private Calendar toCalendar(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private int leapyear(int yDob) {
        int extraLeapDay = 0;

        if (isCheck) {
            for (int i = this.yDob; i <= yDob2; i++) {

                if ((i % 400 == 0) || ((i % 4 == 0) && (i % 100 != 0))) {
                    extraLeapDay++;
                }
            }
        } else {
            for (int i = yDob; i <= year; i++) {

                if ((i % 400 == 0) || ((i % 4 == 0) && (i % 100 != 0))) {
                    extraLeapDay++;
                }
            }
        }

        return extraLeapDay;
    }
}
