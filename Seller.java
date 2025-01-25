import java.util.List;

public class Seller {
    String name;
    int energe;
    String type;
    String willSell;
    int last;
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

    public Seller(String name, int energe, String type, String willSell, int last) {
        this.name = name;
        this.energe = energe;
        this.type = type;
        this.willSell = willSell;
        this.last = last;
    }

    public void eatFood(Food food) {
        energe += food.getIfEatCanGetEnerge();
    }

    public String work(List<Warehouse<? extends Object>> warehouseList, List<Integer> warehouseListNumber) {
        if (energe > 0) {
            if (getWarehouseNumber(warehouseList, warehouseListNumber, type, willSell) > last) {
                return "Sell";
            } else {
                return "CanNot";
            }
        } else {
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

    public String getName() {
        return name;
    }

    public int getEnerge() {
        return energe;
    }

    public int getLast() {
        return last;
    }

    public String getType() {
        return type;
    }

    public String getWillSell() {
        return willSell;
    }
}
