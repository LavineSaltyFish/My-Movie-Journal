package com.example.lemongrass;

import android.content.Intent;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.lemongrass.csvProcess.CsvRow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import com.example.lemongrass.csvProcess.CsvReader;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }

        /*
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
        */

        /*
        System.out.print("For-Each loop: ");
        for (final CsvRow csvRow : CsvReader.builder().build("foo,bar")) {
            System.out.println(csvRow.getFields());
        }
         */

        /*
        Path path = Paths.get("/Users/perchsen/Desktop/view_history.txt");
        Charset charset = Charset.forName("UTF-16");
        try (CsvReader csv = CsvReader.builder().build(Paths.get("/Users/perchsen/Downloads/view_history.txt"), StandardCharsets.UTF_8)) {
            csv.forEach(System.out::println);
        }
        catch (Exception ex) {
            System.out.println("We got unexpected: " + ex.getMessage());
            ex.printStackTrace();
            // System.out.println(path.toAbsolutePath());
        }
        */

        InputStream is = this.getResources().openRawResource(R.raw.view_history);
        Charset charset = Charset.forName("UTF-16");

        try {
            for (final CsvRow csvRow : CsvReader.builder().build(is, charset)) {
                System.out.println(csvRow.getField(3));
            }
        }
        catch (Exception ex) {
            System.out.println("We got unexpected: " + ex.getMessage());
            ex.printStackTrace();
            // System.out.println(path.toAbsolutePath());
        }

        /*
        try (CsvReader csv = CsvReader.builder().build(is, charset)) {
            csv.forEach(System.out::println);
        }
        catch (Exception ex) {
            System.out.println("We got unexpected: " + ex.getMessage());
            ex.printStackTrace();
            // System.out.println(path.toAbsolutePath());
        }
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}