package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.ControllerHost;
import ru.utoplov.vladimir.scenes.SceneView;
import ru.utoplov.vladimir.trackbank.TrackBankManager;
import ru.utoplov.vladimir.transport.TransportManager;

public class NanoKONTROLStudioExtension extends ControllerExtension {

    private TrackBankManager trackBankManager;
    private TransportManager transportManager;

    public static SceneView currentView;

    NanoKONTROLStudioExtension(final NanoKONTROLStudioExtensionDefinition definition, final ControllerHost host) {
        super(definition, host);
    }

    @Override
    public void init() {
        getHost().getMidiInPort(0).setMidiCallback((ShortMidiMessageReceivedCallback) this::onMidi0);
        getHost().getMidiInPort(0).setSysexCallback(this::onSysex0);

        trackBankManager = new TrackBankManager(getHost());
        transportManager = new TransportManager(getHost());

        getHost().showPopupNotification("NanoKONTROL Studio Initialized");
    }

    /**
     * Called when we receive short MIDI message on port 0.
     */
    private void onMidi0(ShortMidiMessage msg) {
//        getHost().showPopupNotification(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
        if (transportManager.isValidCommand(msg)) {
            transportManager.execute(msg, useShift(msg));
        } else if (trackBankManager.isValidCommand(msg)) {
            trackBankManager.execute(msg, useShift(msg));
        }
    }

    @Override
    public void exit() {
        getHost().showPopupNotification("NanoKONTROL Studio Exited");
    }

    @Override
    public void flush() {
        // TODO Send any updates you need here.
    }

    /**
     * Called when we receive sysex MIDI message on port 0.
     */
    private void onSysex0(final String data) {
        SceneView view = NanoKONTROLStudioExtensionDefinition.SysexHandlers.get(data);
        if (view != null) {
            currentView = view;
            getHost().showPopupNotification(String.format("Set mode [%s]", view.getName()));
        }
    }

    private boolean useShift(ShortMidiMessage msg) {
        return msg.getData1() == NanoKONTROLStudioExtensionDefinition.BUTTON_SHIFT && msg.getData2() == 127;
    }

}
