package com.formacionbdi.springboot.app.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service("serviceItemFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest productoClienteRestFeign;
	
	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return (List<Item>) productoClienteRestFeign.listarProductos().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Producto producto = productoClienteRestFeign.getProducto(id);
		return new Item(producto, cantidad);
	}

}
