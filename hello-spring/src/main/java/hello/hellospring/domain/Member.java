package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity전략: 쿼리로 직접 id 지정해주는 게 아니라 DB가 자동으로 id 지정해주는 것
    private Long id; //고객구분하기 위해 시스템이 정하는 id, 고객이 정하는 것 x

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
