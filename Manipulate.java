import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Manipulate {

    public static List<Polyomino> HI(int P) {
        Set<Square> vus = new HashSet<Square>();
        LinkedList<Square> untried = new LinkedList<Square>();
        Square origin = new Square(0, 0);
        untried.add(origin);
        vus.add(origin);
        List<Polyomino> l = new LinkedList<Polyomino>();
        return HI_aux(P, new Polyomino(), untried, vus, l);
    }
   
    private static List<Polyomino> HI_aux(int P, Polyomino parent, LinkedList<Square> untried, Set<Square> vus, List<Polyomino> l) {
        while (!untried.isEmpty()) {
            Square point = untried.pop();
            Square anti_point = point.clone();
            anti_point.reflection_horizontal();
            parent.squares.add(point);
            if (point.y > 0) {
                parent.squares.add(anti_point);
            }

            if (parent.squares.size() <= P)
                l.add(parent.clone());
            if (parent.squares.size() < P) {
               List<Square> voisins = getNeighbours(point);
               LinkedList<Square> copy_untried = (LinkedList<Square>) untried.clone();
               Set<Square> copy_vus = new HashSet<Square>(vus);
               for (Square v : voisins) {
                   if (!vus.contains(v) && v.y >= 0 && (v.x >= 0 || v.y > 0)) {
                       copy_untried.add(v);
                       copy_vus.add(v);
                   }
               }
               HI_aux(P, parent, copy_untried, copy_vus, l);
            }
            parent.squares.remove(point);
            if (point.y > 0) {
                parent.squares.remove(anti_point);
            }
        }
        return l;
    }

    // VI counter
    public static List<Polyomino> VI(int P) {
        Set<Square> vus = new HashSet<Square>();
        LinkedList<Square> untried = new LinkedList<Square>();
        Square origin = new Square(0, 0);
        untried.add(origin);
        vus.add(origin);
        List<Polyomino> l = new LinkedList<Polyomino>();
        return VI_aux(P, new Polyomino(), untried, vus, l);
    }
   
    private static List<Polyomino> VI_aux(int P, Polyomino parent, LinkedList<Square> untried, Set<Square> vus, List<Polyomino> l) {
        while (!untried.isEmpty()) {
            Square point = untried.pop();
            Square anti_point = point.clone();
            anti_point.reflection_vertical();
            parent.squares.add(point);
            if (point.x > 0) {
                parent.squares.add(anti_point);
            }
            if (parent.squares.size() <= P)
                l.add(parent.clone());
            if (parent.squares.size() < P) {
               List<Square> voisins = getNeighbours(point);
               LinkedList<Square> copy_untried = (LinkedList<Square>) untried.clone();
               Set<Square> copy_vus = new HashSet<Square>(vus);
               for (Square v : voisins) {
                   if (!vus.contains(v) && v.x >= 0 && (v.y >= 0 || v.x > 0)) {
                       copy_untried.add(v);
                       copy_vus.add(v);
                   }
               }
               VI_aux(P, parent, copy_untried, copy_vus, l);
            }
            parent.squares.remove(point);
            if (point.x > 0) {
                parent.squares.remove(anti_point);
            }
        }
        return l;
    }




    private static List<Square> getNeighbours(Square origin) {
        List<Square> directions = new LinkedList<Square>();
        directions.add(new Square(0, 1));
        directions.add(new Square(1, 0));
        directions.add(new Square(-1, 0));
        directions.add(new Square(0, -1));
        for (Square x : directions)
            x.add(origin);
        return directions;
    }

    private static void log(String s) {
        System.out.println("Manipulate: " + s);
    }
    
    public static List<Polyomino> HX_VX(int p) {
        List<Polyomino> l = new LinkedList<Polyomino>();
        if (p % 2 == 1) {
            return l;
        }
        else {
            List<Polyomino> N = allFixedRedeimer(p/2);
            System.out.println(N.size());
            for (Polyomino n : N) {
                Polyomino x = n.clone();
                Polyomino y = n.clone();
                x.double_vertical();
                y.double_horizontal();
                l.add(x);
                l.add(y);
            }
            return l;
        }
    }
    
    // Generates all fixed polyominoes following Redeimer's method
    public static List<Polyomino> allFixedRedeimer(int P) {
        Set<Square> vus = new HashSet<Square>();
        LinkedList<Square> untried = new LinkedList<Square>();
        Square origin = new Square(0, 0);
        untried.add(origin);
        vus.add(origin);
        List<Polyomino> l = new LinkedList<Polyomino>();
        return auxFixedRedeimer(P, new Polyomino(), untried, vus, l);
    }

    private static List<Polyomino> auxFixedRedeimer(int P, Polyomino parent, LinkedList<Square> untried, Set<Square> vus, List<Polyomino> l) {
        while (!untried.isEmpty()) {
            Square point = untried.pop();
            parent.squares.add(point);
            l.add(parent.clone());
            if (parent.squares.size() < P) {
               List<Square> voisins = getNeighbours(point);
               LinkedList<Square> copy_untried = (LinkedList<Square>) untried.clone();
               Set<Square> copy_vus = new HashSet<Square>(vus);
               for (Square v : voisins) {
                   if (!vus.contains(v) && v.x >= 0 && (v.y >= 0 || v.x > 0)) {
                       copy_untried.add(v);
                       copy_vus.add(v);
                   }
               }
               auxFixedRedeimer(P, parent, copy_untried, copy_vus, l);
            }
            parent.squares.remove(point);
        }
        return l;
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

        // Second : we check whether it's connected or not
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
//    	Collections.sort(l, new Comparator<Polyomino>(){
//
//    		  public int compare(Polyomino p1, Polyomino p2){
//
//    		    return p1.getDimensions().y-p2.getDimensions().y;
//
//    		  }
//
//    		});
        Set<Square> the_squares = new HashSet<Square>();
        int offsetH = 0;
        int offsetV = 0;
        int max = 0;
        int current;
        int xmax = 75;
        for (Polyomino p : l) {
        	current = p.getDimensions().y;
        	if (current > max) {max = current;}
            Color color = Utilities.randomColor();
            Square[] dimensions = p.getCoordinates();
            if (offsetH + p.getDimensions().x + 2 > xmax) {
            	offsetH = 0;
            	offsetV += max + 2;
            	max = 0;
            }
            p.translation(new Square(offsetH, offsetV));
            for (Square s : p.squares) {
                s.color = color;
                the_squares.add(s);
            }
            offsetH += dimensions[1].x - dimensions[0].x + 2;
        }
        Image2d img = new Image2d(1000, 500);
        for (Square corner : the_squares)
		{
            int x1 = (corner.x + 1) * Polyomino.width;
            int x2 = (corner.x + 2) * Polyomino.width;
            int y1 = (corner.y + 1) * Polyomino.width;
            int y2 = (corner.y + 2) * Polyomino.width;
            int[] the_x = new int[]{x1, x1, x2, x2};
            int[] the_y = new int[]{y1, y2, y2, y1};
			img.addPolygon(the_x, the_y, corner.color);
		}
		new Image2dViewer(img);
    }

}
