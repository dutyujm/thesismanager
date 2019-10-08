package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.TitleMapper;
import cn.dutyujm.pojo.Title;
import cn.dutyujm.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public int deleteByPrimaryKey(Integer titleId) {
        return 0;
    }

    @Override
    public int insert(Title record) {
        return 0;
    }

    @Override
    public int insertSelective(Title record) {
        return 0;
    }

    @Override
    public Title selectByPrimaryKey(Integer titleId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Title record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Title record) {
        return 0;
    }

    @Override
    public List<Title> getTitleAndTeachers(){
        return titleMapper.getTitleAndTeachers();
    }

}
