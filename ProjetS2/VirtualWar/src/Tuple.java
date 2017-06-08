
public class Tuple {
	
	public static final int UNKNOWN = 12;
	public static final int SELECTION = 9;
	public static final int DEPLACEMENT = 10;
	public static final int ACTION = 11;
	protected int x;
	protected int y;
	protected int couleur = UNKNOWN;
	
	public Tuple(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Tuple(int x, int y, int couleur){
		this(x, y);
		this.couleur = couleur;
	}
	
}
