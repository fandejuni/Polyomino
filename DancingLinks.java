
public class DancingLinks {
	ColumnObject H;
	
	public DancingLinks(ColumnObject H){
		this.H = H;
	}
	
	public static ColumnObject toDancingLinks(int[][] M) {
		int m = M.length;
		int n = M[0].length;
		
		ColumnObject H = new ColumnObject(null, null, null, null, null, 0, "H");
		H.C = H;
		H.R = H;
		H.L = H;
	}

}
