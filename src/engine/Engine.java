package engine;

import org.lwjgl.glfw.GLFW;

public class Engine {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    public static final String TITLE = "Engine 0.0.1 alpha";
    private EngineWindow engineWindow;


    public void run() {
        this.init();
    }

    public void init() {
        this.engineWindow=new EngineWindow(WIDTH,HEIGHT,TITLE);
        this.engineWindow.create();
        this.update();

    }


    public void update() {
        while (!this.engineWindow.isCloseRequest()){
            if (Keyboard.keyPreesed(GLFW.GLFW_KEY_A)){
                System.out.println("A");
            }
            Keyboard.handleKeyboardInput();
            this.engineWindow.update();
            //render pass (рендерится пока не закроешь окно)
        }
        this.engineWindow.destroy();
    }

    public EngineWindow getEngineWindow() {
        return this.engineWindow;
    }
}
