package de.jonas.engine.data.dataClasses;

public class SettingsData {
    public Client client;
    public Dev dev;

    public static class Client {
        public Input input;
        public Window window;
        public Render render;
    }

    public static class Input {
        public float mouseSensitivity;
    }

    public static class Window {
        public boolean vsync;
        public int startWidth;
        public int startHeight;
    }

    public static class Render {
        public Camera camera;
        public int fps;
        public int render_distance;
    }

    public static class Camera {
        public float cameraNear;
        public float cameraFar;
        public float fov;
    }

    public static class Dev {
        public boolean debug;
        public boolean wireframe;
    }
}
