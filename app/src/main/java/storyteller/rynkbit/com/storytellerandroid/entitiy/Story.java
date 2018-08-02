package storyteller.rynkbit.com.storytellerandroid.entitiy;

import java.util.List;

public class Story {
    private String id;
    private String title;
    private String description;
    private List<List<String>> messages;

    public Story() {
    }

    public Story(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Story(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<String>> getMessages() {
        return messages;
    }

    public void setMessages(List<List<String>> messages) {
        this.messages = messages;
    }
}
