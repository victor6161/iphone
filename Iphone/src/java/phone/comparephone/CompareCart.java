/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.comparephone;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import phone.iphone.Souvenir;

/**
 *
 * @author Admin
 */
//@Service
public class CompareCart {

    List<CompareCartItem> items;
    int numberOfItems;
//double total;

    public CompareCart() {
        items = new ArrayList<CompareCartItem>();
        numberOfItems = 0;
//            total = 0;
    }

    public synchronized void addItem(Souvenir iphone) {

        boolean newItem = true;
        for (CompareCartItem scItem : items) {
            if (scItem.getProduct().getId() == iphone.getId()) {
                newItem = false;
//                        scItem.incrementQuantity();
            }
        }

        if (newItem) {
            CompareCartItem scItem = new CompareCartItem(iphone);
            items.add(scItem);
        }
    }

    public synchronized void update(Souvenir iphone, String quantity) {

        short qty = -1;
        // cast quantity as short
        qty = Short.parseShort(quantity);
        if (qty >= 0) {
            CompareCartItem item = null;
            for (CompareCartItem scItem : items) {
                if (scItem.getProduct().getId() == iphone.getId()) {
                    if (qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);
                    } else {
                        // if quantity equals 0, save item and break
                        item = scItem;
                        break;
                    }
                }
            }

            if (item != null) {
                // remove from cart

                items.remove(item);
            }
        }
    }

    public synchronized int getNumberOfItems() {

        numberOfItems = 0;
        for (CompareCartItem scItem : items) {
            numberOfItems += scItem.getQuantity();
        }
        return numberOfItems;
    }

    public List<CompareCartItem> getItems() {
        return items;
    }
}
