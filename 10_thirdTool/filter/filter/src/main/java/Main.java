/**
 * User: hanwei
 * Date: 15-8-5
 * Time: ÏÂÎç5:49
 */
public class Main {
    public static void main(String[] args) {
        FilterChain filterChain=new FilterChain();
        filterChain.addFilter(new Filter1());
        filterChain.addFilter(new Filter2());
        filterChain.doFilter();
    }
}
