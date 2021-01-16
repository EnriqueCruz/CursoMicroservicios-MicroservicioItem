package com.formacionbdi.springboot.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.service.ItemService ;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceItemFeign")
	ItemService itemService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "failBuscarItem")
	@RequestMapping(value="/ver/{id}/cantidad/{cantidad}", method = RequestMethod.GET)
	public Item buscarItem(@PathVariable(name="id") Long id, @PathVariable(name="cantidad") Integer cantidad){
		return itemService.findById(id, cantidad);
	}
	
	public Item failBuscarItem(Long id, Integer cantidad) {
		Item item =  new Item();
		Producto producto = new Producto();
		producto.setId(id);
		producto.setNombre("Fallo en Producto servicios");
		producto.setPrecio(1.00);
		item.setProducto(producto);
		item.setCantidad(cantidad);
		return item;
		
	}
	
}
