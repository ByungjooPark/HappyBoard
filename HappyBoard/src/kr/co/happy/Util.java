package kr.co.happy;

public class Util {
	public static int chk_ZeroInteger(String str) {
		int in = 0;
		try {
			in = Integer.parseInt(str);
		} catch (Exception e) {
		}
		
		return in;
	}
	
	public static int chk_OneInteger(String str) {
		int in = 1;
		try {
			in = Integer.parseInt(str);
		} catch (Exception e) {
		}
		
		return in;
	}
	
}
