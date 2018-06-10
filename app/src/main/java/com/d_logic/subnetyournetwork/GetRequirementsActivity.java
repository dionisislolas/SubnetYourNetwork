package com.d_logic.subnetyournetwork;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class GetRequirementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_requirements);

        // Get the message from the intent
        Intent intent = getIntent();

        int ipv4AddressOctet1 = Integer.parseInt( intent.getStringExtra(MainActivity.IPV4ADDRESSOCTET1) );
        int ipv4AddressOctet2 = Integer.parseInt( intent.getStringExtra(MainActivity.IPV4ADDRESSOCTET2) );
        int ipv4AddressOctet3 = Integer.parseInt( intent.getStringExtra(MainActivity.IPV4ADDRESSOCTET3) );
        int ipv4AddressOctet4 = Integer.parseInt( intent.getStringExtra(MainActivity.IPV4ADDRESSOCTET4) );

        int subnetMaskOctet1 = Integer.parseInt( intent.getStringExtra(MainActivity.SUBNETMASKOCTET1) );
        int subnetMaskOctet2 = Integer.parseInt( intent.getStringExtra(MainActivity.SUBNETMASKOCTET2) );
        int subnetMaskOctet3 = Integer.parseInt( intent.getStringExtra(MainActivity.SUBNETMASKOCTET3) );
        int subnetMaskOctet4 = Integer.parseInt( intent.getStringExtra(MainActivity.SUBNETMASKOCTET4) );

        int numberOfSubnets = Integer.parseInt( intent.getStringExtra(MainActivity.NUMBEROFSUBNETS) );

        int numberOfHosts = Integer.parseInt( intent.getStringExtra(MainActivity.NUMBEROFHOSTS) );


        int ipAdd[] = {ipv4AddressOctet1, ipv4AddressOctet2, ipv4AddressOctet3, ipv4AddressOctet4};
        int subMask[] = {subnetMaskOctet1, subnetMaskOctet2, subnetMaskOctet3, subnetMaskOctet4};

        Subnet rootNetwork =
                new Subnet(new com.d_logic.subnetyournetwork.Address(ipAdd), new com.d_logic.subnetyournetwork.Address(subMask));
        CalculateSubnets subnetsCalculator = new CalculateSubnets(rootNetwork,numberOfSubnets,numberOfHosts);

        //This is a test

        //This is a test

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(15);
        textView.setBackgroundColor(Color.GRAY);
        textView.setText( "Root Network is:"
                + subnetsCalculator.getRootNetwork().printSubnet()
                + "\n\nsubnets needed : " + subnetsCalculator.getNumberOfSubnets() + "\nhosts per subnet : " + subnetsCalculator.getNumberOfHosts()
                + "\n\nNetwork bits needed for subneting : " + subnetsCalculator.howManyNetworkBits() + " bits"
                + "\n\nHost bits needed for subneting : " + subnetsCalculator.howManyHostBits() + " bits"
                + "\n\nAvailable bits : " + subnetsCalculator.calculateAvailableBits() + " bits"
                + "\n\nNetwork bits needed for subneting found : " + ( ( subnetsCalculator.checkForAvailableHostBitsAndNetworkBits() == 1 ) ? "true" : "false" )

        );

        LinearLayout linearLayout = new LinearLayout(textView.getContext());
        linearLayout.setGravity(Gravity.FILL_VERTICAL);
        linearLayout.addView(textView);

        Toast.makeText(this, "Proceding to subneting", Toast.LENGTH_LONG).show();



        if ( subnetsCalculator.checkForAvailableHostBitsAndNetworkBits() != subnetsCalculator.FAILED ) {

            // Calculate subnets and puts them in the  list
            List<Subnet> list = subnetsCalculator.calculateSubnets();

            Iterator<Subnet> iterator = list.iterator();

            int i = 0;
            while ( iterator.hasNext() ) {
                textView.append( "\n\nSubnet #" + (i+1) + iterator.next().printSubnet() );
                i++;
            }

            ScrollView scrollView = new ScrollView(linearLayout.getContext());
            scrollView.addView(linearLayout);
            // Set the scroll view as the activity layout
            setContentView(scrollView);

        }
        else {
            Toast.makeText(this, "There aren't available host bits for the subneting", Toast.LENGTH_LONG).show();
            return;
        }

    }
}
