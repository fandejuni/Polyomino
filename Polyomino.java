import java.util.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Polyomino {
	
    public Set<Square> squares;
	
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

	public String toString() {
		return squares.toString();
	}

    public static List<Polyomino> openFile() {
        Path path = Paths.get("/home/thibault/Polyomino/", "polyominoesINF421.txt");
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
}
