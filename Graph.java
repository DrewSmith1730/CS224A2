// jdh CS224 Spring 2023

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    List<Node> nodes;

    //---------------------------------------------------

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    //---------------------------------------------------

    public void addNode(Node node) {
        for (Node n: this.nodes) {
            if (n == node) {
                System.out.println("ERROR: graph already has a node " + n.name);
                assert false;
            }
        }
        this.nodes.add(node);
    }

    //---------------------------------------------------

    public void addEdge(Node n1, Node n2, int weight) {
        // outgoing edge
        int idx1 = this.nodes.indexOf(n1);
        if (idx1 < 0) {
            System.out.println("ERROR: node " + n1.name + " not found in graph");
            assert false;
        }

        int idx2 = this.nodes.indexOf(n2);
        if (idx2 < 0) {
            System.out.println("ERROR: node " + n2.name + " not found in graph");
            assert false;
        }

        Edge e1 = new Edge(n1, n2, weight);
        this.nodes.get(idx1).add(e1);

        Edge e2 = new Edge(n2, n1, weight);
        this.nodes.get(idx2).add(e2);
    }

    //-----------------------------------------------

    public List<Edge> prim(Node s) {
        int totalWeight = 0;
        // seen nodes
        List<Node> S = new ArrayList<>(nodes.size());
        // n - 1 edges in a min spanning tree
        List<Edge> minEdges = new ArrayList<>(nodes.size()-1);
        // add start node to S
        S.add(s);
        boolean seen = false;
        while(S.size() < nodes.size()) {
            // add counter here to drop one of the loops and check if the node is in S before checking any edges
            // cheapest edge init up here
            Node curr;
            Edge currEdge;
            Edge cheapest = null;
            // for each node in S
            for (int i = 0; i < S.size(); i++) {
                curr = S.get(i);
                for (int j = 0; j < curr.adjlist.size(); j++){
                    currEdge = curr.adjlist.get(j);
                    for (Node v : S) {
                        if (v.name == currEdge.n2.name) {
                            seen = true;
                        }
                    }
                    // if the edge is not seen and also lowest edge value seen so far
                    if (!seen) {
                        if (cheapest == null || cheapest.weight > currEdge.weight) {
                            cheapest = currEdge;
                        }
                    }
                    seen = false;
                }
            } // after all nodes and edges so far have been checked for lowet val
            S.add(cheapest.n2); // adds the node visited to S
            minEdges.add(cheapest); // add cheapest node to MST
            totalWeight += cheapest.weight; // add the weight to total weight of MST
        }
        // print total weight before returning
        System.out.print("The total cost of the Minimum spanning tree: ");
        System.out.println(totalWeight);

        return minEdges;
    } // prim()

    //-----------------------------------------------

    public PriorityQueue<Node> updatePQ(PriorityQueue<Node> pq) {
        // add all values to an array order the array add them back in their new order
        List<Node> newOrder = new ArrayList<>(nodes.size());
        while(!pq.isEmpty()){
            newOrder.add(pq.poll());
        }
        newOrder.sort(Node::compareTo);
        for(int i = 0; i < newOrder.size(); i++){
            pq.add(newOrder.get(i));
        }
        return pq;
    }

    public List<Edge> primPQ(Node s) {
        // implement this
        // create list S
        // create list
        int totalWeight = 0;
        int as = nodes.size();
        List<Node> S = new ArrayList<>(as);

        // n - 1 edges in a min spanning tree
        List<Edge> minEdges = new ArrayList<>((as-1));
        List<Boolean> selected = new ArrayList<>((nodes.size()+1));
        for(int i = 0; i < (nodes.size()+1); i++){
            selected.add(false);
        } // set all nodes as not selected


        PriorityQueue<Node> priorQ = new PriorityQueue<>(as);
        for(int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != s){
                // set priorty
                nodes.get(i).priority = Integer.MAX_VALUE;
            } // adds all nodes to the priority queue
            priorQ.add(nodes.get(i));
        } // set all priorites to max value
        // set the priority for s to 0
        List<Edge> cheapest = new ArrayList<>(nodes.size());
        priorQ = updatePQ(priorQ);
        // while s doesnt contain all nodes
        while(S.size() < (nodes.size()-1)){
            // get first node in the priory queue : v
            Node curr = priorQ.poll();

            // for each node adjacent to v not selected already
            for(int i = 0; i < curr.adjlist.size(); i++){
                // check if the node the edge is connecting to is visited or not
                Edge currE = curr.adjlist.get(i);
                if(!selected.get(currE.n2.name)){ // if not seen
                    // if the edge weight is less then the priority make the weight the currE.n2
                    if(currE.weight < currE.n2.priority){
                        currE.n2.priority = currE.weight;
                        // add cheapest edges seen to list
                        cheapest.add(currE);
                    }
                }

            }
            // sort and check all edges in cheapest edges for any going to nodes already visited
            for(int i = 0; i < cheapest.size(); i++){
                if(selected.get(cheapest.get(i).n2.name)){
                    cheapest.remove(i);
                }
            }
            cheapest.sort(Edge::compareTo);

            priorQ = updatePQ(priorQ);
            S.add(priorQ.peek()); // adds the node visited to S
            selected.set(priorQ.peek().name, true); // marks the node as visited
            minEdges.add(cheapest.get(0)); // add cheapest node to MST
            cheapest.remove(0); // remove the cheapest edge that was just used
            totalWeight += priorQ.peek().priority; // weight cost for the MST
        }
        System.out.print("The total cost of the Minimum spanning tree: ");
        System.out.println(totalWeight);
        return minEdges;
    } // primPQ()

} // class Graph

