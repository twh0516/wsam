package com.twh.wsam.data;

import java.sql.Connection;

public interface DataSourceManager {
	Connection getConnection();
}
