import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal {
    String id;
    int grow = 0;
    int top;
    int sex;
    String mode[] = { "生长期", "繁殖期", "成熟期" };
    int modeNum = 0;
    int modeTop[] = { 0, 0 };
    int x;
    int y;
    double ang = 0;
    Random r = new Random();
    int move = 0;

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

    public String getSex() {
        if (sex == 0) {
            return "♀";
        } else {
            return "♂";
        }
    }

    public String getModeNum() {
        return mode[modeNum];
    }

    public Animal(String id, int modeNum, int sex) {
        this.id = id;
        this.modeNum = modeNum;
        this.sex = sex;
        ang = r.nextDouble(0, 2 * 3.14);
        move = r.nextInt(-3, 3);
        setTop();
    }

    public void setGrowTop(int grow, int f) {
        modeTop[0] = grow;
        modeTop[1] = f;
    }

    protected List<Integer> getInformations(String vid) {
        int needMoney = 0;
        int getMoney = 0;
        if (vid.equals("Ox")) {
            needMoney = 70000;
            getMoney = 100000;
            setGrowTop(15000, 50000);
        } else if (vid.equals("Chicken")) {
            needMoney = 10000;
            getMoney = 15000;
            setGrowTop(3000, 6000);
        } else if (vid.equals("Pig")) {
            needMoney = 60000;
            getMoney = 90000;
            setGrowTop(10000, 30000);
        } else if (vid.equals("Fish")) {
            needMoney = 30000;
            getMoney = 60000;
            setGrowTop(2000, 5000);
        } else if (vid.equals("Rabbit")) {
            needMoney = 50000;
            getMoney = 80000;
            setGrowTop(8000, 11000);
        } else if (vid.equals("Sheep")) {
            needMoney = 90000;
            getMoney = 120000;
            setGrowTop(15000, 30000);
        } else if (vid.equals("Duck")) {
            needMoney = 14000;
            getMoney = 25000;
            setGrowTop(3500, 7000);
        } else if (vid.equals("Cow")) {
            needMoney = 80000;
            getMoney = 120000;
            setGrowTop(15000, 35000);
        } else if (vid.equals("Horse")) {
            needMoney = 100000;
            getMoney = 150000;
            setGrowTop(20000, 45000);
        } else if (vid.equals("Bee")) {
            needMoney = 10000;
            getMoney = 25000;
            setGrowTop(3000, 5000);
        } else if (vid.equals("Turkey")) {
            needMoney = 30000;
            getMoney = 55000;
            setGrowTop(3000, 6000);
        }
        List<Integer> needReturn = new ArrayList<>();
        needReturn.add(needMoney);
        needReturn.add(getMoney);
        return needReturn;
    }

    void setTop() {
        getInformations(id);
        if (modeNum == 0) {
            top = modeTop[0];
        } else {
            top = modeTop[1];
        }
    }

    int getCost(String v) {
        return getInformations(v).get(0);
    }

    int getMoney(String v) {
        return (int) getInformations(v).get(1);
    }

    void change(String v) {
        id = v;
        setTop();
        grow = 0;
    }

    void move(){
        ang += r.nextDouble(0, 0.1 * 3.14);
        move += r.nextInt(-2, 2);
        if (move > 3) {
            move = 3;
        }else if (move < -3) {
            move = -3;
        }
        x += Math.cos(ang) * move;
        y += Math.sin(ang) * move;
    }

    void update(int i, int j) {
        if (grow < top) {
            grow += i + j;
        }
        if (grow >= top) {
            grow = top;
            if (r.nextInt(4) == 1 && modeNum == 0) {
                modeNum = 1;
            } else if (modeNum == 1) {
                modeNum = 2;
            } else {
                modeNum = 2;
            }
            setTop();
        }
    }

    void update(int i) {
        if (grow < top) {
            grow += i;
        }
        if (grow >= top) {
            grow = top;
            if (r.nextInt(4) == 1 && modeNum == 0) {
                modeNum = 1;
            } else if (modeNum == 1) {
                modeNum = 2;
            } else {
                modeNum = 2;
            }
            setTop();
        }
    }

    public void setGrow(int grow) {
        this.grow = grow;
    }

    public String getId() {
        return id;
    }

    public int getGrow() {
        return grow;
    }

    public int getTop() {
        return top;
    }
}
