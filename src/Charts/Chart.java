package Charts;
import java.io.File;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import javax.swing.*;

public class Chart extends ApplicationFrame {
    // https://github.com/jfree/jfree-demos/blob/master/src/main/java/org/jfree/chart/demo2/TimeSeriesChartDemo1.java
    // https://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset
    public Chart(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);
    }

    private static XYDataset createPerformanceDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries bubbleSeries = new XYSeries("Bubble Sort");
        XYSeries insertionSeries = new XYSeries("Insertion Sort");
        XYSeries selectionSeries = new XYSeries("Selection Sort");
        XYSeries shellSeries = new XYSeries("Shell Sort");
        XYSeries quickSeries = new XYSeries("Merge Sort");
        XYSeries mergeSeries = new XYSeries("Quick Sort");

        XYSeries[] seriesArr = {bubbleSeries, insertionSeries, selectionSeries,
                                shellSeries, quickSeries, mergeSeries};
        int[] sizeArr = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};

        File file = new File("src/Charts/kSortedPerformanceNumsOnly.txt");
        //File file = new File("src/Charts/performanceNumsOnly.txt");
        try (Scanner scan = new Scanner(file)) {
            for( XYSeries series : seriesArr) {
                for(int size : sizeArr) {
                    series.add(size, scan.nextDouble());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        dataset.addSeries(bubbleSeries);
        dataset.addSeries(insertionSeries);
        dataset.addSeries(selectionSeries);
        dataset.addSeries(shellSeries);
        dataset.addSeries(quickSeries);
        dataset.addSeries(mergeSeries);

        return dataset;
    }
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Sorting Algorithm Analysis",  // title
                "Input Size (N)",             // x-axis label
                "Average Time (ms)",   // y-axis label
                dataset);

        chart.setBackgroundPaint(Color.WHITE);

        XYPlot plot = (XYPlot) chart.getPlot();
        // https://stackoverflow.com/questions/14054091/jfreechart-x-axis-scale
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Input Size (N)");
        TickUnits tickUnits = new TickUnits();
        tickUnits.add(new NumberTickUnit(100));
        tickUnits.add(new NumberTickUnit(500));
        tickUnits.add(new NumberTickUnit(1000));
        tickUnits.add(new NumberTickUnit(2000));
        tickUnits.add(new NumberTickUnit(5000));
        tickUnits.add(new NumberTickUnit(10000));
        tickUnits.add(new NumberTickUnit(20000));
        tickUnits.add(new NumberTickUnit(75000));
        tickUnits.add(new NumberTickUnit(150000));

        xAxis.setStandardTickUnits(tickUnits);
        plot.setDomainAxis(xAxis);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        return chart;
    }

    public static JPanel createDemoPanel() {
        XYDataset dataset = createPerformanceDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        Chart demo = new Chart(
                "Sorting Analysis");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

