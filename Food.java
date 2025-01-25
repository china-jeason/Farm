import java.util.ArrayList;
import java.util.List;

public class Food {
    String id;
    int ifEatCanGetEnerge;
    List<String> formulation = new ArrayList<>();

    public Food(String id) {
        this.id = id;
        ifEatCanGetEnerge = getInformation().get(1);
    }

    public ArrayList<Integer> getInformation() {
        formulation.clear();
        int price = 0;
        int ifEatCanGetEnerge = 0;
        int unitName = 0;
        if (id.equals("Salt")) {
            price = 20;
            ifEatCanGetEnerge = 1;
            unitName = 0;
        } else if (id.equals("Suger")) {
            price = 50;
            ifEatCanGetEnerge = 5;
            unitName = 0;
        } else if (id.equals("Oil")) {
            price = 70;
            ifEatCanGetEnerge = 3;
            unitName = 1;
        } else if (id.equals("Milk")) {
            price = 400;
            ifEatCanGetEnerge = 50;
            unitName = 1;

            addFormulation("Animal", "Cow", 1);
        } else if (id.equals("Flour")) {
            price = 350;
            ifEatCanGetEnerge = 30;
            unitName = 0;

            addFormulation("VAGETABLES", "WHEAT", 3);
        } else if (id.equals("Bread")) {
            price = 3000;
            ifEatCanGetEnerge = 250;
            unitName = 2;

            addFormulation("Food", "Flour", 5);
            addFormulation("Food", "Oil", 3);
            addFormulation("Food", "Suger", 1);
            addFormulation("Food", "Milk", 5);
        } else if (id.equals("Steak")) {
            price = 8000;
            ifEatCanGetEnerge = 1000;
            unitName = 2;

            addFormulation("Animal", "Ox", 1);
        } else if (id.equals("ChichenSteak")) {
            price = 6000;
            ifEatCanGetEnerge = 700;
            unitName = 2;

            addFormulation("Animal", "Chicken", 1);
        } else if (id.equals("Honey")) {
            price = 3000;
            ifEatCanGetEnerge = 250;
            unitName = 1;

            addFormulation("Animal", "Bee", 10);
        } else if (id.equals("Egg")) {
            price = 800;
            ifEatCanGetEnerge = 70;
            unitName = 2;

            addFormulation("Animal", "Chicken", 1);
        } else if (id.equals("Grill")) {
            price = 8000;
            ifEatCanGetEnerge = 700;
            unitName = 2;

            addFormulation("Animal", "Fish", 1);
            addFormulation("Food", "Honey", 3);
            addFormulation("Food", "Salt", 3);
            addFormulation("Food", "Oil", 3);
        } else if (id.equals("RoastTurkey")) {
            price = 8000;
            ifEatCanGetEnerge = 900;
            unitName = 2;

            addFormulation("Animal", "Turkey", 1);
            addFormulation("Food", "Honey", 3);
            addFormulation("Food", "Salt", 3);
            addFormulation("Food", "Oil", 3);
        } else if (id.equals("Biscuit")) {
            price = 4500;
            ifEatCanGetEnerge = 300;
            unitName = 2;

            addFormulation("Food", "Flour", 5);
            addFormulation("Food", "Honey", 3);
            addFormulation("Food", "Salt", 3);
            addFormulation("Food", "Oil", 3);
            addFormulation("Food", "Milk", 2);
        } else if (id.equals("Cheese")) {
            price = 9500;
            ifEatCanGetEnerge = 600;
            unitName = 0;

            addFormulation("Food", "Suger", 2);
            addFormulation("Food", "Milk", 3);
        } else if (id.equals("Toast")) {
            price = 6000;
            ifEatCanGetEnerge = 1000;
            unitName = 2;

            addFormulation("Food", "Flour", 5);
            addFormulation("Food", "Honey", 3);
            addFormulation("Food", "Salt", 1);
            addFormulation("Food", "Oil", 1);
            addFormulation("Food", "Milk", 2);
        } else if (id.equals("Hamburger")) {
            price = 13000;
            ifEatCanGetEnerge = 2500;
            unitName = 2;

            addFormulation("Food", "Bread", 2);
            addFormulation("Food", "Steak", 1);
            addFormulation("Food", "ChichenSteak", 1);
            addFormulation("VAGETABLES", "CABBAGEB", 1);
        } else if (id.equals("Sandwich")) {
            price = 12000;
            ifEatCanGetEnerge = 2000;
            unitName = 2;

            addFormulation("Food", "Bread", 2);
            addFormulation("Food", "Egg", 1);
            addFormulation("Food", "Steak", 1);
            addFormulation("Food", "RoastTurkey", 1);
            addFormulation("Food", "ChichenSteak", 1);
            addFormulation("VAGETABLES", "CABBAGEB", 1);
        } else if (id.equals("TomatoAndEggs")) {
            price = 7000;
            ifEatCanGetEnerge = 1000;
            unitName = 4;

            addFormulation("Food", "Egg", 3);
            addFormulation("VAGETABLES", "TOMATO", 5);
        } else if (id.equals("PearlJadeiteWhiteJadeSoup")) {
            price = 100000;
            ifEatCanGetEnerge = 8000;
            unitName = 4;

            addFormulation("Food", "Salt", 20);
            addFormulation("Food", "Suger", 5);
            addFormulation("Food", "Oil", 20);
            addFormulation("Food", "Egg", 10);
            addFormulation("Food", "Honey", 10);
            addFormulation("VAGETABLES", "TOMATO", 10);
            addFormulation("VAGETABLES", "CABBAGEB", 15);
            addFormulation("VAGETABLES", "TURNIP", 10);
            addFormulation("VAGETABLES", "POTATO", 10);
            addFormulation("VAGETABLES", "ONION", 5);
            addFormulation("VAGETABLES", "SOYBEAN", 10);
            addFormulation("VAGETABLES", "SCALLION", 20);
            addFormulation("VAGETABLES", "CORN", 10);
            addFormulation("VAGETABLES", "JUJUBE", 10);
            addFormulation("Animal", "Ox", 2);
            addFormulation("Animal", "Turkey", 5);
            addFormulation("Animal", "Sheep", 2);
            addFormulation("Animal", "Pig", 2);
            addFormulation("Animal", "Duck", 5);
            addFormulation("VAGETABLES", "PEPPER", 3);
        } else if (id.equals("Coffee")) {
            price = 9000;
            ifEatCanGetEnerge = 1000;
            unitName = 1;

            addFormulation("Food", "Milk", 3);
            addFormulation("VAGETABLES", "COFFEE", 5);
        }else if (id.equals("Pizza")) {
            price = 70000;
            ifEatCanGetEnerge = 6000;
            unitName = 2;

            addFormulation("Food", "Salt", 5);
            addFormulation("Food", "Suger", 2);
            addFormulation("Food", "Oil", 3);
            addFormulation("VAGETABLES", "TOMATO", 4);
            addFormulation("VAGETABLES", "POTATO", 4);
            addFormulation("VAGETABLES", "ONION", 5);
            addFormulation("VAGETABLES", "SCALLION", 10);
            addFormulation("VAGETABLES", "CORN", 4);
            addFormulation("Animal", "Ox", 1);
            addFormulation("Animal", "Turkey", 3);
            addFormulation("Animal", "Sheep", 1);
            addFormulation("Animal", "Pig", 1);
            addFormulation("Animal", "Duck", 3);
            addFormulation("VAGETABLES", "PEPPER", 3);
        }else if (id.equals("Salad")) {
            price = 40000;
            ifEatCanGetEnerge = 4000;
            unitName = 4;

            addFormulation("Food", "Salt", 5);
            addFormulation("Food", "Suger", 2);
            addFormulation("Food", "Oil", 1);
            addFormulation("Food", "Milk", 3);
            addFormulation("VAGETABLES", "TOMATO", 4);
            addFormulation("VAGETABLES", "POTATO", 4);
            addFormulation("VAGETABLES", "ONION", 5);
            addFormulation("VAGETABLES", "SCALLION", 10);
            addFormulation("VAGETABLES", "CORN", 4);
            addFormulation("VAGETABLES", "CABBAGE", 4);
            addFormulation("VAGETABLES", "PEPPER", 3);
            addFormulation("VAGETABLES", "APPLE", 4);
            addFormulation("VAGETABLES", "WATTERMALLON", 2);
            addFormulation("VAGETABLES", "CHERRY", 6);
            addFormulation("VAGETABLES", "PEACH", 4);
        }else{
            price = 0;
            ifEatCanGetEnerge = 0;
            unitName = 0;
        }
        ArrayList<Integer> Information = new ArrayList<>();
        Information.add(price);
        Information.add(ifEatCanGetEnerge);
        Information.add(unitName);
        return Information;
    }

    public String getUnitName(String type) {
        id = type;
        getInformation();
        int unitName = getInformation().get(2);
        String will = "";
        if (unitName == 0) {
            will = "克";
        } else if (unitName == 1) {
            will = "升";
        } else if (unitName == 2) {
            will = "个";
        } else if (unitName == 3) {
            will = "只";
        } else if (unitName == 4) {
            will = "碗";
        }
        return will;
    }

    public void addFormulation(String type, String formulation, int number) {
        for (int i = 0; i < number; i++) {
            this.formulation.add(type + "," + formulation);
        }
    }

    public List<String> getFormulation(String type) {
        id = type;
        getInformation();
        return formulation;
    }

    public int getIfEatCanGetEnerge() {
        return getInformation().get(1);
    }

    public int getIfEatCanGetEnerge(String type) {
        id = type;
        getInformation();
        return getInformation().get(1);
    }

    public String getId() {
        return id;
    }

    public int getPrice(String type) {
        id = type;
        getInformation();
        return getInformation().get(0);
    }

}
