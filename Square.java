public class Square {
    public int x;
    public int y;

    // Creates a Square from coordinates
    public Square(int new_x, int new_y) {
        x = new_x;
        y = new_y;
    }

    // Creates a copy of a Square
    public Square(Square s) {
        x = s.x;
        y = s.y;
    }

    // Horizontal mirror
    public void reflection_horizontal() {
        y = -y;
    }
    
    // Vertical mirror
    public void reflection_vertical() {
        x = - x;
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
        Square s = new Square(a);
        s.add(b);
        return s;
    }

    // Multiply a Square by lambda
    public void dilate(int lambda) {
        x = lambda * x;
        y = lambda * y;
    } 

    public static Square dilate(int lambda, Square s) {
        Square a = new Square(s);
        a.dilate(lambda);
        return a;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
