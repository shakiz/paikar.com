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

    public interface onSubmitOrderClick{
        void onSubmitClick();
    }

    private onSubmitOrderClick onSubmitOrderClick = null;

    public void setOnSubmitOrderClick(onSubmitOrderClick onSubmitOrderClick) {
        this.onSubmitOrderClick = onSubmitOrderClick;
    }

    public void onOrderPlacement(Button placeOrderButton, int noOfProducts, double amount, double deliveryFee) {
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder(noOfProducts, amount, deliveryFee);
            }
        });
    }

    private void submitOrder(int noOfProducts, double amount, double deliveryFee){
        //region open and close bottom sheet and other operations
        View mView = View.inflate(context, R.layout.dialog_submit_order, null);
        final Dialog mBottomSheetDialog = new Dialog(context, R.style.MaterialDialogSheet);

        //region UI views
        Button submitOrderButton = mView.findViewById(R.id.submitOrderButton);
        EditText CouponCode = mView.findViewById(R.id.CouponCode);
        TextView applyCoupon = mView.findViewById(R.id.applyCoupon);
        TextView TotalAmount = mView.findViewById(R.id.TotalAmount);
        TextView DeliveryFee = mView.findViewById(R.id.DeliveryFee);
        TextView NoOfProducts = mView.findViewById(R.id.NoOfProducts);
        TextView TotalAmountAfterCouponAndDelivery = mView.findViewById(R.id.TotalAmountAfterCouponAndDelivery);
        //endregion

        //region set data to UI
        TotalAmount.setText(String.valueOf(amount));
        NoOfProducts.setText("("+noOfProducts+" Products)");
        DeliveryFee.setText(String.valueOf(deliveryFee));
        TotalAmountAfterCouponAndDelivery.setText(String.valueOf(amount + deliveryFee));
        //endregion

        applyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(CouponCode.getText().toString())){
                    //TODO dummy coupon code now
                    TotalAmountAfterCouponAndDelivery.setText(String.valueOf(amount + deliveryFee - 20));
                }
                else{
                    Toast.makeText(context, context.getString(R.string.please_enter_coupon_code), Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetDialog.isShowing()){
                    mBottomSheetDialog.dismiss();
                }
                if (onSubmitOrderClick != null){
                    onSubmitOrderClick.onSubmitClick();
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
                if (mBottomSheetDialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        //endregion

        mBottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //endregion
    }
}
