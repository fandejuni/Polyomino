import java.awt.Color;
import java.util.*;

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

    public boolean hasImage(String s) {
        switch (s) {
            case "HI": return y > 0;
            case "VI": return x > 0;
            case "A": return y - x > 0;
            case "D": return y + x > 0;
            case "R": return true;
            case "Fixed": return false;
        }
        return false;
    }

    public Square canonicalForm(String s) {
        Square point = clone();
        if (s == "R2" && !(x == 0 && y == 0)) {
            while (!(point.x > 0 && point.y >= 0)) {
                point.rotation();
            }
        }
        return point;
    }

    public boolean isAllowed(String s) {
        switch (s) {
            case "HI": return y >= 0 && (x >= 0 || y > 0);
            case "VI": return x >= 0 && (y >= 0 || x > 0);
            case "A": return y - x >= 0 && (y - x > 0 || x >= 0);
            case "D": return y + x >= 0 && (y + x > 0 || x >= 0);
            case "R": return y > 0 || (y == 0 && x > 0);
            case "R2": return true;
            case "Fixed": return x >= 0 && (y >= 0 || x > 0);
        }
        return true;
    }

    public List<Square> images(String s) {
        List<Square> l = new LinkedList<Square>();
        if (s == "R2") {
            image(s);
            l.add(clone());
            image(s);
            l.add(clone());
            image(s);
            l.add(clone());
            image(s);
        }
        else {
            if (hasImage(s)) {
                image(s);
                l.add(clone());
                image(s);
            }
        }
        return l;
    }
 
    public void image(String s) {
        switch (s) {
            case "HI": reflection_horizontal();
                break;
            case "VI": reflection_vertical();
                break;
            case "R": rotation_pi();
                break;
            case "A": reflection_diagonal();
                break;
            case "D": reflection_anti_diagonal();
                break;
            case "R2": rotation();
                break;
        }
    }
    
    public void rotation_pi() {
        x = -x;
        y = -y;
    }

    // Reflection wrt the positive diagonal
    public void reflection_anti_diagonal() {
        int temp = x;
        x = -y;
        y = -temp;
    }


    // Reflection wrt the positive diagonal
    public void reflection_diagonal() {
        int temp = x;
        x = y;
        y = temp;
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
