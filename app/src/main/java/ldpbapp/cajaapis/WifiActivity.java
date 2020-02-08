package ldpbapp.cajaapis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WifiActivity extends AppCompatActivity implements ExampleAdapterWifi.OnItemClickListener {

    public static final String EXTRA_COORDENADAS = "coordenadas";

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

                                JSONObject Coordenadas = jsonObject.getJSONObject("coordenadas_puntos_wifi");

                                //Coordenadas.getString("latidute");

                                mExampleList.add(new ExampleItemWifi(direcciones,puntos,Coordenadas));

                            }

                            mExampleAdapterWifi = new ExampleAdapterWifi(WifiActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapterWifi);
                            mExampleAdapterWifi.setOnItemClickListener(WifiActivity.this);

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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailWifiActivity.class);
        ExampleItemWifi clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_COORDENADAS, clickedItem.getCoordenadas().toString());
        startActivity(detailIntent);
    }
}
