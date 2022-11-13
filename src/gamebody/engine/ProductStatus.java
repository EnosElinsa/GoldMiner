package gamebody.engine;

/**
 * 该类用于标记商店产品的购买状态。
 * <p>变量设置为{@code true}代表用户已经购买该产品，{@code false}代表用户未购买该产品。
 * @author JiajiaPig
 * @see Shop
 * @see Rope
 */
public class ProductStatus {

    // 是否选择炸药、力量饮料、四叶草、石头收藏书和钻石抛光油
    private boolean isDynamite;
    private boolean isDrink;
    private boolean isClover; 
    private boolean isBook;
    private boolean isPolish; 

    public ProductStatus() {}

    /**
     * 设置石头收藏家书籍购买状态
     */
    public void setBook(boolean book) {
        isBook = book;
    }

    /**
     * 设置四叶草购买状态
     * @param clover
     */
    public void setClover(boolean clover) {
        isClover = clover;
    }

    /**
     * 设置能量饮料购买状态
     * @param drink
     */
    public void setDrink(boolean drink) {
        isDrink = drink;
    }

    /**
     * 设置炸弹购买状态
     * @param dynamite
     */
    public void setDynamite(boolean dynamite) {
        isDynamite = dynamite;
    }

    /**
     * 设置钻石抛光购买状态
     * @param polish
     */
    public void setPolish(boolean polish) {
        isPolish = polish;
    }

    /**
     * 获取石头收藏家购买状态
     * @return
     */
    public boolean getIsBook() {
        return isBook;
    }

    /**
     * 获取四叶草购买状态
     * @return
     */
    public boolean getIsClover() {
        return isClover;
    }

    /**
     * 获取能量饮料购买状态
     * @return
     */
    public boolean getIsDrink() {
        return isDrink;
    }

    /**
     * 获取炸弹购买状态
     * @return
     */
    public boolean getIsDynamite() {
        return isDynamite;
    }

    /**
     * 获取钻石抛光购买状态
     * @return
     */
    public boolean getIsPolish() {
        return isPolish;
    }
}
