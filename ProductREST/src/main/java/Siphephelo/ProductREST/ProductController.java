package Siphephelo.ProductREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/product")
    public List<Product> list(){
        return service.listAll();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        try {
            Product product = service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public void add(@RequestBody Product product){
        service.save(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@RequestBody Product product,
                                          @PathVariable Integer id){
        try {
            Product existProduct = service.get(id);
            service.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
