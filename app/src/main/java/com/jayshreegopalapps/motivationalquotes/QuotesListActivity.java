package com.jayshreegopalapps.motivationalquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuotesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<QuotesModel> arrayList = new ArrayList<>();
    QuotesAdapter adapter;
    private boolean isLoading = false;
    int no_of_request_queued = 0;
    int MAX_REQUEST = 5;
    int offset = 0;
    String category;
    boolean isDataAvailableOnDatabase = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);
        initViews();
        extractBundle();
        refreshPage();

    }

    private void extractBundle() {
        if (getIntent().getExtras()!=null) {
            category = getIntent().getExtras().getString("Category");
        }
    }

    private void refreshPage() {
        loadNextNImages(20);
        adapter = new QuotesAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(isDataAvailableOnDatabase) {
                    GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                    if (!isLoading) {
                        if (manager != null && (manager.findLastCompletelyVisibleItemPosition() == (arrayList.size() - 1))) {

                            loadNextNImages(5);
                        } else {
                            System.out.println("last item index " + manager.findLastCompletelyVisibleItemPosition());
                        }
                    } else {
                        System.out.println("array list " + arrayList.size());
                    }
                }
            }
        });
    }

    private void loadNextNImages(int N) {
        System.out.println("Called");
        isLoading = true;
        DownloadImageTask task = new DownloadImageTask(N,new DownloadImageTask.updateArraylist() {
            @Override
            public void update(int rowCount, JSONArray array, int reqAmount) {
                for(int i = 0;i < rowCount;i++) {
                    QuotesModel model = new QuotesModel();
                    try {
                        JSONObject object = new JSONObject(array.getString(i));
                        model.quoteText = object.getString("q_text");
                        model.authorName = object.getString("a_name");
                        model.colorId = RandomUtils.getRandomColor();
                        arrayList.add(model);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(rowCount < reqAmount) {
                    isDataAvailableOnDatabase = false;
                }
                isLoading = false;
            }

            @Override
            public void errorFetching(String reason) {
                isDataAvailableOnDatabase = false;
                //todo check error reason
            }
        });
        task.execute("https://motivational-quotes-webservice.herokuapp.com/api/getQuoteFromCategory/" + offset + "/" + N + "/" + category);
        offset += N;
    }

    private void loadImages() {
        QuotesModel model = new QuotesModel();
        model.quoteText = "abcd";
//        for(int i =0;i < 100;i++) {
            arrayList.add(model);
//        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view_quotes);
    }
}
