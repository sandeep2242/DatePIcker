package pratham.com.datepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static pratham.com.datepicker.FlipAdapter.ids;

public class FlipKartActivity extends AppCompatActivity {

    List<Flip_Get_Set> get_sets = new ArrayList<>();
    private String productName[] = {"Asus Zenfone Max Pro M1 (Blue, 64 GB)", "Redmi Note 5 Pro (Black, 64 GB)", "Samsung Galaxy On8 (Black, 64 GB)",
            "Asus Zenfone Max Pro M1 (Blue, 32 GB)", "Honor 7A (Black, 32 GB)","", "Asus Zenfone Max Pro M1 (Black, 64 GB) "};
    private String rating[] = {"4.3", "2.4", "3.6", "3.8", "3.2","", "4.6"};
    private String votes[] = {"57683", "83805", "67557", "96007", "162108","", "148117"};
    private int price[] = {14999, 15999, 16990, 10999, 8999,0, 14999};
    private int id[] = {1, 2, 3, 4, 5, 0,6};
    private int off[] = {10, 15, 5, 26, 45,0, 32};
    private String imgsUrl[] = {"https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/8/x/n/asus-zenfone-max-pro-m1-zb601kl-4d102in-original-imaf7hajxvhhkckh.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jdhp47k0/mobile/e/h/e/redmi-note-5-pro-na-original-imaf2ashnnbxxks5.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/q/c/e/samsung-galaxy-on8-sm-j810gzkfins-original-imaf7h6xgeug6f9h.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jk1grrk0/mobile/8/x/n/asus-zenfone-max-pro-m1-zb601kl-4d102in-original-imaf7hajxvhhkckh.jpeg?q=70",
            "https://rukminim1.flixcart.com/image/312/312/jhgl5e80/mobile/3/g/f/honor-7a-v100r001-aum-al20-original-imaf5h3wfrpsazhd.jpeg?q=70","",
            "https://rukminim1.flixcart.com/image/312/312/jhgl5e80/mobile/6/6/7/honor-7a-v100r001-aum-al20-original-imaf5h4anxwhmqb4.jpeg?q=70"};
    private View v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_kart);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.title_text,null);
        v.findViewById(R.id.ttView);
        v2=findViewById(R.id.betw);
        getSupportActionBar().setCustomView(v);


        for (int i = 0; i < price.length; i++) {
            Flip_Get_Set get_set = new Flip_Get_Set();
            get_set.setImgUrl(imgsUrl[i]);
            get_set.setPrice(price[i]);
            get_set.setName(productName[i]);
            get_set.setRating(rating[i]);
            get_set.setVotes(votes[i]);
            get_set.setOff(off[i]);
            get_set.setId(id[i]);
            get_set.setHeart(false);
            get_sets.add(get_set);
        }

        RecyclerView recyclerView = findViewById(R.id.fliprecy);
        recyclerView.setNestedScrollingEnabled(false);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FlipAdapter adapter = new FlipAdapter(get_sets, null,v2);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState==RecyclerView.SCROLL_STATE_SETTLING){

                    int pos = layoutManager.findLastVisibleItemPosition();


                    if (pos>=5){
                        v2.setVisibility(View.GONE);  // want to hide view when position 5 has just ended while scrolling upwards

                    }else {
                        v2.setVisibility(View.VISIBLE);//// want to show view when position 5 has just ended while scrolling downwards
                    }

                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int offset = recyclerView.computeVerticalScrollOffset();
                int myCellHeight = recyclerView.getChildAt(0).getHeight();
                Log.i("sand",(offset )+"");
                Log.i("sand",( myCellHeight)+"");
                Log.i("sand",(offset % myCellHeight)+"");

                if (offset % myCellHeight == 0) {
                    final int position = offset / myCellHeight ;
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_flip, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_cart:
                Intent i = new Intent(FlipKartActivity.this, MyCart.class);
                i.putExtra("cartArray", ids);

                if (ids!=null&&ids.size()>0){
                    startActivity(i);
                }else {
                    Toast.makeText(FlipKartActivity.this,"No Items in your wishlist",Toast.LENGTH_SHORT).show();

                }


        }
        return super.onOptionsItemSelected(item);
    }
}
