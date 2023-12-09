package com.exercise.first.ex02.repository;


import com.exercise.first.ex02.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    // 등록
    public void save(Item item) {
        if (item == null) {
            em.persist(item);
        } else {
            em.merge(item);
            // 준영속 엔티티
            // 이후에 사용할 경우
            // Item merge = em.merge(item);
            // 위처럼 사용해야함
        }
    }

    // 1개 조회
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    // 전체 조회
    public List<Item> findAll(){
        return em.createQuery("select m from Item m", Item.class).getResultList();
    }
}
