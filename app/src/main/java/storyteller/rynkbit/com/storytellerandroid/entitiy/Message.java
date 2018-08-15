package storyteller.rynkbit.com.storytellerandroid.entitiy;

public class Message {
    private boolean left;
    private String text;
    private String sender;

    public Message(boolean left, String text, String sender) {
        this.left = left;
        this.text = text;
        this.sender = sender;
    }

    public Message() {
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
