
public class Box {
	private String contain;
	private int[] winCond;
	private boolean filled = false;
	public Box(int[] in){
		winCond = in;
		contain = " ";
	}
	public String getContain(int x, int y) {
		if(contain == " ") return "(" + x + "," + y + ")";
		else return "  " + contain + "  ";
	}
	public int[] setContain(String contain) {
		this.contain = contain;
		filled = true;
		return winCond;
	}
	public boolean getFilled(){
		return filled;
	}
	
}
