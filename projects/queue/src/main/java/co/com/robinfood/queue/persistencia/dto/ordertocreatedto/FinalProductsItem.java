package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

import java.util.List;

@Data
public class FinalProductsItem {
    private String image;
    private int quantity;
    private double totalPrice;
    private List<GroupsItem> groups;
    private List<Object> taxes;
    private Article article;
    private List<Object> discounts;
    private Size size;
    private double price;
    private String name;
    private int id;
    private Object category;
    private String sku;
    private Brand brand;
    private List<Object> removedPortions;
}