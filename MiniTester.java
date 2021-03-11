import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Scanner;

public class MiniTester {

	private static Game loadSudoku(String in) {
		Scanner sc = new Scanner(new BufferedReader (new StringReader(in)));
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] board = new int[rows][columns];
		//Reading the board
		for (int i=0; i<board.length; i++){
			for (int j=0; j<board[i].length; j++){
				String value = sc.next();
				if (value.equals("-")) {
					board[i][j] = -1;
				} else {
					try {
						board[i][j] = Integer.valueOf(value);
					}catch(Exception e) {
						System.out.println("Ups, something went wrong");
					}
				}	
			}
		}
		int regions = sc.nextInt();
		Game game = new Game();
		game.sudoku = game.new Board(rows, columns, regions);
		game.sudoku.setValues(board);
		for (int i=0; i< regions;i++) {
			int num_cells = sc.nextInt();
			Game.Region new_region = game.new Region(num_cells);
			for (int j=0; j< num_cells; j++) {
				String cell = sc.next();
				String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
				String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
				Game.Cell new_cell = game.new Cell(Integer.valueOf(value1)-1,Integer.valueOf(value2)-1);
				new_region.setCell(j, new_cell);
			}
			game.sudoku.setRegion(i, new_region);
		}
		sc.close();
		
