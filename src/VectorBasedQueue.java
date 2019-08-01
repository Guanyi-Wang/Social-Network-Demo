/**
 * This class implements queue based on vector
 * 
 * @author Frederik TEMMERMANS
 * @author Guanyi Wang
 *
 */
public class VectorBasedQueue {

    private Vector data;

    public VectorBasedQueue() {
	data = new Vector(100);
    }

    public void push(Comparable o) {
	data.addLast(o);
    }

    public Comparable pop() {
	Comparable o = data.getFirst();
	data.removeFirst();
	return o;

    }

    public Comparable top() {
	return data.getFirst();
    }

    public int size() {
	return data.count;
    }

    public boolean empty() {
	return data.size() == 0;
    }
}
