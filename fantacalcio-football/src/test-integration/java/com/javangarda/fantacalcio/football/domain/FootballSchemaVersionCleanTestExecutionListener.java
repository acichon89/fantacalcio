package com.javangarda.fantacalcio.football.domain;


import com.javangarda.fantacalcio.util.testsupport.SchemaVersionCleanTestExecutionListener;

public class FootballSchemaVersionCleanTestExecutionListener extends SchemaVersionCleanTestExecutionListener {

	@Override
	protected String getCleanQuery() {
		return "Delete from players; Delete from clubs;";
	}

}
