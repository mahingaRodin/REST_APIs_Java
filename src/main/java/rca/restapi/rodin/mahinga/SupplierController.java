package rca.restapi.rodin.mahinga;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService service;

    @GetMapping("/all")
    public List<Supplier> getAllSuppliers() {
        return service.getAllSuppliers();
    }

    @PostMapping("/create")
    public void createSupplier(@RequestBody Supplier supplier) {
        service.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supp = service.getSupplier(id);
        if(supp.isPresent()) {
            return supp.get();
        } else {
            throw new RuntimeException("Supplier not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        try {
            Supplier sup = service.updateSupplier(id, supplier);
            return ResponseEntity.ok(sup);
        } catch (Exception e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        try {
            service.deleteSupplier(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
