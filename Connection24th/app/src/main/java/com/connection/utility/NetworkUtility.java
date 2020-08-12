package com.connection.utility;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.List;

public class NetworkUtility {
    public static boolean isNetAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean checkLocationManager(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(context)) {
            return true;
        } else {
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(context)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private static boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }


    public static void gifCall(Context context, Integer gif, Integer img, final ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(gif)
                .placeholder(ResourcesCompat.getDrawable(context.getResources(), img, null))
                .fitCenter()
                .into(new ImageViewTarget<GifDrawable>(imageView) {
                    @Override
                    protected void setResource(@Nullable GifDrawable resource) {
                        imageView.setImageDrawable(resource);
                    }
                });

    }

}
