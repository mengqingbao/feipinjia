package com.feipinjia.service.start;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//@SuppressLint("NewApi")
public class InSmsService extends Service {
	private static final String TAG="Test";  
    
    @Override 
    //Serviceʱ������  
    public void onCreate()  
    {  
        Log.i(TAG, "Service onCreate--->");  
        super.onCreate();  
    }  
 
    @Override 
    //��������ʹ��startService()��������Serviceʱ���÷���������  
    public void onStart(Intent intent, int startId)  
    {  
        Log.i(TAG, "Service onStart--->");  
        super.onStart(intent, startId);  
    }  
 
    @Override 
    //��Service����ʹ��ʱ����  
    public void onDestroy()  
    {  
        Log.i(TAG, "Service onDestroy--->");  
        super.onDestroy();  
    }  
 
    @Override 
    //��ʹ��startService()��������Serviceʱ����������ֻ��дreturn null  
    public IBinder onBind(Intent intent)  
    {  
        return null;  
    }  
}
