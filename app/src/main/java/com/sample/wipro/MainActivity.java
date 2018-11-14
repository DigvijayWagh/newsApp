package com.sample.wipro;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout;
        RecyclerView recyclerView;
        // ArrayList for person names
        ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 8", "Person 9", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));
        ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person6, R.drawable.person7, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person6, R.drawable.person7));

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // init SwipeRefreshLayout and ListView
            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
            // get the reference of RecyclerView
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            // set a LinearLayoutManager with default vertical orientation
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            // call the constructor of CustomAdapter to send the reference and data to Adapter
            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, personImages);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
            // implement setOnRefreshListener event on SwipeRefreshLayout
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // cancel the Visual indication of a refresh
                    swipeRefreshLayout.setRefreshing(false);
                    shuffleItems();
                }
            });
        }

        public void shuffleItems() {
            // shuffle the ArrayList's items and set the adapter
            Collections.shuffle(personNames, new Random(System.currentTimeMillis()));
            Collections.shuffle(personImages, new Random(System.currentTimeMillis()));
            // call the constructor of CustomAdapter to send the reference and data to Adapter
            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, personImages);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        }
    }
}
