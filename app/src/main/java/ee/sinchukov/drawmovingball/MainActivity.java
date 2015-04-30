package ee.sinchukov.drawmovingball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    static public final String PREFS_NAME = "sharedPrefsFile";
    static public final String PREFS_KEY_BITMAP_X = "bitmap_x";
    static public final String PREFS_KEY_BITMAP_Y = "bitmap_y";
    static public final int DEFAULT_BITMAP_X = 70;
    static public final int DEFAULT_BITMAP_Y = 70;
    static public final String PREFS_KEY_BITMAP_DX = "bitmap_dx";
    static public final String PREFS_KEY_BITMAP_DY = "bitmap_dy";
    static public final int DEFAULT_BITMAP_DX = 10;
    static public final int DEFAULT_BITMAP_DY = 10;
    static public final String PREFS_KEY_BITMAP_FRAMERATE = "bitmap_framerate";
    static public final int DEFAULT_BITMAP_FRAMERATE = 10;
    static public final String PREFS_KEY_CANVAS_COLOR = "canvas_color";
    static public final int DEFAULT_CANVAS_COLOR = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void openSettings(View view){
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }

    public void startBall(View view){
        Intent intent = new Intent(this,DrawBall.class);
        startActivity(intent);
    }
}
