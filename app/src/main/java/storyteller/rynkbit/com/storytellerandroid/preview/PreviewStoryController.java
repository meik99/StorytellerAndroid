package storyteller.rynkbit.com.storytellerandroid.preview;

import android.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import storyteller.rynkbit.com.storytellerandroid.R;
import storyteller.rynkbit.com.storytellerandroid.net.preview.PreviewLoader;

public class PreviewStoryController {
    public static final int ERROR_LOADING_PREVIEWS = -1;

    private final PreviewActivity mActivity;

    public PreviewStoryController(PreviewActivity activity){
        mActivity = activity;
    }

    public RecyclerView setupPreviewStoryList(){
        RecyclerView listPreviewStories = mActivity.findViewById(R.id.listPreviewStories);
        PreviewLoader previewLoader = new PreviewLoader(mActivity);
        PreviewStoryAdapter listPreviewAdapter = new PreviewStoryAdapter(this);


        listPreviewStories.setLayoutManager(new LinearLayoutManager(
                mActivity, LinearLayoutManager.VERTICAL, false));
        listPreviewStories.setAdapter(listPreviewAdapter);

        previewLoader.addPreviewLoaderListener(listPreviewAdapter);
        previewLoader.execute();

        return listPreviewStories;
    }

    public void showError(int previewErrorCode) {
        if(previewErrorCode == ERROR_LOADING_PREVIEWS){
            AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(mActivity);

            errorDialogBuilder
                    .setTitle(R.string.ERROR_LOADING_PREVIEWS_TITLE)
                    .setMessage(R.string.ERROR_LOADING_PREVIEWS_MESSAGE)
                    .create()
                    .show();
        }
    }

    public void openStory(String storyId) {

    }
}
