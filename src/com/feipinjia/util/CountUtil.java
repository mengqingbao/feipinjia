package com.feipinjia.util;

import java.text.DecimalFormat;

import com.feipinjia.model.Doctor;

public class CountUtil {
	public static String doctorWCommentRate(Doctor doctor) {
		int totalw = getWTotal(doctor);
		String record = "";
		if (totalw > 0) {
			DecimalFormat fmt = new DecimalFormat(".#%");
			float rate = (float) doctor.getGood() / (float) totalw;
			return fmt.format(rate);
		}
		return record;
	}
	
	public static String doctorZCommentRate(Doctor doctor){
		int totalz=	getZTotal(doctor);
		if(totalz>0){
			DecimalFormat fmt = new DecimalFormat(".#%");
			 float rate=(float)doctor.getZgood()/(float)totalz;
			return fmt.format(rate);
			}
		return null;
	}
	
	public static int getWTotal(Doctor doctor){
		return doctor.getGood() + doctor.getNormal() + doctor.getBad();
	}
	
	public static int getZTotal(Doctor doctor){
		return doctor.getZgood()+doctor.getZnormal()+doctor.getZbad();
	}
}
