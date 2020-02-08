package ldpbapp.cajaapis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


import static ldpbapp.cajaapis.WifiActivity.EXTRA_COORDENADAS;

public class DetailWifiActivity extends AppCompatActivity {

    public Double latitud = 0.0;
    public Double longitud = 0.0;

    private MapView myOpenMapView;
    private MapController myMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wifi);

        //final String url ="https://www.datos.gov.co/resource/pkga-gxrz.json";

        Intent intent = getIntent();
        String coordenadas = intent.getStringExtra(EXTRA_COORDENADAS);
        try {
            JSONObject jsonCoordenadas = new JSONObject(coordenadas);
            latitud = jsonCoordenadas.getDouble("latitude");
            longitud = jsonCoordenadas.getDouble("longitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //GeoPoint punto = new GeoPoint(1.2,-77.267);

        myOpenMapView = findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(13);

        myOpenMapView.setMultiTouchControls(true);

        final MyLocationNewOverlay overlay = new MyLocationNewOverlay(myOpenMapView);
        //overlay.enableFollowLocation();
        overlay.enableMyLocation();
        myOpenMapView.getOverlayManager().add(overlay);

        GeoPoint startPoint = new GeoPoint(latitud, longitud);
        Marker startMarker = new Marker(myOpenMapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        myOpenMapView.getOverlays().add(startMarker);
        myMapController.setCenter(startPoint);




    }
}
