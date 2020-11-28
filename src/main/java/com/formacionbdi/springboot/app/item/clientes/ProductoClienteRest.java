package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formacionbdi.springboot.app.item.models.Producto;


//La clase al estar anotada con FeignClient , es elegible para ser inyectada
@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	
	@RequestMapping(value = "/producto", method = RequestMethod.GET)
	public List<Producto> listarProductos();
	
	@RequestMapping(value = "/producto/{id}", method = RequestMethod.GET)
	public Producto getProducto(@PathVariable(name = "id") Long id);
	
}
