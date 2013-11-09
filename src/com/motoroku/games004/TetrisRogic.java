package com.motoroku.games004;

public class TetrisRogic {

	TetrisStage tetrisStage;

	public TetrisRogic() {
		tetrisStage = new TetrisStage();
	}

	private int[][] getBlockPosition(int xOffset, int yOffset, int[][] block) {
		int[][] blockPosition = new int[TetrisBlock.BLOCK_SIZE][TetrisBlock.BLOCK_SIZE];
		int k = 0;
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block[0].length; j++) {
				if (block[i][j] == 1) {
					blockPosition[k][0] = yOffset + i;
					blockPosition[k][1] = xOffset + j;
					k++;
				}
			}
		}
		return blockPosition;
	}

	public void merge(int xOffset, int yOffset, int[][] block, int[][] stage) {
		int[][] blockPosition = getBlockPosition(xOffset, yOffset, block);
		int k = 0;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (k < 4 && i == blockPosition[k][0] && j == blockPosition[k][1]) {
					stage[i][j] = 1;
					k++;
				} else {
					stage[i][j] = 0;
				}
			}
		}
	}
}
