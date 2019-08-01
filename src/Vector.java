/**
 * This file implements the vector class.
 * 
 * @author Bart JANSEN
 * @author Guanyi Wang
 * 
 */
class Vector {
    protected Comparable data[];
    protected int count;

    public Vector(int capacity) {
	data = new Comparable[capacity];
	count = 0;
    }

    public Vector() {
	data = new Comparable[100];// initial capacity is 100
	count = 0;
    }

    public int size() {
	return count;
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public Comparable get(int index) {
	return data[index];
    }

    public void set(int index, Comparable obj) {
	data[index] = obj;
    }

    public boolean contains(Comparable obj) {
	for (int i = 0; i < count; i++) {
	    if (data[i] == obj)
		return true;
	}
	return false;
    }

    public void addFirst(Comparable item) {
	for (int i = count; i > 0; i--) {

	    data[i] = data[i - 1];

	}
	data[0] = item;
	count++;
    }

    /**
     * This method adds a Comparable to the last of vector and will extend
     * capability automatically.
     * 
     * @param o
     */
    public void addLast(Comparable o) {
	if (count + 1 > data.length) {
	    this.extendCapability();
	} else {

	    data[count] = o;
	    count++;
	}
    }

    public boolean binarySearchInt(int key) {
	int start = 0;
	int end = count - 1;
	while (start <= end) {
	    int middle = (start + end + 1) / 2;
	    if (key < (int) data[middle])
		end = middle - 1;
	    else if (key > (int) data[middle])
		start = middle + 1;
	    else
		return true;
	}
	return false;
    }

    public Comparable getFirst() {
	return data[0];
    }

    public Comparable getLast() {
	return data[count - 1];
    }

    public void removeLast() {
	count--;
    }

    public void removeFirst() {
	for (int i = 0; i <= count - 2; i++) {
	    data[i] = data[i + 1];
	}
	count--;
    }

    /**
     * This method print the whole vector
     */
    public void print() {

	for (int i = 0; i < count; i++) {
	    System.out.println("data[" + i + "] = " + data[i]);
	}

    }

    /**
     * This method reverse the whole vector
     */
    public void reverse() {

	for (int i = 0; i < (double) (count) / 2; i++) {
	    Comparable temp = data[i];
	    data[i] = data[count - i - 1];
	    data[count - i - 1] = temp;

	}

    }

    /**
     * This method double each Comparable in vector.
     * 
     * @return a doubled vector
     */
    public Vector doubleVector() {
	Vector v = new Vector(count * 2);// A new vector with double length

	for (int i = 0; i < count; i++) {
	    v.addLast(data[i]);
	    v.addLast(data[i]);
	}

	return v;
    }

    /**
     * This method interleaves two vectors(with the same length)
     * 
     * @return new vector
     */
    public Vector interleaveVector(Vector v) {

	Vector v1 = new Vector(count * 2);
	for (int i = 0; i < count; i++) {
	    v.addLast(data[i]);
	    v.addLast(v.data[i]);
	}

	return v1;

    }

    /**
     * This method extends(doubles) the capability of vector.
     */

    protected void extendCapability() {

	Comparable[] data2 = new Comparable[count * 2];
	for (int i = 0; i < count; i++) {
	    data2[i] = data[i];

	}
	data = data2;
    }

    public int capacity() {

	return data.length;
    }
}