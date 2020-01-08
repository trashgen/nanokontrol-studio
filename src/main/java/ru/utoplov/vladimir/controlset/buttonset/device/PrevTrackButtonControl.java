package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class PrevTrackButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 60;

    public PrevTrackButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.scene.cleanUp();
        cc.remote.prevRemoteControls();
        cc.scene.init();
        cc.showMappingInfo();
    }
}
