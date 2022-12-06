package hello.hellospring.domain;

public class Member {

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
