package yue.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这个工具可以使任何一个view进行拖动。
 *
 */

public class DragViewUtil {
    public static void drag(View v){
        drag(v,0);
    }

    /**
     * 拖动View方法
     * @param v view
     * @param delay 延迟
     */
    public static void drag(View v,long delay){
        v.setOnTouchListener(new TouchListener(delay));
    }
    private static class  TouchListener  implements View.OnTouchListener {
        private float downX;
        private float downY;
        private long downTime;
        private long delay;
        private TouchListener() {
        }
        private TouchListener(long delay) {
            this.delay = delay;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(System.currentTimeMillis() - downTime>= delay){
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    if (xDistance != 0 && yDistance != 0) {
                        int l = (int) (v.getLeft() + xDistance);
                        int r = (int) (v.getRight() + xDistance);
                        int t = (int) (v.getTop() + yDistance);
                        int b = (int) (v.getBottom() + yDistance);
                        v.layout(l, t, r, b);
                        ViewGroup.LayoutParams p = v.getLayoutParams();
                    }
                    }
                    break;
            }
            return false;
        }

        }
}
