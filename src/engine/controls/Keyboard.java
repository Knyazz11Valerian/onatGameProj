package engine.controls;

import engine.EngineWindow;
import org.lwjgl.glfw.GLFW;

public class Keyboard {
    //Массив всех доступных клавиш в ACSII
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    //Возврашает true в том случае, если клавиша нажата/удержена


    public static boolean keyDown(int keyId) {

        return GLFW.glfwGetKey(EngineWindow.getWindow().id, keyId) == 1;
    }

    //Возврашает true в том случае, если клавиша нажата, иначе возврашет false

    public static boolean keyPreesed(int keyId) {
        return keyDown(keyId) && !keys[keyId];
    }

    //Возврашает true в том случае, если клавиша отпушена, иначе возврашет false

    public static boolean keyReleased(int keyId) {
        return !keyDown(keyId) && keys[keyId];
    }

    //Удерживает весь ввод с клавиатуры

    public static void handleKeyboardInput() {
        //выполняет это действие для каждой доступной клавишы
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            keys[i] = keyDown(i);
        }
    }
}

