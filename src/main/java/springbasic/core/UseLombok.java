package springbasic.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UseLombok {
    private String name;

    // lombok을 이용하면 getter, setter 등 함수 만들기 대신, 애노테이션을 이용하여 대신 역할을 한다. 그외 다른것도 있으니 넷에서 검색
    
    public static void main(String[] args) {
        UseLombok useLombok = new UseLombok();
        useLombok.setName("ShinDongA");
        
        System.out.println("name = " + useLombok);
    }
}
