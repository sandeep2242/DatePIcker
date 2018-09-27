package pratham.com.datepicker;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_language,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.english:
                changeLanguage("en");
                break;
            case R.id.hindi:
                changeLanguage("hi");
                break;
            case R.id.french:
                changeLanguage("fr");
                break;
            case R.id.japanese:
                changeLanguage("ja");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void changeLanguage(String lang){
        Locale l = new Locale(lang);//what is the use of this class
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = l;
        resources.updateConfiguration(configuration,metrics);
        finish();
        startActivity(new Intent(LoginActivity.this,LoginActivity.class));
    }
}
