
/**
 * This file implements the Message class.
 * 
 * @author Guanyi Wang
 *
 */
public class Message {
    /*
     * Messages have two types: ads and normal messages. Both of them consist of
     * content, an author, a privateSetting, an age limit, a time stamp and a
     * messageType.
     */
    private String content;
    private String author;

    private int privateSetting; // 0 represents public, 1 friends, 2 friends of
				// friends and so on
    private int ageLimit;
    private int timestamp;
    private int messageType; // 0 represents normal message and 1 represents
			     // ads.

    /* Constructor for Normal Messages. */
    public Message(String messageContent, String author, int privateSettign, int ageLimit) {
	this.content = messageContent;
	this.author = author;
	this.privateSetting = privateSettign;
	this.ageLimit = ageLimit;
	this.timestamp = this.getCurrentTime();
	this.messageType = 0;
    }

    /* Constructor for Ad Messages. */
    public Message(String adContent, String companyName, int ageLimit) {
	this.content = adContent;
	this.author = companyName;
	this.ageLimit = ageLimit;
	this.timestamp = this.getCurrentTime();
	this.messageType = 1;
    }

    /* toString method for whole messages. */
    public String toString() {
	String s = this.content + " Published by:" + author;
	return s;
    }

    /* Method to display message. */
    public void displayMessage() {
	System.out.println(this.toString());
    }

    public String getContent() {
	return content;
    }

    public int getPrivateSetting() {
	return this.privateSetting;
    }

    public int getAgeLimit() {
	return this.ageLimit;
    }

    public String getAuthor() {
	return this.author;
    }

    public int getTimestamp() {
	return this.timestamp;
    }

    public int getMessageType() {
	return this.messageType;
    }

    /**
     * Method to calculate current time stamp.
     * 
     * @return current time stamp
     */
    public int getCurrentTime() {
	return (int) (System.currentTimeMillis() / 1000L);
    }
}
