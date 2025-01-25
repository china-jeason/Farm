import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class NewUnicodeScreen extends Canvas implements Runnable, KeyListener {
    private boolean running = false;
    private Thread thread;
    private double width, height;
    private int widthF, heightF;
    private int fps = 60;
    int key;
    boolean keyPressed;
    String functionReturn = "";
    Function<Integer, String> function;
    Boolean frame = true;
    long LastUpdateTime = 0;
    boolean canShow = false;
    String currentDirectory = System.getProperty("user.dir");
    // 使用 ArrayList 存储文本矩阵，元素为 String 类型
    private CopyOnWriteArrayList<List<String>> screen = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<List<String>> color = new CopyOnWriteArrayList<>();

    static final String WHITE = "#FFFFFF";
    static final String BLACK = "#000000";
    static final String RED = "#FF0000";
    static final String GREEN = "#00FF00";
    static final String BLUE = "#0000FF";
    static final String YELLOW = "#FFFF00";
    static final String CYAN = "#00FFFF";
    static final String MAGENTA = "#FF00FF";
    static final String ORANGE = "#FFA500";
    static final String PURPLE = "#800080";
    static final String BROWN = "#3B3125";
    static final String GRAY = "#808080";
    static final String DARK_GRAY = "#A9A9A9";
    static final String LIGHT_GRAY = "#D3D3D3";
    static final String LIGHT_BLUE = "#ADD8E6";
    static final String LIGHT_GREEN = "#90EE90";
    static final String LIGHT_RED = "#FFC0CB";
    static final String LIGHT_YELLOW = "#FFFFE0";
    static final String LIGHT_CYAN = "#E0FFFF";
    static final String LIGHT_MAGENTA = "#FFB6C1";
    static final String LIGHT_ORANGE = "#FFA07A";
    static final String DARK_BLUE = "#00008B";
    static final String DARK_GREEN = "#006400";
    static final String DARK_RED = "#8B0000";
    static final String DARK_YELLOW = "#FFD700";
    static final String DARK_CYAN = "#008B8B";
    static final String DARK_MAGENTA = "#8B008B";
    static final String PINK = "#FFC0CB";
    static final String NORMAL = "#CCCCCC";

    public NewUnicodeScreen() {
        this("New Unicode Screen", 40, 25, 60);
    }

    public NewUnicodeScreen(String title, int width, int height, int fps) {
        this.widthF = width;
        this.heightF = height;
        this.width = width * 19.25;
        this.height = height * 15.7 * 1.2 + 10;
        this.fps = fps;

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(this);
        // 设置 Canvas 的初始大小
        setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 添加键盘监听器
        frame.addKeyListener(this);

        // 初始化文本矩阵
        initscreen();

        start();

        // 定时器，用于更新文字矩阵
        // Timer timer = new Timer(1000 / fps, new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // updatescreen();
        // }
        // });
        // timer.start();
    }
    
    public void changeColor(int x, int y, String color) {
        this.color.get(x).set(y, color);
    }

    public void setFrame(Boolean frame) {
        this.frame = frame;
    }

    private void addFrame() {
        if (frame) {
            for (int i = 0; i < heightF; i++) {
                for (int j = 0; j < widthF; j++) {
                    if (i == 0 && j == 0) {
                        screen.get(i).set(j, "┌─");
                    } else if (i == 0 && j == widthF - 1) {
                        screen.get(i).set(j, "─┐");
                    } else if (i == heightF - 1 && j == 0) {
                        screen.get(i).set(j, "└─");
                    } else if (i == heightF - 1 && j == widthF - 1) {
                        screen.get(i).set(j, "─┘");
                    } else if (i == 0 || i == heightF - 1) {
                        screen.get(i).set(j, "──");
                    } else if (j == 0) {
                        screen.get(i).set(j, "│ ");
                    } else if (j == widthF - 1) {
                        screen.get(i).set(j, " │");
                    }
                }
            }
        }
    }

    public void setWhenKeyIsPressedShouldDo(Function<Integer, String> function) {
        this.function = function;
    }

    public void whenKeyIsPressedShouldDo() {
        functionReturn = function.apply(key);
    }

    public int findSuitableFPS() {
        setWhenKeyIsPressedShouldDo(key -> {
            if (key == KeyEvent.VK_W) {
                if (fps != -1) {
                    fps++;
                } else {
                    fps += 2;
                }
            } else if (key == KeyEvent.VK_S) {
                if (fps > 1) {
                    fps--;
                } else if (fps == 1) {
                    fps = -1;
                }
            } else if (key == KeyEvent.VK_ESCAPE) {
                return "N";
            }
            return "";
        });
        while (true) {
            // clear();
            addWords(widthF / 2, heightF / 2 - 2, "请按下 W/S 键调整刷新频率");
            addWords("#FF0000", widthF / 2, heightF / 2 - 1, "按 ESC 键退出");
            addWords(widthF / 2, heightF / 2, "当前FPS");
            if (fps > 0) {
                addWords(widthF / 2, heightF / 2 + 1, "[S]< " + fps + " >[W]");
            } else {
                addWords(widthF / 2, heightF / 2 + 1, "[S]< ∞ >[W]");
            }
            if (getFunctionReturn() == "N") {
                break;
            }
            showScreen();
        }
        return fps;
    }

    public void setColor(String color, int x, int y) {
        screen.get(y).set(x, color);
    }

    public boolean isPressed() {
        return keyPressed;
    }

    public boolean isPressed(int code) {
        if (key == code) {
            return true;
        }
        return false;
    }

    public int getWidthF() {
        return widthF;
    }

    public int getHeightF() {
        return heightF;
    }

    public String getFunctionReturn() {
        String func = functionReturn;
        functionReturn = "";
        return func;
    }

    public void addWords(String color, int x, int y, String s) {
        addWords(x, y, s, "m", color);
    }

    public void addWords(int x, int y, String s, String model) {
        addWords(x, y, s, model, "#CCCCCC");
    }

    public void addWords(int x, int y, String s, String model, String color) {
        int biginX = 0;
        int size = getSize(s);
        switch (model) {
            case "m":
                biginX = x - size / 2;
                break;
            case "l":
                biginX = x;
                break;
            case "r":
                biginX = x - s.length();
                break;
            default:
                biginX = x - size / 2;
                break;
        }
        String h = "";
        int lastX = biginX;
        for (int i = 0; i < s.length(); i++) {
            if (isChineseCharacter(s.charAt(i))) {
                if (h == "") {
                    if (inScreen(lastX, y)) {
                        change(lastX, y, s.charAt(i) + "", color);
                    }
                    lastX++;
                } else {
                    if (inScreen(lastX, y)) {
                        change(lastX, y, h + " ", color);
                    }
                    h = "";
                    lastX++;
                    if (inScreen(lastX, y)) {
                        change(lastX, y, s.charAt(i) + "", color);
                    }
                    lastX++;
                }
            } else if (h != "") {
                h = h + s.charAt(i);
                if (inScreen(lastX, y)) {
                    change(lastX, y, h, color);
                }
                lastX++;
                h = "";
            } else {
                h = h + s.charAt(i);
            }

        }
        if (h != "") {
            if (inScreen(lastX, y)) {
                change(lastX, y, h + " ", color);
            }
        }
    }

    public boolean inScreen(int x, int y) {
        boolean in = true;
        if (!frame && (x < 0 || x > widthF - 1 || y < 0 || y > heightF - 1)) {
            in = false;
        } else if (frame && (x < 1 || x > widthF - 2 || y < 1 || y > heightF - 2)) {
            in = false;
        }
        return in;
    }

    public void addWords(int x, int y, String s) {
        addWords(x, y, s, "m");
    }

    public static boolean isChineseCharacter(char ch) {
        if (ch >= '\uFF01' && ch <= '\uFF5E')
            return true;
        if (ch >= '\uFF61' && ch <= '\uFFDC')
            return true;

        if (ch >= '\u4E00' && ch <= '\u9FFF')
            return true;
        if (ch >= '\u3040' && ch <= '\u309F')
            return true;
        if (ch >= '\u30A0' && ch <= '\u30FF')
            return true;
        char more[] = { '￥', '、', '。', '！' };
        for (char c : more) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public int getSize(String s) {
        int size = 0;
        String h = "";
        for (int i = 0; i < s.length(); i++) {
            if (isChineseCharacter(s.charAt(i))) {
                size++;
            } else if (h != "") {
                size++;
                h = "";
            } else {
                h = s.charAt(i) + "";
            }
        }
        if (h != "") {
            size++;
        }
        return size;
    }

    // private void clear(int x, int y) {
    // screen.get(y).set(x, " ");
    // color.get(y).set(x, "#CCCCCC");
    // }

    private void clear() {
        // if (System.currentTimeMillis() - LastUpdateTime < 1000 / fps) {
        // return;
        // }
        // screen.stream().forEach(l -> l.stream().forEach(s -> s = " "));
        // color.stream().forEach(l -> l.stream().forEach(s -> s = "#CCCCCC"));
        // for (int i = 0; i < heightF; i++) {
        // for (int j = 0; j < widthF; j++) {
        // screen.get(i).set(j, " ");
        // color.get(i).set(j, "#CCCCCC");
        // }
        // }
        for (int i = 0; i < heightF; i++) {
            for (int j = 0; j < widthF; j++) {
                screen.get(i).set(j, "  ");
                color.get(i).set(j, "#CCCCCC");
            }
        }
    }

    public void change(int x, int y, String s) {
        screen.get(y).set(x, s);
    }

    public void change(int x, int y, String s, String color) {
        screen.get(y).set(x, s);
        this.color.get(y).set(x, color);
    }

    public String getEnterString() {
        return getEnterString("请输入内容:", "输入框");
    }

    public String getEnterString(String word, String title) {
        String input = JOptionPane.showInputDialog(null, word, title, JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }

    private void updatescreen() {
        addFrame();
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    // 初始化 ArrayList
    private void initscreen() {
        for (int i = 0; i < heightF; i++) {
            List<String> row = new ArrayList<>();
            List<String> clo = new ArrayList<>();
            for (int j = 0; j < widthF; j++) {
                row.add("  ");
                clo.add("#CCCCCC");
            }
            screen.add(row);
            color.add(clo);
        }
    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void showScreen() {
        canShow = true;
    }

    public void run() {
        while (running) {
            if (canShow) {
                updatescreen();
                render();
                canShow = false;
                clear();
            }
            try {
                if (fps > 0) {
                    Thread.sleep(1000 / fps);
                } else {
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        // 屏幕抗锯齿
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 绘制背景
        g2d.setColor(Color.decode("#0C0C0C"));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 绘制文字矩阵
        int x = 5; // 从左上角开始的 x 坐标
        int y = 18; // 从左上角开始的 y 坐标
        // Font cascadingMonoFont = loadFont(); // 加载自定义字体
        Font primaryFont = null;
        Font fallbackFont = null;
        try {
            primaryFont = Font.createFont(Font.TRUETYPE_FONT, new File(currentDirectory+"\\CascadiaMono.ttf")).deriveFont(Font.PLAIN, 16);
            fallbackFont = Font.createFont(Font.TRUETYPE_FONT, new File(currentDirectory+"\\msyh.ttc"))
                    .deriveFont(Font.PLAIN, 16); // 备用字体
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.setFont(primaryFont);

        for (int i = 0; i < heightF; i++) {
            List<String> row = screen.get(i);
            for (int j = 0; j < widthF; j++) {
                String s = row.get(j);
                g2d.setColor(Color.decode(color.get(i).get(j)));
                if (canDisplayString(g2d, s, primaryFont)) {
                    g2d.setFont(primaryFont);
                } else {
                    g2d.setFont(fallbackFont);
                }
                g2d.drawString(s, x, y);
                x += 19;
            }
            x = 5;
            y += 16.0 * 1.2; // 调整 y 坐标以避免文字重叠
        }

        g2d.dispose();
        bs.show();
    }

    // 检查字体是否可以显示字符串
    private boolean canDisplayString(Graphics2D g2d, String str, Font font) {
        for (char c : str.toCharArray()) {
            if (!font.canDisplay(c)) {
                return false;
            }
        }
        return true;
    }

    // 重写 getPreferredSize 方法，确保窗口大小调整时 Canvas 能正确调整大小
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) width, (int) height);
    }

    // 实现 KeyListener 接口的方法
    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        whenKeyIsPressedShouldDo();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 处理按键释放事件，可以添加更多逻辑
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 处理按键输入字符事件，可以添加更多逻辑
    }

    // public static void main(String[] args) {
    // NewUnicodeScreen u = new NewUnicodeScreen();
    // u.findSuitableFPS();
    // }
}