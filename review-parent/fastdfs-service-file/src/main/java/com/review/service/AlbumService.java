package com.review.service;

import com.github.pagehelper.PageInfo;
import com.review.pojo.Album;

import java.util.List;

/**
 * @author 郑泽雄
 * @Description 相册数据处理层接口
 * @create 2020-10-02 16:07
 */
public interface AlbumService {
    /**
     * @param album 相册实体类
     * @param page  页码
     * @param size  单页大小
     * @return 返回选中页面
     * @Description Album多条件分页查询
     */
    PageInfo<Album> findPage(Album album, int page, int size);

    /**
     * @param page 页码
     * @param size 单页大小
     * @return 返回选中页面
     * @Description 分页查询
     */
    PageInfo<Album> findPage(int page, int size);

    /**
     * @param album 相册实体封装类
     * @return 返回list集合
     * @Description 多条件搜索方法
     */
    List<Album> findList(Album album);

    /**
     * @param id 相册类的id
     * @Description 根据ID删除
     */
    void delete(Long id);

    /**
     * @param album 相册实体类
     * @Description 修改相册
     */
    void update(Album album);

    /**
     * @param album 相册实体类
     * @Description 添加相册
     */
    void add(Album album);

    /**
     * @param id 相册实体类ID
     * @return 返回一个相册
     * @Description 根据ID查询
     */
    Album findById(Long id);

    /**
     * @return 返回所有相册集合
     * @Description 查询所有相册
     */
    List<Album> findAll();
}
