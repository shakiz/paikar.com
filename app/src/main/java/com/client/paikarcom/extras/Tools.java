package com.client.paikarcom.extras;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.client.paikarcom.R;
import com.client.paikarcom.activities.home.HomeActivity;

public class Tools {
    private Context context;
    public Dialog orderConfirmationDialog;

    public Tools(Context context) {
        this.context = context;
        orderConfirmationDialog = new Dialog(context);
    }

    //region exit app
    public void exitApp() {
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(exitIntent);
    }
    //endregion

    //region order confirmation dialog
    public void getOrderConfirmationDialog(){
        orderConfirmationDialog.setContentView(R.layout.dialog_layout_order_confirmation);
        orderConfirmationDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        orderConfirmationDialog.getWindow().setDimAmount(0.6f);
        if (orderConfirmationDialog != null) {
            orderConfirmationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        orderConfirmationDialog.setCanceledOnTouchOutside(false);
        orderConfirmationDialog.show();

        Button goHomButton = orderConfirmationDialog.findViewById(R.id.backToHomeButton);
        goHomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBackClick != null){
                    onBackClick.onBackClick();
                }
            }
        });
    }

    public void closeOrderConfirmationDialog(){
        if (orderConfirmationDialog.isShowing()) orderConfirmationDialog.cancel();
    }

    public interface onBackClick{
        void onBackClick();
    }
    private onBackClick onBackClick;

    public void setOnBackClick(onBackClick onBackClick) {
        this.onBackClick = onBackClick;
    }
    //endregion

}
