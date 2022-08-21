package sm.api.util;

public class GenericUtil {
	
	
	public static double getAmount(String value) {
		
		value=  value.toUpperCase().replace(" ", "");
		
		int sign= 1;
		int coeff=1;
		
		if(value.contains("-")) {
			sign =-1;
		}
		
		
		if (value.contains("K")) {
			coeff=coeff/1000;
			value=value.replace("K", "");
		}
		else if (value.contains("M")) {
			coeff=coeff* 1;
			value=value.replace("M", "");
		}
		else if (value.contains("B")) {
			coeff=coeff* 1000;
			value=value.replace("B", "");
		}
		
		
		return Double.parseDouble(value)*sign*coeff;

	}
	
}
