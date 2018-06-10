package com.d_logic.subnetyournetwork;

public class InputChecker {
	
	/** It takes the ipv4 address octets as parameters
	 * and its checks if they are in the appropriate range
	 * if it is so the method returns 0 (zero) else it return 1 (one) */
	public int BadAddress( int o1, int o2, int o3, int o4 ) {
		
		if ( o1<0 || o1 > 255 )
			return 1;
		if ( o2<0 || o2 > 255 )
			return 1;
		if ( o3<0 || o3 > 255 )
			return 1;
		if ( o4<0 || o4 > 255 )
			return 1;

		return 0;
	}
	
	
	/** It takes the subnet mask octets as parameters
	 * and its checks if they are in the appropriate range
	 * if it is so the method returns 0 (zero) else it return 1 (one) */
	public int BadSubnetMask( int o1, int o2, int o3, int o4 ) {
		
		if ( checkSubnetMaskOctets( o1, o2, o3, o4 ) == 1 )
			return 0;
		else 
			return 1;
	}
	
	
	/*
	 * It takes the subnet mask octets as parameters
	 * and its checks if they are in the appropriate range
	 * it returns 0 (zero) if the octets are not correct
	 * and 1 (one) otherwise
	*/
	private int checkSubnetMaskOctets( int o1, int o2, int o3, int o4 ) {

		switch ( checkOctet( o1 ) ) {
			case 0:
				return 0;
			case 1:
				if ( o2 == 0 && o3 == 0 && o4 == 0 ) {
					return 1;
				}
				else {
					return 0;
				}
			case 255:
				if ( o2 == 0 && o3 == 0 && o4 == 0 ) {
					return 1;
				}
				switch ( checkOctet( o2 ) ) {
					case 0:
						return 0;
					case 1:
						if ( o3 == 0 && o4 == 0 ) {
							return 1;
						}
						else {
							return 0;
						}
					case 255:
						if ( o3 == 0 && o4 == 0 ) {
							return 1;
						}
						switch ( checkOctet( o3 ) ) {
							case 0:
								return 0;
							case 1:
								if ( o4 == 0 ) {
									return 1;
								}
								else {
									return 0;
								}
							case 255:
								if ( o4 == 0 ) {
									return 1;
								}
								switch ( checkOctet( o4 ) ) {
									case 0:
										return 0;
									default:
										return 1;
								}
						}
				}
		}

		return 0;
	}
	
	
	/* 
	 * It takes a subnet mask octet as parameter
	 * and it returns:
	 * a. 0 (zero) if the octet is wrong
	 * b. 1 (one) if the octet is not wrong but its not the number 255
	 * c. 255 (tow fifty fife) if the octet is the number 255
	*/
	private int checkOctet( int octet ) {

		switch ( octet ) {
			case 128:
			case 192:
			case 224:
			case 240:
			case 248:
			case 252:
			case 254:
				return 1;
			case 255:
				return 255;
			default:
				return 0;
		}

	}
}