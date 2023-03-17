package hello.core.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class helloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        helloLombok HelloLombok = new helloLombok();
        HelloLombok.setName("asfasf");

        String name = HelloLombok.getName();
        System.out.println("name = " + name);


    }
}
