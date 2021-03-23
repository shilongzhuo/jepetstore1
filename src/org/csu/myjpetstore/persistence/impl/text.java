//package org.csu.myjpetstore.persistence.impl;
//
//import org.csu.myjpetstore.domain.Account;
//import org.csu.myjpetstore.domain.Item;
//import org.csu.myjpetstore.jdbc.CardInfo;
//import org.csu.myjpetstore.service.CategoryService;
//
//import javax.servlet.http.HttpServlet;
//import java.util.List;
//
//public class text extends HttpServlet {
//    private static CategoryService categoryService;
//
//    public static void main(String[] args) {
//        Card_infoDAO card_infoDAO = new Card_infoDAO();
//        List<CardInfo> cardInfo = card_infoDAO.selectALL();
//        for(int i = 0 ;i < cardInfo.size();i++)
//        {
//
//            if (Account.cart1.containsItemId(cardInfo.get(i).getCardId())){
//                Account.cart1.incrementQuantityByItemId(cardInfo.get(i).getCardId());
//                System.out.print("8888888888不该调用");
//            }
//            else {
//                boolean isInStock = categoryService.isItemInStock(cardInfo.get(i).getCardId());
//                Item item = categoryService.getItem(cardInfo.get(i).getCardId());
//                Account.cart1.addItem(item, isInStock);
//                System.out.print("*****"+item);
//            }
//        }
//        System.out.println(Account.cart1.getCartItemList().size());
//    }
//}
