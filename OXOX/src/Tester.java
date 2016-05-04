import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	public static void main (String[] args) throws FileNotFoundException {
		Board b = new Board();
		Scanner scan = new Scanner(System.in);
		boolean turn = false;
		boolean TP = false;
		System.out.println("Welcome to 3D Tic Tac Toe! \n OXX  This game is similar to 2D Tic Tac Toe, but with a 3rd dimension! \n XOX  Keep in mind that there are 49 different ways to win \n XXO  so keep a close eye on your opponent's moves!");
		System.out.println("\nOne or Two players?");
		String play = scan.next();
		while(true){
			if(play.equals("1") || play.equalsIgnoreCase("One")){
				TP = false;
				break;
			}
			else if(play.equals("2") || play.equalsIgnoreCase("Two")){
				TP = true; 
				break;
			}
		}
		int layer; int x; int y;
		while(TP){
			turn = true;
			while(turn){
				System.out.println(b.toString());
				System.out.println("----  X  Player 1  X  ----");
				System.out.println("Which board would you like to place your X on?");
				layer = scan.nextInt();
				System.out.println("What square would you like to place your X in?");
				x = scan.nextInt();
				y = scan.nextInt();
				turn = b.Turn("X", 1, x, y, layer-1);
			}
			turn = true;
			while(turn){
				System.out.println(b.toString());
				System.out.println("----  O  Player 2  O  ----");
				System.out.println("Which board would you like to place your O on?");
				layer = scan.nextInt();
				System.out.println("What square would you like to place your O in?");
				x = scan.nextInt();
				y = scan.nextInt();
				turn = b.Turn("O", -1, x, y, layer-1);
			}
		}
		while(!TP){
			turn = true;
			while(turn){
				System.out.println(b.toString());
				System.out.println("----  X  Player 1  X  ----");
				System.out.println("Which board would you like to place your X on?");
				layer = scan.nextInt();
				System.out.println("What square would you like to place your X in?");
				x = scan.nextInt();
				y = scan.nextInt();
				turn = b.Turn("X", 1, x, y, layer-1);
			}
			System.out.println("----  O  Computer  O  ----");
			turn = true;
			while(turn){
				int[] compPlay = b.CompTurn();
				turn = b.Turn("O", -1, compPlay[0], compPlay[1], compPlay[2]);
				System.out.println("Computer played in [" + compPlay[0] + "," + compPlay[1] + "," + (compPlay[2]+1) + "].\n");
			}
			
		}
		scan.close();
	}
}
