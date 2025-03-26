package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wordNet;

    public HyponymsHandler(String synsetsFile, String hyponymsFile){
        wordNet = new WordNet(synsetsFile, hyponymsFile);
    }

    @Override
    public String handle(NgordnetQuery q){
        return wordNet.hyponyms(q.words()).toString();
    }
}
