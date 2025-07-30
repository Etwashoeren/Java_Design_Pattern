package strategy_pattern;

import strategy_pattern.order.Order;
import strategy_pattern.strategies.PayByCreditCard;
import strategy_pattern.strategies.PayByKakaoPay;
import strategy_pattern.strategies.PayStrategy;

import java.io.*;
import java.util.*;

public class Demo {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 220000);
        priceOnProducts.put(2, 185000);
        priceOnProducts.put(3, 110000);
        priceOnProducts.put(4, 89000);
    }

    public static void main(String[] args) throws IOException {
        while(!order.isClosed()) {
            int cost;

            String continueChoice;

            do {
                System.out.print("상품을 선택해 주세요:" + "\n" +
                        "1 - 메인보드" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - 하드 디스크" + "\n" +
                        "4 - 메모리" + "\n");

                int choice = Integer.parseInt(br.readLine());
                cost = priceOnProducts.get(choice);
                System.out.println("수량: ");
                int count = Integer.parseInt(br.readLine());
                order.setTotalCost(cost * count);
                System.out.print("계속 해서 상품을 선택하시겠습니까? Y/N: ");
                continueChoice = br.readLine();
            } while(continueChoice.equalsIgnoreCase("Y"));

            if(strategy == null) {
                System.out.println("결제 수단을 선택해주세요:" + "\n" +
                        "1 - 카카오페이" + "\n" +
                        "2 - 체크/신용카드");

                String paymentMethod = br.readLine();

                if(paymentMethod.equals("1")) {
                    strategy = new PayByKakaoPay();
                }
                else {
                    strategy = new PayByCreditCard();
                }
            }

            order.processOrder(strategy);

            System.out.print(order.getTotalCost() + "원을 결제하시려면 P를, 계속 해서 쇼핑하시려면 C를 눌러주세요. P/C: ");
            String proceed = br.readLine();
            if(proceed.equalsIgnoreCase("P")) {
                if(strategy.pay(order.getTotalCost())) {
                    System.out.println("결제가 완료되었습니다.");
                }
                else {
                    System.out.println("결제가 실패하였습니다.");
                }
                order.setClosed();
            }
        }
    }
}
