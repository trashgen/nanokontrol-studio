package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class SetButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 55;

    public SetButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        // TODO : Set 'this' SElected device RemoteControls to KnF
//        cc.cursorTrack.isPinned().set(true);
        cc.setRemoteControls();
    }
}
