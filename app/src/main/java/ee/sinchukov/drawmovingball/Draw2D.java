package ee.sinchukov.drawmovingball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by vsinchuk on 4/29/2015.
 */
public class Draw2D extends View {
    private Paint canvasPaint = new Paint();
    private Rect mRect = new Rect();
    private Paint rectPaint = new Paint();
    private int rectColor;
    private Bitmap ballBitmap;

    private int x;
    private int y;
    private static int screenWidth;
    private static int screenHeight;
    private static int ballBitmapWidth;
    private static int ballBitmapHeight;
    private Handler h;

    // sift coordinates pixels by frame,  and frame rate
    private int dx;
    private int dy;
    private int frameRate;

    public int getter_X(){
        return x;
    }
    public int getter_Y(){
        return y;
    }
    public int getter_dX(){
        return dx;
    }
    public int getter_dY(){
        return dy;
    }
    public int getter_frameRate(){
        return frameRate;
    }
    public int getter_color(){return rectColor;}

    public Draw2D(Context context, int start_x,int start_y, int start_dx, int start_dy, int startFrameRate, int startColor) {
        super(context);

        rectColor = startColor;

        // Выводим bitmap image из ресурсов
        Resources res = this.getResources();
        ballBitmap = BitmapFactory.decodeResource(res, R.drawable.ball);
        getBallSize();


        // find screen size
        setScreenWidthHeight(context);

        // first bitmap position
        x=start_x;
        y=start_y;

        //first bitmap shift
        dx=start_dx;
        dy=start_dy;

        //first frame rate
        frameRate = startFrameRate;

        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();   //  redraw view object, i.e. start Draw2D method OnDraw
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(rectColor);
        canvas.drawPaint(canvasPaint);

        rectPaint.setColor(Color.YELLOW);
        rectPaint.setStrokeWidth(3);
        canvas.drawRect(10f, 10f, screenWidth-10, screenHeight-10, rectPaint);
        rectPaint.setStrokeWidth(0);
        rectPaint.setColor(Color.BLACK);
        canvas.drawRect(13f,13f,screenWidth-13,screenHeight-13,rectPaint);

        // change coordinates
        x=x+dx;
        y+=dy;

        if(x>screenWidth -ballBitmapWidth || x<13){
        //if(x>screenWidth){
            dx=dx*(-1);
        }

        if(y>screenHeight-ballBitmapHeight || y<13){
        //if(y>screenHeight){
            dy=dy*(-1);
        }

        // Выводим изображение
        canvas.drawBitmap(ballBitmap, x, y, canvasPaint);

        // handler will start next Runnable r, to redraw using invalidate()
        h.postDelayed(r, frameRate);

    }
    protected void setScreenWidthHeight(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    protected void getBallSize(){
        ballBitmapHeight = ballBitmap.getHeight();
        ballBitmapWidth = ballBitmap.getWidth();
    }
}
