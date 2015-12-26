import com.google.common.collect.Lists;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aartika.rai on 12/26/15.
 */
public class LeaderBoardTest {

    @Test
    public void testAdd() throws Exception {
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        leaderBoard.addToBoard(new User(1), 1);
        leaderBoard.addToBoard(new User(2), 3);
        leaderBoard.addToBoard(new User(3), 6);
        leaderBoard.addToBoard(new User(4), 7);
        leaderBoard.addToBoard(new User(5), 8);
        leaderBoard.addToBoard(new User(6), 7);
        leaderBoard.addToBoard(new User(7), 19);
        leaderBoard.addToBoard(new User(8), 10);
    }

    @Test
    public void testTopN() throws Exception {
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        leaderBoard.addToBoard(new User(1), 1);
        leaderBoard.addToBoard(new User(2), 3);
        leaderBoard.addToBoard(new User(3), 6);
        leaderBoard.addToBoard(new User(4), 7);
        leaderBoard.addToBoard(new User(5), 8);
        leaderBoard.addToBoard(new User(6), 7);
        leaderBoard.addToBoard(new User(7), 19);
        System.out.println(leaderBoard.getNavigableMap());
        assertEquals(Lists.newArrayList(new User(4), new User(5), new User(7)), leaderBoard.topN(3));
    }

    @Test
    public void testNeighbors() throws Exception {
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        leaderBoard.addToBoard(new User(1), 1);
        leaderBoard.addToBoard(new User(2), 3);
        leaderBoard.addToBoard(new User(3), 6);
        leaderBoard.addToBoard(new User(4), 7);
        leaderBoard.addToBoard(new User(5), 8);
        leaderBoard.addToBoard(new User(6), 7);
        leaderBoard.addToBoard(new User(7), 12);
        leaderBoard.addToBoard(new User(8), 45);
        leaderBoard.addToBoard(new User(9), 12);
        leaderBoard.addToBoard(new User(10), 6);
        System.out.println(leaderBoard.getNavigableMap());
        assertEquals(Lists.newArrayList(new User(10), new User(3), new User(6), new User(4), new User(5)),
                leaderBoard.neighbors(new User(6), 2, 2));
    }

    @Test
    public void testNeighborsLimit() throws Exception {
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        leaderBoard.addToBoard(new User(1), 1);
        leaderBoard.addToBoard(new User(2), 3);
        leaderBoard.addToBoard(new User(3), 6);
        leaderBoard.addToBoard(new User(4), 7);
        leaderBoard.addToBoard(new User(5), 8);
        leaderBoard.addToBoard(new User(6), 7);
        leaderBoard.addToBoard(new User(7), 12);
        leaderBoard.addToBoard(new User(8), 45);
        leaderBoard.addToBoard(new User(9), 12);
        leaderBoard.addToBoard(new User(10), 6);
        leaderBoard.addToBoard(new User(11), 50);
        System.out.println(leaderBoard.getNavigableMap());
        assertEquals(Lists.newArrayList(new User(1), new User(2), new User(10), new User(3), new User(6),
                new User(4), new User(5), new User(9), new User(7), new User(8), new User(11)),
                leaderBoard.neighbors(new User(3), 20, 20));
    }

    @Test
    public void testUpdate() throws Exception {
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        leaderBoard.addToBoard(new User(1), 1);
        leaderBoard.addToBoard(new User(2), 3);
        leaderBoard.addToBoard(new User(3), 6);
        leaderBoard.addToBoard(new User(4), 7);
        leaderBoard.addToBoard(new User(5), 8);
        leaderBoard.addToBoard(new User(6), 7);
        leaderBoard.addToBoard(new User(7), 12);
        leaderBoard.addToBoard(new User(8), 45);
        System.out.println(leaderBoard.getNavigableMap());
        assertEquals(Lists.newArrayList(new User(2), new User(3), new User(6), new User(4), new User(5)),
                leaderBoard.neighbors(new User(6), 2, 2));

        leaderBoard.updateBoard(new User(1), 7);
        assertEquals(Lists.newArrayList(new User(3), new User(1), new User(6), new User(4), new User(5)),
                leaderBoard.neighbors(new User(6), 2, 2));
        System.out.println(leaderBoard.getNavigableMap());

    }

    @Test
    public void testAll(){
        NavigableMapLeaderBoard leaderBoard = new NavigableMapLeaderBoard(new SkipListNavigableMap<Double, LinkedMap>());
        for(int i = 0; i < 1000; i++){
            int user = (int) (Math.random()*1000);
            double score = (int) (Math.random() * 1000) + 0.5;
            leaderBoard.addToBoard(new User(user), score);
            if(i % 41 == 0) {
                leaderBoard.topN(20);
                leaderBoard.neighbors(new User(user), 3, 3);
                leaderBoard.updateBoard(new User(user), score + 10.0);
            }
        }
        for(int i = 0; i < 100; i++){
            int user = (int) (Math.random()*1000);
            double score = (int) (Math.random() * 1000) + 0.5;
            leaderBoard.updateBoard(new User(user), score);
        }

    }
}