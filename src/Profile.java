
/**
 * This file implements the Profile class.
 * 
 * @author Guanyi Wang
 *
 */
public class Profile implements Comparable {

    /*
     * There are two kinds of Profiles: 1.User Profile includes a user's name,
     * age, a profile type(0)and two walls which are priority queues storing the
     * messages and ads posted to a specific user. 2.Corporate Profile includes
     * a corporate name and a profile type (1).
     */
    private String username;
    private int userAge;
    private int userProfileType;
    private PriorityQueue normalMessageWall;
    private PriorityQueue adWall;

    /* Constructor for a userProfile. */
    public Profile(String name, int age) {
	this.username = name;
	this.userAge = age;
	this.userProfileType = 0; // 0 means this is a personal profile
	this.normalMessageWall = new PriorityQueue();
	this.adWall = new PriorityQueue();
    }

    /* Constructor for a corporateProfile. */
    public Profile(String name) {
	this.username = name;
	this.userProfileType = 1; // 1 means this is a corporate profile
    }

    /*
     * Method for posting a normal message on the wall. Push the message to the
     * priority queue with timestamp as priority.
     */
    public void postMessage(Message message1, int timestamp) {
	this.normalMessageWall.push(message1, timestamp);

    }

    /*
     * Method for posting an ad on the wall. Push the message to the priority
     * queue with rate stars as priority.
     */
    public void postAd(Message message2, int stars) {
	this.adWall.push(message2, stars);

    }

    public String getUserName() {
	return this.username;
    }

    public int getUserAge() {
	return this.userAge;
    }

    public int getProfileType() {
	return this.userProfileType;
    }

    public PriorityQueue getUserWall() {
	return this.normalMessageWall;
    }

    public PriorityQueue getAdWall() {
	return this.adWall;
    }

    /* To String Method for Profile class. */
    public String toString() {
	if (this.userProfileType == 0) {
	    // personal profile
	    return "User Name:" + this.username + ", User Age: " + this.userAge + ".";
	} else {
	    // corporate profile
	    return "Corporate Name: " + this.username;
	}
    }

    /* Method for displaying profile. */
    public void display() {
	System.out.println(this.toString());
    }

    /* compareTo Method for Profile class. */
    @Override
    public int compareTo(Object o) {
	if (this.username.equals(((Profile) o).getUserName()) && (this.userAge == (((Profile) o).userAge)))
	    return 0;// Two Profiles are same if and only if the user's name and
		     // age are same.
	else {
	    return -1;
	}
    }

}
