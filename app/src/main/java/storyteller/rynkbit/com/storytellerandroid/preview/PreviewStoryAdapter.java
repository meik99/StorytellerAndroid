package storyteller.rynkbit.com.storytellerandroid.preview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.preview.PreviewLoaderListener;

public class PreviewStoryAdapter extends RecyclerView.Adapter<PreviewStoryViewHolder> implements PreviewLoaderListener {
    private final List<Story> mStories = new ArrayList<>();
    private final PreviewStoryController mPreviewStoryController;

    public PreviewStoryAdapter(PreviewStoryController previewStoryController) {
        this.mPreviewStoryController = previewStoryController;
    }


    @NonNull
    @Override
    public PreviewStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPreviewStory =
                LayoutInflater
                .from(
                        parent.getContext()
                )
                .inflate(
                        R.layout.item_preview_story,
                        parent,
                        false
                );

        return new PreviewStoryViewHolder(itemPreviewStory);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewStoryViewHolder holder, int position) {
        final Story story = mStories.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreviewStoryController.openStory(story.getId());
            }
        });

        holder
                .setTitle(story.getTitle())
                .setDescription(story.getDescription());
    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }

    @Override
    public void onResponseFinished(List<Story> stories) {
        mStories.clear();
        mStories.addAll(stories);
        notifyDataSetChanged();
    }

    @Override
    public void onResponseError(Throwable error) {
        mPreviewStoryController
            .showError(PreviewStoryController.ERROR_LOADING_PREVIEWS);
    }
}
