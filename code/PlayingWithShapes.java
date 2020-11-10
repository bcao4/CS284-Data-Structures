package classes;

public class PlayingWithShapes {

	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(4,5);
		Rectangle r2 = new Rectangle(7,9,"blue");
//		r1.setWidth(5);
//		r2.setWidth(4);
		System.out.println("Rectangle r1 has width " + r1.getWidth());
		System.out.println("Rectangle r2 has height " + r2.getHeight());
		System.out.println("Rectangle r1 has color " + r1.getColor());
		System.out.println("Rectangle r2 has color " + r2.getColor());

		Triangle t1 = new Triangle(4,5,"orange");
		Triangle t2 = new Triangle(7,9);

//		System.out.println("No of rectangles is "+ Rectangle.getNoOfRectangles());
		
		System.out.println("Triangle t1 has base " + t1.getBase());
		System.out.println("Triangle t1 has color " + t1.getColor());
	}
}
