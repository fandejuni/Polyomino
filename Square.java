public class Square {
    public int x;
    public int y;

    public Square(int new_x, int new_y) {
        x = new_x;
        y = new_y;
    }

    public Square(Square s) {
        x = s.x;
        y = s.y;
    }

    public void reflection_horizontal() {
        y = -y;
    }
    
    public void reflection_vertical() {
        x = - x;
    }

    public void rotation() {
        int temp = x;
        x = -y;
        y = temp;
    }

    public void anti_rotation() {
        int temp = x;
        x = y;
        y = -temp;
    }

    public void invert() {
        x = -x;
        y = -y;
    }

    public void add(Square s) {
        x = x + s.x;
        y = y + s.y;
    }

    public static Square add(Square a, Square b) {
        Square s = new Square(a);
        s.add(b);
        return s;
    }

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
