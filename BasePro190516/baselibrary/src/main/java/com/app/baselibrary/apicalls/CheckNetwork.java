package com.app.baselibrary.apicalls;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import com.app.baselibrary.BaseStru;

public class CheckNetwork {

	private static boolean isNetwokAvailable = false;

	/**
	 * check wheather network available or not either wi-fi of mobile network
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isNetworkAvailable(Context mContext) {
		ConnectivityManager connectionManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Network[] wifiNetworkes= connectionManager.getAllNetworks();
            if(wifiNetworkes.length > 0) {
                for (Network network : wifiNetworkes) {
                    NetworkInfo wifiInfo = connectionManager.getNetworkInfo(network);
                    if(!isNetwokAvailable){
                        if ((wifiInfo != null && wifiInfo.isConnected())){
                            isNetwokAvailable = true;
                        }
                    }
                }
            }
        }else {
            NetworkInfo wifiInfo = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
                isNetwokAvailable = true;
            }
        }
        if(!isNetwokAvailable && BaseStru.isShowNetworkDialog()){
            ShowDialog(mContext);
        }
		Log.e("isNetworkAvailable: " , ""+isNetwokAvailable);
		return isNetwokAvailable;
	}

    public static void ShowDialog(final Context mContext){
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
        // Setting Dialog Title
        mAlertDialog.setTitle(BaseStru.getNetworkTitle())
                .setMessage(BaseStru.getNetworkMessage())
                .setPositiveButton(BaseStru.getNetworkOpenWifiBtn(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                        mContext.startActivity(intent);
                    }
                })
                /*.setNeutralButton("Open DataConnection", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                        context.startActivity(intent);
                    }
                })*/
                .setNegativeButton(BaseStru.getNetworkCancelBtn(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

}
