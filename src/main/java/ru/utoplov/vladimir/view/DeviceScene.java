package ru.utoplov.vladimir.view;

import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceScene extends AbstractScene {

    private static final String SCENE_NAME = "Device Scene";

    private ControllerContext cc;

    public DeviceScene(ControllerContext cc) {
        this.cc = cc;
    }

    @Override
    public String getName() {
        return SCENE_NAME;
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        cc.cursorTrack.isPinned().set(false);
    }

    @Override
    public void init() {
        super.init();
        cc.cursorTrack.isPinned().set(true);
    }

}
