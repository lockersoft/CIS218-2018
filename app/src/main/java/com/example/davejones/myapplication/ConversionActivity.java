package com.example.davejones.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ConversionActivity extends BaseActivity {

  String resultStr = "";
  EditText edtConversionResult;

  ListView listView;
  String[] items = {
      "Inches to Centimeters",
      "Centimeters to Inches",
      "Miles to Kilometers",
      "Kilometers to Miles",
      "Gallons to Cups",
      "Cups to Gallons"

  };

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_conversion );

    edtConversionResult = findViewById( R.id.edtResultTo );

    // Get The Intent data
    Intent intent = getIntent();
    resultStr = intent.getStringExtra( "RESULT" );
    Log.i( "CALC", "ResultStr: " + resultStr );
    edtConversionResult.setText( resultStr );
    ToastIt( getString( R.string.runningOnCreate) );

    ArrayAdapter adapter = new ArrayAdapter<String>( this, R.layout.activity_listview, items );  // TODO:  change layout to simple layout with only text view in it.
    listView = (ListView)findViewById( R.id.listView );
    listView.setAdapter( adapter );
  }

  public void calcButtonOnClick( View v ){
    // Switch to the other activity - calc
    // INTENT   intension - Intend to do something.
    Intent intent = new Intent( this, MainActivity.class);
    // PUT data in the intent
    startActivity( intent );
  }
}
