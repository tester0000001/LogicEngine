package LogicEngine.eng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expressions")
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "`value`")  // Escaping the keyword
    private String value;

    public Expression() {}

    public Expression(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() { return value; }

    public void setValue(String value) {
        this.value = value;
    }
}