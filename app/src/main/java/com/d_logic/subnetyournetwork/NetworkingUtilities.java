package com.d_logic.subnetyournetwork;

public class NetworkingUtilities {
	
	private static final int SIZEOCTETBIN = 9;
	
	/*
	 * OK
	 * It converts a decimal number to its binary representation
	 * The method is implemented with recursion
	 */
	public static int decimalToBinaryConverter( int decimal, char [] binary, int startNumber, int potition ) {

		int i;
		
		if ( decimal - startNumber > 0 ) {
			binary[ potition ] = '1';
			decimal -= startNumber;
		}
		else if ( decimal - startNumber == 0 ) {
			binary[ potition ] = '1';

			for( i=potition+1; i<SIZEOCTETBIN-1; i++ ) {
				binary[i] = '0';
			}
			binary[ SIZEOCTETBIN-1 ] = '\0';
			return startNumber;
		}
		else {
			binary[ potition ] = '0';
		}

		startNumber /= 2;
		potition +=1;
	 
		if ( startNumber == 0 ) {
			binary[ SIZEOCTETBIN-1 ] = '\0';
			return 0;
		} else {
			return decimalToBinaryConverter( decimal, binary, startNumber, potition );
		}

	}
	
	/* 
	 * It converts a binary array to a decimal number *
	*/
	public static int binaryToDecimalConverter( char binary[] ) {
		int i;
		int startNumber;
		int decimal;

		decimal = 0;
		startNumber = 128;

		for( i=0; i<binary.length-1; i++ ) {
			if ( binary[ i ] == '1' ) {
				decimal += startNumber;
			}
			startNumber /= 2;
		}
		return decimal;
	}
	
}