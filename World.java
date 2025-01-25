import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Function;

public class World {
    List<List<InnerWorld>> map = new ArrayList<>();
    int Weight, Height;
    Posision screenPosision;
    Posision playerPosision;
    Function<InnerWorld, InnerWorld> makeFunction;
    Function<InnerWorld, SimpleEntry<String, String>> updateFunction;
    boolean playerInMiddle = true;
    static FastNoiseLite noise;
    long lastFollow = System.currentTimeMillis();
    int seed;
    static List<InnerWorld> specialWords = new ArrayList<>();

    public World(int Weight, int Height) {
        this(Weight, Height, new Posision(0, 0), new Posision(0, 0), FastNoiseLite.NoiseType.Perlin, 0, w -> {
            return new InnerWorld(w.x, w.y, "", "#CCCCCC");
        });
    }

    public World(int Weight, int Height, Posision screenPosision, Posision playerPosision, int seed) {
        this(Weight, Height, screenPosision, playerPosision, FastNoiseLite.NoiseType.Perlin, seed, w -> {
            return new InnerWorld(w.x, w.y, "", "#CCCCCC");
        });
    }

    public World(int Weight, int Height, int seed, Function<InnerWorld, InnerWorld> function) {
        this(Weight, Height, new Posision(0, 0), new Posision(0, 0), FastNoiseLite.NoiseType.Perlin, seed, function);
    }

    public World(int Weight, int Height, Posision screenPosision, Posision playerPosision,
            FastNoiseLite.NoiseType noiseType, int seed, Function<InnerWorld, InnerWorld> function) {
        this.Weight = Weight;
        this.Height = Height;
        this.screenPosision = screenPosision;
        this.playerPosision = playerPosision;
        makeFunction = function;
        this.seed = seed;
        noise = new FastNoiseLite(seed);
        noise.SetNoiseType(noiseType);
        for (int i = 0; i < Weight; i++) {
            List<InnerWorld> line = new ArrayList<>();
            for (int j = 0; j < Height; j++) {
                InnerWorld block = new InnerWorld(i, j, "", "#CCCCCC");
                block.setSelf(makeFunction.apply(block));
                line.add(block);
            }
            map.add(line);
        }
    }

    public void allRefresh() {
        for (int i = screenPosision.x; i < screenPosision.x + Weight; i++) {
            for (int j = screenPosision.y; j < screenPosision.y + Height; j++) {
                map.get(getMove(i, Weight)).get(getMove(j, Height)).x = i;
                map.get(getMove(i, Weight)).get(getMove(j, Height)).y = j;
                map.get(getMove(i, Weight)).get(getMove(j, Height))
                        .setSelf(makeFunction.apply(map.get(getMove(i, Weight)).get(getMove(j, Height))));
            }
        }
    }

    public void addSpecial(Posision p, String words) {
        addSpecial(p, words, "#CCCCCC");
    }

    public void addSpecial(Posision p, String words, String color) {
        if (p.x >= screenPosision.x && p.x <= screenPosision.x + Weight && p.y >= screenPosision.y
                && p.y <= screenPosision.y + Height) {
            specialWords.add(new InnerWorld(p.x, p.y, words, color));
        }
    }

    public void setNoiseType(FastNoiseLite.NoiseType noiseType) {
        noise.SetNoiseType(noiseType);
    }

    public void setSeed(int seed) {
        this.seed = seed;
        noise.SetSeed(seed);
    }

    double getNoise(int x, int y, double min, double max, float frequency) {
        noise.SetFrequency(frequency);
        noise.SetSeed(seed);
        double getN = noise.GetNoise(x, y);
        double normalizedNoise = (getN + 1.0f) / 2.0f;
        double scaledNoise = normalizedNoise * (max - min) + min;
        return scaledNoise;
    }

    double getNoise(int x, int y, double min, double max, float frequency, int seedD) {
        noise.SetFrequency(frequency);
        noise.SetSeed(seedD);
        double getN = noise.GetNoise(x, y);
        noise.SetSeed(seed);
        double normalizedNoise = (getN + 1.0f) / 2.0f;
        double scaledNoise = normalizedNoise * (max - min) + min;
        return scaledNoise;
    }

