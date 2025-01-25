import java.util.ArrayList;
import java.util.List;

public class Vagetables {
    String id;
    int grow = 0;
    int top;

    public Vagetables(String id) {
        this.id = id;
        setTop();
    }

    protected List<Integer> getInformations(String vid) {
        int topGrow = 0;
        int needMoney = 0;
        int getMoney = 0;
        // 根据vid的值，设置getMoney、needMoney和topGrow的值
        if (vid.equals("CABBAGE")) {
            // 白菜
            getMoney = 40;
            needMoney = 30;
            topGrow = 60;
        } else if (vid.equals("NONE")) {
            // 无
            getMoney = 0;
            needMoney = 0;
            topGrow = 0;
        } else if (vid.equals("ONION")) {
            // 洋葱
            getMoney = 22;
            needMoney = 17;
            topGrow = 16;
        } else if (vid.equals("PEPPER")) {
            // 辣椒
            getMoney = 60;
            needMoney = 45;
            topGrow = 50;
        } else if (vid.equals("TOMATO")) {
            // 西红柿
            getMoney = 70;
            needMoney = 50;
            topGrow = 150;
        } else if (vid.equals("POTATO")) {
            // 土豆
            getMoney = 60;
            needMoney = 45;
            topGrow = 100;
        } else if (vid.equals("PUMPKIN")) {
            // 南瓜
            getMoney = 125;
            needMoney = 100;
            topGrow = 500;
        } else if (vid.equals("SPINACH")) {
            // 菠菜
            getMoney = 100;
            needMoney = 80;
            topGrow = 250;
        } else if (vid.equals("WATERMALLON")) {
            // 西瓜
            getMoney = 100;
            needMoney = 70;
            topGrow = 500;
        } else if (vid.equals("RICE")) {
            // 水稻
            getMoney = 200;
            needMoney = 150;
            topGrow = 800;
        } else if (vid.equals("CORN")) {
            // 玉米
            getMoney = 380;
            needMoney = 260;
            topGrow = 900;
        } else if (vid.equals("WHEAT")) {
            // 小麦
            getMoney = 300;
            needMoney = 200;
            topGrow = 800;
        } else if (vid.equals("SOYBEAN")) {
            // 大豆
            getMoney = 450;
            needMoney = 310;
            topGrow = 600;
        } else if (vid.equals("RAPE")) {
            // 芥菜
            getMoney = 330;
            needMoney = 200;
            topGrow = 500;
        } else if (vid.equals("TURNIP")) {
            // 萝卜
            getMoney = 300;
            needMoney = 230;
            topGrow = 500;
        } else if (vid.equals("GINSENG")) {
            // 人参
            getMoney = 1150;
            needMoney = 850;
            topGrow = 1200;
        } else if (vid.equals("PEANUT")) {
            // 花生
            getMoney = 300;
            needMoney = 210;
            topGrow = 500;
        } else if (vid.equals("APPLE")) {
            // 苹果
            getMoney = 700;
            needMoney = 530;
            topGrow = 3000;
        } else if (vid.equals("PEAR")) {
            // 梨
            getMoney = 700;
            needMoney = 530;
            topGrow = 3000;
        } else if (vid.equals("MINT")) {
            // 薄荷
            getMoney = 850;
            needMoney = 700;
            topGrow = 2000;
        } else if (vid.equals("CHERRY")) {
            // 樱桃
            getMoney = 2700;
            needMoney = 1800;
            topGrow = 3500;
        } else if (vid.equals("JUJUBE")) {
            // 柚子
            getMoney = 1700;
            needMoney = 1300;
            topGrow = 4500;
        } else if (vid.equals("COTTON")) {
            // 棉花
            getMoney = 1000;
            needMoney = 820;
            topGrow = 6000;
        } else if (vid.equals("STRAWBERRY")) {
            // 草莓
            getMoney = 1250;
            needMoney = 900;
            topGrow = 1000;
        } else if (vid.equals("PEACH")) {
            // 桃子
            getMoney = 850;
            needMoney = 600;
            topGrow = 3000;
        } else if (vid.equals("SCALLION")) {
            // 葱
            getMoney = 18;
            needMoney = 15;
            topGrow = 20;
        } else if (vid.equals("PERSIMMON")) {
            // 柿子
            getMoney = 850;
            needMoney = 600;
            topGrow = 3000;
        } else if (vid.equals("CRANBERRY")) {
            // 猕猴桃
            getMoney = 2500;
            needMoney = 1900;
            topGrow = 5000;
        } else if (vid.equals("GRAPEFRUIT")) {
            // 柑橘
            getMoney = 870;
            needMoney = 650;
            topGrow = 5000;
        } else if (vid.equals("PINEAPPLE")) {
            // 菠萝
            getMoney = 850;
            needMoney = 600;
            topGrow = 5000;
        } else if (vid.equals("COFFEE")) {
            // 咖啡
            getMoney = 2200;
            needMoney = 1500;
            topGrow = 3000;
        } else if (vid.equals("DURIAN")) {
            // 榔椰
            getMoney = 23000;
            needMoney = 15000;
            topGrow = 30000;
        } else if (vid.equals("LICCEE")) {
            // 蜜荔枝
            getMoney = 12000;
            needMoney = 8000;
            topGrow = 20000;
        } else if (vid.equals("RAMBUTAN")) {
            // 椰子
            getMoney = 110000;
            needMoney = 80000;
            topGrow = 10000;
        } else if (vid.equals("PEACHB")) {
            // 桃子
            getMoney = 1600;
            needMoney = 1200;
            topGrow = 5000;
        } else if (vid.equals("PEACHC")) {
            // 桃子
            getMoney = 1700;
            needMoney = 1200;
            topGrow = 6000;
        } else if (vid.equals("CABBAGEB")) {
            // 菠菜
            getMoney = 70;
            needMoney = 50;
            topGrow = 500;
        } else if (vid.equals("LYCHEE")) {
            // 蜜荔枝
            getMoney = 155000;
            needMoney = 100000;
            topGrow = 30000;
        } else if (vid.equals("KINGMELON")) {
            // 玉皇梨
            getMoney = 635000;
            needMoney = 450000;
            topGrow = 15000;
        } else if (vid.equals("ROMANGRAPES")) {
            // 玫瑰红葡萄
            getMoney = 423000;
            needMoney = 350000;
            topGrow = 15000;
        } else if (vid.equals("YELLOWGRAGONFRUIT")) {
            // 黄龙果
            getMoney = 36000;
            needMoney = 25000;
            topGrow = 25000;
        } else if (vid.equals("DENSUKEWETERMELON")) {
            // 寅时西瓜
            getMoney = 32800;
            needMoney = 22000;
            topGrow = 40000;
        } else if (vid.equals("DEVONSHIREPINEAPPLE")) {
            // 德文郡菠萝
            getMoney = 105000;
            needMoney = 85000;
            topGrow = 60000;
        } else if (vid.equals("FINGERLIME")) {
            // 柠檬
            getMoney = 370000;
            needMoney = 300000;
            topGrow = 30000;
        } else if (vid.equals("NONFRUIT")) {
            // 非水果
            getMoney = 53000;
            needMoney = 36000;
            topGrow = 30000;
        } else if (vid.equals("ONEAPPLE")) {
            // 苹果
            getMoney = 65000;
            needMoney = 50000;
            topGrow = 50000;
        } else if (vid.equals("KIWANO")) {
            // 瓜
            getMoney = 71000;
            needMoney = 63000;
            topGrow = 45000;
        } else if (vid.equals("KOBICOFFEE")) {
            // 美洲咖啡
            getMoney = 7900;
            needMoney = 6000;
            topGrow = 3000;
        } else if (vid.equals("TRUFFLE")) {
            // 松露
            getMoney = 8500;
            needMoney = 6800;
            topGrow = 7000;
        } else if (vid.equals("TORREYANUTS")) {
            // 桦果
            getMoney = 7300;
            needMoney = 5400;
            topGrow = 3500;
        }
        List<Integer> needReturn = new ArrayList<>();
        needReturn.add(topGrow);
        needReturn.add(needMoney);
        needReturn.add(getMoney);
        return needReturn;
    }

    void setTop() {
        top = getInformations(id).get(0);
    }

    int getCost(String v) {
        return getInformations(v).get(1);
    }

    int getMoney(String v) {
        return (int) getInformations(v).get(2) / 2;
    }

    void change(String v) {
        id = v;
        setTop();
        grow = 0;
    }

    void update(int i, int j) {
        if (grow < top) {
            grow += i + j;
        }
        if (grow > top) {
            grow = top;
        }
    }

    void update(int i) {
        if (grow < top) {
            grow += i;
        }
        if (grow > top) {
            grow = top;
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