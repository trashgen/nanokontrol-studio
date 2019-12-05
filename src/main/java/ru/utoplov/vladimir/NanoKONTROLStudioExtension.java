package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.ControllerHost;
import ru.utoplov.vladimir.view.Scene;

public class NanoKONTROLStudioExtension extends ControllerExtension {

    private static final String NANO_KONTROL_STUDIO_ID = "NANO_KONTROL_STUDIO_ID";
    private static final String NANO_KONTROL_STUDIO_NAME = "NANO_KONTROL_STUDIO_NAME";

    private Scene currentScene;
    private SceneManager sceneManager;

    NanoKONTROLStudioExtension(final NanoKONTROLStudioExtensionDefinition definition, final ControllerHost host) {
        super(definition, host);
    }

    @Override
    public void init() {
        getHost().getMidiInPort(0).setMidiCallback((ShortMidiMessageReceivedCallback) this::onMidi0);
        getHost().getMidiInPort(0).setSysexCallback(this::onSysex0);

        sceneManager = new SceneManager(
                getHost().getMidiOutPort(0),
                getHost().createTransport(),
                getHost().createTrackBank(8, 0, 0, false),
                getHost().createCursorTrack(NANO_KONTROL_STUDIO_ID, NANO_KONTROL_STUDIO_NAME, 8, 0, true));
        currentScene = sceneManager.getFirstScene();

        getHost().showPopupNotification("trashgen NanoKONTROL Studio Initialized");
        getHost().println("trashgen NanoKONTROL Studio Initialized");
    }

    /**
     * Called when we receive short MIDI message on port 0.
     */
    private void onMidi0(ShortMidiMessage msg) {
        // TODO : Check if I have SHIFT. (Yes I can, but not the 'Cycles' Button. Maybe 'Set' or '<<'
        getHost().showPopupNotification(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
        getHost().println(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
        if (!currentScene.handleMidiCommand(msg)) {
            getHost().errorln(String.format("Message [%d] not supported", msg.getData1()));
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
        currentScene = sceneManager.onSceneChange(data);
        getHost().showPopupNotification(String.format("Set mode [%s]", currentScene.getName()));
    }

}
