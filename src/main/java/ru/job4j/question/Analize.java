package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Set<Integer> previousId = prepare(previous);
        Set<Integer> currentId = prepare(current);
        Set<Integer> subtraction = new HashSet<>(previousId);
        subtraction.removeAll(currentId);
        int deleted = subtraction.size();
        Set<Integer> unionId = new HashSet<>(previousId);
        unionId.addAll(currentId);
        int added = unionId.size() - previousId.size();
        Set<User> unionUser = new HashSet<>(previous);
        unionUser.addAll(current);
        int changed = unionUser.size() - unionId.size();
        return new Info(added, changed, deleted);
    }

    private static Set<Integer> prepare(Set<User> data) {
        return data.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

}
