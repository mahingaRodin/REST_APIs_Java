package rca.restapi.rodin.mahinga;


import org.springframework.beans.factory.annotation.Autowired;
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
}
