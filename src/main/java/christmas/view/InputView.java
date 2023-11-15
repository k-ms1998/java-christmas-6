package christmas.view;

import christmas.enums.FoodType;
import christmas.enums.Menu;
import christmas.enums.MessageType;
import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    public static int inputDate() {
        OutputView.printBasicMessage(MessageType.INTRO_MSG);
        OutputView.printBasicMessage(MessageType.ASK_DATE_MSG);

        try {
            int date = Integer.parseInt(Console.readLine());
            checkDate(date);
            return date;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printBasicMessage(MessageType.ERROR_INCORRECT_DATE);
            return inputDate();
        }
    }

    public static Map<Menu, Integer> inputOrder() {
        OutputView.printBasicMessage(MessageType.INPUT_ORDER_MSG);

        try {
            String order = Console.readLine();
            String[] orderSplit = splitInput(order, ",");
            return createOrderMap(orderSplit);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printBasicMessage(MessageType.ERROR_INCORRECT_ORDER);
            return inputOrder();
        }
    }

    private static void checkDate(int date) {
        if(date < 1 || date > 31){
            throw new IllegalStateException();
        }
    }

    private static String[] splitInput(String input, String regex) {
        return input.split(regex);
    }

    private static Map<Menu, Integer> createOrderMap(String[] input) {
        Map<Menu, Integer> currentOrder = new HashMap<>();

        for(String o : input){
            String[] oSplit = splitInput(o, "-");
            checkFoodAndAmountSplit(oSplit);
            Menu food = Menu.findByName(oSplit[0]);
            int amount = checkAndGetAmount(oSplit[1]);
            checkAndAddFoodAmountPair(currentOrder, food, amount);
        }

        return currentOrder;
    }

    private static void checkFoodAndAmountSplit(String[] input) {
        if(input.length != 2){
            throw new IllegalArgumentException();
        }
    }

    private static int checkAndGetAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            if(amount < 1) throw new IllegalArgumentException();

            return amount;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkAndAddFoodAmountPair(Map<Menu, Integer> currentOrder, Menu food, int amount) {
        if (currentOrder.containsKey(food)) {
            throw new IllegalArgumentException();
        }

        currentOrder.put(food, amount);
    }

}
