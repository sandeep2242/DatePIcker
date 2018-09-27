package pratham.com.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    TextView start, end;
    Date dob;
    int limit = 13 * 365;

    public static Calendar getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start = findViewById(R.id.startDate);

        final Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DATE);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, year - 13);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {

                        int totalDays = getTimeRemaining(getDate(year2, month2, dayOfMonth2));

                        start.setText(dayOfMonth2 + "-" + month2 + "-" + year2);

                        if (totalDays < limit) {
                            Toast.makeText(Main2Activity.this, "You should be 13 years or above", Toast.LENGTH_LONG).show();

                        }


                    }
                }, year, month, day);
                pickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                pickerDialog.show();
            }
        });

    }

    public int getTimeRemaining(Calendar calendar)
    {
        Calendar sDate = toCalendar(calendar.getTimeInMillis());
        Calendar eDate = toCalendar(System.currentTimeMillis());

        // Get the represented date in milliseconds
        long milis1 = sDate.getTimeInMillis(); // why didn't use calendar.getTimeInMillis()?
        long milis2 = eDate.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = Math.abs(milis2 - milis1);

        return (int)(diff / (24 * 60 * 60 * 1000));
    }

    private Calendar toCalendar(long timestamp)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
