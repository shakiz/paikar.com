package com.client.paikarcom.extras;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.client.paikarcom.R;

public class OrderPlacementManager {
    private Context context;

    public OrderPlacementManager(Context context) {
        this.context = context;
    }

    public interface submitOrderListener {
        void onCouponApply(double amount);
        void onSubmitClick();
    }

    public submitOrderListener submitOrderListener = null;

    public void setSubmitOrderListener(submitOrderListener submitOrderListener) {
        this.submitOrderListener = submitOrderListener;
    }

    public void initSearchPanel(Button placeOrderButton, int noOfProducts, double amount) {
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });
    }

    private void submitOrder(){
        //region open and close bottom sheet and other operations
        View mView = View.inflate(context, R.layout.dialog_submit_order, null);
        final Dialog mBottomSheetDialog = new Dialog(context, R.style.MaterialDialogSheet);

        EditText CouponCode = mView.findViewById(R.id.CouponCode);
        TextView applyCoupon = mView.findViewById(R.id.applyCoupon);

        applyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(CouponCode.getText().toString())){

                }
                else{
                    Toast.makeText(context, context.getString(R.string.please_enter_coupon_code), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBottomSheetDialog.setContentView(mView);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();

        //region close bottom sheet
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        //endregion

        mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //endregion
    }
}
