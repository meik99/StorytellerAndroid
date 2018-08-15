package storyteller.rynkbit.com.storytellerandroid.story;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class StoryMessageAdapter extends RecyclerView.Adapter<StoryMessageViewHolder>{
    private final StoryPlayer mStoryPlayer;

    public StoryMessageAdapter(StoryPlayer storyPlayer) {
        mStoryPlayer = storyPlayer;
    }

    @NonNull
    @Override
    public StoryMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryMessageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mStoryPlayer.getCurrentIndex();
    }
}
