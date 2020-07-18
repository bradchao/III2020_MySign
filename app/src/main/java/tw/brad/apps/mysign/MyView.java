package tw.brad.apps.mysign;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines = new LinkedList<>();
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        for (LinkedList<HashMap<String,Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"),
                        p1.get("x"), p1.get("y"), paint);
            }
        }
    }

    public void clear(){
        lines.clear();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HashMap<String,Float> point = new HashMap<>();
        point.put("x", event.getX());
        point.put("y", event.getY());

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            LinkedList<HashMap<String,Float>> line = new LinkedList<>();
            lines.add(line);
            line.add(point);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            lines.getLast().add(point);
        }

        invalidate();
        //Log.v("bradlog", event.getX() + " : " + event.getY());
        return true; //super.onTouchEvent(event);
    }
}
