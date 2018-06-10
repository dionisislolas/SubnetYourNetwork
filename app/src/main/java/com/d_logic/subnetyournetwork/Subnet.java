package com.d_logic.subnetyournetwork;

public class Subnet {
	Address ipv4Address;
	Address subnetMask;
	Address networkAddress;
	Address broadcastAddress;
	
	public Subnet(Address ipv4Add, Address subMask) {
		this.ipv4Address = ipv4Add;
		this.subnetMask = subMask;
		setNetworkAddress();
		setBroadcastAddress();
	}
	
	private void setNetworkAddress() {
		char networkAddressOctetsBinary[][] = new char[4][9];
		int networkAddressOctetsDecimal[] = new int [4];
		
		for(int i=0; i<4; i++) {
			networkAddressOctetsBinary[i] = this.ipv4Address.getOctetsBinary()[i].toCharArray();
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<8; j++) {
				if ( this.subnetMask.getOctetsBinary()[i].charAt(j) == '0') {
					networkAddressOctetsBinary[i][j] = '0';
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			networkAddressOctetsDecimal[i] = NetworkingUtilities.binaryToDecimalConverter( networkAddressOctetsBinary[i] );
		}
		
		this.networkAddress = new Address( networkAddressOctetsDecimal );
		
	}
	
	private void setBroadcastAddress() {
		char broadcastAddressOctetsBinary[][] = new char[4][9];
		int broadcastAddressOctetsDecimal[] = new int [4];
		
		for(int i=0; i<4; i++) {
			broadcastAddressOctetsBinary[i] = this.networkAddress.getOctetsBinary()[i].toCharArray();
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<8; j++) {
				if ( this.subnetMask.getOctetsBinary()[i].charAt(j) == '0') {
					broadcastAddressOctetsBinary[i][j] = '1';
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			broadcastAddressOctetsDecimal[i] = NetworkingUtilities.binaryToDecimalConverter( broadcastAddressOctetsBinary[i] );
		}
		
		this.broadcastAddress = new Address( broadcastAddressOctetsDecimal );
	}
	
	public Address getIpv4Address() {
		return this.ipv4Address;
	}
	
	public Address getSubnetMask() {
		return this.subnetMask;
	}
	
	public Address getNetworkAddress() {
		return this.networkAddress;
	}
	
	public Address getBroadcastAddress() {
		return this.broadcastAddress;
	}
	
	public String printIpv4Address() {
		return this.ipv4Address.printAddress();
	}
	
	public String printSubnetMask() {
		return this.subnetMask.printAddress();
	}
	
	public String printNetworkAddress() {
		return this.networkAddress.printAddress();
	}
	
	public String printBroadcastAddress() {
		return this.broadcastAddress.printAddress();
	}
	
	public String printSubnet() {
		String string = new String(
				"\n\nNetwork Address: " + this.printNetworkAddress() +
				"\n\nSubnet mask: " + this.printSubnetMask() +
				"\n\nBroadcast Address: " + this.printBroadcastAddress() +
				"\n\nHost Range IPs:\n" + this.printHostRange()
				);
		
		return string;
	}
	
	private String printHostRange() {
		char tmp [][] = new char[4][9];
		int tmpDecimal[] = new int[4];
		
		for(int i=0; i<4; i++) {
			tmp[i] = this.networkAddress.getOctetsBinary()[i].toCharArray();
		}
		
		tmp[3][7] = '1';
		
		for(int i=0; i<4; i++) {
			tmpDecimal[i] = NetworkingUtilities.binaryToDecimalConverter( tmp[i] );
		}
		
		Address tmpAddress1 = new Address(tmpDecimal);
		
		String str1 = "[ " + tmpAddress1.printAddress() + " - ";
		
		for(int i=0; i<4; i++) {
			tmp[i] = this.broadcastAddress.getOctetsBinary()[i].toCharArray();
		}
		
		tmp[3][7] = '0';
		
		for(int i=0; i<4; i++) {
			tmpDecimal[i] = NetworkingUtilities.binaryToDecimalConverter( tmp[i] );
		}
		
		Address tmpAddress2 = new Address(tmpDecimal);
		
		String str2 = tmpAddress2.printAddress() + " ] ";
		
		return str1+str2;
	}
	
}