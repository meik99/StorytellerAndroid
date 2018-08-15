package storyteller.rynkbit.com.storytellerandroid.story;

import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.story.StoryLoaderListener;

class StoryPlayer implements StoryLoaderListener {
    private int currentIndex = 0;

    @Override
    public void onResponseFinished(Story story) {

    }

    @Override
    public void onResponseError(Throwable error) {

    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
