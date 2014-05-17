package com.feipinjia.service.start;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InBootBroadcastReceiver extends BroadcastReceiver {

	@Override  
    public void onReceive(Context context, Intent intent) {  
        //��ߵ�XXX.class����Ҫ�����ķ���  
        Intent service = new Intent(context,InSmsServicebak.class);  
        context.startService(service);  
        Log.v("TAG", "�����Զ������Զ�����.....");  
       //����Ӧ�ã�����Ϊ��Ҫ�Զ�������Ӧ�õİ���
    }  

}
