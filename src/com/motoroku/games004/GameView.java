package com.motoroku.games004;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	GameHolderCallBack mGameHolderCallBack;

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		SurfaceHolder holder = getHolder();
		mGameHolderCallBack = new GameHolderCallBack();
		holder.addCallback(mGameHolderCallBack);
	}

}
