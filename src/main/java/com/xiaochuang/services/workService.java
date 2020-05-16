package com.xiaochuang.services;

import com.xiaochuang.setClasses.Work;


import java.util.List;

public interface workService {
    public List<Work> selectTenWorksByDate(int startindex);
    public List<Work> selectWorkByTitle(String title);
    public List<Work> selectFiveWorksByWorkid(List<Integer> workidlist);
    public int insertWork(String title,String type);
    public int selectWorkidByTitle(String title);
}
