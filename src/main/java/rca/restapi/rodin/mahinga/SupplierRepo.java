package rca.restapi.rodin.mahinga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    @Query("select s from Supplier as s where s.address=:address")
    List<Supplier> findByAddress(@Param("address") String address);

    @Query(value = "select a from  Supplier as a where a.age=:age", nativeQuery = true)
    List<Supplier> findByAge(@Param(("age"))Long age);
}
