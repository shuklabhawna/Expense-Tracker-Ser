package com.example.sample.expenseTracker.utilities;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {

	      // create 3 BigDecimal objects
	      BigDecimal bg1, bg2, bg3;

	      // create 3 int objects
	      int i1, i2, i3;

	      bg1 = new BigDecimal("123");
	      bg2 = new BigDecimal("0.458");
	      bg3 = new BigDecimal("-12");

	      // assign the signum values of bg1,bg2,bg3 to i1,i2,i3 respectively
	      i1 = bg1.signum();
	      i2 = bg2.signum();
	      i3 = bg3.signum();

	      String str1 = "The Result of Signum function on " + bg1 + " is " + i1;
	      String str2 = "The Result of Signum function on " + bg2 + " is " + i2;
	      String str3 = "The Result of Signum function on " + bg3 + " is " + i3;

	      // print i1,i2,i3 values
	      System.out.println( str1 );
	      System.out.println( str2 );
	      System.out.println( str3 );
	   }
}
