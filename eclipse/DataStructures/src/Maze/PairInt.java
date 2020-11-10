// Brandon Cao

package Maze;

public class PairInt {
	
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	// getters
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	//setters
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		if(!(p instanceof PairInt)) {
			return false;
		} else {
			PairInt temp = (PairInt) p;
			return (this.x == temp.getX() && this.y == temp.getY());
		}
	}
	
	public String toString() {
		return "(" + x + "," + y +")";
	}
	
	public PairInt copy() {
		PairInt temp= new PairInt(x, y);
		return temp;
	}

}
