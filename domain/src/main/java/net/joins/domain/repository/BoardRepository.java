package net.joins.domain.repository;

import net.joins.domain.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findAll();

    List<Board> findAll(Sort sort);
}
