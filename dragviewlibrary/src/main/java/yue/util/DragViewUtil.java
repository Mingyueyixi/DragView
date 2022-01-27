package yue.util;

import android.view.MotionEvent;
import android.view.View;

/**
 * 这个工具可以使任何一个view进行拖动。
 */

public class DragViewUtil {
    public static void registerDragAction(View v) {
        registerDragAction(v, 0);
    }

    /**
     * 拖动View方法
     *
     * @param v     view
     * @param delay 延迟
     */
    public static void registerDragAction(View v, long delay) {
        v.setOnTouchListener(new TouchListener(delay));
    }

    private static class TouchListener implements View.OnTouchListener {
        private float downX;
        private float downY;
        private long downTime;
        private long delay;
        private boolean isMove;
        private boolean canDrag;

        private TouchListener() {
            this(0);
        }

        private TouchListener(long delay) {
            this.delay = delay;
        }

        private boolean haveDelay() {
            return delay > 0;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    isMove = false;
                    downTime = System.currentTimeMillis();
                    if (haveDelay()) {
                        canDrag = false;
                    } else {
                        canDrag = true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (haveDelay() && !canDrag) {
                        long currMillis = System.currentTimeMillis();
                        if (currMillis - downTime >= delay) {
                            canDrag = true;
                        }
                    }
                    if (!canDrag) {
                        break;
                    }
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    if (xDistance != 0 && yDistance != 0) {
                        int l = (int) (v.getLeft() + xDistance);
                        int r = (int) (l + v.getWidth());
                        int t = (int) (v.getTop() + yDistance);
                        int b = (int) (t + v.getHeight());
//                        v.layout(l, t, r, b);
                        v.setLeft(l);
                        v.setTop(t);
                        v.setRight(r);
                        v.setBottom(b);
                        isMove = true;
                    }
                    break;
                default:
                    break;
            }
            return isMove;
        }

    }
}
