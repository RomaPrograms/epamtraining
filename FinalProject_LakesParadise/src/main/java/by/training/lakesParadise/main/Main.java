package by.training.lakesParadise.main;

import by.training.lakesParadise.db.mysql.HomesteadDaoRealization;
import by.training.lakesParadise.entity.Homestead;
import by.training.lakesParadise.entity.Owner;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HomesteadDaoRealization home = new HomesteadDaoRealization();
        /*Homestead homestead = home.read(2);
        System.out.println(homestead.toString());

        List<Homestead> homesteadList = home.read();
        for (var h : homesteadList) {
            System.out.println(h);
        }

        Homestead newHome = new Homestead();
        newHome.setId(2);
        newHome.setTitle("Tilte");
        newHome.setDescription("Description");
        newHome.setPrice(new BigDecimal(12345));
        newHome.setRating(4.5);
        newHome.setStatus(true);
        Owner owner = new Owner();
        owner.setId(3);
        newHome.setOwner(owner);

        home.update(newHome);*/

        /*Homestead newHome1 = new Homestead();
        newHome1.setTitle("Tilte1");
        newHome1.setDescription("Description1");
        newHome1.setPrice(new BigDecimal(123451));
        newHome1.setRating(4.51);
        newHome1.setStatus(true);
        Owner owner1 = new Owner();
        owner1.setId(1);
        newHome1.setOwner(owner1);

        home.create(newHome1);*/

        List<Homestead> homesteadList = home.findByPrice(new BigDecimal(300),
                new BigDecimal(600));
        for (var h : homesteadList) {
            System.out.println(h);
        }
    }
}
