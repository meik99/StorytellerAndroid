package storyteller.rynkbit.com.storytellerandroid.story;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import storyteller.rynkbit.com.storytellerandroid.R;

class StoryController {
    private final StoryActivity mStoryActivity;
    private final StoryModel mStoryModel;

    public StoryController(StoryActivity storyActivity) {
        mStoryModel = new StoryModel();
        mStoryActivity = storyActivity;

        getStoryIdFromIntent();
    }

    private void getStoryIdFromIntent() {
        Intent storyActivityIntent = mStoryActivity.getIntent();

        if(storyActivityIntent.hasExtra(StoryActivity.EXTRA_STORY_ID)){
            mStoryModel.setStoryId(
                    storyActivityIntent.getStringExtra(StoryActivity.EXTRA_STORY_ID)
            );
        }
    }

    public void setupMessageList() {
        RecyclerView listStoryMessages = mStoryActivity.findViewById(R.id.listStoryMessages);

        listStoryMessages.setLayoutManager(
                new LinearLayoutManager(
                        mStoryActivity, LinearLayoutManager.VERTICAL, false));
    }
}
