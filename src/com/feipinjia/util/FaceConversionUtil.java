package com.feipinjia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.feipinjia.model.InSmiley;
import com.feipinjia.activity.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;

public class FaceConversionUtil {
	
	private static FaceConversionUtil mFaceConversionUtil;
	private FaceConversionUtil() {

	}

	public static FaceConversionUtil getInstace() {
		if (mFaceConversionUtil == null) {
			mFaceConversionUtil = new FaceConversionUtil();
		}
		return mFaceConversionUtil;
	}


	
	public List<InSmiley> parseData(int start,int end, Context context) {
		List<InSmiley> list =new ArrayList<InSmiley>();
		try {
			for (int i=start;i<end;i++) {
				String fileName="smiley_"+i;
				int resID = context.getResources().getIdentifier(fileName,
						"drawable", context.getPackageName());
				if (resID != 0) {
					InSmiley smileyEntry= new InSmiley();
					smileyEntry.setId(resID);
					smileyEntry.setCharacter("["+fileName+"]");
					smileyEntry.setFaceName(fileName);
					list.add(smileyEntry);
				}
			}
			InSmiley smileyEntry= new InSmiley();
			smileyEntry.setId(R.drawable.face_del_icon);
			list.add(smileyEntry);
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	public SpannableString addFace(Context context, int imgId,
			String spannableString) {
		if (TextUtils.isEmpty(spannableString)) {
			return null;
		}
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				imgId);
		bitmap = Bitmap.createScaledBitmap(bitmap, 35, 35, true);
		ImageSpan imageSpan = new ImageSpan(context, bitmap);
		SpannableString spannable = new SpannableString(spannableString);
		spannable.setSpan(imageSpan, 0, spannableString.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannable;
	}
	
	public SpannableString getExpressionString(Context context, String str) {
		SpannableString spannableString = new SpannableString(str);
		System.out.println(str+" here is getExpressionString");
		String zhengze = "\\[[^\\]]+\\]";
		Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);
		try {
			dealExpression(context, spannableString, sinaPatten, 0);
		} catch (Exception e) {
			Log.e("dealExpression", e.getMessage());
		}
		return spannableString;
	}
	
	private void dealExpression(Context context,
			SpannableString spannableString, Pattern patten, int start)
			throws Exception {
		Matcher matcher = patten.matcher(spannableString);
		while (matcher.find()) {
			String key = matcher.group();
			key=key.replace("[", "").replace("]", "");
			System.out.println(key+" key:");
			if (matcher.start() < start) {
				continue;
			}
			if (TextUtils.isEmpty(key)) {
				continue;
			}
			int resId = context.getResources().getIdentifier(key, "drawable",
					context.getPackageName());
			// Field field=R.drawable.class.getDeclaredField(value);
			// int resId=Integer.parseInt(field.get(null).toString());
			if (resId != 0) {
				Bitmap bitmap = BitmapFactory.decodeResource(
						context.getResources(), resId);
				bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
				ImageSpan imageSpan = new ImageSpan(bitmap);
				int end = matcher.start() + key.length()+2;
				spannableString.setSpan(imageSpan, matcher.start(), end,
						Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				if (end < spannableString.length()) {
					dealExpression(context, spannableString, patten, end);
				}
				break;
			}
		}
	}

}