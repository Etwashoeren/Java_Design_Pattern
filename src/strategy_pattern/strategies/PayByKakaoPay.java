package strategy_pattern.strategies;

import java.io.*;
import java.util.*;

/**
 * 카카오 페이 결제 방식을 구현하는 Concrete strategy
 */
public class PayByKakaoPay implements PayStrategy {
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("test123", "test1@test.com");
        DATA_BASE.put("qwerty", "test2@test.com");
    }

    /**
     * 사용자 데이터 입력 받기
     */
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.println("고객님의 이메일을 입력해주세요: ");
                email = br.readLine();
                System.out.println("비밀번호를 입력해주세요: ");
                password = br.readLine();
                if(verify()) {
                    System.out.println("인증이 완료되었습니다.");
                }
                else {
                    System.out.println("이메일 혹은 비밀번호가 올바르지 않습니다.");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * 추후 결제 시도를 위한 사용자 데이터 저장
     */
    @Override
    public boolean pay(int paymentAmount) {
        if(signedIn) {
            System.out.println(paymentAmount + "원을 카카오 페이로 결제 합니다.");
            return true;
        }
        else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
