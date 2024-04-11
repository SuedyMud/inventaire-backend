package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUDiscipCref;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUDiscipCrefRepository extends JpaRepository<ZUDiscipCref, String> {
    @Query("select zud from ZUDiscipCref zud")
    Page<ZUDiscipCref> findZUDiscipCref(Pageable pageable);

    List<ZUDiscipCref> findAll();
}
