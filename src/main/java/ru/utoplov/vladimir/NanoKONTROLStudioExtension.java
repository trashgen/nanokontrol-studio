package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.ControllerHost;

public class NanoKONTROLStudioExtension extends ControllerExtension {

    private SceneManager sceneManager;

    NanoKONTROLStudioExtension(final NanoKONTROLStudioExtensionDefinition definition, final ControllerHost host) {
        super(definition, host);
    }

    @Override
    public void init() {
        getHost().getMidiInPort(0).setMidiCallback((ShortMidiMessageReceivedCallback) this::onMidi0);
        getHost().getMidiInPort(0).setSysexCallback(this::onSysex0);

        sceneManager = new SceneManager(getHost());

        getHost().showPopupNotification("NanoKONTROL Studio Initialized");
        getHost().println("NanoKONTROL Studio Initialized");
    }

    /**
     * Called when we receive short MIDI message on port 0.
     */
    private void onMidi0(ShortMidiMessage msg) {
//        getHost().println(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
        if (!sceneManager.getCurrentScene().handleMidiCommand(msg)) {
            getHost().showPopupNotification(String.format("Message [%d] not supported", msg.getData1()));
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
        sceneManager.onSceneChange(data);
        getHost().showPopupNotification(String.format("Set mode [%s]", sceneManager.getCurrentScene().getName()));
    }

}
