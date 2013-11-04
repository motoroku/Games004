package com.motoroku.games004;

import java.util.Random;

public class TetrisBlock {
	int xOffset; // ���ݑ��삵�Ă���u���b�N�̍��W�Ƃ��Ďg��
	int yOffset; // ���ݑ��삵�Ă���u���b�N�̍��W�Ƃ��Ďg��
	Random random = new Random();

	public TetrisBlock() {
		xOffset = TetrisStage.STAGE_WIDTH / 2;
		yOffset = 0;
	}

	public int[][] createNewBlock() {
		int[][] block = null;
		// TODO ������������num�𑀍�B�����_���Ƀu���b�N�z��𐶐����ĕԂ��B
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

		if (yOffset > TetrisStage.STAGE_HEIGHT + 5) {
			yOffset = 0;
		}
	}
}
