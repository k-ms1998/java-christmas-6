package christmas.enums;

public enum Menu {
    SOUP("양송이수프", 6_000, FoodType.APPETIZER),
    TAPAS("타파스", 5_500, FoodType.APPETIZER),
    SALAD("시저샐러드", 8_000, FoodType.APPETIZER),
    BONE("티본스테이크", 55_000, FoodType.DISH),
    RIB("바비큐립", 54_000, FoodType.DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, FoodType.DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, FoodType.DISH),
    CHOCOLATE_CAKE("초코케이크", 15_000, FoodType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, FoodType.DESSERT),
    ZERO_COKE("제로콜라", 3_000, FoodType.DRINK),
    RED_WINE("레드와인", 60_000, FoodType.DRINK),
    CHAMPAGNE("샴페인", 25_000, FoodType.DRINK);
    private String name;
    private int price;
    private FoodType foodType;

    Menu(String name, int price, FoodType foodType) {
        this.name = name;
        this.price = price;
        this.foodType = foodType;
    }

    public static Menu findByName(String name){
        if(name.equals("양송이수프")) return SOUP;
        if(name.equals("타파스")) return TAPAS;
        if(name.equals("시저샐러드")) return SALAD;
        if(name.equals("티본스테이크")) return BONE;
        if(name.equals("바비큐립")) return RIB;
        if(name.equals("해산물파스타")) return SEAFOOD_PASTA;
        if(name.equals("크리스마스파스타")) return CHRISTMAS_PASTA;
        if(name.equals("초코케이크")) return CHOCOLATE_CAKE;
        if(name.equals("아이스크림")) return ICE_CREAM;
        if(name.equals("제로콜라")) return ZERO_COKE;
        if(name.equals("레드와인")) return RED_WINE;
        if(name.equals("샴페인")) return CHAMPAGNE;

        throw new IllegalStateException();
    }
}
