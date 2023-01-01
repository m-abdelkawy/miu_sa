package ProductCommandService.model;

public class ProductQuery {
    private int productNumber;
    private String name;
    private String price;
    private int numberInStock;

    public ProductQuery(int productNumber, String name, String price, int numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }
}
