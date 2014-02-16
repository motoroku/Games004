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

	int turn = 0;
	int horizonMove = 0;

	private Thread thread = null;
	private boolean isAttached = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mTetrisBlock.initBlock();
		while (isAttached) {
			// ブロックの位置をステージに反映させる
			mTetrisLogic.mergeCurrentStage(mTetrisBlock, mTetrisStage);
			// 全体の描画処理
			paint(mHolder);
			/*
			 * ブロックの左右への移動をこのスレッド外で行おうとしたら上手く出来なかったので
			 * ボタンの値を取っておきスレッド内でその値を利用してブロックの左右移動を行う
			 */
			// 左右への移動入力があればその結果を反映させる
			if (horizonMove > 0) {
				for (int i = 0; horizonMove > i; i++) {
					mTetrisBlock.moveRight();
					if (!mTetrisLogic.checkMovingBlock(mTetrisBlock, mTetrisStage)) {
						mTetrisBlock.moveLeft();
						break;
					}
				}
				horizonMove = 0;
			} else if (horizonMove < 0) {
				horizonMove = horizonMove * -1;
				for (int i = 0; horizonMove > i; i++) {
					mTetrisBlock.moveLeft();
					if (!mTetrisLogic.checkMovingBlock(mTetrisBlock, mTetrisStage)) {
						mTetrisBlock.moveRight();
						break;
					}
				}
				horizonMove = 0;
			}

			// ターンが10進むごとにブロックの位置を一つ下に動かす
			if (turn % 10 == 0) {
				mTetrisBlock.moveDown();
				if (!mTetrisLogic.checkMovingBlock(mTetrisBlock, mTetrisStage)) {
					finishTurn();
				}
			}
			// ターンをひとつ進める
			turn++;
		}
	}

	private void finishTurn() {
		// ブロックの位置をステージに反映させる
		mTetrisLogic.mergeCurrentStage(mTetrisBlock, mTetrisStage);
		// ブロックの位置を保存する
		mTetrisLogic.fixCurrentStage(mTetrisStage);
		// 揃っているラインを削除する
		if (mTetrisLogic.clearLine(mTetrisStage)) {
			mTetrisLogic.adjustLines(mTetrisStage);
		}
		// 新しいブロックを生成する
		mTetrisBlock.initBlock();
		// turnを0に戻す
		turn = 0;
		// 左右の動きも一度初期化する
		horizonMove = 0;
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
				horizonMove++;
				break;
			case R.id.buttonLeft:
				horizonMove--;
				break;
			case R.id.imageButtonRotate:
				mTetrisBlock.block = mTetrisBlock.rotate();
			default:
				break;
		}
	}

}
