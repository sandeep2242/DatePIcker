package pratham.com.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Recy extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Movie> movies = new ArrayList<>();
    String name[] = {"Inception","The Dark Knight","Interstellar","Gravity"};
    String language[] = {"English.Hindi","English.Hindi.Kannada","English","English"};
    String certificate[] = {"U/A","U/A","U/A","U/A"};
    String rating[] = {"56%","82%","98%","95%"};
    String url[]= {"http://cdn.mamamia.com.au/wp/wp-content/uploads/2016/12/26151006/inception2.jpg"
    ,"https://assets1.ignimgs.com/2018/07/18/darkknight-1531878932530_1280w.jpg"
    ,"https://vignette.wikia.nocookie.net/interstellarfilm/images/9/9b/Black_hole.png/revision/latest?cb=20150322005003"
    ,"https://i.ytimg.com/vi/VlhJm_KkKEg/maxresdefault.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy);

        String details[] ={this.getString(R.string.inception),
                this.getString(R.string.darkKnight),
                this.getString(R.string.interText),
                this.getString(R.string.gravity)};

        Log.i("sand",this.getString(R.string.interText));
        for (int i = 0; i < name.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(name[i]);
            movie.setLanguage(language[i]);
            movie.setUrls(url[i]);
            movie.setRating(rating[i]);
            movie.setDetails(details[i]);
            movie.setCertificate(certificate[i]);
            movies.add(movie);
        }

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerAddapter addapter = new RecyclerAddapter(movies);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addapter);

    }
}
