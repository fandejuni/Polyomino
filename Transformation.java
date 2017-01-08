import java.util.*;

public class Transformation {

    static public int[][] polyominoTiling(Polyomino ground, List<Polyomino> collection, boolean rotation_allowed, boolean reusable) {
        Square origin = new Square(0, 0);
        ground.putOrigin(origin);
        Square dimensions = ground.getDimensions();
        int WIDTH = dimensions.x;
        int HEIGHT = dimensions.y;
        
        // We give a number to each square of ground
        Map<Square, Integer> which_id = new HashMap<Square, Integer>();
        int i = 0;
        for (Square x : ground.squares) {
            which_id.put(x, i);
            i++;
        }
        int n = i;

        // Gives the ids of collections from ID
        Map<Integer, List<Integer>> collections_with_id = new HashMap<Integer, List<Integer>>();
        // Gives the ID of a collection id
        Map<Integer, Integer> id_of_collection = new HashMap<Integer, Integer>();

        List<int[]> almost_M = new LinkedList<int[]>();

        int ID = 0;
        int id = 0;
        for (Polyomino P : collection) {
            List<Integer> ids_lies_polyomino = new LinkedList<Integer>();
            List<Polyomino> rotations = new LinkedList<Polyomino>();
            if (rotation_allowed)
                rotations = P.turnAround();
            else
                rotations.add(P);
            for (Polyomino p : rotations) {
                System.out.println("");
                System.out.println("Nouveau !");
                System.out.println(p);
                Square dim = p.getDimensions();
                int width = dim.x;
                int height = dim.y;
                for (int x = 0; x < WIDTH - width + 1; x++) {
                    for (int y = 0; y < HEIGHT - height + 1; y++) {
                        System.out.println(new Square(x, y));
                        p.putOrigin(new Square(x, y));
                        if (ground.includes(p)) {
                            System.out.println("Yes");
                            int[] line = new int[n];
                            for (Square s : p.squares) {
                                line[which_id.get(s)] = 1;
                            }
                            almost_M.add(line);
                            id++;
                            ids_lies_polyomino.add(id);
                            id_of_collection.put(id, ID);
                        }
                    }
                }
            }
            collections_with_id.put(ID, ids_lies_polyomino);
            ID++;
        }
        
        // Eventually, we convert almost_M to a matrix
        int[][] M = new int[almost_M.size()][n];
        i = 0;
        for (int[] line : almost_M) {
            M[i] = line;
            i++;
        }
        System.out.println(which_id);
        System.out.println(collections_with_id);
        System.out.println(id_of_collection);
        return M;
    }
}
