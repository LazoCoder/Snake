package snake;
import java.util.Random;

public class Food {
   
   private final Snake snake = Snake.get_snake();
   private final Properties properties = Properties.Instance();
   private final Square food;
   private final int x;
   private final int y;

   Food() {
      this.food = set_food();
      this.x = food.getX();
      this.y = food.getY();
   }

   private Square set_food() {
      final Random rX = new Random();
      final Random rY = new Random();
      final Square square = new Square(Entity.Food, rX.nextInt(properties.getBoardColumns()),
            rY.nextInt(properties.getBoardRows()));
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