import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingData {
    @JsonProperty("FPS")
    private int fps;

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getFps() {
        return fps;
    }
}
