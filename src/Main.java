/**
 * This is the Main class contains the main method with the test examples.
 * 
 * @author Guanyi Wang
 *
 */
public class Main {
    public static void main(String[] args) {
	Network net = new Network();
	// create users
	net.createUserProfile("Kirk", 45);
	net.createUserProfile("Spock", 60);
	net.createUserProfile("McCoy", 50);
	net.createUserProfile("Skywalker", 10);
	net.createUserProfile("Obi-Wan", 30);
	net.createUserProfile("Guanyi", 24);
	net.createUserProfile("Xiao", 23);
	net.createUserProfile("Biyin", 5);
	// create corporate companies
	net.createCorporateProfile("Google");
	net.createCorporateProfile("Facebook");
	net.createCorporateProfile("Amazon");
	// connect users
	net.connect("Guanyi", "Xiao");
	net.connect("Guanyi", "Biyin");
	net.connect("Guanyi", "Spock");
	net.connect("Guanyi", "Obi-Wan");
	net.connect("Spock", "Kirk");
	net.connect("Spock", "McCoy");
	net.connect("Obi-Wan", "Skywalker");
	// Users rate corporate company
	net.rate("Guanyi", "Google", 5);
	net.rate("Guanyi", "Facebook", 1);
	net.rate("Xiao", "Google", 5);
	net.rate("Kirk", "Google", 3);
	net.rate("Skywalker", "Amazon", 9);
	// Users post message
	net.postMessage("Guanyi", "I'm tired today.", 0, 0);
	net.postMessage("Guanyi", "I've finished my work.", 1, 0);
	net.postMessage("Xiao", "I don't like star strek.", 1, 0);
	net.postMessage("Xiao", "I don't like star war.", 1, 0);
	net.postMessage("Xiao", "I don't like star movies.", 1, 0);
	net.postMessage("Spock", "Live long and prosper.", 0, 0);
	net.postMessage("Kirk", "She is hot.", 0, 18);
	// Post ads.
	net.postAd("Google", "You can download the latest chorme now!", 0, true);
	net.postAd("Google", "New playboy is online.", 18, false);
	net.postAd("Facebook", "New version avaiable now!", 0, true);
	// print wall
	net.printWall("Guanyi");
	net.printWall("Biyin");
	net.printFriendList("Guanyi");
	// print distance to exam the function
	System.out.println(net.distance("Xiao", "Kirk"));
	System.out.println(net.distanceExcludeCorporate("Xiao", "Kirk"));
	// print path
	net.printPath("Xiao", "Kirk");
	net.printPathExcludeCorporate("Xiao", "Kirk");

    }
}
