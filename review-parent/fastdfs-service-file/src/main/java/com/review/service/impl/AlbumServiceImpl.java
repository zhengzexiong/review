package com.review.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.review.dao.AlbumMapper;
import com.review.pojo.Album;
import com.review.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 郑泽雄
 * @Description 相册数据处理服务实现层
 * @create 2020-10-02 16:15
 */
public class AlbumServiceImpl implements AlbumService {
    //注入
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * @param album 相册实体类
     * @param page  页码
     * @param size  单页大小
     * @return 分页结果
     * @Description 多条件查询
     */
    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(album);
        //执行搜索
        return new PageInfo<>(albumMapper.selectByExample(example));
    }

    /**
     * @param page 页码
     * @param size 单页大小
     * @return 分页结果
     * @Description 分页查询
     */
    @Override
    public PageInfo<Album> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(albumMapper.selectAll());
    }

    /**
     * @param album 相册实体封装类
     * @return Album集合
     * @Description 条件查询
     */
    @Override
    public List<Album> findList(Album album) {
        //构建查询条件
        Example example = createExample(album);
        //根据构建条件查询数据
        return albumMapper.selectByExample(example);
    }

    public Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            //编号
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            //相册名称
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            //相册封面
            if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            //图片列表
            if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }

    /**
     * @param id 相册类的id
     * @Description 根据id删除
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);

    }

    /**
     * @param album 相册实体类
     * @Description 修改相册
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);

    }

    /**
     * @param album 相册实体类
     * @Description 添加相册
     */
    @Override
    public void add(Album album) {
        albumMapper.insert(album);

    }

    /**
     * @param id 相册实体类ID
     * @return Album
     * @Description 根据ID查询
     */
    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * @return List<Album>
     * @Description 查询所有
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }
}
