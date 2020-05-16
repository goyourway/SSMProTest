package com.xiaochuang.mappers.workMapper;

import com.xiaochuang.setClasses.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface workMapper {

    public List<Work> selectTenWorksByDate(@Param("startindex")int startindex);
    public List<Work> selectWorkByTitle(@Param("title") String title);
    public List<Work> selectFiveWorksByWorkid(@Param("workidlist") List<Integer> workidlist);
    public int insertWork(@Param("title") String title,@Param("type") String type);
    public int selectWorkidByTitle(@Param("title") String title);

}
