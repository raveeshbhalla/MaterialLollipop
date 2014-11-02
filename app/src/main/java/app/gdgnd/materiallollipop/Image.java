package app.gdgnd.materiallollipop;

import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Raveesh on 02/11/14.
 */
public class Image {
    public String POSTER_NAME;
    public String POSTER_URL;
    public String POSTER_IMAGE;
    public String CONTENT_TEXT;
    public String CONTENT_IMAGE;
    public String CONTENT_URL;

    public Image(JSONObject object) throws JSONException {
        CONTENT_URL = object.getString("url");
        JSONObject actor = object.getJSONObject("actor");
        POSTER_NAME = actor.getString("displayName");
        POSTER_URL = actor.getString("url");
        JSONObject img = actor.getJSONObject("image");
        POSTER_IMAGE = img.getString("url");
        JSONObject content = object.getJSONObject("object");
        CONTENT_TEXT = Html.fromHtml(content.getString("content")).toString();
        JSONArray attachments = content.getJSONArray("attachments");
        JSONObject fullImage = attachments.getJSONObject(0).getJSONObject("fullImage");
        CONTENT_IMAGE = fullImage.getString("url");
    }
}
