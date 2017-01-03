package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Type;

public interface TypeService {
	Type findByID(int typeID);
	Type findByName(String type);
	void saveType(Type type);

	void updateType(Type type);

	void deleteTypeById(int TypeID);

	List<Type> findAllTypes();

	void deleteAllTypes();

	boolean isTypeExist(Type type);
}
