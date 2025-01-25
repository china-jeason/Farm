import java.util.Random;

public class Buyer {
    String name;
    int energe;
    String type;
    String willBuy;
    int top;
    Random random = new Random();
    Vagetables testVagetables = new Vagetables("NONE");
    Food testFood = new Food("None");
    Animal testAnimal = new Animal("None", 2, 0);
    int x;
    int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Buyer(String name, int energe, String type, String willBuy, int top) {
        this.name = name;
        this.energe = energe;
        this.type = type;
        this.willBuy = willBuy;
        this.top = top;
    }

    public void eatFood(Food food) {
        energe += food.getIfEatCanGetEnerge();
    }

    public String work(long money, Double upPrice, int have) {
        if (energe > 0) {
            if (have < top) {
                if (type.equals("SEEDS")) {
                    if (money >= (int) testVagetables.getCost(willBuy) * upPrice) {
                        energe -= random.nextInt(3, 7);
                        return "Can";
                    }
                } else if (type.equals("Food")) {
                    if (money >= (int) testFood.getPrice(willBuy) * upPrice) {
                        energe -= random.nextInt(3, 7);
                        return "Can";
                    }
                } else if (type.equals("Animal")) {
                    if (money >= (int) testAnimal.getCost(willBuy) * upPrice) {
                        energe -= random.nextInt(3, 7);
                        return "Can";
                    }
                }
                return "CanNot";
            } else {
                return "TooMany";
            }
        } else {
            energe = 0;
            return "Food";
        }
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getTop() {
        return top;
    }

    public void setEnerge(int energe) {
        this.energe = energe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWillBuy(String willBuy) {
        this.willBuy = willBuy;
    }

    public int getEnerge() {
        return energe;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getWillBuy() {
        return willBuy;
    }
}
