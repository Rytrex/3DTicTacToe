import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
	private Box[][][] board = new Box[3][3][3];
	private int[] win = new int[49];
	private int[][] fill = new int[27][14];
	public Board() throws FileNotFoundException {
		int[] fill0 = {0,18,15,27,29,34,39};
		board[0][0][0] = new Box(fill0);
		int[] fill1 = {3,21,15,48};
		board[0][0][1] = new Box(fill1);
		int[] fill2 = {6,24,15,30,33,38,41};
		board[0][0][2] = new Box(fill2);
		int[] fill3 = {1,18,12,45};
		board[0][1][0] = new Box(fill3);
		int[] fill4 = {4,21,12,29,30};
		board[0][1][1] = new Box(fill4);
		int[] fill5 = {7,24,12,46};
		board[0][1][2] = new Box(fill5);
		int[] fill6 = {2,18,9,28,30,31,42};
		board[0][2][0] = new Box(fill6);
		int[] fill7 = {5,21,9,47};
		board[0][2][1] = new Box(fill7);
		int[] fill8 = {8,24,9,29,32,37,40};
		board[0][2][2] = new Box(fill8);
		int[] fill9 = {0,19,16,43};
		board[1][0][0] = new Box(fill9);
		int[] fill10 = {3,22,16,33,34};
		board[1][0][1] = new Box(fill10);
		int[] fill11 = {6,25,16,44};
		board[1][0][2] = new Box(fill11);
		int[] fill12 = {1,19,13,27,28};
		board[1][1][0] = new Box(fill12);
		int[] fill13 = {4,22,13,43,44,45,46,47,48,39,40,41,42};
		board[1][1][1] = new Box(fill13);
		int[] fill14 = {7,25,13,37,38};
		board[1][1][2] = new Box(fill14);
		int[] fill15 = {2,19,10,44};
		board[1][2][0] = new Box(fill15);
		int[] fill16 = {5,22,10,31,32};
		board[1][2][1] = new Box(fill16);
		int[] fill17 = {8,25,10,43};
		board[1][2][2] = new Box(fill17);
		int[] fill26 = {0,20,17,28,33,35};
		board[2][0][0] = new Box(fill26);
		int[] fill18 = {3,23,17,47,40};
		board[2][0][1] = new Box(fill18);
		int[] fill19 = {6,26,17,34,36,37,42};
		board[2][0][2] = new Box(fill19);
		int[] fill20 = {1,20,14,46};
		board[2][1][0] = new Box(fill20);
		int[] fill21 = {4,23,14,35,36};
		board[2][1][1] = new Box(fill21);
		int[] fill22 = {7,26,14,45};
		board[2][1][2] = new Box(fill22);
		int[] fill23 = {2,20,11,27,32,36,41};
		board[2][2][0] = new Box(fill23);
		int[] fill24 = {5,23,11,48};
		board[2][2][1] = new Box(fill24);
		int[] fill25 = {8,26,11,31,35,38,39};
		board[2][2][2] = new Box(fill25);
		int hold; int count;
		Scanner scan = new Scanner(new File("globalmatrix.txt"));
		for(int i=0; i<27; i++){
			count = 0;
			while(true){
				hold = scan.nextInt();
				if(hold < 2000){
					fill[i][count] = hold;
				}
				else if (hold > 2000){
					fill[i][count] = hold;
					break;
				}
				count++;
			}
		}
	}
	public boolean Turn(String xo, int pm, int x, int y, int z){
		if(board[x][y][z].getContain(x,y) != "  X  " && board[x][y][z].getContain(x,y) != "  O  "){
			int[] update = board[x][y][z].setContain(xo);
			for(int i=0; i<update.length; i++){
				win[update[i]] = win[update[i]] + pm;
			}
			checkWin();
			return false;
		}
		System.out.println("Square is already occupied");
		return true;
	}
	private void checkWin() {
		for(int i=0; i<win.length;i++) {
			if(win[i]==3){
				System.out.println(toString());
				System.out.println("Player 1 won!");
				System.exit(0);
			}
			else if(win[i]==-3){
				System.out.println(toString());
				System.out.println("Player 2 won!");
				System.exit(0);
			}
		}
	}

	public int[] CompTurn(){
		int play = 2016;
		boolean found = false;
		int[] re = new int[3];
		for(int i=0; i<49; i++){
			if(win[i] == -2) {
				play = i;
				found = true;
				break;
			}
		}
		if(!found) {
			for(int i=0; i<49; i++){
				if(win[i] == 2) {
					play = i;
					found = true;
					break;
				}
			}
		}
		if(!found) {
			for(int i=0; i<49; i++){
				if(win[i] == -1) {
					play = i;
					found = true;
					break;
				}
			}
		}
		if(!found) {
			while(true){
				re[0] = (int) Math.round(Math.random()*3);
				re[1] = (int) Math.round(Math.random()*3);
				re[2] = (int) Math.round(Math.random()*3);
				if(!board[re[0]][re[1]][re[2]].getFilled()) return re;
			}
		}
		else {
			int c = 0;
			for(int i=0; i<27; i++){
				for(int j=0; 2000>fill[i][j]; j++){
					if(fill[i][j] == play) {
						re[c] = i;
						c++;
						break;
					}
					if(c==3) break;
				}
			}
			int[] c1 = Convert(re[0]);
			int[] c2 = Convert(re[1]);
			int[] c3 = Convert(re[2]);
			boolean[] choice = new boolean[3];
			if(board[c1[0]][c1[1]][c1[2]].getFilled()) choice[0]=true;
			if(board[c2[0]][c2[1]][c2[2]].getFilled()) choice[1]=true;
			if(board[c3[0]][c3[1]][c3[2]].getFilled()) choice[2]=true;
			while(true){
				int choose = (int) Math.round(Math.random()*3);
				if(choice[choose] == true) {
					if(choose==0){
						re[0] = c1[0];
						re[1] = c1[1];
						re[2] = c1[2];
						return re;
					}
					else if(choose==1){
						re[0] = c2[0];
						re[1] = c2[1];
						re[2] = c2[2];
						return re;
					}
					else {
						re[0] = c3[0];
						re[1] = c3[1];
						re[2] = c3[2];
						return re;
					}
				}
			}
		}
	}
	
	private int[] Convert(int box){
		int[] re = new int[3];
		if(0<=box && box<=8){
			re[2] = 0;
		}
		else if(9<=box && box<=17){
			re[2] = 1;
			box -= 9;
		}
		else if(18<=box && box<=26){
			re[2] = 2;
			box -= 18;
		}
		if(0<=box && box <=2) {
			re[1] = 0;
			re[0] = box;
		}
		else if(3<=box && box <=5){
			re[1] = 1;
			re[0] = box-3;
		}
		else if(6<=box && box <= 8){
			re[1] = 2;
			re[0] = box-6;
		}
		return re;
	}
	
	public Box[][][] getBoard() {
		return board;
	}
	public int[] getWin() {
		return win;
	}
	public String toString() {
		return  "Board 1: \n" +
				board[0][0][0].getContain(0,0) + "|" + board[1][0][0].getContain(1,0) + "|" + board[2][0][0].getContain(2,0) + "\n" +
				"-----------------" + "\n" +
				board[0][1][0].getContain(0,1) + "|" + board[1][1][0].getContain(1,1) + "|" + board[2][1][0].getContain(2,1) + "\n" +
				"-----------------" + "     " + "Board 2:" +  "\n" +
				board[0][2][0].getContain(0,2) + "|" + board[1][2][0].getContain(1,2) + "|" + board[2][2][0].getContain(2,2) + "     " + board[0][0][1].getContain(0,0) + "|" + board[1][0][1].getContain(1,0) + "|" + board[2][0][1].getContain(2,0) + "\n" +
				"                      " + "-----------------" +"\n" +
				"                      " + board[0][1][1].getContain(0,1) + "|" + board[1][1][1].getContain(1,1) + "|" + board[2][1][1].getContain(2,1) + "\n" +
				"                      " + "-----------------" + "     " + "Board 3:" + "\n" +
				"                      " +  board[0][2][1].getContain(0,2) + "|" + board[1][2][1].getContain(1,2) + "|" + board[2][2][1].getContain(2,2) + "     " + board[0][0][2].getContain(0,0) + "|" + board[1][0][2].getContain(1,0) + "|" + board[2][0][2].getContain(2,0) + "\n" +
				"                                            " + "-----------------" + "\n" +
				"                                            " + board[0][1][2].getContain(0,1) + "|" + board[1][1][2].getContain(1,1) + "|" + board[2][1][2].getContain(2,1) + "\n" +
				"                                            " + "-----------------" + "\n" +
				"                                            " +  board[0][2][2].getContain(0,2) + "|" + board[1][2][2].getContain(1,2) + "|" + board[2][2][2].getContain(2,2);
	}
}
