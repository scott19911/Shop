package com.example.electronicshop.dao;

import java.sql.Connection;

public interface TransactionOperation<E> {
    E operation(Connection connection);
}
