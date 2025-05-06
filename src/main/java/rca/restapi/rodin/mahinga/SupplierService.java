package rca.restapi.rodin.mahinga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService{

    @Autowired
    private SupplierRepo supplierRepo;

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public Optional<Supplier> getSupplier(Long id) {
        return supplierRepo.findById(id);
    }

    public Supplier createSupplier(Supplier supplier) {
        supplierRepo.save(supplier);
        return supplier;
    }

}
