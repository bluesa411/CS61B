package main;

import java.util.ArrayList;
import java.util.List;

public class DirectGraph {
    private List<List<Integer>> graph;

    public DirectGraph(){
        graph = new ArrayList<List<Integer>>();
    }

    public void createNode(){
        graph.add(new ArrayList<Integer>());
    }

    public void addEdge(int v, int w){
        if(v < 0 || w < 0 || v >= graph.size() || w >= graph.size()){
            throw new IndexOutOfBoundsException();
        }
        graph.get(v).add(w);
    }
}
