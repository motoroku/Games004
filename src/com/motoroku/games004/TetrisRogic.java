package com.motoroku.games004;

public class TetrisRogic {

	TetrisBlock tetrisBlock;
	TetrisStage tetrisStage;
	int[][] block;

	public TetrisRogic() {
		tetrisBlock = new TetrisBlock();
		tetrisStage = new TetrisStage();
		block = tetrisBlock.createNewBlock();
	}

	private int[][] getBlockPosition(int xOffset, int yOffset, int[][] block) {
		int[][] blockPosition = new int[4][2];
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

	public void addNewBlock() {
		block = tetrisBlock.createNewBlock();
	}

	public void merge() {
		int[][] blockPosition = getBlockPosition(tetrisBlock.xOffset, tetrisBlock.yOffset, block);
		int k = 0;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (k < 4 && i == blockPosition[k][0] && j == blockPosition[k][1]) {
					tetrisStage.stage[i][j] = 1;
					k++;
				} else {
					tetrisStage.stage[i][j] = 0;
				}

			}
		}
	}
}
