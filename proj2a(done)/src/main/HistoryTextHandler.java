package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.*;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngramMap;

    public HistoryTextHandler(NGramMap map) {
        ngramMap = map;
    }
    public String handle(NgordnetQuery query) {
        List<String> words = query.words();
        int startYear = query.startYear();
        int endYear = query.endYear();

        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word);
            builder.append(": ");
            builder.append(ngramMap.weightHistory(word, startYear, endYear).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
