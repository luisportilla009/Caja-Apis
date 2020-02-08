package ldpbapp.cajaapis;

import org.json.JSONObject;

public class ExampleItemWifi {

    private String mDireccion;
    private String mPunto;
    private JSONObject mCoordenadas;

    public ExampleItemWifi(String direccion, String punto, JSONObject coordenadas) {

        mDireccion = direccion;
        mPunto = punto;
        mCoordenadas = coordenadas;

    }


    public  String getDireccion(){
        return mDireccion;
    }

    public String getPunto(){
        return mPunto;
    }

    public  JSONObject getCoordenadas(){
        return mCoordenadas;
    }
}

