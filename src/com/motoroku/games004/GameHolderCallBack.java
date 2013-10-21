package com.motoroku.games004;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class GameHolderCallBack implements SurfaceHolder.Callback, Runnable {
	SurfaceHolder holder = null;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		this.holder = holder;

		Canvas canvas = holder.lockCanvas();
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		canvas.drawLine(0, 0, 100, 100, paint);
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

}
