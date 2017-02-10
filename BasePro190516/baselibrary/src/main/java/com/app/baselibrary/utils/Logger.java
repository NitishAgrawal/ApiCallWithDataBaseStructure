package com.app.baselibrary.utils;

import android.util.Log;

import com.app.baselibrary.base.BaseApp;
import com.app.baselibrary.base.BaseStru;

public class Logger {

	//Tag used to identify the log of our App
	private static final String PACKAGE = BaseApp.getAppContext().getPackageName().toString();
	private static final String TAG = PACKAGE.substring(PACKAGE.lastIndexOf(".")+1);

	//Print informative sentence in green
	public static final int INFO = 1;
	//Print Debug sentence in blue
	public static final int DEBUG = 2;
	//Print error sentence in red
	public static final int ERROR = 3;
	//Print warning sentence in orange
	public static final int WARNING = 4;
	//Print the verbose sentence in black
	public static final int VERBOSE = 5;
	
	
	/**
	 * print the logs depending upon the types.
	 * @param type
	 */
	public static void printLog(int type,String message){
		 
		if(BaseStru.isLogger()){
			
			switch(type){
			case INFO://Information Statement
				printInfo(message);
				break;
			case DEBUG://Debug Statement
				printDebugg(message);
				break;
			case ERROR://Error Statement
				printError(message);
				break;
			case WARNING://Warning Statement
				printWarning(message);
				break;
			case VERBOSE://Verbose Statement
				printVerbose(message);
				break;
			}
		}
		
	}
	
	/**
	 * Print the informative sentences.
	 * @param message
	 */
	private static void printInfo(String message){
		Log.i(TAG, message);
	}
	
	/**
	 * Show the debugg statement.
	 * @param message
	 */
	private static void printDebugg(String message){
		Log.d(TAG, message);
	}
	
	/**
	 * Show the error/exceptions statement.
	 * @param message
	 */
	private static void printError(String message){
		Log.e(TAG, message);
	}
	
	/**
	 * print the warning statement.
	 * @param message
	 */
	private static void printWarning(String message){
		Log.w(TAG, message);
	}
	
	/**
	 * Prin the verbose sentences in black.
	 * @param message
	 */
	private static void printVerbose(String message){
		Log.v(TAG, message);
	}
}
