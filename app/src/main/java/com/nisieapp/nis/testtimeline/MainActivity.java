package com.nisieapp.nis.testtimeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kumulos.android.jsonclient.Kumulos;
import com.kumulos.android.jsonclient.ResponseHandler;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "5mjwzz6yxxq8x31fyqmzrr8bfvnvgkxq";
    private static final String SECRET_KEY = "rg6yrkb9";
    private static final String GET_TIMELINE = "get_timeline";
    RecyclerView mainList;
    TimelineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainList = (RecyclerView) findViewById(R.id.mainList);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TimelineAdapter();
        mainList.setAdapter(adapter);

        initKumulos();
        initData(savedInstanceState);

    }

    private void initData(Bundle savedInstanceState) {
        HashMap params = new HashMap();
        params.put("UserID", "1000");

        Kumulos.call(GET_TIMELINE, params, new ResponseHandler() {
            @Override
            public void didCompleteWithResult(Object result) {
                Log.d("NISNIS", result.toString());
            }
        });
    }

    private void initKumulos() {
        Kumulos.initWithAPIKeyAndSecretKey(API_KEY, SECRET_KEY, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
