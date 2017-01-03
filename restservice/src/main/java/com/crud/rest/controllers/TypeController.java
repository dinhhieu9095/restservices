package com.crud.rest.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.Type;
import com.crud.rest.service.TypeService;
@RestController // combination of @Controller and @ResponseBody annotations
public class TypeController {
	@Autowired
	private TypeService typeService;

	//setter for customerService
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	// Add Flight
		@RequestMapping(value = "/type/new", method = RequestMethod.POST)
		public ResponseEntity<Void> addType(@RequestBody Type type, UriComponentsBuilder ucb) {
			if (typeService.isTypeExist(type)) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else {

				typeService.saveType(type);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucb.path("/type/{typeID}").buildAndExpand(type.getTypeID()).toUri());
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}

		// Get Single Flight
		@RequestMapping(value = "/type/{typeID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Type> getType(@PathVariable("typeID") int typeID) {

			Type type = typeService.findByID(typeID);
			if (type == null) {
				return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Type>(type, HttpStatus.OK);
		}

		// Get All Flights
		@RequestMapping(value = "/types", method = RequestMethod.GET)
		public ResponseEntity<List<Type>> listAllTypes() {
			List<Type> types = typeService.findAllTypes();
			if (types.isEmpty()) {
				return new ResponseEntity<List<Type>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
		}

		// Update Customer
		@RequestMapping(value = "/type/{typeID}", method = RequestMethod.PUT)
		public ResponseEntity<Type> updateFlight(@PathVariable("typeID") int typeID, @RequestBody Type typ) {

			Type type = typeService.findByID(typeID);

			if (type == null) {
				return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
			}

			type.setType(typ.getType());
			type.setTax(typ.getTax());

			typeService.updateType(type);
			return new ResponseEntity<Type>(type, HttpStatus.OK);
		}

		// Delete Customer
		@RequestMapping(value = "/type/{typeID}", method = RequestMethod.DELETE)
		public ResponseEntity<Type> deleteType(@PathVariable("typeID") int typeID) {

			Type type = typeService.findByID(typeID);
			if (type == null) {
				return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
			}

			typeService.deleteTypeById(typeID);
			return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
		}

		// Delete All Customers
		@RequestMapping(value = "/type/deleteall", method = RequestMethod.DELETE)
		public ResponseEntity<Type> deleteAllTypes() {

			typeService.deleteAllTypes();
			return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
		}
		
		
}
