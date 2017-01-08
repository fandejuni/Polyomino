import java.awt.Color;

public class Square {
    public int x;
    public int y;
    public Color color;

    @Override
	public boolean equals(Object o) {
		if ((o == null) || !(o instanceof Square)) {
			return false;
		}
        Square s = (Square) o;
		return	x == s.x && y == s.y;
	}

    @Override
    public int hashCode() {
        return 1;
    }

    // Creates a Square from coordinates and color
    public Square(int new_x, int new_y) {
        x = new_x;
        y = new_y;
    }

    // Creates a copy of a Square
    public Square clone() {
        return new Square(x, y);
    }

    // Horizontal mirror
    public void reflection_horizontal() {
        y = -y;
    }

    public void double_horizontal() {
        y = -(y + 1);
    }
    
    // Vertical mirror
    public void reflection_vertical() {
        x = - x;
    }

    public void double_vertical() {
        x = -(x + 1);
    }

    // Rotation sens trigo
    public void rotation() {
        int temp = x;
        x = -y;
        y = temp;
    }

    // Rotation sens anti-trigo
    public void anti_rotation() {
        int temp = x;
        x = y;
        y = -temp;
    }

    // Double rotation
    public void invert() {
        x = -x;
        y = -y;
    }

    // Add two Squares
    public void add(Square s) {
        x = x + s.x;
        y = y + s.y;
    }

    public static Square add(Square a, Square b) {
        Square s = a.clone();
        s.add(b);
        return s;
    }

    // Multiply a Square by lambda
    public void dilate(int lambda) {
        x = lambda * x;
        y = lambda * y;
    } 

    public static Square dilate(int lambda, Square s) {
        Square a = s.clone();
        a.dilate(lambda);
        return a;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
