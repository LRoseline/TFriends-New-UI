package com.tfriends.root.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.MenuDTO;
import com.tfriends.service.CmsServiceV2;
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
    private CmsServiceV2 cms;

    @GetMapping("/menu")
    public ResponseEntity<Object> menuLoading() {
        List<MenuDTO> dto = service.homeMenu();

        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @GetMapping("/v2/{hash}")
    public ResponseEntity<Object> getBoardRest(PaginationDTOV2 page, @PathVariable("hash") String hash) {
        SecureDTO secure = cms.userBoardList(hash, page);

        return new ResponseEntity<Object>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }

    @GetMapping("/v2/{hash}/read/{arcno}")
    public ResponseEntity<Object> defaultBoardRead(@PathVariable("hash") String hash, @PathVariable("arcno") int no,
            PaginationDTOV2 page) {
        SecureDTO secure = cms.userBoardRead(hash, no, page);

        return new ResponseEntity<Object>(secure, HttpStatusCode.valueOf(secure.getStatus().getCod()));
    }
}
