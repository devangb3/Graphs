package com.example.demo.LearningGraphs;

import java.util.*;

public class GraphTraverser {
    public static void depthFirstTraversal(Vertex start, List<Vertex> visitedVertices){
        System.out.println(start.getData());
        for(Edge e : start.getEdges()){
            Vertex neighbor = e.getEnd();
            if(!visitedVertices.contains(neighbor)){
                visitedVertices.add(neighbor);
                depthFirstTraversal(neighbor,visitedVertices);
            }
        }
    }
    public static void breadthFirstTraversal(Vertex start, List<Vertex> visitedVertives){
        Queue<Vertex> visitQueue = new LinkedList<Vertex>();
        visitQueue.add(start);
        while(!visitQueue.isEmpty()){
            Vertex curr = visitQueue.poll();
            System.out.println(curr.getData());
            for(Edge e : curr.getEdges()){
                Vertex neighbor = e.getEnd();
                if(!visitedVertives.contains(neighbor)) {
                    visitedVertives.add(neighbor);
                    visitQueue.add(neighbor);
                }
            }
        }
    }
    public static void main(String[] args) {
        TestGraph test = new TestGraph();
        Vertex startVertex = test.getStartingVertex();
        depthFirstTraversal(startVertex, new ArrayList<>(List.of(startVertex)));
        System.out.println("BFS");
        breadthFirstTraversal(startVertex, new ArrayList<>(List.of(startVertex)));
    }
}
