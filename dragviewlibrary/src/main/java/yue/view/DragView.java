package yue.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 *自定义的view，能够随意拖动。
 *
 */

public class DragView extends ImageView {
    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float downX;
    private float downY;

    private long downTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    if (xDistance != 0 && yDistance != 0) {
                        int l = (int) (getLeft() + xDistance);
                        int r = (int) (getRight() + xDistance);
                        int t = (int) (getTop() + yDistance);
                        int b = (int) (getBottom() + yDistance);
                        this.layout(l, t, r, b);

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    setPressed(false);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setPressed(false);
                    break;
            }
            return true;
        }
        return false;
    }

}
