package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 연관관계의 주인이 어떤 DB컬럼과 함께 join 처리를 할건지 기재한다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrderStatus status;

    /**
     * 연관관계 메소드 - 멤버와 주문을 연결해주는 메소드
     * @param member
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    /**
     * 연관관계 메소드 - 주문과 주문상품을 연결해주는 메소드
     * @param orderItem
     */
    public void setOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * 연관관계 메소드 - 주문과 배송을 연결해주는 메소드
     * @param delivery
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
