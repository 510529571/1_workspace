import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-8-5
 * Time: ÏÂÎç5:44
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private Iterator<Filter> iterator = null;

    public void doFilter() {
        if (iterator == null) {
            iterator = filters.iterator();
        }
        if (iterator.hasNext()) {
            Filter filter = iterator.next();
            filter.doFilter(this);
        }
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }
}
