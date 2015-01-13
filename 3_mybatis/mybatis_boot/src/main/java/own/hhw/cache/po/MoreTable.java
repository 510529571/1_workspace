package own.hhw.cache.po;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-16
 * Time: ÏÂÎç6:53
 * To change this template use File | Settings | File Templates.
 */
public class MoreTable implements Serializable {
    private static final long serialVersionUID = 8751282105532159741L;

    private String id;
    private String classId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
