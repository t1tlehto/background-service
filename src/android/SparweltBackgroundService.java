package com.sparwelt.cordova.backgroundservice.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.util.Log;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;

import android.content.Context;


import me.leolin.shortcutbadger.ShortcutBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;



public class SparweltBackgroundService extends BackgroundService {
	
	private final static String TAG = SparweltBackgroundService.class.getSimpleName();
	
	private String mHelloTo = "spartwelt";

    private int badgeNumber = 0;


	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();
		
		try {



            badgeNumber++;
            Log.d(TAG, "badge number is " + badgeNumber);

            setBadge();



		} catch (Exception e) {
            e.printStackTrace();

        }
		
		return result;	
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();
		
		try {
			result.put("HelloTo", this.mHelloTo);
		} catch (JSONException e) {
		}
		
		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
			if (config.has("HelloTo"))
				this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}
		
	}     

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub
		
	}


    /**
     * sets badge icon
     */
    private void setBadge() {

        Context ctx = getApplicationContext();

        if (canBadgeAppIcon(ctx)) {
            ShortcutBadger.with(ctx).count(badgeNumber);
        }


    }
    /**
     * Finds out if badgeing the app icon is possible on that device.
     *
     * @param ctx
     * The application context.
     * @return
     * true if its supported.
     */
    private boolean canBadgeAppIcon (Context ctx) {
        ShortcutBadger badger = ShortcutBadger.with(ctx);

        return !(badger instanceof DefaultBadger);
    }
}
