// jdh CS224 Spring 2023

public class Edge {
    int weight;
    Node n1;
    Node n2;

    public Edge(Node n1, Node n2, int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        String s = "(" + this.n1.name + ", " + this.n2.name + "); wt = " + this.weight;
        return s;
    }

    // needed only for grad assignment
    public int compareTo(Object o) {
        Edge otherEdge = (Edge) o;
        if (this.weight < otherEdge.weight)
            return -1;
        else if (this.weight > otherEdge.weight)
            return 1;
        else
            return 0;
    }
} // class Edge

