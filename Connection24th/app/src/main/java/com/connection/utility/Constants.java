package com.connection.utility;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Constants {

    public static final String Initiate_Fragment = "InitiateFragment";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String TOKEN = "token";
    public static final String OTP = "otp";
    public static final String USER_TYPE = "user_type";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String DRIVER_REGISTRATION = "driver_registration";
    public static String STATUS = "preference_data";
    public static String PREF_NAME = "preference_data";
    public static String PREF_LANG = "current_language";
    public static String RIDES_SEARCH_FRAGMENT = "RidesSarchFragment";
    public static String OPTIONS_BASE_FRAGMENT = "OptionsBaseFragment";
    public static String TIFFIN_CENTER_FRAGMENT = "TiffinCenterFragment";
    public static String HOLY_PLACE_FRAGMENT = "HolyPlacesFragment";
    public static String FESTIVAL_AND_EVENTS_FRAGMENT = "FestivalAndEventsFragment";
    public static String INDIAN_STORE_FRAGMENT = "IndiansStoresFragment";
    public static String VIDEOS_FRAGMENT = "VideosFragment";
    public static String ABOUT_US_FRAGMENT = "AboutUsFragment";
    public static String SETTING_FRAGMENT = "SettingFragment";
    public static String HOME_FRAGMENT = "HomeFragment";
    public static String NOTIFICATION_FRAGMENT = "NotificationFragment";
    public static String HOME_FRAGMENT_DRIVER = "HomeFragmentDriver";
    public static String RIDE_FRAGMENT = "Ridefragment";
    public static String PASSANGER_FRAGMENT = "PassangerFragment";
    public static String RIDE_LIST = "ride_list";

    public static final void customToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

 /*   public static void customSnackbar(String msg, Context context, View view) {
        TSnackbar snackbar = TSnackbar.make(view, msg, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.BLACK);
        View snackbarView = snackbar.getView();
        ViewGroup.LayoutParams params = snackbarView.getLayoutParams();
        params.height = 100;
        snackbarView.setLayoutParams(params);
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.snackColor));
        TextView mTextView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        mTextView.setGravity(Gravity.CENTER);
        snackbar.show();
    }*/


    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidEmail(String target) {
        boolean flag;
        if (TextUtils.isEmpty(target)) {
            return true;
        } else {
            flag = emailValidator(target);
            if (flag) {
                // return flag;
            }
            return flag;
        }
        // return (!TextUtils.isEmpty(target) || Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean vehicleNumberValidator(String number) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[A-Za-z]{2}[ -][0-9]{1,2}(?: [A-Za-z])?(?: [A-Za-z]*)? [0-9]{4}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isValidVehicleNumber(String target) {
        boolean flag;
        if (TextUtils.isEmpty(target)) {
            return true;
        } else {
            flag = vehicleNumberValidator(target);
            if (flag) {
                // return flag;
            }
            return flag;
        }
        // return (!TextUtils.isEmpty(target) || Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public static boolean vehicleLicense(String number) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[0-9a-zA-Z]{4,9}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static String currentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    public static String chatTimeFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "EEE, hh:mm";

        //2020-02-21

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
