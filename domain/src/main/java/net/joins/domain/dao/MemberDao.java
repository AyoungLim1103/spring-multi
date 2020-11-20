package net.joins.domain.dao;

import net.joins.domain.entity.Member;
import net.joins.domain.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {

    @Autowired
    SqlSession sqlSession;

    @Autowired
    MemberMapper memberMapper;

    public String getDate() {
        String result = memberMapper.getDate();
        return result;
    }

    public List<Member> allMembers() {
        List<Member> result = sqlSession.selectList("allMembers");
        return result;
    }


}
