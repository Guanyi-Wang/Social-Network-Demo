/**
 * This file implements the Stack class.
 * 
 * @author Guanyi Wang
 * @author Frederik TEMMERMANS
 */
public class Stack {
    private Vector data;
    private int count;

    public Stack() {
	data = new Vector();
	count = 0;
    }

    public void push(Comparable o) {
	data.addLast(o);
    }

    public Comparable pop() {
	count--;
	return data.getLast();
    }

    public Comparable top() {
	return data.getLast();
    }

    public int size() {
	return count;
    }

    public boolean empty() {
	return (count == 0);
    }

}
