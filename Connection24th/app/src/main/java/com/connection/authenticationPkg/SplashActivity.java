package com.connection.authenticationPkg;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.connection.MainActivity;
import com.connection.R;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends BasicActivity {

    private String loginEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loginEntry = AppSession.getStringPreferences(getApplicationContext(), Constants.STATUS);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginEntry == null || loginEntry.isEmpty()) {
                    Intent i2 = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i2);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                } else {
                    Intent intentHome = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }


                /*    case "Usersign":
                        Intent i1 = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        finish();
                        break;
                    case "driversign":
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        finish();
                        break;
                    case "logout":
                        Intent i3 = new Intent(SplashActivity.this, HomeActivity.class);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        startActivity(i3);
                        finish();
                        break;*/


            }
        }, 2000);
    }

    private void gethash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
