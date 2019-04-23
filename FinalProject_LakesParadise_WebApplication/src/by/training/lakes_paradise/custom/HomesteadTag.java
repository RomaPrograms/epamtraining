package by.training.lakes_paradise.custom;

//<div class="row">
//        <div class="col-md-4">
//            <img width="300px" height="200px" class="img-rounded"
//                 src="../img/myImages/1.1_farmstead.jpg"/>
//        </div>
//        <div class="col-md-8">
//            <h2>Дом №1</h2>
//            <p>Этот домик невероятно классный, мы уверены, что он вам понравится.</p>
//            <dl>
//                <dt>Цена</dt>
//                <dd>- 2000</dd>
//                <dt>Колличество человек</dt>
//                <dd>- 8</dd>
//            </dl>
//            <p><a href="contacts.jsp" class="btn btn-default">Узнать больше
//                &raquo;</a></p>
//        </div>
//    </div>

import javax.servlet.jsp.tagext.TagSupport;

public class HomesteadTag extends TagSupport {
    String name;
    String description;
    String price;
    String numberOfPeople;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }


}
