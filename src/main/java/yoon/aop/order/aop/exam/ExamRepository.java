package yoon.aop.order.aop.exam;

import org.springframework.stereotype.Repository;
import yoon.aop.order.aop.exam.annotation.Retry;
import yoon.aop.order.aop.exam.annotation.Trace;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번에 1번 실패하는 요청
     * */
    @Trace
    @Retry(4) // value 바꿀수도있음
    public String save(String itemId){
        seq++;
        if(seq % 5 ==0){
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
