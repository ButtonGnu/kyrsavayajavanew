package com.example.kyrsavayajava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPriceDAOImpl implements DAO<ItemPrice>{
    private static final String FIND_ALL_ITEMS = "SELECT * FROM public.prices;";

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
        if(connection!=null){
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
}
