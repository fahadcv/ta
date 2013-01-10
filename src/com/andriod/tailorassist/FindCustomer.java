package com.andriod.tailorassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class FindCustomer extends Activity {
	Context Ctxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_customer);  
        
        Ctxt = this;        
        
        ImageButton btnFindCustById = (ImageButton) findViewById(R.id.button_searchById);        
        btnFindCustById.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {
				
				EditText CustIdField = (EditText)findViewById(R.id.textView_searchById);
				int custIdTxtLen = CustIdField.getText().length();
				
				if(custIdTxtLen != 0){
									
						long custId = Long.parseLong(CustIdField.getText().toString());						
						Cursor custDetails = getCustDetails(custId,null);
						if(custDetails.getCount()!= 0){
							
							Bundle details = getBundle(custDetails);
							//details.putString("searchedCustName","");
							
							Intent intent = new Intent(Ctxt,SearchCustomerResult.class);				
							/* sending the customer details to next activity 			 */
							
							intent.putExtras(details);			
							
							//start the activity
							
							Ctxt.startActivity(intent);						
						}
						else{
							
							Toast.makeText(Ctxt, R.string.alertTxt_FindCustomer_byId_fail, Toast.LENGTH_SHORT).show();
						}
				}
				else{
						Toast.makeText(Ctxt, R.string.alertTxt_FindCustomer_byId_empty, Toast.LENGTH_SHORT).show();		
									
				}
			}	
		});
        
        ImageButton btnFindCustByMob = (ImageButton) findViewById(R.id.button_searchByMobile);        
        btnFindCustByMob.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {
				
				EditText CustMobField = (EditText)findViewById(R.id.textView_searchByMobile);
				int CustMobTxtLen = CustMobField.getText().length();
				
				if(CustMobTxtLen != 0){
					
						String custMob = CustMobField.getText().toString();
						Cursor custDetails = getCustDetails(0,custMob);
						if(custDetails.getCount()!= 0){
							
							Bundle details = getBundle(custDetails);
							//details.putString("searchedCustName","");
							
							Intent intent = new Intent(Ctxt,SearchCustomerResult.class);				
							/* sending the customer details to next activity 			 */
							
							intent.putExtras(details);			
							
							//start the activity
							
							Ctxt.startActivity(intent);						
						}
						else{
							
							Toast.makeText(Ctxt, R.string.alertTxt_FindCustomer_byMob_fail, Toast.LENGTH_SHORT).show();
						}
				}
				else{
					
					Toast.makeText(Ctxt, R.string.alertTxt_FindCustomer_byMob_empty, Toast.LENGTH_SHORT).show();
				}
			}	
		});
        
        ImageButton btnFindCustByName = (ImageButton) findViewById(R.id.button_searchByName);        
        btnFindCustByName.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {
				
				EditText CustNameField = (EditText)findViewById(R.id.textView_searchByName);								
				String custName = CustNameField.getText().toString();
				
				Bundle bundle = new Bundle();			
				bundle.putString("custName",custName );	
				
				Intent intent = new Intent(Ctxt,SearchCustomerByName.class);	
				
				/* sending the customer details to next activity 			 */			
				intent.putExtras(bundle);				
				//start the activity
				
				Ctxt.startActivity(intent);	
			}	
		});
        
    }
    
    
    
    
	private Bundle getBundle(Cursor custDetails) {
		
		CustomerTable custTable = new CustomerTable(Ctxt);
		long custNum = custDetails.getLong(custDetails.getColumnIndex(custTable.KEY_ROWID));
		String custName = custDetails.getString(custDetails.getColumnIndex(custTable.KEY_NAME));
    	String custMob = custDetails.getString(custDetails.getColumnIndex(custTable.KEY_MOBILE));    		
    	String custAdrs = custDetails.getString(custDetails.getColumnIndex(custTable.KEY_ADDRESS));
    	String custShirtDetails = custDetails.getString(custDetails.getColumnIndex(custTable.KEY_SHIRTDETAILS));
    	String custPantDetails = custDetails.getString(custDetails.getColumnIndex(custTable.KEY_PANTDETAILS));
    	
    	custDetails.close();
    	String details = "\tCustomer Number:\t\t"+ custNum+ 
    			"\n\tName:\t"+ custName+"\n\tMobile:\t"+custMob+"\n\tAddress:\t\t"+custAdrs;  
    	Bundle bundle = new Bundle();			
		bundle.putString("custDetails",details );
		bundle.putLong("custNum",custNum);
		bundle.putString("custName",custName);
		bundle.putString("custMob",custMob);
		bundle.putString("custAdrs",custAdrs);
		bundle.putString("custShirtDetails",custShirtDetails);
		bundle.putString("custPantDetails",custPantDetails);
    	
		return bundle;
	}

	private Cursor getCustDetails(long custId, String custmob) {
		Cursor  custDetails = null ;
    	if(custId != 0 ){
    		CustomerTable custTable = new CustomerTable(Ctxt);
	    	custTable.open();
    		custDetails = custTable.fetchCustomerById(custId);
    		custTable.close();		    		
    	}
    	else if(custmob != null){
    		CustomerTable custTable = new CustomerTable(Ctxt);
	    	custTable.open();
    		custDetails = custTable.fetchCustomerByMob(custmob);
    		custTable.close();	
    	}
    	return custDetails;
		
	}
	
	   public void clearText(View v){
		   
		   ((EditText) v).setText("");
	   }
}
