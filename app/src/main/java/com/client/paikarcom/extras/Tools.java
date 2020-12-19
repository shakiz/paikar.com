package com.client.paikarcom.extras;

import android.content.Context;
import android.content.Intent;

public class Tools {
    private Context context;

    public Tools(Context context) {
        this.context = context;
    }

    //region exit app
    public void exitApp() {
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(exitIntent);
    }
    //endregion
}
