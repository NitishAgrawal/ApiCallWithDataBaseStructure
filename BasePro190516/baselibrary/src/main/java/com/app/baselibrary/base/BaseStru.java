package com.app.baselibrary.base;

import com.app.baselibrary.apicalls.ApiRequest;

import java.util.HashMap;
import java.util.Map;

public final class BaseStru {

    private static String baseUrl = "";
    private static String meddleUrl = "";
    private static String authToken = "";
    private static String databaseName = "DataBs.db";
    private static String authTokenKey = "auth-token";
    private static int databaseVersion = 1;
    private static HashMap<String,ApiRequest> apiReqList = new HashMap<String,ApiRequest>();
    //public static Map<String, HashMap<String,String>> dbSchema = new Map<String, HashMap<java.lang.String, java.lang.String>>()<String,String>>();
    public static Map<String, HashMap<String,String>> dbSchema = null;
    private static boolean isLogger= false;

    private static String sharedPreferencesName = "AppPreferences";
    private static String successKey = "success";
    private static String successValue = "true";
    private static String networkTitle = "Network is not available";
    private static String networkMessage = "No internet connectivity found";
    private static String networkOpenWifiBtn= "Open WIFI";
    private static String networkCancelBtn = "Cancel";
    private static boolean isShowNetworkDialog = true;

    public static boolean isShowNetworkDialog() {
        return isShowNetworkDialog;
    }

    public static void setIsShowNetworkDialog(boolean isShowNetworkDialog) {
        BaseStru.isShowNetworkDialog = isShowNetworkDialog;
    }

    public static String getNetworkTitle() {
        return networkTitle;
    }

    public static void setNetworkTitle(String networkTitle) {
        BaseStru.networkTitle = networkTitle;
    }

    public static String getNetworkMessage() {
        return networkMessage;
    }

    public static void setNetworkMessage(String networkMessage) {
        BaseStru.networkMessage = networkMessage;
    }

    public static String getNetworkOpenWifiBtn() {
        return networkOpenWifiBtn;
    }

    public static void setNetworkOpenWifiBtn(String networkOpenWifiBtn) {
        BaseStru.networkOpenWifiBtn = networkOpenWifiBtn;
    }

    public static String getNetworkCancelBtn() {
        return networkCancelBtn;
    }

    public static void setNetworkCancelBtn(String networkCancelBtn) {
        BaseStru.networkCancelBtn = networkCancelBtn;
    }

    public static String getSuccessValue() {
        return successValue;
    }

    public static void setSuccessValue(String successValue) {
        BaseStru.successValue = successValue;
    }

    public static String getSuccessKey() {
        return successKey;
    }

    public static void setSuccessKey(String successKey) {
        BaseStru.successKey = successKey;
    }

    public static String getSharedPreferencesName() {
        return sharedPreferencesName;
    }

    public static void setSharedPreferencesName(String sharedPreferencesName) {
        BaseStru.sharedPreferencesName = sharedPreferencesName;
    }

    private BaseStru() {}

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        BaseStru.baseUrl = baseUrl;
    }

    public static String getMeddleUrl() {
        return meddleUrl;
    }

    public static void setMeddleUrl(String meddleUrl) {
        BaseStru.meddleUrl = meddleUrl;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        BaseStru.authToken = authToken;
    }

    public HashMap<String, ApiRequest> getApiReqList() {
        return apiReqList;
    }

    public static void setApiReqList(HashMap<String, ApiRequest> apiReqList) {
        BaseStru.apiReqList = apiReqList;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName) {
        BaseStru.databaseName = databaseName;
    }

    public static int getDatabaseVersion() {
        return databaseVersion;
    }

    public static void setDatabaseVersion(int databaseVersion) {
        BaseStru.databaseVersion = databaseVersion;
    }

    public static Map<String, HashMap<String, String>> getDbSchema() {
        return dbSchema;
    }

    public static void setDbSchema(Map<String, HashMap<String, String>> dbSchema) {
        BaseStru.dbSchema = dbSchema;
    }

    public static boolean isLogger() {
        return isLogger;
    }

    public static void setIsLogger(boolean isLogger) {
        BaseStru.isLogger = isLogger;
    }

    public static String getAuthTokenKey() {
        return authTokenKey;
    }

    public static void setAuthTokenKey(String authTokenKey) {
        BaseStru.authTokenKey = authTokenKey;
    }

    // public static final String AUTH_TOKEN = "auth_token";
    //TODO not in use now use Strings
  /*  public static enum ApiNames{
        login;
    }
    //TODO
    public static final ApiRequest login = new ApiRequest("https://rondogo.herokuapp.com/api/v1/sub_categories", ServiceHandler.RequestMethod.GET,
                    false,true,false,false, ApiNames.login);*/


 /* public static final Map<String, HashMap<String,String>> dbSchema = Collections.unmodifiableMap(
        new HashMap<String, HashMap<String,String>>() {{
            //TODO
            //put(SubModel.TABLE_NAME, SubModel.getTableScheme());
            put(SubModel.getInstance().getTableName(), SubModel.getInstance().getAllDBFileds());
        }});*/



}
