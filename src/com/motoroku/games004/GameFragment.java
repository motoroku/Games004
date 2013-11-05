package com.motoroku.games004;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class GameFragment extends Fragment implements OnClickListener {

	GameView mGameView;
	private Button mButtonRight;
	private Button mButtonLeft;
	private ImageButton mButtonRotate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_game, container, false);
		Context context = getActivity();

		FrameLayout gameLayout = (FrameLayout) v.findViewById(R.id.GameLayout);
		mGameView = new GameView(context);
		gameLayout.addView(mGameView);

		mButtonRight = (Button) v.findViewById(R.id.buttonRight);
		mButtonLeft = (Button) v.findViewById(R.id.buttonLeft);
		mButtonRotate = (ImageButton) v.findViewById(R.id.imageButtonRotate);

		mButtonRight.setOnClickListener(this);
		mButtonLeft.setOnClickListener(this);
		mButtonRotate.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		mGameView.onClicked(id);
	}
}
