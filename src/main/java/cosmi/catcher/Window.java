package cosmi.catcher;

import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.nanovg.NanoVGGL2.*;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClear;

public class Window {
    long window;
    long ctx;
    int width, height;

    public Window(int width, int height, String title) {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        this.width = width;
        this.height = height;

        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        ctx = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if (ctx == 0) {
            throw new RuntimeException("Could not init NanoVG");
        }

        glfwSetFramebufferSizeCallback(window, (window, newWidth, newHeight) -> {
            this.width = newWidth;
            this.height = newHeight;
            GL.createCapabilities();
        });

        glfwShowWindow(window);
    }

    public void render(Runnable callback) {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            callback.run();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        nvgDelete(ctx);
        glfwDestroyWindow(window);
        glfwTerminate();
    }


}
