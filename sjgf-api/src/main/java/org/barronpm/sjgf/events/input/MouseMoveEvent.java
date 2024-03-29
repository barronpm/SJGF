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

package org.barronpm.sjgf.events.input;

import org.barronpm.sjgf.GameWindow;
import org.barronpm.sjgf.math.Vector2;

/**
 * This event fires when the mouse moves.
 *
 * @author Patrick Barron
 * @since 1.0
 */
public final class MouseMoveEvent extends GenericMouseEvent {

    private final Vector2 oldPosition;
    private final Vector2 newPosition;

    public MouseMoveEvent(GameWindow window, Vector2 oldPosition, Vector2 newPosition) {
        super(window);
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    /**
     * Returns the previous position of the mouse as a {@link Vector2}
     *
     * @return the previous position of the mouse.
     */
    public Vector2 getOldPosition() {
        return new Vector2(oldPosition);
    }

    /**
     * Returns the current position of the mouse as a {@link Vector2}
     *
     * @return the current position of the mouse.
     */
    public Vector2 getNewPosition() {
        return new Vector2(newPosition);
    }
}
