package pathfinding;

public class app {


	
	public static void main(String[] args) {

		Topo topo = new Topo(5,5);
		int[] d = {0,2};
		int[] a = {4,2};
		Algo algo = new Algo(topo,d,a);
		topo.ToString();
		algo.Path();
		
		
		
		
		
		
	}

}