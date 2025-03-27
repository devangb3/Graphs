package com.example.demo.LearningGraphs;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Integer weight;
    public Edge(Vertex startV, Vertex endV, Integer inputWeight){
        this.start = startV;
        this.end = endV;
        this.weight = inputWeight;
    }
    public Vertex getStart(){
        return start;
    }
    public Vertex getEnd(){
        return end;
    }
    public Integer getWeight(){
        return weight;
    }
    public static void main(String[] args) {

    }
}
