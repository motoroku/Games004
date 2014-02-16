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
	public void mergeCurrentStage(TetrisBlock block, TetrisStage stage) {
		// 一度tempStageを初期化しておく
		clearTempStage(stage);

		int[][] blockPosition = getBlockPosition(block);
		int k = 0;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				// ブロックのある場所を1にする
				if (k < 4 && i == blockPosition[k][0] - 1 && j == blockPosition[k][1]) {
					stage.tempStage[i][j] = 1;
					k++;
				} else {
					// ブロックのない場所に関してはfixStageの状態をコピーする
					stage.tempStage[i][j] = stage.fixStage[i][j];
				}
			}
		}
	}

	/**
	 * ブロックの位置を固定する
	 * @param stage
	 */
	public void fixCurrentStage(TetrisStage stage) {
		// tempStageをfixStageにコピーする
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				stage.fixStage[i][j] = stage.tempStage[i][j];
			}
		}
	}

	/**
	 * ステージを初期化する
	 * @param stage
	 */
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
		// ステージの下に着いたらfalseを返す
		if (blockPosition[blockPosition.length - 1][0] == TetrisStage.STAGE_HEIGHT) {
			return false;
		}
		// blockPositionがfixStageのブロックのある場所とかぶったらfalseを返す
		for (int i = 0; i < blockPosition.length; i++) {
			if (stage.fixStage[blockPosition[i][0]][blockPosition[i][1]] == 1) {
				return false;
			}
		}
		return true;
	}

	public boolean clearLine(TetrisStage stage) {
		boolean cleared = false;
		for (int i = 0; i < TetrisStage.STAGE_HEIGHT; i++) {
			for (int j = 0; j < TetrisStage.STAGE_WIDTH; j++) {
				if (stage.fixStage[i][j] == 0) {
					break;
				} else if (j == TetrisStage.STAGE_WIDTH - 1) {
					for (int k = 0; k < TetrisStage.STAGE_WIDTH; k++) {
						stage.fixStage[i][k] = 0;
					}
					cleared = true;
				}
			}
		}
		return cleared;
	}

	public void adjustLines(TetrisStage stage) {
		// stageの下から見ていく
		for (int i = TetrisStage.STAGE_HEIGHT - 1; i > 0; i--) {
			// 一列が全て空白がどうかをチェック
			if (isBlankLine(stage.fixStage[i])) {
				// チェックしている列の一つ上をコピーし、コピー元は空白にする
				for (int k = 0; k < TetrisStage.STAGE_WIDTH; k++) {
					stage.fixStage[i][k] = stage.fixStage[i - 1][k];
					stage.fixStage[i - 1][k] = 0;
				}
			}
		}
		if (checkAdjust(stage)) {
			adjustLines(stage);
		}
	}

	private boolean isBlankLine(int[] line) {
		int length = line.length;
		for (int i = 0; i < length; i++) {
			if (line[i] == 1) {
				return false;
			}
		}
		return true;
	}

	private boolean checkAdjust(TetrisStage stage) {
		boolean isBlank = false;
		// 一列ずつ下からチェック
		for (int i = TetrisStage.STAGE_HEIGHT - 1; i > 0; i--) {
			// 最初の空白の列があったらチェック開始
			if (!isBlank && isBlankLine(stage.fixStage[i])) {
				isBlank = true;
				continue;
			}
			// 空白列があってから
			if (isBlank) {
				// ブロックのある列があったら
				if (!isBlankLine(stage.fixStage[i])) {
					return true;
				}
			}
		}
		return false;
	}
}
