package com.javangarda.fantacalcio.util.factories;

import com.javangarda.fantacalcio.util.entities.DefaultEntity;

public interface EntityFactory<T extends DefaultEntity> {

	T create();
}
