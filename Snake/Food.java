import java.util.Random;

public class Food {
   
   private Snake snake = Snake.get_snake();
   
   private Square food;
   private int x;
   private int y;

   Food(){
      this.food = set_food();
        this.x = food.getX();
        this.y = food.getY();
   }
   
   private Square set_food() {
      Random rX = new Random();
        Random rY = new Random();
        Square square = new Square(Square.Entity.Food, rX.nextInt(Properties.BOARD_COLUMNS), rY.nextInt(Properties.BOARD_ROWS));
        
        if (food != null && snake.contains(food)) {
            set_food();
        }
        
        return square;
   }
   
   public Square get_food() {
      return food;
   }
   
   public int get_X() {
      return x;
   }
   
   public int get_Y() {
      return y;
   }
   
   
}