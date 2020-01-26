package exception;

import java.util.ArrayList;

public class Faculty {
    private String name;
    private int id;
    private ArrayList<Integer> groupIds=new ArrayList<>();
    private static int serialId=1;
    public Faculty(String name) {
        this.name = name;
        id=serialId;
        serialId++;
    }
    public boolean hasGroup(Group group){
        return groupIds.contains(group.getId());
    }
    void addGroupId(int id){
        groupIds.add(id);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGroupIds() {
        return new ArrayList<>(groupIds);
    }

    public int getId() {
        return id;
    }
}
