package gamebody.engine;

/**
 * 该类用于标记商店产品的购买状态
 * 变量设置为true代表用户已经购买该产品，false代表用户未购买该产品
 */

public class ProductStatus {
    private boolean isDynamite, isDrink, isClover, isBook, isPolish; //是否选择炸药、力量饮料、四叶草、石头书、钻石抛光是否刷出

    public ProductStatus(){
        //商店刚加载，所有商品的选择状态初始化为false
        isBook=false;
        isDrink=false;
        isClover=false;
        isPolish=false;
        isDynamite=false;
    }

    //设置石头收藏家书籍购买状态
    public void setBook(boolean book) {
        isBook = book;
    }

    //设置四叶草购买状态
    public void setClover(boolean clover) {
        isClover = clover;
    }

    //设置能量饮料购买状态
    public void setDrink(boolean drink) {
        isDrink = drink;
    }

    //设置炸弹购买状态
    public void setDynamite(boolean dynamite) {
        isDynamite = dynamite;
    }

    //设置钻石抛光购买状态
    public void setPolish(boolean polish) {
        isPolish = polish;
    }

    //获取石头收藏家购买状态
    public boolean getIsBook() {
        return isBook;
    }

    //获取四叶草购买状态
    public boolean getIsClover() {
        return isClover;
    }

    //获取能量饮料购买状态
    public boolean getIsDrink() {
        return isDrink;
    }

    //获取炸弹购买状态
    public boolean getIsDynamite() {
        return isDynamite;
    }

    //获取钻石抛光购买状态
    public boolean getIsPolish() {
        return isPolish;
    }
}
