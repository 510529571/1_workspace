/**
 * User: hanwei
 * Date: 15-8-5
 * Time: ����5:48
 */
public class Filter2 implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("����filter2");
        filterChain.doFilter();
    }
}
