import java.util.*;

public class ExactCover {
	
	static public List<List<Integer>> resolve(int[][] M) {
		List<List<Integer>> l = new LinkedList<List<Integer>>();
	
		int m = M.length;
		if (m==0) {l.add(new LinkedList<Integer>());}
		else {
			int n = M[0].length;
			boolean isNull = true; 
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (M[i][j] == 1) {
						isNull = false;
					}
				}
			}
			if (n==0 || isNull) {
				if (n==0) {l.add(new LinkedList<Integer>());}
			}
			else {
				/*int a = 0; // x is the index of the column of the first 1 which appears in M 
				int b = 0;
				while (M[a][b]!=1) {
					if (b==n-1) {
						a++;
						b=0;
					}
					else {
						b++;
					}
				}
				int x = b;	*/ 
				int x = 0; // x minimizes the number of sets S containing x
				int min = m;
				for (int j = 0; j < n; j++) {
					int current = 0;
					for (int i = 0; i < m; i++) {
						current += M[i][j];
					}
					if (current <= min && current > 0) {
						min = current;
						x = j;
					}
				}
				for (int i = 0; i < m; i++) {
					if (M[i][x]==1) {
						int[][] M_star = new int[m][n];
						M_star = M;
						List<Integer> S = new LinkedList<Integer>();
						for (int j = 0; j < n; j++) {
							if (M_star[i][j]==1) {
								S.add(j+1);
								for (int k = 0; k < m; k++) {
									if (k!=i) {
										if (M_star[k][j]==1) {
											for (int p = 0; p < n; p++) {
												M_star[k][p]=0;
											}
										}
									}
								}
								M_star[i][j]=0;
							}
						}
						for (List<Integer> P : resolve(M_star)) {
							l.add(P);
						}
						l.add(S);
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
