package sample.hawk.com.mybasicappcomponents.designpattern.command;

/** ConcreteCommand #2 == 命令#2的具體命令類別 */
public class CommandFlipDown implements ICommand {
    private DeviceLight theDeviceLight;

    public CommandFlipDown(final DeviceLight deviceLight) {
        this.theDeviceLight = deviceLight;
    }

    @Override    // Command
    public void execute() {
        theDeviceLight.turnOff();
    }
}
