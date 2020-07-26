package com.jayshreegopalapps.motivationalquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;

    ArrayList<CategoriesModel> arrayList = new ArrayList<>();
    CategoriesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        refreshPage();
    }

    private void refreshPage() {
        loadCategories();
        adapter = new CategoriesAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setHasFixedSize(false);
    }

    private void loadCategories() {

        String[] categoryName ={"AlONE", "Angry","Anniversary","Awesome","Attitude",
                                "Beard","Beautiful","Bike","Birthday","Breakup","Brother","Busy",
                                "Cheat","Childhood","Cool","College","Cute",
                                "Emotional",
                                "Family","Food","Funny","Forgiveness",
                                "God","Gym",
                                "Happiness","Home",
                                "Inspirational","Insult",
                                "Jealous",
                                "Life",
                                "Motivational","Monday Motivation",
                                "Pain","Party","Pets",
                                "Quotes",
                                "Sad","Selfish","Sister","Sorry","Success","Sunday",
                                "Technology","Thank you","Tired","Trust",
                                "Whatsapp","Work","Wise"};

        int[] colors = {R.color.colorRed1,R.color.colorRed2,R.color.colorRed3,R.color.colorRed4,R.color.colorRed5,R.color.colorRed6,R.color.colorRed8,R.color.colorBlue16,R.color.colorBlue15,R.color.colorBlue14,R.color.colorBlue13,R.color.colorBlue12,R.color.colorBlue11,R.color.colorBlue10,R.color.colorBlue9,R.color.colorBlue8,R.color.colorBlue7,R.color.colorBlue6,R.color.colorBlue5,R.color.colorBlue4,R.color.colorBlue3,R.color.colorBlue2,R.color.colorBlue1,R.color.colorBlue17,R.color.colorBlue18,R.color.colorGreen7,R.color.colorGreen6,R.color.colorGreen5,R.color.colorGreen4,R.color.colorGreen2,R.color.colorGreen1};
        int j = 0;
        for (int i=0;i<categoryName.length;i++){
            Toast.makeText(this, ""+categoryName.length, Toast.LENGTH_SHORT).show();
            CategoriesModel model = new CategoriesModel();
            model.categroyName=categoryName[i];
            if(j == (colors.length)) {
                j = 0;
            }
            model.colorId = colors[j];
            j++;
            arrayList.add(model);
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view_categories);
        fab = findViewById(R.id.fab_upload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(intent);
            }
        });
    }
}
