package storyteller.rynkbit.com.storytellerandroid.story;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.net.story.StoryLoader;

class StoryController {
    private final StoryActivity mStoryActivity;
    private final StoryModel mStoryModel;
    private final StoryPlayer mStoryPlayer;

    public StoryController(StoryActivity storyActivity) {
        mStoryModel = new StoryModel();
        mStoryPlayer = new StoryPlayer();
        mStoryActivity = storyActivity;

        getStoryIdFromIntent();
        setupMessageList();
    }

    private void getStoryIdFromIntent() {
        Intent storyActivityIntent = mStoryActivity.getIntent();

        if(storyActivityIntent.hasExtra(StoryActivity.EXTRA_STORY_ID)){
            mStoryModel.setStoryId(
                    storyActivityIntent.getStringExtra(StoryActivity.EXTRA_STORY_ID)
            );

            retrieveStory(mStoryModel.getStoryId());
        }
    }

    private void retrieveStory(String storyId) {
        if(storyId != null && storyId.isEmpty() == false){
            StoryLoader loader = new StoryLoader(mStoryActivity, mStoryModel.getStoryId(), null);
            loader.addStoryLoaderListener(mStoryPlayer);
            loader.execute();
        }
    }

    public void setupMessageList() {
        RecyclerView listStoryMessages = mStoryActivity.findViewById(R.id.listStoryMessages);

        listStoryMessages.setLayoutManager(
                new LinearLayoutManager(
                        mStoryActivity, LinearLayoutManager.VERTICAL, false));
        listStoryMessages.setAdapter(new StoryMessageAdapter(mStoryPlayer));
    }
}
