package by.training.webparsing.main;

import by.training.webparsing.propertymanager.ResourceManager;
import java.util.Locale;

public class Main {
    public static void main(final String args[]) {
//        DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
//        AbstractDeviceBuilder builder
//                = deviceFactory.createDeviceBuilder("dom");
//        builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
//                + "Task_4WebParsing\\src\\main\\resources\\data"
//                + "\\devices.xml");
//        List<Device> list = builder.getDevices();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        ResourceManager manager = ResourceManager.INSTANCE;
        System.out.println(manager.getString("str1"));
        manager.changeResource(new Locale("be","BY"));
        System.out.println(manager.getString("str1"));
    }
}