		return game;
	}
	
	private static void testSudoku1() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 1----------\n");

		String in = "3 5\n"
				+ "- - - - -\n"
				+ "- - - - -\n"
				+ "4 - - - 1\n"
				+ "5\n"
				+ "1 (1,1)\n"
				+ "2 (1,2) (1,3)\n"
				+ "5 (2,1) (2,2) (3,1) (3,2) (3,3)\n"
				+ "4 (2,3) (2,4) (1,4) (1,5)\n"
				+ "3 (3,4) (3,5) (2,5)";

		int[][] expected = {{1,2,1,2,1},{3,5,3,4,3},{4,2,1,2,1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 1.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 1 for the solver method!"+ "\n");
	}
	
	private static void testSudoku2() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 2----------\n");

		String in = "7 7\n"
				+ "- - - - - - -\n"
				+ "4 - - - - 5 -\n"
				+ "- - - - - - -\n"
				+ "- - - 3 - - -\n"
				+ "- - - - - - -\n"
				+ "- - - - - - -\n"
				+ "- - - - - - -\n"
				+ "14\n"
				+ "5 (1,1) (2,1) (3,1) (2,2) (2,3)\n"
				+ "4 (2,4) (1,2) (1,3) (1,4)\n"
				+ "2 (1,5) (1,6)\n"
				+ "1 (1,7)\n"
				+ "4 (4,1) (5,1) (6,1) (5,2)\n"
				+ "1 (7,1)\n"
				+ "4 (3,2) (4,2) (4,3) (5,3)\n"
				+ "3 (7,3) (7,2) (6,2)\n"
				+ "4 (6,3) (6,4) (7,4) (7,5)\n"
				+ "6 (2,5) (3,5) (4,5) (3,6) (2,6) (2,7)\n"
				+ "4 (3,7) (4,7) (4,6) (5,6)\n"
				+ "1 (5,7)\n"
				+ "5 (7,7) (7,6) (6,7) (6,6) (6,5)\n"
				+ "5 (3,3) (3,4) (4,4) (5,4) (5,5)";

		int[][] expected = {{3,1,4,2,1,2,1},{4,2,5,3,6,5,4},{1,3,1,4,2,3,1},{2,4,2,3,1,4,2},{1,3,1,5,2,3,1},{4,2,4,3,4,5,2},{1,3,1,2,1,3,1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 2.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 2 for the solver method!"+ "\n");
	}
	
	private static void testSudoku3() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 3----------\n");

		String in = "1 1\n"
				+ "-\n"
				+ "1\n"
				+ "1 (1,1)";

		int[][] expected = {{1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 3.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 3 for the solver method!"+ "\n");
	}
	
	private static void testSudoku4() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 4----------\n");

		String in = "1 3\n"
				+ "- - -\n"
				+ "2\n"
				+ "1 (1,1)\n"
				+ "2 (1,2) (1,3)";

		int[][] expected = {{1,2,1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 4.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 4 for the solver method!"+ "\n");
	}
	
	private static void testSudoku5() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 5----------\n");

		String in = "1 3\n"
				+ "- - -\n"
				+ "2\n"
				+ "1 (1,3)\n"
				+ "2 (1,2) (1,1)";
		
		int[][] expected = {{1,2,1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 5.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 5 for the solver method!"+ "\n");
	}
	
	private static void testSudoku6() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 6----------\n");

		String in = "3 1\n"
				+ "-\n"
				+ "-\n"
				+ "-\n"
				+ "2\n"
				+ "1 (1,1)\n"
				+ "2 (2,1) (3,1)";
		
		int[][] expected = {{1},{2},{1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 6.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 6 for the solver method!"+ "\n");
	}
	
	private static void testSudoku7() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 7----------\n");

		String in = "3 1\n"
				+ "-\n"
				+ "-\n"
				+ "-\n"
				+ "2\n"
				+ "1 (3,1)\n"
				+ "2 (2,1) (1,1)";

		int[][] expected = {{1},{2},{1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 7.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 7 for the solver method!"+ "\n");
	}
	
	private static void testSudoku8() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 8----------\n");

		String in = "7 7\n"
				+ "2 - - - 2 - 1\n"
				+ "- - - 4 - - -\n"
				+ "4 - - - - - -\n"
				+ "- 3 - - - 3 -\n"
				+ "- - - - - - 4\n"
				+ "- - - 2 - - -\n"
				+ "1 - 3 - - - 3\n"
				+ "10\n"
				+ "5 (1,1) (2,1) (2,2) (3,1) (3,2)\n"
				+ "5 (1,2) (1,3) (1,4) (2,4) (2,3)\n"
				+ "5 (1,6) (1,5) (2,6) (1,7) (2,5)\n"
				+ "4 (3,3) (4,5) (3,5) (3,4)\n"
				+ "5 (4,7) (4,6) (3,6) (3,7) (2,7)\n"
				+ "5 (4,1) (4,2) (5,1) (5,2) (6,1)\n"
				+ "5 (4,3) (4,4) (5,4) (5,3) (5,5)\n"
				+ "5 (5,6) (5,7) (6,6) (6,7) (7,7)\n"
				+ "5 (7,1) (7,2) (7,3) (6,3) (6,2)\n"
				+ "5 (6,4) (6,5) (7,4) (7,5) (7,6)";

		int[][] expected = {{2,5,1,3,2,4,1},{1,3,2,4,5,3,5},{4,5,1,3,2,1,4},{2,3,2,5,4,3,2},{4,1,4,3,1,5,4},{5,2,5,2,4,2,1},{1,4,3,1,3,5,3}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 8.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 8 for the solver method!"+ "\n");
	}
	
	private static void testSudoku9() {
		System.out.println("----------TEST OUTPUT OF SUDOKU 9----------\n");

		String in = "7 7\n"
				+ "- - - - - - 1\n"
				+ "- 5 - - - - -\n"
				+ "- - 3 - - - -\n"
				+ "- - - - - - -\n"
				+ "- - - - 2 - -\n"
				+ "- - - - - 5 -\n"
				+ "4 - - - - - -\n"
				+ "11\n"
				+ "4 (4,1) (1,1) (3,1) (2,1)\n"
				+ "5 (1,2) (2,2) (3,2) (2,3) (2,4)\n"
				+ "5 (1,3) (1,4) (1,5) (1,6) (2,5)\n"
				+ "3 (3,7) (2,7) (1,7)\n"
				+ "5 (2,6) (3,6) (3,4) (3,5) (4,6)\n"
				+ "5 (3,3) (4,3) (4,4) (4,5) (5,3)\n"
				+ "5 (6,2) (6,1) (5,2) (5,1) (4,2)\n"
				+ "4 (7,1) (7,2) (6,3) (7,3)\n"
				+ "5 (7,4) (6,4) (5,4) (6,6) (6,5)\n"
				+ "5 (5,7) (5,5) (4,7) (6,7) (5,6)\n"
				+ "3 (7,6) (7,5) (7,7)";

		int[][] expected = {{2,3,2,5,1,4,1},{4,5,1,4,3,5,3},{3,2,3,2,1,4,2},{1,4,1,4,5,3,5},{2,3,2,3,2,4,1},{1,5,1,4,1,5,3},{4,2,3,2,3,2,1}};
		Game game = loadSudoku(in);
		int[][] result = game.solver();
		if (!Arrays.deepEquals(result, expected))
			System.err.println("Failed Sudoku 9.\nOutput: " + Arrays.deepToString(result) + "\nExpected: " + Arrays.deepToString(expected) + "\n");
		else
			System.out.println("Passed Sudoku test 9 for the solver method!"+ "\n");
	}
	
	
	private static void setPenalizationArray(int[] arr) {
		try {
			Class<?> clazz = Midterm.class;
			Object cc = clazz.newInstance();
	
			Field f1 = cc.getClass().getDeclaredField("penalization");
			f1.setAccessible(true);
			f1.set(cc, arr);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Failed to change penalization variable in Midterm class!");
		}
	}
	
	private static void testMidterm1() {
		System.out.println("----------TEST OUTPUT OF MIDTERM 1----------\n");

		int[] penalization = {216,190,171,59,62};
		setPenalizationArray(penalization);
		int expected = 501;
		int answer = Midterm.lost_marks(penalization);
		if (expected != answer)
			System.err.println("Failed Midterm test 1.\nOutput: " + answer + "\nExpected: " + expected + "\n");
		else
			System.out.println("Passed Midterm test 1 for the lost_marks method!"+ "\n");
	}
	
	private static void testMidterm2() {
		System.out.println("----------TEST OUTPUT OF MIDTERM 2----------\n");

		int[] penalization = {111,179,64,78,219,97,160,40};
		setPenalizationArray(penalization);
		int expected = 535;
		int answer = Midterm.lost_marks(penalization);
		if (expected != answer)
			System.err.println("Failed Midterm test 2.\nOutput: " + answer + "\nExpected: " + expected + "\n");
		else
			System.out.println("Passed Midterm test 2 for the lost_marks method!"+ "\n");
	}
	
	private static void testMidterm3() {
		System.out.println("----------TEST OUTPUT OF MIDTERM 3----------\n");

		int[] penalization = {84,235,135,229,154,185,4,114,86,106};
		setPenalizationArray(penalization);
		int expected = 745;
		int answer = Midterm.lost_marks(penalization);
		if (expected != answer)
			System.err.println("Failed Midterm test 3.\nOutput: " + answer + "\nExpected: " + expected + "\n");
		else
			System.out.println("Passed Midterm test 3 for the lost_marks method!"+ "\n");
	}
	
	private static void testMidterm4() {
		System.out.println("----------TEST OUTPUT OF MIDTERM 4----------\n");

		int[] penalization = {98,180,141,12,161,202,259,214,150,163,60,202,148,137,40,150,15,83,205,115,21,78,166,224,81};
		setPenalizationArray(penalization);
		int expected = 1056;
		int answer = Midterm.lost_marks(penalization);
		if (expected != answer)
			System.err.println("Failed Midterm test 4.\nOutput: " + answer + "\nExpected: " + expected + "\n");
		else
			System.out.println("Passed Midterm test 4 for the lost_marks method!"+ "\n");
	}
	
	private static void testMidterm5() {
		System.out.println("----------TEST OUTPUT OF MIDTERM 5----------\n");

		int[] penalization = {86,181,218,223,104,29,234,236,33,118,1,130,181,212,153,51,101,17,238,105,131,36,249,263,61,132,132,42,64,267,96,35,208,229,253,160,69,96,3,100,73,254,112,12,1,268,220,1,98,119};
		setPenalizationArray(penalization);
		int expected = 1205;
		int answer = Midterm.lost_marks(penalization);
		if (expected != answer)
			System.err.println("Failed Midterm test 5.\nOutput: " + answer + "\nExpected: " + expected + "\n");
		else
			System.out.println("Passed Midterm test 5 for the lost_marks method!"+ "\n");
	}
	
	
	
	
	public static void main(String[] args) {
		testSudoku1();
		testSudoku2();
		testSudoku3();
		testSudoku4();
		testSudoku5();
		testSudoku6();
		testSudoku7();
		testSudoku8();
		testSudoku9();
		
		System.out.println("---------------------------------------------------------------------------------\n");
		
		testMidterm1();
		testMidterm2();
		testMidterm3();
		testMidterm4();
		testMidterm5();

		System.out.println("---------------------------------------------------------------------------------\n");

		
	}
	
}
