package com.d_logic.subnetyournetwork;

public class Address {
	private int octetsDecimal[];
	private String octetsBinary[];
	private String fullAddressBinary;
	
	public Address( int octetsDec[] ) {
		setOctetsDecimal( octetsDec );
		setOctetsBinary();
		setFullAddressBinary();
	}
	
	private void setOctetsDecimal( int octetsDec[] ) {
		this.octetsDecimal = new int [4];
		
		for ( int i=0; i<4; i++ ) {
			this.octetsDecimal[i] = octetsDec[i];
		}
	}
	
	private void setOctetsBinary() {
		this.octetsBinary = new String[4];
		char octetBinary[] = new char [9];
		
		for(int i=0; i<4; i++) {
			NetworkingUtilities.decimalToBinaryConverter(this.octetsDecimal[i], octetBinary, 128, 0);
			this.octetsBinary[i] = new String(octetBinary);
		}

	}
	
	private void setFullAddressBinary() {
		this.fullAddressBinary = new String(
				this.octetsBinary[0] + this.octetsBinary[1] + this.octetsBinary[2] + this.octetsBinary[3]
				);
	}
	
	public int [] getOctetsDecimal() {
		return this.octetsDecimal;
	}
	
	public String [] getOctetsBinary() {
		return this.octetsBinary;
	}
	
	public String getFullAddressBinary() {
		return this.fullAddressBinary;
	}
	
	public String printOctetsDecimal() {
		return new String(
				this.octetsDecimal[0]+"."+this.octetsDecimal[1]+"."+this.octetsDecimal[2]+"."+this.octetsDecimal[3]
				);
	}
	
	public String printOctetsBinary() {
		return this.octetsBinary[0] +"."+ this.octetsBinary[1] +"."+ this.octetsBinary[2] +"."+ this.octetsBinary[3];
	}
	
	public String printFullAddressBinary() {
		return this.fullAddressBinary;
	}
	
	public String printAddress() {
		return this.printOctetsDecimal() + ", " + this.printOctetsBinary();
	}
}