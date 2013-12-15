package com.motoroku.games004;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class GameHolderCallBack implements SurfaceHolder.Callback, Runnable {
	SurfaceHolder mHolder = null;
	TetrisRogic mTetrisRogic;
	TetrisBlock mTetrisBlock;
	TetrisStage mTetrisStage;
	int blockSize = 40;

	private Thread thread = null;
	private boolean isAttached = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int time = 0;
		while (isAttached) {
			if (time == 0) {
				mTetrisBlock.createNewBlock();
			}

			if (time != 0 && time % 10 == 0) {
				mTetrisBlock.moveDown();
			}

			time++;
			mTetrisRogic.merge(mTetrisBlock.xOffset, mTetrisBlock.yOffset, mTetrisBlock.block, mTetrisStage.stage);
			paint(mHolder);

			if (mTetrisBlock.yOffset >= TetrisStage.STAGE_HEIGHT) {
				time = 0;
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mTetrisRogic = new TetrisRogic();
		mTetrisBlock = new TetrisBlock();
		mTetrisStage = new TetrisStage();

		// TODO Auto-generated method stub
		this.mHolder = holder;
		paint(mHolder);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		isAttached = false;
		thread = null;
	}

	/**
	 * 生成されているステージのインスタンスの情報を元に描画処理を行う。
	 * @param holder
	 */
	private void paint(SurfaceHolder holder) {
		int x = 0;
		int y = 0;

		Canvas canvas = holder.lockCanvas();
		Paint paint = new Paint();

		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (mTetrisStage.stage[i][j] == 0) {
					paint.setColor(Color.GREEN);
				} else {
					paint.setColor(Color.RED);
				}
				canvas.drawRect(x, y, x + blockSize, y + blockSize, paint);
				x = x + blockSize + 5;
			}
			x = 0;
			y = y + blockSize + 5;
		}

		holder.unlockCanvasAndPost(canvas);
	}

	/**
	 * ボタンのクリックイベントリスナー
	 * @param id
	 */
	public void onClickedButton(int id) {
		switch (id) {
			case R.id.buttonRight:
				mTetrisBlock.moveRight();
				break;
			case R.id.buttonLeft:
				mTetrisBlock.moveLeft();
				break;
			case R.id.imageButtonRotate:
				mTetrisBlock.block = mTetrisBlock.rotate();
			default:
				break;
		}
	}

}
