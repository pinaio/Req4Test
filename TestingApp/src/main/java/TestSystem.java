import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class TestSystem implements Serializable {
    private String name = "TestingApp 0.1 alpha";
    private ArrayList<Requirement> reqList;

    public TestSystem() {
        reqList = new ArrayList<>();
        Requirement req1 = new Requirement(1, "Erstes Requirement","Das ist das erste","Pascal Wagner");
        Requirement req2 = new Requirement(2, "Zweites Requiremen","Das ist das erste","Pascal Wagner");
        Requirement req3 = new Requirement(3, "Erstes Requiremen","Das ist das erste","Pascal Wagner");
        Requirement req4 = new Requirement(4, "Erstes Requiremen","Das ist das erste","Pascal Wagner");
        Requirement req5 = new Requirement(5, "Erstes Requiremen","Das ist das erste","Pascal Wagner");
        Requirement req6 = new Requirement(6, "Erstes Requiremen","Das ist das erste","Pascal Wagner");
        reqList.add(req1);
        reqList.add(req2);
        reqList.add(req3);
        reqList.add(req4);
        reqList.add(req5);
        reqList.add(req6);
        System.out.println(reqList.get(0).getDescription());


    }

    public String getName() {
        return name;
    }

    public ArrayList<Requirement> getReqList() {
        return reqList;
    }
}
