package com.review.controller;

import com.github.pagehelper.PageInfo;
import com.review.entity.Result;
import com.review.entity.StatusCode;
import com.review.pojo.Album;
import com.review.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 郑泽雄
 * @Description 相册管理服务控制层接口
 * @create 2020-10-02 16:30
 */
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {
    @Autowired(required = false)
    private AlbumService albumService;

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Album album, @PathVariable int page, @PathVariable int size) {
        //执行搜索
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //分页查询
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    @PostMapping(value = "/search")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album) {
        List<Album> list = albumService.findList(album);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Album album, @PathVariable Long id) {
        //设置主键值
        album.setId(id);
        //修改数据
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", album);
    }

    @GetMapping
    public Result<Album> findAll() {
        List<Album> list = albumService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }


}
