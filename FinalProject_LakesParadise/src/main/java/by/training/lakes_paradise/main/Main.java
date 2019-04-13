package by.training.lakes_paradise.main;

import by.training.lakes_paradise.db.mysql.HomesteadDaoRealization;
import by.training.lakes_paradise.db.mysql.OrderDaoRealization;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.db.mysql.UserDaoRealization;

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

        /*List<Homestead> homesteadList = home.findByPrice(new BigDecimal(300),
                new BigDecimal(600));
        for (var h : homesteadList) {
            System.out.println(h);
        }*/

        ProfileDaoRealization prof = new ProfileDaoRealization();
        /*prof.delete(2);
        Profile prof1 = new Profile();
        prof1.setId(6);
        prof1.setLogin("changed");
        prof1.setPassword("changed");
        prof1.setOrders(2);
        prof.update(prof1);*/
        /*prof.delete(2);
        Profile prof2 = new Profile();
        prof2.setId(2);
        prof2.setLogin("added");
        prof2.setPassword("added");
        prof2.setOrders(1);

        System.out.println(prof.create(prof2));*/

        UserDaoRealization userRealiz = new UserDaoRealization();
        //System.out.println(userRealiz.read(3));
        /*for(var us : userRealiz.read()) {
            System.out.println(us);
        }*/
        /*User user1 = new User();
        user1.setId(2);
        user1.setName("Name");
        user1.setSurname("Surname");
        user1.setPhone("+375291844112");
        user1.setTown("Town");
        userRealiz.update(user1);*/

        //userRealiz.delete(2);

        /*User user2 = new User();
        user2.setId(2);
        user2.setName("Name2");
        user2.setSurname("Surname2");
        user2.setPhone("+222222222222");
        user2.setTown("Town2");
        userRealiz.create(user2);*/

        OrderDaoRealization orderDaoRealization = new OrderDaoRealization();
        //Order order = orderDaoRealization.read(2);
        //System.out.println(order);
        /*Order order = new Order();
        Profile profile = new Profile();
        profile.setId(4);
        order.setProfile(profile);
        Homestead homestead = new Homestead();
        homestead.setId(3);
        order.setHomestead(homestead);
        Date date = new Date();
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = (Date)formatter.parse("2000-01-01");
            date1 = (Date)formatter.parse("2001-02-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setStartRenting(date.getTime());
        order.setEndRenting(date1.getTime());
        order.setNumberOfPeople(8);
        order.setPaid(true);
        orderDaoRealization.create(order);*/
        /*Order order = new Order();
        order.setId(2);
        Profile profile = new Profile();
        profile.setId(1);
        order.setProfile(profile);
        Homestead homestead = new Homestead();
        homestead.setId(1);
        order.setHomestead(homestead);
        Date date = new Date();
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = (Date)formatter.parse("2010-10-10");
            date1 = (Date)formatter.parse("2011-11-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setStartRenting(date.getTime());
        order.setEndRenting(date1.getTime());
        order.setNumberOfPeople(8);
        order.setPaid(true);
        orderDaoRealization.update(order);*/

        for(var order : orderDaoRealization.readByProfile(6)) {
            System.out.println(order);
        }
    }
}
