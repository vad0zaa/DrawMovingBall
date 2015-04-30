package ee.sinchukov.drawmovingball;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DrawBall extends ActionBarActivity {

    Draw2D draw2D;
    private int start_x;
    private int start_y;
    private int start_dx;
    private int start_dy;
    private int start_frameRate;
    private int start_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_draw_ball);

        SharedPreferences storeData = getSharedPreferences(MainActivity.PREFS_NAME,0); // 0 means only for this app

        start_x = storeData.getInt(MainActivity.PREFS_KEY_BITMAP_X,MainActivity.DEFAULT_BITMAP_Y);
        start_y = storeData.getInt(MainActivity.PREFS_KEY_BITMAP_Y,MainActivity.DEFAULT_BITMAP_Y);
        start_dx = storeData.getInt(MainActivity.PREFS_KEY_BITMAP_DX,MainActivity.DEFAULT_BITMAP_DX);
        start_dy = storeData.getInt(MainActivity.PREFS_KEY_BITMAP_DY,MainActivity.DEFAULT_BITMAP_DY);
        start_frameRate = storeData.getInt(MainActivity.PREFS_KEY_BITMAP_FRAMERATE,MainActivity.DEFAULT_BITMAP_FRAMERATE);
        start_color = storeData.getInt(MainActivity.PREFS_KEY_CANVAS_COLOR,MainActivity.DEFAULT_CANVAS_COLOR);

        draw2D = new Draw2D(this,start_x, start_y, start_dx, start_dy, start_frameRate, start_color);
        setContentView(draw2D);
    }

    protected void onStop() {
        super.onStop();
        this.savePrefs();
    }

    protected void savePrefs() {
        SharedPreferences storeData = getSharedPreferences(MainActivity.PREFS_NAME,0); // 0 means only for this app
        SharedPreferences.Editor storeDataEditor = storeData.edit();
        storeDataEditor.putInt(MainActivity.PREFS_KEY_BITMAP_X, draw2D.getter_X());
        storeDataEditor.putInt(MainActivity.PREFS_KEY_BITMAP_Y, draw2D.getter_Y());
        storeDataEditor.putInt(MainActivity.PREFS_KEY_BITMAP_DX, draw2D.getter_dX());
        storeDataEditor.putInt(MainActivity.PREFS_KEY_BITMAP_DY, draw2D.getter_dY());
        storeDataEditor.putInt(MainActivity.PREFS_KEY_BITMAP_FRAMERATE, draw2D.getter_frameRate());
        storeDataEditor.putInt(MainActivity.PREFS_KEY_CANVAS_COLOR, draw2D.getter_color());
        storeDataEditor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_draw_ball, menu);
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
