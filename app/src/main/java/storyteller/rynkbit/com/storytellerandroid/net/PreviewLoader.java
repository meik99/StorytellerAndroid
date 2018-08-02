package storyteller.rynkbit.com.storytellerandroid.net;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;

public class PreviewLoader extends AsyncTask<Void, Void, Void> {
    private static RequestQueue mRequestQueue;

    private final String mBaseUrl;
    private final String mPreviewPath;
    private final String mNameStoryId;
    private final String mNameStoryTitle;
    private final String mNameStoryDescription;

    private final List<PreviewLoaderListener> listeners = new ArrayList<>();

    public PreviewLoader(Context context) {
        mBaseUrl = context.getString(R.string.BASE_URL);
        mPreviewPath = context.getString(R.string.PREVIEW_PATH);
        mNameStoryId = context.getString(R.string.NAME_STORY_ID);
        mNameStoryTitle = context.getString(R.string.NAME_STORY_TITLE);
        mNameStoryDescription = context.getString(R.string.NAME_STORY_DESCRIPTION);

        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JsonArrayRequest previewStoryRequest = new JsonArrayRequest(
                Request.Method.GET,
                mBaseUrl + mPreviewPath,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Story> previewStories = new LinkedList<>();
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonStory = response.getJSONObject(i);

                                previewStories.add(
                                        parseStoryFromPreviewObject(jsonStory));
                            }

                            executeListener(previewStories);
                        } catch (JSONException e) {
                            executeListenerWithError(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        executeListenerWithError(error);
                    }
                });

        mRequestQueue.add(previewStoryRequest);

        return null;
    }

    private Story parseStoryFromPreviewObject(JSONObject jsonStory) throws JSONException {
        Story story = new Story();

        story.setId(
                jsonStory.getString(
                        mNameStoryId)
        );
        story.setTitle(
                jsonStory.getString(
                        mNameStoryTitle)
        );
        story.setDescription(
                jsonStory.getString(
                        mNameStoryDescription)
        );

        return story;
    }

    public void addPreviewLoaderListener(PreviewLoaderListener listener){
        if(listener != null){
            listeners.add(listener);
        }
    }

    public void removePreviewLoaderListener(PreviewLoaderListener listener){
        if(listener != null){
            listeners.remove(listener);
        }
    }

    public void executeListener(List<Story> result){
        for (PreviewLoaderListener listener :
                listeners) {
            listener.onResponseFinished(result);
        }
    }

    public void executeListenerWithError(Throwable error){
        for (PreviewLoaderListener listener :
                listeners){
            listener.onResponseError(error);
        }
    }
}
