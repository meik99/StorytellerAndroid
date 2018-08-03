package storyteller.rynkbit.com.storytellerandroid.net.story;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.RESTLoader;

public class StoryLoader extends RESTLoader {
    private final List<StoryLoaderListener> mStoryLoaderListener = new ArrayList<>();

    private final String mFullStoryPath;
    private final String mStoryId;
    private final String mAccessTokenValue;

    public StoryLoader(Context context, String storyId, @Nullable String accessTokenValue){
        super(context);

        mFullStoryPath = context.getString(R.string.FULL_STORY_PATH);
        mStoryId = storyId;
        mAccessTokenValue = accessTokenValue;
    }

    public StoryLoader(Context context, String storyId){
        this(context, storyId, null);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put(getNameStoryId(), mStoryId);
            requestParams.put(getNameAccessTokenValue(), null);

            JsonObjectRequest fullStoryRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            getBaseUrl() + mFullStoryPath,
                            requestParams,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Story fullStory = parseStoryFromStoryObject(response);
                                        callStoryLoaderListener(fullStory);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        callStoryLoaderListenerWithError(e);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    callStoryLoaderListenerWithError(error);
                                }
                            }
                    );

            getRequestQueue().add(fullStoryRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            callStoryLoaderListenerWithError(e);
        }

        return null;
    }

    private Story parseStoryFromStoryObject(JSONObject response) throws JSONException {
        Story result = new Story();
        List<List<String>> messages = new LinkedList<>();
        JSONArray messageArray;
        JSONArray message;
        
        result.setId(response.getString(getNameStoryId()));
        result.setTitle(response.getString(getNameStoryTitle()));
        result.setDescription(response.getString(getNameStoryDescription()));
        
        messageArray = response.getJSONArray(getNameStoryMessages());

        for (int i = 0; i < messageArray.length(); i++) {
            message = messageArray.getJSONArray(i);
            messages.add(new LinkedList<String>());

            for (int j = 0; j < message.length(); j++) {
                messages.get(i).add(message.getString(j));
            }
        }

        result.setMessages(messages);
        return result;
    }
    
    public void addStoryLoaderListener(StoryLoaderListener listener){
        if(listener != null){
            mStoryLoaderListener.add(listener);
        }
    }
    
    public void removeStoryLoaderListener(StoryLoaderListener listener){
        if (listener != null) {
            mStoryLoaderListener.remove(listener);
        }
    }

    public void callStoryLoaderListener(Story story){
        for (StoryLoaderListener listener :
                mStoryLoaderListener) {
            listener.onResponseFinished(story);
        }
    }

    public void callStoryLoaderListenerWithError(Throwable error){
        for (StoryLoaderListener listener :
                mStoryLoaderListener) {
            listener.onResponseError(error);
        }
    }
}
