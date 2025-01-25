import java.util.Random;

public class Employee {
    String name;
    int energe;
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

    public Employee(String name) {
        this.name = name;
        energe = 0;
    }

    public void eatFood(Food food) {
        energe += food.getIfEatCanGetEnerge();
    }

    public String work(Vagetables vagetables) {
        if (energe > 0) {
            if (vagetables.getTop() != 0 && vagetables.getTop() == vagetables.getGrow()) {
                return "Harvest";
            } else if (vagetables.getTop() == 0) {
                return "NONE";
            } else {
                vagetables.update(new Random().nextInt(1, 3));
                energe -= new Random().nextInt(3, 7);
                return "";
            }
        } else {
            energe = 0;
            return "Food";
        }
    }

    public String work(Animal animal){
        if (energe > 0) {
            if (animal.getTop() != 0 && animal.getTop() == animal.getGrow()) {
                return "Harvest";
            } else if (animal.getTop() == 0) {
                return "NONE";
            } else {
                animal.update(new Random().nextInt(1, 3));
                energe -= new Random().nextInt(3, 7);
                return "";
            }
        } else {
            energe = 0;
            return "Food";
        }
    }

    public void setEnerge(int energe) {
        this.energe = energe;
    }

    public String getName() {
        return name;
    }

    public int getEnerge() {
        return energe;
    }
}
