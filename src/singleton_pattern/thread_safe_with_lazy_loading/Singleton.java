package singleton_pattern.thread_safe_with_lazy_loading;

public final class Singleton {
    // 필드가 volatile로 선언되어야만 double check lock이 제대로 작동한다.
    private static volatile Singleton instance;

    public String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        // 여기서 사용된 방식은 '이중 검사 잠금(DCL, Double-Checked Locking)'이라고 불린다.
        // 이 방식은 여러 스레드가 동시에 싱글턴 인스턴스를 얻으려고 할 때,
        // 서로 다른 인스턴스가 생성되는 **경쟁 조건(race condition)**을 방지하기 위해 사용된다.
        //
        // `result`라는 지역 변수가 여기 존재하는 것이 겉보기에는 전혀 의미 없어 보일 수 있다.
        // 그러나 자바에서 이중 검사 잠금을 구현할 때 매우 중요한 **주의사항(caveat)**이 하나 있다.
        // 이 변수는 그 문제를 해결하기 위해 도입된 것이다.
        Singleton result = instance;
        if(result != null) {
            return result;
        }
        synchronized (Singleton.class) {
            if(instance == null) {
                instance = new Singleton(value);
            }
            return instance;
        }
    }
}
