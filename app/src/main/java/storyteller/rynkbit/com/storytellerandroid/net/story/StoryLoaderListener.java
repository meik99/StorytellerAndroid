package storyteller.rynkbit.com.storytellerandroid.net.story;

import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;

public interface StoryLoaderListener {
    void onResponseFinished(Story story);
    void onResponseError(Throwable error);
}