    public void setPlayer(int x, int y) {
        playerPosision = createPosision(x, y);
        followPlayer();
    }

    public void movePlayer(int x, int y) {
        playerPosision = createPosision(playerPosision.x + x, playerPosision.y + y);
        followPlayer();
    }

    public void setPlayerInMiddle(boolean playerInMiddle) {
        this.playerInMiddle = playerInMiddle;
        followPlayer();
    }

    public void followPlayer() {
        if (System.currentTimeMillis() - lastFollow > 100) {
            screenPosision = createPosision((playerPosision.x + (screenPosision.x - Weight / 2)) / 2,
                    (playerPosision.y + (screenPosision.y - Height / 2)) / 2);
            lastFollow = System.currentTimeMillis();
        }
    }

    public int getMove(int a, int b) {
        int re = a % b;
        if (re < 0) {
            re += b;
        }
        return re;
    }

    public void moveScreen(int x, int y) {
        if (!playerInMiddle) {
            screenPosision = createPosision(screenPosision.x + x, screenPosision.y + y);
        } else {
            followPlayer();
        }
    }

    public void toScreen(NewUnicodeScreen screen) {
        // screen.clear();
        for (int i = 0; i < screen.getWidthF(); i++) {
            for (int j = 0; j < screen.getHeightF(); j++) {
                InnerWorld innerW = map
                        .get(getMove((getMove(screenPosision.x, Weight) + (Weight - screen.getWidthF()) / 2 + i),
                                Weight))
                        .get(getMove((getMove(screenPosision.y, Height) + (Height - screen.getHeightF()) / 2 + j),
                                Height));
                screen.addWords(innerW.color, i, j, innerW.type);
            }
        }
        specialWords.stream()
                .forEach(inn -> screen.addWords(inn.x - ((Weight - screen.getWidthF()) / 2 + screenPosision.x),
                        inn.y - ((Height - screen.getHeightF()) / 2 + screenPosision.y), inn.type, "l"));
        specialWords.clear();
    }

    public void update() {
        if (playerInMiddle) {
            followPlayer();
        }
        for (int i = screenPosision.x; i < screenPosision.x + Weight; i++) {
            for (int j = screenPosision.y; j < screenPosision.y + Height; j++) {
                if (map.get(getMove(i, Weight)).get(getMove(j, Height)).x != i
                        || map.get(getMove(i, Weight)).get(getMove(j, Height)).y != j) {
                    map.get(getMove(i, Weight)).get(getMove(j, Height)).x = i;
                    map.get(getMove(i, Weight)).get(getMove(j, Height)).y = j;
                    map.get(getMove(i, Weight)).get(getMove(j, Height))
                            .setSelf(makeFunction.apply(map.get(getMove(i, Weight)).get(getMove(j, Height))));
                } else {
                    map.get(getMove(i, Weight)).get(getMove(j, Height))
                            .setType(updateFunction.apply(map.get(getMove(i, Weight)).get(getMove(j, Height))));
                }
            }
        }
    }

    public void update(Posision posision) {
        map.get(posision.x).get(posision.y).setType(updateFunction.apply(map.get(posision.x).get(posision.y)));
    }

    public void update(int x, int y) {
        update(createPosision(x, y));
    }

    public void update(int x, int y, String type) {
    }

    public void setBlockData(Function<InnerWorld, InnerWorld> function) {
        makeFunction = function;
    }

    public void setUpdateData(Function<InnerWorld, SimpleEntry<String, String>> function) {
        updateFunction = function;
    }

    public static Posision createPosision(int x, int y) {
        return new Posision(x, y);
    }

    public static class Posision {
        int x, y;

        public Posision(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class InnerWorld {
        int x;
        int y;
        String type;
        Map<String, Double> data = new HashMap<>();
        String color = "#CCCCCC";

        public void setColor(String color) {
            this.color = color;
        }

        public void setSelf(InnerWorld i) {
            this.x = i.x;
            this.y = i.y;
            this.type = i.type;
            this.data = i.data;
        }

        public InnerWorld(int x, int y, String type, String color) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setType(SimpleEntry<String, String> s) {
            this.type = s.getKey();
            this.color = s.getValue();

        }

        public String getType() {
            return type;
        }
    }
}
