package com.example.davejones.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConversionActivity extends BaseActivity {

  String resultStr = "";
  EditText edtConversionResult;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_conversion );

    edtConversionResult = findViewById( R.id.edtConversionResult );

    // Get The Intent data
    Intent intent = getIntent();
    resultStr = intent.getStringExtra( "RESULT" );
    Log.i( "CALC", "ResultStr: " + resultStr );
    edtConversionResult.setText( resultStr );
    ToastIt( getString( R.string.runningOnCreate) );
  }

  public void calcButtonOnClick( View v ){
    // Switch to the other activity - calc
    // INTENT   intension - Intend to do something.
    Intent intent = new Intent( this, MainActivity.class);
    // PUT data in the intent
    startActivity( intent );
  }
}
