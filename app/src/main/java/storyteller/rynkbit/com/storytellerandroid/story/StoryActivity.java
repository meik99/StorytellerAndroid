package storyteller.rynkbit.com.storytellerandroid.story;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import storyteller.rynkbit.com.storytellerandroid.R;

public class StoryActivity extends AppCompatActivity {
    public static final String EXTRA_STORY_ID = "story_id";

    private StoryController mStoryController = new StoryController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        mStoryController.setupMessageList();
    }
}
