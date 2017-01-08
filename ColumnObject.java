
public class ColumnObject extends DataObject{
	public int S;
	public String N;
		
	public ColumnObject(DataObject U, DataObject D, DataObject L, DataObject R, ColumnObject C, int S, String N){
		super(U, D, L, R, C);
		this.S = S;
		this.N = N;
		}
}
