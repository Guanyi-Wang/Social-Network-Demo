/**
 * This class consists of method that create or operate on ListElement and
 * LinkedList.
 * 
 * @author Frederik TEMMERMANS
 * @author Guanyi Wang
 */

public class LinkedList {
    private class ListElement {
	private Object el1;
	private ListElement el2;

	private ListElement(Object el, ListElement nextElement) {
	    el1 = el;
	    el2 = nextElement;
	}

	private ListElement(Object el) {
	    this(el, null);
	}

	private Object first() {
	    return el1;
	}

	private ListElement rest() {
	    return el2;
	}

	private void setFirst(Object value) {
	    el1 = value;
	}

	private void setRest(ListElement value) {
	    el2 = value;
	}
    }

    private ListElement head;
    private int count;

    public LinkedList() {
	head = null;
	count = 0;
    }

    public ListElement getHead() {
	return head;
    }

    public void addFirst(Object o) {
	head = new ListElement(o, head);
	count++;
    }

    public Object getFirst() {
	return head.first();
    }

    public Object get(int n) {
	ListElement d = head;
	while (n > 0) {
	    d = (ListElement) d.rest();
	    n--;
	}
	return d.first();
    }

    public String toString() {
	String s = "(";
	ListElement d = head;
	while (d != null) {
	    s += d.first().toString();
	    s += " ";
	    d = (ListElement) d.rest();
	}
	s += ")";
	return s;
    }

    public void removeFirst() {
	head = head.rest();
	count--;
    }

    public void removeLast() {
	ListElement d = head;
	while ((d.rest()).rest() != null) {
	    d = d.rest();

	}
	d.setFirst(null);
    }

    public void addSorted(Comparable o) {
	if (head == null) {
	    head = new ListElement(o, head);
	} else if (((Comparable) head.first()).compareTo(o) > 0) {
	    head = new ListElement(o, head);
	} else {
	    ListElement d = head;
	    while ((d.rest() != null) && (((Comparable) d.rest().first()).compareTo(o) < 0)) {
		d = d.rest();
	    }
	    ListElement next = d.rest();
	    d.setRest(new ListElement(o, next));
	}
	count++;
    }

    public int size() {
	return count;
    }

}
