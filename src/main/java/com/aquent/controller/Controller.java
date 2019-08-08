/*
 * package com.aquent.controller;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.aquent.daoimpl.ProductDaoImpl; import com.aquent.model.Product;
 * 
 * @RestController
 * 
 * @RequestMapping("/save") public class Controller {
 * 
 * @Autowired private ProductDaoImpl productDaoImpl;
 * 
 * @RequestMapping(path="/product", method=RequestMethod.POST) public HttpStatus
 * saveProduct(@Valid @RequestBody Product product) {
 * productDaoImpl.saveProdct(product); return HttpStatus.OK;
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */