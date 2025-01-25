import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;

class Farm {
    // 版本号
    static String version = "11.0";
    static String getVersion = "";
    static boolean versionIsRight = true;
    // 蔬菜列表
    static List<Vagetables> vagetablesList = new ArrayList<>();
    // 土壤列表
    static List<Soil> soilList = new ArrayList<>();
    // 仓库列表
    static List<Warehouse<? extends Object>> warehouseList = new ArrayList<>();
    static List<Integer> warehouseListNumber = new ArrayList<>();
    // 农场照管员列表
    static List<Employee> farmerList = new ArrayList<>();
    // 厨师列表
    static List<Cooker> cookerList = new ArrayList<>();
    // 采购员列表
    static List<Buyer> buyerList = new ArrayList<>();
    // 销售员列表
    static List<Seller> sellerList = new ArrayList<>();
    // 声明一个Animal类型的列表
    static List<Animal> animalList = new ArrayList<>();
    // 声明一个Achiievement类型的列表
    static List<Achievement> achievementList = new ArrayList<>();
    // 金钱
    static long money = 0;
    // 等级
    static long level = 0;
    // 等级显示
    static double levelShow = 0;
    // 最后更新时间
    static long LastUpdateTime = System.currentTimeMillis();
    // 随机数
    static Random random = new Random();
    // 测试蔬菜
    static Vagetables testVagetable = new Vagetables("NONE");
    // 测试动物
    static Animal testAnimal = new Animal("NONE", 2, 0);
    // 测试食物
    static Food testFood = new Food("NONE");
    // 路径
    static String currentDirectory = System.getProperty("user.dir") + "\\save";
    // 输入
    static List<String> input = new ArrayList<>();
    // 蔬菜数组
    static String[] vagetables = { "CABBAGE", "ONION", "PEPPER", "TOMATO", "POTATO", "PUMPKIN", "SPINACH",
            "WATERMALLON", "RICE", "CORN", "WHEAT", "SOYBEAN", "RAPE", "TURNIP", "GINSENG", "PEANUT", "APPLE", "PEAR",
            "MINT", "CHERRY", "JUJUBE", "COTTON", "STRAWBERRY", "PEACH", "SCALLION", "PERSIMMON", "CRANBERRY",
            "GRAPEFRUIT", "PINEAPPLE", "COFFEE", "DURIAN", "LICCEE", "RAMBUTAN", "CABBAGEB", "PEACHB", "PEACHC",
            "LYCHEE", "KINGMELON", "ROMANGRAPES", "YELLOWGRAGONFRUIT", "DENSUKEWETERMELON", "DEVONSHIREPINEAPPLE",
            "FINGERLIME", "NONFRUIT", "ONEAPPLE", "KIWANO", "KOBICOFFEE", "TRUFFLE", "TORREYANUTS", "NONE" };
    // 蔬菜中文数组
    static String[] vagetablesC = { "白菜", "洋葱", "辣椒", "西红柿", "土豆", "南瓜", "菠菜", "西瓜", "水稻", "玉米", "小麦", "大豆",
            "芥菜", "萝卜", "人参", "花生", "红苹果", "梨", "薄荷", "樱桃", "枣", "棉花", "草莓", "桃子", "葱", "柿子", "蔓越莓", "柚子", "菠萝", "黑咖啡",
            "榴莲", "荔枝", "红毛丹", "娃娃菜", "水密桃", "油桃", "增城挂绿荔枝", "夕张王甜瓜", "红葡萄", "火龙果", "黑皮西瓜", "进口菠萝", "指橙", "海巴戟",
            "青苹果", "角瓜", "小粒咖啡", "松露", "香榧子", "无作物" };
    // 土壤数组
    static String[] soil = { "WHITE", "YELLOW", "REDC", "REDZ", "BROWN", "BROWNG", "BROWNB", "BLACKH", "BLACKG",
            "BLACK" };
    // 土壤中文数组
    static String[] soilC = { "白壤", "黄壤", "赤红壤", "砖红壤", "棕壤", "棕钙土", "褐土", "灰黑土", "黑钙土", "黑土" };
    // 食物数组
    static String[] foodsList = { "Salt", "Suger", "Oil", "Milk", "Flour", "Bread", "Steak", "ChichenSteak", "Honey",
            "Egg", "Grill", "RoastTurkey", "Biscuit", "Cheese", "Toast", "Hamburger", "Sandwich", "TomatoAndEggs",
            "PearlJadeiteWhiteJadeSoup", "Coffee", "Pizza", "Salad" };
    // 食物中文数组
    static String[] foodsListC = { "盐", "糖", "油", "牛奶", "面粉", "小麦面包", "牛排", "鸡排", "蜂蜜", "鸡蛋", "烤鱼", "烤火鸡", "饼干", "奶酪",
            "吐司", "汉堡包", "三明治", "番茄鸡蛋汤", "珍珠翡翠白玉汤", "咖啡", "披萨", "沙拉" };
    // 声明一个字符串数组，用于存储动物名称
    static String[] animals = { "Ox", "Chicken", "Pig", "Fish", "Rabbit", "Sheep", "Duck", "Cow", "Horse", "Bee",
            "Turkey" };
    // 声明一个字符串数组，用于存储中文动物名称
    static String[] animalsC = { "牛", "鸡", "猪", "鱼", "兔子", "羊", "鸭", "奶牛", "马", "蜜蜂", "火鸡" };
    // 声明一个字符串数组，用于存储成就名称
    static String[] achievements = { "NewPlayer", "Harvest", "Seed", "All", "GoodLuck", "Again", "Help", "Money",
            "CanMove", "Delicious", "Expensive", "LongTime", "Level", "Wallet", "FinalPlayer", "AutoRun" };
    // 声明一个字符串数组，用于存储中文成就名称
    static String[] achievementsC = { "新玩家", "丰收[第一次收获]", "种子选手[第一次种植作物]", "纵观全局[第一次查看市场价格]", "好运来[第一次随机抽取]",
            "再一次[第一次重置存档]", "找个帮手[拥有一个员工]", "第一桶金[第一次售卖]", "会动的[第一次养殖动物]", "真美味[第一次制作食物]", "真贵[第一次在市场购买]",
            "好久不见[长时间不关注存档]", "农场老户[等级到达30]", "商业大亨[钱包到达10000000000元]", "最终赢家[等级到达100]", "自动化" };
    // 刷新率
    static int fps = 60;
    static int Weight = 65;
    static int Height = 30;
    static int halfWeight = Weight / 2;
    static int halfHeight = Height / 2;
    static NewUnicodeScreen screen = new NewUnicodeScreen("文字农场", Weight, Height, fps);
    static boolean newPlayer;
    static String saveName = "auto";
    // 读取存档
    static ObjectMapper mapper = new ObjectMapper();
    // 世界
    static World world;
    static int seed;
    static String[] words = { "草", "雪", "花", "树", "林", "森", "沙", "泥", "水", "海", "竹", "冱", "冰", "叶", "枝", "岩" };
    // 游戏时
    static long nowGameTime;
    static long gameTimeLastUpdate;
    static long aYear = 2 * 24 * 60 * 60 * 1000;
    static long aSeason = aYear / 4;
    static long aMonth = aSeason / 3;
    static long aDay = aMonth / 30;
    static long aHour = aDay / 24;
    static String[] sky = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
    static String[] land = { "子鼠", "丑牛", "寅虎", "卯兔", "辰龙", "巳蛇", "午马", "未羊", "申猴", "酉鸡", "戌狗", "亥猪" };
    static String[] months = { "正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月" };
    static String[] days = { "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五",
            "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十" };
    static String[] periods = { "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑",
            "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至", "小寒", "大寒" };
    static String[] hours = { "子时", "丑时", "寅时", "卯时", "辰时", "巳时", "午时", "未时", "申时", "酉时", "戌时", "亥时" };
    // 动物行走
    static long LastMove;
    // 新手教程
    static int nowStep;
    static long lastStepTime;
    static String[] steps = {
            "欢迎来到世界模式，在此，你可以通过 W/S/A/D 控制移动",
            "看到了吗，在坐标(0,0)处，有一块土地，数字是它的编号，现在，按下 ESC 来到仓库",
            "在仓库中，你可以看到你所有的物品，现在，通过 W/S 键，选择 进入农场 来进行农作物的种植",
            "你来到了农场，在这里，你可以看到分布于世界各处的农田状况。现在，选择 进入仓库 来购买种子",
            "现在，选择 进入市场",
            "现在，你来到了市场，在这里，你可以购买所有你需要的物品。现在，选择 购买种子",
            "现在，你来到了种植作物界面，你可以选择你想要种植的作物。每一样作物都有自己的编号，现在，输入 A23",
            "现在，你选择了葱种子，你可以选择种植的数量，现在，输入 1",
            "好了，一颗葱种子已经购买完成了，现在，回到农场",
            "选择 种植作物",
            "现在，你需要选择需要种植的土壤，当农田过多时，可以进行自动种植，不过，现在请输入 1",
            "现在，你需要选择需要种植的作物，输入 1",
            "恭喜你，完成了第一块农田的种植，现在，请等待作物成熟之后，选择 收获作物",
            "这里展示了这次收获的情况，请按下 Enter",
            "收获完成的作物将存放在仓库里，选择 进入仓库",
            "现在，我们要进行作物售卖，选择 售出作物",
            "这里要输入需要售卖的作物，现在输入 1",
            "这里要输入需要售卖的作物的数量，现在输入 2",
            "恭喜你，赚到了第一笔金，相信你一定能在接下来的游戏中玩的开心。现在，选择 进入世界",
            "本次新手教程到此结束，希望你能在游戏中玩得愉快"
    };
    // 股票
    static List<Stock> stockList = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setInput();

