package com.a3vam.tiempociudad.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.a3vam.tiempociudad.helpers.ProgressDialogHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.a3vam.tiempociudad.Contanst;



public class BaseUtils {

    private Context mContext;

    private Dialog messageDialog;
    private ProgressDialogHelper mSlashProgressDialogHelper;

    public BaseUtils(Context context) {
        mContext = context;
    }

    /**
     * Muestra un progress dialog que puede ser cancelable o no
     * @param cancelable
     */
    public void showProgressDialog(boolean cancelable) {
        if (isActivityRunning()) {
            if (mSlashProgressDialogHelper == null) {
                mSlashProgressDialogHelper = new ProgressDialogHelper();
            }
            mSlashProgressDialogHelper.hideProgressDialog();
            mSlashProgressDialogHelper.showProgressDialog(mContext, cancelable);
        }
    }

    /**
     * Oculta el progress dialog si se está mostrando
     */
    public void hideProgressDialog() {
        if (isActivityRunning()) {
            if (mSlashProgressDialogHelper != null) {
                mSlashProgressDialogHelper.hideProgressDialog();
            }
        }
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     * @param title Set the title text for this dialog's window. Can be null.
     * @param message Message text to show.
     * @param cancelable Sets whether this dialog is cancelable with the BACK key.
     */
    public void showMessageDialog(String title, String message, boolean cancelable) {
        if (isActivityRunning()) {
            hideMessageDialog();

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setMessage(message);
            if (title != null) {
                dialogBuilder.setTitle(title);
            }
            dialogBuilder.setCancelable(cancelable);
            dialogBuilder.setPositiveButton(mContext.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            messageDialog = dialogBuilder.create();
            messageDialog.show();
        }
    }

    /**
     * Ocultar el mensaje
     */
    public void hideMessageDialog() {
        if (isActivityRunning()) {
            try {
                if (messageDialog != null && messageDialog.isShowing()) {
                    messageDialog.dismiss();
                    messageDialog = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Oculta el teclado del editText pasado por parámetro en caso de que esté abierto
     * @param editText
     */
    public void hideKeyboard(EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    private boolean isActivityRunning() {
        if (mContext != null) {
            return !(mContext instanceof Activity && ((Activity) mContext).isFinishing());
        }
        return true;
    }

    /**
     * Method to verify google play services on the device
     * */
    public boolean checkPlayServices(Context context) {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context,
                        Contanst.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(context,
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();

            }
            return false;
        }
        return true;
    }

}