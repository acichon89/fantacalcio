package com.javangarda.fantacalcio.football.contexts;


import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class SchemaVersionCleanTestExecutionListener extends AbstractTestExecutionListener {

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		DataSource dataSource = testContext.getApplicationContext().getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		Statement stmt = connection.createStatement();
        String query = "Delete from schema_version";
        stmt.executeUpdate(query);
	}

	
}
