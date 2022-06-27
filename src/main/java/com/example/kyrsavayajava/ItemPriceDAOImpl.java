package com.example.kyrsavayajava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPriceDAOImpl implements DAO<ItemPrice> {
    String     dbURL = "jdbc:postgresql://localhost:5432/SPARES3";
    String     user  = "postgres";
    String     pass  = "2001Eliz!!";
    Connection conn;
    private static final String FIND_ALL_ITEMS     = "SELECT * FROM public.prices;";
    private static final String FIND_PRICE_BY_ITEM = "SELECT price FROM public.items_price where item=?;";

    public ItemPriceDAOImpl() {
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(ItemPrice entity) {

    }

    @Override
    public void update(ItemPrice entity) {

    }

    @Override
    public ItemPrice findById(Long id) {
        return null;
    }

    @Override
    public List<ItemPrice> findAll() {
        Connection connection = buildConnection();
        if (connection != null) {
            List<ItemPrice> itemPrices = new ArrayList<>();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ITEMS);
                ResultSet         resultSet         = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ItemPrice itemPrice = new ItemPrice();
                    itemPrice.setId(resultSet.getLong("id"));
                    itemPrice.setItem(resultSet.getString("item"));
                    itemPrice.setPrice(resultSet.getDouble("price"));
                    itemPrices.add(itemPrice);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return itemPrices;
        }
        return null;
    }

    public Long findPriceByItem(String item) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_PRICE_BY_ITEM);
            preparedStatement.setString(1, item);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
