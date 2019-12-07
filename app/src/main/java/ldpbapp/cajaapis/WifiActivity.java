package ldpbapp.cajaapis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WifiActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapterWifi mExampleAdapterWifi;
    private ArrayList<ExampleItemWifi> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        mRecyclerView = findViewById(R.id.recycler_view_wifi);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {

        final String url ="https://www.datos.gov.co/resource/pkga-gxrz.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for (int i= 0; i < response.length(); i++){
                                JSONObject jsonObject = response.getJSONObject(i);

                                String direcciones = jsonObject.getString("direccion");

                                String puntos = jsonObject.getString("punto_wifi");
                                mExampleList.add(new ExampleItemWifi(direcciones,puntos));

                            }

                            mExampleAdapterWifi = new ExampleAdapterWifi(WifiActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapterWifi);

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
