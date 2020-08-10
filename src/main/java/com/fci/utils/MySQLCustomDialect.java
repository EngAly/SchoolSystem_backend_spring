package com.fci.utils;

import org.hibernate.dialect.MySQL8Dialect;

public class MySQLCustomDialect extends MySQL8Dialect {
	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
//		return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci";
	}

}
