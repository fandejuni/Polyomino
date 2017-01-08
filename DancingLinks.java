
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
		
		ColumnObject current_column = H;
		
		DataObject[] leftmost = new DataObject[m];
		DataObject[] element = new DataObject[m];
		
		
		for(int j = 0; j < n; j++){
			
			ColumnObject next_column = new ColumnObject(null, null, current_column, null, null, 0, String.valueOf(j + 1));
			next_column.C = next_column; 
			next_column.U = next_column; 
			next_column.D = next_column;
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
			
			for(int i = 0; i < n; i++){
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

}


