package engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

public class EngineWindow {
    //Переменные окна: ширина, высота, название, и айди
    private int width;
    private int height;
    private String title;
    public long id;

    //Подключение нужной фигни
    public IntBuffer bufferedWidth;
    public IntBuffer bufferedHeight;
    private GLFWVidMode videoMode;

    //Конструктор окна
    public EngineWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //Создание окна
    public void create() {

        //Проверка на наличие библиотек GLFW
        if (!GLFW.glfwInit()){
            System.err.println("GLFW libraries are not initializated.");
            System.exit(-1);
        }

        //Создание окна
        this.id=GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);

        //Закрытие с ошибкой, если создание окна возвращает 0
        if(this.id==0){
            System.err.println("There was a problem in creating window.");
            System.exit(-1);
        }
        //Проталкивание кадров
        try (MemoryStack mem = MemoryStack.stackPush()) {
            this.bufferedWidth= BufferUtils.createIntBuffer(1);
            this.bufferedHeight= BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.id, this.bufferedWidth,this.bufferedHeight);
        }
        catch(Exception e){

        }

        //Задание параметров окну
        GLFW.glfwSetWindowTitle(this.id,this.title);
        GLFW.glfwSetWindowSize(this.id,this.width,this.height);
        GLFW.glfwSetWindowAspectRatio(this.id,this.width,this.height);

    }

    //Обновление окна
    public void update() {

    }

    //Закрытие окна
    public void destroy() {

    }

    //Геттеры, и сеттеры параметров окна
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
