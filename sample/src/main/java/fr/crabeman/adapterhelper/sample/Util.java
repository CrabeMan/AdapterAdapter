package fr.crabeman.adapterhelper.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

public class Util {

    public static void logAndToast(@NonNull Context context, String message) {
        Log.d("AdapterHelper-Sample", message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
