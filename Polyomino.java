import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Polyomino {
	
    public Set<Square> squares;
    static private int width = 20;
    static private Color default_color = Color.RED;
	
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

    // Returns the smallest rectangle containing the Polyomino
    public Square[] getDimensions() {
        int m_x = 1000;
        int m_y = 1000;
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

    // Reflectes the Polyomino with respect to horizontal axis
    public void reflection_horizontal() {
        Square old_dimensions = getDimensions()[1];
        for (Square s : squares) {
            s.reflection_horizontal();
        }
        Square new_dimensions = getDimensions()[1];
        new_dimensions.invert();
        Square offset = Square.add(old_dimensions, new_dimensions);
        for (Square s : squares) {
            s.add(offset);
        }
    }
        
   // Reflectes the Polyomino with respect to vertical axis
    public void reflection_vertical() {
        Square old_dimensions = getDimensions()[1];
        for (Square s : squares) {
            s.reflection_vertical();
        }
        Square new_dimensions = getDimensions()[1];
        new_dimensions.invert();
        Square offset = Square.add(old_dimensions, new_dimensions);
        for (Square s : squares) {
            s.add(offset);
        }
    }

    // Creates Polyominos from a file
    public static List<Polyomino> openFile() {
    	String basePath = new File("").getAbsolutePath();
        Path path = Paths.get(basePath, "polyominoesINF421.txt");
        Charset charset = Charset.forName("UTF-8");
        List<Polyomino> polyominos = new LinkedList<Polyomino>();
		try {
			List<String> lines = Files.readAllLines(path, charset);
			for (String line : lines) {
				polyominos.add(new Polyomino(line));
			}
		} catch (IOException e) {
			System.out.println(e);
		}
        return polyominos;
    }

    // Draw a Polyomino
    public void draw() {
        Image2d img = new Image2d(800,800);
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
 
    // Draw polyominoes
    public static void draw(List<Polyomino> l) {
        Set<Square> the_squares = new HashSet<Square>();
        int offset = 0;
        for (Polyomino p : l) {
            Square[] dimensions = p.getDimensions();
            p.translation(new Square(offset, 0));
            for (Square s : p.squares) {
                the_squares.add(s);
            }
            offset = offset + dimensions[1].x - dimensions[0].x + 2;
        }
        Image2d img = new Image2d(800,800);
        for (Square corner : the_squares)
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
