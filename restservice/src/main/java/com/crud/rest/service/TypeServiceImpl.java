package com.crud.rest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Type;
import com.crud.rest.dao.TypeDao;

public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeDao typeDao;
	//setter for customerDao
		public void setTypeDao(TypeDao typeDao) {
			this.typeDao = typeDao;
		}

	@Override
	public Type findByID(int typeID) {
		// TODO Auto-generated method stub
		return typeDao.findByID(typeID);
	}

	@Override
	public Type findByName(String type) {
		// TODO Auto-generated method stub
		return typeDao.findByName(type);
	}

	@Override
	public void saveType(Type type) {
		// TODO Auto-generated method stub
		typeDao.saveType(type);
	}

	@Override
	public void updateType(Type type) {
		// TODO Auto-generated method stub
		typeDao.updateType(type);
	}

	@Override
	public void deleteTypeById(int TypeID) {
		// TODO Auto-generated method stub
		typeDao.deleteTypeById(TypeID);
	}

	@Override
	public List<Type> findAllTypes() {
		// TODO Auto-generated method stub
		return typeDao.findAllTypes();
	}

	@Override
	public void deleteAllTypes() {
		// TODO Auto-generated method stub
		typeDao.deleteAllTypes();
	}

	@Override
	public boolean isTypeExist(Type type) {
		// TODO Auto-generated method stub
		return typeDao.isTypeExist(type);
	}


}
