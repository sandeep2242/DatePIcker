package pratham.com.datepicker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView start,end;
    int newDate=0,newMonth=0,newYear=0;
    Calendar calendar2,calendar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startDate);
        end = findViewById(R.id.endDate);

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DATE);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);







        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        cal.set(Calendar.HOUR_OF_DAY, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);

                        final int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                        Log.i("dates",lastDate+" ko "+dayOfMonth);
                        newDate = dayOfMonth+1;
                        newMonth = month;
                        newYear = year;
                        calendar2= getDate(newYear,newMonth,newDate);
                        calendar1 = getDate(newYear,newMonth,dayOfMonth);
                        start.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                        if ((month+1)<12){

                            if (dayOfMonth==lastDate){
                                end.setText("1"+"-"+(month+2)+"-"+year);
                            }else {
                                end.setText((dayOfMonth+1)+"-"+(month+1)+"-"+year);
                            }
                        }else {
                            if (dayOfMonth==lastDate){
                                end.setText("1"+"-"+(1)+"-"+(year+1));
                            }else {
                                end.setText((dayOfMonth+1)+"-"+(1)+"-"+(year+1));
                            }
                        }




                    }
                },year,month,day);
                if (calendar1==null){
                    datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                }else {
                    datePickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
                }

                datePickerDialog.show();

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newDate>0){
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            end.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                        }
                    },year,month,day+1);
                    datePickerDialog.getDatePicker().setMinDate(calendar2.getTimeInMillis());
                    datePickerDialog.show();
                }

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activities,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.age:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
            case R.id.ymd:
                startActivity(new Intent(MainActivity.this,YearsMonthsDays.class));
                break;
            case R.id.list:
                startActivity(new Intent(MainActivity.this,ListView.class));
                break;
            case R.id.login:
                startActivity(new Intent(MainActivity.this,FlipKartActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

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
}
