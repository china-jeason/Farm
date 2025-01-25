import java.util.List;
import java.util.Random;

public class Cooker {
    String name;
    int energe;
    String food;
    Food test = new Food("NONE");
    Random random = new Random();
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

    public Cooker(String name, int energe, String food) {
        this.name = name;
        this.energe = energe;
        this.food = food;
        this.test.id = food;
    }

    public void eatFood(Food food) {
        energe += food.getIfEatCanGetEnerge();
    }

    public String work(List<Warehouse<? extends Object>> warehouseList, List<Integer> warehouseListNumber) {
        if (energe > 0) {
            List<String> formulation = test.getFormulation(food);
            long min = -1;
            for (String string : formulation.stream().distinct().toList()) {
                String raw[] = string.split(",");
                List<Warehouse<? extends Object>> l = warehouseList.stream()
                        .filter(warehouse -> warehouse.getType().equals(raw[0]))
                        .filter(warehouse -> warehouse.getName().equals(raw[1]))
                        .toList();
                int haveR = (int) l.size();
                int have = 0;
                if (haveR != 0) {
                    have = getWarehouseNumber(warehouseList, warehouseListNumber, l.get(0));
                }
                long need = formulation.stream().filter(f -> f.equals(string)).count();
                long willMin = (long) (have / need);
                if (min == -1) {
                    min = willMin;
                } else {
                    if (min > willMin) {
                        min = willMin;
                    }
                }
            }
            if (min != 0) {
                energe -= random.nextInt(5, 10);
                return "Can";
            } else {
                return "CanNot";
            }
        } else {
            energe = 0;
            return "Food";
        }
    }

    static int getWarehouseNumber(List<Warehouse<? extends Object>> warehouseList, List<Integer> warehouseListNumber,
            Warehouse<? extends Object> warehouse) {
        for (int i = 0; i < warehouseList.size(); i++) {
            if (warehouseList.get(i).equals(warehouse)) {
                return warehouseListNumber.get(i);
            }
        }
        return 0;
    }

    static int getWarehouseNumber(List<Warehouse<? extends Object>> warehouseList, List<Integer> warehouseListNumber,
            String type, String name) {
        for (int i = 0; i < warehouseList.size(); i++) {
            if (warehouseList.get(i).getType().equals(type) && warehouseList.get(i).getName().equals(name)) {
                return warehouseListNumber.get(i);
            }
        }
        return 0;
    }

    public void setEnerge(int energe) {
        this.energe = energe;
    }

    public void setFood(String food) {
        this.food = food;
        this.test.id = food;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEnerge() {
        return energe;
    }

    public String getFood() {
        return food;
    }
}
