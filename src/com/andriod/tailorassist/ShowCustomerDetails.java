package com.andriod.tailorassist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ShowCustomerDetails extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer_details); 
                      
        Bundle extras = this.getIntent().getExtras();
        
    	long newCustmerNumber = Long.parseLong(extras.getString("newCustmerNumber"));
    	
    	CustomerTable custTable = new CustomerTable(this);
    	custTable.open();
    	Cursor newCustDetails = custTable.fetchCustomerById(newCustmerNumber);
    	custTable.close();
    	
    	
    	if (newCustDetails.getCount()!= 0){    		
    		
    		String custName = newCustDetails.getString(newCustDetails.getColumnIndex(custTable.KEY_NAME));
        	String custMob = newCustDetails.getString(newCustDetails.getColumnIndex(custTable.KEY_MOBILE));    		
        	String custAdrs = newCustDetails.getString(newCustDetails.getColumnIndex(custTable.KEY_ADDRESS));
        	String custShirtDetails = newCustDetails.getString(newCustDetails.getColumnIndex(custTable.KEY_SHIRTDETAILS));
        	String custPantDetails = newCustDetails.getString(newCustDetails.getColumnIndex(custTable.KEY_PANTDETAILS));
        	
        	// getting the string for the details
        	newCustDetails.close();
        	String details = "\t\t Details: \n\n" + "\tCustomer Number:\t"+ newCustmerNumber+ 
        			"\n\tName:\t"+ custName+"\n\tMobile:\t"+custMob+"\n\tAddress:\t"+custAdrs+
        			"\n\n\t\tShirt measurement: \n\t" +custShirtDetails+
        			"\n\n\t\tTrouser Measurement: \n\t" + custPantDetails + "\n\n";  
        	
        	TextView custDetailsView = (TextView) findViewById(R.id.textView_ShowCustDetails);
        	custDetailsView.setText(details);   
    	}
    	
        
    }

    
	public void goHome(View v){
	    	
	    	/*Intent intent = new Intent(this,Matrix.class);
	    	startActivity(intent);*/
	    	
	        Intent parentActivityIntent = new Intent(this, Matrix.class);
	        parentActivityIntent.addFlags(
	                Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(parentActivityIntent);
	        finish();
	    	
	}
}
