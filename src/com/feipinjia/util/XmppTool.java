package com.feipinjia.util;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import android.util.Log;



public class XmppTool {

	private static XMPPConnection con = null;
	
	private static void openConnection() {
		try {
			//服务器连接地址
			ConnectionConfiguration connConfig = new ConnectionConfiguration("192.168.1.101", 5222);

			con = new XMPPConnection(connConfig);
			con.connect();
			con.addConnectionListener(new ConnectionListener() {
	            @Override
				public void connectionClosed() {
	                
	            	System.out.println("连接关闭");
	            }

	            @Override
				public void connectionClosedOnError(Exception e) {
	            	System.out.println("关闭连接异常");
	            }

	            @Override
				public void reconnectingIn(int seconds) {
	            	System.out.println("重新连接时间"+seconds);
	            }

	            @Override
				public void reconnectionFailed(Exception e) {
	            	System.out.println("重新连接异常");
	            }

	            @Override
				public void reconnectionSuccessful() {
	            	System.out.println("重新连接成功");
	            }
	        });
			


			
			
		}
		catch (XMPPException xe) 
		{
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (con == null) {
			openConnection();
		}
		return con;
	}

	public static void closeConnection() {
		if (con != null){
			con.disconnect();
			con = null;
		}
	}

	public static void addPacketListener(PacketListener myListener,
			OrFilter allPF) {
		con.addPacketListener(myListener, allPF);
	}
}
