package com.motoroku.games004;

public class TetrisLogic {

	// TetrisStage tetrisStage;
	//
	// public TetrisRogic() {
	// tetrisStage = new TetrisStage();
	// }

	/**
	 * ブロックの位置情報を取得し、ステージにブロックの位置を反映させる。
	 * @param block ブロックの情報
	 * @param stage ステージの配列
	 */
	public void merge(TetrisBlock block, TetrisStage stage) {
		clearTempStage(stage);

		int[][] blockPosition = getBlockPosition(block);
		int k = 0;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (k < 4 && i == blockPosition[k][0] - 1 && j == blockPosition[k][1]) {
					stage.tempStage[i][j] = 1;
					k++;
				} else {
					stage.tempStage[i][j] = stage.fixStage[i][j];
				}
			}
		}
		if (!checkMovingBlock(block, stage)) {
			for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
				for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
					stage.fixStage[i][j] = stage.tempStage[i][j];
				}
			}
		}
	}

	private void clearTempStage(TetrisStage stage) {
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				stage.tempStage[i][j] = 0;
			}
		}
	}

	/**
	 * ブロックの位置を取得する
	 * @param block ブロックの情報
	 * @return ブロックの一つ一つの位置情報を配列で返す
	 */
	private int[][] getBlockPosition(TetrisBlock block) {
		// ブロックの一つが4マス分で構成されていることを前提としてる
		int[][] blockPosition = new int[4][2];
		int k = 0;
		for (int i = 0; i < block.block.length; i++) {
			for (int j = 0; j < block.block[0].length; j++) {
				if (block.block[i][j] == 1) {
					blockPosition[k][0] = block.yOffset + i;
					blockPosition[k][1] = block.xOffset + j;
					k++;
				}
			}
		}
		return blockPosition;
	}

	/**
	 * ブロックの位置の判定を行う
	 * @param block ブロックの情報
	 * @param stage ステージの情報
	 * @return
	 */
	public boolean checkMovingBlock(TetrisBlock block, TetrisStage stage) {
		int[][] blockPosition = getBlockPosition(block);
		int a = blockPosition.length;
		int b = blockPosition[blockPosition.length - 1][0];
		if (blockPosition[blockPosition.length - 1][0] == TetrisStage.STAGE_HEIGHT) {
			return false;
		}
		for (int i = 0; i < blockPosition.length; i++) {
			if (stage.fixStage[blockPosition[i][0]][blockPosition[i][1]] == 1) {
				return false;
			}
		}
		return true;
	}
}
