package com.review.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 郑泽雄
 * @Description 相册管理实体类
 * @create 2020-10-02 16:01
 */
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;//编号

    @Column(name = "title")
    private String title;//相册名称

    @Column(name = "image")
    private String image;//相册封面

    @Column(name = "image_items")
    private String imageItems;//图片列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageItems() {
        return imageItems;
    }

    public void setImageItems(String imageItems) {
        this.imageItems = imageItems;
    }
}
