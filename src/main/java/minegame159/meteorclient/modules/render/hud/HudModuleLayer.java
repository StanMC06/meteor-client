/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render.hud;

import minegame159.meteorclient.modules.render.hud.modules.HudModule;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;

import java.util.List;

public class HudModuleLayer {
    private final HudRenderer renderer;
    private final List<HudModule> modules;

    private final AlignmentX xAlign;
    private final AlignmentY yAlign;

    private final double x;
    private double y;

    public HudModuleLayer(HudRenderer renderer, List<HudModule> modules, AlignmentX xAlign, AlignmentY yAlign, int xOffset, int yOffset) {
        this.renderer = renderer;
        this.modules = modules;
        this.xAlign = xAlign;
        this.yAlign = yAlign;
        this.x = xOffset * (xAlign == AlignmentX.Right ? -1 : 1);
        this.y = yOffset * (yAlign == AlignmentY.Bottom ? -1 : 1);
    }

    public void add(HudModule module) {
        module.update(renderer);

        module.box.x = xAlign;
        module.box.y = yAlign;
        module.box.xOffset = (int) Math.round(x);
        module.box.yOffset = (int) Math.round(y);

        if (yAlign == AlignmentY.Bottom) y -= 2 + module.box.height;
        else y += 2 + module.box.height;

        modules.add(module);
    }
}
