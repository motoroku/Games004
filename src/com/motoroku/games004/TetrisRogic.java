package com.motoroku.games004;

public class TetrisRogic {

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
		int[][] blockPosition = getBlockPosition(block);
		int k = 0;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (k < 4 && i == blockPosition[k][0] && j == blockPosition[k][1]) {
					stage.stage[i][j] = 1;
					k++;
				} else {
					stage.stage[i][j] = 0;
				}
			}
		}
	}

	/**
	 * ブロックの位置を取得する
	 * @param block ブロックの情報
	 * @return ブロックの一つ一つの位置情報を配列で返す
	 */
	private int[][] getBlockPosition(TetrisBlock block) {
		int[][] blockPosition = new int[TetrisBlock.BLOCK_SIZE][TetrisBlock.BLOCK_SIZE];
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
	 * ブロックの位置が画面より一つ下の場合の判定を行う
	 * @param block ブロックの情報
	 * @param stage ステージの情報
	 * @return
	 */
	public boolean checkMovingBlock(TetrisBlock block, TetrisStage stage) {
		block.yOffset++;
		int[][] blockPosition = getBlockPosition(block);
		block.yOffset--;
		if (blockPosition[blockPosition.length - 1][0] >= TetrisStage.STAGE_HEIGHT) {
			return false;
		}
		return true;
	}
}
