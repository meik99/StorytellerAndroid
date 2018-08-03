package storyteller.rynkbit.com.storytellerandroid.net;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import storyteller.rynkbit.com.storytellerandroid.R;

public abstract class RESTLoader extends AsyncTask<Void, Void, Void> {
    private static RequestQueue mRequestQueue;

    private final String mBaseUrl;
    private final String mNameStoryId;
    private final String mNameStoryTitle;
    private final String mNameStoryDescription;
    private final String mNameStoryMessages;

    public RESTLoader(Context context){
        mBaseUrl = context.getString(R.string.BASE_URL);
        mNameStoryId = context.getString(R.string.NAME_STORY_ID);
        mNameStoryTitle = context.getString(R.string.NAME_STORY_TITLE);
        mNameStoryDescription = context.getString(R.string.NAME_STORY_DESCRIPTION);
        mNameStoryMessages = context.getString(R.string.NAME_STORY_MESSAGES);

        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public String getNameStoryId() {
        return mNameStoryId;
    }

    public String getNameStoryTitle() {
        return mNameStoryTitle;
    }

    public String getNameStoryDescription() {
        return mNameStoryDescription;
    }

    public String getmNameStoryMessages() {
        return mNameStoryMessages;
    }
}
