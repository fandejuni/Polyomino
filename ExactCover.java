import java.util.*;

public class ExactCover {
	
	// Solves the Exact Cover problem with the first algorithm
	static public List<List<Integer>> resolve(int[][] M) {
		List<List<List<Integer>>> l = resolve_aux(M);
		int n = M[0].length;
		for (List<List<Integer>> P : l) {
			int counter = 0;
			for (List<Integer> T : P) {
				counter += T.size();
			}
			if (counter == n) {return P;}
		} 
		return new LinkedList<List<Integer>>();
	}
	
	// Returns all the solutions of the Exact Cover problem 
	static public List<List<List<Integer>>> resolve_aux(int[][] M) {
		List<List<List<Integer>>> l = new LinkedList<List<List<Integer>>>();
	
		int m = M.length;
		int n = M[0].length;
		boolean isNull = true; 
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1) {
					isNull = false;
				}
			}
		}
		
		if (isNull) {
			l.add(new LinkedList<List<Integer>>());
		}
		else {
			int x = 0; // x minimizes the number of sets S containing x
			int min = m;
			for (int j = 0; j < n; j++) {
				int current = 0;
				for (int i = 0; i < m; i++) {
					current += M[i][j];
				}
				if (current < min && current > 0) {
					min = current;
					x = j;
				}
			}
			
			for (int i = 0; i < m; i++) {
				if (M[i][x]==1) {
					int[][] M_star = new int[m][n];
					for (int u = 0; u < m; u++) {
						for (int v = 0; v < n; v++) {
							M_star[u][v] = M[u][v];
						}
					}
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

					for (List<List<Integer>> P : resolve_aux(M_star)) {
						P.add(S);
						l.add(P);
					}
				}
			}
		}

		return l;
	}
    
	// Generates the subsets of [|a,b|] of size k
    static public List<List<Integer>> generateSubsets(int a, int b, int k) {
    	List<List<Integer>> l = new LinkedList<List<Integer>>();
    	if (b-a + 1 < k || a > b) {return l;}
    	if (k==0) {
        	l.add(new LinkedList<Integer>());
    		return l;
    	} 
    	List<List<Integer>> l1 = generateSubsets(a+1,b,k);
    	for (List<Integer> s : generateSubsets(a+1,b,k-1)) {
    		s.add(a);
    		l.add(s);
    	}
    	l.addAll(l1);
    	return(l);
    }
    
    // Generates all subsets of [|1,n|] 
    static public List<List<Integer>> generateAllSubsets(int n) {
    	List<List<Integer>> l = new LinkedList<List<Integer>>();
    	for (int k = 1; k <= n; k++) {
    		l.addAll(generateSubsets(1,n+1,k));
    	}
    	return l;
    }
    
    // Generates a matrix from a list of list of integers
    static public int[][] generateMatrix(List<List<Integer>> l) {
    	int max = 0;
    	for (List<Integer> S : l) {
    		for (Integer a : S) {
    			if (a > max) {max = a;}
    		}
    	}
    	int[][] M = new int[l.size()][max]; 
    	int i = 0;
    	for (List<Integer> S : l) {
    		for (Integer a : S) {
    			M[i][a-1] = 1;
    		}
    		i++;
    	}
    	return M;
    }
   
    // Prints a given matrix
    static public void printMatrix(int[][] M) {
    	for (int[] row : M) {
    		for (int j : row) {
    			System.out.print(j + " ");
    		}
			System.out.println();
    	}
    }
}
