import java.util.*;

public class ExactCover {
    
    static public List<List<Integer>> resolve(Set<Integer> X, List<List<Integer>> C) {
        List<List<Integer>> l = new LinkedList<List<Integer>>();
        if (X.isEmpty()) {
            l.add(new LinkedList<Integer>());
        }
        else {
            Integer x = X.iterator().next();
            for (List<Integer> S : C) {
                if (S.contains(x)) {
                    Set<Integer> X_star = new HashSet<Integer>(X);
                    List<List<Integer>> C_star = new LinkedList<List<Integer>>(C);
                    for (Integer y : S) {
                        X_star.remove(y);
                        for (List<Integer> T : C) {
                            if (T.contains(y)) {
                                C_star.remove(T);
                            }
                        }
                    }
                    List<List<Integer>> r = resolve(X_star, C_star);
                    System.out.println(r);
                    for (List<Integer> P : r) {
                        P.addAll(S);
                        l.add(P);
                    }
                }
            }
        }
        return l;
    }
}
