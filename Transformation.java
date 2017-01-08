import java.util.*;

public class Transformation {

    Map<Square, Integer> which_id;
    Map<Integer, Square> anti_which_id;
    int[][] M;
    int n;

    public void solve() {
        List<List<Integer>> r = ExactCover.resolve(M);
        List<Polyomino> l = new LinkedList<Polyomino>();

        for (List<Integer> u : r) {
            Polyomino p = new Polyomino();
            for (Integer x : u) {
                if (x <= n)
                    p.squares.add(anti_which_id.get(x - 1));

            }
            l.add(p);
        }

        System.out.println(r);
        Manipulate.draw_exactly(l, 50);

    }

    // Possible values for status
    // 1: atMostOnce
    // 2: exactlyOnce
    // 3: reusable
    public Transformation(Polyomino ground, List<Polyomino> collection, boolean rotation_allowed, String status) {
        Square origin = new Square(0, 0);
        ground.putOrigin(origin);
        Square dimensions = ground.getDimensions();
        int WIDTH = dimensions.x;
        int HEIGHT = dimensions.y;
        int n_polyominos = collection.size();
        if (status == "reusable")
            n_polyominos = 0;
        
        // We give a number to each square of ground
        which_id = new HashMap<Square, Integer>();
        anti_which_id = new HashMap<Integer, Square>();
        int i = 0;
        for (Square x : ground.squares) {
            which_id.put(x, i);
            anti_which_id.put(i, x);
            i++;
        }
        n = i;

        List<int[]> almost_M = new LinkedList<int[]>();

        int ID = 0;
        for (Polyomino P : collection) {
            List<Polyomino> rotations = new LinkedList<Polyomino>();
            if (rotation_allowed)
                rotations = P.turnAround();
            else
                rotations.add(P);
            for (Polyomino p : rotations) {
                Square dim = p.getDimensions();
                int width = dim.x;
                int height = dim.y;
                if (status == "atMostOnce") {
                    int[] line = new int[n + n_polyominos];
                    line[n + ID] = 1;
                    almost_M.add(line);
                }
                for (int x = 0; x < WIDTH - width + 1; x++) {
                    for (int y = 0; y < HEIGHT - height + 1; y++) {
                        p.putOrigin(new Square(x, y));
                        if (ground.includes(p)) {
                            int[] line = new int[n + n_polyominos];
                            for (Square s : p.squares) {
                                line[which_id.get(s)] = 1;
                            }
                            if (status != "reusable")
                                line[n + ID] = 1;
                            almost_M.add(line);
                        }
                    }
                }
            }
            ID++;
        }
        
        // Eventually, we convert almost_M to a matrix
        M = new int[almost_M.size()][n];
        i = 0;
        for (int[] line : almost_M) {
            M[i] = line;
            i++;
        }
    }

}
