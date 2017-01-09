import java.util.*;

public class DancingLinks {
	ColumnObject H;
	
	public DancingLinks(ColumnObject H){
		this.H = H;
	}
	
	// Converts a matrix into a ColumnObject
	public static ColumnObject toDancingLinks(int[][] M) {
		int m = M.length;
		int n = M[0].length;
		
		ColumnObject H = new ColumnObject(null, null, null, null, null, 0, "H");
		H.L = H;
		H.R = H;
		H.C = H;
		
		ColumnObject current_column = H;
		
		DataObject[] leftmost = new DataObject[m];
		DataObject[] element = new DataObject[m];
		
		
		for(int j = 0; j < n; j++){
			ColumnObject next_column = new ColumnObject(null, null, current_column, null, null, 0, String.valueOf(j + 1));
			next_column.U = next_column; 
			next_column.D = next_column; 
			next_column.C = next_column;
			current_column.R = next_column;
			current_column = next_column;
			
			DataObject current_data = current_column;
			int nb_ones = 0;
			
			for (int i = 0; i < m; i++){
				if (M[i][j] == 1){
					nb_ones ++;
					DataObject next_data = new DataObject(current_data, null, null, null, current_column);
					current_data.D = next_data;
					current_data = next_data;
					
					if (leftmost[i] == null){
						leftmost[i] = current_data;
						current_data.L = current_data;
						current_data.R = current_data;
					}
					
					else{
						current_data.L = element[i];
						element[i].R = current_data;
					}
					element[i] = current_data;
				}
			}
			
			for(int i = 0; i < m; i++){
				if (leftmost[i] != null && leftmost[i] != element[i]){
					leftmost[i].L = element[i];
					element[i].R = leftmost[i];
				}
			}
					
			current_column.S = nb_ones;
			current_column.U = current_data;
			current_data.D = current_column;
		}
				
		current_column.R = H;
		H.L = current_column;
		return H;
	}
	
	// Covers a column
	public static void coverColumn(ColumnObject x) {
		x.R.L = x.L;
		x.L.R = x.R;
		
		DataObject t = x.D;
		
		while (t != x) {
			DataObject y=t.R;
			while (y != t) {
				y.D.U = y.U;
				y.U.D = y.D;
				y.C.S --;
				y = y.R;
			}
			t = t.D;
		}
		
	}
	
	// Uncovers a column
	public static void uncoverColumn(ColumnObject x) {
		x.R.L = x;
		x.L.R = x;
		
		DataObject t = x.U;
		
		while (t != x) {
			DataObject y=t.L;
			while (y != t) {
				y.D.U = y;
				y.U.D = y;
				y.C.S ++;
				y = y.L;
			}
			t = t.U;
		}
		
	}
	
	// Returns all the solutions the exact cover problem using the DancingLinks structure
	public static List<List<List<Integer>>> exactCover(ColumnObject H) {
		List<List<List<Integer>>> l = new LinkedList<List<List<Integer>>>();
		if (H.R == H) {l.add(new LinkedList<List<Integer>>());}
		else {
			ColumnObject var = (ColumnObject) H.R;
			int min = var.S;
			ColumnObject x = var;
			while (var != H) {
				var = (ColumnObject) var.R;
				if (var.S < min && var.S > 0) {
					min = var.S;
					x = var;
				}
			}
			coverColumn(x);
			DataObject t = x.U;
			while (t != x) {
				DataObject y = t.L;
				List<Integer> t_values = new LinkedList<Integer>();
				t_values.add(Integer.parseInt(x.N));
				while (y != t) {
					coverColumn(y.C);
					t_values.add(Integer.parseInt(y.C.N));
					y = y.L;
				}
				for (List<List<Integer>> P : exactCover(H)) {
					P.add(t_values);
					l.add(P);
				}

				y = t.R;
				while (y != t) {
					uncoverColumn(y.C);
					y = y.R;
				}
				t = t.U;
			}
			uncoverColumn(x.C);
		}
		return l;
	}


	// Returns a solution of the exact cover problem using the DancingLinks structure
	public static List<List<Integer>> exactCover2(ColumnObject H) {
		List<List<List<Integer>>> l = new LinkedList<List<List<Integer>>>();
		if (H.R == H) {l.add(new LinkedList<List<Integer>>());}
		else {
			ColumnObject var = (ColumnObject) H.R;
			int min = var.S;
			ColumnObject x = var;
			while (var != H) {
				var = (ColumnObject) var.R;
				if (var.S < min && var.S > 0) {
					min = var.S;
					x = var;
				}
			}
			coverColumn(x);
			DataObject t = x.U;
			while (t != x) {
				DataObject y = t.L;
				List<Integer> t_values = new LinkedList<Integer>();
				t_values.add(Integer.parseInt(x.N));
				while (y != t) {
					coverColumn(y.C);
					t_values.add(Integer.parseInt(y.C.N));
					y = y.L;
				}
				for (List<List<Integer>> P : exactCover(H)) {
					P.add(t_values);
					return P;
				}
	
				y = t.R;
				while (y != t) {
					uncoverColumn(y.C);
					y = y.R;
				}
				t = t.U;
			}
			uncoverColumn(x.C);
		}
		return new LinkedList<List<Integer>>();
	}
	
}


