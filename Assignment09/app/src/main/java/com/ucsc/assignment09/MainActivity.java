package com.ucsc.assignment09;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the GraphView
        graphView = findViewById(R.id.graph);

        // Create a LineGraphSeries
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 5),
                new DataPoint(2, 5),
                new DataPoint(3, 0),
                new DataPoint(4, 2),
        });

        // Add series to graph
        graphView.addSeries(series);

        // Customize graph appearance if needed
        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(true);
        graphView.getGridLabelRenderer().setLabelVerticalWidth(100);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(5);
        graphView.getGridLabelRenderer().setNumVerticalLabels(4);
        graphView.getGridLabelRenderer().setPadding(50);

        graphView.getViewport().setXAxisBoundsManual(true); // Enable manual bounds for X axis
        graphView.getViewport().setMinX(0); // Minimum X value
        graphView.getViewport().setMaxX(4); // Maximum X value
        graphView.getViewport().setYAxisBoundsManual(true); // Enable manual bounds for Y axis
        graphView.getViewport().setMinY(0); // Minimum Y value
        graphView.getViewport().setMaxY(8); // Maximum Y value

        GridLabelRenderer gridLabelRenderer = graphView.getGridLabelRenderer();

        // Enable horizontal grid lines
        gridLabelRenderer.setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        gridLabelRenderer.setHorizontalLabelsVisible(true);
        gridLabelRenderer.setVerticalLabelsVisible(true);
        // Set grid line color and style
        gridLabelRenderer.setGridColor(Color.LTGRAY); // Color of grid lines

    }
}
