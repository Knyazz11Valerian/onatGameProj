package engine.controls;

import engine.EngineWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class Mouse {

    //Мыш


    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public static double mouseX;
    public static double mouseY;

    //Возврашает true в том случае, если клавиша нажата/удержена


    public static boolean keyDown(int buttonId) {

        return GLFW.glfwGetMouseButton(EngineWindow.getWindow().id, buttonId) == 1;
    }

    public static void setMouseCallBack(long id){
        setCursorPositionCallBack(id);
    }
    public static void setCursorPositionCallBack(long id){
        GLFW.glfwSetCursorPosCallback(id, new GLFWCursorPosCallbackI() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX=xpos;
                mouseY=ypos;
            }
        });

    }

    //Возврашает true в том случае, если клавиша нажата, иначе возврашет false

    public static boolean buttonPreesed(int buttonId) {
        return keyDown(buttonId) && !buttons[buttonId];
    }

    //Возврашает true в том случае, если клавиша отпушена, иначе возврашет false

    public static boolean buttonReleased(int buttonId) {
        return !keyDown(buttonId) && buttons[buttonId];
    }

    //Удерживает весь ввод с клавиатуры

    public static void handleMouseInput() {
        //выполняет это действие для каждой доступной клавишы
        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            buttons[i] = keyDown(i);
        }
    }
}
