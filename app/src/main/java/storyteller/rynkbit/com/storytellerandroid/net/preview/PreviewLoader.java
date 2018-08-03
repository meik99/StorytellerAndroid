package storyteller.rynkbit.com.storytellerandroid.net.preview;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.RESTLoader;

public class PreviewLoader extends RESTLoader {

    private final String mPreviewPath;

    private final List<PreviewLoaderListener> mListeners = new ArrayList<>();

    public PreviewLoader(Context context) {
        super(context);
        mPreviewPath = context.getString(R.string.PREVIEW_PATH);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JsonArrayRequest previewStoryRequest = new JsonArrayRequest(
                Request.Method.GET,
                getBaseUrl() + mPreviewPath,
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

        getRequestQueue().add(previewStoryRequest);

        return null;
    }

    private Story parseStoryFromPreviewObject(JSONObject jsonStory) throws JSONException {
        Story story = new Story();

        story.setId(
                jsonStory.getString(
                        getNameStoryId())
        );
        story.setTitle(
                jsonStory.getString(
                        getNameStoryTitle())
        );
        story.setDescription(
                jsonStory.getString(
                        getNameStoryDescription())
        );

        return story;
    }

    public void addPreviewLoaderListener(PreviewLoaderListener listener){
        if(listener != null){
            mListeners.add(listener);
        }
    }

    public void removePreviewLoaderListener(PreviewLoaderListener listener){
        if(listener != null){
            mListeners.remove(listener);
        }
    }

    public void executeListener(List<Story> result){
        for (PreviewLoaderListener listener :
                mListeners) {
            listener.onResponseFinished(result);
        }
    }

    public void executeListenerWithError(Throwable error){
        for (PreviewLoaderListener listener :
                mListeners){
            listener.onResponseError(error);
        }
    }
}
