package org.example;

import org.example.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaBook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();;
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }

    private static void logic(EntityManager em){
        // save
        Member m1 = new Member("id1","dk",28);
        em.persist(m1);

        // 수정
        m1.setAge(27);

        // 조회
        Member foundMember = em.find(Member.class,"id1");
        System.out.println(foundMember);

        // 리스트
        // List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        // System.out.println(members.size());

        em.remove(m1);
    }
}
