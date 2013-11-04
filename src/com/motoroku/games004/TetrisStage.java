package com.motoroku.games004;

public class TetrisStage {
	int[][] stage = new int[STAGE_HEIGHT][STAGE_WIDTH];
	static int STAGE_WIDTH = 8;
	static int STAGE_HEIGHT = 14;

	public TetrisStage() {
		// ブロックを表示するステージを初期化
		for (int i = 0; i < STAGE_HEIGHT; i++) {
			for (int j = 0; j < STAGE_WIDTH; j++) {
				stage[i][j] = 0;
			}
		}
	}
}
