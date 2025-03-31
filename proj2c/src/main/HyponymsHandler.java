package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static browser.NgordnetQueryType.ANCESTORS;
import static browser.NgordnetQueryType.HYPONYMS;
import static java.util.Collections.sort;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wordNet;
    private NGramMap ngramMap;

    public HyponymsHandler(String synsetsFile, String hyponymsFile, NGramMap ngramMap) {
        wordNet = new WordNet(synsetsFile, hyponymsFile);
        this.ngramMap = ngramMap;
    }

    private class TimeSeriesWithWord{
        private TimeSeries timeSeries;
        private String word;

        TimeSeriesWithWord(TimeSeries timeSeries, String word) {
            this.timeSeries = timeSeries;
            this.word = word;
        }

        TimeSeries getTimeSeries() {
            return timeSeries;
        }

        String getWord() {
            return word;
        }
    }
    private int sumCountTimeSeries(TimeSeries timeSeries) {
        double sum = 0;
        for(double data : timeSeries.data()){
            sum += data;
        }
        return (int) sum;
    }
    @Override
    public String handle(NgordnetQuery q){
        List<String> sameHyponyms = new ArrayList<>();
        if(q.ngordnetQueryType() == HYPONYMS){
            sameHyponyms = wordNet.hyponyms(q.words());
        }
        if(q.ngordnetQueryType() == ANCESTORS){
            sameHyponyms = wordNet.ancestors(q.words());
        }
        if(q.k() == 0){
            return sameHyponyms.toString();
        }
        PriorityQueue<TimeSeriesWithWord> pq = new PriorityQueue<TimeSeriesWithWord>(new TimeSeriesSumComparator());
        for(String hyponym : sameHyponyms){
            TimeSeries wordTimeSeries = ngramMap.countHistory(hyponym, q.startYear(), q.endYear());
            if(sumCountTimeSeries(wordTimeSeries) == 0){
                continue;
            }
            pq.add(new TimeSeriesWithWord(wordTimeSeries, hyponym));
            if(pq.size() > q.k()){
                pq.poll();
            }
        }
        List<String> popularHyponyms = new ArrayList<>();
        for(TimeSeriesWithWord timeSeriesWithWord : pq){
            popularHyponyms.add(timeSeriesWithWord.getWord());
        }
        sort(popularHyponyms);
        return popularHyponyms.toString();
    }


    public class TimeSeriesSumComparator implements Comparator<TimeSeriesWithWord>{


        @Override
        public int compare(TimeSeriesWithWord o1, TimeSeriesWithWord o2) {
            double sum1 = 0;
            for(double count : o1.getTimeSeries().data()){
                sum1 += count;
            }
            double sum2 = 0;
            for(double count : o2.getTimeSeries().data()){
                sum2 += count;
            }
            return (int)(sum1 - sum2);
        }

    }
}
