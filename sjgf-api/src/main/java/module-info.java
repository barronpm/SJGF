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

/**
 * SJGF is a simple Java API for creating 2D graphics.
 *
 * @uses org.barronpm.sjgf.GameWindow an implementation of a GameWindow
 */
module org.barronpm.sjgf {
    requires transitive org.slf4j;
    requires transitive java.desktop;

    requires org.lwjgl.glfw;
    requires org.lwjgl.openal;

    exports org.barronpm.sjgf;
    exports org.barronpm.sjgf.draw;
    exports org.barronpm.sjgf.events;
    exports org.barronpm.sjgf.events.input;
    exports org.barronpm.sjgf.exceptions;
    exports org.barronpm.sjgf.input;
    exports org.barronpm.sjgf.math;
    exports org.barronpm.sjgf.sound;
    exports org.barronpm.sjgf.util;

    uses org.barronpm.sjgf.GameWindow;
}
