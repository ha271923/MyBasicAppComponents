package sample.hawk.com.mybasicappcomponents.designpattern.command;


import java.util.ArrayList;
import java.util.List;

/** The Invoker class = 命令的調用者 */
public class DeviceSwitch {
    private List<ICommand> mCmdHistory = new ArrayList<ICommand>(); // Graphic時, 這如同繪圖命令暫存區

    public void Execute(final ICommand cmd) {
        this.mCmdHistory.add(cmd); // optional
        cmd.execute();
    }

}
