package ru.utoplov.vladimir.controlset.continuousset;

import com.bitwig.extension.controller.api.Parameter;
import com.bitwig.extension.controller.api.Send;
import ru.utoplov.vladimir.controlset.AbstractControlSet;
import ru.utoplov.vladimir.controlset.continuousset.mix.FaderControl;
import ru.utoplov.vladimir.controlset.continuousset.mix.KnobControl;
import ru.utoplov.vladimir.controlset.continuousset.mix.WheelBackwardControl;
import ru.utoplov.vladimir.controlset.continuousset.mix.WheelForwardControl;
import ru.utoplov.vladimir.core.ControllerContext;


public class MixContinuousControlSet extends AbstractControlSet {

    public MixContinuousControlSet(ControllerContext cc) {
        super(cc);

        controls.put(WheelBackwardControl.BUTTON_ID, new WheelBackwardControl(cc));
        controls.put(WheelForwardControl.BUTTON_ID, new WheelForwardControl(cc));

        for (int i = FaderControl.BUTTON_ID_FIRST; i <= FaderControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new FaderControl(cc, i - FaderControl.BUTTON_ID_FIRST));
        }
        for (int i = KnobControl.BUTTON_ID_FIRST; i <= KnobControl.BUTTON_ID_LAST; i++) {
            controls.put(i, new KnobControl(cc, i - KnobControl.BUTTON_ID_FIRST));
        }
    }

    @Override
    public void updateIndication(boolean enabled) {
        for (int i = 0; i < cc.cursorTrack.sendBank().getSizeOfBank(); i++) {
            Send send = cc.cursorTrack.sendBank().getItemAt(i);
            send.setIndication(enabled);
        }

        for (int i = 0; i < cc.trackBank.getSizeOfBank(); i++) {
            Parameter parameter = cc.trackBank.getItemAt(i).volume();
            parameter.setIndication(enabled);
        }
    }

}
