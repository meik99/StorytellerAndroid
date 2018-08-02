package storyteller.rynkbit.com.storytellerandroid.preview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import storyteller.rynkbit.com.storytellerandroid.R;

class PreviewStoryViewHolder extends RecyclerView.ViewHolder{
    private TextView txtStoryTitle;
    private TextView txtStoryDescription;

    public PreviewStoryViewHolder(View itemView) {
        super(itemView);

        txtStoryTitle = itemView.findViewById(R.id.txtStoryTitle);
        txtStoryDescription = itemView.findViewById(R.id.txtStoryDescription);
    }

    public PreviewStoryViewHolder setTitle(String title) {
        txtStoryTitle.setText(title);
        return this;
    }

    public PreviewStoryViewHolder setDescription(String description) {
        txtStoryDescription.setText(description);
        return this;
    }
}
