package com.example.davejones.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ConversionActivity extends BaseActivity {

  String resultStr = "";
  EditText edtConversionResult;
  EditText edtResultFrom;

  ListView listView;
  String[] items = {
      "Inches to Centimeters",
      "Centimeters to Inches",
      "Miles to Kilometers",
      "Kilometers to Miles",
      "Gallons to Cups",
      "Cups to Gallons"
  };


//  id => [description, price ]
  Double[] conversionRates = {
      2.54,
      0.393701,
      1.60934,
      0.6213727366

  };

  Map<String, Double> conversionMap = new HashMap<String, Double>();

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_conversion );


//    conversionMap.put("Inches to Centimeters", 2.56);

    edtConversionResult = findViewById( R.id.edtResultTo );
    edtResultFrom = findViewById( R.id.edtResultFrom );

    // Get The Intent data
    Intent intent = getIntent();
    resultStr = intent.getStringExtra( "RESULT" );
    Log.i( "CALC", "ResultStr: " + resultStr );
    edtConversionResult.setText( resultStr );
//    ToastIt( getString( R.string.runningOnCreate) );

    Double test = 0.5555555555E20;
    edtResultFrom.setText( Double.toString( test ) );
    Double test1 = Double.parseDouble( edtResultFrom.getText().toString() );

    ToastIt( "Double Test: " + (test.equals( test1 ) ? "TRUE" : "FALSE" ));

    ArrayAdapter adapter = new ArrayAdapter<String>( this, R.layout.activity_listview, items );
    listView = (ListView)findViewById( R.id.listView );
    listView.setAdapter( adapter );

    listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

      @Override
      public void onItemClick( AdapterView<?>parent, View view, int position, long id){
        performCalculation(position);
      }
    } );
  }

  private void performCalculation( int position ){
    Double fromNumber,toNumber;
    ToastIt( "You Clicked at: " + items[position] );
    fromNumber = Double.parseDouble( edtResultFrom.getText().toString() );
    toNumber = conversionRates[position] * fromNumber;
    edtConversionResult.setText( Double.toString( toNumber));
//
//    if( position == 0 ) {   // Inches to centimeters
//      fromNumber = Double.parseDouble( edtResultFrom.getText().toString() );
//      centimeters = fromNumber * 2.54;
//      edtConversionResult.setText( Double.toString( centimeters));
//    }
//    if( position == 1 ) {   // centimeters Inches to
//      fromNumber = Double.parseDouble( edtResultFrom.getText().toString() );
//      centimeters = fromNumber * 0.393701;
//      edtConversionResult.setText( Double.toString( centimeters));
//    }
  }

    public void calcButtonOnClick( View v ){
    // Switch to the other activity - calc
    // INTENT   intension - Intend to do something.
    Intent intent = new Intent( this, MainActivity.class);
    // PUT data in the intent
    startActivity( intent );
  }
}
