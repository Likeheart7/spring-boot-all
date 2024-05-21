package com.chenx.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.FTMapper;
import com.chenx.pojo.FT;
import com.chenx.pojo.FTNode;
import com.chenx.pojo.vo.FTNodeVO;
import com.chenx.pojo.vo.FTVO;
import com.chenx.service.FTService;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

@Service
public class FTServiceImpl extends ServiceImpl<FTMapper, FT> implements FTService {

    @Autowired
    private FTMapper ftMapper;

    @Autowired
    private Cache<Long, FTVO> ftvoCache;
    @Override
    public void removeByFtId(Long ftId) {
        ftMapper.removeFtByFtId(ftId);
        ftMapper.removeNodeByFtId(ftId);
        ftvoCache.invalidate(ftId);
    }

    @Override
    public void updateFtByFtId(FT ft) {
        ftMapper.updateById(ft);
        ftvoCache.invalidate(ft.getFtId());
    }

    @Override
    public void updateNode(FTNode ftNode) {
        ftMapper.updateNode(ftNode);
        ftvoCache.invalidate(ftNode.getFtId());
    }

    @Override
    public void addFTNode(FTNode ftNode) {
        ftMapper.addFTNode();
        ftvoCache.invalidate(ftNode.getFtId());
    }

    @Override
    public FTVO getFt(Long ftId) {
        FTVO cacheFTVO = ftvoCache.getIfPresent(ftId);
//        缓存中有，直接从缓存中拿到然后返回。
        if (Objects.nonNull(cacheFTVO)) {
            return cacheFTVO;
        }
        FTVO ftInfo = new FTVO();
        FT ft = ftMapper.selectById(ftId);
        List<FTNodeVO> ftnodes = ftMapper.getNodeWithEventByFtId(ftId);
        BeanUtils.copyProperties(ft, ftInfo);
        ftInfo.setFtNodes(ftnodes);
        ftvoCache.put(ftId, ftInfo);
        return ftInfo;
    }
}