        try {
            File soundFile = new File(currentDirectory + "\\Nature.wav");
            FileInputStream fileInputStream = new FileInputStream(soundFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (IOException | LineUnavailableException e) {
            showAWordForSomeSeconds("音频加载失败", 1);
            e.printStackTrace();
        }

        File file = new File(currentDirectory + "\\setting.json");
        SettingData data = mapper.readValue(file, SettingData.class);
        fps = data.getFps();
        screen.setFps(fps);

        setScreen();

        boolean run = true;
        while (run) {
            run = chooseSave();
            if (!run) {
                break;
            }
            if (newPlayer) {
                newPlayerTeacher();
            }
            LastMove = System.currentTimeMillis();
            lastStepTime = System.currentTimeMillis();
            String nextInterface = "World";
            while (!nextInterface.equals("")) {
                if (nextInterface.equals("Warehouse")) {
                    nextInterface = theWarehouse();
                } else if (nextInterface.equals("Farm")) {
                    nextInterface = theFarm();
                } else if (nextInterface.equals("Market")) {
                    nextInterface = theMarket();
                } else if (nextInterface.equals("Kitchen")) {
                    nextInterface = theKitchen();
                } else if (nextInterface.equals("Pasture")) {
                    nextInterface = thePasture();
                } else if (nextInterface.equals("World")) {
                    nextInterface = MapMode();
                }
            }
            save(saveName);
            showAWordForSomeSeconds("存档中……", 0.5);
        }
        Runtime.getRuntime().halt(0);
    }

    static void newPlayerTeacherP(String informations) {
        boolean checkRR = (informations.equals("WSAD") && nowStep == 0)
                || (informations.equals("toWarehouse") && nowStep == 1)
                || (informations.equals("toFarm") && nowStep == 2)
                || (informations.equals("toWarehouse") && nowStep == 3)
                || (informations.equals("toMarket") && nowStep == 4)
                || (informations.equals("chooseVegetable") && nowStep == 5)
                || (informations.equals("enterVegetableNum") && nowStep == 6)
                || (informations.equals("buySeedFinish") && nowStep == 7)
                || (informations.equals("toFarm") && nowStep == 8)
                || (informations.equals("chooseGrowSoil") && nowStep == 9)
                || (informations.equals("chooseGrowSeed") && nowStep == 10)
                || (informations.equals("finishGrow") && nowStep == 11)
                || (informations.equals("harvest") && nowStep == 12)
                || (informations.equals("checkHarvest") && nowStep == 13)
                || (informations.equals("toWarehouse") && nowStep == 14)
                || (informations.equals("enterSellVegType") && nowStep == 15)
                || (informations.equals("enterSellVegNum") && nowStep == 16)
                || (informations.equals("getMoney") && nowStep == 17)
                || (informations.equals("toWorld") && nowStep == 18)
                || (informations.equals("FINISHTEACHING") && nowStep == 19);
        if (checkRR) {
            nextTeaching();
            lastStepTime = System.currentTimeMillis();
        }
    }

    static String cutString(String s, int end) {
        if (end <= s.length()) {
            return s.substring(0, end);
        } else {
            return s;
        }
    }

    static void showTeaching() {
        screen.addWords(6, 6, cutString(getNowTeaching(), (int) (System.currentTimeMillis() - lastStepTime) / 70),
                "l");
    }

    static String getNowTeaching() {
        if (nowStep < steps.length) {
            return steps[nowStep];
        } else {
            return "";
        }
    }

    static void nextTeaching() {
        nowStep++;
    }

    static List<World.Posision> findSuitableList(int num, String type, World.Posision start) {
        List<World.Posision> list = new ArrayList<>();
        int near = 1;
        while (near * near < num) {
            near++;
        }
        for (int i = 0; i < near; i++) {
            int lll = start.x;
            if (list.size() != 0) {
                lll = list.getLast().x;
            }
            for (int j = 0; j < near; j++) {
                list.add(findSuitablePosision(new World.Posision(lll + 10, start.y + j), type));
            }
        }
        return list;
    }

    static World.Posision findSuitablePosision(World.Posision p) {
        return findSuitablePosision(p, "");
    }

    static World.Posision findSuitablePosision(World.Posision p, String type) {
        int x = p.x;
        int y = p.y;
        SimpleEntry<String, String> getStringA = world.updateFunction
                .apply(world.makeFunction.apply(new World.InnerWorld(x, y, "", "#CCCCCC")));
        String getString = getStringA.getKey();
        if (type.equals("")) {
            List<String> cannot = new ArrayList<>();
            cannot.add("水");
            cannot.add("海");
            while (true) {
                if (!cannot.stream().anyMatch(s -> s.equals(getString))) {
                    int ex = x;
                    if (!soilList.stream().anyMatch(s -> Math.abs(ex - s.x) < 10)) {
                        return new World.Posision(x, y);
                    }
                }
                x++;
            }
        } else if (type.equals("animal")) {
            List<String> cannot = new ArrayList<>();
            cannot.add("水");
            cannot.add("海");
            while (true) {
                if (!cannot.stream().anyMatch(s -> s.equals(getString))) {
                    int ex = x;
                    if (!animalList.stream().anyMatch(s -> Math.abs(ex - s.x) < 3)) {
                        return new World.Posision(x, y);
                    }
                }
                x++;
            }
        } else if (type.equals("fish")) {
            List<String> cannot = new ArrayList<>();
            while (true) {
                if (!cannot.stream().anyMatch(s -> s.equals(getString))) {
                    int ex = x;
                    if (!animalList.stream().anyMatch(s -> Math.abs(ex - s.x) < 3)) {
                        return new World.Posision(x, y);
                    }
                }
                x++;
            }
        }
        return p;
    }

    static String getDayName() {
        int years = (int) (nowGameTime / aYear);
        int day = (int) (nowGameTime % aYear / aDay);
        int hour = (int) (nowGameTime % aDay / aHour / 2);
        String re = sky[years % 10] + land[years % 12] + "年" + months[day / 30] + days[day % 30] + " " + hours[hour];
        return re;
    }

    static String getPeriods() {
        int day = (int) (nowGameTime % aYear / aDay);
        return periods[day / 15];
    }

    static double getTimeUp() {
        return ((-1) * Math.abs(nowGameTime % aYear - aSeason * 2) / (aYear * 1.0) + 1);
    }

    static boolean useTimeToShow(double show, int steps) {
        int move = 100 / steps;
        int up = (100 - move) / steps;
        return show >= (int) (nowGameTime % steps) * up && show <= (int) (nowGameTime % steps) * up + move;
    }

    static String whichCommuty(String s) {
        String re = s;
        if (s.equals("Forest")) {
            re = "树";
        } else if (s.equals("Bamboo")) {
            re = "竹";
        } else if (s.equals("Sea")) {
            re = "海";
        } else if (s.equals("Sand")) {
            re = "岩";
        } else if (s.equals("Plant")) {
            re = "草";
        } else if (s.equals("Snow")) {
            re = "冱";
        }
        return re;
    }

    static boolean checkFind(int x, int y, String type) {
        World.InnerWorld fi = new World.InnerWorld(x, y, "", "#CCCCCC");
        fi.setSelf(world.makeFunction.apply(fi));
        if (whichCommuty(world.updateFunction.apply(fi).getKey()).equals(whichCommuty(type))) {
            return true;
        } else {
            return false;
        }
    }

    static World.Posision findCommunity(World.Posision posision, String type) {
        int x = 0;
        int y = 0;
        screen.addWords(Weight / 2, Height / 2, "查找中");
        while (true) {
            for (int i = -1 * x; i < x; i++) {
                screen.showScreen();
                if (checkFind(posision.x + i, posision.y + y, type)) {
                    return World.createPosision(posision.x + i, posision.y + y);
                }
            }
            for (int i = -1 * x; i < x; i++) {
                screen.showScreen();
                if (checkFind(posision.x + i, posision.y - y, type)) {
                    return World.createPosision(posision.x + i, posision.y - y);
                }
            }
            for (int i = -1 * y; i < y; i++) {
                screen.showScreen();
                if (checkFind(posision.x + x, posision.y + i, type)) {
                    return World.createPosision(posision.x + x, posision.y + i);
                }
            }
            for (int i = -1 * y; i < y; i++) {
                screen.showScreen();
                if (checkFind(posision.x - x, posision.y + i, type)) {
                    return World.createPosision(posision.x - x, posision.y + i);
                }
            }
            x++;
            y++;
        }
    }

    static String MapMode() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        world.setNoiseType(FastNoiseLite.NoiseType.Perlin);
        world.setSeed(seed);
        world.setBlockData(posision -> {
            World.InnerWorld re = new World.InnerWorld(posision.x, posision.y, "", "#CCCCCC");
            double high = world.getNoise(re.x, re.y, -50, 1000, 0.05f);
            double humidity = world.getNoise(posision.x, posision.y, 0, 100, 0.9f);
            double show = world.getNoise(re.x, re.y, 0, 100, 0.9f);
            double communitySeaAndSand = world.getNoise(re.x, re.y, 0, 10000, 0.001f, seed);
            double communityTreeAndBamboo = world.getNoise(re.x, re.y, 0, 10000, 0.001f, seed * 5);
            double communitySnow = world.getNoise(re.x, re.y, 0, 10000, 0.001f, seed * 10);
            re.data.put("high", high);
            re.data.put("humidity", humidity);
            re.data.put("show", show);
            re.data.put("communitySeaAndSand", communitySeaAndSand);
            re.data.put("communityTreeAndBamboo", communityTreeAndBamboo);
            re.data.put("communitySnow", communitySnow);
            return re;
        });
        world.setUpdateData(posision -> {
            String re = "";
            String color = "#CCCCCC";
            boolean canShowMe = true;
            double high = posision.data.get("high");
            double humidity = posision.data.get("humidity");
            double show = posision.data.get("show");
            double communitySeaAndSand = posision.data.get("communitySeaAndSand");
            double communityTreeAndBamboo = posision.data.get("communityTreeAndBamboo");
            double communitySnow = posision.data.get("communitySnow");
            if (communitySeaAndSand >= 7000) {
                if (communitySeaAndSand >= 7500) {
                    if (useTimeToShow(show, 7)) {
                        re = "海";
                        color = NewUnicodeScreen.DARK_BLUE;
                    }
                    canShowMe = false;
                } else {
                    if (show >= 70) {
                        re = "沙";
                        color = NewUnicodeScreen.DARK_YELLOW;
                    }
                }
            } else if (communitySeaAndSand <= 2000) {
                if (show >= 70) {
                    re = "沙";
                    color = NewUnicodeScreen.YELLOW;
                }
                if (show <= 15) {
                    re = "岩";
                    color = NewUnicodeScreen.DARK_GRAY;
                }
            } else if (communityTreeAndBamboo >= 8000) {
                if (communityTreeAndBamboo >= 9000) {
                    if (show <= 60) {
                        re = "森";
                        color = NewUnicodeScreen.DARK_GREEN;
                        canShowMe = false;
                    }
                } else if (communityTreeAndBamboo >= 8500) {
                    if (show <= 45) {
                        re = "林";
                        color = NewUnicodeScreen.LIGHT_GREEN;
                        canShowMe = false;
                    }
                } else {
                    if (show <= 30) {
                        re = "树";
                        color = NewUnicodeScreen.LIGHT_GREEN;
                    }
                }
                if (re == "") {
                    if (show >= 90) {
                        re = "叶";
                        color = NewUnicodeScreen.DARK_GREEN;
                    } else if (show >= 83) {
                        re = "枝";
                        color = NewUnicodeScreen.BROWN;
                    }
                }
            } else if (communityTreeAndBamboo <= 1500) {
                if (show >= 75) {
                    re = "竹";
                    color = NewUnicodeScreen.DARK_GREEN;
                }
                if (show <= 20) {
                    re = "笋";
                    color = NewUnicodeScreen.LIGHT_GREEN;
                }
            } else if (communitySnow >= 9000) {
                if (show >= 70) {
                    re = "雪";
                    color = NewUnicodeScreen.WHITE;
                }
                if (show <= 15) {
                    re = "冱";
                    color = "#436675";
                }
                if (show >= 40 && show <= 45) {
                    re = "冰";
                    color = NewUnicodeScreen.LIGHT_BLUE;
                }
            } else {
                if (high >= 800 + getTimeUp() * 150) {
                    re = "雪";
                    color = NewUnicodeScreen.WHITE;
                } else if (high <= 150
                        + getTimeUp() * 30) {
                    if (high >= 0 + getTimeUp() * 200) {
                        re = "冰";
                        color = NewUnicodeScreen.LIGHT_BLUE;
                    } else if (useTimeToShow(show, 5)) {
                        re = "水";
                        color = NewUnicodeScreen.LIGHT_BLUE;
                        canShowMe = false;
                    } else {
                        re = "";
                        canShowMe = false;
                    }
                } else if (high <= 200) {
                    re = "泥";
                    color = NewUnicodeScreen.BROWN;
                } else if (humidity > 85) {
                    re = "花";
                    color = NewUnicodeScreen.PINK;
                } else if (humidity > 80) {
                    re = "草";
                    color = NewUnicodeScreen.LIGHT_GREEN;
                }
            }
            if (posision.x == world.playerPosision.x && posision.y == world.playerPosision.y) {
                if (canShowMe) {
                    re = "我";
                    color = NewUnicodeScreen.NORMAL;
                }
            }
            return new SimpleEntry<>(re, color);
        });
        world.allRefresh();
        World.Posision a = new World.Posision(0, 0);
        newPlayerTeacherP("toWorld");
        int code = 0;
        while (run) {
            update();
            world.update();
            setScreen();
            levelShow = getLevelShow();

            String getEnterString = screen.getFunctionReturn();
            if (getEnterString.equals("W")) {
                a.y += 2;
                newPlayerTeacherP("WSAD");
            } else if (getEnterString.equals("S")) {
                a.y -= 2;
                newPlayerTeacherP("WSAD");
            } else if (getEnterString.equals("A")) {
                a.x -= 2;
                newPlayerTeacherP("WSAD");
            } else if (getEnterString.equals("D")) {
                a.x += 2;
                newPlayerTeacherP("WSAD");
            } else if (getEnterString.equals("Escape")) {
                nextInterface = "Warehouse";
                run = false;
            } else if (getEnterString.equals("T")) {
                code++;
                if (code >= 5) {
                    String committed = screen.getEnterString("请输入指令", "指令");
                    List<String> comm = Arrays.asList(committed.split(" "));
                    if (comm.size() != 0) {
                        if (comm.size() == 3 && comm.get(0).equals("/tp")) {
                            world.playerPosision = World.createPosision(Integer.valueOf(comm.get(1)),
                                    Integer.valueOf(comm.get(2)));
                        } else if (comm.size() == 2 && comm.get(0).equals("/find")) {
                            if (comm.get(1).equals("Forest")
                                    || comm.get(1).equals("Sand")
                                    || comm.get(1).equals("Sea")
                                    || comm.get(1).equals("Bamboo")
                                    || comm.get(1).equals("Plant")
                                    || comm.get(1).equals("Snow")
                                    || Arrays.asList(words).contains(comm.get(1))) {
                                World.Posision p = findCommunity(world.playerPosision, comm.get(1));
                                String findC = "";
                                if (comm.get(1).equals("Forest")) {
                                    findC = "森林";
                                } else if (comm.get(1).equals("Sand")) {
                                    findC = "沙漠";
                                } else if (comm.get(1).equals("Sea")) {
                                    findC = "海洋";
                                } else if (comm.get(1).equals("Bamboo")) {
                                    findC = "竹林";
                                } else if (comm.get(1).equals("Plant")) {
                                    findC = "平原";
                                } else if (comm.get(1).equals("Snow")) {
                                    findC = "雪原";
                                } else {
                                    findC = "汉字 " + comm.get(1);
                                }
                                while (true) {
                                    screen.addWords(Weight / 2, Height / 2,
                                            "最近的 " + findC + " 在 (" + p.x + "," + p.y + ") 处");
                                    screen.showScreen();
                                    String enterSt = screen.getFunctionReturn();
                                    if (enterSt.equals("Enter")) {
                                        break;
                                    }
                                }
                            } else {
                                showAWordForSomeSeconds("指令存在语法错误", 0.5);
                            }
                        } else if (comm.size() == 3 && comm.get(0).equals("/get")) {
                            if (comm.get(1).equals("Animal")) {
                                if (Integer.valueOf(comm.get(2)) <= animalList.size()
                                        && Integer.valueOf(comm.get(2)) > 0) {
                                    while (true) {
                                        screen.addWords(Weight / 2, Height / 2,
                                                "编号为 " + Integer.valueOf(comm.get(2)) + " 的动物在 ("
                                                        + animalList.get(Integer.valueOf(comm.get(2)) - 1).x + ","
                                                        + animalList.get(Integer.valueOf(comm.get(2)) - 1).y + ") 处");
                                        screen.showScreen();
                                        String enterSt = screen.getFunctionReturn();
                                        if (enterSt.equals("Enter")) {
                                            break;
                                        }
                                    }
                                } else {
                                    showAWordForSomeSeconds("不存在编号为 " + Integer.valueOf(comm.get(2)) + " 的动物", 1);
                                }
                            } else if (comm.get(1).equals("Soil")) {
                                if (Integer.valueOf(comm.get(2)) <= soilList.size()
                                        && Integer.valueOf(comm.get(2)) > 0) {
                                    while (true) {
                                        screen.addWords(Weight / 2, Height / 2,
                                                "编号为 " + Integer.valueOf(comm.get(2)) + " 的农田在 ("
                                                        + soilList.get(Integer.valueOf(comm.get(2)) - 1).x + ","
                                                        + soilList.get(Integer.valueOf(comm.get(2)) - 1).y + ") 处");
                                        screen.showScreen();
                                        String enterSt = screen.getFunctionReturn();
                                        if (enterSt.equals("Enter")) {
                                            break;
                                        }
                                    }
                                } else {
                                    showAWordForSomeSeconds("不存在编号为 " + Integer.valueOf(comm.get(2)) + " 的农田", 1);
                                }
                            } else {
                                showAWordForSomeSeconds("指令存在语法错误", 0.5);
                            }
                        } else {
                            showAWordForSomeSeconds("指令存在语法错误", 0.5);
                        }
                    } else {
                        showAWordForSomeSeconds("指令存在语法错误", 0.5);
                    }
                    code = 0;
                }
            }

            int top = 10;
            if (a.x < 0) {
                if (a.x < (-1) * top) {
                    a.x = (-1) * top;
                } else {
                    a.x++;
                }
            } else if (a.x > 0) {
                if (a.x > top) {
                    a.x = top;
                } else {
                    a.x--;
                }
            }
            if (a.y < 0) {
                if (a.y < (-1) * top) {
                    a.y = (-1) * top;
                } else {
                    a.y++;
                }
            } else if (a.y > 0) {
                if (a.y > top) {
                    a.y = top;
                } else {
                    a.y--;
                }
            }
            world.movePlayer(a.x, -1 * a.y);

            soilList.forEach(s -> {
                world.addSpecial(World.createPosision(s.x, s.y),
                        "[" + (soilList.indexOf(s) + 1) + "|" + vagetablesC[Arrays.asList(vagetables)
                                .indexOf(vagetablesList.get(soilList.indexOf(s)).getId())] + "]");
            });

            animalList.forEach(an -> {
                world.addSpecial(World.createPosision(an.x, an.y),
                        "[" + (animalList.indexOf(an) + 1) + "|" + animalsC[Arrays.asList(animals)
                                .indexOf(an.getId())] + "]");
            });

            world.toScreen(screen);
            showTeaching();
            screen.addWords(Weight - 1, 1, getDayName(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(Weight - 6, 2, getPeriods(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(1, 1, "钱包：" + money + "元  Level " + levelShow, "l", NewUnicodeScreen.LIGHT_GREEN);
            screen.addWords(1, Height - 2, "x:" + world.playerPosision.x + " y:" + world.playerPosision.y, "l",
                    NewUnicodeScreen.DARK_GRAY);
            screen.showScreen();
            if (System.currentTimeMillis() - Lastsave >= 20 * 1000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static void setInput() {
        input.clear();
        input.add("");
        input.add("");
    }

    static boolean Load(String saveS) {
        screen.addWords(halfWeight, halfHeight, "存档加载中……");

        boolean newPlayer = false;

        try {
            File file = new File(currentDirectory + "\\" + saveS + "\\locals.json");
            FarmData data = mapper.readValue(file, FarmData.class);
            // setting
            money = data.getSetting().getMoney();
            LastUpdateTime = data.getSetting().getLastUpdateTime();
            level = data.getSetting().getLevel();
            getVersion = data.getSetting().getVersion();
            nowGameTime = data.getSetting().getTime();
            nowStep = data.getSetting().getStep();
            gameTimeLastUpdate = LastUpdateTime;
            // player
            screen.showScreen();
            // vagetables
            for (FarmData.Farm v : data.getVagetableList()) {
                Vagetables vagetables = new Vagetables(v.getVegetable());
                vagetables.setGrow(v.getGrow());
                vagetablesList.add(vagetables);
                Soil soil = new Soil(v.getSoil(), World.createPosision(v.getX(), v.getY()));
                soil.setX(v.getX());
                soil.setY(v.getY());
                soilList.add(soil);
            }
            screen.showScreen();
            // animals
            for (FarmData.Animal a : data.getAnimalList()) {
                Animal animals = new Animal(a.getAnimal(), a.getMode(), a.getSex());
                animals.setX(a.getX());
                animals.setY(a.getY());
                animalList.add(animals);
            }
            screen.showScreen();
            // warehouse
            for (FarmData.Warehouse w : data.getWarehouseList()) {
                String type = w.getType();
                if (type.equals("Food")) {
                    Warehouse<Food> wareh = new Warehouse<>();
                    wareh.setType(type);
                    wareh.setName(w.getName());
                    wareh.setObject(new Food(w.getName()));
                    addWarehouse(wareh, 1);
                } else if (type.equals("VAGETABLES") || type.equals("SEEDS")) {
                    Warehouse<Vagetables> wareh = new Warehouse<>();
                    wareh.setType(type);
                    wareh.setName(w.getName());
                    addWarehouse(wareh, 1);
                } else if (type.equals("Animal")) {
                    Warehouse<Animal> wareh = new Warehouse<>();
                    wareh.setType(type);
                    wareh.setName(w.getName());
                    addWarehouse(wareh, 1);
                }
            }
            screen.showScreen();
            // employee
            for (FarmData.Employee e : data.getEmployeeList()) {
                String type = e.getType();
                if (type.equals("Farm")) {
                    Employee emp = new Employee(e.getName());
                    emp.setX(e.getX());
                    emp.setY(e.getY());
                    emp.setEnerge(e.getEnerge());
                    farmerList.add(emp);
                } else if (type.equals("Cooker")) {
                    Cooker emp = new Cooker(e.getName(), e.getEnerge(), e.getFood());
                    emp.setX(e.getX());
                    emp.setY(e.getY());
                    cookerList.add(emp);
                } else if (type.equals("Buyer")) {
                    Buyer emp = new Buyer(e.getName(), e.getEnerge(), e.getBuyType(), e.getWill(), e.getTop());
                    emp.setX(e.getX());
                    emp.setY(e.getY());
                    buyerList.add(emp);
                } else if (type.equals("Seller")) {
                    Seller emp = new Seller(e.getName(), e.getEnerge(), e.getBuyType(), e.getWill(), e.getTop());
                    emp.setX(e.getX());
                    emp.setY(e.getY());
                    sellerList.add(emp);
                }
            }
            screen.showScreen();
            // achievement
            for (FarmData.Achievement a : data.getAchievementList()) {
                Achievement achi = new Achievement(a.getName(), a.getTime());
                achievementList.add(achi);
            }
            screen.showScreen();
            // world
            FarmData.World worldg = data.getWorld();
            World.Posision playerp = World.createPosision(worldg.getX(), worldg.getY());
            seed = worldg.getSeed();
            world = new World(Weight * 2, Height * 2, World.createPosision(playerp.x - Weight, playerp.y + Height),
                    playerp, seed);
            screen.showScreen();
            // stock
            File fileS = new File(currentDirectory + "\\" + saveS + "\\stock.json");
            StockData dataS = mapper.readValue(fileS, StockData.class);
            for (StockData.InnerStockData s : dataS.getStock()) {
                Stock stock = new Stock(s.getName(), s.getPrice(), s.getHave(), s.getSeed());
                stockList.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            screen.showScreen();
            clearAll();
            vagetablesList.add(new Vagetables("NONE"));
            soilList.add(new Soil(soil[1], World.createPosision(0, 0)));
            money = 15;
            LastUpdateTime = System.currentTimeMillis();
            level = 0;
            seed = random.nextInt();
            world = new World(Weight * 2, Height * 2, World.createPosision(-1 * Weight, Height),
                    World.createPosision(0, 0), seed);
            nowGameTime = 0;
            nowStep = 0;
            gameTimeLastUpdate = LastUpdateTime;
            save(saveS);
            newPlayer = true;
        }

        return newPlayer;
    }

    static boolean chooseSave() {
        boolean run = true;
        File diractory = new File(currentDirectory);
        File[] files = diractory.listFiles();
        List<String> fileList = Arrays.stream(files).filter(f -> f.isDirectory()).map(File::getName)
                .collect(Collectors.toList());
        fileList.add("[ 新建存档 ]");
        int nowChoice = 0;
        while (run) {
            setScreen();
            screen.addWords(halfWeight - 21, halfHeight - 5, "          农        ", "l");
            screen.addWords(halfWeight - 21, halfHeight - 4, "农农农农农农农农农农农", "l");
            screen.addWords(halfWeight - 21, halfHeight - 3, "农        农        农", "l");
            screen.addWords(halfWeight - 21, halfHeight - 2, "        农            ", "l");
            screen.addWords(halfWeight - 21, halfHeight - 1, "      农        农    ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 0, "    农  农   农       ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 1, "  农农    农农        ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 2, "农  农      农        ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 3, "    农        农      ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 4, "    农    农    农    ", "l");
            screen.addWords(halfWeight - 21, halfHeight + 5, "    农农农        农农", "l");

            for (int i = -7; i < 8; i++) {
                screen.addWords(NewUnicodeScreen.DARK_GRAY, halfWeight - 6, halfHeight + i, "|");
            }

            screen.addWords(halfWeight + 10, halfHeight - 7, "文    字    农    场");
            screen.addWords(NewUnicodeScreen.DARK_GRAY, halfWeight + 10, halfHeight + 7, "version " + version);
            screen.addWords(halfWeight + 10, halfHeight - 2, "请选择存档[" + (nowChoice + 1) + "/" + fileList.size() + "]");
            screen.addWords(NewUnicodeScreen.LIGHT_BLUE, halfWeight + 10, halfHeight, fileList.get(nowChoice));
            screen.addWords(halfWeight + 10 - 10, halfHeight, "<[A]");
            screen.addWords(halfWeight + 10 + 10, halfHeight, "[D]>");
            if (nowChoice != fileList.size() - 1) {
                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight + 10, halfHeight + 2, "按 Enter 键加载存档");
                screen.addWords(NewUnicodeScreen.RED, halfWeight + 10, halfHeight + 3, "按 Delete 键删除存档");
            } else {
                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight + 10, halfHeight + 2, "按 Enter 键创建新存档");
            }
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("A") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("D") && nowChoice < fileList.size() - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                run = false;
            } else if (resultKey.equals("Escape")) {
                boolean quitr = false;
                while (true) {
                    setScreen();
                    screen.addWords(halfWeight, halfHeight - 2, "确认退出游戏？");
                    screen.addWords(halfWeight - 2, halfHeight, "是", "l", NewUnicodeScreen.RED);
                    screen.addWords(halfWeight + 2, halfHeight, "否", "l", NewUnicodeScreen.LIGHT_GREEN);
                    if (quitr) {
                        screen.addWords(halfWeight - 3, halfHeight, "> ", "l");
                    } else {
                        screen.addWords(halfWeight + 1, halfHeight, "> ", "l");
                    }
                    String resultKeyT = screen.getFunctionReturn();
                    if (resultKeyT.equals("Enter")) {
                        break;
                    } else if (resultKeyT.equals("A")) {
                        quitr = true;
                    } else if (resultKeyT.equals("D")) {
                        quitr = false;
                    }
                    screen.showScreen();
                }
                if (quitr) {
                    return false;
                }
            } else if (resultKey.equals("Delete")) {
                if (nowChoice < fileList.size() - 1) {
                    boolean quitr = false;
                    while (true) {
                        setScreen();
                        screen.addWords(halfWeight, halfHeight - 2, "确认删除存档？");
                        screen.addWords(halfWeight - 2, halfHeight, "是", "l", NewUnicodeScreen.RED);
                        screen.addWords(halfWeight + 2, halfHeight, "否", "l", NewUnicodeScreen.LIGHT_GREEN);
                        if (quitr) {
                            screen.addWords(halfWeight - 3, halfHeight, "> ", "l");
                        } else {
                            screen.addWords(halfWeight + 1, halfHeight, "> ", "l");
                        }
                        String resultKeyT = screen.getFunctionReturn();
                        if (resultKeyT.equals("Enter")) {
                            break;
                        } else if (resultKeyT.equals("A")) {
                            quitr = true;
                        } else if (resultKeyT.equals("D")) {
                            quitr = false;
                        }
                        screen.showScreen();
                    }
                    if (quitr) {
                        deleteSave(currentDirectory + "\\" + fileList.get(nowChoice));
                        fileList.remove(nowChoice);
                    }
                }

            }
            screen.showScreen();
        }
        if (nowChoice != fileList.size() - 1) {
            saveName = fileList.get(nowChoice);
        } else {
            run = true;
            while (run) {
                saveName = screen.getEnterString("请输入创建的存档的名字", "新建存档");
                if (saveName.equals("") || fileList.stream().anyMatch(sa -> sa.equals(saveName))) {
                    showAWordForSomeSeconds("存档名不能为空或重复", 0.5);
                } else {
                    run = false;
                }
            }
            new File(currentDirectory + "\\" + saveName).mkdir();
        }

        clearAll();

        newPlayer = Load(saveName);

        if (!getVersion.equals(version) && !newPlayer) {
            showAWordForSomeSeconds("当前存档版本[" + getVersion + "]与游戏版本不符，可能导致游戏出错，请谨慎游玩！", 2.5);
        }
        return true;
    }

    static void deleteSave(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        for (File f : list) {
            if (f.isDirectory()) {
                deleteSave(f.getPath());
            } else {
                f.delete();
            }
        }
        file.delete();
    }

    static void clearAll() {
        vagetablesList.clear();
        soilList.clear();
        animalList.clear();
        farmerList.clear();
        cookerList.clear();
        buyerList.clear();
        sellerList.clear();
        warehouseList.clear();
        warehouseListNumber.clear();
        achievementList.clear();
        money = 0;
        level = 0;
        LastUpdateTime = System.currentTimeMillis();
    }

    static String thePasture() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        int nowChoice = 0;
        while (run) {
            update();
            setScreen();
            levelShow = getLevelShow();
            showTeaching();
            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3, "钱包：" + money + "元  Level " + levelShow);
            screen.addWords(Weight - 1, 1, getDayName(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(Weight - 6, 2, getPeriods(), "r", NewUnicodeScreen.LIGHT_BLUE);
            printPasture(false);
            String needprint[] = {
                    "1.购买动物",
                    "2.获得成熟动物",
                    "3.进入仓库",
                    "4.进入农场",
                    "5.随机购买动物(￥" + (long) Math.floor(10000 * levelShow) + "/个)",
                    "6.查看动物市场价格",
                    "7.进入世界",
                    "8.存档并退出农场",
                    "9.重置存档"
            };
            for (int i = 0; i < needprint.length; i++) {
                String p = needprint[i];
                int move = 0;
                if (i == nowChoice) {
                    p = "> " + p;
                    move = 1;
                }
                screen.addWords(halfWeight - 4 - move, Height - (15 - i), p, "l");
            }
            int a = 0;
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("W") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("S") && nowChoice < needprint.length - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                a = nowChoice + 1;
            }
            screen.showScreen();

            switch (a) {
                case 1:
                    setInput();
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 5,
                                "钱包：" + money + "元  Level " + levelShow);
                        int line = 0;
                        int list = 0;
                        for (int i = 0; i < animals.length; i++) {
                            screen.addWords(halfWeight - 21 + 14 * list, 6 + line,
                                    "<" + (i + 1) + ">" + animalsC[i] + "：￥" + (int) (testAnimal
                                            .getCost(animals[i])
                                            * getUpPrice()),
                                    "l");
                            line++;
                            if (line != 0 && line % (int) (Math.ceil(animals.length / 3.0)) == 0) {
                                list++;
                                line = 0;
                            }
                        }
                        screen.addWords(halfWeight, halfHeight + 6, "请输入需要购买的动物的编号");
                        screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (Integer.valueOf(input.get(0)) > 0
                                    && Integer.valueOf(input.get(0)) <= animals.length) {
                                screen.addWords(halfWeight, halfHeight + 9,
                                        "当前选择 " + animalsC[Integer.valueOf(input.get(0)) - 1] +
                                                " ，可购买 "
                                                + (int) (money
                                                        / (testAnimal
                                                                .getCost(
                                                                        animals[Integer.valueOf(input.get(0)) - 1])
                                                                * getUpPrice()))
                                                + " 只");
                            }
                        }
                        screen.showScreen();
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                    }
                    if (Integer.valueOf(input.get(0)) < 1 || Integer.valueOf(input.get(0)) > animals.length) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }

                    int type = Integer.valueOf(input.get(0)) - 1;

                    setInput();
                    while (true) {
                        screen.addWords(halfWeight, halfHeight - 3, "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight - 1, "当前选择 " + animalsC[type] + " ，可购买"
                                + (int) (money
                                        / (testAnimal
                                                .getCost(animals[type])
                                                * getUpPrice()))
                                + " 只，请输入需要购买数量");
                        screen.addWords(halfWeight, halfHeight, input.get(0));
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        screen.showScreen();
                    }

                    if (Integer.valueOf(input.get(0)) < 1) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    } else if (Integer.valueOf(input.get(0)) > (int) (money
                            / (testAnimal
                                    .getCost(animals[type])
                                    * getUpPrice()))) {
                        showAWordForSomeSeconds("资金不足", 0.5);
                        break;
                    }

                    int buy = Integer.valueOf(input.get(0));
                    for (int i = 0; i < buy; i++) {
                        Animal ani = new Animal(animals[type], 0, random.nextInt(2));
                        ani.x = world.playerPosision.x;
                        ani.y = world.playerPosision.y;
                        animalList.add(ani);
                        money -= (int) (ani.getCost(animals[type]) * getUpPrice());
                    }
                    showAWordForSomeSeconds("购买成功", 0.5);
                    getAchievement("CanMove");
                    break;
                case 2:
                    List<Animal> endAnimals = animalList.stream().filter(ani -> ani.grow == ani.top).toList();
                    if (endAnimals.size() != 0) {
                        harvestAnimal();
                        showAWordForSomeSeconds("获取完成", 0.5);
                    } else {
                        showAWordForSomeSeconds("无成熟动物", 0.5);
                    }
                    break;
                case 3:
                    nextInterface = "Warehouse";
                    run = false;
                    break;
                case 4:
                    nextInterface = "Farm";
                    run = false;
                    break;
                case 5:
                    long price = (long) Math.floor(10000 * levelShow);
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight - 2, "请输入要购买的动物数量");
                        screen.addWords(halfWeight, halfHeight - 1, input.get(0));
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (Integer.valueOf(input.get(0)) > 0) {
                                screen.addWords(halfWeight, halfHeight + 1,
                                        "共需 " + price * Integer.valueOf(input.get(0)) + " 元");
                            }
                        }
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        screen.showScreen();
                    }
                    if (Integer.valueOf(input.get(0)) < 0) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }
                    int num = Integer.valueOf(input.get(0));
                    if (money >= num * price) {
                        for (int fnum = 0; fnum < num; fnum++) {
                            money -= price;
                            int result = random.nextInt(animals.length * 2);
                            for (int l = 0; l < 100; l++) {
                                showAWordForSomeSeconds("<" + (fnum + 1) + ">[ " + l + "% ]", 0.003);
                            }
                            if (result < animals.length) {
                                showAWordForSomeSeconds("恭喜获得 " + animalsC[result] + " ×1", 0.5);
                                animalList.add(new Animal(animals[result], 0, random.nextInt(2)));
                            } else {
                                showAWordForSomeSeconds("很遗憾，啥也没有……", 0.5);
                            }
                        }
                        getAchievement("GoodLuck");
                    } else {
                        showAWordForSomeSeconds("资金不足", 0.5);
                    }
                    break;
                case 6:
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 5,
                                "钱包：" + money + "元  Level " + levelShow);
                        int line = 0;
                        int list = 0;
                        for (int anima = 0; anima < animals.length; anima++) {
                            screen.addWords(halfWeight - 26 + 18 * list, 7 + line,
                                    animalsC[anima] + "：￥" + (int) (testAnimal.getCost(animals[anima])
                                            * getUpPrice()) + " -> ￥"
                                            + (int) (testAnimal.getMoney(animals[anima])
                                                    * getUpPrice()),
                                    "l");
                            line++;
                            if (anima % (Math.ceil((animals.length) / 3.0)) == 0 && anima != 0) {
                                list++;
                                line = 0;
                            }
                        }
                        screen.showScreen();
                        if (screen.getFunctionReturn().equals("Enter")) {
                            break;
                        }
                    }
                    getAchievement("All");
                    break;
                case 7:
                    nextInterface = "World";
                    run = false;
                    break;
                case 8:
                    run = false;
                    break;
                case 9:
                    rebuild();
                    break;
                default:
                    break;
            }
            if (System.currentTimeMillis() - Lastsave >= 20 * 1000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static void harvestAnimal() {
        List<Animal> endAnimals = animalList.stream().filter(ani -> ani.grow == ani.top).toList();
        for (Animal animal : endAnimals) {
            level += Math.floor(animal.getMoney(animals[Arrays.asList(animals).indexOf(animal.getId())])
                    * random.nextDouble(1.5));
            Warehouse<Animal> w = new Warehouse<>();
            w.setType("Animal");
            w.setName(animal.getId());
            addWarehouse(w, 1);
            letWerehouseListSmall();
            animalList.remove(animal);
        }
        getAchievement("Harvest");
    }

    static void rebuild() {
        int mode = 0;
        while (true) {
            screen.addWords(halfWeight, halfHeight - 4, "重置后会删除当前存档，所有进度将丢失，确认重置存档？");
            String f[] = { "1.取消", "2.确认" };
            for (int i = 0; i < 2; i++) {
                String s = f[i];
                int move = 0;
                if (mode == i) {
                    s = "> " + s;
                    move -= 1;
                }
                screen.addWords(halfWeight - 3 + move, halfHeight - 2 + i, s, "l");
            }
            String res = screen.getFunctionReturn();
            if (res.equals("Enter")) {
                break;
            } else if (res.equals("W") && mode > 0) {
                mode--;
            } else if (res.equals("S") && mode < 1) {
                mode++;
            }
            screen.showScreen();
        }
        int delete = mode;
        if (delete == 1) {
            clearAll();
            vagetablesList.add(new Vagetables("NONE"));
            soilList.add(new Soil(soil[1], World.createPosision(0, 0)));
            money = 15;
            level = 0;
            seed = random.nextInt();
            world.playerPosision = World.createPosision(0, 0);
            nowGameTime = 0;
            letWerehouseListSmall();
            save(saveName);
            showAWordForSomeSeconds("重置完成", 0.5);
            getAchievement("Again");
        }
    }

    static String theKitchen() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        int nowChoice = 0;
        newPlayerTeacherP("toKitchen");
        while (run) {
            update();
            setScreen();
            levelShow = getLevelShow();
            showTeaching();
            screen.addWords(halfWeight, halfHeight - 5, "钱包：" + money + "元  Level " + levelShow);
            String needprint[] = {
                    "1.制作食物",
                    "2.返回仓库",
                    "3.存档并退出游戏"
            };
            for (int i = 0; i < needprint.length; i++) {
                String p = needprint[i];
                int move = 0;
                if (i == nowChoice) {
                    p = "> " + p;
                    move = 1;
                }
                screen.addWords(halfWeight - 4 - move, halfHeight - (3 - i), p, "l");
            }
            int a = 0;
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("W") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("S") && nowChoice < needprint.length - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                a = nowChoice + 1;
            }
            screen.showScreen();

            switch (a) {
                case 1:
                    List<String> asListFood = Arrays.asList(foodsList);
                    List<String> canBeMade = new ArrayList<>();
                    for (String string : foodsList) {
                        if (testFood.getFormulation(string).size() != 0) {
                            canBeMade.add(string);
                        }
                    }
                    setInput();
                    boolean canmake = false;

                    while (true) {
                        int maxLines = (int) Math.ceil(canBeMade.size() / 3.0);
                        int line = 0;
                        int list = 0;
                        for (int i = 0; i < canBeMade.size(); i++) {
                            screen.addWords(halfWeight - 24 + 16 * list, 7 + line,
                                    "<" + (i + 1) + ">" + foodsListC[asListFood.indexOf(canBeMade.get(i))]
                                            + "["
                                            + testFood.getIfEatCanGetEnerge(
                                                    foodsList[asListFood.indexOf(canBeMade.get(i))])
                                            + "]",
                                    "l");
                            line++;
                            if (i != 0 && i % maxLines == 0) {
                                list++;
                                line = 0;
                            }
                        }
                        screen.addWords(halfWeight, halfHeight + 6, "请输入需要制作食物的编号");
                        screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (Integer.valueOf(input.get(0)) > 0
                                    && Integer.valueOf(input.get(0)) <= canBeMade.size()) {
                                int willBeMadeFood = Integer.valueOf(input.get(0));
                                List<String> formulation = testFood.getFormulation(canBeMade.get(willBeMadeFood - 1));
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
                                        have = getWarehouseNumber(l.get(0));
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
                                screen.addWords(halfWeight, halfHeight + 9,
                                        "当前选择 " + foodsListC[asListFood.indexOf(canBeMade.get(willBeMadeFood - 1))]
                                                + " ，可以制作 " + min + " "
                                                + testFood.getUnitName(canBeMade.get(willBeMadeFood - 1)));

                            }
                        }
                        screen.showScreen();
                    }
                    if (Integer.valueOf(input.get(0)) < 1
                            || Integer.valueOf(input.get(0)) > canBeMade.size()) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }

                    int willBeMadeFood = Integer.valueOf(input.get(0));
                    List<String> formulation = testFood.getFormulation(canBeMade.get(willBeMadeFood - 1));
                    long min = -1;
                    setInput();
                    while (true) {
                        List<Long> canNotMakeLast = new ArrayList<>();
                        for (String string : formulation.stream().distinct().toList()) {
                            String raw[] = string.split(",");
                            List<Warehouse<? extends Object>> l = warehouseList.stream()
                                    .filter(warehouse -> warehouse.getType().equals(raw[0]))
                                    .filter(warehouse -> warehouse.getName().equals(raw[1]))
                                    .toList();
                            int haveR = (int) l.size();
                            int have = 0;
                            if (haveR != 0) {
                                have = getWarehouseNumber(l.get(0));
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
                            canNotMakeLast.add(need - have);
                        }

                        int line = 0;
                        screen.addWords(halfWeight - 5, 5, "原料：", "l");
                        for (String string : formulation.stream().distinct().toList()) {
                            String raw[] = string.split(",");
                            String needhave = "";
                            if (canNotMakeLast.get(formulation.stream().distinct().toList().indexOf(string)) > 0) {
                                needhave = "  <- "
                                        + canNotMakeLast.get(formulation.stream().distinct().toList().indexOf(string));
                            }
                            if (raw[0].equals("Food")) {
                                screen.addWords(halfWeight - 2, 5 + line,
                                        foodsListC[asListFood.indexOf(raw[1])] + " ×"
                                                + formulation.stream().filter(f -> f.equals(string)).count() + needhave,
                                        "l");
                            } else if (raw[0].equals("VAGETABLES")) {
                                screen.addWords(halfWeight - 2, 5 + line,
                                        vagetablesC[Arrays.asList(vagetables).indexOf(raw[1])] + " ×"
                                                + formulation.stream().filter(f -> f.equals(string)).count() + needhave,
                                        "l");
                            } else if (raw[0].equals("SEEDS")) {
                                screen.addWords(halfWeight - 2, 5 + line,
                                        foodsListC[asListFood.indexOf(raw[1])] + "种子 ×"
                                                + formulation.stream().filter(f -> f.equals(string)).count() + needhave,
                                        "l");
                            } else if (raw[0].equals("Animal")) {
                                screen.addWords(halfWeight - 2, 5 + line,
                                        animalsC[Arrays.asList(animals).indexOf(raw[1])] + " ×"
                                                + formulation.stream().filter(f -> f.equals(string)).count() + needhave,
                                        "l");
                            }
                            line++;
                        }

                        if (min != 0) {
                            canmake = true;
                            screen.addWords(halfWeight, 6 + line,
                                    "你可以制作 " + min + " " + testFood.getUnitName(canBeMade.get(willBeMadeFood - 1))
                                            + foodsListC[asListFood.indexOf(canBeMade.get(willBeMadeFood - 1))]
                                            + "，请输入制作个数");
                            screen.addWords(halfWeight, 7 + line, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        } else {
                            screen.addWords(halfWeight, 6 + line, "原料不足");
                            screen.showScreen();
                            if (screen.getFunctionReturn().equals("Enter")) {
                                break;
                            }
                        }

                    }
                    if (canmake) {
                        if (Integer.valueOf(input.get(0)) < 1
                                || Integer.valueOf(input.get(0)) > min) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        }
                        int willBeMadeFoodNum = Integer.valueOf(input.get(0));
                        for (int i = 0; i < willBeMadeFoodNum; i++) {
                            for (String string : formulation) {
                                String raw[] = string.split(",");
                                int index = warehouseList
                                        .indexOf(warehouseList.stream().filter(w -> w.getType().equals(raw[0]))
                                                .filter(w -> w.getName().equals(raw[1])).toList().get(0));
                                removeWarehouse(index, 1);
                            }
                            Warehouse<Food> warehouse = new Warehouse<>();
                            warehouse.setType("Food");
                            warehouse.setObject(new Food(canBeMade.get(willBeMadeFood - 1)));
                            warehouse.setName(canBeMade.get(willBeMadeFood - 1));
                            addWarehouse(warehouse, 1);
                            letWerehouseListSmall();
                        }
                        showAWordForSomeSeconds("制作成功", 0.5);
                        getAchievement("Delicious");
                        break;
                    } else {
                        break;
                    }
                case 2:
                    nextInterface = "Warehouse";
                    run = false;
                    break;
                case 3:
                    nextInterface = "";
                    run = false;
                    break;
                default:
                    break;
            }

            if (System.currentTimeMillis() - Lastsave >= 20000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static String theMarket() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        int nowChoice = 0;
        newPlayerTeacherP("toMarket");
        while (run) {
            update();
            setScreen();
            levelShow = getLevelShow();
            showTeaching();
            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 5,
                    "钱包：" + money + "元  Level " + levelShow);
            screen.addWords(Weight - 1, 1, getDayName(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(Weight - 6, 2, getPeriods(), "r", NewUnicodeScreen.LIGHT_BLUE);
            String needprint[] = {
                    "1.购买种子",
                    "2.购买食材",
                    "3.返回仓库",
                    "4.雇佣员工",
                    "5.查看股票",
                    "6.存档并退出游戏"
            };
            for (int i = 0; i < needprint.length; i++) {
                String p = needprint[i];
                int move = 0;
                if (i == nowChoice) {
                    p = "> " + p;
                    move = 1;
                }
                screen.addWords(halfWeight - 4 - move, halfHeight - (2 - i), p, "l");
            }
            int a = 0;
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("W") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("S") && nowChoice < needprint.length - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                a = nowChoice + 1;
            }
            screen.showScreen();

            switch (a) {
                case 1:
                    int willBeChoicedType;
                    willBeChoicedType = getBeGrowedVagetable();
                    if (willBeChoicedType == 0) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }
                    setInput();
                    newPlayerTeacherP("enterVegetableNum");
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight - 2, "已选择 " + vagetablesC[willBeChoicedType - 1] + "种子");
                        screen.addWords(halfWeight, halfHeight - 1, "请输入购买种子的数量");
                        showTeaching();
                        screen.addWords(halfWeight, halfHeight, input.get(0));
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        if (!input.get(0).equals("-") && !input.get(0).equals("")) {
                            if (Integer.valueOf(input.get(0)) > 0) {
                                screen.addWords(
                                        halfWeight, halfHeight + 2, "共需 "
                                                + (int) (Integer.valueOf(input.get(0)) * testVagetable
                                                        .getCost(vagetables[willBeChoicedType - 1]) * getUpPrice())
                                                + " 元");
                            }
                        }
                        screen.showScreen();
                    }
                    if (Integer.valueOf(input.get(0)) < 0) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }
                    int willBeChoicedNumber = Integer.valueOf(input.get(0));
                    if (money >= testVagetable.getCost(vagetables[willBeChoicedType - 1])
                            * willBeChoicedNumber * getUpPrice()) {
                        for (int i = 0; i < willBeChoicedNumber; i++) {
                            money -= (int) testVagetable.getCost(vagetables[willBeChoicedType - 1])
                                    * getUpPrice();
                            Warehouse<Vagetables> warehouse = new Warehouse<>();
                            warehouse.setName(vagetables[willBeChoicedType - 1]);
                            warehouse.setType("SEEDS");
                            addWarehouse(warehouse, 1);
                            letWerehouseListSmall();
                        }
                        showAWordForSomeSeconds("购买成功", 0.5);
                        getAchievement("Expensive");
                        newPlayerTeacherP("buySeedFinish");
                    } else {
                        showAWordForSomeSeconds("资金不足", 0.5);
                    }
                    break;
                case 2:
                    setInput();
                    List<String> asListFood = Arrays.asList(foodsList);
                    List<String> canbuyfood = asListFood.stream()
                            .filter(f -> testFood.getPrice(f) < 1000).toList();
                    while (true) {

                        int maxLines = (int) Math.ceil(canbuyfood.size() / 3.0);

                        int line = 0;
                        int list = 0;
                        for (int i = 0; i < canbuyfood.size(); i++) {
                            screen.addWords(halfWeight - 24 + 16 * list, 7 + line,
                                    "<" + (i + 1) + ">"
                                            + foodsListC[asListFood.indexOf(canbuyfood.get(i))] + "["
                                            + testFood.getIfEatCanGetEnerge(canbuyfood.get(i)) + "]" + "：￥"
                                            + (long) (testFood.getPrice(canbuyfood.get(i)) * getUpPrice()),
                                    "l");
                            line++;
                            if (i != 0 && i % maxLines == 0) {
                                list++;
                                line = 0;
                            }
                        }

                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 5,
                                "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight + 7, "请输入需要购买商品的编号");
                        screen.addWords(halfWeight, halfHeight + 8, input.get(0));
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (!(Integer.valueOf(input.get(0)) < 1
                                    || Integer.valueOf(input.get(0)) > canbuyfood.size())) {
                                screen.addWords(halfWeight, halfHeight + 10, "当前选择 "
                                        + foodsListC[asListFood
                                                .indexOf(canbuyfood.get(Integer.valueOf(input.get(0)) - 1))]
                                        + " ，你可以购买 "
                                        + (int) (money
                                                / ((long) testFood
                                                        .getPrice(canbuyfood.get(Integer.valueOf(input.get(0)) - 1))
                                                        * getUpPrice()))
                                        + " "
                                        + testFood.getUnitName(canbuyfood.get(Integer.valueOf(input.get(0)) - 1))
                                        + foodsListC[asListFood
                                                .indexOf(canbuyfood.get(Integer.valueOf(input.get(0)) - 1))]);
                            }
                        }
                        screen.showScreen();
                    }
                    if (Integer.valueOf(input.get(0)) < 1
                            || Integer.valueOf(input.get(0)) > canbuyfood.size()) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }
                    int willBeChoicedGoods = Integer.valueOf(input.get(0));
                    String choiceFoodString = canbuyfood.get(willBeChoicedGoods - 1);
                    if (money >= (long) testFood.getPrice(choiceFoodString) * getUpPrice()) {
                        setInput();
                        while (true) {
                            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 3,
                                    "钱包：" + money + "元  Level " + levelShow);
                            screen.addWords(halfWeight, halfHeight - 1, "你可以购买 "
                                    + (int) (money / ((long) testFood.getPrice(choiceFoodString)
                                            * getUpPrice()))
                                    + " " + testFood.getUnitName(choiceFoodString)
                                    + foodsListC[asListFood
                                            .indexOf(choiceFoodString)]
                                    + "，请输入要购买的数量");
                            screen.addWords(halfWeight, halfHeight, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        if (Integer.valueOf(input.get(0)) < 1) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        } else if (Integer.valueOf(input.get(
                                0)) > (int) (money / ((long) testFood.getPrice(choiceFoodString)
                                        * getUpPrice()))) {
                            showAWordForSomeSeconds("资金不足", 0.5);
                            break;
                        }
                        int willBeChoicedGoodsNumber = Integer.valueOf(input.get(0));
                        for (int i = 0; i < willBeChoicedGoodsNumber; i++) {
                            Warehouse<Food> w = new Warehouse<>();
                            w.setType("Food");
                            w.setName(choiceFoodString);
                            w.setObject(new Food(choiceFoodString));
                            addWarehouse(w, 1);
                            letWerehouseListSmall();
                            money -= (long) testFood.getPrice(choiceFoodString) * getUpPrice();
                        }
                        showAWordForSomeSeconds("购买成功", 0.5);
                        getAchievement("Expensive");
                        break;
                    } else {
                        showAWordForSomeSeconds("资金不足", 0.5);
                        break;
                    }
                case 3:
                    nextInterface = "Warehouse";
                    run = false;
                    break;
                case 4:
                    int nowChoicedType = 0;
                    while (true) {
                        screen.addWords(halfWeight, halfHeight - 4, "请选择雇佣员工的类型");
                        String needprintB[] = {
                                "1.农场照管者",
                                "2.厨师",
                                "3.种子采购员",
                                "4.动物采购员",
                                "5.食材采购员",
                                "6.作物销售员",
                                "7.动物销售员"
                        };
                        for (int i = 0; i < needprintB.length; i++) {
                            String p = needprintB[i];
                            int move = 0;
                            if (i == nowChoicedType) {
                                p = "> " + p;
                                move = 1;
                            }
                            screen.addWords(halfWeight - 3 - move, halfHeight - (2 - i), p, "l");
                        }
                        String resultKeyB = screen.getFunctionReturn();
                        if (resultKeyB.equals("W") && nowChoicedType > 0) {
                            nowChoicedType--;
                        } else if (resultKeyB.equals("S") && nowChoicedType < needprintB.length - 1) {
                            nowChoicedType++;
                        } else if (resultKeyB.equals("Enter")) {
                            nowChoicedType += 1;
                            break;
                        }
                        screen.showScreen();
                    }
                    switch (nowChoicedType) {
                        case 1:
                            buyEmployee("农场照管员", "Farm");
                            break;
                        case 2:
                            buyEmployee("厨师", "Cooker");
                            break;
                        case 3:
                            buyEmployee("种子采购员", "SeedBuyer");
                            break;
                        case 4:
                            buyEmployee("动物采购员", "AnimalBuyer");
                            break;
                        case 5:
                            buyEmployee("食材采购员", "FoodBuyer");
                            break;
                        case 6:
                            buyEmployee("作物销售员", "VagetableSeller");
                            break;
                        case 7:
                            buyEmployee("动物销售员", "AnimalSeller");
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    int nowS = 0;
                    while (true) {
                        update();
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                "钱包：" + money + "元  Level " + levelShow +
                                        "        当前已购买 " + stockList.size() + " 股股票[" + (nowS + 1) + "/"
                                        + (stockList.size() + 1)
                                        + "]");
                        if (nowS < stockList.size()) {
                            int changes = showStock(stockList.get(nowS).getSeed());
                            screen.addWords(halfWeight - 6, Height - 6, "当前股票：" + stockList.get(nowS).getName(), "l");
                            screen.addWords(halfWeight - 6, Height - 5,
                                    "当前持有量：" + stockList.get(nowS).getHave() + " [B]购入 [S]抛售", "l");
                            screen.addWords(1, halfHeight, "[A]<", "l");
                            screen.addWords(Weight + 1, halfHeight, ">[D]", "r");
                            String color;
                            if (changes > 0) {
                                color = NewUnicodeScreen.GREEN;
                            } else {
                                color = NewUnicodeScreen.RED;
                            }
                            screen.addWords(halfWeight - 6, Height - 4, "最近变化：" + changes * getUpPrice(), "l",
                                    color);
                        } else {
                            screen.addWords(halfWeight, halfHeight, "按 Enter 购入新股票");
                            screen.addWords(1, halfHeight, "[A]<", "l");
                            screen.addWords(Weight + 1, halfHeight, ">[D]", "r");
                        }
                        screen.addWords(Weight + 1, Height - 2,
                                "按下 ESC 退出", "r", NewUnicodeScreen.RED);
                        String resultKeyR = screen.getFunctionReturn();
                        if (resultKeyR.equals("D") && nowS < stockList.size()) {
                            nowS++;
                        } else if (resultKeyR.equals("A") && nowS > 0) {
                            nowS--;
                        } else if (resultKeyR.equals("Escape")) {
                            break;
                        } else if (resultKeyR.equals("B") && nowS < stockList.size()) {
                            if (stockList.get(nowS).getPrice() <= 0) {
                                showAWordForSomeSeconds("当前不可购买", 1);
                                break;
                            }
                            int biggest = (int) (money
                                    / (stockList.get(nowS).getPrice((int) (gameTimeLastUpdate / aDay)) * getUpPrice()));
                            setInput();
                            while (true) {
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                        "钱包：" + money + "元  Level " + levelShow);
                                screen.addWords(halfWeight, halfHeight - 3,
                                        "当前最大可购买 " + biggest + " 股");
                                screen.addWords(halfWeight, halfHeight,
                                        "请输入购买数量：");
                                screen.addWords(halfWeight, halfHeight + 1, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            int num = Integer.valueOf(input.get(0));
                            if (num < 0 || num > biggest) {
                                showAWordForSomeSeconds("输入无效或资金不足", 1);
                            } else {
                                buyStock(num, stockList.get(nowS).getName());
                                showAWordForSomeSeconds("购入成功", 1);
                            }
                        } else if (resultKeyR.equals("S") && nowS < stockList.size()) {
                            setInput();
                            while (true) {
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                        "钱包：" + money + "元  Level " + levelShow);
                                screen.addWords(halfWeight, halfHeight - 3,
                                        "当前共有 " + stockList.get(nowS).getHave() + " 股");
                                screen.addWords(halfWeight, halfHeight - 1,
                                        "请输入抛售数量：");
                                screen.addWords(halfWeight, halfHeight, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            int num = Integer.valueOf(input.get(0));
                            if (num < 0 || num > stockList.get(nowS).getHave()) {
                                showAWordForSomeSeconds("输入无效", 1);
                            } else {
                                sellStock(num, stockList.get(nowS).getName());
                                showAWordForSomeSeconds("抛售成功", 1);
                            }
                        } else if (resultKeyR.equals("Enter") && nowS == stockList.size()) {
                            showAWordForSomeSeconds("正在查询新股票……", 1);
                            Stock newStock = new Stock(vagetables[random.nextInt(vagetables.length - 1)]
                                    + foodsList[random.nextInt(foodsList.length - 1)]
                                    + animals[random.nextInt(animals.length - 1)], 0, 0, random.nextInt());
                            while (true) {
                                update();
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 4,
                                        "钱包：" + money + "元  Level " + levelShow);
                                newStock.price = newStock.getPrice((int) (gameTimeLastUpdate / aDay));
                                int changes = showStock(newStock.getSeed());
                                screen.addWords(halfWeight - 6, Height - 6, "当前股票：" + newStock.getName(),
                                        "l");
                                String color;
                                if (changes > 0) {
                                    color = NewUnicodeScreen.GREEN;
                                } else {
                                    color = NewUnicodeScreen.RED;
                                }
                                screen.addWords(halfWeight - 6, Height - 5, "最近变化：" + changes * getUpPrice(), "l",
                                        color);
                                screen.addWords(halfWeight, Height - 4, "[C]更换 [B]购入");
                                screen.addWords(Weight + 1, Height - 2,
                                        "按下 ESC 退出", "r", NewUnicodeScreen.RED);
                                screen.showScreen();
                                resultKeyR = screen.getFunctionReturn();
                                if (resultKeyR.equals("Escape")) {
                                    break;
                                } else if (resultKeyR.equals("C")) {
                                    showAWordForSomeSeconds("正在查询新股票……", 1);
                                    newStock.name = vagetables[random.nextInt(vagetables.length - 1)]
                                            + foodsList[random.nextInt(foodsList.length - 1)]
                                            + animals[random.nextInt(animals.length - 1)];
                                    newStock.seed = random.nextInt();
                                } else if (resultKeyR.equals("B")) {
                                    if (newStock.getPrice() <= 0) {
                                        showAWordForSomeSeconds("当前不可购买", 1);
                                        break;
                                    }
                                    int biggest = (int) (money
                                            / (newStock.getPrice((int) (gameTimeLastUpdate / aDay)) * getUpPrice()));
                                    setInput();
                                    while (true) {
                                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                                "钱包：" + money + "元  Level " + levelShow);
                                        screen.addWords(halfWeight, halfHeight - 3,
                                                "当前最大可购买 " + biggest + " 股");
                                        screen.addWords(halfWeight, halfHeight - 1,
                                                "请输入购买数量：");
                                        screen.addWords(halfWeight, halfHeight, input.get(0));
                                        input = getEnterNumber(input.get(0));
                                        if (input.get(1).equals("T")) {
                                            break;
                                        }
                                        screen.showScreen();
                                    }
                                    int num = Integer.valueOf(input.get(0));
                                    if (num < 0 || num > biggest) {
                                        showAWordForSomeSeconds("输入无效或资金不足", 1);
                                    } else {
                                        buyStock(num, newStock.getName(), newStock.getPrice(), newStock.getSeed());
                                        showAWordForSomeSeconds("购入成功", 1);
                                        break;
                                    }
                                }
                            }
                        }
                        screen.showScreen();
                    }
                    break;
                case 6:
                    nextInterface = "";
                    run = false;
                    break;
                default:
                    break;
            }

            if (System.currentTimeMillis() - Lastsave >= 20000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static int showStock(int seed) {
        Stock s = new Stock("", 0, 0, seed);
        int middle = s.getPrice((int) (gameTimeLastUpdate / aDay));
        List<Integer> changes = new ArrayList<>();
        screen.addWords(Weight - 7, halfHeight - 1, "--- " + middle, "l");
        for (int i = 0; i < Weight; i++) {
            int now = s.getPrice((int) (gameTimeLastUpdate / aDay) - i);
            int last = s.getPrice((int) (gameTimeLastUpdate / aDay) - i - 1);
            changes.add(now - last);
        }

        int y = halfHeight;
        for (int i = Weight - 9; i > 3; i--) {
            String color;
            int up;
            if (changes.get(Weight - 9 - i) > 0) {
                color = NewUnicodeScreen.GREEN;
                up = 1;
            } else {
                color = NewUnicodeScreen.RED;
                up = -1;
            }
            for (int j = 0; j < Math.abs(changes.get(Weight - 6 - i)) / 3000 + 1; j++) {
                if (Math.abs(changes.get(Weight - 6 - i)) / 3000 == 0) {
                    up = 0;
                }
                y += up;
                if (y > 4 && y < Height - 7) {
                    screen.addWords(color, i, y, "▇▇");
                }
            }
        }
        return changes.get(0);
    }

    static void buyStock(int num, String stock) {
        buyStock(num, stock, 0, 0);
    }

    static void buyStock(int num, String stock, long price, int seed) {
        if (stockList.stream().anyMatch(s -> s.getName().equals(stock))) {
            stockList.stream().filter(s -> s.getName().equals(stock)).findFirst().get().buy(num);
            money -= (long) (num
                    * stockList.stream().filter(s -> s.getName().equals(stock)).findFirst().get().getPrice()
                    * getUpPrice());
        } else {
            Stock st = new Stock(stock, price, num, seed);
            stockList.add(st);
            money -= (long) (num * price * getUpPrice());
        }
    }

    static void sellStock(int num, String stock) {
        money += stockList.stream().filter(s -> s.getName().equals(stock)).findFirst().get().sell(num) * getUpPrice();
        if (stockList.stream().filter(s -> s.getName().equals(stock)).findFirst().get().getHave() == 0) {
            stockList.remove(stockList.stream().filter(s -> s.getName().equals(stock)).findFirst().get());
        }
    }

    static void buyEmployee(String need, String type) {
        long price = (long) (100000 * getUpPrice());
        if (money >= price) {
            setInput();
            while (true) {
                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 5,
                        "钱包：" + money + "元  Level " + levelShow);
                screen.addWords(halfWeight, halfHeight - 3, "当前" + need + "雇佣价格：￥" + price + "/人");
                screen.addWords(halfWeight, halfHeight - 1,
                        "当前可以雇佣 " + (long) (money / price) + " 个" + need + "，请输入雇佣" + need + "的数量");
                screen.addWords(halfWeight, halfHeight, input.get(0));
                input = getEnterNumber(input.get(0));
                if (input.get(1).equals("T")) {
                    break;
                }
                screen.showScreen();
            }
            if (Integer.valueOf(input.get(0)) < 1) {
                showAWordForSomeSeconds("输入错误", 0.5);
                return;
            } else if (Integer.valueOf(input.get(0)) > (int) (money / price)) {
                showAWordForSomeSeconds("资金不足", 0.5);
                return;
            }

            int willBeChoicedEmployee = Integer.valueOf(input.get(0));
            int auto = 1;
            String name = "";
            for (int i = 0; i < willBeChoicedEmployee; i++) {
                if (auto != 0) {
                    setInput();
                    boolean finall = false;
                    String wName = "";
                    wName = getEnterString("当请为第 " + (i + 1) + " 个" + need + "命名[输入 AUTO 自动命名]", "员工命名");
                    while (true) {
                        screen.addWords(halfWeight, halfHeight - 2,
                                "当请为第 " + (i + 1) + " 个" + need + "命名[输入 AUTO 自动命名]");
                        screen.addWords(halfWeight, halfHeight, wName);
                        String ent = screen.getFunctionReturn();
                        screen.addWords(halfWeight - 6, halfHeight + 2, "确定", "l");
                        screen.addWords(halfWeight + 4, halfHeight + 2, "重命名", "l");
                        if (ent.equals("A")) {
                            finall = true;
                        } else if (ent.equals("D")) {
                            finall = false;
                        } else if (ent.equals("Enter")) {
                            if (finall) {
                                break;
                            } else {
                                wName = getEnterString("当请为第 " + (i + 1) + " 个" + need + "命名[输入 AUTO 自动命名]", "员工命名");
                            }
                        }
                        if (finall) {
                            screen.addWords(halfWeight - 7, halfHeight + 2, "> ", "l");
                        } else {
                            screen.addWords(halfWeight + 3, halfHeight + 2, "> ", "l");
                        }
                        screen.showScreen();
                    }
                    name = wName;
                    if (name.equals("AUTO")) {
                        auto = 0;
                    }
                }
                if (auto == 0) {
                    if (type.equals("Farm")) {
                        name = type + " " + (farmerList.size() + 1);
                    } else if (type.equals("Cooker")) {
                        name = type + " " + (cookerList.size() + 1);
                    } else if (type.equals("SeedBuyer") || type.equals("AnimalBuyer") || type.equals("VagetableSeller")
                            || type.equals("AnimalSeller") || type.equals("FoodBuyer")) {
                        name = type + " " + (buyerList.size() + 1);
                    }
                }
                if (type.equals("Farm")) {
                    Employee employee = new Employee(name);
                    farmerList.add(employee);
                    money -= price;
                    showAWordForSomeSeconds("雇佣成功", 0.5);
                    getAchievement("Help");
                } else {
                    String words = "";
                    if (type.equals("Cooker")) {
                        words = "请选择厨师 " + name + " 制作的食物";
                    } else if (type.equals("SeedBuyer")) {
                        words = "请选择种子采购员 " + name + " 采购的种子";
                    } else if (type.equals("AnimalBuyer")) {
                        words = "请选择动物采购员 " + name + " 采购的动物";
                    } else if (type.equals("VagetableSeller")) {
                        words = "请选择作物销售员 " + name + " 售卖的作物";
                    } else if (type.equals("AnimalSeller")) {
                        words = "请选择动物销售员 " + name + " 售卖的动物";
                    }
                    setInput();
                    while (true) {
                        printWarehouse(true);
                        screen.addWords(halfWeight, halfHeight + 5, words);
                        screen.addWords(halfWeight, halfHeight + 6, input.get(0));
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        screen.showScreen();
                    }
                    String which = input.get(0);
                    if (type.equals("VagetableSeller") || type.equals("AnimalSeller")) {
                        setInput();
                        while (true) {
                            screen.addWords(halfWeight, halfHeight - 1, "请输入售卖剩余上限");
                            screen.addWords(halfWeight, halfHeight, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        String last = input.get(0);
                        if (!which.equals("-") && !which.equals("") && !last.equals("-") && !last.equals("")) {
                            List<Warehouse<? extends Object>> haveList = new ArrayList<>();
                            String t = "";
                            if (type.equals("VagetableSeller")) {
                                haveList = warehouseList.stream().filter(w -> w.getType().equals("VAGETABLES"))
                                        .toList();
                                t = "VAGETABLES";
                            } else if (type.equals("AnimalSeller")) {
                                haveList = warehouseList.stream().filter(w -> w.getType().equals("Animal"))
                                        .toList();
                                t = "Animal";
                            }
                            Seller seller = new Seller(name, 0, t, haveList.get(Integer.valueOf(which) - 1).getName(),
                                    Integer.valueOf(last));
                            sellerList.add(seller);
                            money -= price;
                            showAWordForSomeSeconds("雇佣成功", 0.5);
                            getAchievement("Help");
                        }
                    } else {
                        if (type.equals("Cooker")) {
                            if (!which.equals("-") && !which.equals("")) {
                                List<Warehouse<? extends Object>> foodListHave = warehouseList.stream()
                                        .filter(w -> w.type.equals("Food")).toList();
                                if (Integer.valueOf(which) > 0
                                        && Integer.valueOf(which) <= foodListHave.size()) {
                                    if (!testFood
                                            .getFormulation(foodListHave.get(Integer.valueOf(which) - 1).getName())
                                            .isEmpty()) {
                                        Cooker cooker = new Cooker(name, 0,
                                                foodListHave.get(Integer.valueOf(which) - 1).getName());
                                        cookerList.add(cooker);
                                        money -= price;
                                        showAWordForSomeSeconds("雇佣成功", 0.5);
                                        getAchievement("Help");
                                    } else {
                                        showAWordForSomeSeconds("此食物无法制作", 0.5);
                                    }
                                } else {
                                    showAWordForSomeSeconds("输入无效", 0.5);
                                }
                            } else {
                                showAWordForSomeSeconds("输入无效", 0.5);
                            }
                        } else {
                            setInput();
                            while (true) {
                                screen.addWords(halfWeight, halfHeight - 1, "请输入采购上限");
                                screen.addWords(halfWeight, halfHeight, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            String last = input.get(0);
                            if (!which.equals("-") && !which.equals("") && !last.equals("-") && !last.equals("")) {
                                List<Warehouse<? extends Object>> haveList = new ArrayList<>();
                                String t = "";
                                if (Integer.valueOf(last) > 0) {
                                    if (type.equals("SeedBuyer")) {
                                        haveList = warehouseList.stream().filter(w -> w.type.equals("SEEDS")).toList();
                                        t = "SEEDS";
                                    } else if (type.equals("AnimalBuyer")) {
                                        haveList = warehouseList.stream().filter(w -> w.type.equals("Animal")).toList();
                                        t = "Animal";
                                    } else if (type.equals("FoodBuyer")) {
                                        haveList = warehouseList.stream().filter(w -> w.type.equals("Food")).toList();
                                        t = "Food";
                                    }
                                    if (Integer.valueOf(which) > 0
                                            && Integer.valueOf(which) <= haveList.size()) {
                                        Buyer buyer = new Buyer(name, 0, t,
                                                haveList.get(Integer.valueOf(which) - 1).getName(),
                                                Integer.valueOf(last));
                                        buyerList.add(buyer);
                                        money -= price;
                                        showAWordForSomeSeconds("雇佣成功", 0.5);
                                        getAchievement("Help");
                                    } else {
                                        showAWordForSomeSeconds("输入无效", 0.5);
                                    }
                                } else {
                                    showAWordForSomeSeconds("输入无效", 0.5);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            showAWordForSomeSeconds("资金不足", 0.5);
        }
    }

    static String theWarehouse() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        int nowChoice = 0;
        newPlayerTeacherP("toWarehouse");
        while (run) {
            update();
            setScreen();
            levelShow = getLevelShow();
            showTeaching();
            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3, "钱包：" + money + "元  Level " + levelShow);
            screen.addWords(Weight - 1, 1, getDayName(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(Weight - 6, 2, getPeriods(), "r", NewUnicodeScreen.LIGHT_BLUE);
            printWarehouse(false);
            String needprint[] = {
                    "1.进入市场",
                    "2.售出作物",
                    "3.售出动物",
                    "4.进入农场",
                    "5.进入牧场",
                    "6.进入厨房",
                    "7.进入世界",
                    "8.存档并退出游戏",
                    "9.重置存档"
            };
            for (int i = 0; i < needprint.length; i++) {
                String p = needprint[i];
                int move = 0;
                if (i == nowChoice) {
                    p = "> " + p;
                    move = 1;
                }
                screen.addWords(halfWeight - 3 - move, halfHeight + (3 + i), p, "l");
            }
            int a = 0;
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("W") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("S") && nowChoice < needprint.length - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                a = nowChoice + 1;
            }
            screen.showScreen();

            switch (a) {
                case 1:
                    nextInterface = "Market";
                    run = false;
                    break;
                case 2:
                    int canBeChoicedNumber = warehouseList.stream()
                            .filter(warehouseList -> warehouseList.getType().equals("VAGETABLES"))
                            .map(Warehouse::getName).distinct().toList().size();
                    if (canBeChoicedNumber != 0) {
                        setInput();
                        newPlayerTeacherP("enterSellVegType");
                        while (true) {
                            showTeaching();
                            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                    "钱包：" + money + "元  Level " + levelShow);
                            printWarehouse(true);
                            screen.addWords(halfWeight, halfHeight + 6, "请选择作物[输入 0 为全部售出]");
                            screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        if (Integer.valueOf(input.get(0)) < 0
                                || Integer.valueOf(input.get(0)) > canBeChoicedNumber) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        }
                        int type = Integer.valueOf(input.get(0));
                        if (type != 0) {
                            String beChoicedTypeName = warehouseList.stream()
                                    .filter(carehouse -> carehouse.getType().equals("VAGETABLES"))
                                    .map(Warehouse::getName).distinct()
                                    .toList().get(type - 1);
                            setInput();
                            newPlayerTeacherP("enterSellVegNum");
                            while (true) {
                                showTeaching();
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                        "钱包：" + money + "元  Level " + levelShow);
                                printWarehouse(true);
                                screen.addWords(halfWeight, halfHeight + 6, "请输入售卖数量[输入 0 为全部售出]");
                                screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            if (Integer.valueOf(input.get(0)) < 0
                                    || Integer.valueOf(input.get(0)) > getWarehouseNumber(warehouseList.stream()
                                            .filter(warehouseList -> warehouseList.getType().equals("VAGETABLES")
                                                    && warehouseList.getName().equals(beChoicedTypeName))
                                            .toList().get(0))) {
                                showAWordForSomeSeconds("输入无效", 0.5);
                                break;
                            }
                            int sellNumber = Integer.valueOf(input.get(0));
                            int getMoney = 0;
                            List<Warehouse<? extends Object>> beChoicedWarehouse = warehouseList.stream()
                                    .filter(warehouseList -> warehouseList.getType().equals("VAGETABLES")
                                            && warehouseList.getName().equals(beChoicedTypeName))
                                    .toList();
                            if (sellNumber == 0) {
                                sellNumber = getWarehouseNumber(beChoicedWarehouse.get(0));
                            }
                            for (int i = 0; i < sellNumber; i++) {
                                if (sellNumber > 0) {
                                    getMoney += testVagetable.getMoney(beChoicedWarehouse.get(0).getName())
                                            * random.nextDouble(0.7, 1.3) * getUpPrice();
                                    int index = warehouseList.indexOf(beChoicedWarehouse.get(0));
                                    removeWarehouse(index, 1);
                                } else {
                                    break;
                                }
                            }
                            money += getMoney;
                            showAWordForSomeSeconds("售出成功，共获得 " + getMoney + " 元", 0.7);
                            newPlayerTeacherP("getMoney");
                            getAchievement("Money");
                        } else {
                            int getMoney = 0;
                            newPlayerTeacherP("enterSellVegNum");
                            for (Warehouse<? extends Object> warehouse : warehouseList.stream()
                                    .filter(warehouseList -> warehouseList.getType().equals("VAGETABLES"))
                                    .toList()) {
                                int times = getWarehouseNumber(warehouse);
                                for (int i = 0; i < times; i++) {
                                    getMoney += testVagetable.getMoney(warehouse.getName())
                                            * random.nextDouble(0.7, 1.3) * getUpPrice();
                                    removeWarehouse(warehouseList.indexOf(warehouse), 1);
                                }
                            }
                            money += getMoney;
                            showAWordForSomeSeconds("售出成功，共获得 " + getMoney + " 元", 0.7);
                            newPlayerTeacherP("getMoney");
                            getAchievement("Money");
                        }
                    } else {
                        showAWordForSomeSeconds("没有可以售出的蔬菜", 0.7);
                    }
                    break;
                case 3:
                    int canBeChoicedNumberA = warehouseList.stream()
                            .filter(warehouseList -> warehouseList.getType().equals("Animal"))
                            .map(Warehouse::getName).distinct().toList().size();
                    if (canBeChoicedNumberA != 0) {
                        setInput();
                        while (true) {
                            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                    "钱包：" + money + "元  Level " + levelShow);
                            printWarehouse(true);
                            screen.addWords(halfWeight, halfHeight + 6, "请选择动物[输入 0 为全部售出]");
                            screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        if (Integer.valueOf(input.get(0)) < 0
                                || Integer.valueOf(input.get(0)) > canBeChoicedNumberA) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        }
                        int type = Integer.valueOf(input.get(0));
                        if (type != 0) {
                            String beChoicedTypeName = warehouseList.stream()
                                    .filter(carehouse -> carehouse.getType().equals("Animal"))
                                    .map(Warehouse::getName).distinct()
                                    .toList().get(type - 1);
                            setInput();
                            while (true) {
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                        "钱包：" + money + "元  Level " + levelShow);
                                printWarehouse(true);
                                screen.addWords(halfWeight, halfHeight + 6, "请输入售卖数量[输入 0 为全部售出]");
                                screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            if (Integer.valueOf(input.get(0)) < 0
                                    || Integer.valueOf(input.get(0)) > getWarehouseNumber(warehouseList.stream()
                                            .filter(warehouseList -> warehouseList.getType().equals("Animal")
                                                    && warehouseList.getName().equals(beChoicedTypeName))
                                            .toList().get(0))) {
                                showAWordForSomeSeconds("输入无效", 0.5);
                                break;
                            }
                            int getMoney = 0;
                            Warehouse<? extends Object> warehouseAni = warehouseList.stream()
                                    .filter(warehouseList -> warehouseList.getType().equals("Animal")
                                            && warehouseList.getName().equals(beChoicedTypeName))
                                    .toList().get(0);
                            int sellNumber = Integer.valueOf(input.get(0));
                            if (sellNumber == 0) {
                                sellNumber = getWarehouseNumber(warehouseAni);
                            }
                            for (int i = 0; i < sellNumber; i++) {
                                if (sellNumber > 0) {
                                    getMoney += testAnimal.getMoney(warehouseAni.getName())
                                            * random.nextDouble(0.7, 1.3) * getUpPrice();
                                    int index = warehouseList.indexOf(warehouseAni);
                                    removeWarehouse(index, 1);
                                } else {
                                    break;
                                }
                            }
                            money += getMoney;
                            showAWordForSomeSeconds("售出成功，共获得 " + getMoney + " 元", 0.7);
                        } else {
                            int getMoney = 0;
                            for (Warehouse<? extends Object> warehouse : warehouseList.stream()
                                    .filter(warehouseList -> warehouseList.getType().equals("Animal"))
                                    .toList()) {
                                int times = getWarehouseNumber(warehouse);
                                for (int i = 0; i < times; i++) {
                                    getMoney += testAnimal.getMoney(warehouse.getName())
                                            * random.nextDouble(0.7, 1.3) * getUpPrice();
                                    int index = warehouseList.indexOf(warehouse);
                                    removeWarehouse(index, 1);
                                }
                            }
                            money += getMoney;
                            showAWordForSomeSeconds("售出成功，共获得 " + getMoney + " 元", 0.7);
                        }
                    } else {
                        showAWordForSomeSeconds("没有可以售出的动物", 0.7);
                    }
                    break;

                case 4:
                    nextInterface = "Farm";
                    run = false;
                    break;
                case 5:
                    nextInterface = "Pasture";
                    run = false;
                    break;
                case 6:
                    nextInterface = "Kitchen";
                    run = false;
                    break;
                case 7:
                    nextInterface = "World";
                    run = false;
                    break;
                case 8:
                    nextInterface = "";
                    run = false;
                    break;
                case 9:
                    rebuild();
                    break;
                default:
                    break;
            }
            if (System.currentTimeMillis() - Lastsave >= 20000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static String theFarm() {
        boolean run = true;
        long Lastsave = System.currentTimeMillis();
        String nextInterface = "";
        int nowChoice = 0;
        newPlayerTeacherP("toFarm");
        while (run) {
            update();
            setScreen();
            levelShow = getLevelShow();
            showTeaching();
            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3, "钱包：" + money + "元  Level " + levelShow);
            screen.addWords(Weight - 1, 1, getDayName(), "r", NewUnicodeScreen.LIGHT_BLUE);
            screen.addWords(Weight - 6, 2, getPeriods(), "r", NewUnicodeScreen.LIGHT_BLUE);
            printFarm(false);
            String needprint[] = {
                    "1.购买/卖出农田(￥" + getSoilPrice() + "/块)",
                    "2.升级农田(￥" + getSoilUpdatePrice() + "/块)",
                    "3.种植作物",
                    "4.收获作物",
                    "5.随机购买作物(￥" + (long) Math.floor(300 * levelShow) + "/个)",
                    "6.查看作物市场价格",
                    "7.进入仓库",
                    "8.进入牧场",
                    "9.进入世界",
                    "10.设置",
                    "11.存档并退出农场",
                    "12.重置存档"
            };
            for (int i = 0; i < needprint.length; i++) {
                String p = needprint[i];
                int move = 0;
                if (i == nowChoice) {
                    p = "> " + p;
                    move = 1;
                }
                screen.addWords(halfWeight - 5 - move, Height - (15 - i), p, "l");
            }
            int a = 0;
            String resultKey = screen.getFunctionReturn();
            if (resultKey.equals("W") && nowChoice > 0) {
                nowChoice--;
            } else if (resultKey.equals("S") && nowChoice < needprint.length - 1) {
                nowChoice++;
            } else if (resultKey.equals("Enter")) {
                a = nowChoice + 1;
            }
            screen.showScreen();

            switch (a) {
                case 1:
                    setInput();
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight - 2, "请输入需要购买的土地的数量(输入负数为卖出土地)");
                        screen.addWords(halfWeight, halfHeight - 1, input.get(0));
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (Integer.valueOf(input.get(0)) > 0) {
                                screen.addWords(halfWeight, halfHeight + 1,
                                        "共需 " + (long) getSoilPrice() * Integer.valueOf(input.get(0)) + " 元");
                            } else if (Integer.valueOf(input.get(0)) != 0) {
                                screen.addWords(halfWeight, halfHeight + 1,
                                        "将得到 " + (long) getSoilPrice() * Integer.valueOf(input.get(0)) * -1
                                                + " 元");

                            }
                        }
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        screen.showScreen();
                    }
                    int buy = Integer.valueOf(input.get(0));
                    int needBuy = getSoilPrice() * buy;
                    if (buy > 0) {
                        if (money >= needBuy) {
                            List<World.Posision> p = findSuitableList(buy, "", world.playerPosision);
                            for (int k = 0; k < buy; k++) {
                                vagetablesList.add(new Vagetables("NONE"));
                                soilList.add(new Soil(soil[random.nextInt(2)], p.get(k)));
                            }
                            money -= needBuy;
                            showAWordForSomeSeconds("购买成功", 0.5);
                        } else {
                            showAWordForSomeSeconds("资金不足", 0.5);
                        }
                    } else {
                        if (buy * (-1) > soilList.size()) {
                            showAWordForSomeSeconds("土地不足", 0.5);
                        } else if (buy == 0) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                        } else {
                            int get = 0;
                            int k = 0, top;
                            if (soilList.stream()
                                    .filter(soil -> vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE"))
                                    .toList().size() >= buy
                                            * (-1)) {
                                top = buy * (-1);
                            } else {
                                top = soilList.stream()
                                        .filter(soil -> vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE"))
                                        .toList().size();
                                int notNoneTop = buy * (-1) - top;
                                for (Soil s : soilList.stream()
                                        .filter(soil -> !vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE"))
                                        .toList()) {
                                    k++;
                                    get += s.getGrow() * 700
                                            + testVagetable.getCost(vagetablesList.get(soilList.indexOf(s)).id)
                                            + 300;
                                    vagetablesList.remove(soilList.indexOf(s));
                                    soilList.remove(soilList.indexOf(s));
                                    if (k >= notNoneTop) {
                                        break;
                                    }
                                }
                                k = 0;
                            }
                            for (Soil s : soilList.stream()
                                    .filter(soil -> vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE"))
                                    .toList()) {
                                k++;
                                get += s.getGrow() * 700 + 300;
                                vagetablesList.remove(soilList.indexOf(s));
                                soilList.remove(soilList.indexOf(s));
                                if (k >= top) {
                                    break;
                                }
                            }
                            money += get;
                            showAWordForSomeSeconds("卖出成功，共赚 " + get + " 元", 0.5);
                        }
                    }
                    break;
                case 2:
                    int pay = getSoilUpdatePrice();
                    if (money >= pay) {
                        setInput();
                        while (true) {
                            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                    "钱包：" + money + "元  Level " + levelShow);
                            printFarm(true);
                            screen.addWords(halfWeight, halfHeight, "请选择需要升级的农田(输入 0 自动升级农田)");
                            screen.addWords(halfWeight, halfHeight + 1, input.get(0));
                            if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                                if (Integer.valueOf(input.get(0)) > 0) {
                                    screen.addWords(halfWeight, halfHeight + 3,
                                            "共需 " + pay + " 元");
                                }
                            }
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        if (Integer.valueOf(input.get(0)) < 0
                                || Integer.valueOf(input.get(0)) > soilList.size()) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        }
                        int upsoil = Integer.valueOf(input.get(0)) - 1;
                        if (upsoil >= 0) {
                            if (soilList.get(upsoil).id.equals("BLACK")) {
                                showAWordForSomeSeconds("该地块已达到最高级，请选择其他地块", 0.5);
                                break;
                            } else {
                                int n = Arrays.asList(soil).indexOf(soilList.get(upsoil).id);
                                soilList.get(upsoil).change(soil[n + 1]);
                            }
                            showAWordForSomeSeconds("升级成功", 0.5);
                            money -= pay;
                        } else if (upsoil == -1) {
                            boolean allBlackSoil = soilList.stream().allMatch(soil -> soil.id.equals("BLACK"));
                            if (allBlackSoil) {
                                showAWordForSomeSeconds("所有农田已达到最高等级，无需升级", 0.5);
                            } else {
                                int mode = 0;
                                while (true) {
                                    screen.addWords(halfWeight, halfHeight - 4, "请选择升级模式：");
                                    String f[] = { "1.优先质量", "2.优先数量" };
                                    for (int i = 0; i < 2; i++) {
                                        String s = f[i];
                                        int move = 0;
                                        if (mode == i) {
                                            s = "> " + s;
                                            move -= 1;
                                        }
                                        screen.addWords(halfWeight - 3 + move, halfHeight - 3 + i, s, "l");
                                    }
                                    String res = screen.getFunctionReturn();
                                    if (res.equals("Enter")) {
                                        break;
                                    } else if (res.equals("W") && mode > 0) {
                                        mode--;
                                    } else if (res.equals("S") && mode < 1) {
                                        mode++;
                                    }
                                    screen.showScreen();
                                }
                                int up = mode + 1;
                                int us = 0;
                                for (Soil s : soilList.stream().filter(soil -> soil.id != "BLACK").toList()) {
                                    if (up == 1) {
                                        while (money >= pay && !s.id.equals("BLACK")) {
                                            s.change(soil[Arrays.asList(soil).indexOf(s.id) + 1]);
                                            us++;
                                            money -= pay;
                                        }
                                    } else if (up == 2) {
                                        if (money >= pay) {
                                            s.change(soil[Arrays.asList(soil).indexOf(s.id) + 1]);
                                            us++;
                                            money -= pay;
                                        }
                                    }
                                }
                                if (up == 1) {
                                    showAWordForSomeSeconds("升级完成，共升级" + us + "次农田", 0.5);
                                } else {
                                    showAWordForSomeSeconds("升级完成，共升级" + us + "块农田", 0.5);
                                }
                            }
                        } else {
                            showAWordForSomeSeconds("输入无效", 0.5);
                        }
                        break;
                    } else {
                        showAWordForSomeSeconds("资金不足", 0.5);
                        break;
                    }
                case 3:
                    if (warehouseList.stream().filter(warehouse -> warehouse.getType().equals("SEEDS")).toList()
                            .size() != 0) {
                        int s, v;
                        setInput();
                        newPlayerTeacherP("chooseGrowSoil");
                        while (true) {
                            showTeaching();
                            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                    "钱包：" + money + "元  Level " + levelShow);
                            printFarm(true);
                            screen.addWords(halfWeight, halfHeight + 6, "请选择农田(输入 0 进行自动种植)");
                            screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                            input = getEnterNumber(input.get(0));
                            if (input.get(1).equals("T")) {
                                break;
                            }
                            screen.showScreen();
                        }
                        if (Integer.valueOf(input.get(0)) < 0
                                || Integer.valueOf(input.get(0)) > soilList.size()) {
                            showAWordForSomeSeconds("输入无效", 0.5);
                            break;
                        }
                        s = Integer.valueOf(input.get(0)) - 1;
                        if (s >= 0) {
                            if (vagetablesList.get(s).getId().equals("NONE") && s >= 0) {
                                setInput();
                                newPlayerTeacherP("chooseGrowSeed");
                                while (true) {
                                    showTeaching();
                                    printWarehouse(true);
                                    screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                            "钱包：" + money + "元  Level " + levelShow);
                                    screen.addWords(halfWeight, halfHeight + 6, "请选择种子");
                                    screen.addWords(halfWeight, halfHeight + 7, input.get(0));
                                    input = getEnterNumber(input.get(0));
                                    if (input.get(1).equals("T")) {
                                        break;
                                    }
                                    screen.showScreen();
                                }
                                if (Integer.valueOf(input.get(0)) <= 0
                                        || Integer.valueOf(input.get(0)) > warehouseList.stream()
                                                .filter(warehouse -> warehouse.getType().equals("SEEDS"))
                                                .map(Warehouse::getName).distinct().toList().size()) {
                                    showAWordForSomeSeconds("输入无效", 0.5);
                                    break;
                                }
                                v = Arrays.asList(vagetables).indexOf(warehouseList.stream()
                                        .filter(warehouse -> warehouse.getType().equals("SEEDS"))
                                        .map(Warehouse::getName).distinct().toList()
                                        .get(Integer.valueOf(input.get(0)) - 1));
                                boolean b = growin(vagetables[v], s);
                                int index = warehouseList.indexOf(warehouseList.stream()
                                        .filter(warehouse -> warehouse.getType().equals("SEEDS")
                                                && warehouse.getName().equals(vagetables[v]))
                                        .findFirst().get());
                                removeWarehouse(index, 1);
                                if (b) {
                                    showAWordForSomeSeconds("种植完成", 0.5);
                                }
                                getAchievement("Seed");
                                newPlayerTeacherP("finishGrow");
                                break;
                            } else {
                                showAWordForSomeSeconds("农田已有作物", 0.5);
                            }
                        } else if (s == -1) {
                            setInput();
                            newPlayerTeacherP("chooseGrowSeed");
                            while (true) {
                                showTeaching();
                                printWarehouse(true);
                                screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3,
                                        "钱包：" + money + "元  Level " + levelShow);
                                screen.addWords(halfWeight, halfHeight, "请选择种子");
                                screen.addWords(halfWeight, halfHeight + 1, input.get(0));
                                input = getEnterNumber(input.get(0));
                                if (input.get(1).equals("T")) {
                                    break;
                                }
                                screen.showScreen();
                            }
                            if (Integer.valueOf(input.get(0)) <= 0
                                    || Integer.valueOf(input.get(0)) > warehouseList.stream()
                                            .filter(warehouse -> warehouse.getType().equals("SEEDS"))
                                            .map(Warehouse::getName).distinct().toList().size()) {
                                showAWordForSomeSeconds("输入无效", 0.5);
                                break;
                            }
                            v = Arrays.asList(vagetables).indexOf(warehouseList.stream()
                                    .filter(warehouse -> warehouse.getType().equals("SEEDS"))
                                    .map(Warehouse::getName).distinct().toList()
                                    .get(Integer.valueOf(input.get(0)) - 1));
                            int needPay = 0;
                            needPay = (int) (testVagetable.getCost(vagetables[v]) * getUpPrice());
                            if (needPay != 0) {
                                boolean b = haveNoneSoil();
                                if (!b) {
                                    showAWordForSomeSeconds("所有农田均已种植", 0.5);
                                } else {
                                    int gf = 0;
                                    for (Vagetables vag : vagetablesList.stream()
                                            .filter(vagetables -> vagetables.getId().equals("NONE")).toList()) {
                                        if (warehouseList.stream()
                                                .anyMatch(warehouse -> warehouse.getType().equals("SEEDS")
                                                        && warehouse.getName().equals(vagetables[v]))) {
                                            growin(vagetables[v], vag);
                                            gf++;
                                            int index = warehouseList.indexOf(warehouseList.stream()
                                                    .filter(warehouse -> warehouse.getType().equals("SEEDS")
                                                            && warehouse.getName().equals(vagetables[v]))
                                                    .findFirst().get());
                                            removeWarehouse(index, 1);
                                        }
                                    }
                                    showAWordForSomeSeconds("种植完成，共种植" + gf + "块农田的作物", 0.5);
                                }
                            }
                            getAchievement("Seed");
                            newPlayerTeacherP("finishGrow");
                        } else {
                            showAWordForSomeSeconds("农田已有作物", 0.5);
                        }
                    } else {
                        showAWordForSomeSeconds("仓库中无作物种子", 0.5);
                    }
                    break;
                case 4:
                    int ff = 0;
                    for (Vagetables va : vagetablesList) {
                        if (va.grow == va.top && va.top != 0) {
                            ff++;
                        }
                    }
                    if (ff > 0) {
                        int[] gv = harvestVagetables();
                        while (true) {
                            showTeaching();
                            int line = 0;
                            screen.addWords(halfWeight - 9, halfHeight - 5 + line, "收获完成，共有", "l",
                                    NewUnicodeScreen.LIGHT_BLUE);
                            if (gv[0] != 0) {
                                screen.addWords(halfWeight - 2, halfHeight - 5 + line, " " + gv[0] + " 块农田损失约90%", "l",
                                        NewUnicodeScreen.LIGHT_RED);
                                line++;
                            }
                            if (gv[1] != 0) {
                                screen.addWords(halfWeight - 2, halfHeight - 5 + line, " " + gv[1] + " 块农田损失约30%", "l",
                                        NewUnicodeScreen.LIGHT_RED);
                                line++;
                            }
                            if (gv[2] != 0) {
                                screen.addWords(halfWeight - 2, halfHeight - 5 + line, " " + gv[2] + " 块农田收获基本正常", "l",
                                        NewUnicodeScreen.LIGHT_GREEN);
                                line++;
                            }
                            if (gv[3] != 0) {
                                screen.addWords(halfWeight - 2, halfHeight - 5 + line, " " + gv[3] + " 块农田增收约30%", "l",
                                        NewUnicodeScreen.LIGHT_GREEN);
                                line++;
                            }
                            screen.addWords(halfWeight - 9, halfHeight - 5 + line,
                                    "共收获" + (gv[0] + gv[1] + gv[2] + gv[3]) + "块农田的作物", "l",
                                    NewUnicodeScreen.LIGHT_BLUE);
                            screen.showScreen();
                            if (screen.getFunctionReturn().equals("Enter")) {
                                break;
                            }
                        }
                        newPlayerTeacherP("checkHarvest");
                    } else {
                        showAWordForSomeSeconds("所有作物未成熟", 0.5);
                    }
                    break;
                case 5:
                    long price = (long) Math.floor(300 * levelShow);
                    setInput();
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight - 4,
                                "钱包：" + money + "元  Level " + levelShow);
                        screen.addWords(halfWeight, halfHeight - 2, "请输入要购买的作物数量");
                        screen.addWords(halfWeight, halfHeight - 1, input.get(0));
                        if (!input.get(0).equals("") && !input.get(0).equals("-")) {
                            if (Integer.valueOf(input.get(0)) > 0) {
                                screen.addWords(halfWeight, halfHeight + 1,
                                        "共需 " + price * Integer.valueOf(input.get(0)) + " 元");
                            }
                        }
                        input = getEnterNumber(input.get(0));
                        if (input.get(1).equals("T")) {
                            break;
                        }
                        screen.showScreen();
                    }
                    if (Integer.valueOf(input.get(0)) < 0) {
                        showAWordForSomeSeconds("输入无效", 0.5);
                        break;
                    }
                    int num = Integer.valueOf(input.get(0));
                    if (money >= num * price) {
                        for (int fnum = 0; fnum < num; fnum++) {
                            money -= price;
                            int result = random.nextInt((vagetables.length - 1) * 2 + soil.length + 5 + 50);
                            for (int l = 0; l < 100; l++) {
                                showAWordForSomeSeconds("<" + (fnum + 1) + ">[ " + l + "% ]", 0.003);
                            }
                            if (0 <= result && result < (vagetables.length - 1)) {
                                showAWordForSomeSeconds("恭喜你，获得了作物 " + vagetablesC[result] + " ×1", 0.7);
                                Warehouse<Vagetables> warehouse = new Warehouse<>();
                                warehouse.setName(vagetables[result]);
                                warehouse.setType("VAGETABLES");
                                addWarehouse(warehouse, 1);
                            } else if ((vagetables.length - 1) <= result && result < (vagetables.length - 1) * 2) {
                                showAWordForSomeSeconds(
                                        "恭喜你，获得了 " + vagetablesC[result - (vagetables.length - 1)] + "种子 ×1", 0.7);
                                Warehouse<Vagetables> warehouse = new Warehouse<>();
                                warehouse.setName(vagetables[result - (vagetables.length - 1)]);
                                warehouse.setType("SEEDS");
                                addWarehouse(warehouse, 1);
                            } else if ((vagetables.length - 1) * 2 <= result
                                    && result < (vagetables.length - 1) * 2 + soil.length) {
                                showAWordForSomeSeconds("恭喜获得 " + soilC[result - (vagetables.length - 1) * 2] + " ×1",
                                        0.7);
                                vagetablesList.add(new Vagetables("NONE"));
                                soilList.add(new Soil(soil[result - (vagetables.length - 1) * 2],
                                        findSuitablePosision(world.playerPosision)));
                            } else if ((vagetables.length - 1) * 2 + soil.length <= result
                                    && result < (vagetables.length - 1) * 2 + soil.length + 5) {
                                int getM = (int) Math.floor(
                                        100 * (result - ((vagetables.length - 1) * 2 + soil.length) + 1) * levelShow);
                                showAWordForSomeSeconds("恭喜获得 " + getM + " 元", 0.7);
                                money += getM;
                            } else {
                                showAWordForSomeSeconds("很遗憾，什么都没有获得", 0.7);
                            }
                            letWerehouseListSmall();
                        }
                        getAchievement("GoodLuck");
                    } else {
                        showAWordForSomeSeconds("资金不足", 0.5);
                    }
                    break;
                case 6:
                    while (true) {
                        screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 5,
                                "钱包：" + money + "元  Level " + levelShow);
                        int line = 0;
                        int list = 0;
                        for (int vagid = 0; vagid < vagetables.length - 1; vagid++) {
                            screen.addWords(halfWeight - 26 + 18 * list, 7 + line,
                                    vagetablesC[vagid] + "：￥" + (int) (testVagetable.getCost(vagetables[vagid])
                                            * getUpPrice()) + " -> ￥"
                                            + (int) (testVagetable.getMoney(vagetables[vagid])
                                                    * getUpPrice() * 2),
                                    "l");
                            line++;
                            if (vagid % (Math.ceil((vagetables.length) / 3.0)) == 0 && vagid != 0) {
                                list++;
                                line = 0;
                            }
                        }
                        screen.showScreen();
                        if (screen.getFunctionReturn().equals("Enter")) {
                            break;
                        }
                    }
                    getAchievement("All");
                    break;
                case 7:
                    nextInterface = "Warehouse";
                    run = false;
                    break;
                case 8:
                    nextInterface = "Pasture";
                    run = false;
                    break;
                case 9:
                    nextInterface = "World";
                    run = false;
                    break;
                case 10:
                    int nowChoiceHelp = 0;
                    int c = 0;
                    while (true) {
                        String needHelp[] = {
                                "1.关于作者",
                                "2.关于农田",
                                "3.关于仓库",
                                "4.关于市场",
                                "5.关于厨房",
                                "6.关于员工",
                                "7.新手教程",
                                "8.返回游戏",
                                "9.调节刷新率",
                                "10.查看成就"
                        };
                        for (int i = 0; i < needHelp.length; i++) {
                            String p = needHelp[i];
                            int move = 0;
                            if (i == nowChoiceHelp) {
                                p = "> " + p;
                                move = 1;
                            }
                            screen.addWords(halfWeight - 3 - move, halfHeight - (4 - i), p, "l");
                        }
                        String resultKeyHelp = screen.getFunctionReturn();
                        if (resultKeyHelp.equals("W") && nowChoiceHelp > 0) {
                            nowChoiceHelp--;
                        } else if (resultKeyHelp.equals("S") && nowChoiceHelp < needHelp.length - 1) {
                            nowChoiceHelp++;
                        } else if (resultKeyHelp.equals("Enter")) {
                            c = nowChoiceHelp + 1;
                        }
                        screen.showScreen();
                        switch (c) {
                            case 1:
                                showAWordForSomeSeconds("作者：Jeason", 1.5);
                                showAWordForSomeSeconds("编写工具：Visual Studio Code", 1.5);
                                showAWordForSomeSeconds("辅助工具：TONGYI Lingma / CODEGEEX", 1.5);
                                break;
                            case 2:
                                showAWordForSomeSeconds("农田由状态，[农田]，选项构成", 1.5);
                                showAWordForSomeSeconds("农田类型如 [蔬菜|农田类型|生长进度] 构成", 1.5);
                                showAWordForSomeSeconds("当农田数量过多时，会出现缩短显示，此时中括号中的是生长进度", 1.5);
                                showAWordForSomeSeconds("你可以通过文字进行交互，种植时仓库中必须有种子", 1.5);
                                break;
                            case 3:
                                showAWordForSomeSeconds("仓库用于储存你的蔬菜、种子、食物", 1.5);
                                showAWordForSomeSeconds("蔬菜可以售卖，种子可以种植，食物可以供给员工", 1.5);
                                showAWordForSomeSeconds("你可以在仓库中通过文字操作它们", 1.5);
                                break;
                            case 4:
                                showAWordForSomeSeconds("市场用于购买种子、食物，雇佣员工", 1.5);
                                showAWordForSomeSeconds("种子可以种植，食物可以供给员工，食物右边的中括号表示可以供给的能量", 1.5);
                                showAWordForSomeSeconds("你可以通过文字进行购买", 1.5);
                                break;
                            case 5:
                                showAWordForSomeSeconds("厨房用于制作食物", 1.5);
                                showAWordForSomeSeconds("制作时，原料必须充足", 1.5);
                                showAWordForSomeSeconds("选择食物时，食物右边的中括号表示可以供给的能量", 1.5);
                                showAWordForSomeSeconds("你可以通过文字进行制作", 1.5);
                                break;
                            case 6:
                                showAWordForSomeSeconds("员工可以帮你实现自动化农场", 1.5);
                                showAWordForSomeSeconds("员工工作时需要消耗能量，能量为零时，会随机食用仓库中的食物", 1.5);
                                int allSize = farmerList.size() + cookerList.size() + buyerList.size()
                                        + sellerList.size();
                                if (allSize != 0) {
                                    showAWordForSomeSeconds("接下来将展示你拥有的所有员工", 1.5);
                                    boolean delete = false;
                                    int priceD = (int) (10000 * getUpPrice());
                                    setInput();
                                    while (true) {
                                        update();
                                        screen.addWords(halfWeight, halfHeight - 7, "员工[类型|精力值]");
                                        int list = 0;
                                        int line = 0;
                                        int all = 1;
                                        String first = "";
                                        if (input.get(1).equals("T")) {
                                            break;
                                        } else if (!input.get(0).equals("-1")) {
                                            delete = true;
                                        } else {
                                            delete = false;
                                        }
                                        if (delete) {
                                            screen.addWords(halfWeight, halfHeight + 8, "当前解雇员工需要 ￥" + priceD + "/人");
                                            screen.addWords(halfWeight, halfHeight + 10,
                                                    "请输入需要解雇的员工的编号[输入 0 全部解雇，输入 -1 隐藏界面并退出]");
                                            screen.addWords(halfWeight, halfHeight + 11, input.get(0));
                                        }
                                        input = getEnterNumber(input.get(0));
                                        for (int i = 0; i < farmerList.size(); i++) {
                                            first = "<" + all + ">";
                                            screen.addWords(halfWeight - 25 + 18 * list, halfHeight - 5 + line,
                                                    first + farmerList.get(i).getName() + " [农场照管员|"
                                                            + farmerList.get(i).getEnerge() + "]",
                                                    "l");
                                            line++;
                                            if (line != 0 && (line) % Math.ceil(allSize / 3.0) == 0) {
                                                list++;
                                                line = 0;
                                            }
                                            all++;
                                        }
                                        for (int i = 0; i < cookerList.size(); i++) {
                                            first = "<" + all + ">";
                                            screen.addWords(halfWeight - 25 + 18 * list, halfHeight - 5 + line,
                                                    first + cookerList.get(i).getName() + " [厨师|"
                                                            + cookerList.get(i).getEnerge() + "]",
                                                    "l");
                                            line++;
                                            if (line != 0 && (line) % Math.ceil(allSize / 3.0) == 0) {
                                                list++;
                                                line = 0;
                                            }
                                            all++;
                                        }
                                        for (int i = 0; i < buyerList.size(); i++) {
                                            first = "<" + all + ">";
                                            String word = "";
                                            if (buyerList.get(i).getType().equals("SEEDS")) {
                                                word = "种子采购员";
                                            } else if (buyerList.get(i).getType().equals("Animal")) {
                                                word = "动物采购员";
                                            } else if (buyerList.get(i).getType().equals("Food")) {
                                                word = "食材采购员";
                                            }
                                            screen.addWords(halfWeight - 25 + 18 * list, halfHeight - 5 + line,
                                                    first + buyerList.get(i).getName() + " [" + word + "|"
                                                            + buyerList.get(i).getEnerge() + "]",
                                                    "l");
                                            line++;
                                            if (line != 0 && (line) % Math.ceil(allSize / 3.0) == 0) {
                                                list++;
                                                line = 0;
                                            }
                                            all++;
                                        }
                                        for (int i = 0; i < sellerList.size(); i++) {
                                            first = "<" + all + ">";
                                            String word = "";
                                            if (sellerList.get(i).getType().equals("VAGETABLES")) {
                                                word = "作物销售员";
                                            } else if (sellerList.get(i).getType().equals("Animal")) {
                                                word = "动物销售员";
                                            }
                                            screen.addWords(halfWeight - 25 + 18 * list, halfHeight - 5 + line,
                                                    first + sellerList.get(i).getName() + " [" + word + "|"
                                                            + sellerList.get(i).getEnerge() + "]",
                                                    "l");
                                            line++;
                                            if (line != 0 && (line) % Math.ceil(allSize / 3.0) == 0) {
                                                list++;
                                                line = 0;
                                            }
                                            all++;
                                        }
                                        screen.showScreen();
                                    }
                                    if (delete) {
                                        if (!input.get(0).equals("-") && !input.get(0).equals("")) {
                                            int choiced = Integer.valueOf(input.get(0));
                                            if (money < priceD) {
                                                showAWordForSomeSeconds("资金不足", 0.5);
                                            } else {
                                                if (choiced > 0 && choiced <= farmerList.size()) {
                                                    farmerList.remove(choiced - 1);
                                                    money -= priceD;
                                                    showAWordForSomeSeconds("解雇成功", 0.5);
                                                } else if (choiced > farmerList.size()
                                                        && choiced <= farmerList.size() + cookerList.size()) {
                                                    cookerList.remove(choiced - farmerList.size() - 1);
                                                    money -= priceD;
                                                    showAWordForSomeSeconds("解雇成功", 0.5);
                                                } else if (choiced > farmerList.size() + cookerList.size()
                                                        && choiced <= farmerList.size() + cookerList.size()
                                                                + buyerList.size()) {
                                                    buyerList.remove(
                                                            choiced - farmerList.size() - cookerList.size() - 1);
                                                    money -= priceD;
                                                    showAWordForSomeSeconds("解雇成功", 0.5);
                                                } else if (choiced > farmerList.size() + cookerList.size()
                                                        + buyerList.size()
                                                        && choiced <= allSize) {
                                                    sellerList.remove(
                                                            choiced - farmerList.size() - cookerList.size()
                                                                    - buyerList.size() - 1);
                                                    money -= priceD;
                                                    showAWordForSomeSeconds("解雇成功", 0.5);
                                                } else if (choiced == 0) {
                                                    if (money < priceD * allSize) {
                                                        showAWordForSomeSeconds("资金不足", 0.5);
                                                    } else {
                                                        farmerList.clear();
                                                        cookerList.clear();
                                                        buyerList.clear();
                                                        sellerList.clear();
                                                        money -= priceD * allSize;
                                                        showAWordForSomeSeconds("解雇成功", 0.5);
                                                    }
                                                } else {
                                                    showAWordForSomeSeconds("输入无效", 0.5);
                                                }
                                            }
                                        } else {
                                            showAWordForSomeSeconds("输入无效", 0.5);
                                        }
                                    }
                                }
                                break;
                            case 7:
                                newPlayerTeacher();
                            case 8:
                                break;
                            case 9:
                                fps = screen.findSuitableFPS();
                                setScreen();
                                break;
                            case 10:
                                int nowS = 0;
                                while (true) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                                    screen.addWords(NewUnicodeScreen.LIGHT_BLUE, halfWeight, 5,
                                            "当前已获得 " + achievementList.size() + " 个成就["
                                                    + (nowS + 1) + "/" + achievementList.size() + "]");
                                    screen.addWords(halfWeight, halfHeight - 3,
                                            "当前已获得 " + achievementList.size() + " 个成就["
                                                    + (nowS + 1) + "/" + achievementList.size() + "]");
                                    int index = Arrays.asList(achievements)
                                            .indexOf(achievementList.get(nowS).getType());
                                    screen.addWords(NewUnicodeScreen.LIGHT_YELLOW, halfWeight, halfHeight - 1,
                                            achievementsC[index]);
                                    screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, halfHeight + 1,
                                            simpleDateFormat.format(new Date(achievementList.get(nowS).getTime())));
                                    screen.addWords(halfWeight - 10, halfHeight, "<[A]");
                                    screen.addWords(halfWeight + 10, halfHeight, "[D]>");
                                    screen.addWords(NewUnicodeScreen.DARK_GRAY, halfWeight, halfHeight + 5,
                                            "按下 ESC 退出");
                                    String resultKeyR = screen.getFunctionReturn();
                                    if (resultKeyR.equals("D") && nowS < achievementList.size() - 1) {
                                        nowS++;
                                    } else if (resultKeyR.equals("A") && nowS > 0) {
                                        nowS--;
                                    } else if (resultKeyR.equals("Escape") && nowS < achievementList.size()) {
                                        break;
                                    }
                                    screen.showScreen();
                                }
                                break;
                            default:
                                break;
                        }
                        if (c == 8) {
                            break;
                        } else {
                            c = 0;
                        }
                    }
                    break;
                case 11:
                    run = false;
                    break;
                case 12:
                    rebuild();
                    break;
                default:
                    break;
            }
            if (System.currentTimeMillis() - Lastsave >= 20 * 1000) {
                save(saveName);
                Lastsave = System.currentTimeMillis();
            }
        }
        return nextInterface;
    }

    static int[] harvestVagetables() {
        int[] gv = { 0, 0, 0, 0 };
        int num = 0;
        for (Vagetables vv : vagetablesList) {
            if (vv.grow == vv.top && vv.top != 0) {
                int nr = random.nextInt(soilList.get(num).getLuck());
                level += Math.floor(vv.getMoney(vv.id) * random.nextDouble(1.3));
                int addNumber = 0;
                if (nr == random.nextInt(30)) {
                    addNumber = random.nextInt(0, 1);
                    gv[0]++;
                } else if (nr == random.nextInt(30)) {
                    addNumber = random.nextInt(2, 4);
                    gv[1]++;
                } else if (nr == random.nextInt(30) || nr == random.nextInt(30)
                        || nr == random.nextInt(30) || nr == random.nextInt(30)) {
                    addNumber = random.nextInt(3, 4);
                    gv[3]++;
                } else {
                    addNumber = random.nextInt(2, 3);
                    gv[2]++;
                }
                for (int i = 0; i < addNumber; i++) {
                    Warehouse<Vagetables> c = new Warehouse<>();
                    c.setType("VAGETABLES");
                    c.setName(vv.id);
                    addWarehouse(c, 1);
                    letWerehouseListSmall();
                }
                vv.change("NONE");
            }
            num++;
        }
        getAchievement("Harvest");
        newPlayerTeacherP("harvest");
        return gv;
    }

    static void newPlayerTeacher() {
        boolean quitr = true;
        while (true) {
            setScreen();
            screen.addWords(halfWeight, halfHeight - 2, "是否进行新手教程？");
            screen.addWords(halfWeight - 2, halfHeight, "是", "l", NewUnicodeScreen.LIGHT_GREEN);
            screen.addWords(halfWeight + 2, halfHeight, "否", "l", NewUnicodeScreen.RED);
            if (quitr) {
                screen.addWords(halfWeight - 3, halfHeight, "> ", "l");
            } else {
                screen.addWords(halfWeight + 1, halfHeight, "> ", "l");
            }
            String resultKeyT = screen.getFunctionReturn();
            if (resultKeyT.equals("Enter")) {
                break;
            } else if (resultKeyT.equals("A")) {
                quitr = true;
            } else if (resultKeyT.equals("D")) {
                quitr = false;
            }
            screen.showScreen();
        }
        if (!quitr) {
            nowStep = steps.length + 1;
            return;
        }
        showAWordForSomeSeconds("欢迎你，新玩家！", 2);
        showAWordForSomeSeconds("这里是文字农场，你可以通过 W/S/Enter 进行交互", 2);
        showAWordForSomeSeconds("这里有各式各样的农田、蔬菜、员工、食物等玩法", 2);
        showAWordForSomeSeconds("你可以在这里享受庄园生活", 2);
        showAWordForSomeSeconds("对了！", 2);
        showAWordForSomeSeconds("游戏有时候还需要一些运气", 2);
        showAWordForSomeSeconds("就比如********", 2);
        showAWordForSomeSeconds("还有，", 2);
        showAWordForSomeSeconds("游戏支持存档离线加载", 2);
        showAWordForSomeSeconds("每隔20s会自动存档一次", 2);
        showAWordForSomeSeconds("当然为了保险起见，每次游玩结束记得存档哦！", 2);
        showAWordForSomeSeconds("还有更多玩法等你探索！", 2);
        showAWordForSomeSeconds("现在，开始照顾你的农场吧！", 2);
        getAchievement("NewPlayer");
        nowStep = 0;
    }

    static double getLevelShow() {
        long workLevel = 1;
        long levelMin = 0;
        long levelMax = 0;
        long upNumber = 10000;
        levelShow = 0;
        while (true) {
            upNumber = (long) (10000 + 30000 * Math.floor(levelShow / 5));
            levelMax = levelMin + workLevel * upNumber;
            if (levelMax > level) {
                break;
            } else {
                levelMin = levelMax;
            }
            workLevel++;
            levelShow = workLevel;
        }
        levelShow = workLevel + (Math.floor((((double) level - levelMin) / (levelMax - levelMin)) * 10)) / 10;
        if (levelShow >= 30) {
            getAchievement("Level");
        }
        if (levelShow >= 100) {
            getAchievement("FinalPlayer");
        }
        return levelShow;
    }

    static int getSoilPrice() {
        int price;
        if (levelShow <= 2) {
            price = (int) Math.floor(500 + 100 * levelShow);
        } else if (levelShow <= 5) {
            price = (int) Math.floor(500 + 200 * levelShow);
        } else if (levelShow <= 10) {
            price = (int) Math.floor(500 + 300 * levelShow);
        } else {
            price = (int) Math.floor(500 + 500 * levelShow);
        }
        return price;
    }

    static int getSoilUpdatePrice() {
        int price;
        if (levelShow <= 2) {
            price = (int) Math.floor(1000 + 200 * levelShow);
        } else if (levelShow <= 5) {
            price = (int) Math.floor(1000 + 400 * levelShow);
        } else if (levelShow <= 10) {
            price = (int) Math.floor(1000 + 700 * levelShow);
        } else {
            price = (int) Math.floor(1000 + 1000 * levelShow);
        }
        return price;
    }

    static boolean haveNoneSoil() {
        boolean haveNoneSoil;
        int s = vagetablesList.stream().filter(vagetable -> vagetable.getId().equals("NONE")).toList().size();
        if (s == 0) {
            haveNoneSoil = false;
        } else {
            haveNoneSoil = true;
        }
        return haveNoneSoil;
    }

    static int getBeGrowedVagetable() {
        setInput();
        newPlayerTeacherP("chooseVegetable");
        ArrayList<List<String>> finalVagetables = getVagetablesFix();
        String[] c = { "A", "B", "C", "D", "E" };
        int f = -1;
        while (true) {
            showTeaching();
            screen.addWords(NewUnicodeScreen.LIGHT_GREEN, halfWeight, 3, "钱包：" + money + "元  Level " + levelShow);
            if (!input.get(0).equals("")) {
                printVagetables(String.valueOf(input.get(0).charAt(0)));
            } else {
                screen.addWords(halfWeight, halfHeight - 4, "请输入 A-E 以选择价格区间");
            }
            screen.addWords(halfWeight, halfHeight + 6, "请选择需要购买的作物种子[形如'A15']");
            screen.addWords(halfWeight, halfHeight + 7, input.get(0));
            input = getEnterString(input.get(0));
            if (input.get(1).equals("T")) {
                break;
            }
            try {
                int q = Arrays.asList(c).indexOf(String.valueOf(input.get(0).charAt(0)));
                f = Arrays.asList(vagetables)
                        .indexOf(finalVagetables.get(q).get(Integer.valueOf(input.get(0).substring(1)) - 1));
                screen.addWords(halfWeight, halfHeight + 9, "已选择 " + vagetablesC[f]
                        + "种子 ，可购买 " + (int) (money / (testVagetable.getCost(vagetables[f]) * getUpPrice()))
                        + " 颗");
            } catch (Exception e) {
                f = -1;
            }

            screen.showScreen();
        }
        return f + 1;
    }

    static double getUpPrice() {
        double upPrice = 1;
        if (levelShow > 2) {
            upPrice = 1 + (levelShow - 2) * 0.7 - Math.sin(nowGameTime % aDay * 4 * 3.14);
        }
        return upPrice;
    }

    static ArrayList<List<String>> getVagetablesFix() {
        List<String> Low = Arrays.asList(vagetables).stream()
                .filter(vag -> testVagetable.getCost(vag) <= 1000 && !vag.equals("NONE")).toList();
        List<String> LowM = Arrays.asList(vagetables).stream().filter(
                vag -> testVagetable.getCost(vag) > 1000
                        && testVagetable.getCost(vag) <= 5000)
                .toList();
        List<String> Middle = Arrays.asList(vagetables).stream()
                .filter(vag -> testVagetable.getCost(vag) > 5000
                        && testVagetable.getCost(vag) <= 10000)
                .toList();
        List<String> MiddleT = Arrays.asList(vagetables).stream()
                .filter(vag -> testVagetable.getCost(vag) > 10000
                        && testVagetable.getCost(vag) <= 50000)
                .toList();
        List<String> Top = Arrays.asList(vagetables).stream()
                .filter(vag -> testVagetable.getCost(vag) > 50000)
                .toList();
        ArrayList<List<String>> finalList = new ArrayList<>();
        finalList.add(Low);
        finalList.add(LowM);
        finalList.add(Middle);
        finalList.add(MiddleT);
        finalList.add(Top);
        return finalList;
    }

    static ArrayList<List<String>> getVagetablesCFix() {
        List<String> LowC = Arrays.asList(vagetablesC).stream()
                .filter(vag -> testVagetable
                        .getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) <= 1000 && !vag.equals("无作物"))
                .toList();
        List<String> LowMC = Arrays.asList(vagetablesC).stream().filter(
                vag -> testVagetable.getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) > 1000
                        && testVagetable.getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) <= 5000)
                .toList();
        List<String> MiddleC = Arrays.asList(vagetablesC).stream()
                .filter(vag -> testVagetable
                        .getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) > 5000
                        && testVagetable.getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) <= 10000)
                .toList();
        List<String> MiddleTC = Arrays.asList(vagetablesC).stream()
                .filter(vag -> testVagetable
                        .getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) > 10000
                        && testVagetable.getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) <= 50000)
                .toList();
        List<String> TopC = Arrays.asList(vagetablesC).stream()
                .filter(vag -> testVagetable
                        .getCost(vagetables[Arrays.asList(vagetablesC).indexOf(vag)]) > 50000)
                .toList();
        ArrayList<List<String>> finalListC = new ArrayList<>();
        finalListC.add(LowC);
        finalListC.add(LowMC);
        finalListC.add(MiddleC);
        finalListC.add(MiddleTC);
        finalListC.add(TopC);
        return finalListC;
    }

    static int printVagetables(String type) {
        double upPrice = getUpPrice();
        ArrayList<List<String>> finalList = getVagetablesFix();
        ArrayList<List<String>> finalListC = getVagetablesCFix();
        int showType = 0;
        String[] priceQ = { "低价区[A]", "低中价区[B]", "中价区[C]", "中高价区[D]", "高价区[E]" };
        if (type.equals("A")) {
            showType = 0;
        } else if (type.equals("B")) {
            showType = 1;
        } else if (type.equals("C")) {
            showType = 2;
        } else if (type.equals("D")) {
            showType = 3;
        } else if (type.equals("E")) {
            showType = 4;
        } else {
            showType = 0;
        }
        screen.addWords(halfWeight, 5, priceQ[showType]);
        int line = 0;
        int list = 0;
        for (int i = 0; i < finalList.get(showType).size(); i++) {
            screen.addWords(halfWeight - 27 + 18 * list, 6 + line, "<" + (i + 1) + ">" + finalListC.get(showType).get(i)
                    + "：￥" + (long) (testVagetable.getCost(finalList.get(showType).get(i)) * upPrice), "l");
            line++;
            if (i != 0 && i % (int) Math.ceil(finalList.get(showType).size() / 3.0) == 0) {
                list++;
                line = 0;
            }
        }
        return finalList.get(showType).size();
    }

    static boolean growin(String a, int s) {
        vagetablesList.get(s).change(a);
        return true;
    }

    static boolean growin(String a, Vagetables s) {
        s.change(a);
        return true;
    }

    static void update() {
        letWerehouseListSmall();
        long currentTimeMillis = System.currentTimeMillis();
        long l = currentTimeMillis - LastUpdateTime;
        int oneOfTen = 0;
        long needCalculate = (l / 5) * (soilList.size() + animalList.size()) * farmerList.size();

        if (System.currentTimeMillis() - lastStepTime > 10 * 1000) {
            newPlayerTeacherP("FINISHTEACHING");
        }
        if ((l > 1 * 60 * 60 * 1000l && needCalculate > 1000L * (500 + 250) * 10) || l > 1 * 24 * 60 * 60 * 1000l) {
            oneOfTen = (int) (Math.floor(l / 5000) / 10);
        }
        if (l > 30 * 24 * 60 * 60 * 1000l) {
            getAchievement("LongTime");
        }
        if (money > 10000000000L) {
            getAchievement("Wallet");
        }
        if (!farmerList.isEmpty() && !cookerList.isEmpty() && !buyerList.isEmpty() && !sellerList.isEmpty()) {
            getAchievement("AutoRun");
        }
        if (System.currentTimeMillis() - gameTimeLastUpdate >= 400) {
            nowGameTime += System.currentTimeMillis() - gameTimeLastUpdate;
            gameTimeLastUpdate = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - LastMove >= 2000) {
            animalList.forEach(ani -> ani.move());
            LastMove = System.currentTimeMillis();
        }
        if (l >= 5000) {
            int iterations = (int) Math.floor(l / 5000);
            for (int i = 0; i < (int) iterations; i++) {
                if (oneOfTen != 0) {
                    screen.addWords(halfWeight, halfHeight - 2, "数据较大，正在计算中");
                    screen.addWords(NewUnicodeScreen.LIGHT_BLUE, halfWeight, halfHeight - 1,
                            (double) ((int) ((i / (double) iterations) * 10000)) / 100 + "%");
                    screen.showScreen();
                }

                // 股票
                stockList.forEach(s -> s.price = s.getPrice((int) (gameTimeLastUpdate / aDay)));

                // 缓存仓库中的食物
                List<Food> availableFoods = new ArrayList<>();
                if (!farmerList.isEmpty() || !cookerList.isEmpty() || !buyerList.isEmpty() || !sellerList.isEmpty()) {
                    availableFoods = warehouseList.stream()
                            .map(Warehouse::getObject)
                            .filter(Food.class::isInstance)
                            .map(Food.class::cast)
                            .filter(food -> food.getIfEatCanGetEnerge() > 50)
                            .collect(Collectors.toList());
                }

                if (!cookerList.isEmpty()) {
                    for (Cooker cooker : cookerList) {
                        String result = cooker.work(warehouseList, warehouseListNumber);
                        if (result.equals("Can")) {
                            List<String> formulation = testFood.getFormulation(cooker.getFood());
                            for (String need : formulation) {
                                String raw[] = need.split(",");
                                int index = warehouseList.indexOf(warehouseList.stream()
                                        .filter(w -> w.getType().equals(raw[0]) && w.getName().equals(raw[1])).toList()
                                        .get(0));
                                removeWarehouse(index, 1);
                            }
                            Warehouse<Food> warehouse = new Warehouse<>();
                            warehouse.setType("Food");
                            warehouse.setName(cooker.getFood());
                            warehouse.setObject(new Food(cooker.getFood()));
                            addWarehouse(warehouse, 1);
                        } else if (result.equals("Food")) {
                            if (!availableFoods.isEmpty()) {
                                Collections.shuffle(availableFoods);
                                Food food = availableFoods.get(0);
                                cooker.eatFood(food);
                                int index = warehouseList
                                        .indexOf(warehouseList.stream().filter(w -> w.getObject() != null)
                                                .filter(w -> w.getType().equals("Food")
                                                        && w.getName().equals(food.getId()))
                                                .findFirst().get());
                                removeWarehouse(index, 1);
                                availableFoods.remove(0);
                            }
                        }
                    }
                }

                if (!buyerList.isEmpty()) {
                    for (Buyer buyer : buyerList) {
                        int give = 0;
                        if (buyer.getType().equals("SEEDS") || buyer.getType().equals("Food")) {
                            give = getWarehouseNumber(buyer.getType(), buyer.getWillBuy());
                        } else if (buyer.getType().equals("Animal")) {
                            give = animalList.stream().filter(a -> a.getId().equals(buyer.getWillBuy())).toList()
                                    .size();
                        }
                        String result = buyer.work(money, getUpPrice(), give);
                        if (result.equals("Can")) {
                            if (buyer.getType().equals("SEEDS")) {
                                Warehouse<Vagetables> warehouse = new Warehouse<>();
                                warehouse.setType("SEEDS");
                                warehouse.setName(buyer.getWillBuy());
                                addWarehouse(warehouse, 1);
                                money -= (int) testVagetable.getCost(buyer.getWillBuy()) * getUpPrice();
                            } else if (buyer.getType().equals("Food")) {
                                Warehouse<Food> warehouse = new Warehouse<>();
                                warehouse.setType("Food");
                                warehouse.setName(buyer.getWillBuy());
                                warehouse.setObject(new Food(buyer.getWillBuy()));
                                addWarehouse(warehouse, 1);
                                money -= (int) testFood.getPrice(buyer.getWillBuy()) * getUpPrice();
                            } else if (buyer.getType().equals("Animal")) {
                                Animal animal = new Animal(buyer.getWillBuy(), 0, random.nextInt(0, 2));
                                animalList.add(animal);
                                money -= (int) testAnimal.getCost(buyer.getWillBuy()) * getUpPrice();
                            }
                        } else if (result.equals("Food")) {
                            if (!availableFoods.isEmpty()) {
                                Collections.shuffle(availableFoods);
                                Food food = availableFoods.get(0);
                                buyer.eatFood(food);
                                int index = warehouseList
                                        .indexOf(warehouseList.stream().filter(w -> w.getObject() != null)
                                                .filter(w -> w.getType().equals("Food")
                                                        && w.getName().equals(food.getId()))
                                                .findFirst().get());
                                removeWarehouse(index, 1);
                                availableFoods.remove(0);
                            }
                        }
                    }
                }

                if (!sellerList.isEmpty()) {
                    for (Seller seller : sellerList) {
                        String result = seller.work(warehouseList, warehouseListNumber);
                        if (result.equals("Sell")) {
                            int index = warehouseList.indexOf(warehouseList.stream().filter(
                                    w -> w.getType().equals(seller.getType())
                                            && w.getName().equals(seller.getWillSell()))
                                    .toList().get(0));
                            removeWarehouse(index, 1);
                            long getMoney = 0;
                            if (seller.getType().equals("VAGETABLES")) {
                                getMoney = (long) (testVagetable.getMoney(seller.getWillSell()) * getUpPrice());
                            } else if (seller.getType().equals("Animal")) {
                                getMoney = (long) (testAnimal.getMoney(seller.getWillSell()) * getUpPrice());
                            }
                            money += getMoney;
                        } else if (result.equals("Food")) {
                            if (!availableFoods.isEmpty()) {
                                Collections.shuffle(availableFoods);
                                Food food = availableFoods.get(0);
                                seller.eatFood(food);
                                int index = warehouseList
                                        .indexOf(warehouseList.stream().filter(w -> w.getObject() != null)
                                                .filter(w -> w.getType().equals("Food")
                                                        && w.getName().equals(food.getId()))
                                                .findFirst().get());
                                removeWarehouse(index, 1);
                                availableFoods.remove(0);
                            }
                        }
                    }
                }

                // 遍历蔬菜列表，更新生长值
                for (int j = 0; j < vagetablesList.size(); j++) {
                    Soil soil = soilList.get(j);
                    int needGrow = soil.getGrow() + (int) Math.floor(random.nextInt(soil.getGrow() + 1) / 2);
                    Vagetables veg = vagetablesList.get(j);
                    veg.update(needGrow);
                    if (!farmerList.isEmpty()) {
                        int empIndex = j % farmerList.size();
                        Employee emp = farmerList.get(empIndex);
                        String check = emp.work(veg);
                        availableFoods = processEmployeeWork(emp, check, availableFoods, soil, j);
                    }
                    soil = null;
                }

                // 更新动物的生长值
                for (int j = 0; j < animalList.size(); j++) {
                    int needGrow = random.nextInt(1, 4);
                    Animal animal = animalList.get(j);
                    animal.update(needGrow);
                    if (!farmerList.isEmpty()) {
                        int empIndex = j % farmerList.size();
                        Employee emp = farmerList.get(empIndex);
                        String check = emp.work(animal);
                        availableFoods = processEmployeeWork(emp, check, availableFoods, animal, j);
                    }
                }

                // 动物繁殖逻辑优化
                Map<String, List<Animal>> groupedAnimals = animalList.stream()
                        .collect(Collectors.groupingBy(Animal::getId, Collectors.toList()));
                for (List<Animal> animals : groupedAnimals.values()) {
                    List<Animal> males = animals.stream().filter(a -> a.modeNum == 1 && a.sex == 1)
                            .collect(Collectors.toList());
                    List<Animal> females = animals.stream().filter(a -> a.modeNum == 1 && a.sex == 0)
                            .collect(Collectors.toList());
                    if (males.size() > 1 && females.size() > 1 && random.nextInt(100) == 5) {
                        males.get(0).modeNum++;
                        females.get(0).modeNum++;
                        animalList.add(new Animal(animals.get(0).getId(), 0, random.nextInt(2)));
                    }
                }
            }
            LastUpdateTime = currentTimeMillis;
        }
    }

    static void letWerehouseListSmall() {
        List<Warehouse<? extends Object>> finalList = new ArrayList<>();
        List<Integer> finalListNum = new ArrayList<>();
        List<String> kinds = warehouseList.stream().map(Warehouse::getType).distinct().collect(Collectors.toList());
        for (String kind : kinds) {
            List<String> names = warehouseList.stream()
                    .filter(w -> w.getType().equals(kind))
                    .map(Warehouse::getName)
                    .distinct()
                    .collect(Collectors.toList());
            for (String name : names) {
                List<Warehouse<? extends Object>> list = warehouseList.stream()
                        .filter(w -> w.getType().equals(kind) && w.getName().equals(name))
                        .collect(Collectors.toList());
                int num = 0;
                for (Warehouse<? extends Object> lists : list) {
                    num += warehouseListNumber.get(warehouseList.indexOf(lists));
                }
                if (num != 0) {
                    finalList.add(list.get(0));
                    finalListNum.add(num);
                }
            }
        }
        warehouseList = finalList;
        warehouseListNumber = finalListNum;
    }

    static int getWarehouseNumber(String type, String name) {
        for (int i = 0; i < warehouseList.size(); i++) {
            if (warehouseList.get(i).getType().equals(type) && warehouseList.get(i).getName().equals(name)) {
                return warehouseListNumber.get(i);
            }
        }
        return 0;
    }

    static int getWarehouseNumber(Warehouse<? extends Object> warehouse) {
        for (int i = 0; i < warehouseList.size(); i++) {
            if (warehouseList.get(i).equals(warehouse)) {
                return warehouseListNumber.get(i);
            }
        }
        return 0;
    }

    static List<Food> processEmployeeWork(Employee emp, String check, List<Food> availableFoods, Object target,
            int j) {
        if ("Harvest".equals(check)) {
            if (target instanceof Soil) {
                harvestVagetables();
            } else if (target instanceof Animal) {
                harvestAnimal();
            }
        } else if ("Food".equals(check) && !availableFoods.isEmpty()) {
            Collections.shuffle(availableFoods);
            Food food = availableFoods.get(0);
            emp.eatFood(food);
            int index = warehouseList.indexOf(warehouseList.stream().filter(w -> w.getObject() != null)
                    .filter(w -> w.getType().equals("Food")
                            && w.getName().equals(food.getId()))
                    .findFirst().get());
            removeWarehouse(index, 1);
            availableFoods.remove(0);
        } else if ("NONE".equals(check)) {
            if (target instanceof Soil) {
                List<String> seeds = warehouseList.stream()
                        .filter(w -> w.getType().equals("SEEDS"))
                        .map(Warehouse::getName)
                        .collect(Collectors.toList());
                if (seeds.size() > 0) {
                    Collections.shuffle(seeds);
                    vagetablesList.get(j).change(seeds.get(0));
                    int index = warehouseList.indexOf(warehouseList.stream().filter(w -> w.getType().equals("SEEDS"))
                            .filter(w -> w.getName().equals(seeds.get(0))).findFirst().get());
                    removeWarehouse(index, 1);
                }
            }
        }
        return availableFoods;
    }

    static void addWarehouse(Warehouse<? extends Object> warehouse, int num) {
        warehouseList.add(warehouse);
        warehouseListNumber.add(num);
        letWerehouseListSmall();
    }

    static void removeWarehouse(int index, int num) {
        warehouseListNumber.set(index, warehouseListNumber.get(index) - num);
        letWerehouseListSmall();
    }

    static void save(String saveS) {
        ObjectMapper mapper = new ObjectMapper();
        // setting
        Map<String, Object> setting = new HashMap<>();
        setting.put("money", money);
        setting.put("LastUpdateTime", LastUpdateTime);
        setting.put("level", level);
        setting.put("version", version);
        setting.put("time", nowGameTime);
        setting.put("step", nowStep);

        Map<String, Object> data = new HashMap<>();
        // farm
        ArrayList<Map<String, Object>> farm = new ArrayList<>();
        for (int i = 0; i < vagetablesList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("Vagetable", vagetablesList.get(i).getId());
            inf.put("Soil", soilList.get(i).getId());
            inf.put("Grow", vagetablesList.get(i).getGrow());
            inf.put("x", soilList.get(i).getX());
            inf.put("y", soilList.get(i).getY());
            farm.add(inf);
        }
        // animal
        ArrayList<Map<String, Object>> animal = new ArrayList<>();
        for (int i = 0; i < animalList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("Animal", animalList.get(i).getId());
            inf.put("mode", animalList.get(i).modeNum);
            inf.put("Grow", animalList.get(i).getGrow());
            inf.put("sex", animalList.get(i).sex);
            inf.put("x", animalList.get(i).getX());
            inf.put("y", animalList.get(i).getY());
            animal.add(inf);
        }
        // warehouse
        ArrayList<Map<String, Object>> warehouse = new ArrayList<>();
        for (int i = 0; i < warehouseList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("type", warehouseList.get(i).getType());
            inf.put("name", warehouseList.get(i).getName());
            inf.put("num", getWarehouseNumber(warehouseList.get(i)));
            warehouse.add(inf);
        }
        // employee
        ArrayList<Map<String, Object>> employee = new ArrayList<>();
        for (int i = 0; i < farmerList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("name", farmerList.get(i).getName());
            inf.put("energe", farmerList.get(i).getEnerge());
            inf.put("type", "Farm");
            inf.put("x", farmerList.get(i).getX());
            inf.put("y", farmerList.get(i).getY());
            employee.add(inf);
        }
        for (int i = 0; i < cookerList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("name", cookerList.get(i).getName());
            inf.put("energe", cookerList.get(i).getEnerge());
            inf.put("type", "Cooker");
            inf.put("food", cookerList.get(i).getFood());
            inf.put("x", cookerList.get(i).getX());
            inf.put("y", cookerList.get(i).getY());
            employee.add(inf);
        }
        for (int i = 0; i < buyerList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("name", buyerList.get(i).getName());
            inf.put("energe", buyerList.get(i).getEnerge());
            inf.put("type", "Buyer");
            inf.put("buyType", buyerList.get(i).getType());
            inf.put("will", buyerList.get(i).getWillBuy());
            inf.put("top", buyerList.get(i).getTop());
            inf.put("x", buyerList.get(i).getX());
            inf.put("y", buyerList.get(i).getY());
            employee.add(inf);
        }
        for (int i = 0; i < sellerList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("name", sellerList.get(i).getName());
            inf.put("energe", sellerList.get(i).getEnerge());
            inf.put("type", "Seller");
            inf.put("buyType", sellerList.get(i).getType());
            inf.put("will", sellerList.get(i).getWillSell());
            inf.put("top", sellerList.get(i).getLast());
            inf.put("x", sellerList.get(i).getX());
            inf.put("y", sellerList.get(i).getY());
            employee.add(inf);
        }
        // achievement
        ArrayList<Map<String, Object>> achievement = new ArrayList<>();
        for (int i = 0; i < achievementList.size(); i++) {
            Map<String, Object> inf = new HashMap<>();
            inf.put("name", achievementList.get(i).getType());
            inf.put("time", achievementList.get(i).getTime());
            achievement.add(inf);
        }
        // world
        Map<String, Object> worldMap = new HashMap<>();
        worldMap.put("x", world.playerPosision.x);
        worldMap.put("y", world.playerPosision.y);
        worldMap.put("seed", seed);
        // all
        data.put("world", worldMap);
        data.put("Achievement", achievement);
        data.put("Employee", employee);
        data.put("Warehouse", warehouse);
        data.put("Animal", animal);
        data.put("farm", farm);
        data.put("setting", setting);
        // stock
        Map<String, Object> stocks = new HashMap<>();
        List<Map<String, Object>> stoList = new ArrayList<>();
        for (Stock st : stockList) {
            Map<String, Object> stoin = new HashMap<>();
            stoin.put("name", st.getName());
            stoin.put("price", st.getPrice());
            stoin.put("have", st.getHave());
            stoin.put("seed", st.getSeed());
            stoList.add(stoin);
        }
        stocks.put("stock", stoList);
        // save
        try {
            File fileData = new File(currentDirectory + "\\" + saveS + "\\stock.json");
            if (!fileData.exists()) {
                fileData.createNewFile();
            }
            mapper.writeValue(fileData, stocks);
        } catch (IOException e) {
            showAWordForSomeSeconds(e.getMessage(), 0.5);
        }
        try {
            File fileData = new File(currentDirectory + "\\" + saveS + "\\locals.json");
            if (!fileData.exists()) {
                fileData.createNewFile();
            }
            mapper.writeValue(fileData, data);
        } catch (IOException e) {
            showAWordForSomeSeconds(e.getMessage(), 0.5);
        }
        Map<String, Object> settingData = new HashMap<>();
        settingData.put("FPS", fps);
        try {
            File fileData = new File(currentDirectory + "\\setting.json");
            if (!fileData.exists()) {
                fileData.createNewFile();
            }
            mapper.writeValue(fileData, settingData);
        } catch (IOException e) {
            showAWordForSomeSeconds(e.getMessage(), 0.5);
        }
    }

    static void printWarehouse(boolean t) {
        List<String> carehouseVagetablesTypes = warehouseList.stream()
                .filter(carehouse -> carehouse.getType().equals("VAGETABLES")).map(Warehouse::getName).distinct()
                .toList();
        int carehouseVagetablesTypesNumber = carehouseVagetablesTypes.size();
        List<String> carehouseSeedsTypes = warehouseList.stream()
                .filter(carehouse -> carehouse.getType().equals("SEEDS")).map(Warehouse::getName).distinct()
                .toList();
        int carehouseSeedsTypesNumber = carehouseSeedsTypes.size();
        List<String> carehouseFoodTypes = warehouseList.stream()
                .filter(carehouse -> carehouse.getType().equals("Food")).map(Warehouse::getName).distinct()
                .toList();
        int carehouseFoodTypesNumber = carehouseFoodTypes.size();
        List<String> carehouseAnimalTypes = warehouseList.stream()
                .filter(carehouse -> carehouse.getType().equals("Animal")).map(Warehouse::getName).distinct()
                .toList();
        int carehouseAnimalTypesNumber = carehouseAnimalTypes.size();
        int line = 5;
        if (carehouseVagetablesTypesNumber != 0) {
            screen.addWords(halfWeight - 27, 5, "农作物：", "l");
            for (String string : carehouseVagetablesTypes) {
                String will = "";
                if (t) {
                    if (carehouseFoodTypes.indexOf(string) + 1 < 10) {
                        will += " ";
                    }
                    will += "<" + (carehouseVagetablesTypes.indexOf(string) + 1) + ">";
                }
                will += vagetablesC[Arrays.asList(vagetables).indexOf(string)];
                will += " ×";
                will += getWarehouseNumber(
                        warehouseList.stream().filter(carehouse -> carehouse.getType().equals("VAGETABLES"))
                                .filter(carehouse -> carehouse.getName().equals(string)).toList().get(0));
                screen.addWords(halfWeight - 23, line, will, "l");
                line++;
            }
        }
        line = 5;
        if (carehouseSeedsTypesNumber != 0) {
            screen.addWords(halfWeight - 13, line, "  种子：", "l");
            for (String string : carehouseSeedsTypes) {
                String will = "";
                if (t) {
                    if (carehouseSeedsTypes.indexOf(string) + 1 < 10) {
                        will += " ";
                    }
                    will += "<" + (carehouseSeedsTypes.indexOf(string) + 1) + ">";
                }
                will += vagetablesC[Arrays.asList(vagetables).indexOf(string)];
                will += "种子";
                will += " ×";
                will += getWarehouseNumber(
                        warehouseList.stream().filter(carehouse -> carehouse.getType().equals("SEEDS"))
                                .filter(carehouse -> carehouse.getName().equals(string)).toList().get(0));
                screen.addWords(halfWeight - 9, line, will, "l");
                line++;
            }
        }
        line = 5;
        if (carehouseAnimalTypesNumber != 0) {
            screen.addWords(halfWeight + 3, line, "  动物：", "l");
            for (String string : carehouseAnimalTypes) {
                String will = "";
                if (t) {
                    if (carehouseAnimalTypes.indexOf(string) + 1 < 10) {
                        will += " ";
                    }
                    will += "<" + (carehouseAnimalTypes.indexOf(string) + 1) + ">";
                }
                will += animalsC[Arrays.asList(animals).indexOf(string)];
                will += " ×";
                will += getWarehouseNumber(
                        warehouseList.stream().filter(carehouse -> carehouse.getType().equals("Animal"))
                                .filter(carehouse -> carehouse.getName().equals(string)).toList().get(0));
                screen.addWords(halfWeight + 7, line, will, "l");
                line++;
            }
        }
        line = 5;
        if (carehouseFoodTypesNumber != 0) {
            screen.addWords(halfWeight + 16, line, "  食物：", "l");
            for (String food : carehouseFoodTypes) {
                String will = "";
                if (t) {
                    if (carehouseFoodTypes.indexOf(food) + 1 < 10) {
                        will += " ";
                    }
                    will += "<" + (carehouseFoodTypes.indexOf(food) + 1) + ">";
                }
                will += foodsListC[Arrays.asList(foodsList).indexOf(food)];
                will += " ×";
                will += getWarehouseNumber(
                        warehouseList.stream().filter(carehouse -> carehouse.getType().equals("Food"))
                                .filter(carehouse -> carehouse.getName().equals(food)).toList().get(0));
                screen.addWords(halfWeight + 20, line, will, "l");
                line++;
            }
        }
    }

    static void printPasture(boolean showID) {
        if (animalList.size() <= 27) {
            int nextLineNeedNumber = 3;
            int line = 5;
            for (int i = 0; i < animalList.size(); i++) {
                String nowAnimal = "";
                if (showID) {
                    if (i + 1 < 10) {
                        nowAnimal += " ";
                    }
                    nowAnimal += "<";
                    nowAnimal += i + 1;
                    nowAnimal += ">";
                }
                nowAnimal += "[";
                int position = 0;
                for (int j = 0; j < animals.length; j++) {
                    if (animals[j].equals(animalList.get(i).getId())) {
                        position = j;
                        break;
                    }
                }
                nowAnimal += animalsC[position];
                nowAnimal += "|";
                nowAnimal += animalList.get(i).getSex();
                nowAnimal += "|";
                nowAnimal += animalList.get(i).getModeNum();
                if (animalList.get(i).grow == animalList.get(i).top && animalList.get(i).top != 0) {
                    nowAnimal += "|已成熟]";
                } else {
                    nowAnimal += "|生长进度：" + animalList.get(i).getGrow() + "/" + animalList.get(i).getTop();
                    nowAnimal += "]";
                }

                screen.addWords(halfWeight + 20 * ((i % nextLineNeedNumber) - 1), line, nowAnimal);
                if ((i + 1) % nextLineNeedNumber == 0 && (i + 1) != animalList.size()) {
                    line++;
                }
            }
        } else {
            String topWords = "";
            topWords += "共有" + animalList.size() + "只动物，";
            int finished = animalList.stream().filter(a -> a.grow == a.top).toList().size();
            if (finished == animalList.size()) {
                topWords += "所有动物已成熟";
            } else if (finished != 0) {
                topWords += finished + " 只动物已成熟";
            } else {
                topWords += "无动物成熟";
            }
            screen.addWords(halfWeight - 13, 5, topWords, "l");
            screen.addWords(halfWeight - 13, 6, "其中有", "l");
            int nowline = 6;
            for (String a : animalList.stream().map(Animal::getId).distinct().filter(va -> !va.equals("NONE"))
                    .toList()) {
                topWords = "";
                topWords += animalList.stream().filter(ani -> ani.getId().equals(a)).toList().size() + " 只"
                        + animalsC[Arrays.asList(animals).indexOf(a)];
                topWords += "||";
                topWords += "♂" + animalList.stream().filter(v -> v.id.equals(a)).map(animal -> animal.sex)
                        .filter(s -> s.equals(1))
                        .toList().size();
                topWords += " ";
                topWords += "♀" + animalList.stream().filter(v -> v.id.equals(a)).map(animal -> animal.sex)
                        .filter(s -> s.equals(0))
                        .toList().size();
                topWords += "||生长进度："
                        + animalList.stream().filter(v -> v.id.equals(a))
                                .mapToInt(Animal::getGrow).min().getAsInt()
                        + "-"
                        + animalList.stream().filter(v -> v.id.equals(a))
                                .mapToInt(Animal::getGrow).max().getAsInt()
                        + " / " + new Animal(a, 2, 0).top;
                screen.addWords(halfWeight - 9, nowline, topWords, "l");
                nowline++;
            }
        }
    }

    static void printFarm(boolean showID) {
        if (soilList.size() <= 27) {
            int nextLineNeedNumber = 3;
            int line = 5;
            for (int i = 0; i < soilList.size(); i++) {
                String nowSoil = "";
                if (showID) {
                    if (i + 1 < 10) {
                        nowSoil += " ";
                    }
                    nowSoil += "<";
                    nowSoil += i + 1;
                    nowSoil += ">";
                }
                nowSoil += "[";
                int position = 0;
                for (int j = 0; j < vagetables.length; j++) {
                    if (vagetables[j].equals(vagetablesList.get(i).getId())) {
                        position = j;
                        break;
                    }
                }
                nowSoil += vagetablesC[position];
                nowSoil += "|";
                nowSoil += soilList.get(i).getName();
                if (vagetablesList.get(i).grow == vagetablesList.get(i).top && vagetablesList.get(i).top != 0) {
                    nowSoil += "|作物已成熟]";
                } else if (vagetablesList.get(i).top == 0) {
                    nowSoil += "|无作物]";
                } else {
                    nowSoil += "|生长进度:" + vagetablesList.get(i).getGrow() + "/" + vagetablesList.get(i).getTop();
                    nowSoil += "]";
                }

                screen.addWords(halfWeight + 20 * ((i % nextLineNeedNumber) - 1), line, nowSoil);
                if ((i + 1) % nextLineNeedNumber == 0 && (i + 1) != vagetablesList.size()) {
                    line++;
                }
            }
        } else {
            String topWords = "";
            topWords += "共有" + soilList.size() + "块农田，";
            int growed = soilList.stream()
                    .filter(soil -> !vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE")).toList().size();
            if (growed == soilList.size()) {
                topWords += "所有农田已种植作物，";
            } else if (growed != 0) {
                topWords += growed + " 块农田已种植作物，" + (soilList.size() - growed) + " 块农田无作物，";
            } else {
                topWords += soilList.size() + " 块农田无作物，";
            }
            int finished = soilList.stream()
                    .filter(soil -> vagetablesList.get(soilList.indexOf(soil)).grow == vagetablesList
                            .get(soilList.indexOf(soil)).top
                            && !vagetablesList.get(soilList.indexOf(soil)).id.equals("NONE"))
                    .toList().size();
            if (finished == soilList.size()) {
                topWords += "所有作物已成熟";
            } else if (finished != 0) {
                topWords += finished + " 块农田的作物已成熟";
            } else {
                topWords += "无作物成熟";
            }
            screen.addWords(halfWeight - 13, 5, topWords, "l");
            if (vagetablesList.stream().anyMatch(vag -> !vag.id.equals("NONE"))) {
                screen.addWords(halfWeight - 13, 6, "其中有", "l");
            }
            int nowline = 6;
            for (String vag : vagetablesList.stream().map(Vagetables::getId).distinct().filter(va -> !va.equals("NONE"))
                    .toList()) {
                topWords = "";
                topWords += soilList.stream()
                        .filter(soil -> vagetablesList.get(soilList.indexOf(soil)).id.equals(vag)).toList().size()
                        + " 块农田种植 " + vagetablesC[Arrays.asList(vagetables).indexOf(vag)];
                topWords += "||生长进度："
                        + vagetablesList.stream().filter(v -> v.id.equals(vag))
                                .mapToInt(Vagetables::getGrow).min().getAsInt()
                        + "-"
                        + vagetablesList.stream().filter(v -> v.id.equals(vag))
                                .mapToInt(Vagetables::getGrow).max().getAsInt()
                        + " / " + new Vagetables(vag).top;
                screen.addWords(halfWeight - 9, nowline, topWords, "l");
                nowline++;
            }
            screen.addWords(halfWeight - 13, nowline, "其中有", "l");
            for (String soill : soilList.stream().map(Soil::getId).distinct().filter(s -> !s.equals("NONE")).toList()) {
                screen.addWords(halfWeight - 9, nowline,
                        soilList.stream().filter(s -> s.getId().equals(soill)).toList().size() + " 块农田土壤为"
                                + soilC[Arrays.asList(soil).indexOf(soill)],
                        "l");
                nowline++;
            }
        }
    }

    static String getEnterString(String body, String title) {
        String enter;
        while (true) {
            enter = screen.getEnterString(body, title);
            if (!enter.equals("")
                    && !farmerList.stream().map(Employee::getName).anyMatch(enter::equals)
                    && !cookerList.stream().map(Cooker::getName).anyMatch(enter::equals)
                    && !buyerList.stream().map(Buyer::getName).anyMatch(enter::equals)
                    && !sellerList.stream().map(Seller::getName).anyMatch(enter::equals)) {
                break;
            } else {
                showAWordForSomeSeconds("输入为空或重名", 0.5);
            }
        }
        return enter;
    }

    static List<String> getEnterString(String last) {
        List<String> ret = new ArrayList<>();
        String enter = screen.getFunctionReturn();
        String num[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        for (String n : num) {
            if (enter.equals(n)) {
                ret.add(last + n);
                ret.add("F");
                return ret;
            }
        }
        if (enter.equals("backspace") && !last.equals("")) {
            ret.add(last.substring(0, last.length() - 1));
            ret.add("F");
            return ret;
        }
        if (enter.equals("Enter") && !last.equals("") && !last.equals("-")) {
            ret.add(last);
            ret.add("T");
            return ret;
        }
        ret.add(last);
        ret.add("F");
        return ret;
    }

    static List<String> getEnterNumber(String last) {
        List<String> ret = new ArrayList<>();
        String enter = screen.getFunctionReturn();
        String num[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-" };
        for (String n : num) {
            if (enter.equals(n)) {
                ret.add(last + n);
                ret.add("F");
                return ret;
            }
        }
        if (enter.equals("backspace") && !last.equals("")) {
            ret.add(last.substring(0, last.length() - 1));
            ret.add("F");
            return ret;
        }
        if (enter.equals("Enter") && !last.equals("") && !last.equals("-")) {
            ret.add(last);
            ret.add("T");
            return ret;
        }
        ret.add(last);
        ret.add("F");
        return ret;
    }

    static void setScreen() {
        screen.setWhenKeyIsPressedShouldDo(f -> {
            if (f == KeyEvent.VK_A) {
                return "A";
            }
            if (f == KeyEvent.VK_B) {
                return "B";
            }
            if (f == KeyEvent.VK_C) {
                return "C";
            }
            if (f == KeyEvent.VK_D) {
                return "D";
            }
            if (f == KeyEvent.VK_E) {
                return "E";
            }
            if (f == KeyEvent.VK_F) {
                return "F";
            }
            if (f == KeyEvent.VK_G) {
                return "G";
            }
            if (f == KeyEvent.VK_H) {
                return "H";
            }
            if (f == KeyEvent.VK_I) {
                return "I";
            }
            if (f == KeyEvent.VK_J) {
                return "J";
            }
            if (f == KeyEvent.VK_K) {
                return "K";
            }
            if (f == KeyEvent.VK_L) {
                return "L";
            }
            if (f == KeyEvent.VK_M) {
                return "M";
            }
            if (f == KeyEvent.VK_N) {
                return "N";
            }
            if (f == KeyEvent.VK_O) {
                return "O";
            }
            if (f == KeyEvent.VK_P) {
                return "P";
            }
            if (f == KeyEvent.VK_Q) {
                return "Q";
            }
            if (f == KeyEvent.VK_R) {
                return "R";
            }
            if (f == KeyEvent.VK_S) {
                return "S";
            }
            if (f == KeyEvent.VK_T) {
                return "T";
            }
            if (f == KeyEvent.VK_U) {
                return "U";
            }
            if (f == KeyEvent.VK_V) {
                return "V";
            }
            if (f == KeyEvent.VK_W) {
                return "W";
            }
            if (f == KeyEvent.VK_X) {
                return "X";
            }
            if (f == KeyEvent.VK_Y) {
                return "Y";
            }
            if (f == KeyEvent.VK_Z) {
                return "Z";
            }
            if (f == KeyEvent.VK_ENTER) {
                return "Enter";
            }
            if (f == KeyEvent.VK_1) {
                return "1";
            }
            if (f == KeyEvent.VK_2) {
                return "2";
            }
            if (f == KeyEvent.VK_3) {
                return "3";
            }
            if (f == KeyEvent.VK_4) {
                return "4";
            }
            if (f == KeyEvent.VK_5) {
                return "5";
            }
            if (f == KeyEvent.VK_6) {
                return "6";
            }
            if (f == KeyEvent.VK_7) {
                return "7";
            }
            if (f == KeyEvent.VK_8) {
                return "8";
            }
            if (f == KeyEvent.VK_9) {
                return "9";
            }
            if (f == KeyEvent.VK_0) {
                return "0";
            }
            if (f == KeyEvent.VK_BACK_SPACE) {
                return "backspace";
            }
            if (f == KeyEvent.VK_MINUS) {
                return "-";
            }
            if (f == KeyEvent.VK_ESCAPE) {
                return "Escape";
            }
            if (f == KeyEvent.VK_DELETE) {
                return "Delete";
            }
            return "";
        });
    }

    static void showAWordForSomeSeconds(String word, double time) {
        long lastu = System.currentTimeMillis();
        while (System.currentTimeMillis() - lastu < 1000 * time) {
            screen.addWords(halfWeight, halfHeight, word);
            screen.showScreen();
        }
    }

    static void showAWordForSomeSeconds(String word, double time, String color) {
        long lastu = System.currentTimeMillis();
        while (System.currentTimeMillis() - lastu < 1000 * time) {
            screen.addWords(color, halfWeight, halfHeight, word);
            screen.showScreen();
        }
    }

    static void getAchievement(String type) {
        boolean already = true;
        for (String string : achievementList.stream().map(Achievement::getType).toList()) {
            if (string.equals(type)) {
                already = false;
            }
        }
        if (already) {
            Achievement achievement = new Achievement(type, System.currentTimeMillis());
            achievementList.add(achievement);
            showAWordForSomeSeconds("恭喜获得成就 " + achievementsC[Arrays.asList(achievements).indexOf(type)], 1);
        }
    }
}