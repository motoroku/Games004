package com.motoroku.games004;

public class TetrisStage {
	int[][] tempStage = new int[STAGE_HEIGHT][STAGE_WIDTH];
	int[][] fixStage = new int[STAGE_HEIGHT][STAGE_WIDTH];
	static int STAGE_WIDTH = 8;
	static int STAGE_HEIGHT = 14;

	public TetrisStage() {
		for (int i = 0; i < STAGE_HEIGHT; i++) {
			for (int j = 0; j < STAGE_WIDTH; j++) {
				tempStage[i][j] = 0;
				fixStage = new int[STAGE_HEIGHT][STAGE_WIDTH];
			}
		}
	}
}
