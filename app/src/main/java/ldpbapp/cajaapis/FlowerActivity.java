package ldpbapp.cajaapis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class FlowerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapterFlower mExampleAdapterFlower;
    private ArrayList<ExampleItemFlower> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);

        mRecyclerView = findViewById(R.id.recycler_view_flower);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        Random rand = new Random();
        int n = rand.nextInt(8);
        String color = "";
        if (n == 0) color="tulip";
        if (n == 1) color="rose";
        if (n == 2) color="bluebell";
        if (n == 3) color="sunflower";
        if (n == 4) color="lotus";
        if (n == 5) color="geranium";
        if (n == 6) color="orchid";
        if (n == 7) color="snowdrop";
        String url = "https://pixabay.com/api/?key=14540949-d2a1852cc523bcda750011057&q="+color+"+&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i= 0; i < jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likeCount = hit.getInt("likes");
                                mExampleList.add(new ExampleItemFlower(imageUrl,creatorName,likeCount));

                            }

                            mExampleAdapterFlower = new ExampleAdapterFlower(FlowerActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapterFlower);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
