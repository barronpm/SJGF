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

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public enum Keys {
    SPACE(GLFW_KEY_SPACE),
    APOSTROPHE(GLFW_KEY_APOSTROPHE),
    COMMA(GLFW_KEY_COMMA),
    MINUS(GLFW_KEY_MINUS),
    PERIOD(GLFW_KEY_PERIOD),
    SLASH(GLFW_KEY_SLASH),
    ZERO(GLFW_KEY_0),
    ONE(GLFW_KEY_1),
    TWO(GLFW_KEY_2),
    THREE(GLFW_KEY_3),
    FOUR(GLFW_KEY_4),
    FIVE(GLFW_KEY_5),
    SIX(GLFW_KEY_6),
    SEVEN(GLFW_KEY_7),
    EIGHT(GLFW_KEY_8),
    NINE(GLFW_KEY_9),
    SEMICOLON(GLFW_KEY_SEMICOLON),
    EQUAL(GLFW_KEY_EQUAL),
    A(GLFW_KEY_A),
    B(GLFW_KEY_B),
    C(GLFW_KEY_C),
    D(GLFW_KEY_D),
    E(GLFW_KEY_E),
    F(GLFW_KEY_F),
    G(GLFW_KEY_G),
    H(GLFW_KEY_H),
    I(GLFW_KEY_I),
    J(GLFW_KEY_J),
    K(GLFW_KEY_K),
    L(GLFW_KEY_L),
    M(GLFW_KEY_M),
    N(GLFW_KEY_N),
    O(GLFW_KEY_O),
    P(GLFW_KEY_P),
    Q(GLFW_KEY_Q),
    R(GLFW_KEY_R),
    S(GLFW_KEY_S),
    T(GLFW_KEY_T),
    U(GLFW_KEY_U),
    V(GLFW_KEY_V),
    W(GLFW_KEY_W),
    X(GLFW_KEY_X),
    Y(GLFW_KEY_Y),
    Z(GLFW_KEY_Z),
    LEFT_BRACKET(GLFW_KEY_LEFT_BRACKET),
    BACKSLASH(GLFW_KEY_BACKSLASH),
    RIGHT_BRACKET(GLFW_KEY_RIGHT_BRACKET),
    GRAVE_ACCENT(GLFW_KEY_GRAVE_ACCENT),
    ESCAPE(GLFW_KEY_ESCAPE),
    ENTER(GLFW_KEY_ENTER),
    TAB(GLFW_KEY_TAB),
    BACKSPACE(GLFW_KEY_BACKSPACE),
    INSERT(GLFW_KEY_INSERT),
    DELETE(GLFW_KEY_DELETE),
    RIGHT(GLFW_KEY_RIGHT),
    LEFT(GLFW_KEY_LEFT),
    DOWN(GLFW_KEY_DOWN),
    UP(GLFW_KEY_UP),
    PAGE_UP(GLFW_KEY_PAGE_UP),
    PAGE_DOWN(GLFW_KEY_PAGE_DOWN),
    HOME(GLFW_KEY_HOME),
    END(GLFW_KEY_END),
    CAPS_LOCK(GLFW_KEY_CAPS_LOCK),
    SCROLL_LOCK(GLFW_KEY_SCROLL_LOCK),
    NUM_LOCK(GLFW_KEY_NUM_LOCK),
    PRINT_SCREEN(GLFW_KEY_PRINT_SCREEN),
    PAUSE(GLFW_KEY_PAUSE),
    F1(GLFW_KEY_F1),
    F2(GLFW_KEY_F2),
    F3(GLFW_KEY_F3),
    F4(GLFW_KEY_F4),
    F5(GLFW_KEY_F5),
    F6(GLFW_KEY_F6),
    F7(GLFW_KEY_F7),
    F8(GLFW_KEY_F8),
    F9(GLFW_KEY_F9),
    F10(GLFW_KEY_F10),
    F11(GLFW_KEY_F11),
    F12(GLFW_KEY_F12),
    F13(GLFW_KEY_F13),
    F14(GLFW_KEY_F14),
    F15(GLFW_KEY_F15),
    F16(GLFW_KEY_F16),
    F17(GLFW_KEY_F17),
    F18(GLFW_KEY_F18),
    F19(GLFW_KEY_F19),
    F20(GLFW_KEY_F20),
    F21(GLFW_KEY_F21),
    F22(GLFW_KEY_F22),
    F23(GLFW_KEY_F23),
    F24(GLFW_KEY_F24),
    F25(GLFW_KEY_F25),
    NUMPAD_0(GLFW_KEY_KP_0),
    NUMPAD_1(GLFW_KEY_KP_1),
    NUMPAD_2(GLFW_KEY_KP_2),
    NUMPAD_3(GLFW_KEY_KP_3),
    NUMPAD_4(GLFW_KEY_KP_4),
    NUMPAD_5(GLFW_KEY_KP_5),
    NUMPAD_6(GLFW_KEY_KP_6),
    NUMPAD_7(GLFW_KEY_KP_7),
    NUMPAD_8(GLFW_KEY_KP_8),
    NUMPAD_9(GLFW_KEY_KP_9),
    NUMPAD_DECIMAL(GLFW_KEY_KP_DECIMAL),
    NUMPAD_DIVIDE(GLFW_KEY_KP_DIVIDE),
    NUMPAD_MULTIPLY(GLFW_KEY_KP_MULTIPLY),
    NUMPAD_SUBTRACT(GLFW_KEY_KP_SUBTRACT),
    NUMPAD_ADD(GLFW_KEY_KP_ADD),
    NUMPAD_ENTER(GLFW_KEY_KP_ENTER),
    NUMPAD_EQUAL(GLFW_KEY_KP_EQUAL),
    LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT),
    LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL),
    LEFT_ALT(GLFW_KEY_LEFT_ALT),
    LEFT_SUPER(GLFW_KEY_LEFT_SUPER),
    RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT),
    RIGHT_CONTROL(GLFW_KEY_RIGHT_CONTROL),
    RIGHT_ALT(GLFW_KEY_RIGHT_ALT),
    RIGHT_SUPER(GLFW_KEY_RIGHT_SUPER),
    MENU(GLFW_KEY_MENU);

    private static final Map<Integer, Keys> codes = new HashMap<>();

    private final int code;

    Keys(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Keys getKeyByCode(int code) {
        // This is a more robust solution than the one in Controller.Buttons, but it is very verbose
        // and should likewise be considered for a replacement by a better solution

        if (codes.isEmpty()) {
            for (Keys key : values()) {
                codes.put(key.code, key);
            }
        }

        return codes.get(code);
    }
}