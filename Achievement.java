public class Achievement {
    String type;
    long time;

    public Achievement(String type,long time){
        this.type = type;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public long getTime() {
        return time;
    }
}
