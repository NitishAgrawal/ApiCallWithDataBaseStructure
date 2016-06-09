package com.app.baselibrary.apicalls;



public class ApiRequest {

    public String path;
    public ServiceHandler.RequestMethod method;
    public boolean authenticate;
    public boolean isShowProgressBar;
    public String reqID;
    public String pDialogMsg = "";
    public boolean isAddMeddleUrl;

    public ApiRequest(String path, String reqID) {
        this(path,ServiceHandler.RequestMethod.GET,true,true,reqID);
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method, String reqID) {
            this(path,method,true,true,reqID);
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method, String reqID, boolean isShowProgressBar) {
        this(path,method,true,isShowProgressBar,reqID);
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method, boolean authenticate, String reqID) {
        this(path,method, authenticate,true,reqID);
    }

    public ApiRequest(boolean isAddMeddleUrl, String path, ServiceHandler.RequestMethod method, String reqID) {
        this(path,method,true,true,reqID,"",isAddMeddleUrl);
    }
    public ApiRequest(String path, ServiceHandler.RequestMethod method, boolean authenticate, String reqID, boolean isAddMeddleUrl) {
        this(path,method, authenticate,true,reqID,"",isAddMeddleUrl);
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method,
                      boolean authenticate, boolean isShowProgressBar,
                      String reqID) {
        this(path,method, authenticate,isShowProgressBar,reqID,"");
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method,
                      boolean authenticate, boolean isShowProgressBar,
                       String reqID, String pDialogMsg
                      ) {
        this(path,method, authenticate,isShowProgressBar,reqID,pDialogMsg,true);
    }

    public ApiRequest(String path, ServiceHandler.RequestMethod method,
                      boolean authenticate, boolean isShowProgressBar,
                       String reqID, String pDialogMsg, boolean isAddMeddleUrl ) {
        this.path = path;
        this.method = method;
        this.authenticate = authenticate;
        this.isShowProgressBar = isShowProgressBar;
        this.reqID = reqID;
        this.pDialogMsg = pDialogMsg;
        this.isAddMeddleUrl = isAddMeddleUrl;
    }


}
