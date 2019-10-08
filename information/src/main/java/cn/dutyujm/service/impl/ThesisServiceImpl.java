package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.ThesisMapper;
import cn.dutyujm.pojo.Thesis;
import cn.dutyujm.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisMapper thesisMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Thesis record) {
        return 0;
    }

    @Override
    public int insertSelective(Thesis record) {
        return thesisMapper.insertSelective(record);
    }

    @Override
    public Thesis selectByPrimaryKey(Integer id) {
        return thesisMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Thesis record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Thesis record) {
        return thesisMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Thesis> getThesisList() {
        return thesisMapper.getThesisList();
    }

//    @Override
//    public boolean  updateThesisSelected(Integer id){
//        return thesisMapper.updateThesisSelected(id);
//    };

    @Override
    public  Thesis selectThesisBySid(Integer sid){
        return thesisMapper.selectThesisBySid(sid);
    }


    @Override
    public  List<Thesis> selectThesisByName(String title){

        return thesisMapper.selectThesisByName(title );
    }

    @Override
   public List<Thesis> pageThesis(Integer page,Integer limit){
        int tmp = (page-1)*limit;
        return thesisMapper.pageThesis(tmp,limit);
    }



}
