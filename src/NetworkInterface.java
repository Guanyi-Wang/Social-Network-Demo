/**
 * This file defines the NetworkInterface interface.
 * 
 * @author Frederik TEMMERMANS
 *
 */
public interface NetworkInterface {
    void createUserProfile(String name, int age);

    void createCorporateProfile(String name);

    void rate(String username, String corperateName, int rate);

    void connect(String username1, String username2);

    void printFriendList(String username);

    void postMessage(String username, String message, int privacy, int ageLimit);

    void postAd(String username, String message, int ageLimit, boolean paid);

    void printWall(String username);
}
