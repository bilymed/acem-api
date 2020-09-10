package com.inventory.acme.models;

import java.util.List;

public class Order {

    private List<Item> _items;
    private String _email;

    public List<Item> get_items() {
        return _items;
    }

    public void set_items(List<Item> _items) {
        this._items = _items;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
