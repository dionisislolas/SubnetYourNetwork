package com.d_logic.subnetyournetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String IPV4ADDRESSOCTET1 = "com.dennislolas.subnetyournetwork.IPV4ADDRESSOCTET1";
    public final static String IPV4ADDRESSOCTET2 = "com.dennislolas.subnetyournetwork.IPV4ADDRESSOCTET2";
    public final static String IPV4ADDRESSOCTET3 = "com.dennislolas.subnetyournetwork.IPV4ADDRESSOCTET3";
    public final static String IPV4ADDRESSOCTET4 = "com.dennislolas.subnetyournetwork.IPV4ADDRESSOCTET4";
    public final static String SUBNETMASKOCTET1 = "com.dennislolas.subnetyournetwork.SUBNETMASKOCTET1";
    public final static String SUBNETMASKOCTET2 = "com.dennislolas.subnetyournetwork.SUBNETMASKOCTET2";
    public final static String SUBNETMASKOCTET3 = "com.dennislolas.subnetyournetwork.SUBNETMASKOCTET3";
    public final static String SUBNETMASKOCTET4 = "com.dennislolas.subnetyournetwork.SUBNETMASKOCTET4";
    public final static String NUMBEROFSUBNETS = "com.dennislolas.subnetyournetwork.NUMBEROFSUBNETS";
    public final static String NUMBEROFHOSTS = "com.dennislolas.subnetyournetwork.NUMBEROFHOSTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the OK button */
    /** This method takes the user input and calls the
     *  GetRequirementsActivity to witch it passes the user input */

    public void callGetRequirementsActivity(View view) {

        // The intent is used to start another activity
        Intent intent = new Intent(this, GetRequirementsActivity.class);

        // puts the ipv4 address octets to the intent

        EditText editText1 = (EditText) findViewById(R.id.ipv4AddressOctet1);
        String ipv4AddressOctet1 = editText1.getText().toString();
        if (ipv4AddressOctet1.matches("")) {
            Toast.makeText(this, "You must enter a valid ipv4 address", Toast.LENGTH_LONG).show();
            editText1.requestFocus();
            return;
        }


        EditText editText2 = (EditText) findViewById(R.id.ipv4AddressOctet2);
        String ipv4AddressOctet2 = editText2.getText().toString();
        if (ipv4AddressOctet2.matches("")) {
            Toast.makeText(this, "You must enter a valid ipv4 address", Toast.LENGTH_LONG).show();
            editText2.requestFocus();
            return;
        }


        EditText editText3 = (EditText) findViewById(R.id.ipv4AddressOctet3);
        String ipv4AddressOctet3 = editText3.getText().toString();
        if (ipv4AddressOctet3.matches("")) {
            Toast.makeText(this, "You must enter a valid ipv4 address", Toast.LENGTH_LONG).show();
            editText3.requestFocus();
            return;
        }


        EditText editText4 = (EditText) findViewById(R.id.ipv4AddressOctet4);
        String ipv4AddressOctet4 = editText4.getText().toString();
        if (ipv4AddressOctet4.matches("")) {
            Toast.makeText(this, "You must enter a valid ipv4 address", Toast.LENGTH_LONG).show();
            editText4.requestFocus();
            return;
        }

        InputChecker checkFor = new InputChecker();
        int o1 = Integer.parseInt(ipv4AddressOctet1);
        int o2 = Integer.parseInt(ipv4AddressOctet2);
        int o3 = Integer.parseInt(ipv4AddressOctet3);
        int o4 = Integer.parseInt(ipv4AddressOctet4);

        if ( checkFor.BadAddress(o1, o2, o3, o4) == 1 ) {
            Toast.makeText(this, "You must enter a valid ipv4 address", Toast.LENGTH_LONG).show();
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            editText1.requestFocus();
            return;
        }

        intent.putExtra(IPV4ADDRESSOCTET1, ipv4AddressOctet1);
        intent.putExtra(IPV4ADDRESSOCTET2, ipv4AddressOctet2);
        intent.putExtra(IPV4ADDRESSOCTET3, ipv4AddressOctet3);
        intent.putExtra(IPV4ADDRESSOCTET4, ipv4AddressOctet4);

        // puts the subnet mask octets to the intent

        EditText editText21 = (EditText) findViewById(R.id.subnetMaskOctet1);
        String subnetMaskOctet1 = editText21.getText().toString();
        if (subnetMaskOctet1.matches("")) {
            Toast.makeText(this, "You must enter a valid subnet mask", Toast.LENGTH_LONG).show();
            editText21.requestFocus();
            return;
        }


        EditText editText22 = (EditText) findViewById(R.id.subnetMaskOctet2);
        String subnetMaskOctet2 = editText22.getText().toString();
        if (subnetMaskOctet2.matches("")) {
            Toast.makeText(this, "You must enter a valid subnet mask", Toast.LENGTH_LONG).show();
            editText22.requestFocus();
            return;
        }


        EditText editText23 = (EditText) findViewById(R.id.subnetMaskOctet3);
        String subnetMaskOctet3 = editText23.getText().toString();
        if (subnetMaskOctet3.matches("")) {
            Toast.makeText(this, "You must enter a valid subnet mask", Toast.LENGTH_LONG).show();
            editText23.requestFocus();
            return;
        }


        EditText editText24 = (EditText) findViewById(R.id.subnetMaskOctet4);
        String subnetMaskOctet4 = editText24.getText().toString();
        if (subnetMaskOctet4.matches("")) {
            Toast.makeText(this, "You must enter a valid subnet mask", Toast.LENGTH_LONG).show();
            editText24.requestFocus();
            return;
        }

        o1 = Integer.parseInt(subnetMaskOctet1);
        o2 = Integer.parseInt(subnetMaskOctet2);
        o3 = Integer.parseInt(subnetMaskOctet3);
        o4 = Integer.parseInt(subnetMaskOctet4);

        if ( checkFor.BadSubnetMask(o1, o2, o3, o4) == 1 ) {
            Toast.makeText(this, "You must enter a valid subnet mask", Toast.LENGTH_LONG).show();
            editText21.setText("");
            editText22.setText("");
            editText23.setText("");
            editText24.setText("");
            editText21.requestFocus();
            return;
        }

        intent.putExtra(SUBNETMASKOCTET1, subnetMaskOctet1);
        intent.putExtra(SUBNETMASKOCTET2, subnetMaskOctet2);
        intent.putExtra(SUBNETMASKOCTET3, subnetMaskOctet3);
        intent.putExtra(SUBNETMASKOCTET4, subnetMaskOctet4);



        // puts the number of subnets to the intent
        EditText editText31 = (EditText) findViewById(R.id.numberOfSubnets);
        String numberOfSubnets = editText31.getText().toString();
        if (numberOfSubnets.matches("")) {
            Toast.makeText(this, "You must enter the number of subnets needed", Toast.LENGTH_LONG).show();
            editText31.requestFocus();
            return;
        }
        if (Integer.parseInt(numberOfSubnets)==0) {
            Toast.makeText(this, "The number of subnets must be a possitive one", Toast.LENGTH_LONG).show();
            editText31.setText("");
            editText31.requestFocus();
            return;
        }
        intent.putExtra(NUMBEROFSUBNETS, numberOfSubnets);



        // puts the number of hosts to the intent
        EditText editText41 = (EditText) findViewById(R.id.numberOfHosts);
        String numberOfHosts = editText41.getText().toString();
        if (numberOfHosts.matches("")) {
            Toast.makeText(this, "You must enter the number of hosts needed", Toast.LENGTH_LONG).show();
            editText41.requestFocus();
            return;
        }
        if (Integer.parseInt(numberOfHosts)==0) {
            Toast.makeText(this, "The number of hosts must be a possitive one", Toast.LENGTH_LONG).show();
            editText41.setText("");
            editText41.requestFocus();
            return;
        }
        intent.putExtra(NUMBEROFHOSTS, numberOfHosts);



        // must check input before starting the activity
        startActivity(intent);

    }
}
