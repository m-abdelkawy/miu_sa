package StockService.domain;

public class Stock {
    private String stockNumber;
    private String price;

    public Stock() {
    }

    public Stock(String stockNumber, String price) {
        this.stockNumber = stockNumber;
        this.price = price;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockNumber='" + stockNumber + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
