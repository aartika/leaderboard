import java.util.List;

/**
 * Created by aartika.rai on 12/26/15.
 */
public interface LeaderBoard {

    public void add(User user, double score);

    public void update(User user, double score);

    public List<User> topN(int n);

    public List<User> neighbors(User user, int n1, int n2);
}
