package strategy_pattern.strategies;

/**
 * Strategy를 위한 공통 인터페이스
 */

public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
