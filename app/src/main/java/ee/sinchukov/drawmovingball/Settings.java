package ee.sinchukov.drawmovingball;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class Settings extends ActionBarActivity {

    TextView textViewFrameRate;
    EditText editTextFrameRate;

    TextView textViewDX;
    EditText editTextDX;
    TextView textViewDY;
    EditText editTextDY;

    TextView textViewColor;
    EditText editTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences storeData = getSharedPreferences(MainActivity.PREFS_NAME,0);

        textViewFrameRate = (TextView)findViewById(R.id.textView_frameRate);
        textViewFrameRate.setText("frame rate: " + storeData.getInt(MainActivity.PREFS_KEY_BITMAP_FRAMERATE, MainActivity.DEFAULT_BITMAP_FRAMERATE));

        textViewDX = (TextView)findViewById(R.id.textView_dX);
        textViewDX.setText("Ball shift dX: " + storeData.getInt(MainActivity.PREFS_KEY_BITMAP_DX, MainActivity.DEFAULT_BITMAP_DX));

        textViewDY = (TextView)findViewById(R.id.textView_dY);
        textViewDY.setText("Ball shift dY: " + storeData.getInt(MainActivity.PREFS_KEY_BITMAP_DY, MainActivity.DEFAULT_BITMAP_DY));

        textViewColor = (TextView)findViewById(R.id.textView_color);
        textViewColor.setText("Canvas color: " + intColorToHexString(storeData.getInt(MainActivity.PREFS_KEY_CANVAS_COLOR, MainActivity.DEFAULT_CANVAS_COLOR)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePrefs();
    }

    protected void savePrefs() {
        getAndSaveInputs(editTextFrameRate, R.id.editText_frameRate, MainActivity.PREFS_KEY_BITMAP_FRAMERATE);
        getAndSaveInputs(editTextDX, R.id.editText_dX, MainActivity.PREFS_KEY_BITMAP_DX);
        getAndSaveInputs(editTextDY, R.id.editText_dY, MainActivity.PREFS_KEY_BITMAP_DY);

        // get color and save to prefs
        try {
            editTextColor = (EditText) findViewById(R.id.editText_color);
            int newColor = stringColorToInt(editTextColor.getText().toString());
            SharedPreferences storeData = getSharedPreferences(MainActivity.PREFS_NAME, 0); // 0 means only for this app
            SharedPreferences.Editor storeDataEditor = storeData.edit();
            storeDataEditor.putInt(MainActivity.PREFS_KEY_CANVAS_COLOR, newColor);
            storeDataEditor.commit();
        } catch (Exception e){}

    }

    protected void getAndSaveInputs(EditText editTextInput, int viewId, String prefsKey){
        SharedPreferences storeData = getSharedPreferences(MainActivity.PREFS_NAME,0); // 0 means only for this app
        SharedPreferences.Editor storeDataEditor = storeData.edit();
        editTextInput = (EditText)findViewById(viewId);
        try {
            int newValue = Integer.parseInt(editTextInput.getText().toString());
            if (newValue > 0) {
                storeDataEditor.putInt(prefsKey, newValue);
            }
            storeDataEditor.commit();
        } catch (Exception e)  // in case of wrong input do nothing
        { }
    }

    protected String intColorToHexString(int intColor){
        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
        return hexColor;
    }



    protected int stringColorToInt (String colorString){
        int intColor = Color.parseColor(colorString);
        return intColor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
