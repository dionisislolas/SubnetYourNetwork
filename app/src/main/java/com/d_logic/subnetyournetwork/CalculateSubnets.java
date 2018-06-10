package com.d_logic.subnetyournetwork;

import java.util.ArrayList;
import java.util.List;

public class CalculateSubnets {
	public final int FAILED = 999;
	
	private Subnet rootNetwork;
	private int numberOfSubnets;
	private int numberOfHosts;
	private List <Subnet> subnetsList = new ArrayList<Subnet>();
	
	public CalculateSubnets( Subnet subnet, int numOfSubnets, int numOfHosts ) {
		setRootNetwork( subnet );
		setNumberOfSubnets( numOfSubnets );
		setNumberOfHosts( numOfHosts );
	}
	
	private void setRootNetwork( Subnet subnet ) {
		this.rootNetwork = subnet;
	}
	
	private void setNumberOfSubnets( int number ) {
		this.numberOfSubnets = number;
	}
	
	
	private void setNumberOfHosts( int number ) {
		this.numberOfHosts = number;
	}
	
	public Subnet getRootNetwork() {
		return this.rootNetwork;
	}
	
	public int getNumberOfSubnets() {
		return this.numberOfSubnets;
	}
	
	public int getNumberOfHosts() {
		return this.numberOfHosts;
	}
	
	/*
	 * OK
	 * Returns the power of base^subscriber
	 * The method is implemented with recursion
	 */
	private int myPower( int base, int subscriber ) {

		if ( subscriber == 0 ) {
			return 1;
		}
		else {
			return base * myPower( base, subscriber - 1 );
		}

	}
	
	/*
	 * OK
	 * Returns the host bits that are required
	 * for the subneting
	 */
	public int howManyHostBits( ) {
		int i, mp;

		for( i=0; i<32; i++ ) {
			mp = myPower( 2, i );
			if ( mp >= this.numberOfHosts+2 ) {
				return i;
			}
		}
		return this.FAILED;
	}
	
	/*
	 * OK
	 * Returns the network bits that we need to borrow 
	 * from the host bits in order to do the subneting
	 */
	public int howManyNetworkBits( ) {
		int i, mp;

		for( i=0; i<32; i++ ) {
			mp = myPower( 2, i );
			if ( mp >= this.numberOfSubnets ) {
				return i;
			}
		}
		return FAILED;
	}
	
	/*
	 * OK
	 * Returns the number of available bits
	 * in the subnet mask
	 */
	public int calculateAvailableBits( ) {
		
		int i;
		int availableBits;

		availableBits = 0;
		for( i=0; i<this.getRootNetwork().getSubnetMask().getFullAddressBinary().length(); i++ ) {
			if ( this.getRootNetwork().getSubnetMask().getFullAddressBinary().charAt(i) == '0' ) {
				availableBits++;
			}
		}

		return availableBits;

	}
	
	/*
	 * OK
	 * if there are available bits it returns 1
	 * otherwise it returns FAILED
	 */
	public int checkForAvailableHostBitsAndNetworkBits( ) {
		int hostBits = this.howManyHostBits();
		int networkBits = this.howManyNetworkBits();
		int availableBits = this.calculateAvailableBits();

		if ( availableBits >= hostBits+networkBits ) {
			return 1;
		} else {
			return FAILED;
		}

	}
	
	// OK
	/*
	 * ��������� 1 (���) �� ���� ������� ������ *
	 * � ��������� ����������� �� ��������	    *
	*/
	private char[] binaryAddition( char number[] , int length ) {
		int i;

		for( i=length-1; i>=0; ) {
			
			if ( number[i] == '0' ) {
				number[i] = '1';
				return number;
			} else {
				number[i] = '0';
				return binaryAddition( number , length-1 );	
			}

		}
		
		return number;
	}
	
