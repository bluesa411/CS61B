package main;

import java.util.HashMap;
import java.util.List;

public class WordNet {
    private HashMap<Integer, List<String>> wordIndexTable;
    private DirectGraph graph;

    public WordNet(String synsetsFile, String hyponymsFile) {
        wordIndexTable = new HashMap<>();
        graph = new DirectGraph();
        //read synsetsFile(complete the convert table, and create node)
    }
}
