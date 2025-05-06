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
    public ResponseEntity<Supplier> upsertSupplier(@PathVariable Long id, @RequestBody Supplier supplierData) {
        Optional<Supplier> optional = service.getSupplier(id);

        Supplier savedSupplier;
        if (optional.isPresent()) {
            // Update existing
            Supplier existing = optional.get();
            existing.setSupplierName(supplierData.getSupplierName());
            existing.setAddress(supplierData.getAddress());
            existing.setAge(supplierData.getAge());
            savedSupplier = service.saveSupplier(existing);
        } else {
            savedSupplier = service.createSupplier(supplierData);
        }

        return ResponseEntity.ok(savedSupplier);
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

    @GetMapping("/address/{address}")
    public List<Supplier> getSupplierByAddress(@PathVariable String address) {
        return service.findByAddress(address);
    }

    @GetMapping("/age/{age}")
    public List<Supplier> getSupplierByAge(@PathVariable Long age) {
        return service.findByAge(age);
    }
}
