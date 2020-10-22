package yaohua.com;

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
import android.widget.Toast;

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
import yaohua.com.Adapters.RecyclerAdapter2;
import yaohua.com.Images.Image1;
import yaohua.com.Images.Image2;
import yaohua.com.Proudcts.Bench_drill;

public class Hot_sells extends AppCompatActivity {

    private static final String TAG = "Hot_sells";

    BottomNavigationView bottomNavigationView;

    //Firebase//
    private DatabaseReference reference2;
    private StorageReference mStorageRef2;

    //Widgets//
    private Context scontext = Hot_sells.this;
    private Context scontext2 = Hot_sells.this;
    private Context scontext3 = Hot_sells.this;
    private RecyclerView recyclerView;

    //Vars//
    private ArrayList<Image2> image2List;
    private RecyclerAdapter2 recyclerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_sells);

        Log.d(TAG, "onCreate Started");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView11);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        reference2 = FirebaseDatabase.getInstance().getReference();

        mStorageRef2 = FirebaseStorage.getInstance().getReference();

        image2List = new ArrayList<>();

        init();

        //bottom navigation//
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNAV11);
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

        Query query = reference2.child("Hot sells");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Image2 image2 = new Image2();

                    image2.setTitle2(snapshot.child("title").getValue().toString());
                    image2.setDetail2(snapshot.child("detail").getValue().toString());
                    image2.setUrl2(snapshot.child("url").getValue().toString());
                    image2.setSells(snapshot.child("sells").getValue().toString());

                    image2List.add(image2);
                }

                recyclerAdapter2 = new RecyclerAdapter2(scontext, scontext2, scontext3,image2List);
                recyclerView.setAdapter(recyclerAdapter2);
                recyclerAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearAll() {

        if (image2List != null) {

            image2List.clear();

            if (recyclerAdapter2 != null) {

                recyclerAdapter2.notifyDataSetChanged();
            }

        }

        image2List = new ArrayList<>();
    }

    //bottom navigation//
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.Homepage:
                            startActivity(new Intent(Hot_sells.this, Homepage.class));
                            break;

                        case R.id.Hot_sells:
                            Toast.makeText(Hot_sells.this, "Hot sells", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.PersonalPage:
                            startActivity(new Intent(Hot_sells.this, Mine.class));
                            break;
                    }

                    return true;
                }
            };//bottom navigation//
}