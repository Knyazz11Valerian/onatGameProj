package engine;

import engine.controls.Keyboard;
import engine.controls.Mouse;
import org.lwjgl.glfw.GLFW;

public class Engine {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    public static final String TITLE = "Engine 0.0.1 alpha";
    private EngineWindow engineWindow;
    public static double mouseXBuf;
    public static double mouseYBuf;


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
            if (Mouse.buttonPreesed(0)||Mouse.buttonPreesed(1)){
                System.out.println("Пиу");
            }
            if(Mouse.mouseX<mouseXBuf){
                System.out.println("Летим влево");
            }
            if(Mouse.mouseX>mouseXBuf){
                System.out.println("Летим вправо");
            }
            if(Mouse.mouseY<mouseYBuf){
                System.out.println("Летим вниз");
            }
            if(Mouse.mouseY>mouseYBuf){
                System.out.println("Летим вверх");
            }

            System.out.println(Mouse.mouseX);

            Keyboard.handleKeyboardInput();
            Mouse.handleMouseInput();
            this.engineWindow.update();
            //render pass (рендерится пока не закроешь окно)
            mouseXBuf=Mouse.mouseX;
            mouseYBuf=Mouse.mouseY;
        }
        this.engineWindow.destroy();
    }

    public EngineWindow getEngineWindow() {
        return this.engineWindow;
    }
}
