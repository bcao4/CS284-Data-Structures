package classes;

public class Triangle extends Shape {
	// Data fields
	private int base;
	private int height;
	
	// Constructor
	
	public Triangle(int base, int height) {
		this(base,height,"not set");
	}
	
	public Triangle(int base, int height, String color) {
		super(color);
		this.base = base;
		this.height = height;
	}



	// Methods
	
	public int getBase() {
		return base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	
	
	
}
