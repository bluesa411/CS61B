package main;

import edu.princeton.cs.algs4.In;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.*;

public class WordNet {
    private HashMap<Integer, HashSet<String>> wordIndexTable;
    private DirectGraph graph;

    public WordNet(String synsetsFile, String hyponymsFile) {
        wordIndexTable = new HashMap<>();
        graph = new DirectGraph();
        //read synsetsFile(complete the convert table, and create node)
        In inSynsets = new In(synsetsFile);
        while(inSynsets.hasNextLine()){
            String line = inSynsets.readLine();
            String[] tokens = line.split(",");
            String[] words = tokens[1].split(" ");
            HashSet<String> wordSet = new HashSet<>();
            for(String word : words){
                wordSet.add(word);
            }
            wordIndexTable.put(Integer.parseInt(tokens[0]), wordSet);
            graph.createNode();
        }
        In inhyponyms = new In(hyponymsFile);
        while(inhyponyms.hasNextLine()){
            String line = inhyponyms.readLine();
            String[] tokens = line.split(",");
            for(int i = 1; i < tokens.length; i++){
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
    }

    private Set<Integer> wordIndex(String word){
        Set<Integer> indexSet = new HashSet<>();
        for(int i : wordIndexTable.keySet()){
            if(wordIndexTable.get(i).contains(word)){
                indexSet.add(i);
            }
        }
        return indexSet;
    }

    private HashSet<String> getWordSet(int index){
        return wordIndexTable.get(index);
    }

    public Set<String> oneNodeHyponyms(int index){
        Set<String> hyponyms = new HashSet<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        indexQueue.add(index);
        while(!indexQueue.isEmpty()){
            hyponyms.addAll(getWordSet(indexQueue.peek()));
            indexQueue.addAll(graph.neighbors(indexQueue.poll()));
        }
        return hyponyms;
    }

    public Set<String> oneWordHyponyms(String word){
        Set<String> hyponyms = new HashSet<>();
        for(int i : wordIndex(word)){
            hyponyms.addAll(oneNodeHyponyms(i));
        }
        return hyponyms;
    }

    public List<String> hyponyms(@NonNull List<String> words){
        Set<String> theSameHyponyms = new HashSet<>();
        boolean firstFlag = true;
        for(String word : words){
            if(firstFlag){
                firstFlag = false;
                theSameHyponyms.addAll(oneWordHyponyms(word));
                continue;
            }
            theSameHyponyms.retainAll(oneWordHyponyms(word));
        }
        List<String> hyponymsList = new ArrayList<>(theSameHyponyms);
        Collections.sort(hyponymsList);
        return hyponymsList;
    }

}
