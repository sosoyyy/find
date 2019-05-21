package com.yjc.find.base.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.find.utils.MyUtil;
import lombok.Data;

import java.util.Map;

@Data
public class MyPage<T> extends Page<T> {

    public MyPage(Map<String,Object> params){
        if(params!=null){
            int size= MyUtil.getInt(params.get("limit"));
            int current=MyUtil.getInt(params.get("page"));
            if(size<1)this.limit=10;
            if(size>100)this.limit=100;
            current=current<1?1:current;
            this.beginIndex=(current-1)*size;
            super.setSize(size);
            super.setCurrent(current);
            params.put("limit", size);
            params.put("beginIndex", beginIndex);
            this.params=params;
        }
    }

    private int beginIndex;

    private int limit;

    private int count;

    private Map<String,Object> params;

    public Map<String,Object> getParams(){
        return params;
    }

}

