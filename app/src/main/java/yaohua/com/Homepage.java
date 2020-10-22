package yaohua.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import yaohua.com.Proudcts.Bench_drill;
import yaohua.com.Proudcts.Bender;
import yaohua.com.Proudcts.Dredge_machine;
import yaohua.com.Proudcts.Electric_tool;
import yaohua.com.Proudcts.Garden_tools;
import yaohua.com.Proudcts.Ladder;
import yaohua.com.Proudcts.Lifting_equipment;
import yaohua.com.Proudcts.Threading_machine;
import yaohua.com.Proudcts.Welding_equipment;


public class Homepage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //category list//
    private ListView cate_list;
    private  String cate_Title [] = {"Welding equipment", "Electric equipment",
            "Lifting equipments", "Garden Tools",
            "Bench drill", "Threading machine", "Bender", "Dredge machine", "Ladder"};
    private String cate_description [] = {"welder, welding torch, plastic fusion splicer, welding rod and wire",
            "Cutting machine, electric hammer drill, hand electric drill, etc.",
            "Hand/electric hoist, jack, truck", "Electric chain saw, gasoline chain saw, branch shears, etc.",
            "Bench drill, Magnetic drill, tapping machine", "Threading machine, twist mill",
            "Bender", "Dredging machine, gambler, mediator, etc.", "Ladder"};
    private int images [] = {R.drawable.welding_equipment, R.drawable.aluminum_sawing_machine,
            R.drawable.lifting_equipment, R.drawable.garden_tools,
            R.drawable.bench_drill, R.drawable.threading_machine, R.drawable.bender,
            R.drawable.dredge_machine, R.drawable.ladder};
    //category list//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //bottom navigation//
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNAV1);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
        //bottom navigation//

        //hide status bar//
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //hide status bar//

        cate_list = (ListView)findViewById(R.id.categoryList);
        //set list adapter//
        MyAdapter adapter = new MyAdapter(this, cate_Title, cate_description, images);
        cate_list.setAdapter(adapter);
        //set list adapter//
        //listview item click//
        cate_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(Homepage.this, Welding_equipment.class));
                }
                if (position == 1){
                    startActivity(new Intent(Homepage.this, Electric_tool.class));
                }
                if (position == 2){
                    startActivity(new Intent(Homepage.this, Lifting_equipment.class));
                }
                if (position == 3){
                    startActivity(new Intent(Homepage.this, Garden_tools.class));
                }
                if (position == 4){
                    startActivity(new Intent(Homepage.this, Bench_drill.class));
                }
                if (position == 5){
                    startActivity(new Intent(Homepage.this, Threading_machine.class));
                }
                if (position == 6){
                    startActivity(new Intent(Homepage.this, Bender.class));
                }
                if (position == 7){
                    startActivity(new Intent(Homepage.this, Dredge_machine.class));
                }
                if (position == 8){
                    startActivity(new Intent(Homepage.this, Ladder.class));
                }
            }
        });//listview item click//

}

    //bottom navigation//
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.Homepage:
                            Toast.makeText(Homepage.this, "Homepage", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.Hot_sells:
                            startActivity(new Intent(Homepage.this, Hot_sells.class));
                            break;

                        case R.id.PersonalPage:
                            startActivity(new Intent(Homepage.this, Mine.class));
                            break;
                    }
                    return true;
                }
            };//bottom navigation//

    //create adapter class//
    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rtitle[];
        String rdescrtiption[];
        int rimags[];

        MyAdapter (Context c, String title[], String description[], int imag[]) {
            super(c, R.layout.row, R.id.mtitle, title);
            this.context = c;
            this.rtitle = title;
            this.rdescrtiption = description;
            this.rimags = imag;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.cate_img);
            TextView myTitle = row.findViewById(R.id.mtitle);
            TextView myDescription = row.findViewById(R.id.stitle);

            //set resources on view//
            images.setImageResource(rimags[position]);
            myTitle.setText(rtitle[position]);
            myDescription.setText(rdescrtiption[position]);

            return row;
        }
    }

}