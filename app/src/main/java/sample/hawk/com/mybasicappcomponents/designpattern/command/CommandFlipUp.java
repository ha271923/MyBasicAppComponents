package sample.hawk.com.mybasicappcomponents.designpattern.command;

/** ConcreteCommand #1 == 命令#1的具體命令類別 */
public class CommandFlipUp implements ICommand {
    private DeviceLight theDeviceLight;

    public CommandFlipUp(final DeviceLight deviceLight) {
        this.theDeviceLight = deviceLight;
    }

    @Override    // Command
    public void execute() {
        theDeviceLight.turnOn();
    }
}
