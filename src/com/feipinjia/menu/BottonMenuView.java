package com.feipinjia.menu;

import com.feipinjia.activity.R;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class BottonMenuView {

	private PopupWindow popupWindow;
	private Context context;
	
	public BottonMenuView(Context cx){
		popupWindow=makePopupWindow(cx);
	}
	
	 // ����һ�����Զ���view��PopupWindow  
    private PopupWindow makePopupWindow(Context cx)  
    {  
        PopupWindow window;  
        window = new PopupWindow(cx);  
          
        //View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow, null);  
        //window.setContentView(contentView);  
        LinearLayout linearLayout = new LinearLayout(cx);  

        linearLayout.setOrientation(LinearLayout.VERTICAL);  
        
        window.setContentView(linearLayout);  
        window.setBackgroundDrawable(cx.getResources().getDrawable(R.drawable.layout_bg1));  
        window.setWidth(linearLayout.getWidth());  
        window.setHeight(150);  
          
        // ����PopupWindow�ⲿ�����Ƿ�ɴ���  
        window.setFocusable(true); //����PopupWindow�ɻ�ý���  
        window.setTouchable(true); //����PopupWindow�ɴ���  
        window.setOutsideTouchable(true); //���÷�PopupWindow����ɴ���  
        return window;  
    }
    
    public void show(View view){
    	popupWindow.showAtLocation(view.findViewById(R.id.in_chat_activity_layout), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 150, 340);
    }
}
