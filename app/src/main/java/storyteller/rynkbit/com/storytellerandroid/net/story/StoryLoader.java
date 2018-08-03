package storyteller.rynkbit.com.storytellerandroid.net.story;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.net.RESTLoader;

public class StoryLoader extends RESTLoader {
    private final List<StoryLoaderListener> listeners = new ArrayList<>();

    private final String mFullStoryPath;

    public StoryLoader(Context context){
        super(context);

        mFullStoryPath = context.getString(R.string.FULL_STORY_PATH);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
