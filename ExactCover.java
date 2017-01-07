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
    
    static public List<List<Integer>> generate(int a, int b, int k) {
    	List<List<Integer>> l = new LinkedList<List<Integer>>();
    	if (b-a + 1 < k || a > b) {return l;}
    	if (k==0) {
        	l.add(new LinkedList<Integer>());
    		return l;
    	} 
    	List<List<Integer>> l1 = generate(a+1,b,k);
    	for (List<Integer> s : generate(a+1,b,k-1)) {
    		s.add(a);
    		l.add(s);
    	}
    	l.addAll(l1);
    	return(l);
    }
}
