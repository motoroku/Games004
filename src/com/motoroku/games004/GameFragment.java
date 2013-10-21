package com.motoroku.games004;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class GameFragment extends Fragment {

	GameView mGameView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_game, container, false);
		Context context = getActivity();

		FrameLayout gameLayout = (FrameLayout) v.findViewById(R.id.GameLayout);
		// LinearLayout fragmentLayout = (LinearLayout)
		// v.findViewById(R.id.FragmentLayout);
		// Button buton = (Button) fragmentLayout.findViewById(R.id.button1);
		mGameView = new GameView(context);

		// TODO å≥Ç©ÇÁÇ†ÇÈÉ{É^ÉìÇå„Ç©ÇÁí«â¡Ç∑ÇÈGameViewÇÃè„Ç…ï\é¶Ç≥ÇπÇΩÇ¢
		// fragmentLayout.removeView(buton);
		gameLayout.addView(mGameView);
		// fragmentLayout.addView(buton);

		return v;
	}
}
