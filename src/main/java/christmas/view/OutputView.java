package christmas.view;

import christmas.PriceUtils;
import christmas.enums.Menu;
import christmas.enums.MessageType;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class OutputView {


    public static void printBasicMessage(MessageType type) {
        System.out.println(type.getMsg());
    }

    public static void printDate(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date));
    }

    public static void printOrder(Map<Menu, Integer> order) {
        System.out.println("<주문 메뉴>");
        for (Menu menu : order.keySet()) {
            int amount = order.get(menu);
            System.out.println(String.format("%s %d개", menu.getName(), amount));
        }
        System.out.println();
    }

    public static void printOriginalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%s원\n", NumberFormat.getNumberInstance(Locale.US).format(totalPrice)));
    }

    public static void printFreebie(int originalPrice) {
        System.out.println("<증정 메뉴>");
        if(PriceUtils.checkFreebie(originalPrice)){
            System.out.println("샴페인 1개\n");
            return;
        }

        System.out.println("없음\n");
    }

    public static void printDiscountDetails(int totalDiscountWorth, int originalPrice, int date, Map<Menu, Integer> order) {
        System.out.println("<혜택 내역>");
        if(totalDiscountWorth == 0){
            System.out.println("없음\n");
            return;
        }
        
        printChristmasDdayDiscountDetails(date);
        printWeekdayDiscountDetails(date, order);
        printWeekdendDiscountDetails(date, order);
        printStarredDiscountDetails(date);
        printFreebieDiscountDetails(originalPrice);
        System.out.println();
    }

    public static void printTotalDiscountWorth(int totalDiscount) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format("%s원", NumberFormat.getNumberInstance(Locale.US).format(-totalDiscount)));
        System.out.println();
    }

    public static void printEstimatedPayment(int originalPrice, int totalDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        int discountedPrice = originalPrice - totalDiscount;
        System.out.println(String.format("%s원", NumberFormat.getNumberInstance(Locale.US).format(discountedPrice)));
        System.out.println();
    }

    public static void printBadge(int totalDiscount) {
        System.out.println("<12월 이벤트 배지>");
        int badge = PriceUtils.calculateBadge(totalDiscount);
        if(badge == 0){
            System.out.println("별");
            return;
        }
        if(badge == 1){
            System.out.println("트리");
            return;
        }
        if(badge == 2){
            System.out.println("산타");
            return;
        }

        System.out.println("없음");
    }

    private static void printChristmasDdayDiscountDetails(int date) {
        int christmasDdayDiscount = PriceUtils.calculateChristmasDdayDiscount(date);
        if(christmasDdayDiscount > 0){
            System.out.println(String.format("크리스마스 디데이 할인: %s원", NumberFormat.getNumberInstance(Locale.US).format(-christmasDdayDiscount)));
        }
    }

    private static void printWeekdayDiscountDetails(int date, Map<Menu, Integer> order) {
        int weekDayDiscount = PriceUtils.calculateWeekDayDiscount(date, order);
        if(weekDayDiscount > 0){
            System.out.println(String.format("평일 할인: %s원", NumberFormat.getNumberInstance(Locale.US).format(-weekDayDiscount)));
        }
    }

    private static void printWeekdendDiscountDetails(int date, Map<Menu, Integer> order) {
        int weekendDiscount = PriceUtils.calculateWeekendDiscount(date, order);
        if(weekendDiscount > 0){
            System.out.println(String.format("주말 할인: %s원", NumberFormat.getNumberInstance(Locale.US).format(-weekendDiscount)));
        }
    }

    private static void printStarredDiscountDetails(int date) {
        int starredDiscount = PriceUtils.calculateStarredDiscount(date);
        if(starredDiscount > 0){
            System.out.println(String.format("특별 할인: %s원", NumberFormat.getNumberInstance(Locale.US).format(-starredDiscount)));
        }

    }

    private static void printFreebieDiscountDetails(int originalPrice) {
        int freebieDiscount = PriceUtils.calculateFreebieDiscount(originalPrice);
        if(freebieDiscount > 0){
            System.out.println(String.format("증정 이벤트: %s원", NumberFormat.getNumberInstance(Locale.US).format(-freebieDiscount)));
        }
    }
}
