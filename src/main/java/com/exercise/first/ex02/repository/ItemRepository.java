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
