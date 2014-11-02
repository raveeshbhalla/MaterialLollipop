package app.gdgnd.materiallollipop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Raveesh on 02/11/14.
 */
public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    private Context mContext;
    private List<Image> mImages;

    public CardAdapter(Context context, List<Image> images){
        mContext = context;
        mImages = images;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.card,viewGroup,false);
        CardHolder holder = new CardHolder(view);
        holder.image = (NetworkImageView)view.findViewById(R.id.image);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardHolder cardHolder, int i) {
        cardHolder.setImage(mImages.get(i));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
