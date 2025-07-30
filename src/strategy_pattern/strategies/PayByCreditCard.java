package strategy_pattern.strategies;

import java.io.*;

/**
 * 신용카드 결제 방식을 구현하는 Concrete strategy
 */
public class PayByCreditCard implements PayStrategy {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.println("카드 번호를 입력해주세요: ");
            String number = br.readLine();
            System.out.println("카드의 유효기간을 입력해주세요.");
            String date = br.readLine();
            System.out.println("CVV 번호를 입력해주세요: ");
            String cvv = br.readLine();
            card = new CreditCard(number, date, cvv);

            // 카드 번호 검증 ...
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 카드 검증 후에 사용자에게 카드 결제 청구 가능
     */
    @Override
    public boolean pay(int paymentAmount) {
        if(cardIsPresent()) {
            System.out.println(paymentAmount + "원을 카드로 결제 합니다.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}
