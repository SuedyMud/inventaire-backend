package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZFrascati;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZFrascatiRepository extends JpaRepository<ZFrascati, String> {
    @Query("select zf from ZFrascati zf")
    Page<ZFrascati> findZFrascati(Pageable pageable);

    List<ZFrascati> findAll();
}
