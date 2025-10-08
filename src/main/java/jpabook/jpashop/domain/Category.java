package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )
    private List<Item> items = new ArrayList<>();

    /**
     * 셀프 양방향 걸기.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany
    @JoinColumn(name = "parent")
    private List<Category> children = new ArrayList<>();

    /**
     * 연관관계 편의 메소드 - 자식 추가
     * @param child
     */
    public void addChildCategory(Category child) {
        this.children.add(child);
        child.setParent(this);
    }
}
