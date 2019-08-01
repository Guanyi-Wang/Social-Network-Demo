/**
 * This file implements the Graph(undirectional) class for storing the
 * relationship between users and corporate companies and corresponding rating
 * stars.
 * 
 * @author Frederik TEMMERMANS
 * @author Guanyi Wang
 *
 */
public class Graph {
    // Graph use a vector to store Nodes.
    private Vector nodes;

    /* Constructor for Graph. */
    public Graph() {
	nodes = new Vector();
    }

    /*
     * In a Node, there are a vector storing Edges, a visited flag, a node
     * indicated the parent, a label ,a distance and the data stored in node.
     */
    protected class Node implements Comparable {

	private Comparable info;
	private Vector edges;
	private Comparable data;
	private boolean visited;
	private int distance;// distance from source node this node
	private Node parent;// used in BFS,point to source node

	public Node(Comparable label, Comparable dataComparable) {
	    this.info = label;
	    this.edges = new Vector();
	    this.data = dataComparable;
	    this.parent = null;
	    this.distance = 0;
	}

	public Node() {

	}

	public void addEdge(Edge e) {
	    edges.addLast(e);
	}

	public int compareTo(Object o) {
	    Node n = (Node) o;
	    return n.data.compareTo(this.data);
	}

	public Comparable getLabel() {
	    return this.info;
	}

	public Node getParent() {
	    return this.parent;
	}

	public Comparable getData() {
	    return this.data;
	}

	public int getDistance() {
	    return this.distance;
	}

	public boolean getVisted() {
	    return this.visited;
	}

	public Vector getEdgeList() {
	    return this.edges;
	}

	public String toString() {
	    return this.data.toString();
	}

	/*
	 * This method get the weight of a edge between this Node another Node
	 * which represented by the input label.
	 */
	public int getWeight(Comparable label) {
	    int n = 0;
	    for (int i = 0; i < edges.size(); i++) {
		if (((Edge) (edges.get(i))).getLabel().equals(label)) {
		    n = ((Edge) (edges.get(i))).getWeight();
		    break;
		}
	    }
	    return n;
	}

	/**
	 * Print all nodes connected to this node.
	 * 
	 * @param includeWeighted
	 *            whether take weighted edge into consideration
	 */
	public void printEdge(boolean includeWeighted) {
	    if (includeWeighted) {
		for (int i = 0; i < edges.size(); i++) {
		    System.out.println(((Edge) (edges.get(i))).getData().toString());
		}
	    } else {
		for (int i = 0; i < edges.size(); i++) {
		    Edge edge = (Edge) edges.get(i);
		    if (edge.getWeight() == -1) {
			System.out.println(edge.getData().toString());
		    }
		}
	    }
	}
    }

    /*
     * An edge includes a Node and a weight . Initial weight is -1.
     */
    private class Edge implements Comparable {
	private Node toNode;
	private int weight;

	public Edge(Node to) {
	    toNode = to;
	    weight = -1;// initial weight is -1
	}

	public Edge(Node to, int w) {
	    toNode = to;
	    weight = w;
	}

	public int compareTo(Object o) {
	    Edge n = (Edge) o;
	    return n.toNode.compareTo(toNode);
	}

	public int getWeight() {
	    return weight;
	}

	public Comparable getData() {
	    return toNode.getData();
	}

	public Comparable getLabel() {
	    return toNode.getLabel();
	}
    }

    public Node getNode(int i) {
	return (Node) nodes.get(i);
    }

    public void addNode(Comparable label, Comparable data) {
	nodes.addLast(new Node(label, data));
    }

    /* Find the corresponding node in graph using the label. */
    public Node findNode(Comparable label) {
	Node res = null;
	for (int i = 0; i <= nodes.size(); i++) {
	    Node n = (Node) nodes.get(i);
	    if (n.getLabel().compareTo(label) == 0) {
		res = n;
		break;
	    }
	}
	return res;
    }

    /* Find the corresponding node in graph using the label and get the data. */
    public Comparable getData(Comparable label) {
	return findNode(label).getData();
    }

    /*
     * Add weighted edges between two edges, since this is an undirectional
     * graph, need to add edges on both nodes.
     */
    public void addWeightedEdge(Comparable nodeLabel1, Comparable nodeLabel2, int w) {
	Node n1 = findNode(nodeLabel1);
	Node n2 = findNode(nodeLabel2);
	n1.addEdge(new Edge(n2, w));
	n2.addEdge(new Edge(n1, w));
    }

