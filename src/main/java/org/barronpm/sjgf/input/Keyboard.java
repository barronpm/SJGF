/*
 *     Copyright 2017 Patrick Barron
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

package org.barronpm.sjgf.input;

import org.barronpm.sjgf.backend.Engine;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public final class Keyboard {

    private Keyboard() {}

    public static boolean isKeyPressed(Keys key) {
        return glfwGetKey(Engine.window, key.getCode()) == GLFW_PRESS;
    }

    public static Set<Keys> getPressedKeys() {
        return Arrays.stream(Keys.values())
                .filter(Keyboard::isKeyPressed)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Keys.class)));
    }
}
