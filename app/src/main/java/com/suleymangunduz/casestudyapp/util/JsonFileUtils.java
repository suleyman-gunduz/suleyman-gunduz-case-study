package com.suleymangunduz.casestudyapp.util;

import android.content.Context;

import java.io.InputStream;
import java.util.Scanner;

import androidx.annotation.RawRes;

public class JsonFileUtils {

    public static String readRawResource(Context context, @RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    private static String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
