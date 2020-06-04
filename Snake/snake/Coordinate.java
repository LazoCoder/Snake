package snake;

public class Coordinate {
	private int x;
	private int y;
	public Coordinate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public String toString() {
		return getX() + ", "+ getY() ;
	}
	@Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate coord = (Coordinate) obj;
        return coord.getX() == getX()&&coord.getY()==getY();
    }
}
