package snake;
/**
 * Represents a square on the board. Each Square has an Entity. An Entity
 * is what is on the Square: either there is food, a piece of the snake,
 * or it is empty.
 */
class Square {

    private Entity entity;
    private Coordinate coord;

    /**
     * Construct the Square with nothing on it.
     * @param x         the x coordinate of the square
     * @param y         the y coordinate of the square
     */
    public Square (int x, int y) {
        this(Entity.Empty, x, y);
    }

    /**
     * Construct the Square with a specified entity.
     * @param entity    the entity that is on the square
     * @param x         the x coordinate of the square
     * @param y         the y coordinate of the square
     */
    public Square (Entity entity, int x, int y) {
        this.entity = entity;
        this.coord=new Coordinate(x,y);
    }

    /**
     * Change what is on the Square.
     * @param entity    the new entity
     */
    public void setEntity (Entity entity) {
        this.entity = entity;
    }

    /**
     * Get what is on the Square.
     * @return          the entity on the Square
     */
    public Entity getEntity () {
        return entity;
    }
    
    public int getX () {
        return coord.getX();
    }

    public int getY () {
        return coord.getY();
    }
   
    public Coordinate getCoord() {
    	return coord;
    }
    @Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Square)) {
            return false;
        }

        Square sq = (Square) obj;
        return sq.getCoord().equals(this.getCoord());
    }

    @Override
    public String toString () {
        return getEntity() + " at (" + getCoord() +")";
    }
}