public class Square {
    public int x;
    public int y;

    public Square(int new_x, int new_y) {
        x = new_x;
        y = new_y;
    }

    public void add(Square s) {
        x = x + s.x;
        y = y + s.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
