import java.util.*;

public class Polyomino {
	
    public Set<List<Integer>> squares;
	
	public Polyomino(String s) {
        squares = new HashSet<List<Integer>>();
        int i = 1;
        int a = 0;
        int b = 0;
        boolean premier = true;
        while (s.charAt(i) != ']') {
            char c = s.charAt(i);
            //if (c == ')') {
               // squares.add(
            i++;
        }
        System.out.println("?");
        System.out.println(s);
	}

}
