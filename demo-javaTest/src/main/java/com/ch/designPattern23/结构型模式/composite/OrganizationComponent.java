package com.ch.designPattern23.结构型模式.composite;

/**
 * @className: OrganizationComponent
 * @Auther: ch
 * @Date: 2021/10/10 09:59
 * @Description: 组合模式组件
 */
public abstract class OrganizationComponent {
    private String name;
    private String desc;

    protected void add(OrganizationComponent organizationComponent) {
        // 默认实现
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        // 默认实现
        throw new UnsupportedOperationException();
    }

    // 构造方法
    public OrganizationComponent(String name, String desc) {
        super();
        this.name = name;
        this.desc = desc;
    }

    // 方法print,做成抽象的,子类都需要实现
    public abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
