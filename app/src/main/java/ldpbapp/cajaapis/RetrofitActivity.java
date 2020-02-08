package ldpbapp.cajaapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class RetrofitActivity extends AppCompatActivity {

    private ArrayList<ExampleItemFlower> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void parseJSON() {

        String url = "https://dog.ceo/api/breeds/image/random";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String urlImage = response.getString("message");
                    ImageView imageView = findViewById(R.id.image);
                    Picasso.get().load(urlImage).fit().centerInside().into(imageView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }


}
