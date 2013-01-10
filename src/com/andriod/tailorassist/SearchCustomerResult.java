package com.andriod.tailorassist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchCustomerResult extends Activity {
	
	Context Ctxt;	
	String searchedCustName = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer_result);
        
        Ctxt =this;    
        
        Bundle custDetailsBundle = this.getIntent().getExtras();
        final long custNum = custDetailsBundle.getLong("custNum");
        final String custName = custDetailsBundle.getString("custName");
        final String custMob = custDetailsBundle.getString("custMob");
        final String custAdrs = custDetailsBundle.getString("custAdrs");
        final String details = custDetailsBundle.getString("custDetails");
        final String shirtDetails = custDetailsBundle.getString("custShirtDetails");
        final String pantDetails = custDetailsBundle.getString("custPantDetails");
        searchedCustName = custDetailsBundle.getString("searchedCustName");
        
        TextView detailsField = (TextView)findViewById(R.id.textView_serchResult_CustDetails);
        detailsField.setText(details);
        
        EditText shirtDetailsField = (EditText)findViewById(R.id.editText_serchResult_ShirtDetails);
        shirtDetailsField.setText(shirtDetails);
        
        EditText pantDetailsField = (EditText)findViewById(R.id.editText_serchResult_PantDetails);
        pantDetailsField.setText(pantDetails);
        
        
        Button editBtn = (Button)findViewById(R.id.btn_SearchResult_Edit);
        editBtn.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {			
											
				EditText shirtDetailsField = (EditText)findViewById(R.id.editText_serchResult_ShirtDetails);
				shirtDetailsField.setEnabled(true);				
				shirtDetailsField.setBackgroundResource(R.color.Color_lightBlue);
				shirtDetailsField.setSelectAllOnFocus(true);
				
				EditText pantDetailsField = (EditText)findViewById(R.id.editText_serchResult_PantDetails);
				pantDetailsField.setEnabled(true);
				pantDetailsField.setBackgroundResource(R.color.Color_lightBlue);
				pantDetailsField.setSelectAllOnFocus(true);
				
				v.setVisibility(4);
				
				
				Button deleteBtn = (Button)findViewById(R.id.btn_SearchResult_Delete);
				deleteBtn.setVisibility(4);
				
				Button homeBtn = (Button)findViewById(R.id.btn_SearchResult_Home);
				homeBtn.setVisibility(4);
				
				Button cancelBtn = (Button)findViewById(R.id.btn_SearchResult_Cancel);
				cancelBtn.setVisibility(1);
				
				Button saveBtn = (Button)findViewById(R.id.btn_SearchResult_Save);
				saveBtn.setVisibility(1);
				
			}	
		});
        
        Button saveBtn = (Button)findViewById(R.id.btn_SearchResult_Save);
        saveBtn.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {			
											
				EditText shirtDetailsField = (EditText)findViewById(R.id.editText_serchResult_ShirtDetails);
				String newShirtMesurement = shirtDetailsField.getText().toString();
				shirtDetailsField.setEnabled(false);				
				shirtDetailsField.setBackgroundResource(R.color.Color_steelBlue);
				shirtDetailsField.setClickable(false);
				
				
				EditText pantDetailsField = (EditText)findViewById(R.id.editText_serchResult_PantDetails);
				String newPantMesurement = pantDetailsField.getText().toString();
				pantDetailsField.setEnabled(false);
				pantDetailsField.setBackgroundResource(R.color.Color_steelBlue);
				pantDetailsField.setClickable(false);
				
				boolean isUpdated = updateMeasurements(custNum, custName, custMob, custAdrs,newShirtMesurement,newPantMesurement);
					
				if(isUpdated){
					Toast.makeText(Ctxt,R.string.alertTxt_SearchResult_customerUpdate_success, Toast.LENGTH_LONG).show();
				}else{
						
					Toast.makeText(Ctxt, R.string.alertTxt_SearchResult_customerUpdate_fail, Toast.LENGTH_LONG).show();
				}
							
				
				v.setVisibility(4);
				
				Button cancelBtn = (Button)findViewById(R.id.btn_SearchResult_Cancel);
				cancelBtn.setVisibility(4);
				
				Button editBtn = (Button)findViewById(R.id.btn_SearchResult_Edit);
				editBtn.setVisibility(0);
				
				Button deleteBtn = (Button)findViewById(R.id.btn_SearchResult_Delete);
				deleteBtn.setVisibility(0);
				
				Button homeBtn = (Button)findViewById(R.id.btn_SearchResult_Home);
				homeBtn.setVisibility(0);	
								
			}
			
		});
        
        Button deleteBtn = (Button)findViewById(R.id.btn_SearchResult_Delete);
        deleteBtn.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v)  {			
											
				deletionDialog(custNum).show();				
				
			}	
		});
        
       
        
    }
    
   public void clearMeasurement(View v){
	   
	   ((EditText) v).setText("");
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
    
    public void goBack(){ 
    	
    	if(searchedCustName != null){
    		
    		Bundle bundle = new Bundle();			
			bundle.putString("custName",searchedCustName );	
			
    		Intent parentActivityIntent = new Intent(this, SearchCustomerByName.class);
            /*parentActivityIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK);*/
    		
    		parentActivityIntent.putExtras(bundle);
            startActivity(parentActivityIntent);
            finish();
    	}
    	else{
    		finish();
    	}
    }
    
    public void cancelEit(View v){
    	
    	EditText shirtDetailsField = (EditText)findViewById(R.id.editText_serchResult_ShirtDetails);		
		shirtDetailsField.setEnabled(false);				
		shirtDetailsField.setBackgroundResource(R.color.Color_steelBlue);
		shirtDetailsField.setClickable(false);
				
		EditText pantDetailsField = (EditText)findViewById(R.id.editText_serchResult_PantDetails);		
		pantDetailsField.setEnabled(false);
		pantDetailsField.setBackgroundResource(R.color.Color_steelBlue);
		pantDetailsField.setClickable(false);
		
    	v.setVisibility(4);
		
		Button saveBtn = (Button)findViewById(R.id.btn_SearchResult_Save);
		saveBtn.setVisibility(4);
		
		Button editBtn = (Button)findViewById(R.id.btn_SearchResult_Edit);
		editBtn.setVisibility(0);
		
		Button deleteBtn = (Button)findViewById(R.id.btn_SearchResult_Delete);
		deleteBtn.setVisibility(0);
		
		Button homeBtn = (Button)findViewById(R.id.btn_SearchResult_Home);
		homeBtn.setVisibility(0);	
    }
    
    private boolean updateMeasurements(long custNum, String custName, String custMob, String custAdrs,String newShirtMesurement,String newPantMesurement) {
		int cNum = (int) custNum;
		boolean updated = false;
    	CustomerTable custTable = new CustomerTable(Ctxt);
    	custTable.open();
    	updated = custTable.updateCustomer(cNum, custName, custMob, custAdrs, newShirtMesurement, newPantMesurement);
		custTable.close();	
		
		return updated;
	}
    
    
    
    public Dialog deletionDialog(final long custNum){
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(Ctxt);
		builder.setMessage(R.string.alertDialogTxt_SearchResult_deleteMessage);
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
				boolean isDeleted = deleteCustomer(custNum);
				if(isDeleted){
					
					Toast.makeText(Ctxt,R.string.alertTxt_SearchResult_customerDelete_success,Toast.LENGTH_LONG).show();					
					/* Going back home after deleting the record*/					
					goBack();
				}
				else{
					
					Toast.makeText(Ctxt,R.string.alertTxt_SearchResult_customerDelete_fail,Toast.LENGTH_LONG).show();
				}
				dialog.dismiss();				
			}		
		});
				
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
				dialog.dismiss();
			}
		});
		return builder.create();
    }
    
    private boolean deleteCustomer(long custNum) {
		
    	boolean deleted = false;
    	CustomerTable custTable = new CustomerTable(Ctxt);
    	custTable.open();
    	deleted = custTable.deleteCustomer(custNum);
		custTable.close();			
		return deleted;
		
	}

}
