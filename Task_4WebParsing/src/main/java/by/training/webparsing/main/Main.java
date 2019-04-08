package by.training.webparsing.main;

import by.training.webparsing.entity.Device;
import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import java.util.List;

/**
 * Class that contains method from which starts our program.
 */
public class Main {

    /**
     * No-argument constructor.
     */
    public Main() { }
    /**
     * First method of our program.
     * @param args - arguments from command line
     */
    public static void main(final String[] args) {
        DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
        AbstractDeviceBuilder builder
                = deviceFactory.createDeviceBuilder("dom");
        builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                + "Task_4WebParsing\\src\\main\\resources\\data"
                + "\\devices.xml");
        List<Device> list = builder.getDevices();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
