package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.*;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap ngramMap;

    public HistoryHandler(NGramMap map) {
        ngramMap = map;
    }

    @Override
    public String handle(NgordnetQuery q){
        List<String> labels = q.words();
        List<TimeSeries> lts = new ArrayList<>();
        for (String label : labels) {
            lts.add(ngramMap.weightHistory(label, q.startYear(), q.endYear()));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodeImage = Plotter.encodeChartAsString(chart);

        return encodeImage;
    }
}
