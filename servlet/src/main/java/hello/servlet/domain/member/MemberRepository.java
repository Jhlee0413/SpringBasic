package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려 필요!
 */

public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();

    // 회원 아이디 초기화
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    //인스턴스 출력
    public static MemberRepository getInstance(){
        return instance;
    }

    //생성자
    private MemberRepository() {
    }

    //새로운 멤버 등록
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //아이디 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    //모든 멤버 출력
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 모든 값을 꺼네 배열로 넘겨
    }

    public void clearStore(){
        store.clear();
    }


}
