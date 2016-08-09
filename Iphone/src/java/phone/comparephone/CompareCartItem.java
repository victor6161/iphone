package phone.comparephone;


import phone.iphone.Souvenir;

public class CompareCartItem {

    Souvenir iphone;
    short quantity;

    public CompareCartItem(Souvenir iphone) {
        this.iphone = iphone;
        quantity = 1;
    }

    public Souvenir getProduct() {
        return iphone;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public double getTotal() {
        double amount = 0;
//amount = (this.getQuantity() *  hmc.getCost().doubleValue());
        amount = (this.getQuantity() * iphone.getPrice());
        return amount;
    }

    @Override
    public String toString() {
        return "CompareCartItem{" + "iphone=" + iphone + ", quantity=" + quantity + '}';
    }
}
