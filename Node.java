// jdh CS224 Spring 2033

import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class Node implements Comparable {
    List<Edge> adjlist;
    int name;
    int priority; // needed for PQ implementation

    public Node(int name) {
        this.name = name;
        this.adjlist = new ArrayList<Edge>();
        this.priority = 0;
    }

    public void add(Edge edge) {
        this.adjlist.add(edge);
    }

    // this is needed for PQ implementation
    public int compareTo(Object o) {
        Node otherNode = (Node) o;
        if (this.priority < otherNode.priority)
            return -1;
        else if (this.priority > otherNode.priority)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        String s = "N" + this.name;
        return s;
    }
} // class Node