	/*
	 * This function does the subneting
	 */
	public List<Subnet> calculateSubnets() {
		int networkBits = this.howManyNetworkBits();
		char newNetworkAddressBinary[][] = new char[4][9];
		int newNetworkAddressDecimal[] = new int[4];
		char newSubnetMaskBinary[][] = new char[4][9];
		int newSubnetMaskDecimal[] = new int[4];
		
		// calculating new subnet mask and new network addresses
		
		// subnet mask ok
		for ( int i=0; i<4; i++ ) {
			newSubnetMaskBinary[i] = this.getRootNetwork().getSubnetMask().getOctetsBinary()[i].toCharArray();
		}
		
		int tmp=0;
		for ( int i=0; i<4; i++ ) {
			for ( int j=0; j<8; j++ ) {
				if ( newSubnetMaskBinary[i][j] == '0' ) {
					newSubnetMaskBinary[i][j] = '1';
					tmp = tmp + 1;
				}
				if (tmp==networkBits) {
					break;
				}
			}
			if (tmp==networkBits) {
				break;
			}
		}
		
		for ( int i=0; i<4; i++ ) {
			newSubnetMaskDecimal[i] = NetworkingUtilities.binaryToDecimalConverter( newSubnetMaskBinary[i] );
		}
		// subnet mask ok
		
		/*-------------------------------------------*/
		
		// network address
		for ( int i=0; i<4; i++ ) {
			newNetworkAddressBinary[i] = this.getRootNetwork().getNetworkAddress().getOctetsBinary()[i].toCharArray();
		}
		
		// network address in one array 
		char netAdd[] = new char [33];
		int a = 0;
		for ( int i=0; i<4; i++ ) {
			for ( int j=0; j<8; j++ ) {
				netAdd[a] = newNetworkAddressBinary[i][j];
				a++;
			}
		}
		netAdd[a] = '\0';
		
		int subnetMaskZeroPosision = 0;
		for( int i=0; i<4; i++ ) {
			for ( int j=0; j<8; j++ ) {
				if ( this.getRootNetwork().getSubnetMask().getOctetsBinary()[i].charAt(j) == '0' ) {
					break;
				}
				subnetMaskZeroPosision = subnetMaskZeroPosision + 1;
			}
		}
		
		// finds the network bits that are about to change
		char networkAddressSubstring[] = new char[ networkBits ];
		int position = subnetMaskZeroPosision;
		for( int i=0; i < networkAddressSubstring.length; i++ ) {
			networkAddressSubstring[i] = netAdd[ position ];
			position = position + 1;
		}
		
		// calculates the new subnets 
		// and puts them in the list
		// String str = "";
		for( int i=0; i<myPower( 2, networkBits ); i++ ) {
			
			if ( i!= 0 ) {
				//networkAddressSubstring +=1 ;
				networkAddressSubstring = binaryAddition( networkAddressSubstring, networkAddressSubstring.length );
			}
			
			// put substring back in the network address
			int y = 0;
			for( int x=subnetMaskZeroPosision; x < subnetMaskZeroPosision + networkAddressSubstring.length ; x++ ) {
				netAdd[x] = networkAddressSubstring[y];
				y = y + 1;
			}
			
			//network address back in octets
			a = 0;
			for ( int x=0; x<4; x++ ) {
				for ( int z=0; z<8; z++ ) {
					newNetworkAddressBinary[x][z] = netAdd[a];
					a++;
				}
			}
			
			for ( int x=0; x<4; x++ ) {
				newNetworkAddressDecimal[x] = NetworkingUtilities.binaryToDecimalConverter( newNetworkAddressBinary[x] );
			}
			
			// create the new subnet
			Subnet newSubnet = new Subnet( 
					new Address( newNetworkAddressDecimal ), 
					new Address( newSubnetMaskDecimal ) );
			//str = str + "\n\n\nSubnet #" + i + "\n" + newSubnet.printSubnet();
			
			//puts the subnet in the list
			subnetsList.add(newSubnet);
		}
		
		return subnetsList;
	}
	
}