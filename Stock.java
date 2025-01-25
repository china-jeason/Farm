public class Stock {
    String name;
    long price;
    long have;
    int seed;

    static FastNoiseLite noise;

    public Stock(String name, long price, long have, int seed) {
        this.name = name;
        this.price = price;
        this.have = have;
        this.seed = seed;
        noise = new FastNoiseLite();
        noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
    }

    public long getHave() {
        return have;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getSeed() {
        return seed;
    }

    public void buy(int amount) {
        have += amount;
    }

    public long sell(int amount) {
        have -= amount;
        return price * amount;
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

    public int getPrice(int day) {
        double a1 = getNoise(day, day, -50000, 150000, 0.01f, seed);
        double a2 = getNoise(day * 2, day * 3, 0, 80000, 0.1f, (int) Math.log(seed));
        double a3 = getNoise(day * 7, day, -100000, 50000, 0.03f, (int) Math.pow(seed, 0.5));
        double a4 = getNoise(day, day * 4, -30000, 40000, 0.2f, (int) Math.pow(seed, day));
        double a5 = getNoise(day * 2, day + 98, -50000, 100000, 0.1f, seed);
        int pri = (int) (a1 * 0.5 + a2 * 0.1 + a3 * 0.3 + a4 * 0.1 + a5 * 0.3);
        if (pri < 0) {
            pri = 0;
        }
        return pri;
    }
}
