package random;

public class Rand {
	
	public static void main(String[] args) {
		System.out.println(validWord("abcdefgb"));
		System.out.println(okNotOk(10, 2));
		yourWord("Bird");
		
		System.out.println(salePrice("socks", 50, true));
	}
	
	public static boolean validWord(String word) {
		boolean bool = true;
		if (!word.startsWith("A") || word.length() < 6 || !word.endsWith("b")) {
		     bool = false;
		}
		return bool;
	}
	
	public static String okNotOk(int num1, int num2) {
		String str;
		
		if (num2 != 0 && num1 > 10 && num1/num2 > 5)
			str = "Ok";
		else
			str = "Not Ok";
		
		return str;
	}
	
	public static void yourWord(String word) {
		String ending;
		if (word.endsWith("ish"))
			ending = "-ishness";
		else if (word.endsWith("at"))
			ending = "-atat";
		else if (word.endsWith("ness"))
			ending = "-bess";
		else
			ending = "";
		
		String message = "Your word was ";
		message += word;
		message += ". ";
		
		word += ending;
		message += word;
		
		message += " is length ";
		message += word.length() + ". ";
		
		message += "All done!";
		
		System.out.println(message);
	}
	
	public static boolean closeEnoughFractions(int numerator1, int denominator1, int numerator2, int denominator2, double fudgeFactor) {
	     if (denominator1 ==0 || denominator2 == 0) {
	          return false;
	     }

	     int fraction1 = numerator1/denominator1;
	     int fraction2 = numerator2/denominator2;

	     if (Math.abs(fraction1 - fraction2) < fudgeFactor) {
	          return true;
	     }
	     
	     return false;
	}
	
	public static double salePrice(String item, double price, boolean coupon) {  
		   double sale = .25;
		   double specialSale = .5;
		   double over50sale = .35;

		   double discountPrice;
		   double amountSaved;

		   String beginning = "Your item, " + item + ", usually  costs " + price + " ,but ";
		   String ending = "you are getting it for ";
		   String priceMessage = "";

		   if (item.equals("socks") && coupon) {
		      priceMessage += "since you have a coupon, " ;

		      discountPrice = price * (1 - specialSale);
		      amountSaved = price * specialSale;
		  } 
		  else if (price > 50) {
		      priceMessage += "since it is over 50, " ;

		      discountPrice = price * (1 - over50sale);
		      amountSaved = price * over50sale;
		  }
		  else {
		      discountPrice = price * (1 - sale);
		      amountSaved = price * sale;
		   }

		  beginning += priceMessage + ending + discountPrice;
		  
		  System.out.println(beginning);

		  return amountSaved;
		}
}
