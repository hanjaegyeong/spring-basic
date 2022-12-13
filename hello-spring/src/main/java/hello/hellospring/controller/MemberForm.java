package hello.hellospring.controller;


//html의 form을 통해 입력받은 value 관리
public class MemberForm {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
