package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfriends.dao.cms.CmsDAOV2;
import com.tfriends.dao.cms.ShortBoard;
import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.cms.WidgetDTOv2;

@Service
public class ShortService {
    @Autowired
    private ShortBoard shorts;

    @Autowired
    private CmsDAOV2 dao;

    public List<WidgetDTOv2> previewShorts() {
        List<WidgetDTOv2> argument = shorts.boardList();
        return shorts.articleResult(argument);
    }

    public DefaultDTOv2 readInIndex(String hash, int no) {
        return dao.boardArticle(hash, no);
    }
}
