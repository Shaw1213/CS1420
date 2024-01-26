package assign01;
//CS1420 Assignment 1 by Shawn Zhang(August 31,2023)

public class FluidCalculator {

	public static void main(String[] args) 
	{
	int uid = 1388867; //my uid
	int gal = 0;//gallon
	int qua = 0;//quart
	int cup = 0;//cups
	int onc1 = 0;//remain ounce from gallon
	int onc2 = 0;//remain ounce from quart
	int onc3 = 0;//remian ounce from cup
	gal=uid/128;
	onc1=uid-(gal*128);
	qua=onc1/32;
	onc2=uid-((gal*128)+(qua*32));
	cup=onc2/8;
	onc3=uid-((cup*8)+(gal*128)+(qua*32));
	
	
	System.out.println(uid+" fluid ounces is equivalent to "+gal+" gallon(s), "+qua+" quart(s), "+cup+" cup(s), and "+onc3+" ounce(s).");
	
	}

}
