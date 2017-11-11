package com.example.kapillamba4.theopensourcemovieapp.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.example.kapillamba4.theopensourcemovieapp.Activities.MainActivity.dialog;

/**
 * Created by kapil on 21/10/17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    private NetworkChangeReceiver mInternetConnectionBroadcastReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                dialog(true);
//                Log.e("Internet Connection:", "Online Connect Intenet ");
            } else {
                dialog(false);
//                Log.e("Internet Connection", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
