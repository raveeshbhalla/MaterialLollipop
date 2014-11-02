package app.gdgnd.materiallollipop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private final static String API = "https://raw.githubusercontent.com/raveeshbhalla/MaterialLollipop/1e0fe26b5603d86f992d9051736d63c9c84234a5/dump.json";
    List<Image> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(getResources().getInteger(R.integer.numOfColumns), StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mImages = new ArrayList<Image>();

        Constants.QUEUE = Volley.newRequestQueue(this);
        Constants.LOADER = new ImageLoader(Constants.QUEUE, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) { }

            @Override
            public Bitmap getBitmap(String key) {
                return null;
            }
        });

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(MainActivity.this.getLocalClassName(), "API response:" + response.toString());
                try {
                    JSONArray items = response.getJSONArray("items");
                    for (int i=0;i<items.length();i++){
                        Image image = new Image(items.getJSONObject(i));
                        mImages.add(image);
                    }
                    CardAdapter adapter = new CardAdapter(MainActivity.this,mImages);
                    recyclerView.setAdapter(adapter);
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
        Constants.QUEUE.add(request);
    }
}
