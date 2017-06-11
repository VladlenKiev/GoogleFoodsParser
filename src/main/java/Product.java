/**
 * Created by Владимир on 11.06.2017.
 */
public class Product implements Comparable<Product>{
    private String ProductId;
    private int counter=1;

    public Product(String ProductId) {
        this.ProductId = ProductId;
    }
    public Product() {    }


    public void increaseCounterCommentPerProduct(){
        this.counter+=1;
    };

    public String getProductId() {
        return ProductId;
    }

    public int getCounter() {
        return counter;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    @Override
    public String toString() {
        return "Product( Productid:"+ProductId+" counter: "+counter+")";
    }

    @Override
    public int compareTo(Product product) {
        if (this.counter>product.getCounter())
            return 1;
        else if(this.counter<product.getCounter())
            return -1;
        else
            return 0;
    }
}
