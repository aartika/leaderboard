import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.LinkedMap;

import java.util.*;

/**
 * Created by aartika.rai on 12/26/15.
 */
public class NavigableMapLeaderBoard implements LeaderBoard{

    private NavigableMap<Double, LinkedMap> navigableMap;
    private Map<User, Double> userToScoreMap = Maps.newHashMap();

    public NavigableMapLeaderBoard(NavigableMap<Double, LinkedMap> navigableMap) {
        this.navigableMap = navigableMap;
    }

    public NavigableMap<Double, LinkedMap> getNavigableMap() {
        return navigableMap;
    }

    public void add(User user, double score) {
        userToScoreMap.put(user, score);
        LinkedMap users;
        if (navigableMap.containsKey(score)) {
            users = navigableMap.get(score);
        } else {
            users = new LinkedMap();
        }
        users.put(user, null);
        navigableMap.put(score, users);
    }

    public void update(User user, double score){
        if(userToScoreMap.containsKey(user)){
            Double currentScore = userToScoreMap.get(user);
            if(currentScore != score){
                userToScoreMap.put(user, score);
                LinkedMap scoreMap = navigableMap.get(currentScore);
                scoreMap.remove(user);
                if(scoreMap.size() == 0)
                    navigableMap.remove(currentScore);
                add(user, score);
            }
        }
        else
            add(user, score);
    }

    public List<User> topN(int n) {
        Map.Entry<Double, LinkedMap> highestKey = navigableMap.lastEntry();
        return lowN((User) highestKey.getValue().firstKey(), n);
    }

    public List<User> neighbors(User user, int n1, int n2) {
        List<User> users = Lists.newArrayList();
        users.addAll(lowN(user, n1));
        List<User> high = highN(user, n2 + 1);
        users.addAll(high.subList(1, high.size()));
        return users;
    }

    private List<User> lowN(User user, int n) {

        List<User> users = Lists.newArrayList();
        while (n > 0 && user != null) {
            users.add(user);
            Double currentScore = userToScoreMap.get(user);
            LinkedMap currentLinkedList = navigableMap.get(currentScore);
            if (currentLinkedList.nextKey(user) != null) {
                user = (User) currentLinkedList.nextKey(user);
            } else {
                Map.Entry<Double, LinkedMap> nextEntry = navigableMap.lowerEntry(currentScore);
                user = nextEntry == null ? null : (User) nextEntry.getValue().firstKey();
            }
            n--;
        }
        return Lists.reverse(users);
    }

    private List<User> highN(User user, int n) {

        List<User> users = Lists.newArrayList();
        while (n > 0 && user != null) {
            users.add(user);
            Double currentScore = userToScoreMap.get(user);
            LinkedMap currentLinkedList = navigableMap.get(currentScore);
            if (currentLinkedList.previousKey(user) != null) {
                user = (User) currentLinkedList.previousKey(user);
            } else {
                Map.Entry<Double, LinkedMap> nextEntry = navigableMap.higherEntry(currentScore);
                user = nextEntry == null ? null : (User) nextEntry.getValue().lastKey();
            }
            n--;
        }
        return users;
    }

}
