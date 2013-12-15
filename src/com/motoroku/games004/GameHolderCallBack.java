package com.motoroku.games004;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class GameHolderCallBack implements SurfaceHolder.Callback, Runnable {
	SurfaceHolder mHolder = null;
	TetrisLogic mTetrisLogic;
	TetrisBlock mTetrisBlock;
	TetrisStage mTetrisStage;
	int blockSize = 40;

	private Thread thread = null;
	private boolean isAttached = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int turn = 0;
		mTetrisBlock.initBlock();
		while (isAttached) {
			// ブロックの位置をステージに反映させる
			mTetrisLogic.merge(mTetrisBlock, mTetrisStage);
			// 全体の描画処理
			paint(mHolder);
			// ターンをひとつ進める
			turn++;
			// ターンが10進むごとにブロックの位置を一つ下に動かす
			if (turn != 0 && turn % 10 == 0) {
				mTetrisBlock.moveDown();
				if (!mTetrisLogic.checkMovingBlock(mTetrisBlock, mTetrisStage)) {
					mTetrisLogic.merge(mTetrisBlock, mTetrisStage);
					turn = 0;
				}
			}
			// ターンが0になったら新しいブロックを生成する
			if (turn == 0) {
				mTetrisBlock.initBlock();
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mTetrisLogic = new TetrisLogic();
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
				if (mTetrisStage.tempStage[i][j] == 0) {
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
