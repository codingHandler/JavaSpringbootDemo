package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: Hours
 * @Auther: ch
 * @Date: 2021/10/11 17:42
 * @Description: 建造设模式
 *
 */
public class Hours {
    // 墙
    private String wall;
    // 电视
    private String TV;
    // 沙发
    private String sofa;
    // 楼顶
    private String roof;

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getTV() {
        return TV;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public String getSofa() {
        return sofa;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    @Override
    public String toString() {
        return "Hours{" +
                "wall='" + wall + '\'' +
                ", TV='" + TV + '\'' +
                ", sofa='" + sofa + '\'' +
                ", roof='" + roof + '\'' +
                '}';
    }
}
