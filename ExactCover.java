import java.util.*;

public class ExactCover {
	
	static public List<List<Integer>> resolve(int[][] M) {
		List<List<Integer>> l = new LinkedList<List<Integer>>();
	
		int m = M.length;
		if (m==0) {l.add(new LinkedList<Integer>());}
		else {
			int n = M[0].length;
			System.out.println(m + " " +n);
			int[][] A = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					A[i][j] = 0;
					System.out.println(M[i][j]);
				}
			}
			System.out.println(M.equals(A));
			if (n==0 || M.equals(A)) {l.add(new LinkedList<Integer>());System.out.println("ok");}
			else {
				int a = 0;
				int b = 0;
				while (M[a][b]!=1) {
					System.out.println(a +  " "+ b + " " + M[a][b]);
					if (b==n-1) {
						a++;
						b=0;
					}
					else {
						b++;
					}
				}
				int x = b;	
				for (int i = 0; i < m; i++) {
					if (M[i][x]==1) {
						List<Integer> S = new LinkedList<Integer>();
						S.add(x);
						for (int j = 0; j < n; j++) {
							if (M[i][j]==1) {
								S.add(j);
								for (int k = 0; k < m; k++) {
									if (k!=i) {
										System.out.println(k);
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
