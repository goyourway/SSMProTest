package com.xiaochuang.servicesImpl;

import com.xiaochuang.mappers.workMapper.workMapper;
import com.xiaochuang.services.workService;
import com.xiaochuang.setClasses.Work;

import java.util.List;

public class workServiceImpl implements workService {

    private workMapper workmapper;
    public void setWorkmapper(workMapper workmapper){
        this.workmapper = workmapper;
    }

    @Override
    public List<Work> selectTenWorksByDate(int startindex) {
        return workmapper.selectTenWorksByDate(startindex);
    }

    @Override
    public List<Work> selectWorkByTitle(String title) {
        return workmapper.selectWorkByTitle(title);
    }

    @Override
    public List<Work> selectFiveWorksByWorkid(List<Integer> workidlist) {
        return workmapper.selectFiveWorksByWorkid(workidlist);
    }

    @Override
    public int insertWork(String title, String type) {
        return workmapper.insertWork(title,type);
    }

    @Override
    public int selectWorkidByTitle(String title) {
        return workmapper.selectWorkidByTitle(title);
    }

}
