package ldpbapp.cajaapis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapterFlower extends RecyclerView.Adapter<ExampleAdapterFlower.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItemFlower> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public ExampleAdapterFlower(Context context, ArrayList<ExampleItemFlower> exampleList){
        mContext =context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item_flower,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItemFlower currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        int likeCount = currentItem.getLikeCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: " + likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public  class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public  TextView mTextViewLikes;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_flower);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator_flower);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes_flower);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
