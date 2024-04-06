package com.spring.chatapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtils {


    public static String  getTime( ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String   currentDateTime = dateFormat.format(Calendar.getInstance().getTime());
        return currentDateTime;
    }


}
