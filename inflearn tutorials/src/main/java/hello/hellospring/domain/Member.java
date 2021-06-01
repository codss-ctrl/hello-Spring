package hello.hellospring.domain;


import javax.persistence.*;

@Entity
public class Member {
    //IDENTITY : 데이터베이스에 insert 시 id 자동 생성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}