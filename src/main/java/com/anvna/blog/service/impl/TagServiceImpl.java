package com.anvna.blog.service.impl;

import com.anvna.blog.NotFoundException;
import com.anvna.blog.dao.TagRepository;
import com.anvna.blog.po.Tag;
import com.anvna.blog.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }


    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = getTag(id);
        if( t == null ){
            throw  new NotFoundException("该标签不存在!");
        }
        BeanUtils.copyProperties(tag, t);
        return saveTag(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {

        return tagRepository.findAllById(convertToList(ids));
    }


    @Override
    public List<Tag> listTag(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    /**
     * 将 , 分隔的 ids 转换成 id list ie 1,2,3
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids){
        List<Long> res = new ArrayList<>();
        if( !"".equals(ids) && ids != null){
            String[] split = ids.split(",");
            for( String s : split ){
                if( StringUtils.isNumeric(s) ){
                    res.add(Long.parseLong(s));
                }else{
                    res.add(getNewTagId(s));
                }
            }
        }
        return res;
    }

    private Long getNewTagId(String name){
        Tag tag = new Tag();
        tag.setName(name);
        Tag save = tagRepository.save(tag);
        return save.getId();
    }
}
