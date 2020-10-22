package yaohua.com.Proudcts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import yaohua.com.Adapters.RecyclerAdapter1;
import yaohua.com.Homepage;
import yaohua.com.Hot_sells;
import yaohua.com.Images.Image1;
import yaohua.com.Mine;
import yaohua.com.R;

public class Threading_machine extends AppCompatActivity {

    private static final String TAG = "Threading_machine";

    BottomNavigationView bottomNavigationView;

    //Firebase//
    private DatabaseReference reference1;
    private StorageReference mStorageRef1;

    //Widgets//
    private Context mcontext1 = Threading_machine.this;
    private Context ycontext1 = Threading_machine.this;
    private Context bcontext1 = Threading_machine.this;
    private RecyclerView recyclerView1;

    //Vars//
    private ArrayList<Image1> image1List;
    private RecyclerAdapter1 recyclerAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading_machine);

        Log.d(TAG, "onCreated started");

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView6);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setHasFixedSize(true);

        reference1 = FirebaseDatabase.getInstance().getReference();

        mStorageRef1 = FirebaseStorage.getInstance().getReference();

        image1List = new ArrayList<>();

        init();

        //bottom navigation//
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNAV9);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
        //bottom navigation//

        //hide status bar//
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //hide status bar//

    }

    private void init() {

        clearAll();

        Query query = reference1.child("Threading machine");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Image1 image1 = new Image1();

                    image1.setUrl(snapshot.child("url").getValue().toString());
                    image1.setTitle(snapshot.child("title").getValue().toString());
                    image1.setPrice(snapshot.child("price").getValue().toString());
                    image1.setDetail(snapshot.child("detail").getValue().toString());

                    image1List.add(image1);

                }

                recyclerAdapter1 = new RecyclerAdapter1(mcontext1, ycontext1, bcontext1, image1List);
                recyclerView1.setAdapter(recyclerAdapter1);
                recyclerAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearAll() {

        if (image1List != null) {

            image1List.clear();

            if (recyclerAdapter1 != null) {

                recyclerAdapter1.notifyDataSetChanged();
            }

        }

        image1List = new ArrayList<>();
    }

    //bottom navigation//
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.Homepage:
                            startActivity(new Intent(Threading_machine.this, Homepage.class));
                            break;

                        case R.id.Hot_sells:
                            startActivity(new Intent(Threading_machine.this, Hot_sells.class));
                            break;

                        case R.id.PersonalPage:
                            startActivity(new Intent(Threading_machine.this, Mine.class));
                            break;
                    }

                    return true;
                }
            };//bottom navigation//

}