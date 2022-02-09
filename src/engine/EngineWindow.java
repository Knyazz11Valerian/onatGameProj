package engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;


import java.nio.IntBuffer;

public class EngineWindow {
    //Переменные окна: ширина, высота, название, и айди
    private int width;
    private int height;
    private String title;
    public long id;
    public static EngineWindow instance;

    //Подключение нужной фигни
    public IntBuffer bufferedWidth;
    public IntBuffer bufferedHeight;
    private GLFWVidMode videoMode;

    //Конструктор окна
    public EngineWindow(int width, int height, String title) {
        instance=this;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //Создание окна
    public void create() {

        //Проверка на наличие библиотек GLFW
        if (!GLFW.glfwInit()) {
            System.err.println("GLFW libraries are not initializated.");
            System.exit(-1);
        }

        //Создание окна
        this.id = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);

        //Закрытие с ошибкой, если создание окна возвращает 0
        if (this.id == 0) {
            System.err.println("There was a problem in creating window.");
            System.exit(-1);
        }
        //Проталкивание кадров
        try (MemoryStack mem = MemoryStack.stackPush()) {
            this.bufferedWidth = BufferUtils.createIntBuffer(1);
            this.bufferedHeight = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.id, this.bufferedWidth, this.bufferedHeight);
        } catch (Exception e) {

        }

        this.videoMode=GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        //Задание параметров окну
        GLFW.glfwSetWindowTitle(this.id, this.title);
        GLFW.glfwSetWindowSize(this.id, this.width, this.height);
        GLFW.glfwSetWindowAspectRatio(this.id, this.width, this.height);
        GLFW.glfwSetWindowPos(this.id, ((this.videoMode.width() - this.bufferedWidth.get(0)) / 2),
                                  (this.videoMode.height() - this.bufferedHeight.get(0)) / 2);
        GLFW.glfwSetWindowSizeLimits(this.id, this.width, this.height,3840, 2160);

        GLFW.glfwMakeContextCurrent(this.id);
        GL.createCapabilities();
        GL11.glViewport(0,0,this.bufferedWidth.get(0),this.bufferedHeight.get(0));

    }

    public static EngineWindow getWindow(){
        return instance;
    }


    //Обновление окна
    public void update() {
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(this.id);
    }

    //Закрытие окна
    public void destroy() {
        GLFW.glfwDestroyWindow(this.id);
    }

    public boolean isCloseRequest(){
        return GLFW.glfwWindowShouldClose(this.id);
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
