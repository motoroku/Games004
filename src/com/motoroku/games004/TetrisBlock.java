package com.motoroku.games004;

import java.util.Random;

public class TetrisBlock {
	int xOffset;
	int yOffset;
	int[][] block;
	Random random = new Random();

	public TetrisBlock() {
		createNewBlock();
	}

	public int[][] createNewBlock() {
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
		resetPosition();
		return block;
	}

	public int[][] rotate(int[][] block) {
		int[][] rotated = new int[block.length][block[0].length];
		for (int x = 0; x < block[0].length; x++) {
			for (int y = 0; y < block.length; y++) {
				rotated[x][block.length - y - 1] = block[y][x];
			}
		}
		return rotated;
	}

	public void moveDown() {
		yOffset++;
	}

	public void moveRight() {
		int copyXOffset = block[0].length + xOffset + 1;
		if (copyXOffset <= TetrisStage.STAGE_WIDTH) {
			xOffset++;
		}
	}

	public void moveLeft() {
		xOffset--;
	}

	private void resetPosition() {
		xOffset = TetrisStage.STAGE_WIDTH / 2;
		yOffset = 0;
	}
}
