package com.connection.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.connection.R;

public class Next_Back_Screen {
    public static void newScreen(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


    }

    public static void backScreen(Context context) {
        ((Activity) (context)).finish();
        //   ((Activity)(context)).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
        //  ((Activity)(context)).finish();
    }
}
