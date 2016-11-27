package com.a3vam.tiempociudad.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.a3vam.tiempociudad.utils.BaseUtils;


/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class FragmentBase extends Fragment {

    protected Context mContext;
    private BaseUtils mSlashBaseUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init base utils
        mSlashBaseUtils = new BaseUtils(mContext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    /**
     * Muestra un progress dialog que puede ser cancelable o no
     * @param cancelable
     */
    protected void showProgressDialog(boolean cancelable) {
        mSlashBaseUtils.showProgressDialog(cancelable);
    }

    /**
     * Oculta el progress dialog si se está mostrando
     */
    protected void hideProgressDialog() {
        mSlashBaseUtils.hideProgressDialog();
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     *
     * @param message
     */
    protected void showMessageDialog(String message) {
        mSlashBaseUtils.showMessageDialog(null, message, true);
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     *
     * @param message
     * @param cancelable
     */
    protected void showMessageDialog(String message, boolean cancelable) {
        mSlashBaseUtils.showMessageDialog(null, message, cancelable);
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     *
     * @param title
     * @param message
     * @param cancelable
     */
    protected void showMessageDialog(String title, String message, boolean cancelable) {
        mSlashBaseUtils.showMessageDialog(title, message, cancelable);
    }

    /**
     * Ocultar el mensaje
     */
    protected void hideMessageDialog() {
        mSlashBaseUtils.hideMessageDialog();
    }

    /**
     * Oculta el teclado del editText pasado por parámetro en caso de que esté abierto
     * @param editText
     */
    protected void hideKeyboard(EditText editText) {
        mSlashBaseUtils.hideKeyboard(editText);
    }

}