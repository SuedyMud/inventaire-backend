package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZFac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface  ZFacRepository extends JpaRepository<ZFac, String> {
    @Query("select zf from ZFac zf")
    Page<ZFac> findZFac(Pageable pageable);

    List<ZFac> findAll();

}
