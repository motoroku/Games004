package com.motoroku.games004;

import java.util.Random;

public class TetrisBlock {
	int xOffset;
	int yOffset;
	static int BLOCK_SIZE = 4;
	int[][] block;
	Random random = new Random();

	public TetrisBlock() {
		createNewBlock();
	}

	/**
	 * ブロックの初期化を行う
	 */
	public void initBlock() {
		createNewBlock();
		resetPosition();
	}

	/**
	 * blockの中身を新しい配列に置き換える。
	 * @return 新しいblockの配列
	 */
	private int[][] createNewBlock() {
		block = null;
		int num = random.nextInt(7) + 1;
		switch (num) {
			case 1:
				block = new int[][] { { 1, 1 }, { 0, 1 }, { 0, 1 } };
				break;
			case 2:
				block = new int[][] { { 1, 1 }, { 1, 0 }, { 1, 0 } };
				break;
			case 3:
				block = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 0 } };
				break;
			case 4:
				block = new int[][] { { 1, 1 }, { 1, 1 } };
				break;
			case 5:
				block = new int[][] { { 1, 1 }, { 0, 1 }, { 0, 1 } };
				break;
			case 6:
				block = new int[][] { { 1, 0 }, { 1, 1 }, { 0, 1 } };
				break;
			case 7:
				block = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 0 } };
				break;
			case 8:
				block = new int[][] { { 1 }, { 1 }, { 1 }, { 1 } };
				// default:
				// block = new int[][] { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		}
		return block;
	}

	/**
	 * ブロックの位置を初期化する
	 */
	private void resetPosition() {
		xOffset = TetrisStage.STAGE_WIDTH / 2;
		yOffset = 0;
	}

	/**
	 * blockの中身を回転させる
	 * @return 回転したblock配列
	 */
	public int[][] rotate() {
		int[][] rotated = new int[block[0].length][block.length];
		for (int x = 0; x < block[0].length; x++) {
			for (int y = 0; y < block.length; y++) {
				rotated[x][block.length - y - 1] = block[y][x];
			}
		}

		if (rotated[0].length + xOffset > TetrisStage.STAGE_WIDTH) {
			int rightPosition = rotated[0].length + xOffset;
			int dif = rightPosition - TetrisStage.STAGE_WIDTH;
			xOffset = xOffset - dif;
		}

		return rotated;
	}

	/**
	 * ブロックの位置を一つ上に動かす
	 */
	public void moveUp() {
		yOffset--;
	}

	/**
	 * ブロックの位置を一つ下に動かす
	 */
	public void moveDown() {
		yOffset++;
	}

	/**
	 * ブロックの位置を右に一つ動かす。もしもステージの幅を超える場合は何もしない
	 */
	public void moveRight() {
		if (block[0].length + xOffset + 1 <= TetrisStage.STAGE_WIDTH) {
			xOffset++;
		}
	}

	/**
	 * ブロックの位置を左に一つ動かす。もしもステージの幅を超える場合は何もしない
	 */
	public void moveLeft() {
		if (xOffset - 1 >= 0) {
			xOffset--;
		}
	}

}
