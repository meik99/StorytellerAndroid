package storyteller.rynkbit.com.storytellerandroid.net.preview;

import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;

public interface PreviewLoaderListener {
    void onResponseFinished(List<Story> stories);
    void onResponseError(Throwable error);
}
