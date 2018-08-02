package storyteller.rynkbit.com.storytellerandroid.preview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import storyteller.rynkbit.com.storytellerandroid.R;

public class PreviewActivity extends AppCompatActivity {

    private final PreviewStoryController mPreviewStoryController =
            new PreviewStoryController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        mPreviewStoryController.setupPreviewStoryList();
    }
}
