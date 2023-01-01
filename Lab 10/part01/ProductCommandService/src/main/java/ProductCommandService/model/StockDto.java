package ProductCommandService.model;

public class StockDto {
    private int productNumber;
    private int quantity;

    public StockDto(int productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }
}
