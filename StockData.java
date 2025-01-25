import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockData {
    @JsonProperty("stock")
    private List<InnerStockData> stock;

    public void setStock(List<InnerStockData> stock) {
        this.stock = stock;
    }

    public List<InnerStockData> getStock() {
        return stock;
    }

    public static class InnerStockData {
        @JsonProperty("name")
        private String name;
        @JsonProperty("price")
        private long price;
        @JsonProperty("have")
        private long have;
        @JsonProperty("seed")
        private int seed;

        public void setHave(int have) {
            this.have = have;
        }

        public long getHave() {
            return have;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public long getPrice() {
            return price;
        }

        public void setSeed(int seed) {
            this.seed = seed;
        }

        public int getSeed() {
            return seed;
        }
    }
}
