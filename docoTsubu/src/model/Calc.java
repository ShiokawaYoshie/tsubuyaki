package model;

import java.io.UnsupportedEncodingException;

public class Calc {

	public boolean checkString (String pass) {
		boolean check = true;
		byte[] buf1;
		try {
			buf1 = pass.getBytes("SJIS");
			if(pass.length() != buf1.length){
				check = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return check;
	}

}
