package storyteller.rynkbit.com.storytellerandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.preview.PreviewLoader;
import storyteller.rynkbit.com.storytellerandroid.net.preview.PreviewLoaderListener;
import storyteller.rynkbit.com.storytellerandroid.net.story.StoryLoader;
import storyteller.rynkbit.com.storytellerandroid.net.story.StoryLoaderListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TestStoryLoader {
    @Test
    public void getPreviewAndLoadStory(){
        final Context context = InstrumentationRegistry.getTargetContext();
        final boolean[] finished = new boolean[] { false };
        final PreviewLoader previewLoader = new PreviewLoader(context);

        previewLoader.addPreviewLoaderListener(
                new PreviewLoaderListener() {
                    @Override
                    public void onResponseFinished(List<Story> stories) {
                        assertTrue(stories.size() >= 1);

                        Story preview = stories.get(0);
                        final String storyId = preview.getId();

                        StoryLoader storyLoader = new StoryLoader(context, storyId);

                        storyLoader.addStoryLoaderListener(new StoryLoaderListener() {
                            @Override
                            public void onResponseFinished(Story story) {
                                assertEquals(storyId, story.getId());
                                assertNotNull(story.getMessages());
                                assertTrue(story.getMessages().size() > 0);
                                finished[0] = true;
                            }

                            @Override
                            public void onResponseError(Throwable error) {
                                error.printStackTrace();
                                fail();
                            }
                        });

                        storyLoader.execute();
                    }

                    @Override
                    public void onResponseError(Throwable error) {
                        error.printStackTrace();
                        fail();
                    }
                }
        );

        previewLoader.execute();

        while(finished[0] == false){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                fail();
            }
        }

    }

}
