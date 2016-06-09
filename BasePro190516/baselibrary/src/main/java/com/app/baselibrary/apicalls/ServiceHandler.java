package com.app.baselibrary.apicalls;

import android.util.Log;

import com.app.baselibrary.BaseStru;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;


public class ServiceHandler {

    static String response = null;

    public static enum RequestMethod{
        GET,POST,PUT,PATCH,DELETE;
    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */

    private String getURlFormPath(String path) {
        return BaseStru.getBaseUrl()+path;
    }

    public JSONObject makeServiceCall(ApiRequest apiRequest,
                                      Map<String, String> params) {
            String paramsStr = null;
            try {
                if(params != null)
                paramsStr = getQuery(params);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return makeServiceCallBase(apiRequest, paramsStr);
    }

    public JSONObject makeServiceCallBase(ApiRequest apiRequest,
                                  String paramsStr) {
        HttpURLConnection urlConnection = null;

        URL url = null;
        JSONObject object = null;
        InputStream inStream = null;
        String temp, response="";
        try {
            String urlStr = this.getURlFormPath((apiRequest.isAddMeddleUrl?BaseStru.getMeddleUrl():"")+apiRequest.path);
            if(apiRequest.method == RequestMethod.DELETE && paramsStr!= null){
                urlStr=urlStr+"?"+paramsStr;
            }
            url = new URL(urlStr);

            Log.e("URL is ", "" + this.getURlFormPath(apiRequest.path));
            Log.e("Method is ", "" + apiRequest.method);
            Log.e("parameter is ", "" + paramsStr);

            urlConnection = (HttpURLConnection) url.openConnection();

            if (apiRequest.method == RequestMethod.POST) {
                urlConnection.setRequestMethod("POST");
            } else if (apiRequest.method == RequestMethod.GET) {
                urlConnection.setRequestMethod("GET");
            } else if (apiRequest.method == RequestMethod.PUT) {
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
            } else if (apiRequest.method == RequestMethod.PATCH) {
                urlConnection.setRequestMethod("PATCH");
            } else if (apiRequest.method == RequestMethod.DELETE) {
                urlConnection.setDoInput(true);
                urlConnection.setInstanceFollowRedirects(false);
                if(apiRequest.authenticate && (BaseStru.getAuthToken() !=null && BaseStru.getAuthToken().toString().length()>1)) {
                    urlConnection.setRequestProperty(BaseStru.getAuthTokenKey(), BaseStru.getAuthToken());
                }
                urlConnection.setRequestProperty("Connection", "Keep-Alive");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestMethod("DELETE");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
            }

            if(apiRequest.authenticate && apiRequest.method != RequestMethod.DELETE && (BaseStru.getAuthToken() !=null && BaseStru.getAuthToken().toString().length()>1)) {
                urlConnection.setRequestProperty("auth_token", BaseStru.getAuthToken());
            }
            if(apiRequest.method != RequestMethod.DELETE && apiRequest.method != RequestMethod.GET) {
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                if(apiRequest.method == RequestMethod.PUT){
                    OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                    if(paramsStr != null &&  paramsStr.length()>0)
                    osw.write(paramsStr);
                    osw.flush();
                    osw.close();
                    System.err.println(urlConnection.getResponseCode());
                }else {
                    OutputStream os = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(paramsStr);
                    Log.e("paramsStr", "is " + paramsStr);
                    writer.flush();
                    writer.close();
                    os.close();
                    urlConnection.connect();
                }
            }
            try {
                int status = urlConnection.getResponseCode();
                Log.e("Print Response Code", "" + status);
                //if((status%100) != HttpStatus.SC_BAD_REQUEST)
                if((status/100) != 2)
                    inStream = urlConnection.getErrorStream();
                else
                    inStream = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                inStream = urlConnection.getErrorStream();
                if(inStream == null){
                    inStream = urlConnection.getInputStream();
                }
            }

            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }

            Log.e("Response is ", "" + response);
            object = (JSONObject) new JSONTokener(response).nextValue();

            //object= new JSONObject(getStringFromInputStream(inStream));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        try {
            Log.e("JsonObject", "" + object.toString());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return object;
    }

    public static String getQuery(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer requestParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
                requestParams.append("&");
            }
        }
        int lenght =requestParams.toString().length();
        if(lenght>0)
            return requestParams.toString().substring(0,(lenght-1));
        else
            return requestParams.toString();
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
