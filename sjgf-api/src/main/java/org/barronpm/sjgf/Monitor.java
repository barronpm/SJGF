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

package org.barronpm.sjgf;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

public final class Monitor {

    static final Map<Long, Monitor> monitors = new HashMap<>();

    private final long handle;

    Monitor(long handle) {
        this.handle = handle;
    }

    public static Monitor getByHandle(long handle) {
        return monitors.get(handle);
    }

    public long getHandle() {
        return handle;
    }

    public int getWidth() {
        return glfwGetVideoMode(handle).width();
    }

    public int getHeight() {
        return glfwGetVideoMode(handle).height();
    }
}
