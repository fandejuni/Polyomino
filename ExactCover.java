import java.util.*;

public class ExactCover {
	
	static public List<List<Integer>> resolve(int[][] M) {
		List<List<Integer>> l = new LinkedList<List<Integer>>();
	
		int n = M.length;
		if (n==0) {l.add(new LinkedList<Integer>());}
		else {
			int m = M[0].length;
			if (m==0) {l.add(new LinkedList<Integer>());}
			else {
				for (int i = 0; i < m; i++) {
					if (M[i][x]==1) {
						List<Integer> S = new LinkedList<Integer>();
						S.add(x);
						for (int j = 0; j < n; j++) {
							if (M[i][j]==1) {
								S.add(j);
								for (int k = 0; k < m; k++) {
									if (k!=i) {
										if (M[k][j]==1) {
											for (int p = 0; p < n; p++) {
												M[k][p]=0;
											}
										}
									}
								}
								M[i][j]=0;
							}
						}
						for (List<Integer> P : resolve(M)) {
							P.addAll(S);
							l.add(P);
						}
						
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
