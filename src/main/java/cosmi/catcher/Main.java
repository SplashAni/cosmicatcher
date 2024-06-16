package cosmi.catcher;

import org.lwjgl.nanovg.NVGColor;

import java.awt.*;

import static org.lwjgl.nanovg.NanoVG.*;

public class Main {
    public static Window window;


    public static void main(String[] args) {

        window = new Window(500, 400, "cosmicatcher");

        long vg = window.ctx;
        window.render(() -> {
            nvgBeginFrame(vg, window.width, window.height, 1f);
            nvgBeginPath(vg);
            nvgRoundedRect(vg, 5, 5, 100, 100, 4);
            nvgFillColor(vg, nvgColor(Color.BLUE));
            nvgFill(vg);
            nvgEndFrame(vg);
        });
    }

    public static NVGColor nvgColor(Color color) {
        NVGColor nvgColor = NVGColor.calloc();
        nvgColor.r(color.getRed() / 255.0f);
        nvgColor.g(color.getGreen() / 255.0f);
        nvgColor.b(color.getBlue() / 255.0f);
        nvgColor.a(color.getAlpha() / 255.0f);
        return nvgColor;
    }
}
