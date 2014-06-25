package net.lab.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Streets")
public class Streets {

    private Integer id;
    private String name;
    private String prim;

    public Streets(){

    }

//***********************************************//
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }
    @Column(name = "prim")
    public String getPrim() {
        return prim;
    }
    //***********************************************//
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrim(String prim) {
        this.prim = prim;
    }
//***********************************************//

}
