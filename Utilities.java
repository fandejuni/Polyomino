import java.awt.Color;
import java.util.*;

public class Utilities {

    public static Color randomColor() {
        Random generator = new Random();
        float r = generator.nextFloat();
        float g = generator.nextFloat();
        float b = generator.nextFloat();
        return new Color(r, g, b);
    }

    private static List<Square> getDirections() {
        List<Square> directions = new LinkedList<Square>();
        directions.add(new Square(1, 0));
        directions.add(new Square(-1, 0));
        directions.add(new Square(0, 1));
        directions.add(new Square(0, -1));
        return directions;
    }

    // pos is valid if in possible or if x, y >= 0 if possible is empty
    private static boolean isValid(Square pos, Set<Square> possible) {
        if (possible.isEmpty()) {
            return pos.x >= 0 && (pos.y >= 0 || pos.x > 0);
        }
        else {
            return possible.contains(pos);
        }
    }

    public static List<Square> getNeighbours(Square origin, Set<Square> possible, Set<Square> vus) {
        
        List<Square> directions = getDirections();
        List<Square> l = new LinkedList<Square>();

        for (Square d : directions) {
            d.add(origin); 
            if (!vus.contains(d) && isValid(d, possible)) {
                l.add(d);
            }
        }
        return l;
    }
}
