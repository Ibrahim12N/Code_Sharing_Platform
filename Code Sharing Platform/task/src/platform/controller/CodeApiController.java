package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.service.CodeService;
import platform.entity.CodeSnippet;

import java.util.List;
import java.util.Map;

@RestController
public class CodeApiController {

    private CodeService codeService;

    @Autowired
    public CodeApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    public CodeSnippet getCode(@PathVariable("id") String id) {
        CodeSnippet codeSnippet = codeService.getCode(id);
        if (codeSnippet.isViewRestricted()) {
            codeService.updateViewById(codeSnippet);
        }
        if (codeSnippet.isTimeRestricted()) {
            codeService.updateTimeById(codeSnippet);
        }
        return codeService.getCode(id);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public List<CodeSnippet> getLatestCode() {
        return codeService.getLatestCodes();
    }

    @PostMapping(path = "/api/code/new", produces = "application/json;charset=UTF-8")
    public Map<String, String> addCode(@RequestBody CodeSnippet newCode) {
        newCode.setViewRestricted(newCode.getViews() > 0);
        newCode.setTimeRestricted(newCode.getTime() > 0);
        codeService.addCode(newCode);
        String uuid = codeService.addCode(newCode);
        return Map.of("id",uuid);
    }

}
