package main;

import java.util.Date;

/**
 * 核心思想是递归
 * @author Ezreal
 *
 */
public class EightQueenSolver {

	private static final short N = 8; //棋盘常数
	private static int count = 0; //解的个数
	
	public static void main(String[] args) {
		Date begin = new Date();
		//初始化棋盘
		short chess[][] = new short[N][N]; //默认值为0
		putQueenAtRow(chess, 0);
		Date end = new Date();
		System.out.println("解决 " +N+ " 皇后问题，用时：" +String.valueOf(end.getTime()-begin.getTime())+ "毫秒，计算结果："+count);
		
	}
	
	private static void putQueenAtRow(short[][] chess, int row) {
		//终止判断
		if(row == N) {
			count++;
			System.out.println("第" + count + "种解：");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(chess[i][j]);
				}
				System.out.println();
			}
			return;
		}
		short[][] chessTemp = chess.clone();
		//向这一行的每一个位置尝试摆放皇后
		//然后检测状态，如果安全则继续执行递归函数
		for(int i = 0; i < N; i++) {
			//摆放之前要清理这一行的摆放记录
			for(int j = 0; j < N; j++) {
				chessTemp[row][j] = 0;
			}
			chessTemp[row][i] = 1;
			
			if(isSafety(chessTemp, row, i)) {
				putQueenAtRow(chessTemp, row + 1);
			}
		}
	}
	
	private static boolean isSafety(short[][] chess, int row, int column) {
		//判断此位置的 左上，正上，右上 方向上是否有棋子
		int step = 1;
		while(row - step >= 0) {
			if(chess[row-step][column] == 1) //正上 
				return false;
			if(column - step >= 0 && chess[row-step][column-step] == 1) //左上
				return false;
			if(column + step < N && chess[row - step][column + step] == 1)
				return false;
			
			step++;
		}
		return true;
	}
}
