package net.joins.domain.repository;

import net.joins.domain.entity.Board;
import net.joins.domain.entity.Reply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReplyRepository extends CrudRepository<Reply, Long> {

    @Query("SELECT r FROM Reply r WHERE r.board = ?1 " + " AND r.rno > 0 ORDER BY r.rno ASC ")
    public List<Reply> getRepliesOfBoard(Board board);
}
