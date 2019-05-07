package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindHomesteadsAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/homesteads.html", true);
        String homesteadName = request.getParameter("homesteadName");
        String homesteadMinPrice = request.getParameter("minPrice");
        String homesteadMaxPrice = request.getParameter("maxPrice");
        StringBuffer stringBuffer = new StringBuffer();

//        if (homesteadName != null || !homesteadName.isEmpty()) {
//            stringBuffer.append("name");
//        }
//
//        if (homesteadMinPrice != null || !homesteadMinPrice.isEmpty()) {
//            if(stringBuffer.length() != 0) {
//                stringBuffer.append("_");
//            }
//            stringBuffer.append("minPrice");
//        }
//
//        if (homesteadMaxPrice != null || !homesteadMaxPrice.isEmpty()) {
//            if(stringBuffer.length() != 0) {
//                stringBuffer.append("_");
//            }
//            stringBuffer.append("maxPrice");
//        }
//
//        switch (Criterion.valueOf(stringBuffer.toString().toUpperCase())) {
//            case NAME:
//                break;
//            case MAX_PRICE:
//                break;
//            case MIN_PRICE:
//                break;
//            case NAME_MAX_PRICE:
//                break;
//            case NAME_MIN_PRICE:
//                break;
//            case MIN_PRICE_MAX_PRICE:
//                break;
//            case NAME_MIN_PRICE_MAX_PRICE:
//                break;
//        }

        return forward;
    }
}
