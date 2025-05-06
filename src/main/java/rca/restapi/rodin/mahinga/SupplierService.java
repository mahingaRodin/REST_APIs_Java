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

    public Supplier updateSupplier(Long id,Supplier uptSupplier) {
        return supplierRepo.findById(id)
                .map(supplier -> {
                    supplier.setSupplierName(uptSupplier.getSupplierName());
                    supplier.setAddress(uptSupplier.getAddress());
                   return supplierRepo.save(supplier);
                })
                .orElseThrow(()-> new RuntimeException("Supplier not found with id: " + id));
    }

    public void deleteSupplier(Long id) {
        if(!supplierRepo.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepo.deleteById(id);
    }

}
