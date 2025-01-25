import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FarmData {
    @JsonProperty("farm")
    private List<Farm> farm;
    @JsonProperty("setting")
    private Setting setting;
    @JsonProperty("Animal")
    private List<Animal> Animal;
    @JsonProperty("Warehouse")
    private List<Warehouse> Warehouse;
    @JsonProperty("Employee")
    private List<Employee> Employee;
    @JsonProperty("Achievement")
    private List<Achievement> Achievement;
    @JsonProperty("world")
    private World world;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public static class World {
        @JsonProperty("x")
        private int x;
        @JsonProperty("y")
        private int y;
        @JsonProperty("seed")
        private int seed;

        public void setSeed(int seed) {
            this.seed = seed;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSeed() {
            return seed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public Setting getSetting() {
        return setting;
    }

    public List<Farm> getVagetableList() {
        return farm;
    }

    public List<Animal> getAnimalList() {
        return Animal;
    }

    public List<Warehouse> getWarehouseList() {
        return Warehouse;
    }

    public List<Employee> getEmployeeList() {
        return Employee;
    }

    public List<Achievement> getAchievementList() {
        return Achievement;
    }

    public void setAchievementList(List<Achievement> achievementList) {
        this.Achievement = achievementList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.Animal = animalList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.Employee = employeeList;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public void setVagetableList(List<Farm> vagetableList) {
        this.farm = vagetableList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.Warehouse = warehouseList;
    }

    public static class Setting {
        @JsonProperty("money")
        private long money;
        @JsonProperty("LastUpdateTime")
        private long lastUpdateTime;
        @JsonProperty("level")
        private int level;
        @JsonProperty("version")
        private String version;
        @JsonProperty("time")
        private long time;
        @JsonProperty("step")
        private int step;

        public int getStep() {
            return step;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public long getMoney() {
            return money;
        }

        public void setMoney(long money) {
            this.money = money;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class Farm {
        @JsonProperty("Vagetable")
        private String vegetable;
        @JsonProperty("Soil")
        private String soil;
        @JsonProperty("Grow")
        private int grow;
        @JsonProperty("x")
        private int x;
        @JsonProperty("y")
        private int y;

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

        public int getGrow() {
            return grow;
        }

        public String getSoil() {
            return soil;
        }

        public String getVegetable() {
            return vegetable;
        }

        public void setGrow(int grow) {
            this.grow = grow;
        }

        public void setSoil(String soil) {
            this.soil = soil;
        }

        public void setVegetable(String vegetable) {
            this.vegetable = vegetable;
        }
    }

    public static class Animal {
        @JsonProperty("Animal")
        private String animal;
        @JsonProperty("mode")
        private int mode;
        @JsonProperty("Grow")
        private int grow;
        @JsonProperty("sex")
        private int sex;
        @JsonProperty("x")
        private int x;
        @JsonProperty("y")
        private int y;

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

        public String getAnimal() {
            return animal;
        }

        public int getGrow() {
            return grow;
        }

        public int getMode() {
            return mode;
        }

        public int getSex() {
            return sex;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public void setGrow(int grow) {
            this.grow = grow;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }

    public static class Warehouse {
        @JsonProperty("type")
        private String type;
        @JsonProperty("name")
        private String name;
        @JsonProperty("num")
        private int num;

        public String getName() {
            return name;
        }

        public int getNum() {
            return num;
        }

        public String getType() {
            return type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Employee {
        @JsonProperty("name")
        private String name;
        @JsonProperty("energe")
        private int energe;
        @JsonProperty("type")
        private String type;
        @JsonProperty("food")
        private String food;
        @JsonProperty("buyType")
        private String buyType;
        @JsonProperty("will")
        private String will;
        @JsonProperty("top")
        private int top;
        @JsonProperty("x")
        private int x;
        @JsonProperty("y")
        private int y;

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

        public String getBuyType() {
            return buyType;
        }

        public int getTop() {
            return top;
        }

        public String getWill() {
            return will;
        }

        public void setBuyType(String buyType) {
            this.buyType = buyType;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public void setWill(String will) {
            this.will = will;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
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

        public void setEnerge(int energe) {
            this.energe = energe;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Achievement {
        @JsonProperty("name")
        private String name;
        @JsonProperty("time")
        private long time;

        public String getName() {
            return name;
        }

        public long getTime() {
            return time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}