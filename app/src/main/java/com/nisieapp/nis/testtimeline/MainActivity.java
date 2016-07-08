package com.nisieapp.nis.testtimeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String APP_KEY = "9F1108C7-03B0-E405-FFC4-FADA845C2800";
    private static final String SECRET_KEY = "BF53B182-3C9B-47C8-FF39-AF4B94D4B500";
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

        initBackendless();
        initData(savedInstanceState);

    }

    private void initBackendless() {
        String appVersion = "v1";
        Backendless.initApp(this, APP_KEY, SECRET_KEY, appVersion);
//        Timeline timeline = new Timeline();
//        timeline.setContext("Tes tes tes tes");
//        timeline.setUserId("1");
//        timeline.setType(111);
//
//        Backendless.Persistence.save(timeline, new AsyncCallback<Timeline>() {
//            @Override
//            public void handleResponse(Timeline timeline) {
//                Log.i("NISNIS", timeline.getUserId() + "has been saved");
//
//            }
//
//            @Override
//            public void handleFault(BackendlessFault backendlessFault) {
//
//            }
//        });

    }

    private void initData(Bundle savedInstanceState) {
        Backendless.Persistence.of(Timeline.class).find(new AsyncCallback<BackendlessCollection<Timeline>>() {
            @Override
            public void handleResponse(BackendlessCollection<Timeline> timelineBackendlessCollection) {
                Log.i("NISNIS",timelineBackendlessCollection.getCurrentPage().get(0).toString());
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
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
