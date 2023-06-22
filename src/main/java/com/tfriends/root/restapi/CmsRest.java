package com.tfriends.root.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.pagination.SearchDTOV2;
import com.tfriends.dto.system.MenuDTO;
import com.tfriends.exception.board.BoardNotFoundException;
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

    public SecureDTO hashCheck(String hash) {
        SecureDTO secure = cms.hashCheck(hash);

        return secure;
    }

    @GetMapping("/v2/{hash}")
    public ResponseEntity<Object> getBoardRest(PaginationDTOV2 page, @PathVariable("hash") String hash,
            SearchDTOV2 dto) {
        SecureDTO secure = this.hashCheck(hash);

        if (secure != null) {
            page.setTotalpage(cms.userBoardCount(secure.getBoard(), dto));

            if (page.getEnd() < dto.getPage()) {
                dto.setPage(page.getEnd());
            }

            if (dto.getPage() <= 0) {
                dto.setPage(1);
            }

            secure.setResult(cms.userBoardList(secure.getBoard(), dto));

            return new ResponseEntity<Object>(secure, HttpStatus.OK);
        } else {
            throw new BoardNotFoundException(hash);
        }
    }
}
