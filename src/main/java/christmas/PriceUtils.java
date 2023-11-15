package christmas;

import christmas.enums.FoodType;
import christmas.enums.Menu;

import java.util.Map;


public class PriceUtils {
    /*
     * 크리스마스 디데이 할인
     * 이벤트 기간: 2023.12.1 ~ 2023.12.25
     * 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
     * 총주문 금액에서 해당 금액만큼 할인
     * (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
     *
     * 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
     *
     * 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
     *
     * 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
     *
     * 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
     *
     * 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용
     */
    private static final int[] dayOfWeek = {
                                -1, 6, 7,
                    1, 2, 3, 4, 5, 6, 7,
                    1, 2, 3, 4, 5, 6, 7,
                    1, 2, 3, 4, 5, 6, 7,
                    1, 2, 3, 4, 5, 6, 7,
                    1
    };

    private static final boolean[] starredDate = {
                                        false, false, false,
            true, false, false, false, false, false, false,
            true, false, false, false, false, false, false,
            true, false, false, false, false, false, false,
            true, true, false, false, false, false, false,
            true
    };

    private static final int WEEKDAY_DISCOUNT_AMOUNT_PER_ITEM = 2023;
    private static final int WEEKEND_DISCOUNT_AMOUNT_PER_ITEM = 2023;
    private static final Menu FREEBIE_MENU = Menu.CHAMPAGNE;

    public static int calculateOriginalPrice(Map<Menu, Integer> order) {
        int totalAmount = 0;
        for (Menu menu : order.keySet()) {
            int amount = order.get(menu);
            totalAmount += menu.getPrice() * amount;
        }

        return totalAmount;
    }

    public static boolean checkFreebie(int totalPrice) {
        if(totalPrice >= 120_000){
            return true;
        }

        return false;
    }

    // 무료 증정 음식/음료는 할인 금액에 포함하지 않는다
    public static int calculateTotalDiscountAmount(int date, Map<Menu, Integer> order) {
        int christmasDdayDiscount = calculateChristmasDdayDiscount(date);

        int weekDayDiscount = calculateWeekDayDiscount(date, order);

        int weekendDiscount = calculateWeekendDiscount(date, order);

        int starredDiscount = calculateStarredDiscount(date);

        return christmasDdayDiscount + weekDayDiscount + weekendDiscount + starredDiscount;
    }

    // 총혜택 금액에는 무료 증정 음식/음료의 가격도 포함
    public static int calculateTotalDiscountWorth(int totalDiscountAmount, int freebiePrice) {
        return totalDiscountAmount + freebiePrice;
    }

    public static int calculateChristmasDdayDiscount(int date) {
        if(date >= 26){
            return 0;
        }
        return 1000 + 100 * (date - 1);
    }

    public static int calculateWeekDayDiscount(int date, Map<Menu, Integer> order) {
        if(dayOfWeek[date] >= 6){
            return 0;
        }

        int result = 0;
        for (Menu menu : order.keySet()) {
            int amount = order.get(menu);
            if (menu.getFoodType() == FoodType.DESSERT) {
                result += amount * WEEKDAY_DISCOUNT_AMOUNT_PER_ITEM;
            }
        }
        return result;
    }

    public static int calculateWeekendDiscount(int date, Map<Menu, Integer> order) {
        if(dayOfWeek[date] <= 5){
            return 0;
        }

        int result = 0;
        for (Menu menu : order.keySet()) {
            if (menu.getFoodType() == FoodType.DESSERT) {
                result += WEEKEND_DISCOUNT_AMOUNT_PER_ITEM;
            }
        }
        return result;
    }

    public static int calculateStarredDiscount(int date) {
        if(starredDate[date]){
            return 1000;
        }

        return 0;
    }

    public static int calculateFreebieDiscount(int originalPrice) {
        if(checkFreebie(originalPrice)){
            return FREEBIE_MENU.getPrice();
        }
        return 0;
    }

    public static int calculateBadge(int totalDiscount) {
        if(totalDiscount >= 20_000){
            return 2;
        }
        if(totalDiscount >= 10_000){
            return 1;
        }
        if(totalDiscount >= 5_000){
            return 0;
        }

        return -1;
    }

}
