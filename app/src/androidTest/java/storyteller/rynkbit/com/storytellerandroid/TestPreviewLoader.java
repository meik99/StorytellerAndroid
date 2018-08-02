package storyteller.rynkbit.com.storytellerandroid;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.ExecutionException;

import storyteller.rynkbit.com.storytellerandroid.entitiy.Story;
import storyteller.rynkbit.com.storytellerandroid.net.PreviewLoader;
import storyteller.rynkbit.com.storytellerandroid.net.PreviewLoaderListener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class TestPreviewLoader {
    @Test
    public void testPreviewLoaderWithoutError() throws Throwable{
        final boolean[] endTest = {false};
        Context context = InstrumentationRegistry.getTargetContext();
        PreviewLoader loader = new PreviewLoader(context);

        loader.addPreviewLoaderListener(new PreviewLoaderListener() {
            @Override
            public void onResponseFinished(List<Story> stories) {
                assertNotNull(stories);
                endTest[0] = true;
            }

            @Override
            public void onResponseError(Throwable error) {
                error.printStackTrace();
                fail();
            }
        });

        loader.execute();

        try {
            loader.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        while(endTest[0] == false){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
