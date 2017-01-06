import java.util.*;
import java.awt.Color;

public class Polyomino {
	
    public Set<Square> squares;
    static public int width = 50;
    static public Color default_color = Color.RED;

    @Override
    public boolean equals(Object o) {
        
        if (o == this) return true;
        if (!(o instanceof Polyomino)) {
            return false;
        }
        Polyomino s = (Polyomino) o;
		return squares.equals(s.squares);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * squares.hashCode();
        return result;
    }

    private void log(String s) {
        System.out.println(s);
    }

    public Polyomino() {
        squares = new HashSet<Square>();
    }

    public Polyomino(Set<Square> carres) {
        squares = carres;
    }

    // Creates a Polyomino from text
	public Polyomino(String s) {
        squares = new HashSet<Square>();
        int i = 1;
        int a = 0;
        int b = 0;
        boolean premier = true;
        while (s.charAt(i) != ']') {
            char c = s.charAt(i);
            if (c == ',') {
                premier = !premier;
            }
            else if (c == ')') {
                squares.add(new Square(a, b));
                a = 0;
                b = 0;
            }
            else if (c != '(' && c != ' ') {
                int x = Character.getNumericValue(c);
                if (premier) {
                    a = 10 * a + x;
                }
                else {
                    b = 10 * b + x;
                }
            }
            i++;
        }
	}
    
    public Polyomino clone() {
        Set<Square> new_squares = new HashSet<Square>();
        for (Square s : squares) {
            new_squares.add(s.clone());
        }
        return new Polyomino(new_squares);
    }

    // Returns the smallest rectangle containing the Polyomino
    public Square[] getCoordinates() {
        int m_x = 10000000;
        int m_y = 10000000;
        int M_x = 0;
        int M_y = 0;
        for (Square s : squares) {
            m_x = Math.min(m_x, s.x);
            m_y = Math.min(m_y, s.y);
            M_x = Math.max(M_x, s.x);
            M_y = Math.max(M_y, s.y);
        }
        return new Square[]{new Square(m_x, m_y), new Square(M_x, M_y)};
    }

    public Square getDimensions() {
        Square[] coords = getCoordinates();
        int width = coords[1].x - coords[0].x;
        int height = coords[1].y - coords[0].y;
        return new Square(width, height);
    }

    public Square getOrigin() {
        return getCoordinates()[0];
    }

    // Translates the Polyomino in order to make origin its new origin
    public void putOrigin(Square origin) {
        Square current_origin = getOrigin();
        current_origin.invert();
        Square offset = Square.add(origin, current_origin);
        translation(offset);
    }
 
    // Translates the Polyomino to the left and to the top, until possible
    public void canonicalForm() {
        putOrigin(new Square(0, 0));
    }

    // Translates the Polyomino
    public void translation(Square t) {
        for (Square s : squares) {
            s.add(t);
        }
    }

    // Dilates the Polyomino
    public void dilation(int lambda) {
        Set<Square> new_squares = new HashSet<Square>();
        for (Square s : squares) {
            for (int x = 0; x < lambda; x++) {
                for (int y = 0; y < lambda; y++) {
                    new_squares.add(new Square(lambda * s.x + x, lambda * s.y + y));
                }
            }
        }
        squares = new_squares;
    }

    // Reflects the Polyomino with respect to horizontal axis
    public void reflection_horizontal() {
        Square old_center = getOrigin();
        for (Square s : squares) {
            s.reflection_horizontal();
        }
        putOrigin(old_center);
   }
        
    // Reflects the Polyomino with respect to vertical axis
    public void reflection_vertical() {
        Square old_center = getOrigin();
        for (Square s : squares) {
            s.reflection_vertical();
        }
        putOrigin(old_center);
    }

    // Rotates the Polyomino in sens trigo
    public void rotation() {
        Square old_center = getOrigin();
        System.out.println(old_center);
        for (Square s : squares) {
            s.rotation();
        }
        putOrigin(old_center);
    }

    // Rotates the Polyomino in sens anti-trigo
    public void anti_rotation() {
        Square old_center = getOrigin();
        for (Square s : squares) {
            s.anti_rotation();
        }
        putOrigin(old_center);
    }

    public boolean isConnected() {

        if (squares.size() == 0) {
            return false;
        }
        else {

            Set<Square> vus = new HashSet<Square>();
            LinkedList<Square> a_voir = new LinkedList<Square>();
            Square first = squares.iterator().next();
            a_voir.add(first);
            vus.add(first);

            while (!a_voir.isEmpty()) {

                Square s = a_voir.pop();

                List<Square> directions = new LinkedList<Square>();
                directions.add(new Square(1, 0));
                directions.add(new Square(-1, 0));
                directions.add(new Square(0, 1));
                directions.add(new Square(0, -1));

                for (Square d : directions) {
                    d.add(s); 
                    if (!vus.contains(d) && squares.contains(d)) {
                        a_voir.add(d);
                        vus.add(d);
                    }
                }
            }
            return (squares.size() == vus.size());
        }
    }

    // Draw a Polyomino
    public void draw() {
        Square dim = getDimensions();
        Image2d img = new Image2d((dim.x + 2) * width, (dim.y + 2) * width);
        for (Square corner : squares)
		{
            int x1 = (corner.x + 1) * width;
            int x2 = (corner.x + 2) * width;
            int y1 = (corner.y + 1) * width;
            int y2 = (corner.y + 2) * width;
            int[] the_x = new int[]{x1, x1, x2, x2};
            int[] the_y = new int[]{y1, y2, y2, y1};
			img.addPolygon(the_x, the_y, default_color);
		}
		new Image2dViewer(img);
    }
 
	public String toString() {
		return squares.toString();
	}

}
