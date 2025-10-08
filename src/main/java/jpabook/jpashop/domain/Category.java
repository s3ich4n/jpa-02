package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany()
    @JoinTable()
    private List<Item> items = new ArrayList<>();
}