package com.example.davejones.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends BaseActivity {

  Button btn1;
  TextView txtDebug;
  public static EditText edtResult;
  Button btnPlus, btnMinus, btnMult, btnDiv, btnBackspace;
  boolean usedDecimalPoint = false;
  Button btnEquals;
  int pendingOperation;
  double lastNumber, currentValue;
  boolean portrait = true;


  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    // Get the current screen Orientation  (Landscape or Portrait)

    Display display = ((WindowManager) getSystemService( WINDOW_SERVICE ) ).getDefaultDisplay();
    int orientation = display.getRotation();  // Surface.ROTATION_90 or Surface.ROTATION_270
    portrait = ( orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180 );// ? true : false;
    Log.d( "CALC", getString( R.string.orientation) + portrait );


    btn1 = findViewById( R.id.btn1 );
    edtResult = (EditText) findViewById( R.id.edtResultTo );
    btnPlus = (Button) findViewById( R.id.btnPlus );
    btnMinus = (Button) findViewById( R.id.btnMinus );
    btnMult = (Button) findViewById( R.id.btnMult );
    btnDiv = (Button) findViewById( R.id.btnDiv );
    btnEquals = (Button) findViewById( R.id.btnEquals );
    txtDebug = (TextView) findViewById( R.id.txtDebug );

    if( !portrait ) {   // Only do in landscape
      // DELETE/BACKSPACE  button image
      btnBackspace = (Button) findViewById( R.id.btnBackspace );
      btnBackspace.setText( "\u232b" );
    }
//    "\u03C0" === PI

    // Disallow soft keyboard
    edtResult.setShowSoftInputOnFocus( false );
    edtResult.setInputType( InputType.TYPE_NULL );
    edtResult.setFocusable( false );
    edtResult.setText( "" );


  }

  @Override
  public void onConfigurationChanged( Configuration newConfig ) {
    super.onConfigurationChanged( newConfig );
    Log.d( "CALC", "OrientationChange: " );

    // Checks the orientation of the screen
    if( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
      ToastIt( getString( R.string.landscape) );
      portrait = false;
    } else if( newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) {
      ToastIt( getString( R.string.portrait) );
      portrait = true;
    }
  }

  private void appendLog( String msg ) {
    txtDebug.append( msg + "\n" );
  }

  // Handles all the operations
  public void btnOperationOnClick( View v ) {
    int operation = ( (Button) v ).getId();
    int btnPlusID = btnPlus.getId();
    lastNumber = Double.parseDouble( edtResult.getText().toString() );
    Log.i( "CALC", "ButtonID: " + operation );
    appendLog( "ButtonID:" + operation );
    if( pendingOperation != 0 && operation == btnPlusID ) {
      btnEquals.performClick();
      Log.i( "CALC", "Is Plus: " );
      // Calculations
//        Toast.makeText(this, "ButtonID: " + operation, Toast.LENGTH_LONG).show();
      pendingOperation = btnPlusID;
      // CLear the result
      edtResult.setText( "" );
    }
  }

  public void btnMathOperation( View v ) {
    double value = Math.PI;//(Double.parseDouble( edtResult.getText().toString() ));
    edtResult.setText( Double.toString( value ) );
    usedDecimalPoint = true;
  }

  public void btnRandom( View v ) {
    edtResult.setText( Double.toString( Math.random() ) );
    usedDecimalPoint = true;
  }

  // Handles all numbers and decimal point
  public void btnNumberOnClick( View v ) {
    String newButton = ( (Button) v ).getText().toString();
    String currentResult = edtResult.getText().toString();
    if( newButton.equals( getString( R.string.period)) ) {
      if( !usedDecimalPoint ) {
        edtResult.setText( currentResult + getString( R.string.period) );
        usedDecimalPoint = true;
      }
    } else {
      edtResult.setText( currentResult + newButton );
    }
  }

  public void btnEqualsOnClick( View v ) {
    // Assume that I have a pendingOperation and 2 numbers
    Log.i( "CALC", "EQUALS ON CLICK:" );
    if( pendingOperation != 0 ) {
      switch( pendingOperation ) {
        case R.id.btnPlus:
          currentValue = lastNumber + Double.parseDouble( edtResult.getText().toString() );
          edtResult.setText( Double.toString( currentValue ) );
          pendingOperation = 0;
          lastNumber = 0;
          break;

        case R.id.btnMinus:
          currentValue = lastNumber - Double.parseDouble( edtResult.getText().toString() );
          edtResult.setText( Double.toString( currentValue ) );
          pendingOperation = 0;
          lastNumber = 0;
          break;

        default:
          break;
      }
    }
  }

  public void btnClearOnClick( View v ) {
    edtResult.setText( "" );
    usedDecimalPoint = false;
  }

  public void btnConversionOnClick( View v ) {
    Intent intent = new Intent( this, ConversionActivity.class );
    // PUT data in the intent
    intent.putExtra( "RESULT", edtResult.getText().toString() );
    startActivity( intent );
  }
}
