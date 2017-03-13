package me.web.common.enums;

public enum DataSources {
	
	DATA_SOURCE_ONE("dataSourceOne");
	
	private String value;
	
	// 构造方法
    private DataSources(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
