package christmas;

import christmas.enums.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Application {

    static int date;
    static Map<Menu, Integer> order;
    static int originalPrice;
    static int totalDiscountAmount;
    static int totalDiscountWorth;

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        initDateAndOrder();
        applyEvent();
        endOrder();
    }

    private static void initDateAndOrder() {
        date = InputView.inputDate();
        order = InputView.inputOrder();

        OutputView.printDate(date);
        OutputView.printOrder(order);
    }

    private static void applyEvent() {
        originalPrice = PriceUtils.calculateOriginalPrice(order);
        int freebiePrice = PriceUtils.calculateFreebieDiscount(originalPrice);
        totalDiscountAmount = PriceUtils.calculateTotalDiscountAmount(date, order);
        totalDiscountWorth = PriceUtils.calculateTotalDiscountWorth(totalDiscountAmount, freebiePrice);

        OutputView.printOriginalPrice(originalPrice);
        OutputView.printFreebie(originalPrice);
        OutputView.printDiscountDetails(totalDiscountWorth, originalPrice, date, order);
        OutputView.printTotalDiscountWorth(totalDiscountWorth);
    }

    private static void endOrder() {
        OutputView.printEstimatedPayment(originalPrice, totalDiscountAmount);
        OutputView.printBadge(totalDiscountWorth);
    }

}
