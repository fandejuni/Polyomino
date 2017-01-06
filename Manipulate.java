import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Manipulate {

    private static void log(String s) {
        System.out.println("Manipulate: " + s);
    }

    // Generates all free polyominoes in a given area
    public static List<Polyomino> generateAllFreePolyominoes(int width, int height) {
        List<Polyomino> fixed = generateAllFixedPolyominoes(width, height);
        Set<Polyomino> free = new HashSet<Polyomino>();
        
        for (Polyomino p : fixed) {
            Polyomino current = p;
            boolean in = false;
            for (int i = 0; i < 4; i++) {
                in = in || free.contains(current);
                current.rotation();
            }
            current.reflection_vertical();
            for (int i = 0; i < 4; i++) {
                in = in || free.contains(current);
                current.rotation();
            }
            if (!in) {
                free.add(p);
            }
        }
        return new LinkedList<Polyomino>(free);
    }
        

    // Generates all fixed polyominoes in a given area
    public static List<Polyomino> generateAllFixedPolyominoes(int width, int height) {
        
        // First : we generate every possible set of squares
        LinkedList<Polyomino> all = new LinkedList<Polyomino>();
        all.add(new Polyomino());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                LinkedList<Polyomino> bigger = new LinkedList<Polyomino>();
                for (Polyomino p : all) {
                    bigger.add(p);
                    Polyomino p2 = p.clone();
                    p2.squares.add(new Square(x, y));
                    bigger.add(p2);
                }
                all = bigger;
            }
        }

        // Second : we check wether it's connected or not
        // Plus, we put them in canonical forms, and add them in a set
        Set<Polyomino> checked = new HashSet<Polyomino>();
        for (Polyomino p : all) {
           if (p.isConnected()) {
               p.canonicalForm();
               checked.add(p);
               
           }
        }

        return new LinkedList<Polyomino>(checked);
    }

    // Creates Polyominoes from a file
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

    // Draw Polyominoes
    public static void draw(List<Polyomino> l) {
        Set<Square> the_squares = new HashSet<Square>();
        int offset = 0;
        Square dim = new Square(0, 0);
        for (Polyomino p : l) {
            dim.add(p.getDimensions());
            dim.add(new Square(1, 1));
            Square[] dimensions = p.getCoordinates();
            p.translation(new Square(offset, 0));
            for (Square s : p.squares) {
                the_squares.add(s);
            }
            offset = offset + dimensions[1].x - dimensions[0].x + 2;
        }
        Image2d img = new Image2d((dim.x + 2) * Polyomino.width, (dim.y + 2) * Polyomino.width);
        for (Square corner : the_squares)
		{
            int x1 = (corner.x + 1) * Polyomino.width;
            int x2 = (corner.x + 2) * Polyomino.width;
            int y1 = (corner.y + 1) * Polyomino.width;
            int y2 = (corner.y + 2) * Polyomino.width;
            int[] the_x = new int[]{x1, x1, x2, x2};
            int[] the_y = new int[]{y1, y2, y2, y1};
			img.addPolygon(the_x, the_y, Polyomino.default_color);
		}
		new Image2dViewer(img);
    }

}
