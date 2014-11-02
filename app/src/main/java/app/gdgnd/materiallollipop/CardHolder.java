package app.gdgnd.materiallollipop;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Raveesh on 02/11/14.
 */
public class CardHolder extends RecyclerView.ViewHolder {
    NetworkImageView image;

    public CardHolder(View itemView) {
        super(itemView);
    }

    public void setImage(Image img){
        image.setImageUrl(img.POSTER_URL, Constants.LOADER);
    }
}
