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


public class AddMeasurement extends Activity {
	
	Context Ctxt;
	public static final int FLAG_SHIRT = 0;
	public static final int FLAG_TROUSER = 1;
	
	int enabledEditText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_measurements); 
        Ctxt = this;
        //testing changes
        Button btnSaveMeasurement = (Button) findViewById(R.id.button_addMeasurementSave);
        
        btnSaveMeasurement.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
							
				long newCustNumber = addCustomerDetails(v);
				
				if(newCustNumber != 0){
				
					String newCustNum =  String.valueOf(newCustNumber);
					
					//Show the next layout view
					Intent intent = new Intent(Ctxt,ShowCustomerDetails.class);
					
					/* sending the customer details to next activity 			 */
					Bundle bundle = new Bundle();			
					bundle.putString("newCustmerNumber", newCustNum);
					
					intent.putExtras(bundle);			
					
					//start the activity
					
					Ctxt.startActivity(intent);
					
				}									
			}			
		});
    }
    

    // save customer details
    public long addCustomerDetails(View view){    	
    	
    	long newCustNo = 0;
    	String custShirt = ((EditText)findViewById(R.id.editText_shirtMeasurement)).getText().toString();    	
    	String custPant = ((EditText)findViewById(R.id.editText_pantMeasurement)).getText().toString();;
    	if((custShirt.length()== 0) || (custPant .length()== 0)){
    		
    		Toast.makeText(Ctxt, R.string.alertTxt_NewCustomer_Measuremetns_emptyMsg, Toast.
    				LENGTH_LONG).show();
    	}
    	else{
		    	Bundle extras = this.getIntent().getExtras();
		    	
		    	String custName = extras.getString("custName");
		    	String custMobile = extras.getString("custMobile");
		    	String custAddress = extras.getString("custAddress");   	
		    	
		    	
		    	CustomerTable custTable = new CustomerTable(this);
		    	custTable.open();
		    	newCustNo = custTable.addCustomer(custName, custMobile, custAddress, custShirt, custPant);   	
		    	    	
		    	custTable.close();
		    	
		    	Toast.makeText(Ctxt, "Customer details stored successfully as customer number : " + newCustNo,Toast.LENGTH_SHORT).show();
    	}
		return newCustNo;    	
    }
    
    
    
   
    
    
   /* public void btnSmIn(View view) {

        // Create the text view
    			

		    	int enabledEditTextViewID = getEnabledEditTextViewID(view);    			
		        EditText Details = (EditText) findViewById(enabledEditTextViewID);
                /*String DetailsString = Details.getText().toString();
                Details.append("\n" + "Sm In" + "\t");                
    }*/
    
    
    public void editTextClicked(View v){
    	
    	int idEditText = v.getId();
    	
    	if(idEditText==R.id.editText_shirtMeasurement){
    		
    		enabledEditText = FLAG_SHIRT;
    		Toast.makeText(Ctxt, "shirt clicked", Toast.LENGTH_SHORT).show();
    	}
    	else{
    		
    		enabledEditText = FLAG_TROUSER;
    		Toast.makeText(Ctxt, "Pant clicked", Toast.LENGTH_SHORT).show();
    	}    	
    }
    
    public void extraBtnclick(View v){
    	
    	Button clickedBtn = (Button)findViewById(v.getId());    	
    	String btnTxt = clickedBtn.getText().toString();
    	
    	if(btnTxt.contentEquals("Down")){
    		
    		btnTxt = "\n";
    	}
    	
    	EditText measurementField;
    	
    	if(enabledEditText == FLAG_SHIRT ){
    		
    		measurementField = (EditText)findViewById(R.id.editText_shirtMeasurement);    				
    	}
    	else{
    		
    		measurementField = (EditText)findViewById(R.id.editText_pantMeasurement);
    	}
    	
    	/*String currentMesurementText = measurementField.getText().toString();
    	currentMesurementText = currentMesurementText + btnTxt+"\t";  */ 
    	
    	measurementField.append(btnTxt + "\t"); 	
    	
    }
    

}  