    /* Add edges between two edges. */
    public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2) {
	Node n1 = findNode(nodeLabel1);
	Node n2 = findNode(nodeLabel2);
	n1.addEdge(new Edge(n2));
	n2.addEdge(new Edge(n1));
    }

    /* Get weight of an edge between two nodes. */
    public int getWeight(Comparable nodeLabel1, Comparable nodeLabel2) {
	Node n1 = findNode(nodeLabel1);
	return n1.getWeight(nodeLabel2);
    }

    /**
     * This function use Breath First Search to find the shortest path from one
     * to the corresponding node with input nodeLabel.
     * 
     * 
     * @param current
     *            source node of the search.
     * @param nodeLabel
     *            label of target node.
     * @param includeWeightedEdge
     *            TURE means include weighted edge, FALSE means exclude weighted
     *            edge only unweighted(weight=-1) is allowed.
     * @return Target Node.
     */
    public Node BFS(Node current, Comparable nodeLabel, boolean includeWeightedEdge) {
	Node targetNode = new Node();// Node to be found
	// Initialize visited flag, distance and parent pointer.
	for (int i = 0; i < nodes.size(); i++) {
	    Node temp = (Node) nodes.get(i);
	    temp.visited = false;
	    temp.parent = null;
	    temp.distance = 0;
	}
	// use a queue to store unprocessed nodes.
	VectorBasedQueue queue = new VectorBasedQueue();
	current.visited = true;
	queue.push(current);
	while (!queue.empty()) {
	    // childNode is one of the edges of corresponding parentNode.
	    Node parentNode = (Node) queue.pop();
	    if ((parentNode.getLabel().compareTo(nodeLabel) != 0)) {
		// Traverse all the edges of the Node
		for (int i = 0; i < parentNode.edges.size(); i++) {
		    Edge e = (Edge) parentNode.edges.get(i);
		    Node childNode = (Node) e.toNode;
		    // when exclude weighted and the node is weighted
		    if (!childNode.visited) { // node is unvisited
			if (includeWeightedEdge) {// all edges should be
						  // included
			    childNode.parent = parentNode;// set parent
							  // relationship
			    childNode.visited = true;
			    childNode.distance = parentNode.distance + 1;// Update
									 // distance.
			    queue.push(childNode);
			} else if (e.getWeight() == -1) {// only unweighted edge
							 // included
			    childNode.parent = parentNode;// set parent
							  // relationship
			    childNode.visited = true;
			    childNode.distance = parentNode.distance + 1;// Update
									 // distance.
			    queue.push(childNode);
			}
		    }
		}
	    } else {
		targetNode = parentNode;
		break;// stop processing nodes from queue
	    }

	}
	return targetNode;
    }

    /**
     * Method to get distance between two nodes include or exclude weighted
     * path.
     * 
     * @param nodeLabel1
     *            source node
     * @param nodeLabel2
     *            target node
     * @param includeWeightedEdge
     *            TURE means include weighted edge, FALSE means exclude weighted
     *            edge only unweighted(weight=-1) is allowed.
     * @return distance
     */
    public int getDistance(Comparable nodeLabel1, Comparable nodeLabel2, boolean includeWeightedEdge) {
	Node start = findNode(nodeLabel1);
	return BFS(start, nodeLabel2, includeWeightedEdge).getDistance();

    }

    /**
     * Method to print the path between two nodes.
     * 
     * @param nodeLabel1
     *            source node
     * @param nodeLabel2
     *            target node
     * @param includeWeightedEdge
     *            TURE means include weighted edge, FALSE means exclude weighted
     *            edge only unweighted(weight=-1) is allowed.
     */
    public void printPath(Comparable nodeLabel1, Comparable nodeLabel2, boolean includeWeightedEdge) {

	Node start = findNode(nodeLabel1);
	Node end = this.BFS(start, nodeLabel2, includeWeightedEdge);
	while (end != null) {
	    System.out.println(end.getLabel());
	    end = end.parent;
	}

    }

    /**
     * This method finds nodes whose distance to source is less than the
     * distanceLimitaion and return them as a Vector. Mention that only node
     * with unweighted edges are counted.
     * 
     * @param nodeLabel
     *            label of source node
     * @param distanceLimiation
     *            distance limitation should be satisfied
     * @return nodeList a vector storing all satisfied data
     */
    public Vector getNodesWithinDistance(Comparable nodeLabel, int distanceLimiation) {

	Node start = findNode(nodeLabel);
	Vector dataList = new Vector();// store nodes within the distance
	// Initialize visited flag, distance and parent pointer.
	for (int i = 0; i < nodes.size(); i++) {
	    Node temp = (Node) nodes.get(i);
	    temp.visited = false;
	    temp.distance = 0;
	}
	// use a queue to store unprocessed nodes.
	VectorBasedQueue queue = new VectorBasedQueue();
	start.visited = true;
	queue.push(start);
	while (!queue.empty()) {
	    Node parentNode = (Node) queue.pop();
	    // If distance is still within limitation
	    if (parentNode.distance < distanceLimiation) {
		for (int i = 0; i < parentNode.edges.size(); i++) {
		    Edge e = (Edge) parentNode.edges.get(i);
		    if (e.getWeight() == -1) {// exclude weighted edges
			Node childNode = (Node) e.toNode;
			// Update distance
			childNode.distance = parentNode.distance + 1;
			queue.push(childNode);
			// Store it in nodeList
			dataList.addLast(childNode.getData());
		    }
		}

	    }

	}
	return dataList;
    }

    /*
     * Get data of all edges of a specific node
     * 
     * @return vector storing data in all edges.
     */
    public Vector getDataList(Comparable nodeLabel) {
	Vector dataList = new Vector();
	Vector edgeList = this.findNode(nodeLabel).getEdgeList();
	for (int i = 0; i < edgeList.size(); i++) {
	    Comparable data = ((Edge) edgeList.get(i)).getData();
	    dataList.addLast(data);
	}
	return dataList;
    }
}
