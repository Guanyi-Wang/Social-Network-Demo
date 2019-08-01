/**
 * This class consists of method that create or operate on priority queue based
 * on LinkedList.
 * 
 * @author Frederik TEMMERMANS
 * @author Guanyi Wang
 * @see LinkedList
 */
public class PriorityQueue {
    private class PriorityPair implements Comparable {
	private Object element;
	private Object priority;

	private PriorityPair(Object element, Object priority) {
	    this.element = element;
	    this.priority = priority;

	}

	private Object getElement() {
	    return element;
	}

	private Object getPriority() {
	    return priority;
	}

	public int compareTo(Object o) {
	    PriorityPair p2 = (PriorityPair) o;
	    return ((Comparable) priority).compareTo(p2.priority);
	}
    }

    private LinkedList data;
    private int count;

    public PriorityQueue() {
	data = new LinkedList();

    }

    public void push(Object element, int priority) {
	PriorityPair p2 = new PriorityPair(element, priority);
	data.addSorted(p2);
	count++;
    }

    public Object pop() {
	Object d = ((PriorityPair) data.getFirst()).getElement();
	data.removeFirst();
	count--;
	return d;
    }

    public Object top() {
	return ((PriorityPair) data.getFirst()).getElement();
    }

    public int size() {
	return count;
    }

    public boolean empty() {
	return this.count == 0;
    }

}
