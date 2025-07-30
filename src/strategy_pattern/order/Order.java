package strategy_pattern.order;

import strategy_pattern.strategies.PayStrategy;

/**
 * 주문 클래스. 사용자가 선택한 결제 관련 Concrete strategy는 알지 못한다.
 * 공통 strategy 인터페이스를 사용하여 결제 관련 데이터를 수집한 내용을 strategy 객체에 위임한다.
 * 이는 데이터베이스에 주문 저장을 할 때 사용할 수 있다.
 */
public class Order {
    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
        // 이 부분에서 strategy로부터 결제 데이터를 수집하고 저장할 수 있다.
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}
