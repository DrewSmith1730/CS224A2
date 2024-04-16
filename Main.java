// jdh CS224 Spring 2023

import java.util.List;

public class Main {

    public static void main(String args[]) {
        testOne();
        testTwo();
    }

    // From the lecture slides.
    // Starting at node 10, here are the edges that form an MST:
    // (10, 8), (8, 5), (5, 2), (2, 1), (1, 3), (3, 4), (5, 6),
    // (2, 9), (6, 7); the total weight is 54
    // Your function should select nodes in this order:
    // 10, 8, 5, 2, 1, 3, 4, 6, 9, 7

    public static void testOne() {
        System.out.println("this is testOne()");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        Graph G = new Graph();
        G.addNode(n1);
        G.addNode(n2);
        G.addNode(n3);
        G.addNode(n4);
        G.addNode(n5);
        G.addNode(n6);
        G.addNode(n7);
        G.addNode(n8);
        G.addNode(n9);
        G.addNode(n10);

        G.addEdge(n1, n2, 1);
        G.addEdge(n1, n3, 2);
        G.addEdge(n2, n3, 4);
        G.addEdge(n2, n4, 21);
        G.addEdge(n2, n5, 6);
        G.addEdge(n2, n9, 9);
        G.addEdge(n3, n4, 5);
        G.addEdge(n3, n6, 8);
        G.addEdge(n4, n5, 12);
        G.addEdge(n5, n6, 7);
        G.addEdge(n5, n8, 3);
        G.addEdge(n5, n9, 20);
        G.addEdge(n6, n7, 11);
        G.addEdge(n6, n10, 13);
        G.addEdge(n7, n8, 14);
        G.addEdge(n8, n9, 16);
        G.addEdge(n8, n10, 10);

        System.out.println("first call prim()");
        List<Edge> edges = G.prim(n10);
        for (Edge e: edges)
            System.out.println(e);

        System.out.println();
        System.out.println("now call primPQ()");
        edges = G.primPQ(n10);
        for (Edge e: edges)
            System.out.println(e);

//  // grad assignment
//  System.out.println();
//  System.out.println("now call kruskal()");
//  edges = G.kruskal();
//  for (Edge e: edges)
//    System.out.println(e);

    } // testOne()

    public static void testTwo() {
        System.out.println("this is testTwo()");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Graph G = new Graph();
        G.addNode(n1);
        G.addNode(n2);
        G.addNode(n3);
        G.addNode(n4);
        G.addNode(n5);

        G.addEdge(n1, n2, 4);
        G.addEdge(n1, n4, 6);
        G.addEdge(n1, n5, 9);
        G.addEdge(n2, n3, 12);
        G.addEdge(n2, n4, 11);
        G.addEdge(n3, n4, 5);
        G.addEdge(n3, n5, 10);
        G.addEdge(n4, n5, 2);

        System.out.println("first call prim()");
        List<Edge> edges = G.prim(n5);
        for (Edge e: edges)
            System.out.println(e);

        System.out.println();
        System.out.println("now call primPQ()");
        edges = G.primPQ(n5);
        for (Edge e: edges)
            System.out.println(e);

    } // testTwo()
}
