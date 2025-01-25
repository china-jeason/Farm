import java.util.ArrayList;
import java.util.List;

class Soil {
    String id = "";
    int x;
    int y;

    public Soil(String id,World.Posision p) {
        this.id = id;
        this.x = p.x;
        this.y = p.y;
    }

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

    protected List<Object> getInformations() {
        int grow;
        int luck;
        String name;
        if (id.equals("WHITE")) {
            grow = 0;
            luck = 10;
            name = "白壤";
        } else if (id.equals("YELLOW")) {
            grow = 1;
            luck = 10;
            name = "黄壤";
        } else if (id.equals("REDC")) {
            grow = 1;
            luck = 13;
            name = "赤红壤";
        } else if (id.equals("REDZ")) {
            grow = 2;
            luck = 13;
            name = "砖红壤";
        } else if (id.equals("BROWN")) {
            grow = 3;
            luck = 15;
            name = "棕壤";
        } else if (id.equals("BROWNG")) {
            grow = 4;
            luck = 15;
            name = "棕钙土";
        } else if (id.equals("BROWNB")) {
            grow = 5;
            luck = 20;
            name = "褐土";
        } else if (id.equals("BLACKH")) {
            grow = 6;
            luck = 20;
            name = "灰黑土";
        } else if (id.equals("BLACKG")) {
            grow = 7;
            luck = 25;
            name = "黑钙土";
        } else if (id.equals("BLACK")) {
            grow = 8;
            luck = 30;
            name = "黑土";
        } else {
            grow = 0;
            luck = 0;
            name = "";
        }
        List<Object> needReturn = new ArrayList<>();
        needReturn.add(grow);
        needReturn.add(luck);
        needReturn.add(name);
        return needReturn;

    }

    public String getId() {
        return id;
    }

    public void change(String id) {
        this.id = id;
    }

    public int getGrow() {
        return (int) getInformations().get(0);
    }

    public int getLuck() {
        return (int) getInformations().get(1);
    }

    public String getName() {
        return (String) getInformations().get(2);
    }
}