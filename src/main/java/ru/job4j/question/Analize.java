package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        Set<Integer> idSet = new HashSet<>();
        for (User user : previous) {
            if (!current.contains(user)) {
                deleted++;
                idSet.add(user.getId());
            }
        }
        for (User user : current) {
            if (idSet.contains(user.getId())) {
                changed++;
                deleted--;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}
