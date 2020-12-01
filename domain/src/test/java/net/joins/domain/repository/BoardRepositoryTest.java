package net.joins.domain.repository;

import com.querydsl.core.BooleanBuilder;
import lombok.extern.java.Log;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Member;
import net.joins.domain.entity.QBoard;
import net.joins.domain.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active:arumu")
@Commit
@Log
public class BoardRepositoryTest {
    @Autowired
    BoardRepository repo;
    @Autowired
    MemberRepository userRepo;
    @Autowired
    BoardService boardService;

    @Test
    public void insertBoardDummies(){

        IntStream.range(1,10).forEach(i->{
            Member member = new Member();
            //member.setMemberSeq((long) i);
            member.setMemberId("member"+i);
            member.setName("user"+i);
            member.setEmail("dfdfdf"+i+"@example.com");
            userRepo.save(member);

            Board board = new Board();
            //board.setBno((long) i);
            board.setTitle("title"+i);
            board.setContent("Content"+i);
            board.setDelYN("N");
            board.setMember(member);
            repo.save(board);
        });
    }

    @Test
    public void testList1(){
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC,"bno");
        Page<Board> result = repo.findAll(
                repo.makePredicate(null,null),pageable);

        log.info("PAGE : "+result.getPageable());
        log.info("===================================");
        result.getContent().forEach(board -> log.info(""+board));
    }

    @Test
    public void makePredicate() {
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = new QBoard("board");

        //bno>0
        builder.and(board.bno.gt(0));

        log.info(builder.toString());
    }
}