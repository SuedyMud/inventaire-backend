package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZSFI;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZSFIRepository extends JpaRepository<ZSFI, Integer> {
    @Query("select zs from ZSFI zs")
    Page<ZSFI> findZSFI(Pageable pageable);

    List<ZSFI> findAll();
}
