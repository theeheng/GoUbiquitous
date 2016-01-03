package com.example.android.sunshine.app.wearable;

import android.content.Intent;
import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineAuthenticator;
import com.example.android.sunshine.app.sync.SunshineAuthenticatorService;
import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.MessageEvent;

public class ListenerService extends com.google.android.gms.wearable.WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.v("myTag", "Message received on phone  from "+messageEvent.getSourceNodeId()  );

        if (messageEvent.getPath().equals("/message_path")) {
            final String message = new String(messageEvent.getData());
            Log.v("myTag", "Message path received on phone is: " + messageEvent.getPath());
            Log.v("myTag", "Message received on phone is: " + message);

            startService(new Intent(SunshineSyncAdapter.ACTION_DATA_UPDATED)
                    .setClass(this, WeatherWearService.class));
        } else {
            super.onMessageReceived(messageEvent);
        }
    }
    }