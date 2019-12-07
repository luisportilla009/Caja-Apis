package ldpbapp.cajaapis;

public class ExampleItemWifi {

    private String mDireccion;
    private String mPunto;

    public ExampleItemWifi(String direccion, String punto) {

        mDireccion = direccion;
        mPunto = punto;
    }


    public  String getDireccion(){
        return mDireccion;
    }

    public String getPunto(){
        return mPunto;
    }
}

