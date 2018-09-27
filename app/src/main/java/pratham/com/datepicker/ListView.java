package pratham.com.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListView extends AppCompatActivity {

    Integer num[] ={1,2,3,4,5,6,7,8,9,10};
    String s = "null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        android.widget.ListView listView = findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, num);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);


      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //diff between onItemClick and onItemSelected
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                s = String.valueOf(parent.getItemAtPosition(position));
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        menu.setHeaderTitle("Select the Action");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.call){
            Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.copy){
            Toast.makeText(getApplicationContext(),"Copy",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return super.onContextItemSelected(item);
    }
}
