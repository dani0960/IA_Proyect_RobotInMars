package Heurística;

public class Manhattan {

	public static int manhattan (Node a, Node b) {
		return Math.abs(a.getX()-b.getX())+ Math.abs(a.getY()-b.getY());
	}
}
