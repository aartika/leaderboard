import java.util.List;

/**
 * Created by aartika.rai on 12/26/15.
 */
public interface LeaderBoard<T> {

    public void addToBoard(T user, double score);

    public void updateBoard(T user, double score);

    public List<T> topN(int n);

    public List<T> neighbors(T user, int n1, int n2);
}
