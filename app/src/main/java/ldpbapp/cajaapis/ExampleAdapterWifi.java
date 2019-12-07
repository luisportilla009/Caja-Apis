package ldpbapp.cajaapis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapterWifi extends RecyclerView.Adapter<ExampleAdapterWifi.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItemWifi> mExampleList;

    public ExampleAdapterWifi(Context context, ArrayList<ExampleItemWifi> exampleList){
        mContext =context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleAdapterWifi.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item_wifi,parent,false);
        return new ExampleAdapterWifi.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapterWifi.ExampleViewHolder holder, int position) {
        ExampleItemWifi currentItem = mExampleList.get(position);


        String direcciones = currentItem.getDireccion();
        String puntos = currentItem.getPunto();

        holder.mTextViewDireccion.setText("Dirección: " + direcciones);
        holder.mTextViewPunto.setText("Punto Wifi: " + puntos);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public  class ExampleViewHolder extends RecyclerView.ViewHolder{


        public TextView mTextViewDireccion;
        public  TextView mTextViewPunto;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewDireccion = itemView.findViewById(R.id.text_view_direccion_wifi);
            mTextViewPunto = itemView.findViewById(R.id.text_view_punto_wifi);
        }
    }
}
