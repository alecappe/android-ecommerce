package sophia.com.ecommerce2.data;

import java.util.List;

/**
 * Created by archimede on 13/07/17.
 */

public class ConfirmOrder {
    private List<Item> cart;
    private String name;
    private String surname;
    private String address;
    private String city;
    private Integer mId;
    private Integer orderNumber;
    private Integer orderItem;


    public Integer getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }


    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }



    public ConfirmOrder() {
    }

    public ConfirmOrder(List<Item> cart, String name, String surname, String address, String city) {
        this.cart = cart;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
