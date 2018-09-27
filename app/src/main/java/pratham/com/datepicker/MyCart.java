package pratham.com.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCart extends AppCompatActivity {

    List<Flip_Get_Set> get_sets = new ArrayList<>();
    private String productName[] = {"Asus Zenfone Max Pro M1 (Blue, 64 GB)", "Redmi Note 5 Pro (Black, 64 GB)", "Samsung Galaxy On8 (Black, 64 GB)",
            "Asus Zenfone Max Pro M1 (Blue, 32 GB)", "Honor 7A (Black, 32 GB)", "Asus Zenfone Max Pro M1 (Black, 64 GB) "};
    private String rating[] = {"4.3", "2.4", "3.6", "3.8", "3.2", "4.6"};
    private String votes[] = {"57683", "83805", "67557", "96007", "162108", "148117"};
    private int price[] = {14999, 15999, 16990, 10999, 8999, 14999};
    private int id[] = {1, 2, 3, 4, 5, 6};
    private int off[] = {10, 15, 5, 26, 45, 32};
    private String imgsUrl[] = {"https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/8/x/n/asus-zenfone-max-pro-m1-zb601kl-4d102in-original-imaf7hajxvhhkckh.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jdhp47k0/mobile/e/h/e/redmi-note-5-pro-na-original-imaf2ashnnbxxks5.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/q/c/e/samsung-galaxy-on8-sm-j810gzkfins-original-imaf7h6xgeug6f9h.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/8/x/n/asus-zenfone-max-pro-m1-zb601kl-4d102in-original-imaf7hajxvhhkckh.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jhgl5e80/mobile/3/g/f/honor-7a-v100r001-aum-al20-original-imaf5h3wfrpsazhd.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jhgl5e80/mobile/6/6/7/honor-7a-v100r001-aum-al20-original-imaf5h4anxwhmqb4.jpeg?q=70"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        Bundle  b = getIntent().getExtras();
        ArrayList<Integer> array = b.getIntegerArrayList("cartArray");

        for (int i = 0; i < price.length; i++) {
            Flip_Get_Set get_set = new Flip_Get_Set();
            get_set.setImgUrl(imgsUrl[i]);
            get_set.setPrice(price[i]);
            get_set.setName(productName[i]);
            get_set.setRating(rating[i]);
            get_set.setVotes(votes[i]);
            get_set.setOff(off[i]);
            get_set.setId(id[i]);
            get_sets.add(get_set);
        }

        RecyclerView recyclerView = findViewById(R.id.fliprecy2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FlipAdapter adapter = new FlipAdapter(get_sets,array, null);
        recyclerView.setAdapter(adapter);
    }
}
