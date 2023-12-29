package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZDiscipCref;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZDiscipCrefRepository extends JpaRepository<ZDiscipCref, String> {
    @Query("select zdc from ZDiscipCref zdc")
    Page<ZDiscipCref> findZDiscipCref(Pageable pageable);

    List<ZDiscipCref> findAll();
}
