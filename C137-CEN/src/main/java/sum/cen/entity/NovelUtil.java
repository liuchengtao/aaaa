package sum.cen.entity;

/**
 * 字符云数据封装
 * @author cen    2018年6月28日下午5:50:55
 *
 */
public class NovelUtil {
	private String name;
	private int   value;
	@Override
	public String toString() {
		return "NovelUtil [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
