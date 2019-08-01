/**
 * This file implements the Network class which implements NetworkInterface
 * interface.
 * 
 * @author Guanyi Wang
 *
 */
/*
 * A network includes a relational network of users and corporate, two counters,
 * one for messages and one for nodes.
 */
public class Network implements NetworkInterface {
    private int messageCount;
    public Graph relationalNetwork = new Graph();
    private int nodeCount = 0;

    /*
     * While creating a Profile, the profile will be stored in the graph as a
     * node. The (user)name will be a label of the corresponding node.
     */
    public void createUserProfile(String name, int age) { // create userProfile

	Profile userProfile = new Profile(name, age);
	relationalNetwork.addNode(name, userProfile);// use name as label and
						     // store profile in data
	nodeCount++;
    }

    public void createCorporateProfile(String name) { // create corporateProfile

	Profile coporateProfile = new Profile(name);
	relationalNetwork.addNode(name, coporateProfile);// same as the last
							 // method
	nodeCount++;
    }

    /*
     * While a user rate a corporate company, it will add an weighted edge
     * between user and company, with rate star as its weight.
     */
    public void rate(String username, String corperateName, int rate) {
	relationalNetwork.addWeightedEdge(username, corperateName, rate);
    }

    /* Method to print the rate of a user to a specific company. */
    public void printRate(String username, String corporateName) {
	System.out.println(relationalNetwork.getWeight(username, corporateName));
    }

    /*
     * This method is used to connect two simple user by adding an edge between
     * them. The weight between two simple users will always be -1.
     */
    public void connect(String username1, String username2) {
	relationalNetwork.addEdge(username1, username2);
    }

    /* Methods to print a list of friends of a given user */
    public void printFriendList(String username) {
	System.out.println("Friend List of " + username + ":");
	// should not print corporate company
	relationalNetwork.findNode(username).printEdge(false);
	System.out.println();
    }

    /*
     * Methods to get the distance between two users including the corporate
     * nodes in between.
     */
    public int distance(String username1, String username2) {
	return relationalNetwork.getDistance(username1, username2, true);
    }

    /*
     * Methods to print the path between two users including the corporate nodes
     * in between.
     */
    public void printPath(String username1, String username2) {
	System.out.println("The path form " + username1 + "to " + username2 + "(Include corporate):");
	relationalNetwork.printPath(username1, username2, true);
	System.out.println();
    }

    /*
     * Methods to get the distance between two users excluding the corporate
     * nodes in between.
     */
    public int distanceExcludeCorporate(String username1, String username2) {
	return relationalNetwork.getDistance(username1, username2, false);
    }

    /*
     * Methods to print the path between two users excluding the corporate nodes
     * in between.
     */
    public void printPathExcludeCorporate(String username1, String username2) {
	System.out.println("The path form " + username1 + "to " + username2 + "(Exclude corporate):");
	relationalNetwork.printPath(username1, username2, false);
	System.out.println();
    }

    /*
     * Post messages to users' wall which satisfied the agelimit and privacy
     * settings. This method is only used when a simple user posts its messages.
     * Messages are only added to the friends' userWall waiting to be printed.
     */
    public void postMessage(String username, String message, int privacy, int ageLimit) {
	// Generate message
	Message message1 = new Message(message, username, privacy, ageLimit);
	if (privacy == 0) {// this message is public
	    for (int i = 0; i < nodeCount; i++) {// traverse node list
		Profile p = (Profile) relationalNetwork.getNode(i).getData();
		// this is a simple user's profile and age satisfied
		if ((p.getProfileType() == 0) && (p.getUserAge() >= ageLimit)) {
		    // push it into user's wall
		    p.getUserWall().push(message1, message1.getCurrentTime());
		}
	    }
	} else {// find friends according to privacy
	    // get all nodes in graph which satisfy the privacy setting
	    Vector profileList = relationalNetwork.getNodesWithinDistance(username, privacy);
	    // post messages into these user's profile
	    for (int i = 0; i < profileList.size(); i++) {
		Profile temProfile = (Profile) profileList.get(i);
		if (temProfile.getUserAge() >= ageLimit) { // age satisfied
		    temProfile.getUserWall().push(message1, message1.getTimestamp());
		}
	    }
	}

    }

    /*
     * Post ads to users' adWall which satisfied the ageLimitaion. Paid ads are
     * posted to everyone, simple ads are only posted to users who ever rated on
     * the company.
     */
    public void postAd(String companyName, String message, int ageLimit, boolean paid) {
	// int timestamp = getCurrentTime();
	// generate ad message
	Message ad = new Message(message, companyName, ageLimit);
	if (paid) {// post to all users satisfied age limit
	    for (int i = 0; i < nodeCount; i++) {// traverse node list
		Profile p = (Profile) relationalNetwork.getNode(i).getData();
		// this is a simple user's profile and age satisfied
		if ((p.getProfileType() == 0) && (p.getUserAge() >= ageLimit)) {
		    // push it into user's wall
		    p.getAdWall().push(ad, ad.getCurrentTime());
		}
	    }
	} else {// only post to who subscribed it
		// find subscribers' profiles
	    Vector profileList = relationalNetwork.getDataList(companyName);
	    for (int i = 0; i < profileList.size(); i++) {
		Profile profile = (Profile) profileList.get(i);
		if (profile.getUserAge() > ageLimit) {
		    // Get user's name
		    Comparable userName = profile.getUserName();
		    // Get user's rate
		    int star = relationalNetwork.getWeight(companyName, userName);
		    // set priority, one star equals to one hour
		    int priority = ad.getTimestamp() + star * 3600;
		    profile.getAdWall().push(ad, priority);
		}
	    }
	}
    }
    /*
     * This method is used to get messages and ad from user's wall and print it
     * following : add one ad after 4 messages.
     */

    public void printWall(String username) {
	System.out.println(username + "'s Message Wall:");
	int adIndicator = 0;// indicate when to insert an ad
	// Two temporary wall to get messages
	PriorityQueue temMessageWall = new PriorityQueue();
	PriorityQueue tempAdWall = new PriorityQueue();
	// get user's profile
	Profile profile = (Profile) relationalNetwork.findNode(username).getData();
	temMessageWall = profile.getUserWall();
	tempAdWall = profile.getAdWall();
	while (!temMessageWall.empty()) {
	    if (adIndicator >= 4) {
		if (tempAdWall.top() == null) {// no ad so far
		    ((Message) (temMessageWall.pop())).displayMessage();
		} else {// insert an ad
		    ((Message) (tempAdWall.pop())).displayMessage();
		    ((Message) (temMessageWall.pop())).displayMessage();
		    adIndicator = 1;// reset indicator
		}
	    } else// normal display
		((Message) (temMessageWall.pop())).displayMessage();
	    adIndicator++;
	}

	System.out.println();// Adjust the format
    }

    private int getCurrentTime() {
	return (int) (System.currentTimeMillis() / 1000L);
    }

}
