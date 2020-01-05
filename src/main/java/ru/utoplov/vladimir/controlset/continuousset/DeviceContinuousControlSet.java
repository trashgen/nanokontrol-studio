package ru.utoplov.vladimir.controlset.continuousset;

import com.bitwig.extension.controller.api.RemoteControl;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.continuousset.device.FaderControl;
import ru.utoplov.vladimir.controlset.continuousset.device.KnobControl;
import ru.utoplov.vladimir.controlset.continuousset.device.WheelBackwardControl;
import ru.utoplov.vladimir.controlset.continuousset.device.WheelForwardControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class DeviceContinuousControlSet extends AbstractControlSet {

    public DeviceContinuousControlSet(ControllerContext controllerContext) {
        super(controllerContext);

        controls.put(WheelBackwardControl.BUTTON_ID, new WheelBackwardControl(controllerContext));
        controls.put(WheelForwardControl.BUTTON_ID, new WheelForwardControl(controllerContext));

        for (int i = KnobControl.BUTTON_ID_FIRST; i <= KnobControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new KnobControl(controllerContext, i - KnobControl.BUTTON_ID_FIRST));
        }
        for (int i = FaderControl.BUTTON_ID_FIRST; i <= FaderControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new FaderControl(controllerContext, i - FaderControl.BUTTON_ID_FIRST));
        }
    }

    @Override
    public void updateIndication(boolean enabled) {
        for (int i = 0; i < cc.controlsOne.getParameterCount(); i++) {
            RemoteControl parameter = cc.controlsOne.getParameter(i);
            parameter.setIndication(enabled);
        }
        for (int i = 0; i < cc.controlsTwo.getParameterCount(); i++) {
            RemoteControl parameter = cc.controlsTwo.getParameter(i);
            parameter.setIndication(enabled);
        }
    }

}
