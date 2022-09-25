package com.example.electronicshop.dao;

import java.sql.Connection;

public interface TransactionOperation {
     Object operation(Connection connection);
}
