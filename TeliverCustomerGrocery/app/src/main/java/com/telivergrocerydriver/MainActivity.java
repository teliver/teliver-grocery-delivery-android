package com.telivergrocerydriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.teliver.sdk.core.TLog;
import com.teliver.sdk.core.Teliver;
import com.teliver.sdk.core.TrackingListener;
import com.teliver.sdk.models.MarkerOption;
import com.teliver.sdk.models.TLocation;
import com.teliver.sdk.models.TrackingBuilder;
import com.teliver.sdk.models.UserBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> listProducts;

    private String userName = "grocery_customer";

    private Application application;

    private Button btnTrackOrder;

    private String trackingId = "TELIVERTRK_567";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TLog.setVisible(true);
        Teliver.identifyUser(new UserBuilder(userName).setUserType(UserBuilder.USER_TYPE.CONSUMER).registerPush().build());
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("tripId"));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Drawable drawable = toolbar.getNavigationIcon();
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        application = (Application) getApplicationContext();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnTrackOrder = (Button) findViewById(R.id.btnTrackOrder);
        AdapterProducts adapterProducts = new AdapterProducts(this);
        listProducts = new ArrayList<>();
        listProducts.add(new Model(R.drawable.ic_dettol, "SUGAR 500 ML", "₹ 129.94", "₹ 123.44"));
        listProducts.add(new Model(R.drawable.ic_buds, "AASHIRVAD ATTA BROKEN WHEAT 1KG", "₹ 45", "₹ 42.75"));
        listProducts.add(new Model(R.drawable.ic_cotton, "MOONG DAL 125GMS", "₹ 105", "₹ 95"));
        adapterProducts.setData(listProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterProducts);

        btnTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teliver.startTracking(new TrackingBuilder(new MarkerOption(trackingId)).withListener(new TrackingListener() {
                    @Override
                    public void onTrackingStarted(String trackingId) {
                        Log.d("TELIVER::", "onTrackingStarted: " + trackingId);
                    }

                    @Override
                    public void onLocationUpdate(String trackingId, TLocation location) {
                        Log.d("TELIVER::", "onLocationUpdate: " + location.getLatitude() + location.getLongitude());
                    }

                    @Override
                    public void onTrackingEnded(String trackingId) {
                        Log.d("TELIVER::", "onTrackingEnded: " + trackingId);
                        application.storeBooleanInPref("IN_CURRENT_TRIP", false);
                        btnTrackOrder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorHint));
                        btnTrackOrder.setEnabled(false);
                    }

                    @Override
                    public void onTrackingError(String reason) {
                        Log.d("TELIVER::", "onTrackingError: " + reason);

                    }
                }).build());
            }
        });
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String payLoad = intent.getStringExtra("payload");
            if (payLoad.equalsIgnoreCase("1")) {
                btnTrackOrder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                btnTrackOrder.setEnabled(true);
                application.storeBooleanInPref("IN_CURRENT_TRIP", true);
            } else if (payLoad.equalsIgnoreCase("0")) {
                btnTrackOrder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorHint));
                btnTrackOrder.setEnabled(false);
                application.storeBooleanInPref("IN_CURRENT_TRIP", false);
                application.deletePreference();
                finish();
            }
        }
    };

    @Override
    protected void onResume() {
        if (application.getBooleanInPef("IN_CURRENT_TRIP")) {
            btnTrackOrder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
            btnTrackOrder.setEnabled(true);
        }
        super.onResume();
    }
}
