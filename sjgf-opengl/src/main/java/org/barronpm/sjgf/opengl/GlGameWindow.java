/*
 *     Copyright 2017-2018 Patrick Barron
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.barronpm.sjgf.opengl;

import org.barronpm.sjgf.*;
import org.barronpm.sjgf.draw.Color;
import org.barronpm.sjgf.draw.Texture;
import org.barronpm.sjgf.exceptions.SJGFException;
import org.barronpm.sjgf.opengl.draw.GlGraphics;
import org.barronpm.sjgf.util.Args;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GlGameWindow implements GameWindow {

    private Game game;
    private long window;
    private ResourceLoader<Texture> textureLoader;

    private String title;
    private Monitor monitor;
    private boolean useVsync;
    private WindowState state;

    private EventDispatcher eventDispatcher;

    public GlGameWindow() {
        if (!glfwInit())
            throw new SJGFException("Failed to initialize GLFW");

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_DEPTH_BITS, 32);

        title = "SJGF";
        state = WindowState.RESTORED;
        window = glfwCreateWindow(800, 600, title, NULL, NULL);

        if (window == NULL)
            throw new SJGFException("Failed to create window");

        textureLoader = new GlTextureLoader();
    }

    @Override
    public void start() {
        glfwMakeContextCurrent(window);
        eventDispatcher = new EventDispatcher(this, window);
        GL.createCapabilities();
        GlGraphics graphics = new GlGraphics(this);

        game.init(this);
        double previous = glfwGetTime();
        while (!glfwWindowShouldClose(window)) {
            double current = glfwGetTime();
            double elapsed = current - previous;
            previous = current;

            glfwPollEvents();

            game.update(this, elapsed);

            glViewport(0, 0, getWidth(), getHeight());
            Color color = game.getBackgroundColor();
            glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            game.render(this, graphics);

            graphics.draw();
            glfwSwapBuffers(window);
        }

        game.dispose();
        graphics.dispose();
    }

    @Override
    public void close() {
        glfwSetWindowShouldClose(window, true);
    }

    @Override
    public void addListener(EventListener listener) {
        eventDispatcher.addListener(listener);
    }

    @Override
    public void removeListener(EventListener listener) {
        eventDispatcher.removeListener(listener);
    }

    @Override
    public Set<EventListener> getListeners() {
        return new HashSet<>(eventDispatcher.getListeners());
    }

    @Override
    public int getX() {
        MemoryStack stack = MemoryStack.stackPush();
        IntBuffer buffer = stack.mallocInt(1);
        glfwGetWindowPos(window, buffer, null);
        int x = buffer.get();
        stack.pop();

        return x;
    }

    @Override
    public void setX(int x) {
        glfwSetWindowPos(window, x, getY());
    }

    @Override
    public int getY() {
        MemoryStack stack = MemoryStack.stackPush();
        IntBuffer buffer = stack.mallocInt(1);
        glfwGetWindowPos(window, null, buffer);
        int y = buffer.get();
        stack.pop();

        return y;
    }

    @Override
    public void setY(int y) {
        glfwSetWindowPos(window, getX(), y);
    }

    @Override
    public int getWidth() {
        MemoryStack stack = MemoryStack.stackPush();
        IntBuffer buffer = stack.mallocInt(1);
        glfwGetWindowSize(window, buffer, null);
        int width = buffer.get();
        stack.pop();

        return width;
    }

    @Override
    public void setWidth(int width) {
        glfwSetWindowSize(window, width, getHeight());
    }

    @Override
    public int getHeight() {
        MemoryStack stack = MemoryStack.stackPush();
        IntBuffer buffer = stack.mallocInt(1);
        glfwGetWindowSize(window, null, buffer);
        int height = buffer.get();
        stack.pop();

        return height;
    }

    @Override
    public void setHeight(int height) {
        glfwSetWindowSize(window, getWidth(), height);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isUsingVSync() {
        return useVsync;
    }

    @Override
    public void setUseVSync(boolean useVSync) {
        glfwSwapInterval(useVSync ? 1 : 0);
        this.useVsync = useVSync;
    }

    @Override
    public Monitor getMonitor() {
        return monitor;
    }

    @Override
    public void setMonitor(Monitor monitor) {
        glfwSetWindowMonitor(window, monitor.getHandle(), getX(), getY(), getWidth(), getHeight(), GLFW_DONT_CARE);
        this.monitor = monitor;
    }

    @Override
    public WindowState getState() {
        return state;
    }

    @Override
    public void setState(WindowState state) {
        switch (state) {
            case MINIMIZED:
                glfwIconifyWindow(window);
                break;
            case RESTORED:
                glfwSetWindowMonitor(window, NULL, getX(), getY(), getWidth(), getHeight(), GLFW_DONT_CARE);
                break;
            case MAXIMIZED:
                glfwSetWindowMonitor(window, NULL, getX(), getY(), getWidth(), getHeight(), GLFW_DONT_CARE);
                glfwMaximizeWindow(window);
                break;
            case FULLSCREEN:
                glfwSetWindowMonitor(window, getMonitor().getHandle(), getX(), getY(), getWidth(), getHeight(), GLFW_DONT_CARE);
                break;
            default:
                throw new SJGFException("Unknown state!: " + state);
        }
        this.state = state;
    }

    @Override
    public boolean isVisible() {
        return glfwGetWindowAttrib(window, GLFW_VISIBLE) == GLFW_TRUE;
    }

    @Override
    public boolean isResizable() {
        return glfwGetWindowAttrib(window, GLFW_RESIZABLE) == GLFW_TRUE;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setGame(Game game) {
        Args.notNull(game, "game");
        this.game = game;
    }

    @Override
    public ResourceLoader<Texture> getTextureLoader() {
        return textureLoader;
    }

    public long getHandle() {
        return window;
    }
}
