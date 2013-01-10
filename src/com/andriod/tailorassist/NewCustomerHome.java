package com.andriod.tailorassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCustomerHome extends Activity {
	
	Context Ctxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer_home);    
        
        Ctxt= this;
        Button btnSAddMeasurement = (Button) findViewById(R.id.button_addMeasurement);
        
        btnSAddMeasurement.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String custName = new String();
			String custMobile = new String();
			String custAddress = new String();
			
			EditText Name = (EditText) findViewById(R.id.editText_name);
			EditText Mobile = (EditText) findViewById(R.id.editText_mobileNumber);
			EditText Address = (EditText) findViewById(R.id.editText_address);
			custName = Name.getText().toString();			
			custMobile = Mobile.getText().toString();			
			custAddress = Address.getText().toString();
			
			if((custName.length()== 0) || (custMobile.length()== 0) ){
				
				Toast.makeText(Ctxt, R.string.alertTxt_NewCustomer_Name_mobile_empty, Toast.LENGTH_LONG).show();
			}
			else{
			
//				Intent intent = new Intent(Ctxt,AddMeasurement.class );
				Intent intent = new Intent(Ctxt,MeasurementsEntryActivity.class );
				/* sending the customer details to next activity 			 */
				Bundle bundle = new Bundle();
				
				bundle.putString("custName", custName);
				bundle.putString("custMobile", custMobile);
				bundle.putString("custAddress", custAddress);
				
				intent.putExtras(bundle);			
				
				//start the activity			
				Ctxt.startActivity(intent);
			}			
			
		}
	});
   }
            
}
