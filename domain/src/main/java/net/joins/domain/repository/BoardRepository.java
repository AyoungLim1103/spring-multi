package net.joins.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.QBoard;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    List<Board> findAll();

    List<Board> findAll(Sort sort);
}
