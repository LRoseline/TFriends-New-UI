package com.tfriends.root.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.pagination.CountPageDTO;
import com.tfriends.dto.system.MenuDTO;
import com.tfriends.dto.system.TrashDTO;
import com.tfriends.service.CmsService;
import com.tfriends.service.HomeService;

@RestController
@RequestMapping("/api/community")
public class CmsRest {

    @GetMapping("/normal")
    public ResponseEntity<Object> requestMethodName() {
        DefaultDTO dto = new DefaultDTO();
        dto.setContent("[h1]Hello, world![/h1]");
        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @Autowired
    private HomeService service;

    @Autowired
    private CmsService cms;

    @GetMapping("/menu")
    public ResponseEntity<Object> menuLoading() {
        List<MenuDTO> dto = service.homeMenu();

        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<Object> meunAPI(CountPageDTO dto) {
        List<DefaultDTO> list = cms.userBoardList(dto);

        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }

    @GetMapping("/recycle")
    public List<TrashDTO> getTrashCan() {
        return cms.bearerTrash();
    }
}
